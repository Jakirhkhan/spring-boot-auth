package com.springboot.auth.taxpayer.controller;

import com.springboot.auth.taxpayer.entity.TaxPayer;
import com.springboot.auth.taxpayer.service.TaxPayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.time.temporal.Temporal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class TaxPayerController {

    @Autowired
    private TaxPayerService taxPayerService;


    @GetMapping("/tax-payers")
    @Cacheable(value="resources")
    public List<TaxPayer>  getAll(){
        return taxPayerService.getAllTaxPayer();
    }

    @GetMapping("/tax-payers/{id}")
    public ResponseEntity<?> getTaxPayer(@PathVariable String id){
        Optional<TaxPayer> taxPayer = taxPayerService.getTaxPayerById(id);
        return ResponseEntity.ok().body(taxPayer);
    }

    @PostMapping("/tax-payers")
    public void create(@RequestBody TaxPayer taxPayer){
        taxPayerService.saveTaxPayer(taxPayer);
    }
}
