# Angular Shopping Store - Complete Code Extraction

## Project Structure
```
shopping-store/
├── src/
│   ├── app/
│   │   ├── data/
│   │   │   └── products.data.ts
│   │   ├── layout/
│   │   │   ├── footer/
│   │   │   │   ├── footer.component.ts
│   │   │   │   ├── footer.component.html
│   │   │   │   └── footer.component.css
│   │   │   └── top-bar/
│   │   │       ├── top-bar.component.ts
│   │   │       ├── top-bar.component.html
│   │   │       └── top-bar.component.css
│   │   ├── pages/
│   │   │   ├── home/
│   │   │   │   ├── components/
│   │   │   │   │   ├── btn-continue/
│   │   │   │   │   │   ├── btn-continue.component.ts
│   │   │   │   │   │   ├── btn-continue.component.html
│   │   │   │   │   │   └── btn-continue.component.css
│   │   │   │   │   ├── deals/
│   │   │   │   │   │   ├── deals.component.ts
│   │   │   │   │   │   ├── deals.component.html
│   │   │   │   │   │   └── deals.component.css
│   │   │   │   │   ├── product-card/
│   │   │   │   │   │   ├── product-card.component.ts
│   │   │   │   │   │   ├── product-card.component.html
│   │   │   │   │   │   └── product-card.component.css
│   │   │   │   │   └── products/
│   │   │   │   │       ├── products.component.ts
│   │   │   │   │       ├── products.component.html
│   │   │   │   │       └── products.component.css
│   │   │   │   ├── home.component.ts
│   │   │   │   ├── home.component.html
│   │   │   │   └── home.component.css
│   │   │   ├── cart/
│   │   │   │   ├── components/
│   │   │   │   │   ├── cart-item-card/
│   │   │   │   │   │   ├── cart-item-card.component.ts
│   │   │   │   │   │   ├── cart-item-card.component.html
│   │   │   │   │   │   └── cart-item-card.component.css
│   │   │   │   │   └── quantity-stepper/
│   │   │   │   │       ├── quantity-stepper.component.ts
│   │   │   │   │       ├── quantity-stepper.component.html
│   │   │   │   │       └── quantity-stepper.component.css
│   │   │   │   ├── cart.component.ts
│   │   │   │   ├── cart.component.html
│   │   │   │   └── cart.component.css
│   │   │   ├── success/
│   │   │   │   ├── success.component.ts
│   │   │   │   ├── success.component.html
│   │   │   │   └── success.component.css
│   │   │   └── cancel/
│   │   │       ├── cancel.component.ts
│   │   │       ├── cancel.component.html
│   │   │       └── cancel.component.css
│   │   ├── pipes/
│   │   │   └── truncate.pipe.ts
│   │   ├── services/
│   │   │   └── cart.service.ts
│   │   ├── app.component.ts
│   │   ├── app.component.html
│   │   ├── app.component.css
│   │   ├── app.routes.ts
│   │   └── app.config.ts
│   ├── assets/
│   │   ├── icons/
│   │   └── images/
│   ├── environments/
│   │   └── environment.ts
│   ├── index.html
│   └── styles.css
```

---

## Complete Code Files

### 1. **Environment Files**

#### `src/environments/environment.ts`
```typescript
export const environment = {
  STRIPE_PK: 'pk_1234567890ABCDEFGHIJKLMNOP...',
};
```

---

### 2. **Data Layer**

#### `src/app/data/products.data.ts`
```typescript
export interface Product {
  id: string;
  name: string;
  price: number;
  thumbUrl: string;
  imageUrl: string;
  description: string;
}

export const PRODUCTS: Product[] = [
  {
    id: '8347836c-35db-4307-ac5d-cbdf19a9a50c',
    name: 'Black+Decker Helix Performance Premium Hand Mixer, 5-Speed Mixer, Red, MX600R',
    description: '',
    price: 102,
    thumbUrl: 'assets/images/products/thumbs/8347836c-35db-4307-ac5d-cbdf19a9a50c.jpg',
    imageUrl: 'assets/images/products/8347836c-35db-4307-ac5d-cbdf19a9a50c.jpg',
  },
  {
    id: 'f8c2cc34-ddb7-46ec-96ee-e27d1765df10',
    name: 'KitchenAid Pro Line 5 Speed Hand Mixer, Aqua Sky',
    description: '',
    price: 115,
    thumbUrl: 'assets/images/products/thumbs/f8c2cc34-ddb7-46ec-96ee-e27d1765df10.jpg',
    imageUrl: 'assets/images/products/f8c2cc34-ddb7-46ec-96ee-e27d1765df10.jpg',
  },
  {
    id: '2927b969-1c97-4a36-b4ab-a0777d8690e2',
    name: 'KitchenAid 5-Speed Ultra Power Hand Mixer',
    description: '',
    price: 114,
    thumbUrl: 'assets/images/products/thumbs/2927b969-1c97-4a36-b4ab-a0777d8690e2.jpg',
    imageUrl: 'assets/images/products/2927b969-1c97-4a36-b4ab-a0777d8690e2.jpg',
  },
  {
    id: '57ba7441-8bea-4340-9f49-dc369d108cf7',
    name: 'KitchenAid 5-Speed Hand Mixer',
    description: '',
    price: 105,
    thumbUrl: 'assets/images/products/thumbs/57ba7441-8bea-4340-9f49-dc369d108cf7.jpg',
    imageUrl: 'assets/images/products/57ba7441-8bea-4340-9f49-dc369d108cf7.jpg',
  },
  {
    id: '552809ab-7ea3-4e48-be23-3c7c53219284',
    name: 'Cuisinart Power 5-Speed 220-Watt Hand Mixer, White',
    description: '',
    price: 119,
    thumbUrl: 'assets/images/products/thumbs/552809ab-7ea3-4e48-be23-3c7c53219284.jpg',
    imageUrl: 'assets/images/products/552809ab-7ea3-4e48-be23-3c7c53219284.jpg',
  },
  {
    id: '893deaf7-5224-4702-9ba6-4fc4c52bed22',
    name: 'KitchenAid 5 Speed Ultra Power Hand Mixer',
    description: '',
    price: 109.99,
    thumbUrl: 'assets/images/products/thumbs/893deaf7-5224-4702-9ba6-4fc4c52bed22.jpg',
    imageUrl: 'assets/images/products/893deaf7-5224-4702-9ba6-4fc4c52bed22.jpg',
  },
  {
    id: '26917fd7-61c3-41d7-b7cd-3c823bae77d2',
    name: 'KitchenAid 5-Speed Hand Mixer, Empire Red',
    description: '',
    price: 120,
    thumbUrl: 'assets/images/products/thumbs/26917fd7-61c3-41d7-b7cd-3c823bae77d2.jpg',
    imageUrl: 'assets/images/products/26917fd7-61c3-41d7-b7cd-3c823bae77d2.jpg',
  },
  {
    id: '30e575f5-8997-4c34-aa44-88af74452db2',
    name: 'Cuisinart 5-Speed Hand Mixer, Black',
    description: '',
    price: 110,
    thumbUrl: 'assets/images/products/thumbs/30e575f5-8997-4c34-aa44-88af74452db2.jpg',
    imageUrl: 'assets/images/products/30e575f5-8997-4c34-aa44-88af74452db2.jpg',
  },
];
```

