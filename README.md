# Spring Boot Quiz Microservices

A Java-based Quiz Application built using Spring Boot and Microservices Architecture. The application separates quiz management, question management, API routing, and service discovery into independent services.

## Technologies Used

- Java
- Spring Boot
- Spring Cloud
- Spring Data JPA and Hibernate
- REST APIs
- Feign Client
- Eureka Service Discovery
- API Gateway
- Maven
- SQL Database

## Project Structure

| Service | Responsibility |
|---|---|
| `api-gateway` | Entry point for routing API requests |
| `question` | Manages questions and calculates quiz scores |
| `quiz` | Creates quizzes and retrieves quiz questions |
| `servicediscovery` | Supports service registration and discovery |

## Architecture

The client sends requests through the API Gateway. The Quiz service communicates with the Question service through a Feign client. The Question service manages question data and evaluates submitted answers. Service Discovery supports locating registered services.

## API Documentation

Base URL from the Postman collection: `http://localhost:2517`

### Question Management APIs

| Method | Endpoint | Description |
|---|---|---|
| POST | `/questions/add` | Add multiple questions |
| GET | `/questions?page=0&size=10` | Retrieve paginated questions |
| GET | `/questions/{category}` | Get question IDs by category |
| POST | `/questions` | Retrieve question details by IDs |
| POST | `/questions/results` | Calculate a score |
| PUT | `/questions` | Update a question |
| DELETE | `/questions/{id}` | Delete a question |

### Quiz Management APIs

| Method | Endpoint | Description |
|---|---|---|
| POST | `/quiz` | Create a quiz for a category |
| GET | `/quiz/{quizId}` | Retrieve questions in a quiz |
| POST | `/quiz/result` | Submit answers and retrieve the score |
| DELETE | `/quiz/{id}` | Delete a quiz |

### Sample Requests

#### 1. Add Questions

`POST /questions/add`

```json
[
  {
    "category": "java",
    "question": "Which keyword is used to inherit a class?",
    "option1": "implements",
    "option2": "extends",
    "option3": "inherits",
    "option4": "super",
    "answer": 2
  }
]
```

#### 2. Create a Quiz

`POST /quiz`

```json
{
  "category": "java"
}
```

#### 3. Retrieve Questions by IDs

`POST /questions`

```json
[1, 2, 3]
```

#### 4. Submit Quiz Answers

`POST /quiz/result`

```json
[
  {
    "questionId": 1,
    "answer": 2
  },
  {
    "questionId": 2,
    "answer": 3
  }
]
```

#### 5. Update a Question

`PUT /questions`

```json
{
  "id": 1,
  "category": "java",
  "question": "Which keyword is used to inherit a class?",
  "option1": "implements",
  "option2": "extends",
  "option3": "inherits",
  "option4": "super",
  "answer": 2
}
```

### Testing with Postman

Import the Postman collection into Postman and configure the base URL as `http://localhost:2517`. Start the required services and execute the requests to test the APIs.

## Getting Started

1. Clone the repository:

   ```bash
   git clone https://github.com/VINAY-CHALLA/springboot-quiz-microservices.git
   ```

2. Open the project in your IDE.
3. Configure the database connection and service ports in the application properties.
4. Build the services using Maven.
5. Start Service Discovery, the Question and Quiz services, and the API Gateway according to their configuration.
6. Import the Postman collection and test the endpoints.

## Author

**Vinay Challa**

- GitHub: https://github.com/VINAY-CHALLA
- LinkedIn: https://www.linkedin.com/in/challa-vinay/
