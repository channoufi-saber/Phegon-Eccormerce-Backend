package com.phegondev.Phegon_Ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.phegondev.Phegon_Ecommerce.entity.User;

public interface UserRepo extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);
}
