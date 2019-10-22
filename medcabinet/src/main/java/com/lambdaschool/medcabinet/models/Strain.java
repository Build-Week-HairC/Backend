package com.lambdaschool.medcabinet.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "strains")
public class Strain extends Auditable
{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long strainid;

  @Column(nullable = false, unique = true)
  private String strain;

  @Column(nullable = false)
  private String type;

  @Column(nullable = false)
  private double rating;

  private String description;

  @ManyToMany
  @JoinTable(name = "userstrains",
             joinColumns = {@JoinColumn(name = "strainid")},
             inverseJoinColumns = {@JoinColumn(name = "userid")})
  @JsonIgnoreProperties("strains")
  private List<User> users = new ArrayList<>();

  @ManyToMany
  @JoinTable(name = "straineffects",
             joinColumns = {@JoinColumn(name = "strainid")},
             inverseJoinColumns = {@JoinColumn(name = "effectid")})
  @JsonIgnoreProperties("strains")
  private List<Effect> effects = new ArrayList<>();

  @ManyToMany
  @JoinTable(name = "strainflavors",
             joinColumns = {@JoinColumn(name = "strainid")},
             inverseJoinColumns = {@JoinColumn(name = "flavorid")})
  @JsonIgnoreProperties("strains")
  private List<Flavor> flavors = new ArrayList<>();

  public Strain()
  {
  }

  public Strain(String strain, String type, Double rating, String description)
  {
    this.strain = strain;
    this.type = type;
    this.rating = rating;
    this.description = description;
  }

  public Long getStrainid()
  {
    return strainid;
  }

  public void setStrainid(long strainid)
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

  public double getRating()
  {
    return rating;
  }

  public void setRating(Double rating)
  {
    this.rating = rating;
  }

  public String getDescription()
  {
    return description;
  }

  public void setDescription(String description)
  {
    this.description = description;
  }

  public List<User> getUsers()
  {
    return users;
  }

  public void setUsers(List<User> users)
  {
    this.users = users;
  }

  public List<Effect> getEffects()
  {
    return effects;
  }

  public void setEffects(List<Effect> effects)
  {
    this.effects = effects;
  }

  public List<Flavor> getFlavors()
  {
    return flavors;
  }

  public void setFlavors(List<Flavor> flavors)
  {
    this.flavors = flavors;
  }

  @Override
  public String toString()
  {
    return "Strain{" + "strainid=" + strainid + ", strainname='" + strain + '\'' + ", type='" + type + '\'' + ", rating=" + rating + ", description='" + description + '\'' + ", users=" + users + ", effects=" + effects + ", flavors=" + flavors + '}';
  }
}
