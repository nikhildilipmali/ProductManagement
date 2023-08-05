package com.example.product.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Product {

	@Id
	private String productId;
	@NotNull(message = "product name is required...")
	private String productName;
	@Min(1)
	private String productQty;
	@Min(1)
	private double productPrice;
	@NotNull(message = "productType is required...")
	private String productType;
	
	
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Product(String productId, String productName, String productQty, double productPrice, String productType) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productQty = productQty;
		this.productPrice = productPrice;
		this.productType = productType;
	}


	public String getProductId() {
		return productId;
	}


	public void setProductId(String productId) {
		this.productId = productId;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public String getProductQty() {
		return productQty;
	}


	public void setProductQty(String productQty) {
		this.productQty = productQty;
	}


	public double getProductPrice() {
		return productPrice;
	}


	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}


	public String getProductType() {
		return productType;
	}


	public void setProductType(String productType) {
		this.productType = productType;
	}


	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", productQty=" + productQty
				+ ", productPrice=" + productPrice + ", productType=" + productType + "]";
	}


	
	
	
	
	
}
