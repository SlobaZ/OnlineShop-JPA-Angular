import { Component, OnInit } from '@angular/core';
import { Shopping } from '../shopping'
import { ShoppingService } from '../shopping.service'
import { Router } from '@angular/router';
@Component({
  selector: 'app-shopping-list',
  templateUrl: './shopping-list.component.html',
  styleUrls: ['./shopping-list.component.css']
})
export class ShoppingListComponent implements OnInit {

  shoppings?: Shopping[]; 

  constructor(private shoppingService: ShoppingService,
    private router: Router) { }

  ngOnInit(): void {
    this.getShoppings();
  }

  private getShoppings(){
    this.shoppingService.getShoppingsList().subscribe(data => {
      this.shoppings = data;
    });
  }

  addShopping(){
	this.router.navigate(['create-shopping']);
	}

  updateShopping(id: number){
    this.router.navigate(['update-shopping', id]);
  }

  deleteShopping(id: number){
    this.shoppingService.deleteShopping(id).subscribe( data => {
      console.log(data);
      this.getShoppings();
    })
  }
}
