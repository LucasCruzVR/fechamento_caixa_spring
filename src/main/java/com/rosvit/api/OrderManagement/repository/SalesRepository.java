package com.rosvit.api.OrderManagement.repository;

import com.rosvit.api.OrderManagement.domain.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SalesRepository extends JpaRepository<Sales, Long> {

    @Query("SELECT s FROM Sales s WHERE s.date = :date")
    List<Sales> allSalesByDay(@Param("date") LocalDate date);

    @Query(value = "SELECT * FROM Sales WHERE to_char(date, 'MM') = :month AND to_char(date, 'YYYY') = :year", nativeQuery = true)
    List<Sales> allSalesByMonth(@Param("month") String month, @Param("year") String year);
}
