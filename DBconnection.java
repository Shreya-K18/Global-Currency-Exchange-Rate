package com.globalfinance;

import java.sql.Connection;
import java.sql.DriverManager;



public class DBconnection {
 public static void main(String[] args) {
	 try {
		 Connection con = 
DriverManager.getConnection(
		"jdbc:mysql://localhost:3306/global_finance",
		"root",
		"Project@123"
		);
		 
System.out.println("Connected to MYsql successsfully!");
	 } catch (Exception e) {
		 e.printStackTrace();

						 
	 }
 }
}
