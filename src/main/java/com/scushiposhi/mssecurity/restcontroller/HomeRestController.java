package com.scushiposhi.mssecurity.restcontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeRestController {
    @GetMapping(value = "/")
    public String welcome(){
        return "<h1>welcome<h1>";
    }
    @GetMapping(value = "/user")
    public String welcomeUser(){
        return "<h1>welcome user<h1>";
    }
    @GetMapping(value = "/admin")
    public String welcomeAdmin(){
        return "<h1>welcome admin<h1>";
    }
}
