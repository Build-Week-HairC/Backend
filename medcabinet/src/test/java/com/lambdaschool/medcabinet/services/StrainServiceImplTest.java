package com.lambdaschool.medcabinet.services;

import com.lambdaschool.medcabinet.MedCabinetApplication;
import com.lambdaschool.medcabinet.exceptions.ResourceFoundException;
import com.lambdaschool.medcabinet.exceptions.ResourceNotFoundException;
import com.lambdaschool.medcabinet.models.ResStrain;
import com.lambdaschool.medcabinet.models.User;
import com.lambdaschool.medcabinet.models.UserRoles;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MedCabinetApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StrainServiceImplTest
{
  @Autowired
  private StrainService strainService;

  @Before
  public void setUp() throws Exception
  {
    MockitoAnnotations.initMocks(this);
  }

  @After
  public void tearDown() throws Exception
  {
  }

  @Test
  public void A_findAll()
  {
    assertEquals(3, strainService.findAll().size());
  }

  @Test
  public void B_findByUsername()
  {
    assertEquals(1, strainService.findByUsername("admin").size());
    assertEquals(3, strainService.findByUsername("admin").get(0).getEffects().size());
    assertEquals(2, strainService.findByUsername("admin").get(0).getFlavors().size());
  }

  @Test
  public void C_findById()
  {
    assertEquals("test1", strainService.findById(14L).getStrain());
  }

  @Test
  public void D_save()
  {
    ResStrain testStrain = new ResStrain("teststrain", "testtype", 3.3, "this is a test strain");

    assertEquals("teststrain", strainService.save(testStrain).getStrain());
  }

  @Test
  public void E_addToUser()
  {
    ResStrain testStrain = new ResStrain("coolstrain", "testtype", 3.3, "this is a test strain");

    assertEquals("coolstrain", strainService.addToUser("testmon", testStrain).getStrain());
  }

  @Test(expected = ResourceFoundException.class)
  public void F_foundAddToUser()
  {
    ResStrain testStrain = new ResStrain("coolstrain", "testtype", 3.3, "this is a test strain");

    assertEquals("coolstrain", strainService.addToUser("testmon", testStrain).getStrain());
  }

  @Test
  public void H_deleteUserStrain()
  {
    ResStrain testStrain = new ResStrain("nicestrain", "testtype", 3.3, "this is a test strain");
    strainService.addToUser("testmon", testStrain);

    strainService.deleteUserStrain("testmon", "nicestrain");
    assertEquals(false, strainService.findByUsername("testmon").contains(testStrain));
  }

  @Test(expected = ResourceNotFoundException.class)
  public void I_notFoundDeleteUserStrain()
  {
    ResStrain testStrain = new ResStrain("nicestrain", "testtype", 3.3, "this is a test strain");

    strainService.deleteUserStrain("testmon", "nicestrain");
    assertEquals(false, strainService.findByUsername("testmon").contains(testStrain));
  }
}