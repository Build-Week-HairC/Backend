package com.lambdaschool.medcabinet.services;

import com.lambdaschool.medcabinet.models.ResStrain;
import com.lambdaschool.medcabinet.models.Strain;
import com.lambdaschool.medcabinet.view.StrainView;

import java.util.List;

public interface StrainService
{
  List<Strain> findAll();

  List<ResStrain> findByUsername(String username);

  ResStrain findById(Long strainid);

  Strain save(ResStrain strain);

  Strain addToUser(String username, ResStrain strain);

  Strain update(Strain strain, Long strainid);

  void deleteUserStrain(String username, String strainname);
}
