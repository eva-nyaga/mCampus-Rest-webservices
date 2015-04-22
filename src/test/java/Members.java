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


public class Members {

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
		        .executeQuery("select COLUMN_NAME from ALL_TAB_COLUMNS where TABLE_NAME='STG_KRA_MEM_DETS' AND OWNER = 'APITEST_OWNER'");
		    while (rset.next())
		     // System.out.println(rset.getString(1));
		    System.out.println(rset.getString(1));
		 
			 // Create a hash map
			 HashMap hm = new HashMap();
		      // Put elements to the map

		      hm.put("TITLE", "TITLE");
			  hm.put("DEPTNAME", "DEPTNAME");
			  hm.put("STATION", "STATION");
			  hm.put("CLN_CAT_CODE", "CLN_CAT_CODE");
			  hm.put("INSERT_DATE", "INSERT_DATE");
			  hm.put("CLN_UNIQUE_MEM_NUMBER", "CLN_UNIQUE_MEM_NUMBER");
			  hm.put("REC_ID", "REC_ID");
			  hm.put("SCHEME_END_DATE", "SCHEME_END_DATE");
			  hm.put("STATUS", "STATUS");
			  hm.put("CLN_POL_ID", "CLN_POL_ID");
			  hm.put("CLN_POL_CODE", "CLN_POL_CODE");
			  hm.put("CLN_COM_CODE", "CLN_COM_CODE");
			  hm.put("CLN_POL_NUMBER", "CLN_POL_NUMBER");
			  hm.put("USER_ID", "USER_ID");
			  hm.put("SCHEME_START_DATE", "SCHEME_START_DATE");
			  hm.put("FAMILY_CODE", "FAMILY_CODE");
			  hm.put("MEM_TYPE", "MEM_TYPE");
			  hm.put("GLOBAL_ID", "GLOBAL_ID");
			  hm.put("GENDER", "GENDER");
			  hm.put("NHIF_NUMBER", "NHIF_NUMBER");
			  hm.put("STAFF_NUMBER", "STAFF_NUMBER");
			  hm.put("MEMBERSHIP_NUMBER", "MEMBERSHIP_NUMBER");
			  hm.put("PHOTO", "PHOTO");
			  hm.put("KIN_NATID", "KIN_NATID");
			  hm.put("KIN_EMAIL", "KIN_EMAIL");
			  hm.put("KIN_TEL_NO", "KIN_TEL_NO");
			  hm.put("KIN_ONAMES", "KIN_ONAMES");
			  hm.put("KIN_MNAME", "KIN_MNAME");
			  hm.put("KIN_FNAME", "KIN_FNAME");
			  hm.put("CUT_OFF_AGE", "CUT_OFF_AGE");
			  hm.put("ACTIONED_DATE", "ACTIONED_DATE");
			  hm.put("MODIFICATION_DATE", "MODIFICATION_DATE");
			  hm.put("MEM_STATUS", "MEM_STATUS");
			  hm.put("DEACT_DATE", "DEACT_DATE");
			  hm.put("JOIN_DATE", "JOIN_DATE");
			  hm.put("CARD_SERIAL_NUMBER", "CARD_SERIAL_NUMBER");
			  hm.put("DOB", "DOB");
			  hm.put("OTHER_NAMES", "OTHER_NAMES");
			  hm.put("THIRD_NAME", "THIRD_NAME");
			  hm.put("SECOND_NAME", "SECOND_NAME");
			  hm.put("SURNAME", "SURNAME");
			  hm.put("ID_NUMBER", "ID_NUMBER");
			  hm.put("COM_ID", "COM_ID");
			  hm.put("POL_ID", "POL_ID");
			  hm.put("PRINT_DATE", "PRINT_DATE");
			  hm.put("OTHER_NUMBER", "OTHER_NUMBER");
			  hm.put("PRINT_CARD", "PRINT_CARD");
			  hm.put("CUT_OFF_EXEMPTION", "CUT_OFF_EXEMPTION");
			  hm.put("REGION", "REGION");
			  hm.put("PHONE_NO", "PHONE_NO");
			  hm.put("EMAIL", "EMAIL");
			  hm.put("PRINT_DATE", "PRINT_DATE");
			  hm.put("INSURER_ID", "INSURER_ID");
			  hm.put("ROAMING_ENABLED", "ROAMING_ENABLED");
			  hm.put("ROAMING_COUNTRIES", "ROAMING_COUNTRIES");
	      
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
/*
		String updateTableSQL = "UPDATE INTEG_USER.SMARTAPI_FIN_CLIENT_MAP"
				+ " SET MEM_DETS_CHANGES_MAP = '"+serializedMap+"' ";
*/
	      
			String updateTableSQL = "UPDATE INTEG_USER.SMARTAPI_FIN_CLIENT_MAP"
					+ " SET MEM_DETS_MAP = '"+serializedMap+"' ";


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
