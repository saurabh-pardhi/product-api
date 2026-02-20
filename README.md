How to Run This Project (Using Docker)
✅ 1️⃣ Prerequisites

Make sure you have installed:

-- Docker Desktop

Check installation:

docker --version

✅ 2️⃣ Clone the Repository
git clone https://github.com/saurabh-pardhi/product-api
cd product-api


✅ 3️⃣ Run the Application

Inside the project root (where docker-compose.yml exists):
run this command --

docker-compose up -d


✅ 4️⃣ Verify Containers

docker ps

You should see:

product-api
product-postgres


🌐 Access the Application
🔹 API Base URL
http://localhost:8080
🔹 Swagger UI

http://localhost:8080/swagger-ui.html

or

http://localhost:8080/swagger-ui/index.html
