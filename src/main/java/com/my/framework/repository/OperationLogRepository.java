package com.my.framework.repository;

import com.my.framework.domain.OperationLog;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the OperationLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OperationLogRepository extends JpaRepository<OperationLog, Long> {
}
