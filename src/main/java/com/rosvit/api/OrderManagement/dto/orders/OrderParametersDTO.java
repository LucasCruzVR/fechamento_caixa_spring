package com.rosvit.api.OrderManagement.dto.orders;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderParametersDTO {

    private LocalDate startDate;
    private LocalDate endDate;
}
