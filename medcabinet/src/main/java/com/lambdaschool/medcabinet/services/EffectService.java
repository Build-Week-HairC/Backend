package com.lambdaschool.medcabinet.services;

import com.lambdaschool.medcabinet.models.Effect;

import java.util.List;

public interface EffectService
{
  Effect save(String effectname);

  void delete(long effectid);

  void saveList(List<String> effects, Long strainid);
}
