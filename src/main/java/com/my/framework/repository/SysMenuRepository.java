package com.my.framework.repository;

import com.my.framework.domain.SysMenu;

import com.my.framework.domain.enumeration.MenuStatusType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data  repository for the SysMenu entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysMenuRepository extends JpaRepository<SysMenu, Long> {

    boolean existsByFullNameAndStatus(String fullName, MenuStatusType status);

    Optional<SysMenu> findByFullNameAndStatus(String fullName, MenuStatusType status);
}
