/**
 * 
 */
package com.shopping.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Amit
 *
 */
public class RdsConnection {

	// JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   //static final String DB_URL = "jdbc:mysql://cmpe282.ch5k92keofxy.us-west-2.rds.amazonaws.com:3306/mydb";
	   static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/mydb";
	   //  Database credentials
	   /*static final String USER = "amitdikkar";
	   static final String PASS = "amitdikkar";
	   */
	   static final String USER = "root";
	   static final String PASS = "root";
	   
	   Connection conn = null;
	   Statement stmt = null;
	   
	public RdsConnection() {
		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			// STEP 3: Open a connection
			System.out.println("Connecting to a selected database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Connecting to a selected database...");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	   
	   public Connection getRdsConnection(){
		   return conn;
	   }
}
