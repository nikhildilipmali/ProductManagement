package com.example.product.dao;

import java.util.List;

import com.example.product.entity.Product;

public interface ProductDao {

	
	public boolean saveProduct(Product product) ;
	
	public List<Product>getAllProduct();
	
	public Product getProductById(String productId);
	
	
	public boolean updateProductById(Product product);
	
	public boolean deleteProductById(String productId);
}
