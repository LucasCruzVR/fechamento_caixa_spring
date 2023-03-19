package com.rosvit.api.OrderManagement.repository;

import com.rosvit.api.OrderManagement.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
