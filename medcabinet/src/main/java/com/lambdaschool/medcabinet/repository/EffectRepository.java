package com.lambdaschool.medcabinet.repository;

import com.lambdaschool.medcabinet.models.Effect;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EffectRepository extends CrudRepository<Effect, Long>
{
  Effect findEffectByEffectname(String effectname);

  @Transactional
  @Modifying
  @Query(value = "INSERT INTO straineffects (strainid, effectid) VALUES (:strainid, :effectid)",
         nativeQuery = true)
  void insertStrainEffect(long strainid,
                        long effectid);

  @Query(value = "SELECT e.effectname FROM effects e JOIN straineffects se ON e.effectid = se.effectid WHERE se.strainid = :strainid",
         nativeQuery = true)
  List<String> findByUserId(Long strainid);
}
