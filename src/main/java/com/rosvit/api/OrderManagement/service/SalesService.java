package com.rosvit.api.OrderManagement.service;

import com.rosvit.api.OrderManagement.domain.Sales;
import com.rosvit.api.OrderManagement.dto.orders.OrderParametersDTO;
import com.rosvit.api.OrderManagement.dto.sales.*;
import com.rosvit.api.OrderManagement.repository.SalesRepository;
import com.rosvit.api.OrderManagement.util.MapperClass;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class SalesService {

    private final SalesRepository salesRepository;

    public AllSalesDTO getAllSalesOfDay(SalesParameterDTO salesParametersDTO) {
        AllSalesDTO salesList = new AllSalesDTO();
        salesList.setSales(MapperClass.converter(salesRepository.allSalesByDate(salesParametersDTO.getStartDate(), salesParametersDTO.getEndDate()), SalesDTO.class));
        var total = salesList.getSales().stream().mapToDouble(SalesDTO::getValue).sum();
        salesList.setTotal(total);

        return salesList;
    }

    @Transactional
    public void createSales(CreateSalesDTO createSalesDTO) {
        Sales sales = Sales.builder()
                .date(createSalesDTO.getDate())
                .description(createSalesDTO.getDescription())
                .paid(createSalesDTO.getPaid())
                .delivery(createSalesDTO.getDelivery())
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
                .delivery(updateSalesDTO.getDelivery())
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
        return salesRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, "Venda não encontrada"));
    }
}
