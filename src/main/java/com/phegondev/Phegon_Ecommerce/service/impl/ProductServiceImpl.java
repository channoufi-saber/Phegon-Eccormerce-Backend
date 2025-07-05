package com.phegondev.Phegon_Ecommerce.service.impl;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.phegondev.Phegon_Ecommerce.dto.ProductDto;
import com.phegondev.Phegon_Ecommerce.dto.Response;
import com.phegondev.Phegon_Ecommerce.entity.Category;
import com.phegondev.Phegon_Ecommerce.entity.Product;
import com.phegondev.Phegon_Ecommerce.exception.NotFoundException;
import com.phegondev.Phegon_Ecommerce.mapper.EntityDtoMapper;
import com.phegondev.Phegon_Ecommerce.repository.CategoryRepo;
import com.phegondev.Phegon_Ecommerce.repository.ProductRepo;
import com.phegondev.Phegon_Ecommerce.service.interf.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
	
	private final ProductRepo productRepo;
	private final CategoryRepo categoryRepo;
	private final EntityDtoMapper entityDtoMapper;
	
	@Override
	public Response createProduct(Long categoryId, MultipartFile image, String name, String description,BigDecimal price) {
		Category category=categoryRepo.findById(categoryId).orElseThrow(()-> new NotFoundException("Category not found"));
		String productImageUrl=saveImageLocally(image);
		Product product=new Product();
		product.setCategory(category);
		product.setPrice(price);
		product.setName(name);
		product.setDescription(description);
		product.setImageUrl(productImageUrl);
		productRepo.save(product);
		
		return Response.builder().status(200).message("Product successfully created").build();
	}

	private String saveImageLocally(MultipartFile image) {
		try {
			String uploadDir="uploads/products/";
			File uploadPath=new File(uploadDir);
			if (!uploadPath.exists()) {
				uploadPath.mkdirs();
			}
			String originalFilename=image.getOriginalFilename();
			String fileExtension=originalFilename.substring(originalFilename.lastIndexOf("."));
			String uniqueFilename=UUID.randomUUID().toString() + fileExtension;
			File destination=new File(uploadPath.getAbsolutePath() + File.separator + uniqueFilename);
			image.transferTo(destination);
			return "/"+uploadDir+uniqueFilename;
		} catch (IOException e) {
			throw new RuntimeException("Failed to save image:"+e.getMessage(),e);
		}
	}

	@Override
	public Response updateProduct(Long productId, Long categoryId, MultipartFile image, String name, String description,
			BigDecimal price) {
		Product product=productRepo.findById(productId).orElseThrow(()->new NotFoundException("Product Not Found"));
		Category category=null;
		String productImageUrl=null;
		if (categoryId !=null) {
			category=categoryRepo.findById(categoryId).orElseThrow(()->new NotFoundException("Category Not found"));
		}
		if (image != null && !image.isEmpty()) {
			 productImageUrl=saveImageLocally(image);
		}
		if(category !=null) product.setCategory(category);
		if(name !=null) product.setName(name);
		if(price !=null) product.setPrice(price);
		if(description !=null) product.setDescription(description);
		if(productImageUrl !=null) product.setImageUrl(productImageUrl);
		productRepo.save(product);
		return Response.builder().status(200).message("Product updated successfully").build();
	}

	@Override
	public Response deleteProduct(Long productId) {
		Product product=productRepo.findById(productId).orElseThrow(()->new NotFoundException("Product Not Found"));
		productRepo.delete(product);
		return Response.builder().status(200).message("Product deleted successfully").build();
	}

	@Override
	public Response getProductById(Long productId) {
		Product product=productRepo.findById(productId).orElseThrow(()->new NotFoundException("Product Not Found"));
		ProductDto productDto=entityDtoMapper.mapProductToDtoBasic(product);
		return Response.builder().status(200).product(productDto).build();
	}

	@Override
	public Response getAllProducts() {
		List<ProductDto> productList=productRepo.findAll(Sort.by(Sort.Direction.DESC,"id")).stream().map(entityDtoMapper::mapProductToDtoBasic).collect(Collectors.toList());
		return Response.builder().status(200).productList(productList).build();
	}

	@Override
	public Response getProductsByCategory(Long categoryId) {
		List<Product> products=productRepo.findByCategoryId(categoryId);
		if (products.isEmpty()) {
			throw new NotFoundException("No Products found for this category");
		}
		List<ProductDto> productDtoList=products.stream().map(entityDtoMapper::mapProductToDtoBasic).collect(Collectors.toList());
		return Response.builder().status(200).productList(productDtoList).build();
	}

	@Override
	public Response searchProduct(String searchValue) {
		List<Product> products=productRepo.findByNameContainingOrDescriptionContaining(searchValue, searchValue);
		if (products.isEmpty()) {
			throw new NotFoundException("No Products found for this category");
		}
		List<ProductDto> productDtoList=products.stream().map(entityDtoMapper::mapProductToDtoBasic).collect(Collectors.toList());
		return Response.builder().productList(productDtoList).build();
	}

}
