package com.learn.lcf.service.consumer.feign.impl;

import com.learn.lcf.service.consumer.feign.FeignService;
import org.springframework.stereotype.Component;

public class FeignServiceImpl {

    // @Override
    public String hello(String msg) {
        return "default message";
    }
}
