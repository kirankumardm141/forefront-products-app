import { TODO_JPA_API_URL } from './../../app.constants';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Product } from '../../list-todos/list-todos.component';

@Injectable({
  providedIn: 'root'
})
export class ProductDataService {

  constructor(
    private http:HttpClient
  ) { }

  retrieveAllProducts(username) {
    return this.http.get<Product[]>(`${TODO_JPA_API_URL}/users/${username}/products`);
    //console.log("Execute Hello World Bean Service")
  }

  deleteProduct(username, id){
    return this.http.delete(`${TODO_JPA_API_URL}/users/${username}/products/${id}`);
  }

  retrieveProduct(username, id){
    return this.http.get<Product>(`${TODO_JPA_API_URL}/users/${username}/products/${id}`);
  }

  updateProduct(username, id, product){
    return this.http.put(
          `${TODO_JPA_API_URL}/users/${username}/products/${id}`
                , product);
  }

  createProduct(username, product){
    return this.http.post(
              `${TODO_JPA_API_URL}/users/${product.name}/products`
                , product);
  }

}
