package com.phegondev.Phegon_Ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.phegondev.Phegon_Ecommerce.entity.Order;

public interface OrderRepo extends JpaRepository<Order, Long> {

}
