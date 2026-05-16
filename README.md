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

## Docker

Build and run both backend and frontend:

```powershell
docker compose up --build
```

Open the app:

```text
http://localhost:4200
```

Build images separately:

```powershell
docker build -t insurance-backend:latest ./backend
docker build -t insurance-frontend:latest ./frontend
```

Tag and push to Docker Hub:

```powershell
docker tag insurance-backend:latest your-dockerhub-user/insurance-backend:latest
docker tag insurance-frontend:latest your-dockerhub-user/insurance-frontend:latest
docker push your-dockerhub-user/insurance-backend:latest
docker push your-dockerhub-user/insurance-frontend:latest
```

## Jenkins Pipeline With SonarQube

The root `Jenkinsfile` builds both projects and runs one SonarQube scan for backend and frontend.

Required Jenkins tools:

- `JDK17`
- `Maven3`
- `Node20`
- `SonarScanner`

Required Jenkins SonarQube server name:

```text
SonarQube
```

If your Jenkins tool names are different, update the `tools` and `environment` values in `Jenkinsfile`.

Pipeline stages:

- Checkout
- Backend Maven build and test
- Frontend npm install and Angular build
- SonarQube analysis
- Quality gate
- Docker compose build