---

### 3. **Pipes**

#### `src/app/pipes/truncate.pipe.ts`
```typescript
import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'truncate',
  standalone: true,
})
export class TruncatePipe implements PipeTransform {
  transform(value: string): string {
    const limit = 40;
    const trail = '...';
    return value.length > limit ? value.substring(0, limit) + trail : value;
  }
}
```

---

### 4. **Services**

#### `src/app/services/cart.service.ts`
```typescript
import { Injectable, signal } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class CartService {
  cart = signal<Cart>({
    items: [],
    count: 0,
    total: 0,
  });

  constructor() {}

  addItem(item: CartItem) {
    const itemObj = this.cart().items.find((t) => t.id === item.id);
    if (itemObj) {
      this.increaseItem(itemObj);
    } else {
      this.cart.update((prevCart) => ({
        ...prevCart,
        items: [...prevCart.items, item],
        count: prevCart.count + 1,
        total: prevCart.total + item.price,
      }));
    }
  }

  increaseItem(item: CartItem) {
    this.cart.update((prevCart) => {
      const newCart = {
        ...prevCart,
        items: [...prevCart.items],
      };
      const itemObj = newCart.items.find((t) => t.id === item.id);
      itemObj!.quantity = itemObj!.quantity + 1;
      newCart.count++;
      newCart.total += itemObj!.price;
      return newCart;
    });
  }

  decreaseItem(item: CartItem) {
    this.cart.update((prevCart) => {
      const newCart = {
        ...prevCart,
        items: [...prevCart.items],
      };
      const itemObj = newCart.items.find((t) => t.id === item.id);
      itemObj!.quantity = itemObj!.quantity - 1;
      newCart.count--;
      newCart.total -= itemObj!.price;
      return newCart;
    });
  }

  removeItem(item: CartItem) {
    this.cart.update((prevCart) => {
      const newCart = {
        ...prevCart,
        items: [...prevCart.items.filter((t) => t.id !== item.id)],
      };
      const itemObj = prevCart.items.find((t) => t.id === item.id);
      newCart.count -= itemObj!.quantity;
      newCart.total -= itemObj!.price * itemObj!.quantity;
      return newCart;
    });
  }
}

export interface CartItem {
  id: string;
  name: string;
  imageUrl: string;
  price: number;
  quantity: number;
}

export interface Cart {
  items: CartItem[];
  count: number;
  total: number;
}
```

---

### 5. **Layout Components**

#### `src/app/layout/top-bar/top-bar.component.ts`
```typescript
import { DecimalPipe } from '@angular/common';
import { Component, computed } from '@angular/core';
import { RouterLink } from '@angular/router';
import { CartService } from '../../services/cart.service';

@Component({
  selector: 'app-top-bar',
  standalone: true,
  imports: [DecimalPipe, RouterLink],
  templateUrl: './top-bar.component.html',
  styleUrl: './top-bar.component.css',
})
export class TopBarComponent {
  total = computed(() => this.cartService.cart().total);
  count = computed(() => this.cartService.cart().count);

  constructor(private cartService: CartService) {}
}
```

#### `src/app/layout/top-bar/top-bar.component.html`
```html
<div class="wrapper">
  <div class="brand-group" routerLink="/">
    <img src="assets/icons/menu.png" alt="" class="menu link" />
    <img src="assets/images/logo.png" alt="" class="logo link" />
    <div class="brand-name link">Shopping Store</div>
  </div>
  <div class="search-group">
    <input type="search" class="search-input" placeholder="Search" />
    <div class="search-icon-wrapper link">
      <img src="assets/icons/search.png" alt="" class="search-icon" />
    </div>
  </div>
  <div class="user-group">
    <img src="assets/icons/user.png" alt="" class="user-icon link" />
    <div class="cart" routerLink="/cart">
      <img src="assets/icons/cart.png" alt="" class="cart-icon link" />
      <span class="cart-count">{{ count() }}</span>
      <span class="cart-total">${{ total() | number : "1.2-2" }}</span>
    </div>
  </div>
</div>
<div class="mobile-wrapper">
  <div class="search-group mobile">
    <input type="search" class="search-input" placeholder="Search" />
    <div class="search-icon-wrapper link">
      <img src="assets/icons/search.png" alt="" class="search-icon" />
    </div>
  </div>
</div>
```

#### `src/app/layout/top-bar/top-bar.component.css`
```css
.wrapper,
.mobile-wrapper {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  height: 80px;
  padding: 10px 20px;
  background-color: var(--main-color);
  color: var(--text-color);
  position: fixed;
  z-index: 100;
  top: 0px;
  left: 0px;
}
@media (min-width: 760px) {
  .wrapper,
  .mobile-wrapper {
    padding: 10px 30px;
  }
}
.mobile-wrapper {
  top: 80px;
  display: flex;
}
@media (min-width: 760px) {
  .mobile-wrapper {
    display: none;
  }
}
.brand-group {
  height: 100%;
  display: flex;
  align-items: center;
  gap: 10px;
}
@media (min-width: 760px) {
  .brand-group {
    gap: 20px;
  }
}
.menu {
  width: 20px;
  height: 100%;
  object-fit: contain;
  cursor: pointer;
  margin-right: 10px;
}
@media (min-width: 760px) {
  .menu {
    margin-right: 20px;
  }
}
.logo {
  width: 42px;
  height: 100%;
  object-fit: contain;
  cursor: pointer;
}
.brand-name {
  text-wrap: nowrap;
  cursor: pointer;
  margin-left: 4px;
  padding: 30px 0px;
}
.user-group {
  display: flex;
  align-items: center;
  height: 100%;
  padding-right: 10px;
  gap: 20px;
}
@media (min-width: 760px) {
  .user-group {
    gap: 30px;
    padding-right: 20px;
  }
}
.user-icon,
.cart-icon {
  width: 24px;
  height: 100%;
  object-fit: contain;
  cursor: pointer;
}
.cart {
  position: relative;
  cursor: pointer;
}
.cart-count {
  background-color: var(--accent-color);
  font-size: 0.9em;
  width: 20px;
  height: 20px;
  border-radius: 50%;
  display: flex;
  text-align: center;
  justify-content: center;
  align-items: center;
  position: absolute;
  top: -4px;
  right: -8px;
}
.cart-total {
  font-size: 0.8em;
  position: absolute;
  bottom: -12px;
  left: 0px;
}
.search-group {
  height: 100%;
  display: none;
  align-items: center;
}
@media (min-width: 760px) {
  .search-group {
    display: flex;
    margin-left: -40px;
  }
}
.search-input {
  width: 100%;
  height: var(--search-input-height);
  border-radius: 30px;
  outline: none;
  border: none;
  padding-left: 30px;
  padding-right: 60px;
}
@media (min-width: 760px) {
  .search-input {
    width: 600px;
  }
}
.search-icon-wrapper {
  background-color: var(--accent-color);
  border-top-right-radius: 30px;
  border-bottom-right-radius: 30px;
  height: var(--search-input-height);
  width: calc(var(--search-input-height) + 10px);
  margin-left: calc(-1 * var(--search-input-height) + 10px);
}
.search-icon {
  width: 100%;
  height: 100%;
  object-fit: contain;
  padding: 11px;
  cursor: pointer;
}
.mobile {
  display: flex;
  width: 100%;
  padding-top: 2px;
}
@media (min-width: 760px) {
  .mobile {
    display: none;
  }
}
```

