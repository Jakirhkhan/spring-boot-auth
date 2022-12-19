package com.springboot.auth.taxtransaction.repository;

import com.springboot.auth.taxtransaction.entity.TaxTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxTransactionRepository extends JpaRepository<TaxTransaction, String> {
    TaxTransaction findByTin(Long tin);
}
