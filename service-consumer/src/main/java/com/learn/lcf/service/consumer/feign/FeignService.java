package com.learn.lcf.service.consumer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient(name = "service-provider")
public interface FeignService {

    @GetMapping(value = "/hello")
    String hello(@RequestParam(value = "msg") String msg);
}
