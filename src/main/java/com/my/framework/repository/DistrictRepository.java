package com.my.framework.repository;

import com.my.framework.domain.District;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the District entity.
 */
@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {

    @Query(value = "call sel(:root_id)", nativeQuery = true)
    List<Object> call(@Param(value = "root_id") Long rootId);
}
