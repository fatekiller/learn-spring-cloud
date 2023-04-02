package com.learn.lcf.service.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableScheduling
//@EnableEurekaClient
public class ServiceProviderApp {

    public static void main(String[] args) {
        SpringApplication.run(ServiceProviderApp.class, args);
    }

}
