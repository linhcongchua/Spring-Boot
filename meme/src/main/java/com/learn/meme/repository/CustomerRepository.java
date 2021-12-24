package com.learn.meme.repository;

import com.learn.meme.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    UserDetails getByUsername(String username);

    Customer getCustomerByUsername(String username);

    @Query(value = "select * from customer where create_date between :i_from and :i_to", nativeQuery = true)
    List<Customer> getNewUsersInMonth(@Param("i_from") Date from, @Param("i_to") Date to);
}
