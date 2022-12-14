package com.springboot.auth.taxpayer.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(name = "tax-payers")
public class TaxPayer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "first_name", length = 100)
    @NotNull
    @NotEmpty
    private String firstName;

    @Column(name = "last_name", length = 150)
    @NotNull
    @NotEmpty
    private String lastName;

    @Column(name = "address", length = 255)
    private String address;

    @Column(name = "tin",length = 12)
    @NotNull
    private Long tin;

    @Column(name = "nid")
    @Size(min = 10, max = 17)
    @NotNull
    private String  nid;

    @Column(name = "mobile_no")
    @Size(min = 11, max = 11)
    @NotNull
    private String  mobileNo;

    @Column(name = "zone", length = 3)
    @NotNull
    private String  zone;

    @NotNull
    @Column(name = "circle", length = 3)
    private String circle;

    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dob;
}
