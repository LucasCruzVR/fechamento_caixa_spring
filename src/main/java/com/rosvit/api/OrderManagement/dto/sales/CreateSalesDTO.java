package com.rosvit.api.OrderManagement.dto.sales;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateSalesDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Double value;
    private Boolean paid;
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
}