#### `src/app/layout/footer/footer.component.ts`
```typescript
import { Component } from '@angular/core';

@Component({
  selector: 'app-footer',
  standalone: true,
  imports: [],
  templateUrl: './footer.component.html',
  styleUrl: './footer.component.css',
})
export class FooterComponent {
  year = new Date().getFullYear();
}
```

#### `src/app/layout/footer/footer.component.html`
```html
<footer class="wrapper">
  <div>About Shopping Store</div>
  <div>Help</div>
  <div>Privacy Notice</div>
  <div>Customer Service</div>
  <div>Terms of Use</div>
</footer>
<footer class="wrapper copyright">
  <p>© {{ year }} Shopping Store. All Rights Reserved.</p>
</footer>
```

#### `src/app/layout/footer/footer.component.css`
```css
.wrapper {
  display: flex;
  width: 100%;
  justify-content: flex-start;
  align-items: center;
  flex-direction: column;
  background-color: var(--footer-color);
  color: var(--text-color);
  padding: 50px 10px;
  gap: 20px;
  font-size: 0.9em;
  cursor: default;
}
.copyright {
  background-color: var(--main-color);
}
```

---

### 6. **Home Page Components**

#### `src/app/pages/home/home.component.ts`
```typescript
import { Component } from '@angular/core';
import { DealsComponent } from './components/deals/deals.component';
import { ProductsComponent } from './components/products/products.component';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [DealsComponent, ProductsComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css',
})
export class HomeComponent {}
```

#### `src/app/pages/home/home.component.html`
```html
<app-deals></app-deals>
<app-products></app-products>
```

#### `src/app/pages/home/home.component.css`
```css
/* Empty styles */
```

---

#### `src/app/pages/home/components/deals/deals.component.ts`
```typescript
import { Component } from '@angular/core';

@Component({
  selector: 'app-deals',
  standalone: true,
  imports: [],
  templateUrl: './deals.component.html',
  styleUrl: './deals.component.css',
})
export class DealsComponent {}
```

#### `src/app/pages/home/components/deals/deals.component.html`
```html
<picture>
  <source
    class="image"
    srcset="assets/images/deals/deal-3-large.jpg"
    media="(min-width: 760px)"
  />
  <img class="image" src="assets/images/deals/deal-3.jpg" alt="" />
</picture>
```

#### `src/app/pages/home/components/deals/deals.component.css`
```css
.image {
  width: 100%;
  height: 100%;
  object-fit: contain;
}
```

---

#### `src/app/pages/home/components/products/products.component.ts`
```typescript
import { Component } from '@angular/core';
import { PRODUCTS } from '../../../../data/products.data';
import { ProductCardComponent } from '../product-card/product-card.component';
import { CartService } from '../../../../services/cart.service';

@Component({
  selector: 'app-products',
  standalone: true,
  imports: [ProductCardComponent],
  templateUrl: './products.component.html',
  styleUrl: './products.component.css',
})
export class ProductsComponent {
  products = PRODUCTS;

  constructor(private cartService: CartService) {}

  onAdd(product: any) {
    this.cartService.addItem({
      id: product.id,
      name: product.name,
      imageUrl: product.imageUrl,
      price: product.price,
      quantity: 1,
    });
  }
}
```

#### `src/app/pages/home/components/products/products.component.html`
```html
<div class="wrapper">
  @for (product of products; track product.id) {
    <app-product-card
      (add)="onAdd($event)"
      [product]="product"
    ></app-product-card>
  }
</div>
```

#### `src/app/pages/home/components/products/products.component.css`
```css
.wrapper {
  width: 100%;
  display: grid;
  max-height: fit-content;
  grid-template-columns: auto;
  row-gap: 70px;
}
@media (min-width: 760px) {
  .wrapper {
    grid-template-columns: repeat(6, auto);
    row-gap: 10px;
  }
}
app-product-card {
  margin: auto;
}
```

---

#### `src/app/pages/home/components/product-card/product-card.component.ts`
```typescript
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Product } from '../../../../data/products.data';
import { TruncatePipe } from '../../../../pipes/truncate.pipe';

@Component({
  selector: 'app-product-card',
  standalone: true,
  imports: [TruncatePipe],
  templateUrl: './product-card.component.html',
  styleUrl: './product-card.component.css',
})
export class ProductCardComponent {
  @Input() product!: Product;
  @Output() add = new EventEmitter<Product>();

  onAdd() {
    this.add.next(this.product);
  }
}
```

#### `src/app/pages/home/components/product-card/product-card.component.html`
```html
@if (product) {
  <div class="wrapper">
    <img class="image" [src]="product.thumbUrl" [alt]="product.name" />
    <div class="price">${{ product.price }}</div>
    <div class="name">{{ product.name | truncate }}</div>
    <button class="button link" (click)="onAdd()"><span>+</span> Add</button>
  </div>
}
```

#### `src/app/pages/home/components/product-card/product-card.component.css`
```css
.wrapper {
  background-color: #ffffff;
  height: 500px;
  width: 100%;
  max-width: 600px;
  padding: 20px;
  padding-top: 80px;
  display: grid;
  grid-template-rows: 300px 40px 60px 40px;
  border-top: 1px solid rgba(128, 128, 128, 0.5);
}
@media (min-width: 760px) {
  .wrapper {
    border-top: none;
    padding-top: 20px;
  }
}
.image {
  width: 100%;
  height: 100%;
  object-fit: contain;
  cursor: pointer;
}
.price {
  font-weight: 700;
  font-size: 1.2em;
  cursor: pointer;
}
.name {
  width: 100%;
  cursor: pointer;
  font-size: 0.9em;
  line-height: 1.6em;
}
.button {
  outline: none;
  border: 2px solid gray;
  line-height: 1.1em;
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 10px;
  width: 80px;
  height: 36px;
  border-radius: 30px;
  cursor: pointer;
  background-color: #ffffff;
  font-weight: 500;
  user-select: none;
}
.button span {
  font-size: 1.8em;
  line-height: 1.1em;
}
.button:hover {
  border: 3px solid rgba(0, 0, 0, 0.8);
}
```

---

#### `src/app/pages/home/components/btn-continue/btn-continue.component.ts`
```typescript
import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-btn-continue',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './btn-continue.component.html',
  styleUrl: './btn-continue.component.css',
})
export class BtnContinueComponent {}
```

#### `src/app/pages/home/components/btn-continue/btn-continue.component.html`
```html
<div class="btn" routerLink="/">Continue Shopping</div>
```

