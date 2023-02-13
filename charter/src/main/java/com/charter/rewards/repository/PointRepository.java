package com.charter.rewards.repository;

import com.charter.rewards.vo.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PointRepository extends JpaRepository<Point, Long> {
    @Query(" SELECT c.customerId, SUM(p.pointAmount), Month(p.transactionDate) as month" +
            " FROM Customer c JOIN c.points p " +
            " WHERE c.customerId = p.customer.customerId" +
            " AND p.transactionDate BETWEEN :startDate AND :endDate" +
            " GROUP BY c.customerId, Month(p.transactionDate)")
    List<Object[]> findPointsByMonth(@Param("startDate") LocalDate startDate,
                                     @Param("endDate") LocalDate endDate);
}
