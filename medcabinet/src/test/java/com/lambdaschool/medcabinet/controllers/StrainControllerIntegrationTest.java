package com.lambdaschool.medcabinet.controllers;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StrainControllerIntegrationTest
{
  @Autowired
  private WebApplicationContext webApplicationContext;

  private MockMvc mockMvc;

  @Before
  public void setUp() throws Exception
  {
    RestAssuredMockMvc.webAppContextSetup(webApplicationContext);

    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
        .apply(SecurityMockMvcConfigurers.springSecurity())
        .build();
  }

  @After
  public void tearDown() throws Exception
  {
  }

  @WithUserDetails("admin")
  @Test
  public void listAllStrains() throws Exception
  {
    this.mockMvc.perform(get("/strains/strains/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("test1")));
  }

  @WithUserDetails("admin")
  @Test
  public void findStrainsByUser() throws Exception
  {
    this.mockMvc.perform(get("/strains/strains/user"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("test1")));
  }

  @WithUserDetails("admin")
  @Test
  public void addStrainToUser() throws Exception
  {
    mockMvc.perform(MockMvcRequestBuilders.post("/strains/strain")
                                          .content("{\"strain\": \"coolstrain\", \"type\": \"testtype\", \"description\" : \"hahahaha\", \"rating\" : 4.3}")
                                          .contentType(MediaType.APPLICATION_JSON)
                                          .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @WithUserDetails("admin")
  @Test
  public void deleteStrainFromUser() throws Exception
  {
    mockMvc.perform(MockMvcRequestBuilders.delete("/strains/strain")
                                          .content("{\"strain\": \"coolstrain\"}")
                                          .contentType(MediaType.APPLICATION_JSON)
                                          .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().is2xxSuccessful());
  }
}