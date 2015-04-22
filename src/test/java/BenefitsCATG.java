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


public class BenefitsCATG {

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
		        .executeQuery("select COLUMN_NAME from ALL_TAB_COLUMNS where TABLE_NAME='STG_KRA_BENEFITS_CATG' AND OWNER = 'KRA_OWNER'");
		    while (rset.next())
		      System.out.println(rset.getString(1).toUpperCase());
		 
			 // Create a hash map
			 HashMap hm = new HashMap();
		      // Put elements to the map

		      hm.put("REC_ID", "REC_ID");
			  hm.put("SMART_FPLAN", "SMART_FPLAN");
			  hm.put("SMART_MPLAN", "SMART_MPLAN");
			  hm.put("STATUS", "STATUS");
			  hm.put("CLN_POL_ID", "CLN_POL_ID");
			  hm.put("CLN_POL_CODE", "CLN_POL_CODE");
			  hm.put("CUT_OFF_AGE", "CUT_OFF_AGE");
			  hm.put("CUT_OFF_IND", "CUT_OFF_IND");
			  hm.put("DATE_ADDED", "DATE_ADDED");
			  hm.put("WAITING_PERIOD", "WAITING_PERIOD");
			  hm.put("SPEND_THRESPCT", "SPEND_THRESPCT");
			  hm.put("SPEND_THRESHOLD", "SPEND_THRESHOLD");
			  hm.put("CLN_CAT_CODE", "CLN_CAT_CODE");
			  hm.put("AUTO_GROWTH_PCT", "AUTO_GROWTH_PCT");
			  hm.put("AUTO_GROWTH_IND", "AUTO_GROWTH_IND");
			  hm.put("AUTO_REPLENISH_IND", "AUTO_REPLENISH_IND");
			  hm.put("POLICY_NUMBER", "POLICY_NUMBER");
			  hm.put("OUT_PATIENT_OVERALL", "OUT_PATIENT_OVERALL");
			  hm.put("IN_PATIENT_OVERALL", "IN_PATIENT_OVERALL");
			  hm.put("CAT_DESC", "CAT_DESC");
			  hm.put("SMART_CAT_NO", "SMART_CAT_NO");
			  hm.put("SMART_FSPLAN", "SMART_FSPLAN");
			  hm.put("SMART_MSPLAN", "SMART_MSPLAN");
			  hm.put("MED_CATG", "MED_CATG");
			  hm.put("USER_ID", "USER_ID");
			  hm.put("INSURER_ID", "INSURER_ID");
	      
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
				+ " SET BENEFITS_CATG_MAP = '"+serializedMap+"' ";



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
