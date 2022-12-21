package com.springboot.auth.taxtransaction.repository;

import com.springboot.auth.taxtransaction.entity.TaxTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TaxTransactionRepository extends JpaRepository<TaxTransaction, String> {
    TaxTransaction findByTin(Long tin);
    @Query("SELECT t FROM TaxTransaction t WHERE t.tin=:tin and t.incomeYear=:incomeYear")
    TaxTransaction findByTinAndIncomeYear(Long tin, String incomeYear);

//    @Query("SELECT t FROM Tutorial t WHERE LOWER(t.title) LIKE LOWER(CONCAT('%', :title,'%')) AND t.published=:isPublished")
//    List<Tutorial> findByTitleContainingCaseInsensitiveAndPublished(String title, boolean isPublished);
}
