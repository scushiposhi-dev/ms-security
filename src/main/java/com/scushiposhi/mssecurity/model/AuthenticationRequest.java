package com.scushiposhi.mssecurity.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class AuthenticationRequest {
    private String username;
    private String password;
}
