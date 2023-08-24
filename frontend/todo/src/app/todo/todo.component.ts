import { ActivatedRoute, Router } from '@angular/router';
import { ProductDataService } from './../service/data/todo-data.service';
import { Component, OnInit } from '@angular/core';
import { Product } from '../list-todos/list-todos.component';

@Component({
  selector: 'app-todo',
  templateUrl: './todo.component.html',
  styleUrls: ['./todo.component.css']
})
export class ProductComponent implements OnInit {

  id:number
  product: Product

  constructor(
    private productService: ProductDataService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit() {
    
    this.id = this.route.snapshot.params['id'];
    
    this.product = new Product(this.id,'',2098,78);
    
    if(this.id!=-1) {
      this.productService.retrieveProduct('kiran', this.id)
          .subscribe (
            data => this.product = data
          )
    }
  }

  saveProduct() {
    console.log("Inside Save Product !");
    if(this.id == -1) { //=== ==
      this.productService.createProduct('kiran', this.product)
          .subscribe (
            data => {
              console.log(data)
              this.router.navigate(['products'])
            }
          )
    } else {
      this.productService.updateProduct('kiran', this.id, this.product)
          .subscribe (
            data => {
              console.log(data)
              this.router.navigate(['products'])
            }
          )
    }
  }

}
