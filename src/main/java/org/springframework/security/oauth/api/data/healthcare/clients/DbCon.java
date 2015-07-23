package org.springframework.security.oauth.api.data.healthcare.clients;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DbCon {

	/**
	 * @param args
	 */

	  public static Connection getDBConnectionINTEG() {
	    	 
			Connection dbConnection = null;

			final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
			final String DB_CONNECTION = "jdbc:oracle:thin:@192.168.1.228:1521/integstaging";
			final String DB_USER = "integ_user";
			final String DB_PASSWORD = "integ_123";

			try {
	 
				Class.forName(DB_DRIVER);
	 
			} catch (ClassNotFoundException e) {
	 
				System.out.println(e.getMessage());
	 
			}
	 
			try {
	 
				dbConnection = DriverManager.getConnection(
	                            DB_CONNECTION, DB_USER,DB_PASSWORD);
				return dbConnection;
	 
			} catch (SQLException e) {
	 
				System.out.println(e.getMessage());
	 
			}
	 
			return dbConnection;
	 
		}
	  
	  
	  
	
	  public static Connection getDBConnectionBO() {
	    	 
			Connection dbConnection = null;

			final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
			final String DB_CONNECTION = "jdbc:oracle:thin:@192.180.3.14:1520/smart";
			final String DB_USER = "API_USER";
			final String DB_PASSWORD = "ulTr@n7";

			try {
	 
				Class.forName(DB_DRIVER);
	 
			} catch (ClassNotFoundException e) {
	 
				System.out.println(e.getMessage());
	 
			}
	 
			try {
	 
				dbConnection = DriverManager.getConnection(
	                            DB_CONNECTION, DB_USER,DB_PASSWORD);
				return dbConnection;
	 
			} catch (SQLException e) {
	 
				System.out.println(e.getMessage());
	 
			}
	 
			return dbConnection;
	 
		}
	  
	  
	  
	  
		
	  public static Connection getDBConnectionMSSQL(String dbname) {
		  
		   Connection dbConnection = null;
		   Statement statement = null;
		   
       try {
       	
           Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
           dbConnection = DriverManager.getConnection(
                           "jdbc:sqlserver://192.180.3.14:1433;databaseName="+dbname+";selectMethod=cursor",
                           "sa", "YesoBa1002");

           System.out.println("DATABASE NAME IS:"
                   + dbConnection.getMetaData().getDatabaseProductName());
 
       } catch (Exception e) {
           e.printStackTrace();
       }
       
			return dbConnection;
	 
		}

}
