package com.forefront.rest.webservices.restfulwebservices.product;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@CrossOrigin(origins="http://localhost:4200")
@RestController
public class ProductJpaResource {
	
	@Autowired
	private ProductHardcodedService productService;

	@Autowired
	private ProductJpaRepository productJpaRepository;

	
	@GetMapping("/jpa/users/{username}/products")
	public List<Product> getAllProducts(@PathVariable String username){
//		return productJpaRepository.findByName(username);
		return productJpaRepository.findAll();
		//return todoService.findAll();
	}

	@GetMapping("/jpa/users/{username}/products/{id}")
	public Product getProduct(@PathVariable String username, @PathVariable long id){
		return productJpaRepository.findById(id).get();
		//return todoService.findById(id);
	}

	//DELETE /users/{username}/todos/{id}
	@DeleteMapping("/jpa/users/{username}/products/{id}")
	public ResponseEntity<Void> deleteProduct(
			@PathVariable String username, @PathVariable long id){
		
		//Todo todo = todoService.deleteById(id);
		productJpaRepository.deleteById(id);
		
		return ResponseEntity.noContent().build();
		//return ResponseEntity.notFound().build();
	}
	

	//Edit/Update a Todo
	//PUT /users/{user_name}/todos/{todo_id}
	@PutMapping("/jpa/users/{username}/products/{id}")
	public ResponseEntity<Product> updateProduct(
			@PathVariable String username,
			@PathVariable long id, @RequestBody Product product){
		
		//Todo todoUpdated = todoService.save(todo);
		Product productUpdated = productJpaRepository.save(product);
		
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
	
	@PostMapping("/jpa/users/{username}/products")
	public ResponseEntity<Void> createProduct(
			@PathVariable String username, @RequestBody Product product){
		
		//Todo createdTodo = todoService.save(todo);
		product.setName(username);
		Product createdProduct = productJpaRepository.save(product);
		
		//Location
		//Get current resource url
		///{id}
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(createdProduct.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
		
}
