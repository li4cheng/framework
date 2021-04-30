package com.my.framework.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModelProperty;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;
import org.javers.core.diff.changetype.ValueChange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JsonUtils {

    private static final Logger log = LoggerFactory.getLogger(JsonUtils.class);

    private static final Javers javers = JaversBuilder
        .javers()
        .registerValue(ZonedDateTime.class,
            (a, b) -> a.toInstant().getEpochSecond() == b.toInstant().getEpochSecond(),
            (a) -> String.valueOf(a.toInstant().getEpochSecond()))
        .build();

    public static List<ChangeLog> getDiffList(Object originCla, Object newCla) {
        Diff diff = javers.compare(originCla, newCla);
        JSONArray jsonArray = JSON.parseArray(JSONObject.toJSONString(diff.getChanges()));
        List<ChangeLog> changeLogList = jsonArray.stream()
            .map(o -> JSONObject.parseObject(JSONObject.toJSONString(o), ChangeLog.class))
            .collect(Collectors.toList());

        return changeLogList;
    }

    public static class ChangeLog {

        @ApiModelProperty(value = "修改对象类")
        private Object affectedObject;

        @ApiModelProperty(value = "原值")
        private Object left;

        @ApiModelProperty(value = "新值")
        private Object right;

        @ApiModelProperty(value = "修改对象字段")
        private String propertyNameWithPath;

        public Object getAffectedObject() {
            return affectedObject;
        }

        public void setAffectedObject(Object affectedObject) {
            this.affectedObject = affectedObject;
        }

        public Object getLeft() {
            return left;
        }

        public void setLeft(Object left) {
            this.left = left;
        }

        public Object getRight() {
            return right;
        }

        public void setRight(Object right) {
            this.right = right;
        }

        public String getPropertyNameWithPath() {
            return propertyNameWithPath;
        }

        public void setPropertyNameWithPath(String propertyNameWithPath) {
            this.propertyNameWithPath = propertyNameWithPath;
        }
    }
}