#### `src/app/pages/home/components/btn-continue/btn-continue.component.css`
```css
.btn {
  margin-top: 50px;
  width: 100%;
  color: #ffffff;
  background-color: var(--accent-color);
  height: 36px;
  border-radius: 30px;
  cursor: pointer;
  outline: none;
  border: 1px solid gray;
  padding: 0px 20px;
  display: flex;
  justify-content: center;
  align-items: center;
}
.btn:hover {
  filter: brightness(1.1);
}
```

---

### 7. **Cart Page Components**

#### `src/app/pages/cart/cart.component.ts`
```typescript
import { Component, computed } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { loadStripe } from '@stripe/stripe-js';
import { CartService } from '../../services/cart.service';
import { CartItemCardComponent } from './components/cart-item-card/cart-item-card.component';
import { environment } from '../../../environments/environment';

@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [CartItemCardComponent],
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.css',
})
export class CartComponent {
  count = computed(() => this.cartService.cart().count);
  total = computed(() => this.cartService.cart().total);
  items = computed(() => this.cartService.cart().items);

  constructor(
    private cartService: CartService,
    private http: HttpClient
  ) {}

  onItemQuantityUpdate(quantity: number, id: string) {
    let increase = true;
    const item = this.items().find((t) => t.id === id);
    if (quantity < item!.quantity) increase = false;
    if (increase) {
      this.cartService.increaseItem(item!);
    } else {
      this.cartService.decreaseItem(item!);
    }
  }

  onRemoveItem(id: string) {
    const item = this.items().find((t) => t.id === id);
    this.cartService.removeItem(item!);
  }

  async onCheckout() {
    const stripe = await loadStripe(environment.STRIPE_PK);
    const body = this.cartService.cart().items;
    const headers = {
      'Content-Type': 'application/json',
    };

    this.http
      .post('http://localhost:8000/api/create-checkout-session', body, {
        headers: headers,
      })
      .subscribe({
        next: async (response) => {
          const session = response as any;
          const result = await stripe?.redirectToCheckout({
            sessionId: session.id,
          });
          if (result?.error) {
            console.log(result?.error);
          }
        },
        error: (response) => {
          if (response?.error) {
            console.log(response?.error);
          }
        },
      });
  }
}
```

#### `src/app/pages/cart/cart.component.html`
```html
<div class="wrapper">
  <header class="header">
    <strong>Cart </strong>({{
      count() === 1 ? count() + " item" : count() + " items"
    }})
  </header>

  @if (count()) {
    <main class="main">
      <div class="items shadow">
        <div class="title">
          <strong>
            {{ count() === 1 ? count() + " item" : count() + " items" }}
          </strong>
        </div>
        <div class="arrival">
          <strong>Arrives by Wed, Jan 24</strong>
        </div>
        <div class="content">
          @for (item of items(); track item.id) {
            <app-cart-item-card
              [item]="item"
              (itemQuantityUpdate)="onItemQuantityUpdate($event, item.id)"
              (removeItem)="onRemoveItem(item.id)"
            ></app-cart-item-card>
          }
        </div>
      </div>

      <aside class="total shadow">
        <button class="checkout" (click)="onCheckout()">
          <strong>Continue to Checkout</strong>
        </button>
        <div class="subtotal value-pair">
          <div>
            <strong>Subtotal</strong>({{
              count() === 1 ? count() + " item" : count() + " items"
            }})
          </div>
          <div>${{ total() }}</div>
        </div>
        <div class="taxes value-pair">
          <div><strong>Taxes</strong></div>
          <div>Calculated at checkout</div>
        </div>
        <div class="estimated-total value-pair">
          <div><strong>Estimated Total</strong></div>
          <div class="estimated-total-value">
            <strong>${{ total() }}</strong>
          </div>
        </div>
      </aside>
    </main>
  }
</div>

@if (!count()) {
  <div class="cart-empty">Cart is Empty!</div>
}
```

#### `src/app/pages/cart/cart.component.css`
```css
.wrapper {
  padding: 10px;
  padding-top: 40px;
}
@media (min-width: 760px) {
  .wrapper {
    padding: 0px 200px;
    padding-top: 40px;
  }
}
.cart-empty {
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  text-align: center;
  padding: 80px;
}
.header {
  font-size: 1.4em;
  cursor: default;
}
.main {
  width: 100%;
  display: grid;
  grid-template-columns: auto;
  gap: 20px;
  padding: 40px 0px;
  cursor: default;
}
@media (min-width: 760px) {
  .main {
    grid-template-columns: 65% 35%;
  }
}
.items {
  display: grid;
  grid-template-columns: auto;
  border: 1px solid rgba(128, 128, 128, 0.3);
  border-radius: 10px;
  padding: 40px;
}
.title {
  padding-bottom: 0px;
}
.arrival {
  font-size: 0.8em;
  padding-top: 30px;
  padding-bottom: 20px;
}
.content {
  display: grid;
  grid-template-columns: auto;
  row-gap: 40px;
}
.total {
  display: grid;
  grid-template-columns: auto;
  gap: 10px;
  border: 1px solid rgba(128, 128, 128, 0.3);
  border-radius: 10px;
  padding: 40px;
  height: fit-content;
}
.checkout {
  width: 100%;
  color: #ffffff;
  background-color: var(--accent-color);
  height: 36px;
  border-radius: 30px;
  cursor: pointer;
  outline: none;
  border: 1px solid gray;
}
.checkout:hover {
  filter: brightness(1.1);
}
.value-pair {
  display: flex;
  justify-content: space-between;
}
.subtotal,
.taxes,
.estimated-total {
  font-size: 0.9em;
  padding: 20px 0px;
}
.subtotal {
  padding-top: 40px;
}
.estimated-total-value {
  font-size: 1.2em;
}
```

---

#### `src/app/pages/cart/components/cart-item-card/cart-item-card.component.ts`
```typescript
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { CartItem } from '../../../../services/cart.service';
import { QuantityStepperComponent } from '../quantity-stepper/quantity-stepper.component';

@Component({
  selector: 'app-cart-item-card',
  standalone: true,
  imports: [QuantityStepperComponent],
  templateUrl: './cart-item-card.component.html',
  styleUrl: './cart-item-card.component.css',
})
export class CartItemCardComponent {
  @Input() item!: CartItem;
  @Output() itemQuantityUpdate = new EventEmitter<number>();
  @Output() removeItem = new EventEmitter<void>();

  onQuantityChange(quantity: number) {
    this.itemQuantityUpdate.next(quantity);
  }

  onRemoveItem() {
    this.removeItem.next();
  }
}
```

#### `src/app/pages/cart/components/cart-item-card/cart-item-card.component.html`
```html
@if (item) {
  <div class="container">
    <div class="wrapper">
      <img [src]="item.imageUrl" [alt]="item.name" class="image" />
      <div class="name">
        {{ item.name }}
        <div class="detail">
          Free 90-day returns<br />
          Gift Eligible<br />
        </div>
      </div>
      <div class="price">
        <strong>${{ item.price }}</strong>
      </div>
    </div>
    <section class="controls">
      <span class="remove" (click)="onRemoveItem()">Remove</span>
      <app-quantity-stepper
        [quantity]="item.quantity"
        (quantityChange)="onQuantityChange($event)"
      ></app-quantity-stepper>
    </section>
  </div>
}
```

