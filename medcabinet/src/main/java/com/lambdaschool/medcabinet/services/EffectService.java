package com.lambdaschool.medcabinet.services;

import com.lambdaschool.medcabinet.models.Effect;

import java.util.List;

public interface EffectService
{
  Effect save(String effectname);

  void saveList(List<String> effects, Long strainid);
}
