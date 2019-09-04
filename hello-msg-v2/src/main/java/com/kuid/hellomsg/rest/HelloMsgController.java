package com.kuid.hellomsg.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloMsgController {

    @GetMapping("/hello")
    public String hello() {
        return "UPDATED - This is from v2";
    }
}
