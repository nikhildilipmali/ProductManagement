package com.example.product.controller;

import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.product.entity.Product;
import com.example.product.service.ProductService;

@RestController
public class ProductController {
	@Autowired
	private ProductService service;

	@PostMapping("/saveProduct")
	public ResponseEntity<Boolean> saveProduct(@Valid @RequestBody Product product) {
		boolean isAdded = service.saveProduct(product);
		if (isAdded) {
			return new ResponseEntity<Boolean>(isAdded, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Boolean>(isAdded, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/getAllProduct")
	public ResponseEntity<List<Product>> getAllProduct() {
		List<Product> isListProduct = service.getAllProduct();

		if (isListProduct.isEmpty()) {
			return new ResponseEntity<List<Product>>(isListProduct, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<Product>>(isListProduct, HttpStatus.FOUND);
		}

	}

	@GetMapping(value= "/getProductById/{productId}")
	public ResponseEntity<Product> getProductById(@PathVariable String productId) {
		Product product = service.getProductById(productId);
		if (product != null) {
			return new ResponseEntity<Product>(product,HttpStatus.OK);
		} else {
			return new ResponseEntity<Product>( HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/updateProductById")
	public ResponseEntity<Boolean> updateProductById(@RequestBody Product product) {
		boolean isUpdated = service.updateProductById(product);
		if (isUpdated) {
			return new ResponseEntity<Boolean>(isUpdated, HttpStatus.OK);
		} else {
			return new ResponseEntity<Boolean>(HttpStatus.NO_CONTENT);
		}
	}

	@DeleteMapping("/deleteProduct/{productId}")
	public ResponseEntity<Boolean> deleteProductById(@PathVariable String productId) {
		boolean isDeleted = service.deleteProductById(productId);

		if (isDeleted) {
			return new ResponseEntity<Boolean>(isDeleted, HttpStatus.OK);
		} else {
			return new ResponseEntity<Boolean>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping(value = "/sortById")
	public ResponseEntity<List<Product>> sortByProductId(@RequestParam String sortBy) {
		List<Product> sortedProduct = service.sortByProductId(sortBy);

		if (!sortedProduct.isEmpty()) {
			return new ResponseEntity<List<Product>>(sortedProduct, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT);
		}

	}

	@GetMapping("/getMaxPriceProducts")
	public ResponseEntity<Product> getMaxPriceProducts() {
		Product product = service.getMaxPriceProducts();
		if (product != null) {
			return new ResponseEntity<Product>(product, HttpStatus.OK);
		} else {
			return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/getSumOfProductPrice")
	public ResponseEntity<Double> getSumOfProductPrice() {
		double sum = service.getSumOfProductPrice();
		if (sum > 0) {
			return new ResponseEntity<Double>(sum, HttpStatus.OK);
		} else {
			return new ResponseEntity<Double>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/getTotalCountOfProducts")
	public ResponseEntity<Integer> getTotalCountOfProducts() {
		int totalProductCount = service.getTotalCountOfProducts();
		if (totalProductCount > 0) {
			return new ResponseEntity<Integer>(totalProductCount, HttpStatus.OK);

		} else {
			return new ResponseEntity<Integer>(HttpStatus.NO_CONTENT);
		}
	}

}
