export type ProductType = 'HEALTH' | 'MOTOR' | 'TRAVEL';
export type OrderStatus = 'CREATED' | 'CONFIRMED' | 'CANCELLED';

export interface InsuranceProduct {
  id: number;
  name: string;
  type: ProductType;
  description: string;
  provider: string;
  premium: number;
  coverageAmount: number;
  durationMonths: number;
  badge: string;
}

export interface CartItem {
  id: number;
  product: InsuranceProduct;
  quantity: number;
  insuredName: string;
  insuredEmail: string;
  note?: string;
}

export interface CartSummary {
  items: CartItem[];
  totalPremium: number;
}

export interface AddCartItemRequest {
  productId: number;
  quantity: number;
  insuredName: string;
  insuredEmail: string;
  note?: string;
}

export interface CheckoutRequest {
  customerName: string;
  customerEmail: string;
}

export interface OrderItem {
  id: number;
  productId: number;
  productName: string;
  productType: ProductType;
  quantity: number;
  unitPremium: number;
  lineTotal: number;
  insuredName: string;
  insuredEmail: string;
}

export interface InsuranceOrder {
  id: number;
  customerName: string;
  customerEmail: string;
  orderedAt: string;
  totalPremium: number;
  status: OrderStatus;
  items: OrderItem[];
}
