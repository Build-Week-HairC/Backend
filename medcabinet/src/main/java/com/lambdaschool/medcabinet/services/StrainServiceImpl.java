package com.lambdaschool.medcabinet.services;

import com.lambdaschool.medcabinet.models.Strain;
import com.lambdaschool.medcabinet.repository.StrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "strainService")
public class StrainServiceImpl implements StrainService
{
  @Autowired
  private StrainRepository strainrepos;

  @Override
  public List<Strain> findByUserId(long userid)
  {
    return null;
  }

  @Override
  public Strain addToUser(long userid)
  {
    return null;
  }

  @Override
  public Strain update(Strain strain, long strainid)
  {
    return null;
  }

  @Override
  public void deleteUserStrain(long strainid, long userid)
  {

  }
}
