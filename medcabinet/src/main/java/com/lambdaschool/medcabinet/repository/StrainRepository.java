package com.lambdaschool.medcabinet.repository;

import com.lambdaschool.medcabinet.models.Strain;
import com.lambdaschool.medcabinet.view.JustTheCount;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface StrainRepository extends CrudRepository<Strain, Long>
{
  @Query(value = "SELECT * FROM strains s JOIN userstrains us ON s.strainid = us.strainid",
         nativeQuery = true)

  Strain findByStrainname(String strainname);

  @Query(value = "SELECT COUNT(*) as count FROM userstrains WHERE userid = :userid AND strainid = :strainid",
         nativeQuery = true)
  JustTheCount checkUserStrainsCombo(long userid,
                               long strainid);

  @Transactional
  @Modifying
  @Query(value = "INSERT INTO userstrains (userid, strainid) VALUES (:userid, :strainid)",
         nativeQuery = true)
  void insertUserStrain(long userid,
                   long strainid);
}