#### `src/app/pages/cart/components/cart-item-card/cart-item-card.component.css`
```css
.container {
  position: relative;
  width: 100%;
  height: 100%;
  padding: 40px 0px;
  border-top: 1px solid rgba(128, 128, 128, 0.2);
}
.wrapper {
  width: 100%;
  display: grid;
  grid-template-columns: 100px auto 60px;
  place-items: center;
  cursor: default;
}
.image {
  width: 100px;
  height: 100%;
  object-fit: contain;
}
.name {
  line-height: 1.6em;
}
@media (min-width: 760px) {
  .name {
    padding-right: 80px;
  }
}
.detail {
  opacity: 0.8;
  font-size: 0.8em;
  padding-top: 10px;
  line-height: 1.6em;
  display: none;
}
@media (min-width: 760px) {
  .detail {
    display: block;
  }
}
.controls {
  display: flex;
  gap: 20px;
  align-items: center;
  position: absolute;
  right: 0px;
  padding-top: 20px;
}
@media (min-width: 760px) {
  .controls {
    padding-top: 0px;
  }
}
.remove {
  text-decoration: underline;
  transform: scale(0.9);
  font-weight: 300;
  cursor: pointer;
}
```

---

#### `src/app/pages/cart/components/quantity-stepper/quantity-stepper.component.ts`
```typescript
import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-quantity-stepper',
  standalone: true,
  imports: [],
  templateUrl: './quantity-stepper.component.html',
  styleUrl: './quantity-stepper.component.css',
})
export class QuantityStepperComponent {
  @Input() quantity: number = 1;
  @Output() quantityChange = new EventEmitter<number>();

  onIncreaseQuantity() {
    this.quantityChange.next(this.quantity + 1);
  }

  onDecreaseQuantity() {
    if (this.quantity > 1) this.quantityChange.next(this.quantity - 1);
  }
}
```

#### `src/app/pages/cart/components/quantity-stepper/quantity-stepper.component.html`
```html
<div class="wrapper">
  <div class="button" (click)="onDecreaseQuantity()">-</div>
  <div class="quantity">
    <strong>{{ quantity }}</strong>
  </div>
  <div class="button" (click)="onIncreaseQuantity()">+</div>
</div>
```

#### `src/app/pages/cart/components/quantity-stepper/quantity-stepper.component.css`
```css
.wrapper {
  width: 140px;
  height: 34px;
  display: grid;
  grid-template-columns: 25px 70px 25px;
  border: 1px solid rgba(128, 128, 128, 0.3);
  border-radius: 30px;
  place-content: center center;
  place-items: center center;
  padding: 0px 40px;
}
.button {
  width: 25px;
  font-weight: 200;
  font-size: 20px;
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
}
.button:hover {
  color: #ffffff;
  background-color: gray;
  border-radius: 50%;
}
```

---

### 8. **Success Page**

#### `src/app/pages/success/success.component.ts`
```typescript
import { Component } from '@angular/core';
import { BtnContinueComponent } from '../home/components/btn-continue/btn-continue.component';

@Component({
  selector: 'app-success',
  standalone: true,
  imports: [BtnContinueComponent],
  templateUrl: './success.component.html',
  styleUrl: './success.component.css',
})
export class SuccessComponent {}
```

#### `src/app/pages/success/success.component.html`
```html
<div class="wrapper">
  <img class="image" src="assets/icons/success.png" />
  <h1>Congratulations!</h1>
  <p class="text">Thank you! Your payment has been received.</p>
  <app-btn-continue></app-btn-continue>
</div>
```

#### `src/app/pages/success/success.component.css`
```css
.wrapper {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: 100px;
  gap: 20px;
  cursor: default;
  text-align: center;
}
.image {
  width: 100px;
  height: 100%;
  object-fit: contain;
}
.text {
  font-size: 1.2em;
}
```

---

### 9. **Cancel Page**

#### `src/app/pages/cancel/cancel.component.ts`
```typescript
import { Component } from '@angular/core';
import { BtnContinueComponent } from '../home/components/btn-continue/btn-continue.component';

@Component({
  selector: 'app-cancel',
  standalone: true,
  imports: [BtnContinueComponent],
  templateUrl: './cancel.component.html',
  styleUrl: './cancel.component.css',
})
export class CancelComponent {}
```

#### `src/app/pages/cancel/cancel.component.html`
```html
<div class="wrapper">
  <img class="image" src="assets/icons/cancel.png" />
  <h1>Cancelled!</h1>
  <p class="text">Your payment has been cancelled.</p>
  <app-btn-continue></app-btn-continue>
</div>
```

#### `src/app/pages/cancel/cancel.component.css`
```css
.wrapper {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: 100px;
  gap: 20px;
  cursor: default;
  text-align: center;
}
.image {
  width: 100px;
  height: 100%;
  object-fit: contain;
}
.text {
  font-size: 1.2em;
}
```

---

### 10. **App Component**

#### `src/app/app.component.ts`
```typescript
import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { TopBarComponent } from './layout/top-bar/top-bar.component';
import { FooterComponent } from './layout/footer/footer.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet, TopBarComponent, FooterComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {}
```

#### `src/app/app.component.html`
```html
<app-top-bar></app-top-bar>
<main class="main">
  <router-outlet></router-outlet>
</main>
<app-footer></app-footer>
```

#### `src/app/app.component.css`
```css
.main {
  height: 100%;
  padding: 160px 0;
}
@media (min-width: 760px) {
  .main {
    padding: 80px 0px;
  }
}
```

---

### 11. **Routing Configuration**

#### `src/app/app.routes.ts`
```typescript
import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { CartComponent } from './pages/cart/cart.component';
import { SuccessComponent } from './pages/success/success.component';
import { CancelComponent } from './pages/cancel/cancel.component';

export const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'cart', component: CartComponent },
  { path: 'success', component: SuccessComponent },
  { path: 'cancel', component: CancelComponent },
];
```

#### `src/app/app.config.ts`
```typescript
import { ApplicationConfig, importProvidersFrom } from '@angular/core';
import { provideRouter } from '@angular/router';
import { routes } from './app.routes';
import { HttpClientModule } from '@angular/common/http';

export const appConfig: ApplicationConfig = {
  providers: [provideRouter(routes), importProvidersFrom(HttpClientModule)],
};
```

---

### 12. **Global Styles**

#### `src/styles.css`
```css
* {
  margin: 0px;
  padding: 0px;
  box-sizing: border-box;
}

html,
body {
  min-height: 100%;
  background-color: var(--text-color);
  color: rgba(0, 0, 0, 0.7);
}

body {
  font-family: Arial, Helvetica, sans-serif;
}

.link:hover {
  filter: brightness(1.1);
}

:root {
  --main-color: #2196f3;
  --accent-color: #ff4081;
  --text-color: #ffffff;
  --footer-color: #42a5f5;
  --search-input-height: 40px;
}

.shadow {
  box-shadow: 0 0.0625rem 0.125rem 0.0625rem #00000026;
}
```

#### `src/index.html`
```html
<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>Shopping Store</title>
  <base href="/">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="icon" type="image/png" href="assets/images/logo.png">
</head>
<body>
  <app-root></app-root>
</body>
</html>
```

