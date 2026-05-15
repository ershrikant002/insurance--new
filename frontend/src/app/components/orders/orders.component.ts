import { CommonModule, CurrencyPipe, DatePipe } from '@angular/common';
import { Component, Input, OnChanges, SimpleChanges } from '@angular/core';
import { InsuranceOrder } from '../../models/insurance.models';
import { InsuranceApiService } from '../../services/insurance-api.service';

@Component({
  selector: 'app-orders',
  standalone: true,
  imports: [CommonModule, CurrencyPipe, DatePipe],
  templateUrl: './orders.component.html',
  styleUrl: './orders.component.css'
})
export class OrdersComponent implements OnChanges {
  @Input() refreshToken = 0;
  orders: InsuranceOrder[] = [];

  constructor(private readonly api: InsuranceApiService) {}

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['refreshToken']) {
      this.load();
    }
  }

  load(): void {
    this.api.orders().subscribe((orders) => this.orders = orders);
  }
}
