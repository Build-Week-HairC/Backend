package com.lambdaschool.medcabinet.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "effects")
public class Effect
{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long effectid;

  @Column(nullable = false, unique = true)
  private String effectname;

  @ManyToMany(mappedBy = "effects")
  @JsonIgnoreProperties("effects")
  private List<Strain> strains = new ArrayList<>();

  public Effect()
  {
  }

  public Effect(String effectname)
  {
    this.effectname = effectname;
  }

  public long getEffectid()
  {
    return effectid;
  }

  public void setEffectid(long effectid)
  {
    this.effectid = effectid;
  }

  public String getEffectname()
  {
    return effectname;
  }

  public void setEffectname(String effectname)
  {
    this.effectname = effectname;
  }

  public List<Strain> getStrains()
  {
    return strains;
  }

  public void setStrains(List<Strain> strains)
  {
    this.strains = strains;
  }

  @Override
  public String toString()
  {
    return "Effect{" + "effectid=" + effectid + ", effectname='" + effectname + '\'' + ", strains=" + strains + '}';
  }
}
