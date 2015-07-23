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


public class M_COMPANIES_MAP {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		   Connection dbConnection = null;
		   Statement statement = null;
			 // Create a hash map
		      HashMap hm = new HashMap();
		      
		//select COLUMN_NAME from ALL_TAB_COLUMNS where TABLE_NAME='MAP_AAR_COMPANIES' AND OWNER = 'AAR_OWNER';
		dbConnection = getDBConnection();
		 Statement stmt = dbConnection.createStatement();
		    ResultSet rset = stmt
		        .executeQuery("select COLUMN_NAME from ALL_TAB_COLUMNS where TABLE_NAME='MAP_GI_BENEFITS' AND OWNER = 'GI_OWNER'");
		    while (rset.next())
		     // System.out.println(rset.getString(1));
		    	//System.out.println("");
		    
		      System.out.println(hm.put(""+rset.getString(1)+"", ""+rset.getString(1)+""));
		 

	

		      // Put elements to the map
		     // 
			 // hm.put("STATUS_DESCRIPTION", "STATUS_DESCRIPTION");


	      
	      // Get a set of the entries
	      Set set = hm.entrySet();
	      // Get an iterator
	      Iterator i = set.iterator();
	      // Display elements
	      while(i.hasNext()) {
	         Map.Entry me = (Map.Entry)i.next();
	         System.out.print(me.getKey() + ":");
	         System.out.println(me.getValue());
	      }
  	
	   Gson gson = new Gson();
	   String serializedMap = gson.toJson(hm);
	   
	   /*
UPDATE INTEG_USER.SMARTAPI_FIN_CLIENT_MAP SET M_COMPANIES_MAP = '{"CLN_POL_CODE":"CLN_POL_CODE","MAPPED_BY":"MAPPED_BY","CLN_CODE":"CLN_CODE","COMPANY_NAME":"COMPANY_NAME","POL_ID":"POL_ID","STATUS":"STATUS","SMART_CODE2":"SMART_CODE2","ALPHACOUNT":"ALPHACOUNT","CLN_POL_CODE_CLIENT":"CLN_POL_CODE_CLIENT"}'
	    */
	   
	    System.out.println(hm);
	    System.out.println(serializedMap);
		    
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
				+ " SET M_COMPANIES_MAP = '"+serializedMap+"' ";

      //  System.out.println(updateTableSQL);
 //{"REC_ID":"REC_ID","CLN_DESCRIPTION":"CLN_DESCRIPTION","SMART_DESC":"SMART_DESC","INSERT_DATE":"INSERT_DATE","PICKED_STATUS":"PICKED_STATUS","SMART_POOL_NUMBER":"SMART_POOL_NUMBER","INSURER_ID":"INSURER_ID","CLN_BEN_CODE":"CLN_BEN_CODE","PICKED_DATE":"PICKED_DATE"}	
 //{"POL_ID":"POL_ID","CLIENT_CHILD_BEN_CODE":"CLIENT_CHILD_BEN_CODE","CLIENT_DESCRIPTION":"CLIENT_DESCRIPTION","SMART_DESC":"SMART_DESC","SMART_POOL_NUMBER":"SMART_POOL_NUMBER","STATUS":"STATUS","CLIENT_POL_ID":"CLIENT_POL_ID","CLIENT_BEN_CODE":"CLIENT_BEN_CODE"}
///'{"REC_ID":"REC_ID","CLN_DESCRIPTION":"CLIENT_DESCRIPTION","SMART_DESC":"SMART_DESC","INSERT_DATE":"INSERT_DATE","PICKED_STATUS":"STATUS","SMART_POOL_NUMBER":"SMART_POOL_NUMBER","INSURER_ID":"INSURER_ID","CLN_BEN_CODE":"CLIENT_BEN_CODE","PICKED_DATE":"PICKED_DATE","CLN_POL_CODE":"CLIENT_POL_ID","USER_NAME":"USER_NAME","MAPPED_BY":"MAPPED_BY"}'
		
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
			final String DB_CONNECTION = "jdbc:oracle:thin:@192.168.1.228:1521/integstaging";
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
