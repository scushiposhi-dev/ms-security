package com.scushiposhi.mssecurity.restcontrollers;

import com.scushiposhi.mssecurity.security.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor(onConstructor_ = @Autowired)
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
    @GetMapping(value = "/authentication")
    public String authenticate(){
        return "<h1>welcome admin<h1>";
    }
}
