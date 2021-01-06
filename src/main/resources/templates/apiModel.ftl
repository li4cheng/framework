package com.my.framework.yApi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Transactional
@RestController
@RequestMapping("/api")
@Api(tags = "${tags}")
<#if className??>
public class ${className} {
<#else>
public class Null {
</#if>

    private final Logger log = LoggerFactory.getLogger(${className}.class);

    <#list data as value>

    <#if value.status == "undone">
    <#if value.method == "GET">
    @GetMapping("${value.path}")
    <#else>
    @PostMapping("${value.path}")
    </#if>
    @ApiOperation(value = "${value.title + ""}")
    public ResponseEntity<${value.resParams}> ${value.methodName + "(${value.params})"} {

        return ResponseEntity.ok().build();
    }
    </#if>
    </#list>
}
