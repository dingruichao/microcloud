package com.ding.springcloud.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SuppressWarnings("SpringJavaAutowiringInspection")
public class TestController {




    @RequestMapping(value = "/test/get/{id}", method = RequestMethod.GET)
    public String get(@PathVariable("id") Long id) {
        return "ok,"+id;
    }



}
