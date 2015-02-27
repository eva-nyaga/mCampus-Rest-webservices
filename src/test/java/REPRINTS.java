import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class REPRINTS {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		   Connection dbConnection = null;
		   Statement statement = null;
		 
		//select COLUMN_NAME from ALL_TAB_COLUMNS where TABLE_NAME='STG_CIC_CLAIMS';
		dbConnection = getDBConnection();
		 Statement stmt = dbConnection.createStatement();
		    ResultSet rset = stmt
		        .executeQuery("select COLUMN_NAME from ALL_TAB_COLUMNS where TABLE_NAME='MAP_KRA_PROVIDER' AND OWNER = 'APITEST_OWNER'");
		    while (rset.next())
		      System.out.println(rset.getString(1));
		 

	
			 // Create a hash map
		      HashMap hm = new HashMap();
		      // Put elements to the map
		      /*
		      hm.put("CARDREQUESTNO", "CARDREQUESTNO");
			  hm.put("OTHER_NAMES", "OTHER_NAMES");
			  hm.put("THIRD_NAME", "THIRD_NAME");
			  hm.put("SECOND_NAME", "SECOND_NAME");
			  hm.put("SURNAME", "SURNAME");
			  hm.put("PRINT_DATE", "PRINT_DATE");
			  hm.put("CARD_SERIAL_NUMBER", "CARD_SERIAL_NUMBER");
			  hm.put("REORDER_REASON", "REORDER_REASON");
			  hm.put("INSERT_DATE", "INSERT_DATE");
			  hm.put("STATUS", "STATUS");
			  hm.put("USER_ID", "USER_ID");
			  hm.put("REORDER_DATE", "REORDER_DATE");
			  hm.put("STAFF_NO", "STAFF_NO");
			  hm.put("MEMBER_NO", "MEMBER_NO");
			  hm.put("ID", "ID");
			  */
		      
		      hm.put("COUNTRY_CODE", "COUNTRY_CODE");
		      hm.put("CLN_PROV_CODE", "CLN_PROV_CODE");
		      hm.put("CLN_DESCRIPTION", "CLN_DESCRIPTION");
		      hm.put("SMART_PROV_CODE", "SMART_PROV_CODE");
		      hm.put("STATUS", "STATUS");
		      hm.put("TOWN", "TOWN");
		      hm.put("BUILDING", "BUILDING");
		      hm.put("STREET", "STREET");
		      hm.put("POSTAL_NR", "POSTAL_NR");
		      hm.put("TEL_NR", "TEL_NR");
		      hm.put("INSERT_DATE", "INSERT_DATE");
		      hm.put("FAX_NR", "FAX_NR");
		      hm.put("EMAIL_ADDRESS", "EMAIL_ADDRESS");
		      hm.put("CONTACT_PERSON", "CONTACT_PERSON");
		      hm.put("SATELITE_PROV", "SATELITE_PROV");
		      hm.put("SMART_DESCRIPTION", "SMART_DESCRIPTION");


	      
	      // Get a set of the entries
	      Set set = hm.entrySet();
	      // Get an iterator
	      Iterator i = set.iterator();
	      // Display elements
	      while(i.hasNext()) {
	         Map.Entry me = (Map.Entry)i.next();
	         //System.out.print(me.getKey() + ": ");
	         //System.out.println(me.getValue());
	      }
  	
	   Gson gson = new Gson();
	   String serializedMap = gson.toJson(hm);
	   
	  // System.out.println(hm);
	  // System.out.println(serializedMap);
		    
	   HashMap MIMI = new HashMap();
	   MIMI = gson.fromJson(serializedMap, new TypeToken<HashMap<String, String>>(){}.getType());

	      // Get a set of the entries
	      Set set2 = MIMI.entrySet();
	      // Get an iterator
	      Iterator i2 = set2.iterator();
	      // Display elements
	      while(i2.hasNext()) {
	         Map.Entry me2 = (Map.Entry)i2.next();
	           //System.out.print(me2.getKey() + ":");
	           //System.out.println(me2.getValue());
	      }
	     // Object value = MIMI.get("POLICY_NUMBER");
	    //  System.out.println(value);

		String updateTableSQL = "UPDATE INTEG_USER.SMARTAPI_FIN_CLIENT_MAP"
				+ " SET PROVIDERS_MAP = '"+serializedMap+"' ";


System.out.println(updateTableSQL);
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			//statement.execute(updateTableSQL);

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (statement != null) {
				statement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}

		}
		

 
      
      
      
	}
	
	
	
	  private static Connection getDBConnection() {
	    	 
			Connection dbConnection = null;

			final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
			final String DB_CONNECTION = "jdbc:oracle:thin:@192.168.1.27:1521/integstaging";
			final String DB_USER = "INTEG_USER";
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

}
