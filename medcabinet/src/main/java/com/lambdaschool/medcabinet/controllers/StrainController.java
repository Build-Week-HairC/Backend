package com.lambdaschool.medcabinet.controllers;

import com.lambdaschool.medcabinet.services.StrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/strains")
public class StrainController
{

  @Autowired
  private StrainService strainService;

  // find strains by user id
  // GET -- /strains/strains/user/{userid}
  @GetMapping(value = "/strains/user/{userid}",
              produces = {"application/json"})
  public ResponseEntity<?> findStrainsByUser(@PathVariable long userid)
  {
    return new ResponseEntity<>(HttpStatus.OK);
  }

  // add a strain to a user by user id
  // if the strain is not in the database, adds it
  // POST -- /strains/strain/user/{userid}
  @PostMapping(value = "/strain/user/{userid}",
               consumes = {"application/json"},
               produces = {"application/json"})
  public ResponseEntity<?> addStrainToUser(@PathVariable long userid)
  {
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  // edit strain -- TODO what fields should update?
  // PUT -- /strains/strain/{strainid}
  @PutMapping(value = "/user/{userid}",
               consumes = {"application/json"},
               produces = {"application/json"})
  public ResponseEntity<?> editStrain(@PathVariable long userid)
  {
    return new ResponseEntity<>(HttpStatus.OK);
  }

  // delete strain from user using ids
  // does not delete strain from database if it belongs to other users
  // DELETE -- /strains/strain/{strainid}/user/{userid}
  @DeleteMapping(value = "/strain/{strainid}/user/{userid}",
              produces = {"application/json"})
  public ResponseEntity<?> findStrainsByUser(@PathVariable long strainid, @PathVariable long userid)
  {
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
