package com.lambdaschool.medcabinet.services;

import com.lambdaschool.medcabinet.models.ResStrain;
import com.lambdaschool.medcabinet.models.Strain;
import com.lambdaschool.medcabinet.view.StrainView;

import java.util.List;

public interface StrainService
{
  List<Strain> findAll();

  List<StrainView> findByUserId(Long userid);

  Strain save(ResStrain strain);

  Strain addToUser(Long userid, ResStrain strain);

  Strain update(Strain strain, Long strainid);

  void deleteUserStrain(Long strainid, Long userid);
}
