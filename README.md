# Library Management System

This is a Library Management System built with Spring Boot, featuring caching, database integration, logging, and unit testing capabilities.

## Features

- **Caching**: Uses Caffeine for efficient caching.
- **Data Storage**: Integrates both H2 and MySQL databases.
- **Logging**: Implements Aspect for detailed logging.
- **Unit Testing**: Incorporates Mockito for thorough unit testing.

## Running the Application

1. Clone the repository to your local machine.
2. Open Eclipse IDE.
3. Navigate to `File -> Import -> Maven -> Existing Maven Projects`.
4. Select the root directory of the project and click `Finish`.
5. In the Package Explorer pane, right-click on the project and choose `Run As -> Spring Boot App`.

## Interacting with API Endpoints

You can interact with the API using HTTP clients like curl, Postman, etc. A Postman collection is provided in the repository.

### Available Endpoints

- **Book Management**
  - `GET /api/books`: Retrieve all books.
  - `GET /api/books/{id}`: Retrieve a book by ID.
  - `POST /api/books`: Add a new book.
  - `PUT /api/books/{id}`: Update a book's information.
  - `DELETE /api/books/{id}`: Remove a book.

- **Patron Management**
  - `GET /api/patrons`: Retrieve all patrons.
  - `GET /api/patrons/{id}`: Retrieve a patron by ID.
  - `POST /api/patrons`: Add a new patron.
  - `PUT /api/patrons/{id}`: Update a patron's information.
  - `DELETE /api/patrons/{id}`: Remove a patron.

- **Borrowing**
  - `POST /api/borrow/{bookId}/patron/{patronId}`: Borrow a book.
  - `PUT /api/return/{bookId}/patron/{patronId}`: Return a borrowed book.

## Configuring Data Storage

The application supports both H2 and MySQL databases. Switch between them in the `application.yml` file.

For MySQL, update the JDBC path in `application-mysql.yml`.

## Logging

Logging is implemented using Aspect, with outputs to the console.

## Transaction Management

Transactions are managed using Spring's `@Transactional` annotation.

## Testing

Unit tests are implemented using Mockito. Run tests via Eclipse by right-clicking on the project and selecting `Run As -> JUnit Test`.

## Caching

Caching is handled by Caffeine. Configuration is in `CacheConfig.java`. Cache statistics are logged every 10 seconds by `CacheMonitoringService.java`.

For more detailed information, please refer to the individual Java files in the project.
