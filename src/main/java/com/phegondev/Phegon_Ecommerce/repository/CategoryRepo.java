package com.phegondev.Phegon_Ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.phegondev.Phegon_Ecommerce.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Long> {

}
