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

public class DBConInteractive {
	
	
	
	public static Connection getConnection() {
		String SID = "integstaging";
		String jdbcUrl = "jdbc:oracle:thin:@//192.168.1.27:1521/"+SID;
		String jdbcUser = "INTERACTIVE";
		String jdbcPassword = "Int3ract123";

		Connection conn = null;
		try {
		Class.forName("oracle.jdbc.driver.OracleDriver");

		conn = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
		if (conn != null) {
		    System.out.println("Connected with SID:"+SID);
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