---

### 13. **Node.js Backend**

#### `package.json` (Node.js)
```json
{
  "name": "node-shopping-store",
  "version": "1.0.0",
  "main": "index.js",
  "scripts": {
    "start": "nodemon index.js"
  },
  "dependencies": {
    "cors": "^2.8.5",
    "dotenv": "^16.0.3",
    "express": "^4.18.2",
    "nodemon": "^2.0.20",
    "stripe": "^11.8.0"
  }
}
```

#### `.env` (Node.js)
```
STRIPE_SECRET_KEY=sk_123456666...
```

#### `index.js` (Node.js)
```javascript
const cors = require("cors");
const express = require("express");
require("dotenv").config();
const stripe = require("stripe")(process.env.STRIPE_SECRET_KEY);

const app = express();
app.use(express.json());
app.use(cors());

// Routes
app.get("/", (req, res) => {
  res.send("Server is running..");
});

app.post("/api/create-checkout-session", async (req, res) => {
  const origin = req.get("origin");
  const lineItems = req.body.map((item) => ({
    price_data: {
      currency: "usd",
      product_data: {
        name: item.name,
        images: [item.imageUrl],
      },
      unit_amount: Math.round(item.price * 100),
    },
    quantity: item.quantity,
  }));

  const session = await stripe.checkout.sessions.create({
    payment_method_types: ["card"],
    line_items: lineItems,
    mode: "payment",
    success_url: origin + "/success",
    cancel_url: origin + "/cancel",
  });

  res.json({ id: session.id });
});

app.listen(8000, () => {
  console.log("Server started at port 8000");
});
```

---

## Application Flow Explanation

### **1. Application Initialization**
- `main.ts` bootstraps `AppComponent`
- `AppComponent` imports `TopBarComponent`, `FooterComponent`, and `RouterOutlet`
- Routes are configured in `app.routes.ts`

### **2. Home Page Flow**
1. User visits `/` → Redirects to `/home`
2. `HomeComponent` displays:
   - `DealsComponent` (promotional banner image)
   - `ProductsComponent` (product grid)
3. Each product is rendered using `ProductCardComponent`
4. Product names are truncated using `TruncatePipe`

### **3. Adding Items to Cart**
1. User clicks "Add" button on a product card
2. `ProductCardComponent` emits `add` event with product data
3. `ProductsComponent` calls `cartService.addItem()`
4. `CartService` updates the signal-based cart state
5. `TopBarComponent` updates automatically (count and total via computed signals)

### **4. Cart Page Flow**
1. User clicks cart icon → Navigates to `/cart`
2. `CartComponent` displays:
   - List of items using `CartItemCardComponent`
   - Each item has a `QuantityStepperComponent`
   - Total summary with checkout button
3. Quantity changes:
   - User clicks +/- on stepper
   - `QuantityStepperComponent` emits `quantityChange`
   - `CartItemCardComponent` emits `itemQuantityUpdate`
   - `CartComponent` calls appropriate service method
4. Remove item: Click "Remove" → `CartService.removeItem()`

### **5. Checkout Flow**
1. User clicks "Continue to Checkout"
2. `CartComponent.onCheckout()`:
   - Loads Stripe with public key from environment
   - Sends cart items to Node.js backend (`/api/create-checkout-session`)
   - Redirects to Stripe checkout page
3. Payment processing:
   - User completes payment on Stripe
   - Redirected to `/success` (success) or `/cancel` (cancelled)

### **6. Success/Cancel Pages**
- Both pages display appropriate message and a "Continue Shopping" button
- Button redirects back to home page

---

### Key Technologies Used:
- **Angular 17+** with standalone components
- **Signals** for reactive state management
- **Stripe API** for payment processing
- **Node.js + Express** for backend
- **CORS** for cross-origin requests

# Angular Shopping Store - Complete Interview Concepts Guide

## 🎯 Core Angular Concepts

### 1. **Standalone Components (Angular 17+)**

**What are standalone components?**
- Components that don't need to be declared in an `NgModule`
- They manage their own dependencies directly
- Introduced in Angular 14, fully stable in Angular 17+

**Key Interview Points:**
```typescript
@Component({
  selector: 'app-top-bar',
  standalone: true,  // Key property
  imports: [DecimalPipe, RouterLink],  // Direct imports
  templateUrl: './top-bar.component.html',
  styleUrl: './top-bar.component.css',
})
```
- ✅ **Pros:** Lazy loading, better tree-shaking, simpler architecture
- ❌ **Cons:** Dependency duplication if not careful
- **Q:** "Why use standalone components over NgModules?"
- **A:** Better performance through tree-shaking, simpler codebase, easier to maintain

---

### 2. **Angular Signals (Reactive State Management)**

**What are Signals?**
- A reactive primitive for managing state
- Provide fine-grained reactivity without Zone.js
- Part of Angular's new reactivity model

**Key Interview Points:**

```typescript
cart = signal<Cart>({
  items: [],
  count: 0,
  total: 0,
});

// Computed signals
total = computed(() => this.cartService.cart().total);
count = computed(() => this.cartService.cart().count);

// Updating signals
this.cart.update((prevCart) => ({
  ...prevCart,
  count: prevCart.count + 1,
  total: prevCart.total + item.price,
}));
```

**Interview Q&A:**
- **Q:** "What's the difference between Signals and RxJS Observables?"
- **A:** 
  - Signals: Synchronous, push-based, simpler API, better for UI state
  - Observables: Asynchronous, push-pull, powerful operators, better for streams
  
- **Q:** "Why use signals in Angular?"
- **A:** 
  - More predictable change detection
  - Better performance (no Zone.js overhead)
  - Simpler mental model than observables for UI state
  - Automatic dependency tracking

---

### 3. **Dependency Injection (DI) Pattern**

**What is DI?**
- Design pattern where classes request dependencies from external sources
- Angular's core mechanism for providing services

**Key Interview Points:**

```typescript
@Injectable({
  providedIn: 'root',  // Singleton pattern
})
export class CartService {
  // Service logic
}

// Using in components
constructor(private cartService: CartService) {}
```

**Interview Q&A:**
- **Q:** "What are the different ways to provide a service in Angular?"
- **A:**
  - `providedIn: 'root'` - Application-wide singleton
  - `providers: []` in NgModule - Specific module scope
  - `providers: []` in Component - Component-level scope
  
- **Q:** "What's the difference between constructor injection and setter injection?"
- **A:** Constructor injection is preferred in Angular for immutability and clarity

---

## 🏗️ Architecture & Design Patterns

### 4. **Component Architecture**

**What are the different component types?**

```typescript
// 1. Smart Components (Container)
export class CartComponent {
  // Contains logic, fetches data, manages state
  constructor(private cartService: CartService) {}
}

// 2. Dumb Components (Presentational)
export class ProductCardComponent {
  @Input() product!: Product;
  @Output() add = new EventEmitter<Product>();
}
```

**Interview Q&A:**
- **Q:** "What's the difference between Smart and Dumb components?"
- **A:**
  - Smart: Contains business logic, uses services, manages state
  - Dumb: Pure presentation, receives data via @Input, emits events via @Output
  
