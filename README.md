# Insurance Spring Boot + Angular Project

This project is a complete demo insurance marketplace with:

- Health, motor, and travel insurance catalogue
- Cart item add, quantity update, remove, and clear APIs
- Checkout/order service
- Angular product browser, cart, checkout form, and recent orders view
- In-memory H2 database seeded with sample policies

## Project Structure

```text
backend/   Spring Boot REST API
frontend/  Angular single page app
```

## Backend

From `backend`:

```powershell
$env:JAVA_HOME="C:\Program Files\Eclipse Adoptium\jdk-17.0.19.10-hotspot"
mvn spring-boot:run
```

API base URL:

```text
http://localhost:8080/api
```

Useful endpoints:

- `GET /api/products`
- `GET /api/products?type=HEALTH`
- `GET /api/products?type=MOTOR`
- `GET /api/products?type=TRAVEL`
- `GET /api/cart`
- `POST /api/cart/items`
- `PUT /api/cart/items/{id}`
- `DELETE /api/cart/items/{id}`
- `POST /api/orders/checkout`
- `GET /api/orders`

H2 console:

```text
http://localhost:8080/h2-console
```

Use JDBC URL `jdbc:h2:mem:insurancedb`, username `sa`, and an empty password.

## Frontend

From `frontend`:

```powershell
npm install
npm start
```

Angular will run at:

```text
http://localhost:4200
```

The frontend expects the backend at `http://localhost:8080/api`.
