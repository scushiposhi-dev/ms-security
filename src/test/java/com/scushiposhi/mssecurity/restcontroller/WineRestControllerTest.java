package com.scushiposhi.mssecurity.restcontroller;

import com.scushiposhi.mssecurity.repositories.WineRepository;
import com.scushiposhi.mssecurity.services.WineService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
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
                        .get("http://localhost:8080/wines/1")
                        .with(httpBasic("POLSI","123test123")))
                .andExpect(status().isOk());
    }
}