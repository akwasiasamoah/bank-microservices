package com.asamoah.accounts.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountsDto {

    @NotEmpty
   @Pattern(regexp = "^[0-9]{10}$", message = "Account number should be a 10 digit number")
    private Long accountNumber;

    @NotEmpty(message = "Account type cannot be null or empty")
    private String accountType;

    @NotEmpty(message = "Branch name cannot be null or empty")
    private String branchAddress;
}