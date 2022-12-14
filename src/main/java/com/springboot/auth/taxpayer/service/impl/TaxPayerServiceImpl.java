package com.springboot.auth.taxpayer.service.impl;

import com.springboot.auth.taxpayer.entity.TaxPayer;
import com.springboot.auth.taxpayer.repository.TaxPayerRepository;
import com.springboot.auth.taxpayer.service.TaxPayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaxPayerServiceImpl implements TaxPayerService {
    @Autowired
    private TaxPayerRepository taxPayerRepository;
    @Override
    public TaxPayer getTaxPayerByTin(int tin) {
        return taxPayerRepository.getTaxPayerByTin(tin);
    }

    public Optional<TaxPayer> getTaxPayerById(String id) {
        return taxPayerRepository.findById(id);
    }

    public List<TaxPayer> getAllTaxPayer(){
        return taxPayerRepository.findAll();
    }

    public void saveTaxPayer(TaxPayer taxPayer){
        if (taxPayer.getId() == null){
            taxPayer.setId(UUID.randomUUID().toString());
        }
        taxPayerRepository.save(taxPayer);
    }
}
