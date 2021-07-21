package com.my.framework.customConfig.yapi;

import com.alibaba.fastjson.JSONObject;
import com.my.framework.config.ApplicationProperties;
import com.my.framework.customConfig.yapi.yApi.PropertiesDTO;
import com.my.framework.customConfig.yapi.yApi.YApiData;
import com.my.framework.customConfig.yapi.yApi.YApiDataList;
import com.my.framework.utils.Utils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class YApiService {

    private static final Logger log = LoggerFactory.getLogger(YApiService.class);

    private final ApplicationProperties applicationProperties;

    private static final String API_MODEL = "apiModel.ftl";
    private static final String DTO_MODEL = "dtoModel.ftl";

    public YApiService(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    public void saveInterface(YApiData yApiData) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("className", (StringUtils.isEmpty(yApiData.getDesc()) ? yApiData.getName() : yApiData.getDesc()).replaceAll(" ", ""));
        dataMap.put("tags", yApiData.getName());
        List<YApiDataList> list = yApiData.getList().stream().peek(yApiDataList -> {
            String interfaceName = Utils.camelCase(yApiDataList.getPath().substring(1).split("/\\{")[0]);
            List<String> params = new ArrayList<>();
            // 配置传参格式 及 对应的 QM实体类
            if ("GET".equals(yApiDataList.getMethod()) || "DELETE".equals(yApiDataList.getMethod())) {
                //PathVariable
                for (Map<String, Object> reqParam : yApiDataList.getReqParams()) {
                    params.add("@PathVariable Object " + reqParam.get("name"));
                }
                //RequestParam
                for (Map<String, Object> map : yApiDataList.getReqQuery()) {
                    if (map.get("required").equals("1")) {
                        params.add("@RequestParam String " + Utils.camelCase(map.get("name").toString()));
                    } else {
                        params.add("@RequestParam(required = false) String " + Utils.camelCase(map.get("name").toString()));
                    }
                }
            } else {
                Map<String, Object> reqDtoMap = new HashMap<>();
                reqDtoMap.put("modelName", interfaceName + "QM");
                List<PropertiesDTO> reqPropertiesDTOList = new ArrayList<>();
                for (Map<String, Object> map : yApiDataList.getReqBodyForm()) {
                    PropertiesDTO propertiesDTO = new PropertiesDTO();
                    propertiesDTO.setProperties(map.get("name").toString());
                    propertiesDTO.setType(transform(map.get("type").toString(), null));
                    propertiesDTO.setDescription(map.get("desc") == null ? "" : map.get("desc").toString());
                    reqPropertiesDTOList.add(propertiesDTO);
                }
                if (yApiDataList.getReqBodyOther() != null) {
                    JSONObject otherJson = JSONObject.parseObject(yApiDataList.getReqBodyOther().toString());
                    List<String> keyList;
                    if (otherJson.get("type").equals("array") && otherJson.get("items") != null) {
                        if (((JSONObject) otherJson.get("items")).get("properties") != null) {
                            keyList = new ArrayList<>(((JSONObject) ((JSONObject) otherJson.get("items")).get("properties")).keySet());
                            for (String key : keyList) {
                                PropertiesDTO propertiesDTO = new PropertiesDTO();
                                propertiesDTO.setProperties(key);
                                propertiesDTO.setType(transform((((JSONObject) ((JSONObject) ((JSONObject) otherJson.get("items")).get("properties")).get(key)).get("type").toString()), null));
                                Object description = (((JSONObject) ((JSONObject) ((JSONObject) otherJson.get("items")).get("properties")).get(key)).get("description"));
                                propertiesDTO.setDescription(description == null ? "" : description.toString());
                                reqPropertiesDTOList.add(propertiesDTO);
                            }
                        } else {
                            System.out.println("wkn:" + otherJson.get("items"));
                        }
                    } else {//type == object
                        if (otherJson.get("properties") != null) {
                            keyList = new ArrayList<>(((JSONObject) otherJson.get("properties")).keySet());
                            for (String key : keyList) {
                                PropertiesDTO propertiesDTO = new PropertiesDTO();
                                propertiesDTO.setProperties(key);
                                propertiesDTO.setType(transform(((JSONObject) ((JSONObject) otherJson.get("properties")).get(key)).get("type").toString(), null));
                                Object description = ((JSONObject) ((JSONObject) otherJson.get("properties")).get(key)).get("description");
                                propertiesDTO.setDescription(description == null ? "" : description.toString());
                                reqPropertiesDTOList.add(propertiesDTO);
                            }
                        } else {
                            System.out.println(otherJson);
                        }
                    }
                }
                reqDtoMap.put("properties", reqPropertiesDTOList);
                String dtoName = interfaceName + "QM";
                String upperCaseDtoName = Utils.firstUpperCase(dtoName);
                //生成DTO类
                outputFile(reqDtoMap, upperCaseDtoName, DTO_MODEL);
                params.add("@RequestBody " + upperCaseDtoName + " " + dtoName);
            }
            String resParams = "Void";
            if (yApiDataList.getResBody() != null) {
                // 配置返回参数并生成对应的DTO
                Map<String, Object> resDtoMap = new HashMap<>();
                if (yApiDataList.getResBody() instanceof JSONObject) {
                    JSONObject resJson = JSONObject.parseObject(yApiDataList.getResBody().toString());
                    if (resJson != null && resJson.get("items") != null && ((JSONObject) resJson.get("items")).get("required") != null) {
                        resDtoMap.put("modelName", interfaceName + "DTO");
                        List<PropertiesDTO> resPropertiesDTOList = new ArrayList<>();
                        for (String required : ((List<String>) ((JSONObject) resJson.get("items")).get("required"))) {
                            PropertiesDTO propertiesDTO = new PropertiesDTO();
                            propertiesDTO.setProperties(required);
                            String type = ((JSONObject) ((JSONObject) ((JSONObject) resJson.get("items")).get("properties")).get(required)).get("type").toString();
                            if (type.equals("array")) {
                                propertiesDTO.setType(transform(type, ((JSONObject) ((JSONObject) ((JSONObject) resJson.get("items")).get("properties")).get(required))));
                            } else {
                                propertiesDTO.setType(transform(type, null));
                            }
                            Object description = ((JSONObject) ((JSONObject) ((JSONObject) resJson.get("items")).get("properties")).get(required)).get("description");
                            propertiesDTO.setDescription(description == null ? "" : description.toString());
                            resPropertiesDTOList.add(propertiesDTO);
                        }
                        resDtoMap.put("properties", resPropertiesDTOList);
                        String dtoName = interfaceName + "DTO";
                        String upperCaseDtoName = Utils.firstUpperCase(dtoName);
                        //生成DTO类
                        outputFile(resDtoMap, upperCaseDtoName, DTO_MODEL);
                        resParams = upperCaseDtoName.equals("") ? "Void" : upperCaseDtoName;
                    }
                }
            }
            yApiDataList.setParams(String.join(", ", params));
            yApiDataList.setResParams(resParams);
            yApiDataList.setMethodName(Utils.camelCase(yApiDataList.getPath().substring(1).split("/\\{")[0]));
        }).collect(Collectors.toList());
        dataMap.put("data", list);

        outputFile(dataMap, yApiData.getDesc() == null ? "Null" : yApiData.getDesc(), API_MODEL);
    }

    /**
     * 输出文件
     */
    private void outputFile(Map<String, Object> dataMap, String className, String model) {
        Configuration configuration = new Configuration();
        Writer out = null;
        try {
            // 获取模版路径
            configuration.setDirectoryForTemplateLoading(new File(applicationProperties.getTemplatePath()));
            // 加载模版文件
            Template template = configuration.getTemplate(model);
            // 生成数据
            File docFile;
            if (DTO_MODEL.equals(model)) {
                docFile = new File(applicationProperties.getPath() + "\\" + className + ".java");
            } else {
                docFile = new File(applicationProperties.getPath() + "\\" + Utils.firstUpperCase(dataMap.get("className").toString()) + ".java");
            }
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
            // 输出文件
            template.process(dataMap, out, null);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != out) {
                    out.flush();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /**
     * type转换
     */
    private String transform(String s, JSONObject json) {
        String type;
        switch (s) {
            case "string":
            case "text":
                type = "String";
                break;
            case "number":
                type = "BigDecimal";
                break;
            case "array":
                if (!StringUtils.isEmpty(json)) {
                    String innerType = ((JSONObject) json.get("items")).get("type").toString();
                    if ("array".equals(innerType)) {
                        type = "List<" + transform(innerType, (JSONObject) json.get("items")) + ">";
                    } else {
                        type = "List<" + transform(innerType, null) + ">";
                    }
                } else {
                    type = "List<Object>";
                }
                break;
            case "boolean":
                type = "Boolean";
                break;
            case "integer":
                type = "Integer";
                break;
            case "file":
                type = "MultipartFile";
                break;
            default:
                type = "Object";
        }

        return type;
    }
}
