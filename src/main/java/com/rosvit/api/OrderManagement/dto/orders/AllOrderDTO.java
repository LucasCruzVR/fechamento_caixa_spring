package com.rosvit.api.OrderManagement.dto.orders;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AllOrderDTO {

    private List<OrderDTO> orderList;
    private Double total;
}
