package com.asamoah.accounts.repositories;


import com.asamoah.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    /**
     * Find customer by mobile number
     * @param mobileNumber - mobile number
     * @return Optional<Customer>
     */
    Optional<Customer> findByMobileNumber(String mobileNumber);
}
