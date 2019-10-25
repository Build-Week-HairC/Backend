package com.lambdaschool.medcabinet.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.medcabinet.models.*;
import com.lambdaschool.medcabinet.services.EffectService;
import com.lambdaschool.medcabinet.services.FlavorService;
import com.lambdaschool.medcabinet.services.StrainService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@WebMvcTest(value = StrainController.class, secure = false)
public class StrainControllerTest
{
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private StrainService strainService;

  @MockBean
  private EffectService effectService;

  @MockBean
  private FlavorService flavorService;

  private List<ResStrain> resStrainList;
  private List<Strain> strainList;

  @Before
  public void setUp() throws Exception
  {
    resStrainList = new ArrayList<>();
    strainList = new ArrayList<>();

    Strain s1 = new Strain("test1", "hybrid", 4.0, "$100 OG is a 50/50 hybrid strain that packs a strong punch. The name supposedly refers to both its strength and high price when it first started showing up in Hollywood.");
    Strain s2 = new Strain("test2", "hybrid", 4.7, "The ‘98 Aloha White Widow is an especially potent cut of White Widow that has grown in renown alongside Hawaiian legends like Maui Wowie and Kona Gold.");
    Strain s3 = new Strain("test3", "sativa", 4.4, "1024 is a sativa-dominant hybrid bred in Spain by Medical Seeds Co. The breeders claim to guard the secret genetics due to security reasons, but regardless of its genetic heritage, 1024 is a THC power");

    ResStrain rs1 = new ResStrain("test1", "hybrid", 4.0, "$100 OG is a 50/50 hybrid strain that packs a strong punch. The name supposedly refers to both its strength and high price when it first started showing up in Hollywood.");
    ResStrain rs2 = new ResStrain("test2", "hybrid", 4.7, "The ‘98 Aloha White Widow is an especially potent cut of White Widow that has grown in renown alongside Hawaiian legends like Maui Wowie and Kona Gold.");
    ResStrain rs3 = new ResStrain("test3", "sativa", 4.4, "1024 is a sativa-dominant hybrid bred in Spain by Medical Seeds Co. The breeders claim to guard the secret genetics due to security reasons, but regardless of its genetic heritage, 1024 is a THC power");

    List<String> s1Effects = new ArrayList<>();
    s1Effects.add("uplifted");
    s1Effects.add("euphoric");
    s1Effects.add("giggly");

    List<String> s1Flavors = new ArrayList<>();
    s1Flavors.add("tobacco");
    s1Flavors.add("earthy");

    effectService.saveList(s1Effects, 14L);
    flavorService.saveList(s1Flavors,14L);

    resStrainList.add(rs1);
    resStrainList.add(rs2);
    resStrainList.add(rs3);

    strainList.add(s1);
    strainList.add(s2);
    strainList.add(s3);
  }

  @After
  public void tearDown() throws Exception
  {
  }

  @Test
  public void listAllStrains() throws Exception
  {
    String apiUrl = "/strains/strains/all";

    Mockito.when(strainService.findAll()).thenReturn(strainList);

    RequestBuilder requestBuilder = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);

    MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
    String actualResult = mvcResult.getResponse().getContentAsString();

    ObjectMapper mapper = new ObjectMapper();
    String expectedResult = mapper.writeValueAsString(strainList);

    assertEquals("listAllStrains returns list", expectedResult, actualResult);
  }

  @Test
  public void findStrainsByUser() throws Exception
  {
    String apiUrl = "/strains/strains/user";

    Mockito.when(strainService.findByUsername("fjkdfjd")).thenReturn(resStrainList);

    RequestBuilder requestBuilder = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);

    MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
    String actualResult = mvcResult.getResponse().getContentAsString();

    ObjectMapper mapper = new ObjectMapper();
    String expectedResult = mapper.writeValueAsString(resStrainList);

    assertEquals("findStrainsByUser returns list", expectedResult, actualResult);
  }

  @Test
  public void addStrainToUser() throws Exception
  {
//    String apiUrl = "/strains/strain";
//
//    ArrayList<String> effects = new ArrayList<>();
//    ArrayList<String> flavors = new ArrayList<>();
//    ResStrain testStrain = new ResStrain();
//    testStrain.setStrainid(222L);
//    testStrain.setStrain("testname");
//    testStrain.setType("type");
//    testStrain.setRating(5.5);
//    testStrain.setDescription("this is a test strain");
//    testStrain.setEffects(effects);
//    testStrain.setFlavors(flavors);
//
//    ObjectMapper mapper = new ObjectMapper();
//    String strainString = mapper.writeValueAsString(testStrain);
//
//    Mockito.when(strainService.addToUser(any(String.class), any(ResStrain.class))).thenReturn(testStrain);
//
//    RequestBuilder requestBuilder = MockMvcRequestBuilders.post(apiUrl).contentType(MediaType.APPLICATION_JSON)
//                                                                        .accept(MediaType.APPLICATION_JSON)
//                                                                        .content(strainString);
//
//    mockMvc.perform(requestBuilder).andExpect(st)
  }

  @Test
  public void deleteStrainFromUser()
  {
  }
}