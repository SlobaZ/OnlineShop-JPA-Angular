import { Component, OnInit } from '@angular/core';
import { Shopping } from '../shopping';
import { ShoppingService } from '../shopping.service';
import { User } from '../user';
import { UserService } from '../user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-shopping',
  templateUrl: './create-shopping.component.html',
  styleUrls: ['./create-shopping.component.css']
})
export class CreateShoppingComponent implements OnInit {

  shopping: Shopping = new Shopping();
  users?: User[]; 
  shoppingId?: any;
	
  constructor(private shoppingService: ShoppingService, private userService: UserService,
    private router: Router) { }

  ngOnInit(): void {
	this.getUsers();
  }


	private getUsers(){
    this.userService.getUsersList().subscribe(data => {
      this.users = data;
    });
  }

  saveShopping(){
    this.shoppingService.createShopping(this.shopping).subscribe( data =>{
      console.log(data);
      this.shopping = data;
      this.goToItemList(this.shopping.id);
    },
    error => console.log(error));
  }

  goToItemList(id: number){
    this.router.navigate(['buy', id]);
  }
  
  onSubmit(){
    console.log(this.shopping);
    this.saveShopping();
  }
}
