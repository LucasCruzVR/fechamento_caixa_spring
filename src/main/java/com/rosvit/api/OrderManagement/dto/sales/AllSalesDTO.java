package com.rosvit.api.OrderManagement.dto.sales;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AllSalesDTO {

    private Long id;
    private Double value;
    private Boolean paid;
    private String description;
    private LocalDate date;
}
