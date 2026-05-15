import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import {
  AddCartItemRequest,
  CartItem,
  CartSummary,
  CheckoutRequest,
  InsuranceOrder,
  InsuranceProduct,
  ProductType
} from '../models/insurance.models';

@Injectable({ providedIn: 'root' })
export class InsuranceApiService {
  private readonly apiUrl = environment.apiUrl;

  constructor(private readonly http: HttpClient) {}

  products(type?: ProductType): Observable<InsuranceProduct[]> {
    const params = type ? new HttpParams().set('type', type) : undefined;
    return this.http.get<InsuranceProduct[]>(`${this.apiUrl}/products`, { params });
  }

  cart(): Observable<CartSummary> {
    return this.http.get<CartSummary>(`${this.apiUrl}/cart`);
  }

  addToCart(request: AddCartItemRequest): Observable<CartItem> {
    return this.http.post<CartItem>(`${this.apiUrl}/cart/items`, request);
  }

  updateCartItem(id: number, quantity: number): Observable<CartItem> {
    return this.http.put<CartItem>(`${this.apiUrl}/cart/items/${id}`, { quantity });
  }

  removeCartItem(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/cart/items/${id}`);
  }

  clearCart(): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/cart`);
  }

  checkout(request: CheckoutRequest): Observable<InsuranceOrder> {
    return this.http.post<InsuranceOrder>(`${this.apiUrl}/orders/checkout`, request);
  }

  orders(): Observable<InsuranceOrder[]> {
    return this.http.get<InsuranceOrder[]>(`${this.apiUrl}/orders`);
  }
}
