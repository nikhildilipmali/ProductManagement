package com.example.product.service;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.product.dao.ProductDao;
import com.example.product.entity.Product;
import com.example.product.sort.ProductIdComparator;
import com.example.product.sort.ProductNameComparator;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	@Override
	public boolean saveProduct(Product product) {
		String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
		product.setProductId(time);
		boolean isAdded = productDao.saveProduct(product);

		return isAdded;

	}

	@Override
	public List<Product> getAllProduct() {
		List<Product> list = productDao.getAllProduct();
		return list;
	}

	@Override
	public Product getProductById(String productId) {
		Product product = productDao.getProductById(productId);

		return product;
	}

	@Override
	public boolean updateProductById(Product product) {
		boolean isUpdated = productDao.updateProductById(product);
		return isUpdated;
	}

	@Override
	public boolean deleteProductById(String productId) {
		boolean isDeleted = productDao.deleteProductById(productId);
		return isDeleted;
	}

	@Override
	public List<Product> sortByProductId(String sortBy) {
		List<Product> allProducts = getAllProduct();

		if (allProducts.size() > 1) {

			if (sortBy.equalsIgnoreCase("productId")) {
				Collections.sort(allProducts, new ProductIdComparator());

			} else if (sortBy.equalsIgnoreCase("productName")) {
				Collections.sort(allProducts, new ProductNameComparator());
			}
		}
		return allProducts;
	}

	@Override
	public Product getMaxPriceProducts() {
		List<Product> allProduct = getAllProduct();
		Product product = null;
		if (!allProduct.isEmpty()) {
			product = allProduct.stream().max(Comparator.comparingDouble(Product::getProductPrice)).get();
		}
		return product;
	}

	@Override
	public double getSumOfProductPrice() {
		List<Product> allProduct = getAllProduct();
		double sum = 0;
		if (!allProduct.isEmpty()) {
			sum = allProduct.stream().mapToDouble(Product::getProductPrice).sum();
		}
		return sum;
	}

	@Override
	public int getTotalCountOfProducts() {
		List<Product> allProduct = getAllProduct();
		int size = 0;
		if (!allProduct.isEmpty()) {
			size = allProduct.size();
		}
		return size;
	}

}
