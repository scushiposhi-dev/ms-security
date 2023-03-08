package com.scushiposhi.mssecurity.config;

import com.scushiposhi.mssecurity.security.CustomSecurityPasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.scushiposhi.mssecurity.security.CustomSecurityPasswordEncoder.EncodingEnum.BCRYPT;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        //return PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return CustomSecurityPasswordEncoder.createDelegatingPasswordEncoder(BCRYPT);
    }
}
