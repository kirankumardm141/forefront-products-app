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
public class ProductResource {
	
	@Autowired
	private ProductHardcodedService productService;
	
	@GetMapping("/users/{username}/product")
	public List<Product> getAllProducts(@PathVariable String username){
		return productService.findByName(username);
	}

	@GetMapping("/users/{username}/products/{id}")
	public Product getTodo(@PathVariable String username, @PathVariable long id){
		return productService.findById(id);
	}

	//DELETE /users/{username}/todos/{id}
	@DeleteMapping("/users/{username}/products/{id}")
	public ResponseEntity<Void> deleteProduct(
			@PathVariable String username, @PathVariable long id){
		
		Product product = productService.deleteById(id);
		
		if(product!=null) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.notFound().build();
	}
	

	//Edit/Update a Todo
	//PUT /users/{user_name}/todos/{todo_id}
	@PutMapping("/users/{username}/products/{id}")
	public ResponseEntity<Product> updateProduct(
			@PathVariable String username,
			@PathVariable long id, @RequestBody Product product){
		
		Product productUpdated = productService.save(product);
		
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
	
	@PostMapping("/users/{username}/products")
	public ResponseEntity<Void> updateProduct(
			@PathVariable String username, @RequestBody Product product){
		
		Product createdProduct = productService.save(product);
		
		//Location
		//Get current resource url
		///{id}
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(createdProduct.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
		
}
