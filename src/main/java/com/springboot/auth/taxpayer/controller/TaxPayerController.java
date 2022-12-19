package com.springboot.auth.taxpayer.controller;

import com.springboot.auth.exception.ResourceAlreadyExistsException;
import com.springboot.auth.exception.ResourceNotFoundException;
import com.springboot.auth.taxpayer.entity.TaxPayer;
import com.springboot.auth.taxpayer.service.TaxPayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class TaxPayerController {

    @Autowired
    private TaxPayerService taxPayerService;


    @GetMapping("/tax-payers")
    @Cacheable(value="taxpayers")
    @ResponseBody
    public List<TaxPayer>  getAll(){
        return taxPayerService.getAllTaxPayer();
    }

    @GetMapping("/tax-payers/{id}")
    public ResponseEntity<TaxPayer> getTaxPayer(@PathVariable String id){
        TaxPayer taxPayer = taxPayerService.getTaxPayerById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Taxpayer with ID: "+ id + "is not found"));
        return new ResponseEntity<>(taxPayer, HttpStatus.OK);

    }

    @PostMapping("/tax-payers")
    public ResponseEntity<TaxPayer> create(@RequestBody TaxPayer taxPayer){
        TaxPayer existingTaxPayer = taxPayerService.getTaxPayerByTin(taxPayer.getTin());
        if (existingTaxPayer == null) {
           return new ResponseEntity<TaxPayer>(taxPayerService.saveTaxPayer(taxPayer), HttpStatus.CREATED);
        }
        else
            throw new ResourceAlreadyExistsException("Tax Payer already exists!!");

    }

    @PutMapping("/tax-payers/{id}")
    @CachePut(value="taxpayers", key="#id")
    public void update(@RequestBody TaxPayer taxPayer,@PathVariable String id){
        TaxPayer taxPayer1 = taxPayerService.getTaxPayerById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Taxpayer with ID: "+ id + "is not found"));
        taxPayerService.saveTaxPayer(taxPayer);
    }
}
