package com.my.framework.web.demo.Yapi;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.my.framework.customConfig.yapi.YApiService;
import com.my.framework.customConfig.yapi.yApi.YApiData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Transactional
@RestController
@RequestMapping("/api/yApi")
@Api(tags = "yApi导出")
public class YApiResource {

    private static final Logger log = LoggerFactory.getLogger(YApiResource.class);

    private final YApiService yApiService;

    public YApiResource(YApiService yApiService) {
        this.yApiService = yApiService;
    }

    @PostMapping("/getYApiInterJsonData")
    @ApiOperation(value = "通过yApi导出json文件生成接口与实体类")
    public ResponseEntity<Void> getYApiInterJsonData(@RequestParam MultipartFile file) throws IOException {
        String content = new String(file.getBytes());
        JSONArray jsonArray = (JSONArray) JSONArray.parse(content);
        jsonArray.forEach(jsonObject -> {
            String body = JSONObject.toJSONString(jsonObject);
            Object objectJson = JSON.parse(body);
            String s = objectJson.toString();
            YApiData yApiData = JSONObject.parseObject(s, YApiData.class);
            yApiService.saveInterface(yApiData);
        });

        return ResponseEntity.ok().build();
    }
}
