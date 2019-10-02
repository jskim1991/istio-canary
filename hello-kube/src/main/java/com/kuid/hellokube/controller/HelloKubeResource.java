package com.kuid.hellokube.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloKubeResource {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/helloWorld")
    public String getHelloWorld() {
        return "Hello Docker World";
    }

    @GetMapping("/istio")
    public String testIstio() {
        String returnValue = restTemplate.getForObject("http://hello-msg:8888/hello", String.class);
        System.out.println("return value : " + returnValue);

        return returnValue;
    }

    @GetMapping("/init")
    public String init() {
        return "INIT for HELLO-KUBE";
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
