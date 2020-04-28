package com.hhi.rabbitmqapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriTemplateHandler;

import java.time.Duration;


@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder, @Value("${rabbitmqapi.basicauthentication.username}") String username,@Value("${rabbitmqapi.basicauthentication.password}") String password) {

         RestTemplate restTemplate = restTemplateBuilder
                .requestFactory(() -> new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()))
                .setConnectTimeout(Duration.ofMillis(5000)) // connection-timeout
                .setReadTimeout(Duration.ofMillis(5000)) // read-timeout
                .basicAuthentication(username,password)
                .build();

        DefaultUriTemplateHandler defaultUriTemplateHandler = new DefaultUriTemplateHandler();
        defaultUriTemplateHandler.setParsePath(true);
        restTemplate.setUriTemplateHandler(defaultUriTemplateHandler);

        return restTemplate;
    }

}
