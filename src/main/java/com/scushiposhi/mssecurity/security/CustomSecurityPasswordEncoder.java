package com.scushiposhi.mssecurity.security;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static com.scushiposhi.mssecurity.security.CustomSecurityPasswordEncoder.EncodingEnum.*;

public class CustomSecurityPasswordEncoder {
    public enum EncodingEnum{
        BCRYPT("bcrypt"),
        LDAP("ldap"),
        MD_4 ("MD4"),
        MD_5("MD5"),
        NOOP("noop"),
        PBKDF_2("pbkdf2"),
        SCRYPT("scrypt"),
        SHA_1("SHA-1"),
        SHA_256("SHA-256"),
        SHA_2561("sha256"),
        ARGON_2("argon2");
        String encodedId;
        EncodingEnum(String encodedId){
            this.encodedId=encodedId;
        }
        EncodingEnum fromString(String encodedId){
            return Stream.of(EncodingEnum.values())
                    .filter(e->e.encodedId.equalsIgnoreCase(encodedId))
                    .findFirst()
                    .orElse(null);
      }
    }
    public static PasswordEncoder createDelegatingPasswordEncoder(EncodingEnum encodingEnum){
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put(BCRYPT.encodedId, new BCryptPasswordEncoder());
        encoders.put(LDAP.encodedId, new org.springframework.security.crypto.password.LdapShaPasswordEncoder());
        encoders.put(MD_4.encodedId, new org.springframework.security.crypto.password.Md4PasswordEncoder());
        encoders.put(MD_5.encodedId, new org.springframework.security.crypto.password.MessageDigestPasswordEncoder("MD5"));
        encoders.put(NOOP.encodedId, org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance());
        encoders.put(PBKDF_2.encodedId, new Pbkdf2PasswordEncoder());
        encoders.put(SCRYPT.encodedId, new SCryptPasswordEncoder());
        encoders.put(SHA_1.encodedId, new org.springframework.security.crypto.password.MessageDigestPasswordEncoder("SHA-1"));
        encoders.put(SHA_256.encodedId,
                new org.springframework.security.crypto.password.MessageDigestPasswordEncoder("SHA-256"));
        encoders.put(SHA_2561.encodedId, new org.springframework.security.crypto.password.StandardPasswordEncoder());
        encoders.put(ARGON_2.encodedId, new Argon2PasswordEncoder());

        return new DelegatingPasswordEncoder(encodingEnum!=null?encodingEnum.encodedId:BCRYPT.encodedId,encoders);
    }
    private CustomSecurityPasswordEncoder(){}

}
