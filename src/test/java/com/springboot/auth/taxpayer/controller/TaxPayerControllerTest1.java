package com.springboot.auth.taxpayer.controller;

import static org.assertj.core.api.Assertions.assertThat;

import com.springboot.auth.taxpayer.entity.TaxPayer;
import com.springboot.auth.taxpayer.service.TaxPayerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RequestBody;

@SpringBootTest
@WebMvcTest(TaxPayerController.class)
public class TaxPayerControllerTest1 {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TaxPayerController taxPayerController;

    @Autowired
    private TaxPayerService taxPayerService;
    @Test
    public void contextLoads() throws Exception{
        assertThat(taxPayerController).isNotNull();
    }


}
