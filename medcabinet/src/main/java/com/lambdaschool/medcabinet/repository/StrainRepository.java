package com.lambdaschool.medcabinet.repository;

import com.lambdaschool.medcabinet.models.Strain;
import com.lambdaschool.medcabinet.view.JustTheCount;
import com.lambdaschool.medcabinet.view.StrainView;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StrainRepository extends CrudRepository<Strain, Long>
{
  @Query(value = "SELECT s.strainid, s.strain, s.type, s.rating, s.description FROM strains s JOIN userstrains us ON s.strainid = us.strainid WHERE us.userid = :userid",
         nativeQuery = true)
  List<StrainView> findByUserId(Long userid);

  Strain findByStrain(String strain);

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

  @Transactional
  @Modifying
  @Query(value = "DELETE FROM userstrains WHERE userid = :userid AND strainid = :strainid",
         nativeQuery = true)
  void deleteUserStrain(Long userid,
                       Long strainid);
}
