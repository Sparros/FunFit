# FunFit Project

## Overview

The **FunFit Project** is a web-based application developed for managing **participants** and **batches** in a fitness program. This system allows administrators to create, view, and delete batches and participants, providing an easy-to-use interface for managing fitness program details. It is built using **Java**, **Servlets**, **JSP (JavaServer Pages)**, **Bootstrap**, and **MySQL**.

## Features

- **Batch Management**: 
  - Create new batches
  - Display all batches in a dropdown
  - Delete batches (with associated participants)
  
- **Participant Management**:
  - Add new participants with their details (name, age, email, batch)
  - View participant details
  - Delete participants

- **Search and Filter**:
  - Filter participants by batch
  - Display all participants or filter by selected batch

- **Responsive UI**: 
  - Built with **Bootstrap** for a mobile-friendly and responsive design.

## Technologies Used

- **Java**: Programming language used for backend logic.
- **Servlets**: To handle HTTP requests and responses.
- **JSP (JavaServer Pages)**: For dynamically generated web pages.
- **MySQL**: For database management and data persistence.
- **Bootstrap**: For front-end design and responsiveness.
- **JDBC**: Java Database Connectivity to interact with the database.

## Setup

### Prerequisites

- **Java** (JDK 8 or higher)
- **Apache Tomcat** (for deploying the web application)
- **MySQL** (for database management)

### Configure the Database

1. Create a MySQL database named `funfit`.
2. Import the schema for `participants` and `batches` tables.

Example SQL:

```sql
CREATE TABLE batches (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE participants (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    email VARCHAR(100) NOT NULL,
    batch_id INT,
    FOREIGN KEY (batch_id) REFERENCES batches(id)
);
```

### Configure Database Connection

In the project, configure the `db.properties` or the relevant database connection file with your database credentials.

### Deploy the Application

Deploy the project to Apache Tomcat by copying the project to the `webapps` folder of Tomcat or use an IDE like Eclipse to run the project.

### Running the Application

After deploying the application, open your browser and go to:

```bash
http://localhost:8080/funfit-project/
```

### Usage

- **Homepage**: Lists the batches available and provides options to view participants or add new participants.
- **Batch Management**: Admin can add new batches or delete existing ones.
- **Participant Management**: Admin can add new participants, view their details, or delete them.
- **Filtering**: Use the dropdown to filter participants by batch.

