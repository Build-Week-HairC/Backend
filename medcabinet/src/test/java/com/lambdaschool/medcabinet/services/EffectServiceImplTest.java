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
public class EffectServiceImplTest
{
  @Autowired
  private EffectService effectService;

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
    assertEquals("testeffect", effectService.save("testeffect").getEffectname());
  }

  @Test
  public void saveList()
  {
    List<String> effects = new ArrayList<>();
    effects.add("happy");
    effects.add("giggly");
    effectService.saveList(effects, 15L);
    assertEquals(2, strainService.findById(15L).getEffects().size());
  }
}