package com.example.client.controller;

import com.example.client.dto.Req;
import com.example.client.dto.User;
import com.example.client.service.RestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/client")
public class ApiConroller {
    @Autowired
    private RestTemplateService restTemplateService;
        public ApiConroller(RestTemplateService restTemplateService) {
        this.restTemplateService = restTemplateService;
    }

    @GetMapping("/hello")
    public Req<User> getHello(){
        // return restTemplateService.hello(name, age);
        // return restTemplateService.post();
        // return restTemplateService.exchange();
        return  restTemplateService.genericExchange();

    }
}
