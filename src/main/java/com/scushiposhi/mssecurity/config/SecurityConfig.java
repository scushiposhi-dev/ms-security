package com.scushiposhi.mssecurity.config;

import com.scushiposhi.mssecurity.security.CustomSecurityPasswordEncoder;
import com.scushiposhi.mssecurity.security.JwtAuthenticationProvider;
import com.scushiposhi.mssecurity.security.JwtRequestFilter;
import com.scushiposhi.mssecurity.security.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.RequestMatcherDelegatingAuthenticationManagerResolver;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;

import static com.scushiposhi.mssecurity.security.CustomSecurityPasswordEncoder.EncodingEnum.BCRYPT;
import static com.scushiposhi.mssecurity.utils.EntityUtils.WineAuthoritiesEnum.*;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
    private final JwtRequestFilter jwtRequestFilter;


    private final UserDetailsService userService;
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        //return PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return CustomSecurityPasswordEncoder.createDelegatingPasswordEncoder(BCRYPT);
    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http//httpBasic(Customizer.withDefaults())//.csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/admin").hasAuthority(ADMIN_PAGE_READ.name())
//                .antMatchers("/user").hasAuthority(USER_PAGE_READ.name())
//                .antMatchers("/wines**").hasAuthority(WINE_READ.name())
//                .antMatchers("/").permitAll()
//                .and().formLogin();
//               return http.build();
//    }
    @Bean
    public SecurityFilterChain securityFilterChainJwt(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests().antMatchers("/authenticate").permitAll()
                .anyRequest().authenticated()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
