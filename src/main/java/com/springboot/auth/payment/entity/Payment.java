package com.springboot.auth.payment.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class Payment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotNull
    private String taxTransactionId;

    @NotNull
    @Column(name = "tax_year", length = 9)
    private String incomeYear;

    @Column(name = "tin",length = 12)
    @NotNull
    private Long tin;

    @NotNull
    private Double paidAmount;

    private String paymentMethod;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime paymentDate;

}
