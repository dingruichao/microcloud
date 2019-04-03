package com.ding.springcloud.controller;



import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestControllerConsumer {
    //private static final String REST_URL_PREFIX = "http://localhost:8001";
    private static final String REST_URL_PREFIX = "http://microcloud-provider";





    @RequestMapping(value = "/consumer/test/get/{id}")
    public String get(@PathVariable("id") Long id)
    {

        return "aaaaaaaaaaaaa";
    }


}
