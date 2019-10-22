package com.lambdaschool.medcabinet.controllers;

import com.lambdaschool.medcabinet.models.Strain;
import com.lambdaschool.medcabinet.services.StrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/strains")
public class StrainController
{

  @Autowired
  private StrainService strainService;

  // find all strains
  // GET -- /strains/strains
  @GetMapping(value = "/strains",
              produces = {"application/json"})
  public ResponseEntity<?> listAllStrains()
  {
    return new ResponseEntity<>(strainService.findAll(), HttpStatus.OK);
  }

  // find strains by user id
  // GET -- /strains/strains/user/{userid}
  @GetMapping(value = "/strains/user/{userid}",
              produces = {"application/json"})
  public ResponseEntity<?> findStrainsByUserId(@PathVariable
                                                 Long userid)
  {
    return new ResponseEntity<>(strainService.findByUserId(userid), HttpStatus.OK);
  }

  // add a strain to a user by user id
  // if the strain is not in the database, adds it
  // POST -- /strains/strain/user/{userid}
  @PostMapping(value = "/strain/user/{userid}",
               consumes = {"application/json"},
               produces = {"application/json"})
  public ResponseEntity<?> addStrainToUser(@PathVariable Long userid,
                                           @Valid
                                           @RequestBody
                                               Strain strain)
  {
    Strain newStrain = strainService.addToUser(userid, strain);
    return new ResponseEntity<>(newStrain, HttpStatus.OK);
  }

  // edit strain -- TODO what fields should update?
  // PUT -- /strains/strain/{strainid}
  @PutMapping(value = "/user/{userid}",
               consumes = {"application/json"},
               produces = {"application/json"})
  public ResponseEntity<?> editStrain(@PathVariable Long userid)
  {
    return new ResponseEntity<>(HttpStatus.OK);
  }

  // delete strain from user using ids
  // does not delete strain from database if it belongs to other users
  // DELETE -- /strains/strain/{strainid}/user/{userid}
  @DeleteMapping(value = "/strain/{strainid}/user/{userid}",
              produces = {"application/json"})
  public ResponseEntity<?> findStrainsByUser(@PathVariable Long strainid, @PathVariable Long userid)
  {
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
