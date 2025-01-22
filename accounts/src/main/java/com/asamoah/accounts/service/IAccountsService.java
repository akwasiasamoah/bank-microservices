package com.asamoah.accounts.service;

import com.asamoah.accounts.dto.CustomerDto;

public interface IAccountsService {

    /**
     * Create a new account
     * @param customerDto - CustomerDto object
     */
    void createAccount(CustomerDto customerDto);

    /**
     * Fetch account details
     * @param mobileNumber - mobile number
     * @return CustomerDto object
     */
    CustomerDto fetchAccount(String mobileNumber);

    /**
     * @param customerDto - CustomerDto Object
     * @return boolean indicating if the update of Account details is successful or not
     */
    boolean updateAccount(CustomerDto customerDto);


    /**
     * @param mobileNumber - mobile number
     * @return boolean indicating if the deletion of Account details is successful or not
     */

    boolean deleteAccount(String mobileNumber);



}
