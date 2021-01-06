package com.my.framework.service.dto.yApi;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class YApiData {

    @ApiModelProperty(value = "生成类tags")
    private String name;

    @ApiModelProperty(value = "生成类名")
    private String desc;

    @ApiModelProperty(value = "接口数据列表")
    private List<YApiDataList> list;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<YApiDataList> getList() {
        return list;
    }

    public void setList(List<YApiDataList> list) {
        this.list = list;
    }
}
