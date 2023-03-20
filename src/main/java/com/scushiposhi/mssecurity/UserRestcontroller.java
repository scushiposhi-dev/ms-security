package com.scushiposhi.mssecurity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Collection;

@RestController
public class UserRestcontroller {

    @GetMapping("/user")
    public String getUser(@AuthenticationPrincipal OAuth2User principal){
        String login = principal.getAttribute("login");
         principal.getAttribute("login");
        Collection<? extends GrantedAuthority> authorities = principal.getAuthorities();
        return login;
    }
}
