package com.rosvit.api.OrderManagement.dto.sales;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SalesDTO {

    private Long id;

    private Double value;
    private Boolean paid;
    private Boolean delivery;
    private String description;
    private LocalDate date;
}
