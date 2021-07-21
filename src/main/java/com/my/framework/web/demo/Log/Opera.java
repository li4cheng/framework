package com.my.framework.web.demo.Log;

public class Opera {

    String logCode;

    Long operatorId;

    public String getLogCode() {
        return logCode;
    }

    public void setLogCode(String logCode) {
        this.logCode = logCode;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    @Override
    public String toString() {
        return "Opera{" +
            "logCode='" + logCode + '\'' +
            ", operatorId=" + operatorId +
            '}';
    }
}
