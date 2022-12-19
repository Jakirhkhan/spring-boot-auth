package com.springboot.auth.taxtransaction.controller;


import com.springboot.auth.exception.ResourceNotFoundException;
import com.springboot.auth.taxtransaction.entity.TaxTransaction;
import com.springboot.auth.taxtransaction.repository.TaxTransactionRepository;
import com.springboot.auth.taxtransaction.service.TaxTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TaxTransactionController {

    @Autowired
    TaxTransactionService taxTransactionService;
    @GetMapping("/tax-transactions")
    public List<TaxTransaction> all(){
        return taxTransactionService.findAll();
    }
    @GetMapping("/tax-transactions/{id}")
    public ResponseEntity<?> getOne(@PathVariable String id){
        TaxTransaction taxTransaction = taxTransactionService.getTById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Taxpayer with ID: "+ id + "is not found"));
        return new ResponseEntity<>(taxTransaction, HttpStatus.OK);
    }
    @PostMapping("/tax-transactions")
    public ResponseEntity<?> create(@RequestBody TaxTransaction taxTransaction){
        return new ResponseEntity<>(taxTransactionService.save(taxTransaction), HttpStatus.CREATED);
    }
    @PutMapping("/tax-transactions")
    public ResponseEntity<?> update(TaxTransaction taxTransaction, String id){
        TaxTransaction existingTaxTransaction = taxTransactionService.getTById(id)
                .orElseThrow(()->new ResourceNotFoundException("Tax Transaction not found with id- "+id));
        return new ResponseEntity<>(taxTransactionService.save(existingTaxTransaction), HttpStatus.OK);
    }

}
