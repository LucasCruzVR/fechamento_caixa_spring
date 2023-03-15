package com.rosvit.api.OrderManagement.service;

import com.rosvit.api.OrderManagement.domain.Sales;
import com.rosvit.api.OrderManagement.dto.sales.AllSalesDTO;
import com.rosvit.api.OrderManagement.dto.sales.CreateSalesDTO;
import com.rosvit.api.OrderManagement.dto.sales.SalesInfoDTO;
import com.rosvit.api.OrderManagement.dto.sales.UpdateSalesDTO;
import com.rosvit.api.OrderManagement.repository.SalesRepository;
import com.rosvit.api.OrderManagement.util.MapperClass;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SalesService {

    private final SalesRepository salesRepository;

    public SalesInfoDTO getAllSalesOfDay(LocalDate date) {
        SalesInfoDTO salesInfoDTO = new SalesInfoDTO();
        salesInfoDTO.setSales(MapperClass.converter(salesRepository.allSalesByDay(date), AllSalesDTO.class));
        var total = salesInfoDTO.getSales().stream().mapToDouble((sales) -> sales.getValue()).sum();
        salesInfoDTO.setTotal(total);

        return salesInfoDTO;
    }

    public SalesInfoDTO getAllSalesOfMonth(String month, String year) {
        SalesInfoDTO salesInfoDTO = new SalesInfoDTO();
        salesInfoDTO.setSales(MapperClass.converter(salesRepository.allSalesByMonth(month, year), AllSalesDTO.class));
        var total = salesInfoDTO.getSales().stream().mapToDouble((sales) -> sales.getValue()).sum();
        salesInfoDTO.setTotal(total);

        return salesInfoDTO;
    }

    public SalesInfoDTO getAllSales() {
        SalesInfoDTO salesInfoDTO = new SalesInfoDTO();
        salesInfoDTO.setSales(MapperClass.converter(salesRepository.findAll(), AllSalesDTO.class));
        var total = salesInfoDTO.getSales().stream().mapToDouble((sales) -> sales.getValue()).sum();
        salesInfoDTO.setTotal(total);

        return salesInfoDTO;
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
