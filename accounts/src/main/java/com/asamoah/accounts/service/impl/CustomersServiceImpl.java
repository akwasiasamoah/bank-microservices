package com.asamoah.accounts.service.impl;

import com.asamoah.accounts.dto.AccountsDto;
import com.asamoah.accounts.dto.CardsDto;
import com.asamoah.accounts.dto.CustomerDetailsDto;
import com.asamoah.accounts.dto.LoansDto;
import com.asamoah.accounts.entity.Accounts;
import com.asamoah.accounts.entity.Customer;
import com.asamoah.accounts.exception.ResourceNotFoundException;
import com.asamoah.accounts.mapper.AccountsMapper;
import com.asamoah.accounts.mapper.CustomerMapper;
import com.asamoah.accounts.repositories.AccountsRepository;
import com.asamoah.accounts.repositories.CustomerRepository;
import com.asamoah.accounts.service.ICustomersService;
import com.asamoah.accounts.service.client.CardsFeignClient;
import com.asamoah.accounts.service.client.LoansFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomersServiceImpl implements ICustomersService {

    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;
    private final CardsFeignClient cardsFeignClient;
    private final LoansFeignClient loansFeignClient;

    /**
     * @param mobileNumber - Input Mobile Number
     * @return Customer Details based on a given mobileNumber
     */
    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber, String correlationId) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );

        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(correlationId,mobileNumber);
        customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());

        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(correlationId,mobileNumber);
        customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());

        return customerDetailsDto;

    }
}