package com.kuid.hellokube.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloKubeResource {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/welcome")
    public String welcome() {
        String msg = "";
        try {
            msg = restTemplate.getForObject("http://hello-msg-app:8888/hello", String.class);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            msg = e.getMessage();
        }
        return msg;
    }

    @GetMapping("/init")
    public String init() {
        return "INIT for HELLO-KUBE";
    }

    @Bean
    public RestTemplate rest() {
        return new RestTemplate();
    }
}
