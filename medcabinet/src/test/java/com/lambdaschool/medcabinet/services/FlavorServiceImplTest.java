package com.lambdaschool.medcabinet.services;

import com.lambdaschool.medcabinet.MedCabinetApplication;
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
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MedCabinetApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FlavorServiceImplTest
{

  @Autowired
  private FlavorService flavorService;

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
  public void save()
  {
    assertEquals("testflavor", flavorService.save("testflavor").getFlavorname());
  }

  @Test
  public void saveList()
  {
    List<String> flavors = new ArrayList<>();
    flavors.add("earthy");
    flavors.add("flowery");
    flavorService.saveList(flavors, 15L);
    assertEquals(2, strainService.findById(15L).getFlavors().size());
  }
}