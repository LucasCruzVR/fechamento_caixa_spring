package com.rosvit.api.OrderManagement.dto.orders;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Double value;
    private String description;
    private LocalDate date;
}
