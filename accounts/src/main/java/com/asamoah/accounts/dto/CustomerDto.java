package com.asamoah.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
@Schema(
        name = "Customer",
        description = "Schema to hold Customer and Account information"
)
public class CustomerDto {

    @Schema(
            description = "Name of the customer",
            example = "John Mensah"
    )
    @NotEmpty(message = "Name cannot be null or empty")
    @Size(min = 5, max =30, message = "The length of the customer name should be between 5 and 30")
    private String name;

    @Schema(
            description = "Email address of the customer",
            example = "example@gmail.com"
    )
    @NotEmpty(message = "Email cannot be null or empty")
    @Email(message = "Email address should be a valid value")
    private String email;

    @Schema(
            description = "Mobile number of the customer",
            example = "0241234567"
    )
    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number should be exactly 10 digits")
    private String mobileNumber;

    @Schema(
            description = "Account information of the customer"
    )
    private AccountsDto accountsDto;
}
