import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ProductListComponent } from './product-list/product-list.component';
import { CreateProductComponent } from './create-product/create-product.component';
import { UpdateProductComponent } from './update-product/update-product.component';
import { UserListComponent } from './user-list/user-list.component';
import { CreateUserComponent } from './create-user/create-user.component';
import { UpdateUserComponent } from './update-user/update-user.component';
import { ShoppingListComponent } from './shopping-list/shopping-list.component';
import { UpdateShoppingComponent } from './update-shopping/update-shopping.component';
import { CreateShoppingComponent } from './create-shopping/create-shopping.component';
import { ItemListComponent } from './item-list/item-list.component';

const routes: Routes = [
	{path: '', redirectTo: 'products', pathMatch: 'full'},
	{path: 'products', component: ProductListComponent},
	{path: 'create-product', component: CreateProductComponent},
	{path: 'update-product/:id', component: UpdateProductComponent},
	{path: 'users', component: UserListComponent},
	{path: 'create-user', component: CreateUserComponent},
 	{path: 'update-user/:id', component: UpdateUserComponent},
	{path: 'shoppings', component: ShoppingListComponent},
	{path: 'update-shopping/:id', component: UpdateShoppingComponent},
  	{path: 'startshopping', component: CreateShoppingComponent},
	{path: 'buy/:id', component: ItemListComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
