import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CartComponent } from '../cart/cart.component';
import { OrdersComponent } from '../orders/orders.component';
import { ProductListComponent } from '../product-list/product-list.component';
import { ProductType } from '../../models/insurance.models';

@Component({
  selector: 'app-shell',
  standalone: true,
  imports: [CommonModule, CartComponent, OrdersComponent, ProductListComponent],
  templateUrl: './shell.component.html',
  styleUrl: './shell.component.css'
})
export class ShellComponent {
  selectedType: ProductType = 'HEALTH';
  readonly types: ProductType[] = ['HEALTH', 'MOTOR', 'TRAVEL'];
  cartRefreshToken = 1;
  orderRefreshToken = 1;

  selectType(type: ProductType): void {
    this.selectedType = type;
  }

  refreshCart(): void {
    this.cartRefreshToken++;
  }

  refreshOrders(): void {
    this.orderRefreshToken++;
  }
}
