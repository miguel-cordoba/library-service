
## Overview

This project is a backend service designed to manage `Books` and their associated `Authors`. It includes functionality for creating new books and authors, as well as handling the relationships between them. The solution was built with best practices in mind, using DTOs to separate concerns and aiming for clean code. However, due to time constraints, a few areas were left unfinished or need refinement.

### Requirements

The application was intended to provide the following:

- **Swagger-UI**: An interactive Swagger-UI page for users to interact with the API and view the documentation. (http://localhost:8080/swagger-ui.html
        )
- **PostgreSQL Database**: A PostgreSQL database is used for data storage and persistence.
- **Docker Support**: The application is to be delivered as a Docker image and/or using Docker Compose for easy deployment and scalability.

### Architecture

- **Separation of Concerns**: The application was designed to ensure that different layers are decoupled. The service layer handles business logic, and the persistence layer (repositories) manages database interactions.
- **DTO Approach**: DTOs were utilized to map data between the service/controller and the entity layers, maintaining clear separation between internal domain models and external data representations.
- **Layered Architecture**: The application follows a classical layered approach, ensuring each layer has a clear responsibility:
  - **Controller Layer**: Handles HTTP requests and responses.
  - **Service Layer**: Contains the core business logic.
  - **Repository Layer**: Manages database interactions via PostgreSQL.
  
Efforts were made to organize the code following clean, maintainable, and testable principles.

## Approach

### Design Principles

- **DTO Approach**: To ensure a clean separation between the data layer (entities) and the service/controller layers, DTOs (Data Transfer Objects) were used. This approach keeps the service logic focused on the business rules, not on persistence concerns.
- **Separation of Concerns**: The service layer handles business logic, and the persistence layer (repositories) handles database interaction. Each layer is decoupled to make the codebase more maintainable.
- **Clean Code**: Efforts were made to follow clean code practices, ensuring the system was easy to understand, maintain, and extend.

### Key Features

- **Book Creation**: Books are created and linked to authors via their `id`.
- **Author Creation**: Authors are required to exist before books can be added.
- **Circular Dependency Handling**: Efforts were made to properly manage the bidirectional relationship between `Author` and `Book` entities.

### Challenges Encountered

1. **Duplicate Key Errors (Constraint Violation)**:
   - During book creation, a constraint violation error occurred due to a duplicate `id` in the `Book` table.
   - **Root Cause**: The `id` for the book entity was set to `null`, but the database was attempting to insert a record with an already existing `id`, causing the violation.
   - **What Was Tried**: Manual `id` nullification, as well as various cascade configurations for saving `Author` and `Book` entities.

2. **Circular Dependencies**:
   - The bidirectional relationship between `Author` and `Book` created difficulties when trying to save and update the entities. Both entities reference each other, which led to challenges in properly persisting them without causing an infinite loop or broken relationships.
   - **What Was Tried**: Several strategies for decoupling the two entities, such as ensuring the author's `bookSet` was updated after a book was created, but circular dependencies still remained a challenge.

3. **DTO Mapping**:
   - **DTOs** were used to separate the data transfer layer from the domain model, but managing deeply nested entities, especially the circular references, made the mapping logic more complicated than expected.

4. **Time Pressure**:
   - The task had strict time constraints, which limited the ability to implement, test, and refine the solution fully. Some features were not finished, and other aspects of the design (like circular dependency resolution) were only partially addressed.

## Assumptions

- **Author Data**: It was assumed that the `Author` entity already exists in the database before any books are created. If the author is missing, the user is informed that they need to create the author first.
- **Auto-Generated IDs**: I assumed that Hibernate would handle ID generation for entities with `null` IDs. However, this assumption didn’t work as expected, leading to the duplicate key constraint violation.
- **Bidirectional Relationships**: Efforts were made to properly manage the bidirectional relationships between `Author` and `Book`. The complexity of these relationships was underestimated, especially when working with cascading operations.

## Issues Not Fully Resolved

1. **Circular Dependencies**: 
   - I wasn't able to resolve the bidirectional relationship between `Author` and `Book` entities fully. This is a key area where further attention would be required to prevent issues like infinite loops or missing updates.

2. **ID Generation Issue**:
   - The issue with duplicate primary key violations related to the `id` being set to `null` was not fully fixed. Proper auto-generation of the ID would need to be revisited.

## Next Steps

If more time were available, the following improvements would be made:

1. **Fixing the ID Generation Issue**: 
   - Ensuring the database properly auto-generates IDs for `Book` entities when `id` is `null`.

2. **Circular Dependency Management**:
   - Refining the handling of bidirectional relationships, ensuring that updates to both `Author` and `Book` entities are synchronized without causing data inconsistencies or circular dependency issues.

3. **Testing & Edge Cases**:
   - Expanding the testing coverage to handle edge cases, such as missing authors, duplicate books, and proper cascading updates to nested entities.

4. **Additional Features**:
   - Adding the ability to create authors directly within the same request as the book, which would simplify the flow for the user.

## Running the Application

### Prerequisites

Before running the application, make sure you have the following installed:

- Docker
- Docker Compose
- PostgreSQL

### Setup with Docker

To run the application using Docker, you can use the provided `Dockerfile` and `docker-compose.yml` for easy deployment.

#### Docker Compose Setup

1. Clone the repository.
2. Run the following command to start the application and PostgreSQL database:

   ```bash
   docker-compose up
