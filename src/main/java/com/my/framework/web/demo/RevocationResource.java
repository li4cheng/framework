package com.my.framework.web.demo;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.framework.domain.OperationLog;
import com.my.framework.repository.OperationLogRepository;
import com.my.framework.utils.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.SerializationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@Transactional
@RestController
@RequestMapping("/api/revocation")
@Api(tags = "撤销测试")
public class RevocationResource {

    private static final Logger log = LoggerFactory.getLogger(RevocationResource.class);

    private final ObjectMapper objectMapper;
    private final OperationLogRepository operationLogRepository;

    public RevocationResource(ObjectMapper objectMapper, OperationLogRepository operationLogRepository) {
        this.objectMapper = objectMapper;
        this.operationLogRepository = operationLogRepository;
    }

    @GetMapping("/test")
    @ApiOperation(value = "撤销测试")
    public ResponseEntity<List<JsonUtils.ChangeLog>> test() {
        OperationLog log1 = operationLogRepository.findById(11L).orElse(new OperationLog());
        OperationLog log2 = SerializationUtils.clone(log1);
        log2.setInterfaceName("kkk");
        log2.setOperatorId(123123123L);
        List<JsonUtils.ChangeLog> changeLogList = JsonUtils.getDiffList(log1, log2);

        return ResponseEntity.ok(changeLogList);
    }

    @GetMapping("/test2")
    public ResponseEntity<Void> test2() {
        TreeNode<String> treeNode = new TreeNode<String>()
            .id(1L)
            .parentId(null)
            .date("一")
            .childTreeNode(
                new TreeNode<String>().id(2L).parentId(1L).date("一一"),
                new TreeNode<String>().id(3L).parentId(1L).date("一二")
            );

        String json = JSONObject.toJSONString(treeNode);
        System.out.println(json);

        return ResponseEntity.noContent().build();
    }

    private static class TreeNode<T> {

        @ApiModelProperty(value = "本级id")
        private Long id;

        @ApiModelProperty(value = "上级id")
        private Long parentId;

        @ApiModelProperty(value = "数据")
        private T date;

        @ApiModelProperty(value = "子目录")
        private List<TreeNode<T>> childTreeNode;

        public Long getId() {
            return id;
        }

        public TreeNode<T> id(Long id) {
            this.id = id;
            return this;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getParentId() {
            return parentId;
        }

        public TreeNode<T> parentId(Long parentId) {
            this.parentId = parentId;
            return this;
        }

        public void setParentId(Long parentId) {
            this.parentId = parentId;
        }

        public T getDate() {
            return date;
        }

        public TreeNode<T> date(T date) {
            this.date = date;
            return this;
        }

        public void setDate(T date) {
            this.date = date;
        }

        public List<TreeNode<T>> getChildTreeNode() {
            return childTreeNode;
        }

        @SafeVarargs
        public final TreeNode<T> childTreeNode(TreeNode<T>... childTreeNode) {
            this.childTreeNode = Arrays.asList(childTreeNode);
            return this;
        }

        public void setChildTreeNode(List<TreeNode<T>> childTreeNode) {
            this.childTreeNode = childTreeNode;
        }
    }
}
