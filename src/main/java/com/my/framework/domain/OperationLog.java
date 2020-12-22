package com.my.framework.domain;

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
            "}";
    }
}
