package com.my.framework.yApi;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

public class ${modelName?cap_first} {
    <#list properties as value>

    @ApiModelProperty(value = "${value.description}")
    private ${value.type} ${value.properties};
    </#list>
    <#list properties as value>

    public ${value.type} get${value.properties?cap_first}() {
        return ${value.properties};
    }

    public void set${value.properties?cap_first}(${value.type} ${value.properties}) {
        this.${value.properties} = ${value.properties};
    }
    </#list>
}
