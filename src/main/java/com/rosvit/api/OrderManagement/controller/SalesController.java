package com.rosvit.api.OrderManagement.controller;

import com.rosvit.api.OrderManagement.dto.orders.OrderParametersDTO;
import com.rosvit.api.OrderManagement.dto.sales.CreateSalesDTO;
import com.rosvit.api.OrderManagement.dto.sales.AllSalesDTO;
import com.rosvit.api.OrderManagement.dto.sales.SalesParameterDTO;
import com.rosvit.api.OrderManagement.dto.sales.UpdateSalesDTO;
import com.rosvit.api.OrderManagement.service.SalesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/sales")
@RequiredArgsConstructor
public class SalesController {

    private final SalesService salesService;

    @GetMapping("")
    @ResponseBody
    public ResponseEntity<AllSalesDTO> listAllSalesOfDay(@RequestBody @Valid SalesParameterDTO salesParametersDTO) {
        return ResponseEntity.ok().body(salesService.getAllSalesOfDay(salesParametersDTO));
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createSales(@RequestBody @Valid CreateSalesDTO createSalesDTO) {
        salesService.createSales(createSalesDTO);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateSales(@PathVariable Long id, @RequestBody @Valid UpdateSalesDTO updateSalesDTO) {
        salesService.updateSales(id, updateSalesDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteSales(@PathVariable Long id) {
        salesService.deleteSales(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
