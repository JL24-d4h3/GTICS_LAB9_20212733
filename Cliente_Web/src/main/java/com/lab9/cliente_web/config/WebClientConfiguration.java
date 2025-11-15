package com.lab9.cliente_web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Base64;

@Configuration
public class WebClientConfiguration {

    @Bean
    public WebClient webClient(
            @Value("${app.restful.url-base}") String baseUrl,
            @Value("${app.restful.username}") String username,
            @Value("${app.restful.password}") String password
    ) {

        String authString = username + ":" + password;
        String authHeader = "Basic " + Base64.getEncoder().encodeToString(authString.getBytes());

        return WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.AUTHORIZATION, authHeader)
                .build();
    }
}