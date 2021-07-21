package com.my.framework.web.demo.Log;

import com.my.framework.customConfig.currentUser.CurrentUser;
import com.my.framework.customConfig.currentUser.UserModel;
import com.my.framework.customConfig.log.Log;
import com.my.framework.customConfig.log.LogTag;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/api/log")
@Api(tags = "日志测试")
public class LogResource {

    private static Logger log = LoggerFactory.getLogger(LogResource.class);

    @PostMapping("/test/{id}")
    @ApiOperation(value = "日志测试")
    @Log(tag = LogTag.TEST)
    public ResponseEntity<String> test(@PathVariable Long id, @RequestParam String name, @RequestBody Opera opera, @ApiIgnore @CurrentUser UserModel userModel) {

        return ResponseEntity.ok(id + " " + name + " " + opera.toString());
    }
}
