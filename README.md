# 🎬 Movie App - Spring Boot

A **Spring Boot** backend for the **Movie App**, providing API endpoints for managing movies, authentication, and integrating with the **OMDB API**.

## 🚀 Features
- 🔍 **Search Movies** by title or ID using **OMDB API**.
- 📜 **List Movies** with details like title, genre, director, etc.
- 🔐 **Authentication & Authorization** using Spring Security.
- 👤 **User Roles**: Admin & Regular users.
- 🛠 **Admin Controls** to manage movies.

## 🏗️ Technologies Used
- **Backend**: Spring Boot (Java 8+)
- **Database**: PostgreSQL / MySQL (Configurable)
- **Security**: Spring Security with Basic Auth
- **API Integration**: OMDB API
- **ORM**: Spring Data JPA with Hibernate


## 🔧 Installation & Setup
### 🔹 Clone the Repository
```sh
git clone https://github.com/negm12/movie_app-spring-boot.git
cd movie_app-spring-boot
```

## Update src/main/resources/application.properties with your PostgreSQL / MySQL database settings:
spring.datasource.url=jdbc:mysql://localhost:3306/movie_db.
spring.datasource.username=root.
spring.datasource.password=root.
spring.jpa.hibernate.ddl-auto=update.


## Build & Run the Application
mvn clean install.
mvn spring-boot:run.

## Then open http://localhost:8080 in your browser.

## Authentication
**Admin Credentials:**
Username: admin .
Password: admin123 .

**User Credentials:**
Username: user .
Password: user123 .


## API Endpoints

# 📖 Movie App - API Documentation  

## 🌍 Base URL  
```
http://localhost:8080
```

---

## 🔑 **Authentication APIs**  

### 🔹 Login  
**Endpoint:**  
```http
POST /auth/login
```  
**Request Body:**  
```json
{
  "username": "admin",
  "password": "admin123"
}
```  
**Response:**  
```json
{
  "id":user id,
  "username":user name,
  "password": password,
  "role": "ROLE_ADMIN"
}
```  
**Description:**  
- Authenticates a user and returns a **user object**.

---

### 🔹 Register  
**Endpoint:**  
```http
POST /auth/register
```  
**Request Body:**  
```json
{
  "username": "newuser",
  "password": "password123",
  "role": "ROLE_USER"
}
```  
**Response:**  
```json
{
  "message": "User registered successfully"
}
```  
**Description:**  
- Registers a new user.

---

## 🎬 **Movie APIs**  

### 🔹 Search Movies by Title  
**Endpoint:**  
```http
GET /movies?title={title}
```  
**Response:**  
```json
[
  {
    "imdbID": "tt1234567",
    "title": "Inception",
    "year": "2010",
    "genre": "Sci-Fi",
    "director": "Christopher Nolan"
  }
]
```  
**Description:**  
- Fetches all movies that match the given **title**.

---

### 🔹 Get Movie by ID  
**Endpoint:**  
```http
GET /movies/{id}
```  
**Example Request:**  
```http
GET /movies/tt1234567
```  
**Response:**  
```json
{
  "imdbID": "tt1234567",
  "title": "Inception",
  "year": "2010",
  "genre": "Sci-Fi",
  "director": "Christopher Nolan",
  "plot": "A thief who enters the dreams of others to steal their secrets.",
  "actors": "Leonardo DiCaprio, Joseph Gordon-Levitt, Ellen Page"
}
```  
**Description:**  
- Fetches movie details by **IMDb ID**.

---

### 🔹 Add a Movie (Admin Only)  
**Endpoint:**  
```http
POST /movies
```  
**Headers:**  
```http
Authorization: Bearer <JWT_TOKEN>
```
**Request Body:**  
```json
{
  "title": "Interstellar",
  "year": "2014",
  "genre": "Sci-Fi",
  "director": "Christopher Nolan"
}
```
**Response:**  
```json
{
  "message": "Movie added successfully",
  "movie": {
    "imdbID": "tt0816692",
    "title": "Interstellar",
    "year": "2014",
    "genre": "Sci-Fi",
    "director": "Christopher Nolan"
  }
}
```  
**Description:**  
- Adds a new movie (**Admin only**).

---

### 🔹 Delete a Movie (Admin Only)  
**Endpoint:**  
```http
DELETE /movies/{id}
```
**Headers:**  
```http
Authorization: Bearer <JWT_TOKEN>
```
**Response:**  
```json
{
  "message": "Movie deleted successfully"
}
```  
**Description:**  
- Deletes a movie by **IMDb ID** (**Admin only**).

---

## 🛠 **Admin APIs**  

### 🔹 Get All Users (Admin Only)  
**Endpoint:**  
```http
GET /admin/users
```
**Headers:**  
```http
Authorization: Bearer <JWT_TOKEN>
```
**Response:**  
```json
[
  {
    "id": 1,
    "username": "admin",
    "role": "ROLE_ADMIN"
  },
  {
    "id": 2,
    "username": "user1",
    "role": "ROLE_USER"
  }
]
```  
**Description:**  
- Fetches all registered users (**Admin only**).



## 📜 License  
This project is licensed under the MIT License.




