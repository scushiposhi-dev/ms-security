package com.scushiposhi.mssecurity.config;

import com.scushiposhi.mssecurity.security.CustomSecurityPasswordEncoder;
import com.scushiposhi.mssecurity.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static com.scushiposhi.mssecurity.security.CustomSecurityPasswordEncoder.EncodingEnum.BCRYPT;

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
        http.httpBasic(Customizer.withDefaults())//.csrf().disable()
//                .authorizeRequests(auth->auth.anyRequest().authenticated());
                .authorizeHttpRequests((authz) -> authz.antMatchers("/wines/**").hasAnyAuthority("DEVELOPER"));
              //  .authorizeHttpRequests((authz) -> authz.antMatchers("/wines/**").hasAnyRole("DEVELOPER"));// it inserts the prefix ROLE_
        return http.build();
    }

}
