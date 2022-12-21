package com.springboot.auth.payment.repository;

import com.springboot.auth.payment.entity.Payment;
import com.springboot.auth.taxtransaction.entity.TaxTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, String> {
    Payment findByTinAndIncomeYear(Long tin, String incomeYear);
}
