import { Component, OnInit } from '@angular/core';
import { ShoppingService } from '../shopping.service';
import { Shopping } from '../shopping';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-update-shopping',
  templateUrl: './update-shopping.component.html',
  styleUrls: ['./update-shopping.component.css']
})
export class UpdateShoppingComponent implements OnInit {
 
  id?: any;
  shopping: Shopping = new Shopping();
  constructor(private shoppingService: ShoppingService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];

    this.shoppingService.getShoppingById(this.id).subscribe(data => {
      this.shopping = data;
    }, error => console.log(error));
  }
 
  onSubmit(){
    this.shoppingService.updateShopping(this.id, this.shopping).subscribe( data =>{
      this.goToShoppingList();
    }
    , error => console.log(error));
  }

  goToShoppingList(){
    this.router.navigate(['/shoppings']);
  }
}
