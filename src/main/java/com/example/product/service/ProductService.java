package com.example.product.service;

import java.util.List;

import com.example.product.entity.Product;

public interface ProductService {

	public boolean saveProduct(Product product) ;
	
	public List<Product>getAllProduct();
	
	public Product getProductById(String productId);
	
	public boolean updateProductById(Product product);
	
	public boolean deleteProductById(String productId);
	public List<Product> sortByProductId(String sortBy);
	public Product getMaxPriceProducts();
	public double getSumOfProductPrice();
	public int getTotalCountOfProducts();
}
