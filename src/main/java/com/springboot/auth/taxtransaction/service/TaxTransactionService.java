package com.springboot.auth.taxtransaction.service;

import com.springboot.auth.taxtransaction.entity.TaxTransaction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TaxTransactionService {

    List<TaxTransaction> findAll();
    TaxTransaction getByTin(Long tin);
    Optional<TaxTransaction> getTById(String id);

    TaxTransaction save(TaxTransaction taxTransaction);
}
