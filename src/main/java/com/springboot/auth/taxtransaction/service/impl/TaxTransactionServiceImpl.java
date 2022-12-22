package com.springboot.auth.taxtransaction.service.impl;

import com.springboot.auth.exception.ResourceAlreadyExistsException;
import com.springboot.auth.exception.ResourceNotFoundException;
import com.springboot.auth.externalapi.ExternalApiCall;
import com.springboot.auth.taxpayer.entity.TaxPayer;
import com.springboot.auth.taxtransaction.entity.TaxTransaction;
import com.springboot.auth.taxtransaction.repository.TaxTransactionRepository;
import com.springboot.auth.taxtransaction.service.TaxTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;


@Service
public class TaxTransactionServiceImpl implements TaxTransactionService {

    @Autowired
    TaxTransactionRepository taxTransactionRepository;

    @Override
    public List<TaxTransaction> findAll() {
        return taxTransactionRepository.findAll();
    }

    @Override
    public TaxTransaction getByTin(Long tin) {
        return taxTransactionRepository.findByTin(tin);
    }

    @Override
    public Optional<TaxTransaction> getTById(String id) {
        return taxTransactionRepository.findById(id);
    }

    @Override
    public TaxTransaction save(TaxTransaction taxTransaction) {
        TaxTransaction existingTaxTransaction = taxTransactionRepository.findByTinAndIncomeYear(taxTransaction.getTin(), taxTransaction.getIncomeYear());
        if(existingTaxTransaction!=null){
            throw new ResourceNotFoundException("Tax already calculated for TIN-"+taxTransaction.getTin()+" of the Income Year-"+taxTransaction.getIncomeYear());
        }
        String uri = "http://localhost:8090/api/v1/tax-payers/tin/"+taxTransaction.getTin();
        TaxPayer taxpayer = (TaxPayer) ExternalApiCall.getRemoveObject(uri);
        if(taxpayer==null){
            throw new ResourceNotFoundException("Tax Payer not found for TIN-"+taxTransaction.getTin());
        }
        taxTransaction.setTotalTax(calculateTax(taxpayer.getGender(),taxTransaction.getSalary()));
        return taxTransactionRepository.save(taxTransaction);
    }

    @Override
    public TaxTransaction findByTinAndIncomeYear(Long tin, String incomeYear) {
        return taxTransactionRepository.findByTinAndIncomeYear(tin,incomeYear);
    }
    public double calculateTax(String gender, double salary) {
        double tax = 0;
        double initialSlab = (gender.equals("M"))?300000:350000;
        if(salary>initialSlab+1300000) {
            tax += 100000*0.05;
            tax += 300000*0.1;
            tax += 400000*0.15;
            tax += 500000*0.2;
            tax += (salary - (initialSlab+1300000))*0.25;
        }else if(salary > (initialSlab+800000) && salary <= (initialSlab+1300000)){
            tax += 100000*0.05;
            tax += 300000*0.1;
            tax += 400000*0.15;
            tax += (salary - (initialSlab+800000))*0.2;
        }else if(salary > (initialSlab+400000) && salary <= (initialSlab+800000)){
            tax += 100000*0.05;
            tax += 300000*0.1;
            tax += (salary - (initialSlab+400000))*0.15;
        }else if(salary > (initialSlab+100000) && salary <= (initialSlab+400000)){
            tax += 100000*0.05;
            tax += (salary - (initialSlab+100000))*0.1;
        }else if(salary > initialSlab && salary <= (initialSlab+100000)){
            tax += (salary - initialSlab)*0.05;
        }
        return tax;
    }
}
