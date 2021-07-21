package com.my.framework.web.demo.Revocation;

import com.my.framework.customConfig.log.table.OperationLog;
import com.my.framework.repository.OperationLogRepository;
import com.my.framework.utils.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.SerializationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Transactional
@RestController
@RequestMapping("/api/revocation")
@Api(tags = "撤销(todo)")
public class RevocationResource {

    private static final Logger log = LoggerFactory.getLogger(RevocationResource.class);

    private final OperationLogRepository operationLogRepository;

    public RevocationResource(OperationLogRepository operationLogRepository) {
        this.operationLogRepository = operationLogRepository;
    }

    @GetMapping("/test")
    @ApiOperation(value = "撤销")
    public ResponseEntity<List<JsonUtils.ChangeLog>> test() {
        OperationLog log1 = operationLogRepository.findById(11L).orElse(new OperationLog());
        OperationLog log2 = SerializationUtils.clone(log1);
        log2.setInterfaceName("kkk");
        log2.setOperatorId(123123123L);
        List<JsonUtils.ChangeLog> changeLogList = JsonUtils.getDiffList(log1, log2);

        return ResponseEntity.ok(changeLogList);
    }
}
