package com.rosvit.api.OrderManagement.repository;

import com.rosvit.api.OrderManagement.domain.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesRepository extends JpaRepository<Sales, Long> {

    @Query("SELECT s FROM Sales s WHERE s.date = CURRENT_DATE")
    List<Sales> allSalesByDay();
}
