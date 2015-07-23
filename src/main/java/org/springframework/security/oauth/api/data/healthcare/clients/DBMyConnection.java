package org.springframework.security.oauth.api.data.healthcare.clients;

//STEP 1. Import required packages
import java.sql.*;


public class DBMyConnection {

	 Connection conn = null;
	 // JDBC driver name and database URL
	 static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	 static final String DB_URL = "jdbc:mysql://192.180.3.14:3307/smartlink";

	 //  Database credentials
	 static final String USER = "DISTRIBUTION";
	 static final String PASS = "distribution";
	 
    public  DBMyConnection() throws ClassNotFoundException, SQLException{
    	
    //STEP 2: Register JDBC driver
    Class.forName("com.mysql.jdbc.Driver");

    //STEP 3: Open a connection
    System.out.println("Connecting to database...");
    conn = DriverManager.getConnection(DB_URL,USER,PASS);

     }

	public Statement createStatement() {
		// TODO Auto-generated method stub
		return null;
	}
	 
}
