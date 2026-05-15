import { CommonModule, CurrencyPipe } from '@angular/common';
import { Component, EventEmitter, Input, OnChanges, Output, SimpleChanges } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { InsuranceProduct, ProductType } from '../../models/insurance.models';
import { InsuranceApiService } from '../../services/insurance-api.service';

@Component({
  selector: 'app-product-list',
  standalone: true,
  imports: [CommonModule, CurrencyPipe, FormsModule],
  templateUrl: './product-list.component.html',
  styleUrl: './product-list.component.css'
})
export class ProductListComponent implements OnChanges {
  @Input({ required: true }) selectedType!: ProductType;
  @Output() cartChanged = new EventEmitter<void>();

  products: InsuranceProduct[] = [];
  loading = false;
  error = '';
  customer = {
    insuredName: 'Demo Customer',
    insuredEmail: 'demo@example.com'
  };

  constructor(private readonly api: InsuranceApiService) {}

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['selectedType']) {
      this.loadProducts();
    }
  }

  loadProducts(): void {
    this.loading = true;
    this.error = '';
    this.api.products(this.selectedType).subscribe({
      next: (products) => {
        this.products = products;
        this.loading = false;
      },
      error: () => {
        this.error = 'Unable to load insurance products.';
        this.loading = false;
      }
    });
  }

  add(product: InsuranceProduct): void {
    this.api.addToCart({
      productId: product.id,
      quantity: 1,
      insuredName: this.customer.insuredName,
      insuredEmail: this.customer.insuredEmail,
      note: `${product.type.toLowerCase()} cover request`
    }).subscribe({
      next: () => this.cartChanged.emit(),
      error: () => this.error = 'Unable to add policy to cart.'
    });
  }
}
