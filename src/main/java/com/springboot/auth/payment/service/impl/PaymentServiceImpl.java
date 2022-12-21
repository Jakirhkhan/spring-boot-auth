package com.springboot.auth.payment.service.impl;

import com.springboot.auth.exception.ResourceNotFoundException;
import com.springboot.auth.lib.DataManager;
import com.springboot.auth.payment.entity.Payment;
import com.springboot.auth.payment.repository.PaymentRepository;
import com.springboot.auth.payment.service.PaymentService;
import com.springboot.auth.taxpayer.repository.TaxPayerRepository;
import com.springboot.auth.taxtransaction.entity.TaxTransaction;
import com.springboot.auth.taxtransaction.service.TaxTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    private TaxPayerRepository taxPayerRepository;

    @Autowired
    private TaxTransactionService taxTransactionService;
    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment findByTinAndIncomeYear(Long tin, String incomeYear) {
        return paymentRepository.findByTinAndIncomeYear(tin,incomeYear);
    }

    @Override
    public Optional<Payment> getPayments(String id) {
        return paymentRepository.findById(id);
    }

    @Override
    public Payment save(Payment payment) {
        TaxTransaction taxTransaction = taxTransactionService.findByTinAndIncomeYear(payment.getTin(), payment.getIncomeYear());
        if(taxTransaction==null){
            throw new ResourceNotFoundException("Tax is not calculated for TIN-"+payment.getTin()+" of the Income Year-"+payment.getIncomeYear());
        }
        payment.setTaxTransactionId(taxTransaction.getId());
        payment.setPaidAmount(taxTransaction.getTotalTax());
        payment.setPaymentDate(DataManager.getLocalDateTime());
        return paymentRepository.save(payment);
    }
}
