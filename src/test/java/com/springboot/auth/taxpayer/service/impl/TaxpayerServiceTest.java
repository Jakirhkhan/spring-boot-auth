package com.springboot.auth.taxpayer.service.impl;

import com.springboot.auth.taxpayer.entity.TaxPayer;
import com.springboot.auth.taxpayer.repository.TaxPayerRepository;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;


@ExtendWith(MockitoExtension.class)
public class TaxpayerServiceTest {
    @Mock
    private TaxPayerRepository taxPayerRepository;

    @InjectMocks
    private TaxPayerServiceImpl taxPayerService;

    private TaxPayer taxPayer;

    @BeforeEach
    public void setup(){
        taxPayer = TaxPayer.builder()
                .id(UUID.randomUUID().toString())
                .firstName("Ramesh")
                .lastName("Fadatare")
                .address("Dhaka")
                .zone("4")
                .circle("22")
                .build();
    }
}
