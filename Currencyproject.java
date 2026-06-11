package com.globalfinance;

import java.sql.*;
import java.util.Scanner;
import java.net.*;
import java.io.*;

public class Currencyproject{


	    static final String DB_URL = "jdbc:mysql://localhost:3306/global_finance";
	    static final String USER = "root";
	    static final String PASS = "Project@123";

	    static final String API_KEY = "70a848aca51defea8547095f";

	    public static void main(String[] args) {

	        Scanner sc = new Scanner(System.in);

	        try {
	            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
	            System.out.println("Connected to DB\n");

	            int choice;

	            do {
	                System.out.println("\n===== Currency System =====");
	                System.out.println("1. Convert Currency (Live API)");
	                System.out.println("2. View Stored Rates");
	                System.out.println("3. Exit");
	                System.out.print("Enter choice: ");
	                choice = sc.nextInt();

	                switch (choice) {

	                    case 1:
	                        convertCurrency(con, sc);
	                        break;

	                    case 2:
	                        showRates(con);
	                        break;

	                    case 3:
	                        System.out.println("Exiting...");
	                        break;

	                    default:
	                        System.out.println("Invalid choice!");
	                }

	            } while (choice != 3);

	            con.close();

	        } catch (Exception e) {
	            System.out.println("Error: " + e.getMessage());
	        }
	    }

	    // 🔥 Convert using API
	    public static void convertCurrency(Connection con, Scanner sc) {
	        try {
	            System.out.print("Enter FROM currency code (USD): ");
	            String from = sc.next();

	            System.out.print("Enter TO currency code (INR): ");
	            String to = sc.next();

	            System.out.print("Enter amount: ");
	            double amount = sc.nextDouble();

	            String urlStr = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/" + from;

	            URL url = new URL(urlStr);
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	            conn.setRequestMethod("GET");

	            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            String line;
	            StringBuilder result = new StringBuilder();

	            while ((line = reader.readLine()) != null) {
	                result.append(line);
	            }
	            reader.close();

	            String json = result.toString();

	            String search = "\"" + to + "\":";
	            int index = json.indexOf(search);

	            if (index == -1) {
	                System.out.println("Currency not found in API");
	                return;
	            }

	            int start = index + search.length();
	            int end = json.indexOf(",", start);

	            double rate = Double.parseDouble(json.substring(start, end));

	            double converted = amount * rate;

	            System.out.println("Live Rate: " + rate);
	            System.out.println("Converted Amount: " + converted);

	            // Store in DB
	            String insert = "INSERT INTO exchange_rate (from_currency, to_currency, rate, update_date) VALUES (?, ?, ?, CURDATE())";

	            PreparedStatement ps = con.prepareStatement(insert);

	            ps.setInt(1, 1);
	            ps.setInt(2, 2); 
	            ps.setDouble(3, rate);

	            ps.executeUpdate();

	            System.out.println("Rate saved in database");

	        } catch (Exception e) {
	            System.out.println("Error in conversion: " + e.getMessage());
	        }
	    }

	    // 🔥 Show stored rates
	    public static void showRates(Connection con) {
	        try {
	            String query = "SELECT * FROM exchange_rate";
	            Statement stmt = con.createStatement();
	            ResultSet rs = stmt.executeQuery(query);

	            System.out.println("\nStored Exchange Rates:");
	            while (rs.next()) {
	                int id = rs.getInt("rate_id");
	                int from = rs.getInt("from_currency");
	                int to = rs.getInt("to_currency");
	                double rate = rs.getDouble("rate");
	                Date date = rs.getDate("update_date");

	                System.out.println(id + " | " + from + " -> " + to + " = " + rate + " (" + date + ")");
	            }

	        } catch (Exception e) {
	            System.out.println("Error fetching data: " + e.getMessage());
	        }
	    }
	}


    

            
    