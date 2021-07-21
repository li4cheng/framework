package com.my.framework.customConfig.yapi.yApi;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.Map;

public class YApiDataList {

    @ApiModelProperty(value = "接口ApiOperation")
    private String title;

    @ApiModelProperty(value = "接口路径")
    private String path;

    @ApiModelProperty(value = "接口路径及路径参数")
    private Map<String, Object> queryPath;

    @ApiModelProperty(value = "路径参数")
    private List<Map<String, Object>> reqParams;

    @ApiModelProperty(value = "GET/POST")
    private String method;

    @ApiModelProperty(value = "是否已完成（done/undone）")
    private String status;

    @ApiModelProperty(value = "POST请求参数数据列表")
    private List<Map<String, Object>> reqBodyForm;

    @ApiModelProperty(value = "POST请求参数数据列表补充")
    private Object reqBodyOther;

    @ApiModelProperty(value = "GET请求参数数据列表")
    private List<Map<String, Object>> reqQuery;

    @ApiModelProperty(value = "返回数据")
    private Object resBody;

    @ApiModelProperty(value = "参数")
    private String params;

    @ApiModelProperty(value = "返回参数")
    private String resParams;

    @ApiModelProperty(value = "方法名")
    private String methodName;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Map<String, Object> getQueryPath() {
        return queryPath;
    }

    public void setQueryPath(Map<String, Object> queryPath) {
        this.queryPath = queryPath;
    }

    public List<Map<String, Object>> getReqParams() {
        return reqParams;
    }

    public void setReqParams(List<Map<String, Object>> reqParams) {
        this.reqParams = reqParams;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Map<String, Object>> getReqBodyForm() {
        return reqBodyForm;
    }

    public void setReqBodyForm(List<Map<String, Object>> reqBodyForm) {
        this.reqBodyForm = reqBodyForm;
    }

    public Object getReqBodyOther() {
        return reqBodyOther;
    }

    public void setReqBodyOther(Object reqBodyOther) {
        this.reqBodyOther = reqBodyOther;
    }

    public List<Map<String, Object>> getReqQuery() {
        return reqQuery;
    }

    public void setReqQuery(List<Map<String, Object>> reqQuery) {
        this.reqQuery = reqQuery;
    }

    public Object getResBody() {
        return resBody;
    }

    public void setResBody(Object resBody) {
        this.resBody = resBody;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getResParams() {
        return resParams;
    }

    public void setResParams(String resParams) {
        this.resParams = resParams;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}
