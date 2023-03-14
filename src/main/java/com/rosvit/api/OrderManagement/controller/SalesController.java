package com.rosvit.api.OrderManagement.controller;

import com.rosvit.api.OrderManagement.dto.sales.AllSalesDTO;
import com.rosvit.api.OrderManagement.dto.sales.CreateSalesDTO;
import com.rosvit.api.OrderManagement.dto.sales.SalesInfoDTO;
import com.rosvit.api.OrderManagement.service.SalesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sales")
@RequiredArgsConstructor
public class SalesController {

    private final SalesService salesService;

    @GetMapping("/day")
    @ResponseBody
    public ResponseEntity<SalesInfoDTO> listAllSalesOfDay() {
        return ResponseEntity.ok().body(salesService.getAllSalesOfDay());
    }

    @GetMapping()
    @ResponseBody
    public ResponseEntity<SalesInfoDTO> listAllSales() {
        return ResponseEntity.ok().body(salesService.getAllSales());
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createSales(@RequestBody CreateSalesDTO createSalesDTO) {
        salesService.createSales(createSalesDTO);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
