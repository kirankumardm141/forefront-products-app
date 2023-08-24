package com.forefront.rest.webservices.restfulwebservices.product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;


@Service
public class ProductHardcodedService {
	
	private static List<Product> products = new ArrayList<>();
	private static long idCounter = 0;
	
	static {
		products.add(new Product(++idCounter, "kiran",2000L,25L));
		products.add(new Product(++idCounter, "kiran",2344L,56L));
		products.add(new Product(++idCounter, "kiran",6756L,67L));
	}
	
	public List<Product> findAll() {
		return products;
	}

	public Product save(Product product) {
		if(product.getId()==-1 || product.getId()==0) {
			product.setId(++idCounter);
			products.add(product);
		} else {
			deleteById(product.getId());
			products.add(product);
		}
		return product;
	}
	
	public Product deleteById(long id) {
		Product product = findById(id);
		
		if(product==null) return null;
		
		if(products.remove(product)) {
			return product;
		}
		
		return null;
	}

	public Product findById(long id) {
		for(Product product:products) {
			if(product.getId() == id) {
				return product;
			}
		}
		
		return null;
	}

	public List<Product> findByName(String username) {
		// TODO Auto-generated method stub
		return products;
	}
	
}
