package com.phegondev.Phegon_Ecommerce.dto;

import lombok.Data;

@Data
public class OrderItemRequest {

	private Long productId;
	private int quantity;
}
