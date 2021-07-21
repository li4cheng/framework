package com.my.framework.customConfig.restTemplate;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class RequestService {

    private final RestTemplate restTemplate;

    public RequestService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public JSONObject get(String url, LinkedMultiValueMap<String, String> params, String token) {
        URI uri = UriComponentsBuilder.fromUriString(url).queryParams(params).build().toUri();
        HttpEntity<Void> httpEntity = token == null ? null : httpEntity(token);
        return restTemplate.exchange(uri, HttpMethod.GET, httpEntity, JSONObject.class).getBody();
    }

    public JSONObject post(String url, LinkedMultiValueMap<String, String> params, String token) {
        URI uri = UriComponentsBuilder.fromUriString(url).queryParams(params).build().toUri();
        HttpEntity<Void> httpEntity = token == null ? null : httpEntity(token);
        return restTemplate.exchange(uri, HttpMethod.POST, httpEntity, JSONObject.class).getBody();
    }

    public JSONObject request(String url, LinkedMultiValueMap<String, String> params, String token, HttpMethod httpMethod) {
        URI uri = UriComponentsBuilder.fromUriString(url).queryParams(params).build().toUri();
        HttpEntity<Void> httpEntity = token == null ? null : httpEntity(token);
        return restTemplate.exchange(uri, httpMethod, httpEntity, JSONObject.class).getBody();
    }

    private HttpEntity<Void> httpEntity(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", token);
        return new HttpEntity<>(headers);
    }
}
