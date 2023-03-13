package com.scushiposhi.mssecurity.restcontrollers;

import com.scushiposhi.mssecurity.model.AuthenticationRequest;
import com.scushiposhi.mssecurity.repositories.WineRepository;
import com.scushiposhi.mssecurity.services.WineService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class WineRestControllerTest {
    @Autowired
    WineService wineService;
    @Autowired
    WineRepository wineRepository;
    @Autowired
    WebApplicationContext wac;

    protected MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .apply(springSecurity())
                .build();
    }
    @Test
    void t() throws Exception {

        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("http://localhost:8080/authenticate")
                                .content("{ \"username\": \"POLSI\",\n" +
                                        "    \"password\": \"123test123\"}")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}