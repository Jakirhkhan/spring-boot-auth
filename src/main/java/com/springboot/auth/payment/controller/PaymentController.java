package com.springboot.auth.payment.controller;

import com.springboot.auth.exception.ResourceAlreadyExistsException;
import com.springboot.auth.payment.entity.Payment;
import com.springboot.auth.payment.repository.PaymentRepository;
import com.springboot.auth.payment.service.PaymentService;
import com.springboot.auth.taxpayer.entity.TaxPayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PaymentController {
    @Autowired
    PaymentService paymentService;
    @Autowired
    private PaymentRepository paymentRepository;

    @GetMapping("/payments")
    public List<Payment> getAllPayments(){
        return paymentService.getAllPayments();
    }

    @PostMapping("/payments")
    public ResponseEntity<?> create(@RequestBody Payment payment){
        try {
            Payment existingPayment = paymentService.findByTinAndIncomeYear(payment.getTin(), payment.getIncomeYear());
            if(existingPayment!=null){throw new ResourceAlreadyExistsException("Tax Payer already exists!!");}
            return new ResponseEntity<Payment>(paymentService.save(payment), HttpStatus.CREATED);
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            return new ResponseEntity<Payment>((Payment) null, HttpStatus.CONFLICT);
        }
    }
}
