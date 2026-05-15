import { CommonModule, CurrencyPipe } from '@angular/common';
import { Component, EventEmitter, Input, OnChanges, Output, SimpleChanges } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CartSummary } from '../../models/insurance.models';
import { InsuranceApiService } from '../../services/insurance-api.service';

@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [CommonModule, CurrencyPipe, FormsModule],
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.css'
})
export class CartComponent implements OnChanges {
  @Input() refreshToken = 0;
  @Output() checkedOut = new EventEmitter<void>();

  cart: CartSummary = { items: [], totalPremium: 0 };
  checkout = {
    customerName: 'Demo Customer',
    customerEmail: 'demo@example.com'
  };
  message = '';

  constructor(private readonly api: InsuranceApiService) {}

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['refreshToken']) {
      this.load();
    }
  }

  load(): void {
    this.api.cart().subscribe((cart) => this.cart = cart);
  }

  update(id: number, quantity: number): void {
    this.api.updateCartItem(id, quantity).subscribe(() => this.load());
  }

  remove(id: number): void {
    this.api.removeCartItem(id).subscribe(() => this.load());
  }

  placeOrder(): void {
    this.message = '';
    this.api.checkout(this.checkout).subscribe({
      next: (order) => {
        this.message = `Order #${order.id} confirmed`;
        this.load();
        this.checkedOut.emit();
      },
      error: (error) => this.message = error?.error?.message ?? 'Checkout failed'
    });
  }
}
