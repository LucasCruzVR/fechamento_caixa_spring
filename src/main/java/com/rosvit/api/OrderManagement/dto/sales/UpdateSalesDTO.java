package com.rosvit.api.OrderManagement.dto.sales;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateSalesDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Double value;
    private Boolean paid;
    private String description;
}
