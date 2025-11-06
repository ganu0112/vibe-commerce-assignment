# Vibe Commerce - Mock E-Com Cart (Assignment)

This repo contains a simple full-stack shopping cart assignment:
- Backend: Spring Boot (Java) + MySQL (JPA)
- Frontend: React (Vite)

## How to run (backend)
1. Install Java 17+ and Maven.
2. Create a MySQL database called `vibecommerce` and update `backend/src/main/resources/application.properties` with your MySQL username/password.
3. From `/backend` run:
   ```
   mvn spring-boot:run
   ```
   Server runs on `http://localhost:8080`.

## How to run (frontend)
1. Install Node 18+ and npm.
2. From `/frontend`:
   ```
   npm install
   npm run dev
   ```
   Frontend runs on `http://localhost:5173` and talks to backend at `http://localhost:8080`.

## APIs
- `GET /api/products` - list products
- `GET /api/cart` - get cart items
- `POST /api/cart` - add {productId, qty}
- `DELETE /api/cart/{id}` - remove item
- `POST /api/cart/checkout` - {name,email} -> receipt

## Notes
- Uses MySQL for persistence. Spring JPA `ddl-auto=update` will create tables.
- Simple in-memory product seeding is included.
- For the assignment, you can record a 1-2 minute demo showing product add/remove and checkout.

