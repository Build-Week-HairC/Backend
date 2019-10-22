package com.lambdaschool.medcabinet.models;

import java.util.ArrayList;
import java.util.List;

public class ResStrain
{
  private Integer strainid;
  private String strain;
  private String type;
  private Double rating;
  private List<String> effects = new ArrayList<>();
  private List<String> flavors = new ArrayList<>();
  private String description;

  public ResStrain()
  {
  }

  public ResStrain(Integer strainid, String strain, String type, Double rating, String description)
  {
    this.strainid = strainid;
    this.strain = strain;
    this.type = type;
    this.rating = rating;
    this.description = description;
  }

  public Integer getStrainid()
  {
    return strainid;
  }

  public void setStrainid(Integer strainid)
  {
    this.strainid = strainid;
  }

  public String getStrain()
  {
    return strain;
  }

  public void setStrain(String strain)
  {
    this.strain = strain;
  }

  public String getType()
  {
    return type;
  }

  public void setType(String type)
  {
    this.type = type;
  }

  public Double getRating()
  {
    return rating;
  }

  public void setRating(Double rating)
  {
    this.rating = rating;
  }

  public List<String> getEffects()
  {
    return effects;
  }

  public void setEffects(List<String> effects)
  {
    this.effects = effects;
  }

  public List<String> getFlavors()
  {
    return flavors;
  }

  public void setFlavors(List<String> flavors)
  {
    this.flavors = flavors;
  }

  public String getDescription()
  {
    return description;
  }

  public void setDescription(String description)
  {
    this.description = description;
  }

  @Override
  public String toString()
  {
    return "ResStrain{" + "strainid=" + strainid + ", strain='" + strain + '\'' + ", type='" + type + '\'' + ", rating=" + rating + ", effects=" + effects + ", flavors=" + flavors + ", description='" + description + '\'' + '}';
  }
}
