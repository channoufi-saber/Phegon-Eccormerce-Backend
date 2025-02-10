package com.phegondev.Phegon_Ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.phegondev.Phegon_Ecommerce.entity.Address;

public interface AddressRepo extends JpaRepository<Address, Long> {

}
