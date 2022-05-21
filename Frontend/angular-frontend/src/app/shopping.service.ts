import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';
import { Shopping } from './shopping';

@Injectable({
  providedIn: 'root'
})
export class ShoppingService {

  private baseURL = "http://localhost:8080/shoppings";

  constructor(private httpClient: HttpClient) { }
  
  getShoppingsList(): Observable<Shopping[]>{
    return this.httpClient.get<Shopping[]>(`${this.baseURL}`);
  }

  createShopping(shopping: Shopping): Observable<Object>{
    return this.httpClient.post(`${this.baseURL}`, shopping);
  }

  getShoppingById(id: number): Observable<Shopping>{
    return this.httpClient.get<Shopping>(`${this.baseURL}/${id}`);
  }

  updateShopping(id: number, shopping: Shopping): Observable<Object>{
    return this.httpClient.put(`${this.baseURL}/${id}`, shopping);
  }

  deleteShopping(id: number): Observable<Object>{
    return this.httpClient.delete(`${this.baseURL}/${id}`);
  }

  buy(id: number): Observable<Object>{
    return this.httpClient.post(`${this.baseURL}/${id}/buy`, id);
  }



}
