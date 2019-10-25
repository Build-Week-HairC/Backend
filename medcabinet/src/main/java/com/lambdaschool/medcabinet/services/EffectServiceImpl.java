package com.lambdaschool.medcabinet.services;

import com.lambdaschool.medcabinet.exceptions.ResourceFoundException;
import com.lambdaschool.medcabinet.models.Effect;
import com.lambdaschool.medcabinet.repository.EffectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "effectService")
public class EffectServiceImpl implements EffectService
{
  @Autowired
  private EffectRepository effectrepos;

  @Override
  public Effect save(String effectname)
  {
    Effect newEffect = new Effect();
    newEffect.setEffectname(effectname);
    return effectrepos.save(newEffect);
  }

  @Override
  public void saveList(List<String> effects, Long strainid)
  {
    for (String effectname : effects)
    {
      if (effectrepos.findEffectByEffectname(effectname) != null)
      {
        Effect currentEffect = effectrepos.findEffectByEffectname(effectname);
        if (effectrepos.checkStrainEffectCombo(strainid, currentEffect.getEffectid()).getCount() <= 0)
        {
          effectrepos.insertStrainEffect(strainid, currentEffect.getEffectid());
        }
        else
        {
          // do nothing
        }
      } else
      {
        Effect newEffect = this.save(effectname);
        effectrepos.insertStrainEffect(strainid, newEffect.getEffectid());
      }
    }
  }

}
