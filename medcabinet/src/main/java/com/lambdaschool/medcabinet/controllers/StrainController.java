package com.lambdaschool.medcabinet.controllers;

import com.lambdaschool.medcabinet.models.ResStrain;
import com.lambdaschool.medcabinet.models.Strain;
import com.lambdaschool.medcabinet.services.EffectService;
import com.lambdaschool.medcabinet.services.FlavorService;
import com.lambdaschool.medcabinet.services.StrainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/strains")
public class StrainController
{
  private static final Logger logger = LoggerFactory.getLogger(StrainController.class);

  @Autowired
  private StrainService strainService;

  @Autowired
  private EffectService effectService;

  @Autowired
  private FlavorService flavorService;

  // find all strains
  // GET -- /strains/strains/all
  @GetMapping(value = "/strains/all",
              produces = {"application/json"})
  public ResponseEntity<?> listAllStrains(HttpServletRequest request)
  {
    logger.trace(request.getMethod()
        .toUpperCase() + " " + request.getRequestURI() + " accessed");

    return new ResponseEntity<>(strainService.findAll(), HttpStatus.OK);
  }

  // find strains by user
  // GET -- /strains/strains
  @GetMapping(value = "/strains/user",
              produces = {"application/json"})
  public ResponseEntity<?> findStrainsByUser(Authentication authentication,
                                             HttpServletRequest request)
  {
    logger.trace(request.getMethod()
        .toUpperCase() + " " + request.getRequestURI() + " accessed");

    return new ResponseEntity<>(strainService.findByUsername(authentication.getName()), HttpStatus.OK);
  }

  // add a strain to a user by user id
  // if the strain is not in the database, adds it
  // POST -- /strains/strain
  @PostMapping(value = "/strain",
               consumes = {"application/json"},
               produces = {"application/json"})
  public ResponseEntity<?> addStrainToUser(Authentication authentication,
                                           @Valid
                                           @RequestBody
                                               ResStrain strain,
                                           HttpServletRequest request)
  {
    logger.trace(request.getMethod()
        .toUpperCase() + " " + request.getRequestURI() + " accessed");

    Strain newStrain = strainService.addToUser(authentication.getName(), strain);

    effectService.saveList(strain.getEffects(), newStrain.getStrainid());
    flavorService.saveList(strain.getFlavors(), newStrain.getStrainid());

    return new ResponseEntity<>(strainService.findById(newStrain.getStrainid()), HttpStatus.OK);
  }

  // delete strain from user
  // does not delete strain from database if it belongs to other users
  // DELETE -- /strains/strain/
  @DeleteMapping(value = "/strain",
              produces = {"application/json"})
  public ResponseEntity<?> deleteStrainFromUser(Authentication authentication,
                                                @RequestBody ResStrain strain,
                                                HttpServletRequest request)
  {
    logger.trace(request.getMethod()
        .toUpperCase() + " " + request.getRequestURI() + " accessed");

    strainService.deleteUserStrain(authentication.getName(), strain.getStrain());
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
