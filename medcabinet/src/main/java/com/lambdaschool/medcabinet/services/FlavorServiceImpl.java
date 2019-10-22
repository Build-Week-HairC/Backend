package com.lambdaschool.medcabinet.services;

import com.lambdaschool.medcabinet.models.Flavor;
import com.lambdaschool.medcabinet.repository.FlavorRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class FlavorServiceImpl implements FlavorService
{
  @Autowired
  private FlavorRepository flavorrepos;

  @Override
  public Flavor save(String flavorname)
  {
    Flavor newFlavor = new Flavor();
    newFlavor.setFlavorname(flavorname);
    return flavorrepos.save(newFlavor);
  }

  @Override
  public void saveList(List<String> flavors, Long strainid)
  {
    for (String flavorname : flavors)
    {
      if (flavorrepos.findFlavorByFlavorname(flavorname) != null)
      {
        flavorrepos.insertStrainFlavor(strainid, flavorrepos.findFlavorByFlavorname(flavorname).getFlavorid());
      } else
      {
        Flavor newEffect = this.save(flavorname);
        flavorrepos.insertStrainFlavor(strainid, newEffect.getFlavorid());
      }
    }
  }

  @Override
  public void delete(long flavorid)
  {

  }
}
