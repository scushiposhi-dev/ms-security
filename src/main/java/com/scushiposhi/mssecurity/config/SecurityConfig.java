package com.scushiposhi.mssecurity.config;

import com.scushiposhi.mssecurity.services.security.CustomSecurityPasswordEncoder;
import com.scushiposhi.mssecurity.services.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static com.scushiposhi.mssecurity.services.security.CustomSecurityPasswordEncoder.EncodingEnum.BCRYPT;
import static com.scushiposhi.mssecurity.utils.EntityUtils.WineAuthoritiesEnum.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    UserService authorizeHttpRequests;

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        //return PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return CustomSecurityPasswordEncoder.createDelegatingPasswordEncoder(BCRYPT);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http//httpBasic(Customizer.withDefaults())//.csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin").hasAuthority(ADMIN_PAGE_READ.name())
                .antMatchers("/user").hasAuthority(USER_PAGE_READ.name())
                .antMatchers("/wines**").hasAuthority(WINE_READ.name())
                .antMatchers("/").permitAll()
                .and().formLogin();
               return http.build();
    }

}
