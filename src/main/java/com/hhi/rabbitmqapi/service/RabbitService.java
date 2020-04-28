package com.hhi.rabbitmqapi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;

@Slf4j
@Service
@RequiredArgsConstructor
public class RabbitService {

    private final RestTemplate restTemplate;

    @Value("${rabbitmqapi.url}")
    private String url;

    public String addAccount(String username, String password, String tags){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject reqBody= new JSONObject();
        reqBody.put("password",password);
        reqBody.put("tags",tags);

        HttpEntity<String> req = new HttpEntity<String>(reqBody.toString(),headers);
        ResponseEntity<String> response = restTemplate.exchange(url+"/api/users/{username}", HttpMethod.PUT, req, String.class,username);
        return response.toString();
    }

    public String addVhostPermission(String username){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject reqBody = new JSONObject();
        reqBody.put("configure",".*");
        reqBody.put("write",".*");
        reqBody.put("read",".*");

        HttpEntity<String> req = new HttpEntity<>(reqBody.toString(),headers);
        ResponseEntity<String> response = restTemplate.exchange(url+"/api/permissions/{vhost}/{username}", HttpMethod.PUT, req, String.class, "/",username);
        return response.toString();
    }

    public String addTopicPermission(String username) throws UnsupportedEncodingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject reqBody = new JSONObject();
        reqBody.put("exchange","");
        reqBody.put("write",".*");
        reqBody.put("read",".*");

        HttpEntity<String> req = new HttpEntity<>(reqBody.toString(),headers);
        ResponseEntity<String> response = restTemplate.exchange(url+"/api/topic-permissions/{vhost}/{username}", HttpMethod.PUT, req, String.class, "/",username);
        return response.toString();
    }




}
