package com.lambdaschool.medcabinet.models;

import com.lambdaschool.medcabinet.logging.Loggable;

@Loggable
// matches the JSON returned from ds api
public class APIStrain
{
  private String StrainID;
  private String Strain;
  private String Type;
  private Double Rating;
  private String Effects;
  private String Flavor;
  private String Description;

  public APIStrain()
  {
  }

  public String getStrainID()
  {
    return StrainID;
  }

  public void setStrainID(String strainID)
  {
    StrainID = strainID;
  }

  public String getStrain()
  {
    return Strain;
  }

  public void setStrain(String strain)
  {
    Strain = strain;
  }

  public String getType()
  {
    return Type;
  }

  public void setType(String type)
  {
    Type = type;
  }

  public Double getRating()
  {
    return Rating;
  }

  public void setRating(Double rating)
  {
    Rating = rating;
  }

  public String getEffects()
  {
    return Effects;
  }

  public void setEffects(String effects)
  {
    Effects = effects;
  }

  public String getFlavor()
  {
    return Flavor;
  }

  public void setFlavor(String flavor)
  {
    Flavor = flavor;
  }

  public String getDescription()
  {
    return Description;
  }

  public void setDescription(String description)
  {
    Description = description;
  }

  @Override
  public String toString()
  {
    return "APIStrain{" + "StrainID='" + StrainID + '\'' + ", Strain='" + Strain + '\'' + ", Type='" + Type + '\'' + ", Rating=" + Rating + ", Effects='" + Effects + '\'' + ", Flavor='" + Flavor + '\'' + ", Description='" + Description + '\'' + '}';
  }
}
