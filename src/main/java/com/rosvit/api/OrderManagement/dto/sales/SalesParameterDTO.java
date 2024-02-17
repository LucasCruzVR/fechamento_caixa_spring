package com.rosvit.api.OrderManagement.dto.sales;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SalesParameterDTO {

    private LocalDate startDate;
    private LocalDate endDate;
}
