package com.lambdaschool.medcabinet.services;

import com.lambdaschool.medcabinet.exceptions.ResourceFoundException;
import com.lambdaschool.medcabinet.exceptions.ResourceNotFoundException;
import com.lambdaschool.medcabinet.models.*;
import com.lambdaschool.medcabinet.repository.EffectRepository;
import com.lambdaschool.medcabinet.repository.FlavorRepository;
import com.lambdaschool.medcabinet.repository.StrainRepository;
import com.lambdaschool.medcabinet.repository.UserRepository;
import com.lambdaschool.medcabinet.view.EffectName;
import com.lambdaschool.medcabinet.view.StrainView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "strainService")
public class StrainServiceImpl implements StrainService
{
  @Autowired
  private StrainRepository strainrepos;

  @Autowired
  private UserRepository userrepos;

  @Autowired
  private EffectRepository effectrepos;

  @Autowired
  private FlavorRepository flavorrepos;

  @Override
  public List<Strain> findAll()
  {
    List<Strain> list = new ArrayList<>();
    strainrepos.findAll().iterator().forEachRemaining(list::add);
    return list;
  }

  @Override
  public List<ResStrain> findByUsername(String username)
  {
    User currentUser = userrepos.findByUsername(username);
    List<StrainView> currentStrains = strainrepos.findByUserId(currentUser.getUserid());

//    List<EffectName> effects = effectrepos.findByUserId(currentUser.getUserid());

//    List<String> flavors = new ArrayList<>();



    List<ResStrain> resStrains = new ArrayList<>();
    for(StrainView strain : currentStrains)
    {
      ResStrain newStrain = new ResStrain();
      newStrain.setStrainid(strain.getStrainid());
      newStrain.setStrain(strain.getStrain());
      newStrain.setType(strain.getType());
      newStrain.setRating(strain.getRating());
      newStrain.setDescription(strain.getDescription());

//      System.out.println("\n\nSize of effects: " + effectrepos.findByUserId(currentUser.getUserid()).size() + "\n");

      for(EffectName e : effectrepos.findByStrainId(strain.getStrainid()))
      {
        System.out.println(e.getEffectname());
        newStrain.getEffects().add(e.getEffectname());
      }

      resStrains.add(newStrain);
    }

    return resStrains;
  }

  @Override
  public Strain findById(Long strainid)
  {
    return strainrepos.findById(strainid).orElseThrow(() -> new ResourceNotFoundException("Strain not found"));
  }

  @Override
  public Strain save(ResStrain strain)
  {
    Strain newStrain = new Strain();

    newStrain.setStrain(strain.getStrain());
    newStrain.setType(strain.getType());
    newStrain.setRating(strain.getRating());
    newStrain.setDescription(strain.getDescription());

    return strainrepos.save(newStrain);
  }

  @Override
  public Strain addToUser(String username, ResStrain strain)
  {
    User currentUser = userrepos.findByUsername(username);

    if (strainrepos.findByStrain(strain.getStrain()) != null)
    {
      Strain currentStrain = strainrepos.findByStrain(strain.getStrain());
      if (strainrepos.checkUserStrainsCombo(currentUser.getUserid(), currentStrain.getStrainid()).getCount() <= 0)
      {
        strainrepos.insertUserStrain(currentUser.getUserid(), currentStrain.getStrainid());
        return currentStrain;
      } else
      {
        throw new ResourceFoundException("User has already saved this strain (User-Strain combination exists)");
      }
    } else {
      Strain newStrain = this.save(strain);
      strainrepos.insertUserStrain(currentUser.getUserid(), newStrain.getStrainid());
      return strainrepos.findByStrain(newStrain.getStrain());
    }
  }

  @Override
  public Strain update(Strain strain, Long strainid)
  {
    return null;
  }

  @Override
  public void deleteUserStrain(Long strainid, Long userid)
  {

  }
}
