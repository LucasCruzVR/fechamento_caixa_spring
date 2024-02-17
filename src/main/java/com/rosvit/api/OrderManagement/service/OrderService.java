package com.rosvit.api.OrderManagement.service;

import com.rosvit.api.OrderManagement.domain.Order;
import com.rosvit.api.OrderManagement.dto.orders.AllOrderDTO;
import com.rosvit.api.OrderManagement.dto.orders.OrderDTO;
import com.rosvit.api.OrderManagement.dto.orders.OrderParametersDTO;
import com.rosvit.api.OrderManagement.repository.OrderRepository;
import com.rosvit.api.OrderManagement.util.MapperClass;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public AllOrderDTO getAllOrders(OrderParametersDTO orderParametersDTO) {
        AllOrderDTO orderList = new AllOrderDTO();
        orderList.setOrderList(MapperClass.converter(orderRepository.allOrderByDate(orderParametersDTO.getStartDate(), orderParametersDTO.getEndDate()), OrderDTO.class));
        var total = orderList.getOrderList().stream().mapToDouble(OrderDTO::getValue).sum();
        orderList.setTotal(total);
        return orderList;
    }

    @Transactional
    public void createOrder(OrderDTO orderDTO) {
        Order order = Order.builder()
                .description(orderDTO.getDescription())
                .date(orderDTO.getDate())
                .value(orderDTO.getValue())
                .build();
        orderRepository.save(order);
    }

    @Transactional
    public void updateOrder(Long id, OrderDTO orderDTO) {
        Order order = getOrder(id);
        order = order.toBuilder()
                .description(orderDTO.getDescription())
                .value(orderDTO.getValue())
                .build();
        orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        try {
            orderRepository.deleteById(id);
        } catch (Exception err) {
            throw new ObjectNotFoundException(id, "Pedido de compra não encontrado");
        }
    }

    private Order getOrder(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, "Pedido de compra não encontrado"));
    }
}
