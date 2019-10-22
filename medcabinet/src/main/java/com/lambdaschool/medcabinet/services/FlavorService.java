package com.lambdaschool.medcabinet.services;

import com.lambdaschool.medcabinet.models.Flavor;

import java.util.List;

public interface FlavorService
{
  Flavor save(String flavorname);

  void delete(long flavorid);

  void saveList(List<String> flavors, Long strainid);
}
