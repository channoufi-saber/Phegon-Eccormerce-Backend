package com.phegondev.Phegon_Ecommerce.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.phegondev.Phegon_Ecommerce.dto.CategoryDto;
import com.phegondev.Phegon_Ecommerce.dto.Response;
import com.phegondev.Phegon_Ecommerce.entity.Category;
import com.phegondev.Phegon_Ecommerce.exception.NotFoundException;
import com.phegondev.Phegon_Ecommerce.mapper.EntityDtoMapper;
import com.phegondev.Phegon_Ecommerce.repository.CategoryRepo;
import com.phegondev.Phegon_Ecommerce.service.interf.CategoryService;

public class CategoryServiceImpl implements CategoryService {
	
	private final CategoryRepo categoryRepo = null;
	private final EntityDtoMapper entityDtoMapper = new EntityDtoMapper();
	

	@Override
	public Response createCategory(CategoryDto categoryRequest) {
		Category category=new Category();
		category.setName(categoryRequest.getName());
		categoryRepo.save(category);
		return Response.builder().status(200).message("Category created successfully").build();
	}

	@Override
	public Response updateCategory(Long categoryId, CategoryDto categoryRequest) {
		Category category=categoryRepo.findById(categoryId).orElseThrow(()->new NotFoundException("Ctagory Not Found"));
		categoryRepo.save(category);
		return Response.builder().status(200).message("category updated successfully").build();
	}

	@Override
	public Response getAllCategories() {
		List<Category> categories=categoryRepo.findAll();
		List<CategoryDto> categoryDtoList=categories.stream().map(entityDtoMapper::mapCategoryToDtoBasic).collect(Collectors.toList());
		return Response.builder().status(200).categoryList(categoryDtoList).build();
	}

	@Override
	public Response getCategoryById(Long categoryId) {
		Category category=categoryRepo.findById(categoryId).orElseThrow(()->new NotFoundException("Category Not Found"));
		CategoryDto categoryDto=entityDtoMapper.mapCategoryToDtoBasic(category);
		return Response.builder().status(200).category(categoryDto).build();
	}

	@Override
	public Response deleteCategory(Long categoryId) {
		Category category=categoryRepo.findById(categoryId).orElseThrow(()->new NotFoundException("Category Not Found"));
		categoryRepo.delete(category);
		return Response.builder().status(200).message("Category was deleted successfully").build();
	}

}
