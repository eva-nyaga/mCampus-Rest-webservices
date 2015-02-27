package org.springframework.security.oauth.ussd.model;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.springframework.security.oauth.api.data.healthcare.clients.DBConSmart;


/**
 * THIS IS THE MAIN PROCESSOR FOR THE ENTIRE APPLICATION.
 *  IT CONTAINS THE SPECIFICATIONS REQUIRED BY THE API.
 *  
 * API_PROCESSOR FUNCTION COMMUNICATES DIRECTLY WITH THE API
 */

public class dbAccess {
	

	
	public dbAccess() {
		// TODO Auto-generated constructor stub
	}
	
	
	

	
	



	
	public void insertLogData(String service_id, String msg_id, String status_msg) throws SQLException {
	        
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String insertTableSQL = "INSERT INTO INTERACTIVE.MSG_LOGS"
				+ "(SERVICE_ID, MSG_ID, STATUS_MSG, INSERT_TIME) VALUES"
				+ "(?,?,?,?)";
 
		try {
			

			dbConnection = DBConSmart.getConnection();
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);
 
			preparedStatement.setString(1, service_id);
			preparedStatement.setString(2, msg_id);
			preparedStatement.setString(3, status_msg);
			preparedStatement.setTimestamp(4,getCurrentTimeStamp());
				
			// execute insert SQL stetement
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
	

	
	public void insertNewMessages(int HOST_MSG_ID, String HOST_DATE, String HOST_LINKID, String FROM_NO, String TEXT_DATA, String DEST_TO) throws SQLException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
 
		String insertTableSQL = "INSERT INTO INTERACTIVE.MSG_IN"
				+ "(HOST_MSG_ID, HOST_DATE, DATE_RECEIVED, HOST_LINKID, FROM_NO, TEXT_DATA, DEST_TO, PROCESSED) VALUES"
				+ "(?,?,?,?,?,?,?,?)";
 
		try {
			

			dbConnection = DBConSmart.getConnection();
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);
 
			preparedStatement.setLong(1, HOST_MSG_ID);
			preparedStatement.setString(2, HOST_DATE);
			preparedStatement.setTimestamp(3,getCurrentTimeStamp());
			preparedStatement.setString(4, HOST_LINKID);
			preparedStatement.setString(5, FROM_NO);
			preparedStatement.setString(6, TEXT_DATA);
			preparedStatement.setString(7, DEST_TO);
			preparedStatement.setLong(8, 0);
				
			// execute insert SQL stetement
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
	

	
	private  void updateMessageData_MSG_IN(String smart_messageid) throws SQLException {
        
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String updateTableSQL = "UPDATE INTERACTIVE.MSG_IN SET PROCESSED = ?, DATE_PICKED = ? WHERE ID = ?";

		
		System.out.println(updateTableSQL);
		
		try {
			

			dbConnection = DBConSmart.getConnection();
			preparedStatement = dbConnection.prepareStatement(updateTableSQL);
 
			preparedStatement.setString(1, "1");
			preparedStatement.setTimestamp(2,getCurrentTimeStamp());
			preparedStatement.setString(3, smart_messageid);

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
	
	
	public  void updateOPT_MSG_TYPES(String SERVICE_ID, String MSG_ID, String MSG) throws SQLException {
        
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
