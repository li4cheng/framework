package com.my.framework.repository;

import com.my.framework.customConfig.log.table.OperationLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the OperationLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OperationLogRepository extends JpaRepository<OperationLog, Long> {
}
