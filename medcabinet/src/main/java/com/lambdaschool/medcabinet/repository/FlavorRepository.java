package com.lambdaschool.medcabinet.repository;

import com.lambdaschool.medcabinet.models.Flavor;
import com.lambdaschool.medcabinet.view.FlavorName;
import com.lambdaschool.medcabinet.view.JustTheCount;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FlavorRepository extends CrudRepository<Flavor, Long>
{
  Flavor findFlavorByFlavorname(String flavorname);

  @Transactional
  @Modifying
  @Query(value = "INSERT INTO strainflavors (strainid, flavorid) VALUES (:strainid, :flavorid)",
         nativeQuery = true)
  void insertStrainFlavor(long strainid,
                          long flavorid);

  @Query(value = "SELECT f.flavorname as flavorname FROM flavors f JOIN strainflavors sf ON f.flavorid = sf.flavorid WHERE sf.strainid = :strainid",
         nativeQuery = true)
  List<FlavorName> findByStrainId(Long strainid);

  @Query(value = "SELECT COUNT(*) as count FROM strainflavors WHERE strainid = :strainid AND flavorid = :flavorid",
         nativeQuery = true)
  JustTheCount checkStrainFlavorCombo(long strainid,
                                     long flavorid);
}
