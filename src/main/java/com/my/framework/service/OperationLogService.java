package com.my.framework.service;

import com.my.framework.customConfig.error.CustomException;
import com.my.framework.domain.OperationLog;
import com.my.framework.domain.User;
import com.my.framework.repository.OperationLogRepository;
import com.my.framework.repository.UserRepository;
import com.my.framework.security.SecurityUtils;
import com.my.framework.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class OperationLogService {

    Logger log = LoggerFactory.getLogger(OperationLogService.class);

    private final UserRepository userRepository;
    private final OperationLogRepository operationLogRepository;

    public OperationLogService(UserRepository userRepository, OperationLogRepository operationLogRepository) {
        this.userRepository = userRepository;
        this.operationLogRepository = operationLogRepository;
    }

    public void saveOperationLog(String interfaceName) {
        OperationLog operationLog = new OperationLog();
        operationLog.setOperationLogCode(Utils.getUUID());
        operationLog.setInterfaceName(interfaceName);
        operationLog.setOperationTime(Instant.now());
        if (!SecurityUtils.getCurrentUserLogin().isPresent()) {
            throw new CustomException("用户未登录");
        }
        String login = SecurityUtils.getCurrentUserLogin().get();
        Optional<User> userOpt = userRepository.findOneByLogin(login);
        if (!userOpt.isPresent()) {
            throw new CustomException("用户不存在");
        }
        operationLog.setOperatorId(userOpt.get().getId());
        operationLog.setOperatorName(userOpt.get().getName());
        operationLogRepository.save(operationLog);
    }
}
