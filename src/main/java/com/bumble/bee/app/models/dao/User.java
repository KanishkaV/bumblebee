package com.bumble.bee.app.models.dao;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.Instant;

@Data
@jakarta.persistence.Entity
public class User implements Entity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birth_date")
    private Instant birthDate;

    @Column(name = "loan_amount")
    private Double loanAmount;

    @Column(name = "loan_installment")
    private Double loanInstallment;

    @Override
    public String getEntityName() {
        return "User";
    }
}
