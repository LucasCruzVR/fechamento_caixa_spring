package com.rosvit.api.OrderManagement.service;

import com.rosvit.api.OrderManagement.domain.Sales;
import com.rosvit.api.OrderManagement.dto.sales.SalesDTO;
import com.rosvit.api.OrderManagement.dto.sales.CreateSalesDTO;
import com.rosvit.api.OrderManagement.dto.sales.AllSalesDTO;
import com.rosvit.api.OrderManagement.dto.sales.UpdateSalesDTO;
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

    public AllSalesDTO getAllSalesOfDay(LocalDate date) {
        AllSalesDTO salesList = new AllSalesDTO();
        salesList.setSales(MapperClass.converter(salesRepository.allSalesByDay(date), SalesDTO.class));
        var total = salesList.getSales().stream().mapToDouble((sales) -> sales.getValue()).sum();
        salesList.setTotal(total);

        return salesList;
    }

    public AllSalesDTO getAllSalesOfMonth(String month, String year) {
        AllSalesDTO salesInfoDTO = new AllSalesDTO();
        salesInfoDTO.setSales(MapperClass.converter(salesRepository.allSalesByMonth(month, year), SalesDTO.class));
        var total = salesInfoDTO.getSales().stream().mapToDouble((sales) -> sales.getValue()).sum();
        salesInfoDTO.setTotal(total);

        return salesInfoDTO;
    }

    public AllSalesDTO getAllSales() {
        AllSalesDTO salesInfoDTO = new AllSalesDTO();
        salesInfoDTO.setSales(MapperClass.converter(salesRepository.findAll(), SalesDTO.class));
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
