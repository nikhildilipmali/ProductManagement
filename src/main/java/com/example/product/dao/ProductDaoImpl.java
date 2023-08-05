package com.example.product.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.product.entity.Product;

@Repository
public class ProductDaoImpl implements ProductDao {

	@Autowired
	SessionFactory sessionFactory;
	List<Product> list = new ArrayList();

	@Override
	public boolean saveProduct(Product product) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		boolean isAdded = false;
		try {
			session.save(product);
			transaction.commit();
			isAdded = true;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return isAdded;
	}

	@Override
	public List<Product> getAllProduct() {
		Session session = sessionFactory.openSession();
		// List<Product> list = null;
		try {
			Criteria criteria = session.createCriteria(Product.class);
			list = criteria.list();
			// session.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;

	}

	@Override
	public Product getProductById(String productId) {
		Session session = null;
		Product product = null;
		try {
			session = sessionFactory.openSession();
			product = session.get(Product.class, productId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}
	@Override
	public boolean updateProductById(Product product) {
		Session session = null;
		Transaction transaction = null; // save, update,delete
		boolean isUpdated = false;
		try {
			session = sessionFactory.openSession();

			transaction = session.beginTransaction();
			Product prd = session.get(Product.class, product.getProductId());
			if (prd != null) {
				session.evict(prd);
				session.update(product);
				transaction.commit();
				isUpdated = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isUpdated;
	}

	@Override
	public boolean deleteProductById(String productId) {
		Session session = null;
		Transaction transaction = null; // save, update,delete
		boolean isDeleted = false;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Product product = session.get(Product.class, productId);
			if (product != null) {
				session.delete(product);
				transaction.commit();
				isDeleted = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isDeleted;
	}

}
