package com.charter.rewards.repository;

import com.charter.rewards.vo.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT c, SUM(p.pointAmount) FROM Customer c" +
            " JOIN c.points p" +
            " WHERE c.customerId = p.customer.customerId" +
            " GROUP BY c.customerId")
    List<Object[]> findCustomersWithPoints();

}
