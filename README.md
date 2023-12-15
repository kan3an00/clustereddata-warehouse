# ClusteredData Warehouse

This project is part of a Scrum team's development effort to create a data warehouse for Bloomberg to analyze FX deals.

## Overview

The system aims to accept deal details and persist them into a database. The required request fields include Deal Unique Id, From Currency ISO Code "Ordering Currency," To Currency ISO Code, Deal timestamp, and Deal Amount in the ordering currency.

## Functionality

- Validates row structure (checks for missing fields, type format, etc.).
- Prevents importing duplicate requests.
- Implements error/exception handling.
- Provides proper logging and documentation.
- Supports unit testing with coverage.

## Technology Stack

- **Database:** MySQL.
- **Deployment:** Docker Compose.
- **Build Tool:** Maven.
- **Framework:** Java Spring Boot.

## Getting Started

### Prerequisites

- Java Development Kit (JDK)
- Docker
- Maven

### Installation

1. Clone the repository.
2. Navigate to the project root.

### Usage

#### Building the Application

```bash
# Build the Docker containers
docker-compose up --build
