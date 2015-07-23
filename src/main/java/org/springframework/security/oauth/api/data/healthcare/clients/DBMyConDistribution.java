package org.springframework.security.oauth.api.data.healthcare.clients;

import java.sql.*;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
* Class DBConnection establishes a connection with a given database
* and provides a method for disconnecting to that database
*
* @version 0.8
*
*/

public class DBMyConDistribution {
	
	
	
	public static Connection getConnection() {
		
		String jdbcUrl = "jdbc:mysql://192.180.3.14:3307/smartlink";
		String jdbcUser = "DISTRIBUTION";
		String jdbcPassword = "distribution";

		Connection conn = null;
		try {
		Class.forName("com.mysql.jdbc.Driver");

		conn = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
		if (conn != null) {
		    System.out.println("Connected to .14");
		}
		} catch (Exception e) {
		e.printStackTrace();
		}
		return conn;
		}

	  private static InitialContext createContext() throws NamingException {
		    Properties env = new Properties();
		    env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.rmi.registry.RegistryContextFactory");
		    env.put(Context.PROVIDER_URL, "rmi://localhost:1099");
		    InitialContext context = new InitialContext(env);
		    return context;
		  }
	
    
    
}
