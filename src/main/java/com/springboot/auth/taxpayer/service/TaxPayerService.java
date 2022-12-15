package com.springboot.auth.taxpayer.service;

import com.springboot.auth.taxpayer.entity.TaxPayer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TaxPayerService {

    List<TaxPayer> getAllTaxPayer();
    TaxPayer getTaxPayerByTin(Long tin);
    Optional<TaxPayer> getTaxPayerById(String id);

    TaxPayer saveTaxPayer(TaxPayer taxPayer);


}
