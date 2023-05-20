package com.rosvit.api.OrderManagement.dto.sales;

import jakarta.validation.constraints.Digits;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AllSalesDTO {
    @Digits(integer=10, fraction=2)
    private Double total;
    private List<SalesDTO> sales;
}
