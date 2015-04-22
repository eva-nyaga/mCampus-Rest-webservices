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


public class POLICY {

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
		        .executeQuery("select COLUMN_NAME from ALL_TAB_COLUMNS where TABLE_NAME='STG_KRA_POLICY' AND OWNER = 'KRA_OWNER'");
		    while (rset.next())
		      System.out.println(rset.getString(1).toLowerCase());
		 

	
			 // Create a hash map
		      HashMap hm = new HashMap();
		      // Put elements to the map
		      hm.put("SMART_CODE", "SMART_CODE");
			  hm.put("STATUS", "STATUS");
			  hm.put("COM_ID", "COM_ID");
			  hm.put("END_DATE", "END_DATE");
			  hm.put("START_DATE", "START_DATE");
			  hm.put("POL_NAME", "POL_NAME");
			  hm.put("POLICY_NUMBER", "POLICY_NUMBER");
			  hm.put("REC_ID", "REC_ID");
			  hm.put("CLN_POL_ID", "CLN_POL_ID");
			  hm.put("CLN_POL_CODE", "CLN_POL_CODE");
			  hm.put("CLN_POL_TYPE", "CLN_POL_TYPE");
			  hm.put("AUTO_REPLENISH_IND", "AUTO_REPLENISH_IND");
			  hm.put("CLN_CODE", "CLN_CODE");
			  hm.put("ACTIONED_DATE", "ACTIONED_DATE");
			  hm.put("MODIFICATION_DATE", "MODIFICATION_DATE");
			  hm.put("POLICY_STATUS", "POLICY_STATUS");
			  hm.put("SPEND_THRESPCT", "SPEND_THRESPCT");
			  hm.put("CLAIM_GRACE_PERIOD", "CLAIM_GRACE_PERIOD");
			  hm.put("USER_ID", "USER_ID");
			  hm.put("DATE_ADDED", "DATE_ADDED");
			  hm.put("POL_TYPE_ID", "POL_TYPE_ID");
			  hm.put("CAP_CONSEQ_IND", "CAP_CONSEQ_IND");
			  hm.put("CAP_WITHIN_DURATION", "CAP_WITHIN_DURATION");
			  hm.put("CAP_FREQOF_USE", "CAP_FREQOF_USE");
			  hm.put("CAPITATION_AMOUNT", "CAPITATION_AMOUNT");
			  hm.put("CAPITATION_IND", "CAPITATION_IND");
			  hm.put("POLICY_CURRENCY_ID", "POLICY_CURRENCY_ID");
			  hm.put("DEL_PTCONTACT_TEL", "DEL_PTCONTACT_TEL");
			  hm.put("DEL_CONTACT_EMAIL", "DEL_CONTACT_EMAIL");
			  hm.put("DEL_CONTACT", "DEL_CONTACT");
			  hm.put("DELIVERY_POINT", "DELIVERY_POINT");
			  hm.put("INV_PTCONTACT_TEL", "INV_PTCONTACT_TEL");
			  hm.put("INV_CONTACT_EMAIL", "INV_CONTACT_EMAIL");
			  hm.put("INVOICE_CONTACT", "INVOICE_CONTACT");
			  hm.put("INVOICE_PT", "INVOICE_PT");
			  hm.put("CUT_OFF_AGE", "CUT_OFF_AGE");
			  hm.put("CUT_OFF_IND", "CUT_OFF_IND");
			  hm.put("CLN_POL_CODE_CLIENT", "CLN_POL_CODE_CLIENT");
			  hm.put("INSERT_DATE", "INSERT_DATE");
			  hm.put("POLICY_CONV_RATE", "POLICY_CONV_RATE");
			  hm.put("ANNIV", "ANNIV");
			  hm.put("POL_ID", "POL_ID");
			  hm.put("INSURER_ID", "INSURER_ID");
			  hm.put("COUNTRY_CODE", "COUNTRY_CODE");
			  hm.put("TRANS_STATUS", "TRANS_STATUS");
			  hm.put("TRANS_STATUS_CODE", "TRANS_STATUS_CODE");
			  

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
				+ " SET POLICY_MAP = '"+serializedMap+"' ";



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
