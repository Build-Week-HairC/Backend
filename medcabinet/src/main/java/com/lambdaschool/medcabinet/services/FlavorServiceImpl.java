package com.lambdaschool.medcabinet.services;

import com.lambdaschool.medcabinet.models.Flavor;
import com.lambdaschool.medcabinet.repository.FlavorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "flavorService")
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
        Flavor currentFlavor = flavorrepos.findFlavorByFlavorname(flavorname);
        if (flavorrepos.checkStrainFlavorCombo(strainid, currentFlavor.getFlavorid()).getCount() <= 0)
        {
          flavorrepos.insertStrainFlavor(strainid, currentFlavor.getFlavorid());
        }
        else
        {
          // do nothing
        }
      } else
      {
        Flavor newEffect = this.save(flavorname);
        flavorrepos.insertStrainFlavor(strainid, newEffect.getFlavorid());
      }
    }
  }

}
