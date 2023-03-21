package com.learn.lcf.service.consumer.consumer;

import com.learn.lcf.service.consumer.feign.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {


    @Autowired
    private FeignService feignService;

    @RequestMapping("/consume")
    public String consume(String msg) {
        return feignService.hello(msg);
    }
}
