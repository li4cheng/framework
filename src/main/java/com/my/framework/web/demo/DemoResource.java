package com.my.framework.web.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.my.framework.domain.User;
import com.my.framework.service.RequestService;
import com.my.framework.utils.Utils;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@RestController
@RequestMapping("/api")
@Api(tags = "测试")
public class DemoResource {

    private final RequestService requestService;
    private final JPAQueryFactory queryFactory;

    public DemoResource(RequestService requestService, JPAQueryFactory queryFactory) {
        this.requestService = requestService;
        this.queryFactory = queryFactory;
    }

    @GetMapping("/get")
    public ResponseEntity<Object> getTest() {
        LinkedMultiValueMap<String, String> param = new LinkedMultiValueMap<>();
        param.add("username", "quanzantest");
        param.add("password", "7074ddc3e006810688241196414e49e2");
        param.add("channelCode", "quanzan-002");
        JSONObject jsonObject = requestService.post("http://ybinsure.com/t/icar/std/login", param, null);
        String token = ((Map<String, String>)jsonObject.get("data")).get("token");
        JSONObject object = requestService.post("http://ybinsure.com/t/icar/std/channel/queryOrganization", null, token);
        LinkedMultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("organizationId", ((Map<String, List<Map<String, String>>>)(object.get("data"))).get("list").get(0).get("value"));
        JSONObject result = requestService.post("http://ybinsure.com/t/icar/std/channel/queryCompany", map, token);
        User user = JSON.parseObject(JSON.toJSONString(result), User.class);
        return ResponseEntity.ok(result);
    }
}
