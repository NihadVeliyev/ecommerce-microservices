E-Commerce Microservices with RabbitMQ (Ordering Flow)
Objective
Build an E-Commerce platform using Microservice Architecture.
- Services communicate via REST APIs for CRUD operations.
- RabbitMQ is used only for the order processing workflow (order → payment → inventory → delivery).
Microservices
1. ms-user
Role: Manage user information.
REST APIs:
- POST /users → Register user
- GET /users/{id} → Get user info 
2. ms-auth
Role: Authentication (JWT).
REST APIs:
- POST /auth/register → Register new user (calls ms-user)
- POST /auth/login → Login & get JWT
3. ms-inventory
Role: Manage products and stock.
REST APIs:
- POST /products → Add product
- GET /products/{id} → Product details
- PUT /products/{id}/stock → Update stock

RabbitMQ Events:
- Consume: ORDER_CREATED (decrease stock)
- Publish: STOCK_UPDATED
4. ms-account
Role: Manage wallet balance.
REST APIs:
- GET /accounts/{userId} → Get balance
- POST /accounts/{userId}/credit → Add funds
5. ms-order
Role: Create and track orders.
REST APIs:
- POST /orders → Create order (publishes ORDER_CREATED)
- GET /orders/{id} → Order details

RabbitMQ Events:
- Publish: ORDER_CREATED, ORDER_CONFIRMED
- Consume: PAYMENT_SUCCESS, PAYMENT_FAILED, DELIVERY_COMPLETED, STOCK_UPDATED
6. ms-payment
Role: Process payments.
REST APIs:
- GET /payments/{id} → Payment status

RabbitMQ Events:
- Consume: ORDER_CREATED
- Publish: PAYMENT_SUCCESS, PAYMENT_FAILED
7. ms-delivery
Role: Handle delivery scheduling and tracking.
REST APIs:
- GET /delivery/{id} → Track delivery

RabbitMQ Events:
- Consume: ORDER_CONFIRMED
- Publish: DELIVERY_COMPLETED
Ordering Workflow (Event-Driven)
1. User places order → ms-order publishes ORDER_CREATED.
2. ms-inventory consumes ORDER_CREATED → decreases stock → publishes STOCK_UPDATED.
3. ms-payment consumes ORDER_CREATED → checks funds in ms-account.
   - On success → publishes PAYMENT_SUCCESS.
   - On failure → publishes PAYMENT_FAILED.
4. ms-order consumes:
   - PAYMENT_FAILED → order = CANCELLED.
   - PAYMENT_SUCCESS + STOCK_UPDATED → order = CONFIRMED → publishes ORDER_CONFIRMED.
5. ms-delivery consumes ORDER_CONFIRMED → schedules delivery.
6. On delivery completion → publishes DELIVERY_COMPLETED.
7. ms-order consumes → finalizes status = DELIVERED.
Event Schemas
- ORDER_CREATED: {eventId ,orderId, userId, items, totalAmount, currency }
- STOCK_UPDATED: { orderId, productId, newStockLevel, status: 'RESERVED' }
- PAYMENT_SUCCESS: { orderId, userId, amount, currency, status: 'SUCCESS' }
- PAYMENT_FAILED: { orderId, userId, amount, currency, status: 'FAILED', reason }
- ORDER_CONFIRMED: { orderId, userId, deliveryAddress, items }
- DELIVERY_COMPLETED: { orderId, userId, deliveryId, status: 'DELIVERED' }
Technical Requirements
1. Each microservice has its own database.
2. REST APIs for user/account/inventory management.
3. RabbitMQ for order workflow events (ORDER_CREATED, STOCK_UPDATED, PAYMENT_SUCCESS, etc.).
4. Stock must be decremented when an order is placed.
5. Provide Docker Compose setup for all services + RabbitMQ.
6. Document APIs with Swagger/OpenAPI.
7. Error handling
8. Unit tests 


 Deliverables
Source code for microservices.
RabbitMQ consumers & publishers for ordering workflow.
Swagger docs.
Docker Compose file.
Demo scenario:
   1. Register & login user
   2. Add funds to account
   3. Add product(s) to inventory
   4. Place an order → stock decreases → payment processed → delivery scheduled → order completed
