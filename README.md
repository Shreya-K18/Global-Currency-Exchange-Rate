# Global Currency Exchange Rate

## Overview

Global Currency Exchange Rate is a Java-based application that provides real-time currency conversion using live exchange rate data. The project integrates Java, JDBC, MySQL, and an Exchange Rate API to deliver accurate and up-to-date currency conversion results.

## Features

- Real-time currency conversion
- Dynamic exchange rate updates using API integration
- Java and MySQL database connectivity using JDBC
- User-friendly interface
- Secure database connection
- Stores and manages currency-related data

## Technologies Used

- Java
- JDBC
- MySQL
- Exchange Rate API
- Eclipse IDE

## Project Structure

- Currencyproject.java – Main project logic
- CurrencyUI.java – User interface
- DBconnection.java – Database connection setup
- currencyexchange.sql – Database schema and tables

## How It Works

1. The application fetches the latest exchange rates through an API.
2. Exchange rates are updated dynamically.
3. Users enter the source currency, target currency, and amount.
4. The application calculates and displays the converted value.
5. Data is managed using a MySQL database connected through JDBC.

## Setup Instructions

1. Import the `currencyexchange.sql` file into MySQL.
2. Configure database credentials in `DBconnection.java`.
3. Install the required JDBC driver.
4. Run the project in Eclipse IDE.
5. Ensure an active internet connection for live exchange rate updates.

## Future Improvements

- Support for more currencies
- Exchange rate history tracking
- Graphical analytics dashboard
- Enhanced user interface

## Author

Shreya Khandelwal
