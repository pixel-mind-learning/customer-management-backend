package com.customer_management_system.repository.customer;

import com.customer_management_system.model.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author maleeshasa
 * @Date 2025-05-18
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByNic(String nic);
}
