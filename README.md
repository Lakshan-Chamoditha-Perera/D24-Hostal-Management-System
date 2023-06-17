# D24 Hostel Management System

The D24 Hostel Management System is a comprehensive software solution designed to automate the student registration and fee payment process for the D24 Hostel. This system provides efficient management of hostel accommodations and payments, streamlining the overall process for both students and hostel staff.

## Features

- **Student Management**: Allows CRUD operations for managing student records, including creating, reading, updating, and deleting student information. Students can be registered with their personal details, including name, contact information, and campus student ID.

- **Room Management**: Provides CRUD operations for managing room details, including room types, availability, and capacity. Hostel staff can easily add, update, or remove rooms from the system, ensuring accurate information about available accommodations.

- **Reservation Management**: Facilitates the reservation process by allowing students to request room reservations for specific time periods. Hostel staff can review and approve these reservations, ensuring efficient allocation of available rooms to students.

- **Payment Tracking**: Tracks the payment details of students, including key money payments and monthly fees. Hostel staff can easily monitor payment statuses, identify outstanding balances, and generate payment reports.

- **Reporting and Analytics**: Generates reports and analytics to provide insights into occupancy rates, revenue generation, and financial trends. These reports help hostel management make informed decisions and plan for future accommodations.

- **User Authentication and Access Control**: Implements a secure login system, ensuring that only authorized users can access the system. Different user roles can be defined, such as administrators and staff members, with appropriate access permissions.

## Technologies Used

- **Front-end**: HTML, CSS, JavaScript, React.js
- **Back-end**: Node.js, Express.js
- **Database**: MySQL
- **ORM (Object-Relational Mapping)**: Hibernate

## Design Patterns

- **Singleton Pattern**: The `DaoFactory` class follows the Singleton pattern to ensure that only one instance of the factory is created and used throughout the application. It provides a centralized way to retrieve different DAO implementations.

- **Factory Pattern**: The `DaoFactory` class also demonstrates the Factory pattern by providing a factory method to create specific DAO implementations based on the requested type. It abstracts the creation logic and allows easy extension of DAO classes.

- **Facade Pattern**: The system design incorporates the Facade pattern to provide a simplified interface for interacting with complex subsystems. The controller classes act as facades that encapsulate the business logic and interact with the DAO layer.

## Installation

1. Clone the repository:
   ```shell
   git clone https://github.com/ShanPereraX/D24-Hostal-Management-System.git
