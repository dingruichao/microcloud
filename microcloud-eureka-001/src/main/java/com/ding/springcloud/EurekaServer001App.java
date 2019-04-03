package com.ding.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServer001App {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServer001App.class, args);
    }
}
