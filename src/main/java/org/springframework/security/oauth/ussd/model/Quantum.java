package org.springframework.security.oauth.ussd.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.springframework.security.oauth.api.data.healthcare.clients.DBConSmart;

public class Quantum {

	
	public  void verifyUser(String SERVICE_ID, String MSG_ID, String MSG) throws SQLException {
        
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String updateTableSQL = "UPDATE INTERACTIVE.OPT_MSG_TYPES SET INSERT_TIME = ?, MSG_DESC = ? WHERE SERVICE_ID = ? AND MSG_ID = ? ";

		try {
			
			dbConnection = DBConSmart.getConnection();
			preparedStatement = dbConnection.prepareStatement(updateTableSQL);
 
			preparedStatement.setTimestamp(1,getCurrentTimeStamp());
			preparedStatement.setString(2, MSG);
			preparedStatement.setString(3, SERVICE_ID);
			preparedStatement.setString(4, MSG_ID);

			// execute update SQL stetement
			preparedStatement.executeUpdate();
           
			//System.out.println("Record is inserted into DBUSER table!");
 
		} catch (SQLException e) {
 
			System.out.println(e.getMessage());
 
		} finally {
 
			if (preparedStatement != null) {
				preparedStatement.close();
			}
 
			if (dbConnection != null) {
				dbConnection.close();
			}
 
		}
 
	}


	
	
	
	private  java.sql.Timestamp getCurrentTimeStamp() {
		java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime());
	}

	
	private  String getCurrentSMSTimeStamp() {
		java.util.Date today = new java.util.Date();
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(today.getTime());
	}
}
