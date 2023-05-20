package com.rosvit.api.OrderManagement.repository;

import com.rosvit.api.OrderManagement.domain.Order;
import com.rosvit.api.OrderManagement.domain.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "SELECT * FROM orders WHERE to_char(date, 'MM') = :month AND to_char(date, 'YYYY') = :year", nativeQuery = true)
    List<Order> allOrderByMonth(@Param("month") String month, @Param("year") String year);
}
