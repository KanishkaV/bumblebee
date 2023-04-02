package com.bumble.bee.app.models.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.Instant;

@Data
public class UserDto implements Dto<Long> {

    @NotBlank(groups = {ValidationGroups.Update.class}, message = "Product Id is mandatory")
    private Long id;

    private String firstName;

    private String lastName;

    private Instant birthDate;

    private String role;

    private Double loanAmount;

    private Double loanInstallment;

}
