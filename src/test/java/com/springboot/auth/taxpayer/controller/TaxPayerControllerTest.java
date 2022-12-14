package com.springboot.auth.taxpayer.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TaxPayerControllerTest {
    @Autowired
    private TaxPayerController taxPayerController;


    @Test
    public void contextLoads() throws Exception{
        assertThat(taxPayerController).isNotNull();
    }

}