- **Q:** "Why separate presentation from logic?"
- **A:** Better reusability, easier testing, clearer separation of concerns

---

### 5. **Input/Output Communication Pattern**

**What is Component Communication?**

```typescript
// Parent → Child (Property Binding)
@Input() product!: Product;  // In child
<app-product-card [product]="product"></app-product-card>  // In parent

// Child → Parent (Event Binding)
@Output() add = new EventEmitter<Product>();  // In child
this.add.next(this.product);  // Emitting
<app-product-card (add)="onAdd($event)"></app-product-card>  // In parent
```

**Interview Q&A:**
- **Q:** "What are the ways to communicate between Angular components?"
- **A:**
  1. @Input/@Output (parent-child)
  2. Service with Subject (any components)
  3. Route parameters
  4. ViewChild/ContentChild
  5. State management (NgRx, etc.)

- **Q:** "What's the difference between @Input and @Output?"
- **A:** @Input passes data down (parent → child), @Output passes events up (child → parent)

---

### 6. **Content Projection Pattern**

**What is Content Projection?**
- Passing content from parent to child components
- Using `<ng-content>` or structural directives

**From the book:**
```html
<!-- Parent -->
<app-deals></app-deals>
<app-products></app-products>

<!-- Footer projection -->
<footer class="wrapper">
  <div>About Shopping Store</div>
  <div>Help</div>
  <div>Privacy Notice</div>
</footer>
```

**Interview Q&A:**
- **Q:** "What is ng-content and when would you use it?"
- **A:** ng-content allows content transclusion, used for reusable components like cards, dialogs, modals

---

## 📦 Data Management

### 7. **Service Layer Pattern**

**What is a Service?**
- Class that provides specific functionality
- Handles business logic, data fetching, state management
- Injected via Dependency Injection

```typescript
@Injectable({
  providedIn: 'root'
})
export class CartService {
  cart = signal<Cart>({ items: [], count: 0, total: 0 });
  
  addItem(item: CartItem) {
    // Business logic
  }
}
```

**Interview Q&A:**
- **Q:** "Why use services instead of putting logic in components?"
- **A:** Separation of concerns, reusability, easier testing, maintainability

---

### 8. **Immutability Principle**

**What is Immutability?**
- Never modify data directly
- Create new copies with changes

```typescript
// ❌ Mutating
this.cart.items.push(newItem);

// ✅ Immutable
this.cart.update((prevCart) => ({
  ...prevCart,  // Spread operator
  items: [...prevCart.items, item],  // New array
  count: prevCart.count + 1,
  total: prevCart.total + item.price,
}));
```

**Interview Q&A:**
- **Q:** "Why is immutability important in Angular?"
- **A:** 
  - Enables OnPush change detection
  - Predictable state updates
  - Easier debugging (time-travel)
  - Prevents side effects

---

## 🎨 UI & Styling

### 9. **Responsive Design Implementation**

**What are the key responsive concepts?**

```css
/* Mobile-first approach */
.wrapper {
  padding: 10px 20px;
  
  /* Desktop overrides */
  @media (min-width: 760px) {
    padding: 10px 30px;
    grid-template-columns: repeat(6, auto);
  }
}

/* CSS Grid for layout */
.wrapper {
  display: grid;
  grid-template-columns: auto;
  row-gap: 70px;
}

/* CSS Variables (Custom Properties) */
:root {
  --main-color: #2196f3;
  --accent-color: #ff4081;
}
```

**Interview Q&A:**
- **Q:** "What's the difference between Mobile-First and Desktop-First design?"
- **A:**
  - Mobile-First: Starts with mobile styles, adds complexity for larger screens
  - Desktop-First: Starts with desktop styles, adds complexity for smaller screens
  
- **Q:** "When would you use CSS Grid vs Flexbox?"
- **A:** Grid for 2D layouts (rows and columns), Flexbox for 1D layouts (single axis)

---

### 10. **CSS Custom Properties (Variables)**

**What are CSS Variables?**

```css
:root {
  --main-color: #2196f3;
  --accent-color: #ff4081;
  --search-input-height: 40px;
}

.button {
  background-color: var(--accent-color);
  height: var(--search-input-height);
}
```

**Interview Q&A:**
- **Q:** "What are the benefits of CSS variables?"
- **A:** 
  - Centralized theming
  - Runtime changes without recompiling
  - Inheritance and cascade
  - Better maintainability

---

## 🛠️ Angular Features

### 11. **Pipes (Data Transformation)**

**What are Pipes?**
- Pure functions that transform data in templates
- Built-in and custom

```typescript
@Pipe({
  name: 'truncate',
  standalone: true,
})
export class TruncatePipe implements PipeTransform {
  transform(value: string): string {
    const limit = 40;
    const trail = '...';
    return value.length > limit ? value.substring(0, limit) + trail : value;
  }
}

// Usage in template
{{ product.name | truncate }}
{{ total() | number : "1.2-2" }}
```

**Interview Q&A:**
- **Q:** "What's the difference between pure and impure pipes?"
- **A:**
  - Pure: Only re-executes when input changes (default, better performance)
  - Impure: Executes on every change detection cycle (use with caution)
  
- **Q:** "When would you create a custom pipe?"
- **A:** For reusable formatting logic: truncate text, currency conversion, date formatting

---

### 12. **Routing and Navigation**

**What is Angular Routing?**

```typescript
export const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'cart', component: CartComponent },
  { path: 'success', component: SuccessComponent },
  { path: 'cancel', component: CancelComponent },
];

// Navigation
<div class="cart" routerLink="/cart">
<img src="assets/icons/cart.png" routerLink="/cart">
```

**Interview Q&A:**
- **Q:** "What's the difference between RouterModule and RouterOutlet?"
- **A:** 
  - RouterModule: Configures routes and provides routing services
  - RouterOutlet: The placeholder where routed components are rendered

---

### 13. **Lazy Loading with Standalone Components**

**What is Lazy Loading?**
- Loading modules/components only when needed
- Reduces initial bundle size

**Interview Q&A:**
- **Q:** "How does lazy loading work with standalone components?"
- **A:** Routes can directly load standalone components, making lazy loading simpler:
```typescript
{
  path: 'cart',
  loadComponent: () => 
    import('./pages/cart/cart.component').then(m => m.CartComponent)
}
```

---

## 🔄 State Management

### 14. **Reactive State Management Patterns**

**What patterns are used?**

```typescript
// Signal-based state management (Simple)
cart = signal<Cart>({ items: [], count: 0, total: 0 });

// Computed values
total = computed(() => this.cartService.cart().total);

// Update functions
addItem(item: CartItem) {
  this.cart.update((prevCart) => ({
    ...prevCart,
    items: [...prevCart.items, item],
    count: prevCart.count + 1,
    total: prevCart.total + item.price,
  }));
}
```

**Interview Q&A:**
- **Q:** "How does Angular's signal-based state management compare to NgRx?"
- **A:**
  - Signals: Simpler, less boilerplate, good for app-level state
  - NgRx: More complex, enterprise-ready, time-travel debugging
  
- **Q:** "What are the benefits of using signals over BehaviorSubject?"
- **A:** 
  - Less boilerplate
  - Automatic dependency tracking
  - Better performance
  - No subscription management

