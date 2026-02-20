🚀 How to Run This Project with Docker

Follow these simple steps to get the Product API up and running locally using Docker.

✅ 1️⃣ Prerequisites

Make sure you have Docker Desktop installed:

🔗 Install Docker Desktop for your OS

Check that Docker is installed:

docker --version
docker compose version


✅ 2️⃣ Clone the Repository
git clone https://github.com/saurabh-pardhi/product-api.git
cd product-api


✅ 3️⃣ Start the Application

Make sure you are in the project root (where docker-compose.yml is located), then run:

docker-compose up -d


This will start both:

PostgreSQL database (product-postgres)
Product API (product-api)



✅ 4️⃣ Check if Containers Are Running
docker ps

You should see something like:

CONTAINER ID   IMAGE                        NAMES
xxxxxxx        saurabh2108/product-api      product-api
xxxxxxx        postgres:15                  product-postgres


🌐 5️⃣ Access the Application

API Base URL:

http://localhost:8080

Swagger UI (Interactive API Docs):

http://localhost:8080/swagger-ui.html

or

http://localhost:8080/swagger-ui/index.html


----------------------------------------------------------------------------

🚀 API Usage Guide

Follow this step-by-step flow to use the application after running it locally.

Base URL 👉 http://localhost:8080

🧑‍💻 1. Register a New User

🔗 POST http://localhost:8080/auth/register

Description: Create a new account.

Request Body

{
  "username": "john_doe",
  "password": "password123"
}

✅ Response

{
  "message": "User registered successfully"
}

🔐 2. Login to Get Tokens

🔗 POST http://localhost:8080/auth/login

Description: Authenticate and receive tokens.

Request Body

{
  "username": "john_doe",
  "password": "password123"
}

✅ Response

{
  "accessToken": "your-access-token",
  "refreshToken": "your-refresh-token"
}

📌 Important:
Use the accessToken in the header for protected APIs.

Authorization: Bearer <accessToken>

📦 3. Access Protected APIs

Example: Get all products

🔗 GET http://localhost:8080/products

You must include the Authorization header.


🔄 4. Refresh Token (When Access Token Expires)

🔗 POST http://localhost:8080/auth/refresh

Request Body

{
  "refreshToken": "your-refresh-token"
}

✅ Response

{
  "accessToken": "new-access-token",
  "refreshToken": "new-refresh-token"
}
🧭 Flow Summary

➡️ Register → Login → Use APIs → Refresh token when expired

1️⃣ Register user
2️⃣ Login to get tokens
3️⃣ Call protected APIs with access token
4️⃣ Refresh token if expired

📚 List of Access APIs (Full Links)
🔑 Authentication APIs

📝 Register → POST http://localhost:8080/auth/register

🔐 Login → POST http://localhost:8080/auth/login

🔄 Refresh Token → POST http://localhost:8080/auth/refresh

📦 Product APIs

📄 Get All Products → GET http://localhost:8080/products

🔍 Get Product By ID → GET http://localhost:8080/products/{id}

➕ Create Product → POST http://localhost:8080/products

✏️ Update Product → PUT http://localhost:8080/products/{id}

🗑️ Delete Product → DELETE http://localhost:8080/products/{id}
