package com.forefront.rest.webservices.restfulwebservices.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductJpaRepository extends JpaRepository<Product, Long>{
	List<Product> findByName(String username);
}
