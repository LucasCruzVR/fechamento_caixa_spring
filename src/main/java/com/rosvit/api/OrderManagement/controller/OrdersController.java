package com.rosvit.api.OrderManagement.controller;

import com.rosvit.api.OrderManagement.dto.orders.AllOrderDTO;
import com.rosvit.api.OrderManagement.dto.orders.AllOrderDTO;
import com.rosvit.api.OrderManagement.dto.orders.OrderDTO;
import com.rosvit.api.OrderManagement.dto.orders.OrderParametersDTO;
import com.rosvit.api.OrderManagement.dto.sales.CreateSalesDTO;
import com.rosvit.api.OrderManagement.dto.sales.UpdateSalesDTO;
import com.rosvit.api.OrderManagement.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrdersController {

    private final OrderService orderService;

    @GetMapping("/")
    @ResponseBody
    public ResponseEntity<AllOrderDTO> listAllSalesOfMonth(@RequestBody @Valid OrderParametersDTO orderParametersDTO) {
        return ResponseEntity.ok().body(orderService.getAllOrders(orderParametersDTO));
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createSales(@RequestBody @Valid OrderDTO orderDTO) {
        orderService.createOrder(orderDTO);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateSales(@PathVariable Long id, @RequestBody @Valid OrderDTO orderDTO) {
        orderService.updateOrder(id, orderDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteSales(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
