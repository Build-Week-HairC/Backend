package com.lambdaschool.medcabinet.services;

import com.lambdaschool.medcabinet.models.Strain;

import java.util.List;

public interface StrainService
{
  List<Strain> findAll();

  List<Strain> findByUserId(Long userid);

  Strain save(Strain strain);

  void addToUser(Long userid, Strain strain);

  Strain update(Strain strain, Long strainid);

  void deleteUserStrain(Long strainid, Long userid);
}
