package com.phegondev.Phegon_Ecommerce.service.interf;

import java.time.LocalDateTime;

import org.springframework.data.domain.Pageable;

import com.phegondev.Phegon_Ecommerce.dto.OrderRequest;
import com.phegondev.Phegon_Ecommerce.dto.Response;
import com.phegondev.Phegon_Ecommerce.enums.OrderStatus;

public interface OrderItemService {
	Response placeOrder(OrderRequest orderRequest);
	Response updateOrderItemStatus(Long orderItemId,String status);
	Response filterOrderItems(OrderStatus status,LocalDateTime startDate,LocalDateTime endDate,Long itemId,Pageable pageable);

}
