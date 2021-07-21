package com.my.framework.customConfig.yapi.yApi;

import io.swagger.annotations.ApiModelProperty;

public class PropertiesDTO {

    @ApiModelProperty(value = "字段")
    private String properties;

    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "描述")
    private String description;

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
