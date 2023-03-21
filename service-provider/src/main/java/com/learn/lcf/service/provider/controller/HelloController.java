package com.learn.lcf.service.provider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Value("${spring.application.name}")
    private String appName;

    @RequestMapping(path = "/hello")
    public String hello(String msg) {
        System.out.println("call hello" + msg);
        return "from " + appName + " " + msg;
    }
}
