package com.rosvit.api.OrderManagement.service;

import com.rosvit.api.OrderManagement.domain.Sales;
import com.rosvit.api.OrderManagement.dto.sales.AllSalesDTO;
import com.rosvit.api.OrderManagement.dto.sales.CreateSalesDTO;
import com.rosvit.api.OrderManagement.dto.sales.UpdateSalesDTO;
import com.rosvit.api.OrderManagement.repository.SalesRepository;
import com.rosvit.api.OrderManagement.util.MapperClass;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SalesService {

    private final SalesRepository salesRepository;

    public List<AllSalesDTO> getAllSalesOfDay() {
        return MapperClass.converter(salesRepository.allSalesByDay(), AllSalesDTO.class);
    }

    public List<AllSalesDTO> getAllSales() {
        return MapperClass.converter(salesRepository.findAll(), AllSalesDTO.class);
    }

    @Transactional
    public void createSales(CreateSalesDTO createSalesDTO) {
        Sales sales = Sales.builder()
                .date(createSalesDTO.getDate())
                .description(createSalesDTO.getDescription())
                .paid(createSalesDTO.getPaid())
                .value(createSalesDTO.getValue())
                .build();
        salesRepository.save(sales);
    }

    @Transactional
    public void updateSales(Long id, UpdateSalesDTO updateSalesDTO) {
        Sales sales = getSales(id);
        sales = sales.toBuilder()
                .description(updateSalesDTO.getDescription())
                .paid(updateSalesDTO.getPaid())
                .value(updateSalesDTO.getValue())
                .build();
        salesRepository.save(sales);
    }

    public void deleteSales(Long id) {
        try {
            salesRepository.deleteById(id);
        } catch (Exception err) {
            throw new ObjectNotFoundException(id, "Sales not found");
        }
    }

    private Sales getSales(Long id) {
        return salesRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, "Sales not found"));
    }
}
