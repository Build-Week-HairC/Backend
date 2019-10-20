package com.lambdaschool.medcabinet.services;

import com.lambdaschool.medcabinet.models.Strain;

import java.util.List;

public interface StrainService
{
  List<Strain> findByUserId(long userid);

  Strain addToUser(long userid);

  Strain update(Strain strain, long strainid);

  void deleteUserStrain(long strainid, long userid);
}
