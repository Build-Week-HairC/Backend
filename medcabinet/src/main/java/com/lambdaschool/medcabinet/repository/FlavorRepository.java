package com.lambdaschool.medcabinet.repository;

import com.lambdaschool.medcabinet.models.Flavor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface FlavorRepository extends CrudRepository<Flavor, Long>
{
  Flavor findFlavorByFlavorname(String flavorname);

  @Transactional
  @Modifying
  @Query(value = "INSERT INTO strainflavors (strainid, flavorid) VALUES (:strainid, :flavorid)",
         nativeQuery = true)
  void insertStrainFlavor(long strainid,
                          long flavorid);
}
