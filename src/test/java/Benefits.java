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


public class Benefits {

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
		        .executeQuery("select COLUMN_NAME from ALL_TAB_COLUMNS where TABLE_NAME='STG_KRA_BENEFITS'  AND OWNER = 'KRA_OWNER'");
		    while (rset.next())
		      System.out.println(rset.getString(1).toLowerCase());
		 
			 // Create a hash map
		      HashMap hm = new HashMap();
		      // Put elements to the map

		      hm.put("REC_ID", "REC_ID");
			  hm.put("STATUS", "STATUS");
			  hm.put("CLN_POL_ID", "CLN_POL_ID");
			  hm.put("CLN_POL_CODE", "CLN_POL_CODE");
			  hm.put("AUTO_GROWTH_PCT", "AUTO_GROWTH_PCT");
			  hm.put("AUTO_GROWTH_IND", "AUTO_GROWTH_IND");
			  hm.put("BEN_LINKED2_CLNCODE", "BEN_LINKED2_CLNCODE");
			  hm.put("BEN_TYP_DESC", "BEN_TYP_DESC");
			  hm.put("CLN_BEN_CODE", "CLN_BEN_CODE");
			  hm.put("USER_ID", "USER_ID");
			  hm.put("BEN_END_DATE", "BEN_END_DATE");
			  hm.put("AUTO_REPLENISH_IND", "AUTO_REPLENISH_IND");
			  hm.put("SPEND_THRESPCT", "SPEND_THRESPCT");
			  hm.put("SPEND_THRESHOLD", "SPEND_THRESHOLD");
			  hm.put("MEM_ASSIGNED_BENEFIT", "MEM_ASSIGNED_BENEFIT");
			  hm.put("SERVICE_TYPE", "SERVICE_TYPE");
			  hm.put("EFFECTIVE_DATE", "EFFECTIVE_DATE");
			  hm.put("WAITING_PERIOD", "WAITING_PERIOD");
			  hm.put("GENDER_APPLICABILITY", "GENDER_APPLICABILITY");
			  hm.put("DATE_ADDED", "DATE_ADDED");
			  hm.put("LIMIT_AMT", "LIMIT_AMT");
			  hm.put("SUB_LIMIT_AMT", "SUB_LIMIT_AMT");
			  hm.put("BEN_LINKED2_ID", "BEN_LINKED2_ID");
			  hm.put("CO_PAY_PERCENT", "CO_PAY_PERCENT");
			  hm.put("CO_PAY_AMT", "CO_PAY_AMT");
			  hm.put("BEN_TYPE_ID", "BEN_TYPE_ID");
			  hm.put("CAT_CODE", "CAT_CODE");
			  hm.put("POLICY_NUMBER", "POLICY_NUMBER");
			  hm.put("BENEFIT_DESC", "BENEFIT_DESC");
			  hm.put("SMART_BEN_ID", "SMART_BEN_ID");
			  hm.put("CLN_BEN_CLAUSE_CODE", "CLN_BEN_CLAUSE_CODE");
			  hm.put("COPAY_IND", "COPAY_IND");
			  hm.put("AUTO_REPLENISH_LIMTYPE", "AUTO_REPLENISH_LIMTYPE");
			  hm.put("AUTO_REPLENISH_LIMIT", "AUTO_REPLENISH_LIMIT");
			  hm.put("AUTO_GROWTH_RATEIND", "AUTO_GROWTH_RATEIND");
			  hm.put("AUTO_GROWTH_RATE", "AUTO_GROWTH_RATE");
			  hm.put("AUTO_GROWTH_CEILING", "AUTO_GROWTH_CEILING");
			  hm.put("CUT_OFF_IND", "CUT_OFF_IND");
			  hm.put("CUT_OFF_AGE", "CUT_OFF_AGE");
			  hm.put("PICKED_STATUS", "PICKED_STATUS");
			  hm.put("PICKED_DATE", "PICKED_DATE");
			  hm.put("INSURER_ID", "INSURER_ID");
			  hm.put("LAYERED_IND", "LAYERED_IND");
			  
	      
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
				+ " SET BENEFITS_MAP = '"+serializedMap+"'";



		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			//System.out.println(updateTableSQL);
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
