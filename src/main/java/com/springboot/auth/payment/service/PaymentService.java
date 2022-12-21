package com.springboot.auth.payment.service;

import com.springboot.auth.payment.entity.Payment;
import com.springboot.auth.taxtransaction.entity.TaxTransaction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PaymentService {
    List<Payment> getAllPayments();
    Payment findByTinAndIncomeYear(Long tin, String incomeYear);
    Optional<Payment> getPayments(String id);
    Payment save(Payment payment);
}
