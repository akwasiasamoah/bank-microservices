package com.asamoah.accounts.service.impl;

import com.asamoah.accounts.constants.AccountsConstants;
import com.asamoah.accounts.dto.AccountsDto;
import com.asamoah.accounts.dto.CustomerDto;
import com.asamoah.accounts.entity.Accounts;
import com.asamoah.accounts.entity.Customer;
import com.asamoah.accounts.exception.CustomerAlreadyExistsException;
import com.asamoah.accounts.exception.ResourceNotFoundException;
import com.asamoah.accounts.mapper.AccountsMapper;
import com.asamoah.accounts.mapper.CustomerMapper;
import com.asamoah.accounts.repositories.AccountsRepository;
import com.asamoah.accounts.repositories.CustomerRepository;
import com.asamoah.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;


@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;



    /**
     * Create a new account
     * @param customerDto - CustomerDto object
     */
    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());

        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());

        if (optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer with mobile number " + customerDto.getMobileNumber() + " already exists");
        }
        customer.setCreatedBy(AccountsConstants.SYSTEM);
        customer.setCreatedAt(LocalDateTime.now());
        Customer savedCustomer = customerRepository.save(customer);
        Accounts newAccount = createNewAccount(savedCustomer);
        accountsRepository.save(newAccount);
    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
      Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("Customer","mobileNumber", mobileNumber));

      Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(() -> new ResourceNotFoundException("Accounts","customerId", customer.getCustomerId().toString()));

      CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
      customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));
      return customerDto;
    }

    /**
     * @param customerDto - CustomerDto Object
     * @return boolean indicating if the update of Account details is successful or not
     */
    @Override
    public boolean updateAccount(CustomerDto customerDto) {

        boolean isUpdated = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if(accountsDto !=null ){
            Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "AccountNumber", accountsDto.getAccountNumber().toString())
            );
            AccountsMapper.mapToAccounts(accountsDto, accounts);
            accounts = accountsRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString())
            );
            CustomerMapper.mapToCustomer(customerDto,customer);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return  isUpdated;
    }

    /**
     * @param mobileNumber - mobile number
     * @return boolean indicating if the deletion of Account details is successful or not
     */

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }

    /**
     * @param customer - Customer Object
     * @return the new account details
     */
    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        newAccount.setCreatedBy(AccountsConstants.SYSTEM);
        newAccount.setCreatedAt(LocalDateTime.now());
        return newAccount;
    }
}
