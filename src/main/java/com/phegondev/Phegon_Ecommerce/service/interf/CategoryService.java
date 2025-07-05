package com.phegondev.Phegon_Ecommerce.service.interf;

import com.phegondev.Phegon_Ecommerce.dto.CategoryDto;
import com.phegondev.Phegon_Ecommerce.dto.Response;

public interface CategoryService {

	Response createCategory(CategoryDto categoryRequest);
	Response updateCategory(Long categoryId, CategoryDto categoryRequest);
	Response getAllCategories();
	Response getCategoryById(Long categoryId);
	Response deleteCategory(Long categoryId);
}
