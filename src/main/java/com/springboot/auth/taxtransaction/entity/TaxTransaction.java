package com.springboot.auth.taxtransaction.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "Tax_transactions")
public class TaxTransaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "tin",length = 12)
    @NotNull
    private Long tin;

    @NotNull
    @Column(name = "tax_year", length = 9)
    private String taxYear;

    @NotNull
    private double salary;

    private double totalTax;
}
