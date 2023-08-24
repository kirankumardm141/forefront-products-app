import { ProductDataService } from './../service/data/todo-data.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

export class Product {
  constructor(
    public id: number,
    public name: string,
    public price: number,
    public stock: number
  ){

  }
}

@Component({
  selector: 'app-list-todos',
  templateUrl: './list-todos.component.html',
  styleUrls: ['./list-todos.component.css']
})
export class ListProductsComponent implements OnInit {

  products: Product[]

  message: string

  // = [
  //   new Todo(1, 'Learn to Dance', false, new Date()),
  //   new Todo(2, 'Become an Expert at Angular', false, new Date()),
  //   new Todo(3, 'Visit India', false, new Date())
  //   // {id : 1, description : },
  //   // {id : 2, description : ''},
  //   // {id : 3, description : 'Visit India'}
  // ]

  // todo = {
  //     id : 1,
  //     description: 'Learn to Dance'
  // }

  constructor(
    private productService:ProductDataService,
    private router : Router
  ) { }

  ngOnInit() {
    this.refreshProducts();
  }

  refreshProducts(){
    this.productService.retrieveAllProducts('kiran').subscribe(
      response => {
        console.log(response);
        this.products = response;
      }
    )
  }

  deleteProduct(id) {
    console.log(`delete todo ${id}` )
    this.productService.deleteProduct('kiran', id).subscribe (
      response => {
        console.log(response);
        this.message = `Delete of Product ${id} Successful!`;
        this.refreshProducts();
      }
    )
  }

  updateProduct(id) {
    console.log(`update ${id}`)
    this.router.navigate(['products',id])
  }

  addProduct() {
    this.router.navigate(['products',-1])
  }
}
