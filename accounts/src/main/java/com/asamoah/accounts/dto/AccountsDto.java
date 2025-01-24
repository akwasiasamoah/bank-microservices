package com.asamoah.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name = "Accounts",
        description = "Schema to hold Account information"
)
public class AccountsDto {


    @Schema(
            description = "Account number of the customer",
            example = "1234567890"
    )
    @NotEmpty
   @Pattern(regexp = "^[0-9]{10}$", message = "Account number should be a 10 digit number")
    private Long accountNumber;

    @Schema(
            description = "Account type of the Eazy Bank account",
            example = "Savings"
    )
    @NotEmpty(message = "Account type cannot be null or empty")
    private String accountType;

    @Schema(
            description = "Branch name of the Eazy Bank account",
            example = "Kumasi Main Branch"
    )
    @NotEmpty(message = "Branch name cannot be null or empty")
    private String branchAddress;
}
