package org.springframework.security.oauth.api.data.healthcare.clients;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		for(int i = 0; i<= 50; i++){
			

		ConnectionPool cnxpool = null;
        try {                          	
        	cnxpool = new ConnectionPool("com.mysql.jdbc.Driver", "jdbc:mysql://192.180.3.14:3307/smartlink",
					"DISTRIBUTION", "distribution", 10, 10000, true);
		Connection cnx = cnxpool.getConnection();
		Statement stmt = cnx.createStatement();
		String query = "SELECT PATIENT_NAME FROM smartlink.benefit_totals WHERE medicalaid_number like '%7923694-00%';";
		ResultSet rs = stmt.executeQuery(query);

		while (rs.next()) {
		System.out.println(rs.getString(1));
		} //end while
		cnx.close();
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		}
        
        
        
        
        
        
	}

}
