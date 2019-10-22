package com.lambdaschool.medcabinet.models;

import com.fasterxml.jackson.annotation.JsonProperty;
public class APIStrain
{
  @JsonProperty("StrainID")
  private Long StrainID;
  @JsonProperty("Strain")
  private String Strain;
  @JsonProperty("Type")
  private String Type;
  @JsonProperty("Rating")
  private Double Rating;
  @JsonProperty("Effects")
  private String Effects;
  @JsonProperty("Flavor")
  private String Flavor;
  @JsonProperty("Description")
  private String Description;
  public APIStrain()
  {
  }
  public Long getStrainID()
  {
    return StrainID;
  }
  public void setStrainID(Long strainID)
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
