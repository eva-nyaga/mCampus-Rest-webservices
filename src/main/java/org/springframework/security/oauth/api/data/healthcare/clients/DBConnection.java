package org.springframework.security.oauth.api.data.healthcare.clients;

import java.sql.*;

/**
* Class DBConnection establishes a connection with a given database
* and provides a method for disconnecting to that database
*
* @version 0.8
*
*/

public class DBConnection {

	public static Connection getConnection(String[] DBParams) {
		if (DBParams[1] == null || DBParams[1].equals("")) return null;
		if (DBParams[2] == null || DBParams[2].equals("")) return null;
		if (DBParams[3] == null) return null;
		
		Connection conn = null;
		try {
		Class.forName("oracle.jdbc.driver.OracleDriver");

		conn = DriverManager.getConnection(DBParams[1], DBParams[2], DBParams[3]);
		if (conn != null) {
		    System.out.println("Connected with SID: "+DBParams[0]);
		   }
		  } catch (Exception e) {
		e.printStackTrace();
		}
		
	 return conn;
	}
 
    
}