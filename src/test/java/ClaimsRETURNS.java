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


public class ClaimsRETURNS {

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
		        .executeQuery("select COLUMN_NAME from ALL_TAB_COLUMNS where TABLE_NAME='STG_KRA_CLAIM_RETURNS' AND OWNER = 'KRA_OWNER'");
		    while (rset.next())
		      System.out.println(rset.getString(1));
		 

	
			 // Create a hash map
		      HashMap hm = new HashMap();
		      // Put elements to the map
		      hm.put("INVOICE_DATE", "INVOICE_DATE");
			  hm.put("INSERT_DATE", "INSERT_DATE");
			  hm.put("CLN_POL_CODE", "CLN_POL_CODE");
			  hm.put("REC_ID", "REC_ID");
			  hm.put("USER_ID", "USER_ID");
			  hm.put("BENEFIT_CODE", "BENEFIT_CODE");
			  hm.put("ACTION_NAME", "ACTION_NAME");
			  hm.put("ACTION_CODE", "ACTION_CODE");
			  hm.put("INVOICE_NUMBER", "INVOICE_NUMBER");
			  hm.put("PROVIDER_CODE", "PROVIDER_CODE");
			  hm.put("ANNIV", "ANNIV");
			  hm.put("MEMBER_NUMBER", "MEMBER_NUMBER");
			  hm.put("SMART_PICKED_DATE", "SMART_PICKED_DATE");
			  hm.put("SMART_PICKED", "SMART_PICKED");
			  hm.put("DATE_ENTERED", "DATE_ENTERED");
			  hm.put("RETURN_REASON", "RETURN_REASON");
			  hm.put("RETURN_CODE", "RETURN_CODE");
			  hm.put("RETURNED_AMOUNT", "RETURNED_AMOUNT");
			  hm.put("SMART_BILL_ID", "SMART_BILL_ID");
			  hm.put("INVOICE_ID", "INVOICE_ID");
	      
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
	      Object value = MIMI.get("POLICY_NUMBER");
	    //  System.out.println(value);

		String updateTableSQL = "UPDATE INTEG_USER.SMARTAPI_FIN_CLIENT_MAP"
				+ " SET CLAIM_RETURNS_MAP = '"+serializedMap+"' ";



		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
		//	statement.execute(updateTableSQL);

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
