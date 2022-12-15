package com.springboot.auth.taxpayer.repository;

import com.springboot.auth.taxpayer.entity.TaxPayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxPayerRepository extends JpaRepository<TaxPayer, String> {
    TaxPayer getTaxPayerByTin(Long tin);
    TaxPayer save(TaxPayer taxPayer);
}
