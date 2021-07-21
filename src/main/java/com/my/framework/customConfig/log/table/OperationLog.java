package com.my.framework.customConfig.log.table;

import com.my.framework.customConfig.log.LogTag;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A OperationLog.
 */
@Entity
@Table(name = "operation_log")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OperationLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 操作日志编号
     */
    @ApiModelProperty(value = "操作日志编号")
    @Column(name = "operation_log_code")
    private String operationLogCode;

    /**
     * 操作人ID
     */
    @ApiModelProperty(value = "操作人ID")
    @Column(name = "operator_id")
    private Long operatorId;

    /**
     * 操作人姓名
     */
    @ApiModelProperty(value = "操作人姓名")
    @Column(name = "operator_name")
    private String operatorName;

    /**
     * 操作时间
     */
    @ApiModelProperty(value = "操作时间")
    @Column(name = "operation_time")
    private Instant operationTime;

    /**
     * 调用接口名称
     */
    @ApiModelProperty(value = "调用接口名称")
    @Column(name = "interface_name")
    private String interfaceName;

    /**
     * 传参
     */
    @ApiModelProperty(value = "传参")
    @Column(name = "param_json")
    private String paramJson;

    /**
     * 返回值
     */
    @ApiModelProperty(value = "返回值")
    @Column(name = "return_value")
    private String returnValue;

    /**
     * 标签
     */
    @ApiModelProperty(value = "标签")
    @Enumerated(EnumType.STRING)
    @Column(name = "tag")
    private LogTag tag;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOperationLogCode() {
        return operationLogCode;
    }

    public OperationLog operationLogCode(String operationLogCode) {
        this.operationLogCode = operationLogCode;
        return this;
    }

    public void setOperationLogCode(String operationLogCode) {
        this.operationLogCode = operationLogCode;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public OperationLog operatorId(Long operatorId) {
        this.operatorId = operatorId;
        return this;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public OperationLog operatorName(String operatorName) {
        this.operatorName = operatorName;
        return this;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public Instant getOperationTime() {
        return operationTime;
    }

    public OperationLog operationTime(Instant operationTime) {
        this.operationTime = operationTime;
        return this;
    }

    public void setOperationTime(Instant operationTime) {
        this.operationTime = operationTime;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public OperationLog interfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
        return this;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getParamJson() {
        return paramJson;
    }

    public OperationLog paramJson(String paramJson) {
        this.paramJson = paramJson;
        return this;
    }

    public void setParamJson(String paramJson) {
        this.paramJson = paramJson;
    }

    public String getReturnValue() {
        return returnValue;
    }

    public OperationLog returnValue(String returnValue) {
        this.returnValue = returnValue;
        return this;
    }

    public void setReturnValue(String returnValue) {
        this.returnValue = returnValue;
    }

    public LogTag getTag() {
        return tag;
    }

    public OperationLog tag(LogTag tag) {
        this.tag = tag;
        return this;
    }

    public void setTag(LogTag tag) {
        this.tag = tag;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OperationLog)) {
            return false;
        }
        return id != null && id.equals(((OperationLog) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OperationLog{" +
            "id=" + getId() +
            ", operationLogCode='" + getOperationLogCode() + "'" +
            ", operatorId=" + getOperatorId() +
            ", operatorName='" + getOperatorName() + "'" +
            ", operationTime='" + getOperationTime() + "'" +
            ", interfaceName='" + getInterfaceName() + "'" +
            ", paramJson='" + getParamJson() + "'" +
            ", returnValue='" + getReturnValue() + "'" +
            ", tag='" + getTag() + "'" +
            "}";
    }
}
