package com.scushiposhi.mssecurity.restcontrollers;

import com.scushiposhi.mssecurity.entities.Wine;
import com.scushiposhi.mssecurity.model.AuthenticationRequest;
import com.scushiposhi.mssecurity.model.AuthenticationResponse;
import com.scushiposhi.mssecurity.security.JwtAuthenticationProvider;
import com.scushiposhi.mssecurity.security.JwtUtil;
import com.scushiposhi.mssecurity.services.WineService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
public class WineRestController {
    private  final WineService wineService;
    private final UserDetailsService userDetailsService;
    private final JwtAuthenticationProvider authenticationProvider;
    private final JwtUtil jwtUtil;

    @GetMapping(value = "/wines/{wineId}", produces = "application/json")
    public ResponseEntity<Wine> getWineById(@PathVariable(name = "wineId") Long wineId){
        return wineService.getWineById(wineId);
    }
    @GetMapping(value = "/wines",produces = "application/json")
    public ResponseEntity<List<Wine>> getWines(){
        return wineService.getWines();
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest){

        authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),authenticationRequest.getPassword()));
        UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        String s = jwtUtil.generateToken(userDetails);

        return new ResponseEntity<>(new AuthenticationResponse(s), HttpStatus.OK);
    }

}