---

## 🔌 API Integration

### 15. **HTTP Client & Observables**

**What is HttpClient?**

```typescript
// In app.config.ts
providers: [importProvidersFrom(HttpClientModule)]

// In component
constructor(private http: HttpClient) {}

this.http
  .post('http://localhost:8000/api/create-checkout-session', body, {
    headers: headers,
  })
  .subscribe({
    next: async (response) => {
      const session = response as any;
      const result = await stripe?.redirectToCheckout({
        sessionId: session.id,
      });
    },
    error: (response) => {
      console.log(response?.error);
    },
  });
```

**Interview Q&A:**
- **Q:** "What's the difference between HttpClient and HttpModule?"
- **A:** HttpModule (deprecated) used older API, HttpClientModule is modern with JSON response handling
- **Q:** "How do you handle errors in HTTP requests?"
- **A:** Using catchError operator or subscribe error callback

---

## 💳 Payment Integration

### 16. **Stripe Integration Pattern**

**What is the Stripe integration flow?**

```typescript
// 1. Install Stripe
npm i @stripe/stripe-js

// 2. Load Stripe
const stripe = await loadStripe(environment.STRIPE_PK);

// 3. Create checkout session (backend)
app.post("/api/create-checkout-session", async (req, res) => {
  const lineItems = req.body.map((item) => ({
    price_data: {
      currency: "usd",
      product_data: {
        name: item.name,
        images: [item.imageUrl],
      },
      unit_amount: Math.round(item.price * 100),
    },
    quantity: item.quantity,
  }));

  const session = await stripe.checkout.sessions.create({
    payment_method_types: ["card"],
    line_items: lineItems,
    mode: "payment",
    success_url: origin + "/success",
    cancel_url: origin + "/cancel",
  });

  res.json({ id: session.id });
});

// 4. Redirect to Stripe
await stripe?.redirectToCheckout({ sessionId: session.id });
```

**Interview Q&A:**
- **Q:** "Why process payments on the server, not client?"
- **A:** Security - secret keys should never be exposed to client
- **Q:** "What are the key steps in Stripe Checkout integration?"
- **A:**
  1. Create checkout session on server
  2. Redirect user to Stripe
  3. Handle success/cancel callbacks

---

## 🌐 Backend Architecture

### 17. **Node.js/Express Backend**

**What is the Express architecture?**

```javascript
const cors = require("cors");
const express = require("express");
require("dotenv").config();
const stripe = require("stripe")(process.env.STRIPE_SECRET_KEY);

const app = express();
app.use(express.json());
app.use(cors());

// Simple endpoint
app.get("/", (req, res) => {
  res.send("Server is running..");
});

// Payment endpoint
app.post("/api/create-checkout-session", async (req, res) => {
  // Payment logic
});

app.listen(8000, () => {
  console.log("Server started at port 8000");
});
```

**Interview Q&A:**
- **Q:** "What is the purpose of CORS and why was it needed?"
- **A:** CORS allows web applications running at one origin to access resources from a different origin (frontend on port 4200, backend on port 8000)
- **Q:** "Why use environment variables (.env)?"
- **A:** To store sensitive information (API keys) outside the codebase

---

## 🛡️ Security Concepts

### 18. **Environment Separation**

```typescript
// environment.ts
export const environment = {
  STRIPE_PK: 'pk_1234567890ABCDEFGHIJKLMNOP...',
};
```

**Interview Q&A:**
- **Q:** "Why separate environment configurations?"
- **A:** 
  - Development vs Production keys
  - Different API endpoints
  - Feature flags

---

## 🧪 Best Practices

### 19. **Folder Structure & Organization**

**What is the recommended folder structure?**

```
src/
├── app/
│   ├── data/          # Static data files
│   ├── layout/        # Shared layout components
│   ├── pages/         # Page-level components
│   ├── pipes/         # Custom pipes
│   └── services/      # Business logic services
├── assets/            # Images, icons, etc.
└── environments/      # Environment configs
```

**Interview Q&A:**
- **Q:** "Why separate components by feature?"
- **A:** Better organization, scalability, maintainability, easier to find files

---

## 🚀 Performance Optimization

### 20. **Change Detection Strategies**

**Interview Q&A:**
- **Q:** "What is Change Detection in Angular?"
- **A:** The process that updates the DOM when data changes
- **Q:** "What are the two change detection strategies?"
- **A:**
  - Default: Checks entire component tree
  - OnPush: Only checks when inputs change (better performance)

---

## 📋 Summary - Key Interview Questions

### **Frequently Asked Questions:**

1. **"Explain the component lifecycle in Angular"**
   - Constructor → ngOnInit → ngOnChanges → ngDoCheck → ngAfterContentInit → ngAfterContentChecked → ngAfterViewInit → ngAfterViewChecked → ngOnDestroy

2. **"What is the difference between @ViewChild and @ContentChild?"**
   - ViewChild: Queries elements in the component's template
   - ContentChild: Queries elements projected via ng-content

3. **"How does Angular's Dependency Injection work?"**
   - Hierarchical injector system, services registered at different levels

4. **"What is the difference between Structural and Attribute Directives?"**
   - Structural: Change DOM structure (*ngIf, *ngFor)
   - Attribute: Change element appearance/behavior (ngClass, ngStyle)

5. **"What is RxJS and why use it with Angular?"**
   - Reactive Extensions for JavaScript
   - Handles async operations (HTTP, events, state)

6. **"Explain the difference between Subject and BehaviorSubject"**
   - Subject: Multicast observable, no initial value
   - BehaviorSubject: Requires initial value, emits last value to new subscribers

7. **"How do you handle forms in Angular?"**
   - Template-driven (simple forms)
   - Reactive forms (complex, validation, dynamic)

8. **"What are Angular Guards and why use them?"**
   - CanActivate, CanDeactivate, Resolve
   - Control route navigation (authentication, data loading)

9. **"Explain Interceptors in Angular"**
   - Middleware for HTTP requests/responses
   - Used for authentication, logging, error handling

10. **"What is the difference between Promise and Observable?"**
    - Promise: Single value, immediate execution
    - Observable: Multiple values, lazy execution

---

## 🎯 Key Takeaways for Interviews

### **Strong Points of This Application:**

1. **Modern Angular (17+)** - Using latest features (standalone, signals)
2. **Reactive State Management** - Signal-based approach
3. **Clean Architecture** - Separation of concerns
4. **Real-world Integration** - Payment processing with Stripe
5. **Responsive Design** - Mobile-first approach
6. **TypeScript Best Practices** - Interfaces, types, strict typing
7. **Full-stack Implementation** - Angular + Node.js

### **Keywords to Remember:**

- Standalone Components
- Signals (WritableSignal, computed)
- Dependency Injection (DI)
- Component Communication (@Input/@Output)
- Pipes (Pure/Impure)
- RxJS Observables
- HTTP Interceptors
- Lazy Loading
- Route Guards
- Content Projection
- Change Detection (OnPush)
- Services and Provider Scope
- Environment Configuration
- CORS (Cross-Origin Resource Sharing)
- Stripe Integration

