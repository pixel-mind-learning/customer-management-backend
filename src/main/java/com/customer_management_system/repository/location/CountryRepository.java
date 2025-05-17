package com.customer_management_system.repository.location;

import com.customer_management_system.model.location.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author maleeshasa
 * @Date 2025-05-18
 */
@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
}
