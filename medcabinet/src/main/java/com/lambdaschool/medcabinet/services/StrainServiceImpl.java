package com.lambdaschool.medcabinet.services;

import com.lambdaschool.medcabinet.exceptions.ResourceFoundException;
import com.lambdaschool.medcabinet.exceptions.ResourceNotFoundException;
import com.lambdaschool.medcabinet.models.Strain;
import com.lambdaschool.medcabinet.models.User;
import com.lambdaschool.medcabinet.repository.StrainRepository;
import com.lambdaschool.medcabinet.repository.UserRepository;
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

  @Override
  public List<Strain> findAll()
  {
    List<Strain> list = new ArrayList<>();
    strainrepos.findAll().iterator().forEachRemaining(list::add);
    return list;
  }

  @Override
  public List<Strain> findByUserId(Long userid)
  {
    List<Strain> list = new ArrayList<>();
//    strainrepos.fin
    return null;
  }

  @Override
  public Strain save(Strain strain)
  {
    Strain newStrain = new Strain();

    newStrain.setStrainname(strain.getStrainname());
    newStrain.setType(strain.getType());
    newStrain.setRating(strain.getRating());

    if (strain.getDescription() != null)
    {
      newStrain.setDescription(strain.getDescription());
    }

    return strainrepos.save(newStrain);
  }

  @Override
  public void addToUser(Long userid, Strain strain)
  {
    User user = userrepos.findById(userid).orElseThrow(() -> new ResourceNotFoundException("No user with id " + userid));

    Strain currentStrain = strainrepos.findByStrainname(strain.getStrainname());

//    if (currentStrain.getStrainid() != null)
//    {
      if (strainrepos.checkUserStrainsCombo(user.getUserid(), currentStrain.getStrainid()).getCount() <= 0)
      {
        strainrepos.insertUserStrain(user.getUserid(), currentStrain.getStrainid());
      } else
      {
        throw new ResourceFoundException("User-strain combination already exists");
      }
//    } else {
//      save(strain);
//      strainrepos.insertUserStrain(user.getUserid(), strainrepos.findByStrainname(strain.getStrainname()).getStrainid());
//    }
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
