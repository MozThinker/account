# account-api
This is a README file for Account-api. Account-api is a Rest API which allows users to create new current accounts and also get the customer information details.

## Used Tech Stack
- Spring Boot
- Java 17
- Spring Data JPA
- GIT ACTIONS
- Restful API
- OpenAPI documentation
- H2 In memory database
- JUnit 5


Please find below the instructions on how to run the application locally.

## Prerequisites

Before running the application, ensure that you have the following installed on your machine:

- Java Development Kit (JDK) 17 or higher
- Maven build tool

## Getting Started

Follow these steps to run the application locally:

1. Clone the repository:

   ```bash
   git clone https://github.com/MozThinker/account.git
   cd account-api
   ```

2. Build the application using Maven:

   ```bash
   mvn clean install
   ```

3. Run the application:

   ```bash
   java -jar target/account-api.jar
   ```

   The application will start and listen on the default port (8080).

4. Access the application Swagger page to test the API:

   Open a web browser and visit http://localhost:8080/swagger-ui/index.html to access the application.

## Testing

To run the automated tests for the application, use the following command:

```bash
mvn test
```


## Contact

If you have any questions or need further assistance, please contact [edson.mutombene@gmail.com](mailto:edson.mutombene@gmail.com).