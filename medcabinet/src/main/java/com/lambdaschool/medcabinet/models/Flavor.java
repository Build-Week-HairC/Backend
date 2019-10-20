package com.lambdaschool.medcabinet.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "flavors")
public class Flavor
{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long flavorid;

  @Column(nullable = false, unique = true)
  private String flavorname;

  @ManyToMany(mappedBy = "flavors")
  @JsonIgnoreProperties("flavors")
  private List<Strain> strains = new ArrayList<>();

  public Flavor()
  {
  }

  public Flavor(String flavorname)
  {
    this.flavorname = flavorname;
  }

  public long getFlavorid()
  {
    return flavorid;
  }

  public void setFlavorid(long flavorid)
  {
    this.flavorid = flavorid;
  }

  public String getFlavorname()
  {
    return flavorname;
  }

  public void setFlavorname(String flavorname)
  {
    this.flavorname = flavorname;
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
    return "Flavor{" + "flavorid=" + flavorid + ", flavorname='" + flavorname + '\'' + ", strains=" + strains + '}';
  }
}
