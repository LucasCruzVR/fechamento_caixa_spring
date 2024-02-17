package com.rosvit.api.OrderManagement.repository;

import com.rosvit.api.OrderManagement.domain.Order;
import com.rosvit.api.OrderManagement.domain.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "SELECT * FROM Sales WHERE date >= :startDate AND date <= :endDate", nativeQuery = true)
    List<Order> allOrderByDate(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
