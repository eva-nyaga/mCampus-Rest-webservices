package org.springframework.security.oauth.api.service.healthcare.clients;

import java.sql.Connection;

import java.sql.Blob;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.security.oauth.api.data.healthcare.clients.DBConSmartBO;
import org.springframework.security.oauth.api.data.healthcare.clients.DBConSmart;
import org.springframework.security.oauth.api.data.healthcare.clients.DBConnection;
import org.springframework.security.oauth.api.data.healthcare.clients.RequestMap;
import org.springframework.security.oauth.api.model.healthcare.clients.Activation;
import org.springframework.security.oauth.api.model.healthcare.clients.Balance;
import org.springframework.security.oauth.api.model.healthcare.clients.Cardreprint;
import org.springframework.security.oauth.api.model.healthcare.clients.Categorychange;
import org.springframework.security.oauth.api.model.healthcare.clients.Deactivation;
import org.springframework.security.oauth.api.model.healthcare.clients.Fingerprintremoval;
import org.springframework.security.oauth.api.model.healthcare.clients.Member;
import org.springframework.security.oauth.api.model.healthcare.clients.MoneyAddition;
import org.springframework.security.oauth.api.model.healthcare.clients.MoneyReduction;
import org.springframework.security.oauth.api.model.healthcare.clients.Person;
import org.springframework.security.oauth.api.model.healthcare.clients.Renewal;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;




@Service("membersService")
public class MembersService implements IMembersService {
	
	private Map<String, Member> members = new HashMap<String, Member>();
	private Map<String, MoneyAddition> moneyadditions = new HashMap<String, MoneyAddition>();
	private Map<String, MoneyReduction> moneyreductions = new HashMap<String, MoneyReduction>();
	private Map<String, Categorychange> categorychanges = new HashMap<String, Categorychange>();
	private Map<String, Activation> activations = new HashMap<String, Activation>();
	private Map<String, Deactivation> deactivations = new HashMap<String, Deactivation>();
	private Map<String, Cardreprint> cardreprints = new HashMap<String, Cardreprint>();
	private Map<String, Fingerprintremoval> fingerprintremovals = new HashMap<String, Fingerprintremoval>();
	private AtomicInteger idGen = new AtomicInteger();
	
	
	String guarded_cols[] = new String[] {
			"id",
			"created_at",
			"last_update"
	};
	/*
	String allowed_stg_members_cols[] = new String[] {
			"policyNumber",
			"providerCode",
			"membershipNumber",
			"memberName",
			"cardSerial",
			"transactionDate",
			"serviceDescription",
			"benefitCode",
			"amount",
			"dateReceived",
			"providerMemberId",
			"smartInvoiceNr",
			"status",
			"admitId",
			"tqUniqueMemNumber"			
	};
	*/
	
	
	String http_params[] = new String[] {
			"fields",
			"q",
			"orderby",
			"startindex",
			"maxresults",
			"restrictby",
			"language",
			"currencyid",
			"countryid",
			"format",
			"customerid",
			"groupby"	
	};
	

    public MembersService(){
    }
    

    
	public void MembersServiceDBAccess(String[] DBParams, String CUSTOMERID, String customercountry, int startindex, int maxresults, int status, String restrict,  String orderby){
        int lastindex  = startindex+maxresults;
        
       
		Connection connection = null;
        PreparedStatement members_statement = null;
        PreparedStatement service_tags_statement = null;
        ResultSet members_resultSet = null;
        ResultSet service_tags_resultSet = null;
        
    
        String[] SMART = getSMART_CODE(DBParams, CUSTOMERID);
        
     
        Gson gson = new Gson();
        HashMap MEMBERS_MAP =  gson.fromJson(SMART[34], new TypeToken<HashMap<String, String>>(){}.getType());
 
        try {
            connection = DBConnection.getConnection(DBParams);
            
            
			System.out.println("SELECT outer.* "+
					  " FROM (SELECT ROWNUM rn, inner.* "+
		        		" FROM (  SELECT e.* "+
		        		" FROM "+SMART[4]+"."+SMART[9]+" e "+
		        		" WHERE "+MEMBERS_MAP.get("STATUS")+" = "+status+" "+
		        		" ORDER BY "+orderby+") inner) outer "+
		        		" WHERE outer.rn >= "+startindex+" AND outer.rn < "+lastindex+"");
			
            try {
				members_statement = connection.prepareStatement("SELECT outer.* "+
						  " FROM (SELECT ROWNUM rn, inner.* "+
			        		" FROM (  SELECT e.* "+
			        		" FROM "+SMART[4]+"."+SMART[9]+" e "+
			        		" WHERE "+MEMBERS_MAP.get("STATUS")+" = "+status+" "+
			        		" ORDER BY "+orderby+") inner) outer "+
			        		" WHERE outer.rn >= "+startindex+" AND outer.rn < "+lastindex+"");
				members_resultSet = members_statement.executeQuery();
				

				if (!members_resultSet.isBeforeFirst() ) {    
				    throw new IllegalArgumentException("Query result was empty");
				   }

				while (members_resultSet.next()) {
				      String rec_Id = members_resultSet.getString("rec_Id");
                      addMember(new Member(
                			 members_resultSet.getString("pol_Id"),
                 			 members_resultSet.getString("com_Id"),
                 			 members_resultSet.getString("id_Number"),
                 			 members_resultSet.getString("surname"),
                 			 members_resultSet.getString("second_Name"),
                 			 members_resultSet.getString("third_Name"),
                 			 members_resultSet.getString("other_Names"),
                 			 members_resultSet.getDate("dob"),
                 			 members_resultSet.getString("card_Serial_Number"),
                 			 members_resultSet.getDate("join_Date"),
                 			 members_resultSet.getDate("deact_Date"),
                 			 members_resultSet.getLong("mem_Status"),
                 			 members_resultSet.getDate("modification_Date"),
                 			 members_resultSet.getDate("actioned_Date"),
                 		     members_resultSet.getLong("cut_Off_Age"),
                 			 members_resultSet.getString("kin_FName"),
                 			 members_resultSet.getString("kin_MName"),
                 			 members_resultSet.getString("kin_ONames"),
                 			 members_resultSet.getString("kin_Tel_No"),
                 			 members_resultSet.getString("kin_Email"),
                 			 members_resultSet.getString("kin_NatId"),
                 			 members_resultSet.getBlob("photo"),
                 			 members_resultSet.getString("staff_Number"),
                 			 members_resultSet.getString("nhif_Number"),
                 			 members_resultSet.getString("gender"),
                 		     members_resultSet.getString("global_Id"),
                 			 members_resultSet.getString("membership_Number"),
                 			 members_resultSet.getString("mem_Type"),
                 			 members_resultSet.getString("family_Code"),
                 			 members_resultSet.getDate("scheme_Start_Date"),
                 			 members_resultSet.getString("user_ID"),
                 			 members_resultSet.getString("cln_Pol_Number"),
                 			 members_resultSet.getString("cln_Com_Code"),
                 			 members_resultSet.getString("cln_Pol_Code"),
                 			 members_resultSet.getString("cln_Pol_Id"),
                 			 members_resultSet.getString("status"),
                 			 members_resultSet.getDate("scheme_End_Date"),
                 			 rec_Id,
                 			 members_resultSet.getString("cln_Unique_Mem_Number"),
                 			 members_resultSet.getDate("insert_Date"),
                 			 members_resultSet.getString("cln_Cat_Code"),
                 			 members_resultSet.getString("station"),
                 			 members_resultSet.getString("deptName"),
                 			 members_resultSet.getString("title"),
                 			 members_resultSet.getDate("print_Date"),
                 			 members_resultSet.getString("other_Number"),
                  			 members_resultSet.getString("print_Card"),
                  			 members_resultSet.getString("cut_Off_Exemption"),
                  			 members_resultSet.getString("region"),
                  			 members_resultSet.getString("phone_No"),
                  			 members_resultSet.getString("email"),
                  			 members_resultSet.getString("insurer_Id"),
                  			 members_resultSet.getString("roaming_Enabled"),
                  			 members_resultSet.getString("roaming_Countries")
                         ), rec_Id);


                  // System.out.println("first member"+members_resultSet.getString("member_name"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } finally {
        	
            if (members_resultSet != null) try { members_resultSet.close(); } catch (SQLException ignore) {}
        	//if (service_tags_resultSet != null) try { service_tags_resultSet.close(); } catch (SQLException ignore) {}
            if (members_statement != null) try { members_statement.close(); } catch (SQLException ignore) {}
          //  if (service_tags_statement != null) try { service_tags_statement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }
        
	}
	
/*
	public void CardreprintMembersServiceDBAccess(String[] DBParams, String CUSTOMERID, String customercountry, int startindex, int maxresults, int status, String restrict,  String orderby){
        int lastindex  = startindex+maxresults;
        
       
		Connection connection = null;
        PreparedStatement members_statement = null;
        PreparedStatement service_tags_statement = null;
        ResultSet members_resultSet = null;
        ResultSet service_tags_resultSet = null;
        
    
        String[] SMART = getSMART_CODE(DBParams, CUSTOMERID);
        
     
        Gson gson = new Gson();
        HashMap CARDREPRINTS_MAP =  gson.fromJson(SMART[36], new TypeToken<HashMap<String, String>>(){}.getType());
 
        try {
            connection = DBConnection.getConnection(DBParams);
            
            
			System.out.println("SELECT outer.* "+
					  " FROM (SELECT ROWNUM rn, inner.* "+
		        		" FROM (  SELECT e.* "+
		        		" FROM "+SMART[4]+"."+SMART[23]+" e "+
		        		" WHERE "+CARDREPRINTS_MAP.get("STATUS")+" = "+status+" "+
		        		" ORDER BY "+orderby+") inner) outer "+
		        		" WHERE outer.rn >= "+startindex+" AND outer.rn < "+lastindex+"");
			
            try {
				members_statement = connection.prepareStatement("SELECT outer.* "+
						  " FROM (SELECT ROWNUM rn, inner.* "+
			        		" FROM (  SELECT e.* "+
			        		" FROM "+SMART[4]+"."+SMART[23]+" e "+
			        		" WHERE "+CARDREPRINTS_MAP.get("STATUS")+" = "+status+" "+
			        		" ORDER BY "+orderby+") inner) outer "+
			        		" WHERE outer.rn >= "+startindex+" AND outer.rn < "+lastindex+"");
				members_resultSet = members_statement.executeQuery();
				

				if (!members_resultSet.isBeforeFirst() ) {    
				    throw new IllegalArgumentException("Query result was empty");
				   }

				while (members_resultSet.next()) {
				      String rec_Id = members_resultSet.getString("id");

                      addCardreprint(
                    		  new Cardreprint(    
   			                        members_resultSet.getString("member_No"),
   			                        members_resultSet.getString("user_Id"),
   			                        members_resultSet.getString("staff_No"),
   			                        members_resultSet.getDate("reorder_Date"),
   			                        members_resultSet.getString("reorder_Reason"),
   			                        members_resultSet.getString("card_Serial_Number"),
   			                        members_resultSet.getDate("print_Date"),
   			                        members_resultSet.getString("surname"),
   			                        members_resultSet.getString("second_Name"),
   			                        members_resultSet.getString("third_Name"),
   			                        members_resultSet.getString("other_Names"),
   			                        members_resultSet.getString("cardRequestNo"),
   			                     rec_Id
   								     )	
                    		  , rec_Id);
 
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } finally {
        	
            if (members_resultSet != null) try { members_resultSet.close(); } catch (SQLException ignore) {}
        	//if (service_tags_resultSet != null) try { service_tags_resultSet.close(); } catch (SQLException ignore) {}
            if (members_statement != null) try { members_statement.close(); } catch (SQLException ignore) {}
          //  if (service_tags_statement != null) try { service_tags_statement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }
        
	}
	*/


	

	public void ActivateMembersServiceDBAccess(String[] DBParams, String CUSTOMERID, String customercountry, int startindex, int maxresults, int status, String restrict,  String orderby){
        int lastindex  = startindex+maxresults;
        
       
		Connection connection = null;
        PreparedStatement members_statement = null;
        PreparedStatement service_tags_statement = null;
        ResultSet memberactivations_resultSet = null;
        ResultSet service_tags_resultSet = null;
        
    
        String[] SMART = getSMART_CODE(DBParams, CUSTOMERID);
        
     
        Gson gson = new Gson();
        HashMap ACTIVATIONS_MAP =  gson.fromJson(SMART[32], new TypeToken<HashMap<String, String>>(){}.getType());
 
        try {
            connection = DBConnection.getConnection(DBParams);
            String status_code = "10";
            
			System.out.println("SELECT outer.* "+
					  " FROM (SELECT ROWNUM rn, inner.* "+
		        		" FROM (  SELECT e.* "+
		        		" FROM "+SMART[4]+"."+SMART[19]+" e "+
		        		" WHERE "+ACTIVATIONS_MAP.get("PICKED_STATUS")+" = "+status+" "+
		        		" AND "+ACTIVATIONS_MAP.get("STATUS_CODE")+" = "+status_code+" "+
		        		" ORDER BY "+orderby+") inner) outer "+
		        		" WHERE outer.rn >= "+startindex+" AND outer.rn < "+lastindex+"");
			
            try {
				members_statement = connection.prepareStatement("SELECT outer.* "+
						  " FROM (SELECT ROWNUM rn, inner.* "+
			        		" FROM (  SELECT e.* "+
			        		" FROM "+SMART[4]+"."+SMART[19]+" e "+
			        		" WHERE "+ACTIVATIONS_MAP.get("PICKED_STATUS")+" = "+status+" "+
			        		" AND "+ACTIVATIONS_MAP.get("STATUS_CODE")+" = "+status_code+" "+
			        		" ORDER BY "+orderby+") inner) outer "+
			        		" WHERE outer.rn >= "+startindex+" AND outer.rn < "+lastindex+"");
				memberactivations_resultSet = members_statement.executeQuery();
				

				if (!memberactivations_resultSet.isBeforeFirst() ) {    
				    throw new IllegalArgumentException("Query result was empty");
				   }

				while (memberactivations_resultSet.next()) {
				      String rec_Id = memberactivations_resultSet.getString("id");

                      addActivation(
        				      new Activation(
        				    		  memberactivations_resultSet.getString("member_no"),
        				    		  memberactivations_resultSet.getString("user_Id"),
        				    		  memberactivations_resultSet.getString("status_Desc"),
        				    		  memberactivations_resultSet.getString("anniv"),
        				    		  memberactivations_resultSet.getString("status_Reason"),
        				    		  memberactivations_resultSet.getDate("status_Date")
        				    		  )	
                    		  , rec_Id);

                  // System.out.println("first member"+memberactivations_resultSet.getString("member_name"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } finally {
        	
            if (memberactivations_resultSet != null) try { memberactivations_resultSet.close(); } catch (SQLException ignore) {}
        	//if (service_tags_resultSet != null) try { service_tags_resultSet.close(); } catch (SQLException ignore) {}
            if (members_statement != null) try { members_statement.close(); } catch (SQLException ignore) {}
          //  if (service_tags_statement != null) try { service_tags_statement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }
        
	}
	


	public void DeactivateMembersServiceDBAccess(String[] DBParams, String CUSTOMERID, String customercountry, int startindex, int maxresults, int status, String restrict,  String orderby){
        int lastindex  = startindex+maxresults;
        
       
		Connection connection = null;
        PreparedStatement members_statement = null;
        PreparedStatement service_tags_statement = null;
        ResultSet memberdeactivations_resultSet = null;
        ResultSet service_tags_resultSet = null;
        
    
        String[] SMART = getSMART_CODE(DBParams, CUSTOMERID);
        
     
        Gson gson = new Gson();
        HashMap DEACTIVATIONS_MAP =  gson.fromJson(SMART[32], new TypeToken<HashMap<String, String>>(){}.getType());
 
        try {
            connection = DBConnection.getConnection(DBParams);
            String status_code = "11";
            
			System.out.println("SELECT outer.* "+
					  " FROM (SELECT ROWNUM rn, inner.* "+
		        		" FROM (  SELECT e.* "+
		        		" FROM "+SMART[4]+"."+SMART[19]+" e "+
		        		" WHERE "+DEACTIVATIONS_MAP.get("PICKED_STATUS")+" = "+status+" "+
		        		" AND "+DEACTIVATIONS_MAP.get("STATUS_CODE")+" = "+status_code+" "+
		        		" ORDER BY "+orderby+") inner) outer "+
		        		" WHERE outer.rn >= "+startindex+" AND outer.rn < "+lastindex+"");
			
            try {
				members_statement = connection.prepareStatement("SELECT outer.* "+
						  " FROM (SELECT ROWNUM rn, inner.* "+
			        		" FROM (  SELECT e.* "+
			        		" FROM "+SMART[4]+"."+SMART[19]+" e "+
			        		" WHERE "+DEACTIVATIONS_MAP.get("PICKED_STATUS")+" = "+status+" "+
			        		" AND "+DEACTIVATIONS_MAP.get("STATUS_CODE")+" = "+status_code+" "+
			        		" ORDER BY "+orderby+") inner) outer "+
			        		" WHERE outer.rn >= "+startindex+" AND outer.rn < "+lastindex+"");
				memberdeactivations_resultSet = members_statement.executeQuery();
				

				if (!memberdeactivations_resultSet.isBeforeFirst() ) {    
				    throw new IllegalArgumentException("Query result was empty");
				   }

				while (memberdeactivations_resultSet.next()) {
				      String rec_Id = memberdeactivations_resultSet.getString("id");

                      addDeactivation(
        				      new Deactivation(
        				    		  memberdeactivations_resultSet.getString("member_no"),
        				    		  memberdeactivations_resultSet.getString("user_Id"),
        				    		  memberdeactivations_resultSet.getString("status_Desc"),
        				    		  memberdeactivations_resultSet.getString("anniv"),
        				    		  memberdeactivations_resultSet.getString("status_Reason"),
        				    		  memberdeactivations_resultSet.getDate("status_Date")
        				    		  )	
                    		  , rec_Id);

                  // System.out.println("first member"+memberdeactivations_resultSet.getString("member_name"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } finally {
        	
            if (memberdeactivations_resultSet != null) try { memberdeactivations_resultSet.close(); } catch (SQLException ignore) {}
        	//if (service_tags_resultSet != null) try { service_tags_resultSet.close(); } catch (SQLException ignore) {}
            if (members_statement != null) try { members_statement.close(); } catch (SQLException ignore) {}
          //  if (service_tags_statement != null) try { service_tags_statement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }
        
	}
	

	

	public void FingerprintremovalMembersServiceDBAccess(String[] DBParams, String CUSTOMERID, String customercountry, int startindex, int maxresults, int status, String restrict,  String orderby){
        int lastindex  = startindex+maxresults;
        
       
		Connection connection = null;
        PreparedStatement members_statement = null;
        PreparedStatement service_tags_statement = null;
        ResultSet memberfingerprintremoval_resultSet = null;
        ResultSet service_tags_resultSet = null;
        
    
        String[] SMART = getSMART_CODE(DBParams, CUSTOMERID);
        
     
        Gson gson = new Gson();
        HashMap FINGERPRINTREMOVAL_MAP =  gson.fromJson(SMART[30], new TypeToken<HashMap<String, String>>(){}.getType());
 
        try {
            connection = DBConnection.getConnection(DBParams);
            String status_code = "11";
            
			System.out.println("SELECT outer.* "+
					  " FROM (SELECT ROWNUM rn, inner.* "+
		        		" FROM (  SELECT e.* "+
		        		" FROM "+SMART[4]+"."+SMART[41]+" e "+
		        		" WHERE "+FINGERPRINTREMOVAL_MAP.get("PICKED_STATUS")+" = "+status+" "+
		        		" ORDER BY "+orderby+") inner) outer "+
		        		" WHERE outer.rn >= "+startindex+" AND outer.rn < "+lastindex+"");
			
            try {
				members_statement = connection.prepareStatement("SELECT outer.* "+
						  " FROM (SELECT ROWNUM rn, inner.* "+
			        		" FROM (  SELECT e.* "+
			        		" FROM "+SMART[4]+"."+SMART[41]+" e "+
			        		" WHERE "+FINGERPRINTREMOVAL_MAP.get("PICKED_STATUS")+" = "+status+" "+
			        		" ORDER BY "+orderby+") inner) outer "+
			        		" WHERE outer.rn >= "+startindex+" AND outer.rn < "+lastindex+"");
				memberfingerprintremoval_resultSet = members_statement.executeQuery();
				

				if (!memberfingerprintremoval_resultSet.isBeforeFirst() ) {    
				    throw new IllegalArgumentException("Query result was empty");
				   }

				while (memberfingerprintremoval_resultSet.next()) {
				      String rec_Id = memberfingerprintremoval_resultSet.getString("id");

                      addFingerprint(
        				      new Fingerprintremoval(
        				    		  memberfingerprintremoval_resultSet.getString("member_no"),
        				    		  memberfingerprintremoval_resultSet.getString("user_Id"),
        				    		  memberfingerprintremoval_resultSet.getString("status_Desc"),
        				    		  memberfingerprintremoval_resultSet.getString("status_Code"),
        				    		  memberfingerprintremoval_resultSet.getString("status_Reason"),
        				    		  memberfingerprintremoval_resultSet.getDate("status_Date")
        				    		  )	
                    		  , rec_Id);

                  // System.out.println("first member"+memberfingerprintremoval_resultSet.getString("member_name"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } finally {
        	
            if (memberfingerprintremoval_resultSet != null) try { memberfingerprintremoval_resultSet.close(); } catch (SQLException ignore) {}
        	//if (service_tags_resultSet != null) try { service_tags_resultSet.close(); } catch (SQLException ignore) {}
            if (members_statement != null) try { members_statement.close(); } catch (SQLException ignore) {}
          //  if (service_tags_statement != null) try { service_tags_statement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }
        
	}
	
	
	
	public void MoneyadditionMembersServiceDBAccess(String[] DBParams, String CUSTOMERID, String customercountry, int startindex, int maxresults, int status, String restrict,  String orderby){
        int lastindex  = startindex+maxresults;
        
       
		Connection connection = null;
        PreparedStatement members_statement = null;
        PreparedStatement service_tags_statement = null;
        ResultSet members_resultSet = null;
        ResultSet service_tags_resultSet = null;
        
    
        String[] SMART = getSMART_CODE(DBParams, CUSTOMERID);
        
     
        Gson gson = new Gson();
        HashMap CARDREPRINTS_MAP =  gson.fromJson(SMART[29], new TypeToken<HashMap<String, String>>(){}.getType());
 
        try {
            connection = DBConnection.getConnection(DBParams);
            String returncode = "16";
            
			System.out.println("SELECT outer.* "+
					  " FROM (SELECT ROWNUM rn, inner.* "+
		        		" FROM (  SELECT e.* "+
		        		" FROM "+SMART[4]+"."+SMART[15]+" e "+
		        		" WHERE "+CARDREPRINTS_MAP.get("SMART_PICKED")+" = "+status+" "+
		        		" AND "+CARDREPRINTS_MAP.get("RETURN_CODE")+" = "+returncode+" "+
		        		" ORDER BY "+orderby+") inner) outer "+
		        		" WHERE outer.rn >= "+startindex+" AND outer.rn < "+lastindex+"");
			
            try {
            	
				members_statement = connection.prepareStatement("SELECT outer.* "+
						  " FROM (SELECT ROWNUM rn, inner.* "+
			        		" FROM (  SELECT e.* "+
			        		" FROM "+SMART[4]+"."+SMART[15]+" e "+
			        		" WHERE "+CARDREPRINTS_MAP.get("SMART_PICKED")+" = "+status+" "+
			        		" AND "+CARDREPRINTS_MAP.get("RETURN_CODE")+" = "+returncode+" "+
			        		" ORDER BY "+orderby+") inner) outer "+
			        		" WHERE outer.rn >= "+startindex+" AND outer.rn < "+lastindex+"");
				members_resultSet = members_statement.executeQuery();
				

				if (!members_resultSet.isBeforeFirst() ) {    
				    throw new IllegalArgumentException("Query result was empty");
				   }


				while (members_resultSet.next()) {
				      String rec_Id = members_resultSet.getString("rec_id");

                      addMoneyaddition(
                    		  new MoneyAddition(    
                    					members_resultSet.getString("smart_Bill_Id"),
                    					members_resultSet.getString("returned_Amount"),
                    					members_resultSet.getString("return_Code"),
                    					members_resultSet.getString("return_Reason"),
                    					members_resultSet.getDate("date_Entered"),
                    					members_resultSet.getString("member_Number"),
                    					members_resultSet.getString("anniv"),
                    					members_resultSet.getString("provider_Code"),
                    					members_resultSet.getString("invoice_Number"),
                    					members_resultSet.getString("action_Code"),
                    					members_resultSet.getString("action_Name"),
                    					members_resultSet.getString("benefit_Code"),
                    					members_resultSet.getString("user_Id"),
                    					rec_Id,
                    					members_resultSet.getString("cln_Pol_Code"),
                    					members_resultSet.getDate("invoice_Date"),
                    					members_resultSet.getString("invoice_Id")		
   								     )	
                    		  , rec_Id);


                  // System.out.println("first member"+members_resultSet.getString("member_name"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } finally {
        	
            if (members_resultSet != null) try { members_resultSet.close(); } catch (SQLException ignore) {}
        	//if (service_tags_resultSet != null) try { service_tags_resultSet.close(); } catch (SQLException ignore) {}
            if (members_statement != null) try { members_statement.close(); } catch (SQLException ignore) {}
          //  if (service_tags_statement != null) try { service_tags_statement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }
        
	}


	
	public void MoneyreductionMembersServiceDBAccess(String[] DBParams, String CUSTOMERID, String customercountry, int startindex, int maxresults, int status, String restrict,  String orderby){
        int lastindex  = startindex+maxresults;
        
       
		Connection connection = null;
        PreparedStatement members_statement = null;
        PreparedStatement service_tags_statement = null;
        ResultSet members_resultSet = null;
        ResultSet service_tags_resultSet = null;
        
        System.out.println("ppppppppPPPP");
        
   
        String[] SMART = getSMART_CODE(DBParams, CUSTOMERID);
        
        
        Gson gson = new Gson();
        HashMap CARDREPRINTS_MAP =  gson.fromJson(SMART[29], new TypeToken<HashMap<String, String>>(){}.getType());
 
  
        try {
            connection = DBConnection.getConnection(DBParams);
            String returncode = "16";
            
			System.out.println("TEST   SELECT outer.* "+
					  " FROM (SELECT ROWNUM rn, inner.* "+
		        		" FROM (  SELECT e.* "+
		        		" FROM "+SMART[4]+"."+SMART[15]+" e "+
		        		" WHERE "+CARDREPRINTS_MAP.get("SMART_PICKED")+" = "+status+" "+
		        		" AND "+CARDREPRINTS_MAP.get("RETURN_CODE")+" = "+returncode+" "+
		        		" ORDER BY "+orderby+") inner) outer "+
		        		" WHERE outer.rn >= "+startindex+" AND outer.rn < "+lastindex+"");
			
            try {
            	
				members_statement = connection.prepareStatement("SELECT outer.* "+
						  " FROM (SELECT ROWNUM rn, inner.* "+
			        		" FROM (  SELECT e.* "+
			        		" FROM "+SMART[4]+"."+SMART[15]+" e "+
			        		" WHERE "+CARDREPRINTS_MAP.get("SMART_PICKED")+" = "+status+" "+
			        		" AND "+CARDREPRINTS_MAP.get("RETURN_CODE")+" = "+returncode+" "+
			        		" ORDER BY "+orderby+") inner) outer "+
			        		" WHERE outer.rn >= "+startindex+" AND outer.rn < "+lastindex+"");
				members_resultSet = members_statement.executeQuery();
				

				if (!members_resultSet.isBeforeFirst() ) {    
				    throw new IllegalArgumentException("Query result was empty");
				   }


				while (members_resultSet.next()) {
				      String rec_Id = members_resultSet.getString("rec_id");

                      addMoneyreduction(
                    		  new MoneyReduction(    
                    					members_resultSet.getString("smart_Bill_Id"),
                    					members_resultSet.getString("returned_Amount"),
                    					members_resultSet.getString("return_Code"),
                    					members_resultSet.getString("return_Reason"),
                    					members_resultSet.getDate("date_Entered"),
                    					members_resultSet.getString("member_Number"),
                    					members_resultSet.getString("anniv"),
                    					members_resultSet.getString("provider_Code"),
                    					members_resultSet.getString("invoice_Number"),
                    					members_resultSet.getString("action_Code"),
                    					members_resultSet.getString("action_Name"),
                    					members_resultSet.getString("benefit_Code"),
                    					members_resultSet.getString("user_Id"),
                    					rec_Id,
                    					members_resultSet.getString("cln_Pol_Code"),
                    					members_resultSet.getDate("invoice_Date"),
                    					members_resultSet.getString("invoice_Id")		
   								     )	
                    		  , rec_Id);


                  // System.out.println("first member"+members_resultSet.getString("member_name"));
		 
				}
				
				System.out.println("QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } finally {
        	
            if (members_resultSet != null) try { members_resultSet.close(); } catch (SQLException ignore) {}
        	//if (service_tags_resultSet != null) try { service_tags_resultSet.close(); } catch (SQLException ignore) {}
            if (members_statement != null) try { members_statement.close(); } catch (SQLException ignore) {}
          //  if (service_tags_statement != null) try { service_tags_statement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        } 
	}

	
	
	public void MoneyCategorychangeMembersServiceDBAccess(String[] DBParams, String CUSTOMERID, String customercountry, int startindex, int maxresults, int status, String restrict,  String orderby){
        int lastindex  = startindex+maxresults;
        
       
		Connection connection = null;
        PreparedStatement members_statement = null;
        PreparedStatement service_tags_statement = null;
        ResultSet members_resultSet = null;
        ResultSet service_tags_resultSet = null;
        
        System.out.println("ppppppppPPPP");
        
   
        String[] SMART = getSMART_CODE(DBParams, CUSTOMERID);
        
        
        Gson gson = new Gson();
        HashMap CARDREPRINTS_MAP =  gson.fromJson(SMART[33], new TypeToken<HashMap<String, String>>(){}.getType());
 
  
        try {
            connection = DBConnection.getConnection(DBParams);
            String returncode = "16";
            
			System.out.println("TEST   SELECT outer.* "+
					  " FROM (SELECT ROWNUM rn, inner.* "+
		        		" FROM (  SELECT e.* "+
		        		" FROM "+SMART[4]+"."+SMART[40]+" e "+
		        		" WHERE "+CARDREPRINTS_MAP.get("SMART_PICKED")+" = "+status+" "+
		        		" ORDER BY "+orderby+") inner) outer "+
		        		" WHERE outer.rn >= "+startindex+" AND outer.rn < "+lastindex+"");
			
            try {
            	
				members_statement = connection.prepareStatement("SELECT outer.* "+
						  " FROM (SELECT ROWNUM rn, inner.* "+
			        		" FROM (  SELECT e.* "+
			        		" FROM "+SMART[4]+"."+SMART[40]+" e "+
			        		" WHERE "+CARDREPRINTS_MAP.get("SMART_PICKED")+" = "+status+" "+
			        		" ORDER BY "+orderby+") inner) outer "+
			        		" WHERE outer.rn >= "+startindex+" AND outer.rn < "+lastindex+"");
				members_resultSet = members_statement.executeQuery();
				

				if (!members_resultSet.isBeforeFirst() ) {    
				    throw new IllegalArgumentException("Query result was empty");
				   }


				while (members_resultSet.next()) {
				      String rec_Id = members_resultSet.getString("rec_id");

                      addCategorychange(
        				      new Categorychange(   
        						      members_resultSet.getString("membership_Number"),
        						      members_resultSet.getString("user_Id"),
        						      members_resultSet.getDate("start_Date"),
        						      members_resultSet.getString("scheme_Year"),
        						      members_resultSet.getString("new_Grade"),
        						      members_resultSet.getDate("end_Date"),
        						      members_resultSet.getString("cln_Pol_Code"),
        						      rec_Id )
                    		  , rec_Id);

                  // System.out.println("first member"+members_resultSet.getString("member_name"));
		 
				}
				
				System.out.println("QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } finally {
        	
            if (members_resultSet != null) try { members_resultSet.close(); } catch (SQLException ignore) {}
        	//if (service_tags_resultSet != null) try { service_tags_resultSet.close(); } catch (SQLException ignore) {}
            if (members_statement != null) try { members_statement.close(); } catch (SQLException ignore) {}
          //  if (service_tags_statement != null) try { service_tags_statement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }
    
	}

	
	public void MembersServiceDBAccessChanges(String[] DBParams, String CUSTOMERID, String customercountry, int startindex, int maxresults, int status, String restrict,  String orderby){
        int lastindex  = startindex+maxresults;
        
       
		Connection connection = null;
        PreparedStatement members_statement = null;
        PreparedStatement service_tags_statement = null;
        ResultSet members_resultSet = null;
        ResultSet service_tags_resultSet = null;
        
    
        String[] SMART = getSMART_CODE(DBParams, CUSTOMERID);
        
     
        Gson gson = new Gson();
        HashMap MEMBERS_MAP =  gson.fromJson(SMART[39], new TypeToken<HashMap<String, String>>(){}.getType());
 
        try {
            connection = DBConnection.getConnection(DBParams);
            
            
			System.out.println("SELECT outer.* "+
					  " FROM (SELECT ROWNUM rn, inner.* "+
		        		" FROM (  SELECT e.* "+
		        		" FROM "+SMART[4]+"."+SMART[38]+" e "+
		        		" WHERE "+MEMBERS_MAP.get("STATUS")+" = "+status+" "+
		        		" ORDER BY "+orderby+") inner) outer "+
		        		" WHERE outer.rn >= "+startindex+" AND outer.rn < "+lastindex+"");
			
            try {
				members_statement = connection.prepareStatement("SELECT outer.* "+
						  " FROM (SELECT ROWNUM rn, inner.* "+
			        		" FROM (  SELECT e.* "+
			        		" FROM "+SMART[4]+"."+SMART[38]+" e "+
			        		" WHERE "+MEMBERS_MAP.get("STATUS")+" = "+status+" "+
			        		" ORDER BY "+orderby+") inner) outer "+
			        		" WHERE outer.rn >= "+startindex+" AND outer.rn < "+lastindex+"");
				members_resultSet = members_statement.executeQuery();
				

				if (!members_resultSet.isBeforeFirst() ) {    
				    throw new IllegalArgumentException("Query result was empty");
				   }

				while (members_resultSet.next()) {
				      String rec_Id = members_resultSet.getString("rec_Id");
                      addMember(new Member(
                			 members_resultSet.getString("pol_Id"),
                 			 members_resultSet.getString("com_Id"),
                 			 members_resultSet.getString("id_Number"),
                 			 members_resultSet.getString("surname"),
                 			 members_resultSet.getString("second_Name"),
                 			 members_resultSet.getString("third_Name"),
                 			 members_resultSet.getString("other_Names"),
                 			 members_resultSet.getDate("dob"),
                 			 members_resultSet.getString("card_Serial_Number"),
                 			 members_resultSet.getDate("join_Date"),
                 			 members_resultSet.getDate("deact_Date"),
                 			 members_resultSet.getLong("mem_Status"),
                 			 members_resultSet.getDate("modification_Date"),
                 			 members_resultSet.getDate("actioned_Date"),
                 		     members_resultSet.getLong("cut_Off_Age"),
                 			 members_resultSet.getString("kin_FName"),
                 			 members_resultSet.getString("kin_MName"),
                 			 members_resultSet.getString("kin_ONames"),
                 			 members_resultSet.getString("kin_Tel_No"),
                 			 members_resultSet.getString("kin_Email"),
                 			 members_resultSet.getString("kin_NatId"),
                 			 members_resultSet.getBlob("photo"),
                 			 members_resultSet.getString("staff_Number"),
                 			 members_resultSet.getString("nhif_Number"),
                 			 members_resultSet.getString("gender"),
                 		     members_resultSet.getString("global_Id"),
                 			 members_resultSet.getString("membership_Number"),
                 			 members_resultSet.getString("mem_Type"),
                 			 members_resultSet.getString("family_Code"),
                 			 members_resultSet.getDate("scheme_Start_Date"),
                 			 members_resultSet.getString("user_ID"),
                 			 members_resultSet.getString("cln_Pol_Number"),
                 			 members_resultSet.getString("cln_Com_Code"),
                 			 members_resultSet.getString("cln_Pol_Code"),
                 			 members_resultSet.getString("cln_Pol_Id"),
                 			 members_resultSet.getString("status"),
                 			 members_resultSet.getDate("scheme_End_Date"),
                 			 rec_Id,
                 			 members_resultSet.getString("cln_Unique_Mem_Number"),
                 			 members_resultSet.getDate("insert_Date"),
                 			 members_resultSet.getString("cln_Cat_Code"),
                 			 members_resultSet.getString("station"),
                 			 members_resultSet.getString("deptName"),
                 			 members_resultSet.getString("title"),
                 			 members_resultSet.getDate("print_Date"),
                 			 members_resultSet.getString("other_Number"),
                  			 members_resultSet.getString("print_Card"),
                  			 members_resultSet.getString("cut_Off_Exemption"),
                  			 members_resultSet.getString("region"),
                  			 members_resultSet.getString("phone_No"),
                  			 members_resultSet.getString("email"),
                  			 members_resultSet.getString("insurer_Id"),
                  			 members_resultSet.getString("roaming_Enabled"),
                  			 members_resultSet.getString("roaming_Countries")
                         ), rec_Id);


                  // System.out.println("first member"+members_resultSet.getString("member_name"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } finally {
        	
            if (members_resultSet != null) try { members_resultSet.close(); } catch (SQLException ignore) {}
        	//if (service_tags_resultSet != null) try { service_tags_resultSet.close(); } catch (SQLException ignore) {}
            if (members_statement != null) try { members_statement.close(); } catch (SQLException ignore) {}
          //  if (service_tags_statement != null) try { service_tags_statement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }
        
	}

	private String addMembersServiceDBAccess(String[] DBParams, int id,
			Member member, String medical_allocation_cover, String staff_number, String CUSTOMERID, String country) {
		
		
		System.out.println("HAVE BEEN LOOKING FOR YOU PRECIOUS!!!!!");
	       String[] SMART = getSMART_CODE(DBParams, CUSTOMERID);
	       Gson gson = new Gson();
	       int key = 0;
	       HashMap MEMBERS_MAP =  gson.fromJson(SMART[34], new TypeToken<HashMap<String, String>>(){}.getType());

			String itemId = Long.toString(get());
			String recid = itemId;
		    //String recid = itemId.substring(itemId.length() - 15);
		    
		// TODO Auto-generated method stub
			Connection dbMemberConnection = null;
			//Connection dbConnection = null;
			PreparedStatement preparedStatement = null;
	 
			String insertTableSQL = "INSERT INTO "+SMART[4]+"."+SMART[9]+""
					+ "(" +
					" "+MEMBERS_MAP.get("TITLE")+"," +
					" "+MEMBERS_MAP.get("DEPTNAME")+"," +
					" "+MEMBERS_MAP.get("STATION")+"," +
					" "+MEMBERS_MAP.get("CLN_CAT_CODE")+"," +
					" "+MEMBERS_MAP.get("CLN_UNIQUE_MEM_NUMBER")+"," +
					" "+MEMBERS_MAP.get("REC_ID")+"," +
					" "+MEMBERS_MAP.get("CLN_POL_ID")+"," +
					" "+MEMBERS_MAP.get("CLN_POL_CODE")+"," +
					" "+MEMBERS_MAP.get("CLN_COM_CODE")+"," +
					" "+MEMBERS_MAP.get("CLN_POL_NUMBER")+"," +
					" "+MEMBERS_MAP.get("USER_ID")+"," +
					" "+MEMBERS_MAP.get("FAMILY_CODE")+"," +
					" "+MEMBERS_MAP.get("MEM_TYPE")+"," +
					" "+MEMBERS_MAP.get("GENDER")+"," +
					" "+MEMBERS_MAP.get("NHIF_NUMBER")+"," +
					" "+MEMBERS_MAP.get("STAFF_NUMBER")+"," +
					" "+MEMBERS_MAP.get("MEMBERSHIP_NUMBER")+"," +
					" "+MEMBERS_MAP.get("KIN_NATID")+"," +
					" "+MEMBERS_MAP.get("KIN_EMAIL")+"," +
					" "+MEMBERS_MAP.get("KIN_TEL_NO")+"," +
					" "+MEMBERS_MAP.get("KIN_ONAMES")+"," +
					" "+MEMBERS_MAP.get("KIN_MNAME")+"," +
					" "+MEMBERS_MAP.get("KIN_FNAME")+"," +
					" "+MEMBERS_MAP.get("CUT_OFF_AGE")+"," +
					" "+MEMBERS_MAP.get("MEM_STATUS")+"," +
				    " "+MEMBERS_MAP.get("DOB")+"," +
					" "+MEMBERS_MAP.get("OTHER_NAMES")+"," +
					" "+MEMBERS_MAP.get("THIRD_NAME")+"," +
					" "+MEMBERS_MAP.get("SECOND_NAME")+"," +
					" "+MEMBERS_MAP.get("SURNAME")+"," +
					" "+MEMBERS_MAP.get("ID_NUMBER")+"," +
					" "+MEMBERS_MAP.get("COM_ID")+"," +
					" "+MEMBERS_MAP.get("POL_ID")+"," +
					" "+MEMBERS_MAP.get("OTHER_NUMBER")+", " +
					" "+MEMBERS_MAP.get("INSERT_DATE")+", " +
					" "+MEMBERS_MAP.get("STATUS")+", " +
					" "+MEMBERS_MAP.get("PRINT_CARD")+", " +
					" "+MEMBERS_MAP.get("CUT_OFF_EXEMPTION")+", " +
					" "+MEMBERS_MAP.get("REGION")+", " +
					" "+MEMBERS_MAP.get("PHONE_NO")+", " +
					" "+MEMBERS_MAP.get("EMAIL")+", " +
					" "+MEMBERS_MAP.get("INSURER_ID")+", " +
					" "+MEMBERS_MAP.get("ROAMING_ENABLED")+", " +
					" "+MEMBERS_MAP.get("ROAMING_COUNTRIES")+" " +
					")" +
					" VALUES"
					+ "( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?  )";
	 
			try {

				dbMemberConnection = DBConnection.getConnection(DBParams);
				preparedStatement = dbMemberConnection.prepareStatement(insertTableSQL);
				preparedStatement.setString(1, member.getTitle());
				preparedStatement.setString(2, member.getDeptName());
				preparedStatement.setString(3, member.getStation());
				preparedStatement.setString(4, member.getClnCatCode());
				preparedStatement.setString(5, member.getClnUniqueMemNumber());
				preparedStatement.setString(6, recid);
				preparedStatement.setString(7, member.getClnPolId());
				preparedStatement.setString(8, member.getClnPolCode());
				preparedStatement.setString(9, member.getClnComCode());
				preparedStatement.setString(10, member.getClnPolNumber());
				preparedStatement.setString(11, member.getUserID());
				preparedStatement.setString(12, member.getFamilyCode());
				preparedStatement.setString(13, member.getMemType());
				preparedStatement.setString(14, member.getGender().toUpperCase());
				preparedStatement.setString(15, member.getNhifNumber());
				preparedStatement.setString(16, member.getStaffNumber());
				preparedStatement.setString(17, member.getMembershipNumber());
				preparedStatement.setString(18, member.getKinNatId());
				preparedStatement.setString(19, member.getKinEmail());
				preparedStatement.setString(20, member.getKinTelNo());
				preparedStatement.setString(21, member.getKinONames());
				preparedStatement.setString(22, member.getKinMName());
				preparedStatement.setString(23, member.getKinFName());
				preparedStatement.setLong(24, member.getCutOffAge());
				preparedStatement.setLong(25, member.getMemStatus());
				preparedStatement.setDate(26, member.getDob());
				preparedStatement.setString(27, member.getOtherNames());
				preparedStatement.setString(28, member.getThirdName());
				preparedStatement.setString(29, member.getSecondName());
				preparedStatement.setString(30, member.getSurname());
				preparedStatement.setString(31, member.getIdNumber());
				preparedStatement.setString(32, member.getComId());
				preparedStatement.setString(33, member.getPolId());
				preparedStatement.setString(34, member.getOtherNumber());
				preparedStatement.setDate(35, getCurrentTimeStamp());
				preparedStatement.setInt(36, 0);
				
				preparedStatement.setString(37, member.getPrintCard());
				preparedStatement.setString(38, member.getCutOffExemption());
				preparedStatement.setString(39, member.getRegion());
				preparedStatement.setString(40, member.getPhoneNo());
				preparedStatement.setString(41, member.getEmail());
				preparedStatement.setString(42, member.getInsurerId());
				preparedStatement.setString(43, member.getRoamingEnabled());
				preparedStatement.setString(44, member.getRoamingCountries());
				
				System.out.println(insertTableSQL);
				// execute insert SQL stetement
				preparedStatement.executeUpdate();


				System.out.println("Record is inserted into DBUSER table!");
				
				
	 
			} catch (SQLException e) {
	 
				System.out.println(e.getMessage());
				throw new IllegalArgumentException(e.getMessage());			
	 
			} finally {
	 
				if (preparedStatement != null) {
					try {
						preparedStatement.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
	 
				if (dbMemberConnection != null) {
					try {
						dbMemberConnection.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
	 
			}

		return recid;	
	}
	
	
	
	
	
	
	private String addCategorychangesMembersServiceDBAccess(String[] DBParams, String id, Categorychange categorychange, String CUSTOMERID, String country) {
		
		String itemId = Long.toString(get());
		String recid = Long.toString(get());
		
		System.out.println("HAVE BEEN LOOKING FOR YOU PRECIOUS!!!!!");
	       String[] SMART = getSMART_CODE(DBParams, CUSTOMERID);
	       Gson gson = new Gson();
	       int key = 0;
	       HashMap CATEGORYCHANGES_MAP =  gson.fromJson(SMART[33], new TypeToken<HashMap<String, String>>(){}.getType());

		// TODO Auto-generated method stub
			Connection dbCategorychangeConnection = null;
			//Connection dbConnection = null;
			PreparedStatement preparedStatement = null;

			
			String insertTableSQL = "INSERT INTO "+SMART[4]+"."+SMART[40]+""
					+ "(" +
					" "+CATEGORYCHANGES_MAP.get("MEMBERSHIP_NUMBER")+"," +
					" "+CATEGORYCHANGES_MAP.get("START_DATE")+"," +
					" "+CATEGORYCHANGES_MAP.get("SCHEME_YEAR")+"," +
					" "+CATEGORYCHANGES_MAP.get("NEW_GRADE")+"," +
					" "+CATEGORYCHANGES_MAP.get("END_DATE")+"," +
					" "+CATEGORYCHANGES_MAP.get("USER_ID")+"," +
					" "+CATEGORYCHANGES_MAP.get("REC_ID")+"," +
					" "+CATEGORYCHANGES_MAP.get("STATUS")+"," +
					" "+CATEGORYCHANGES_MAP.get("SMART_PICKED")+"," +
					" "+CATEGORYCHANGES_MAP.get("INSERT_DATE")+"," +
					" "+CATEGORYCHANGES_MAP.get("CLN_POL_CODE")+" " +
					")" +
					" VALUES"
					+ "( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			try {
				
				dbCategorychangeConnection = DBConnection.getConnection(DBParams);
				preparedStatement = dbCategorychangeConnection.prepareStatement(insertTableSQL);
				preparedStatement.setString(1, categorychange.getMemberNumber());
				preparedStatement.setDate(2, categorychange.getStartDate());
				preparedStatement.setString(3, categorychange.getSchemeYear());
				preparedStatement.setString(4, categorychange.getNewGrade());
				preparedStatement.setDate(5, categorychange.getEndDate());
				preparedStatement.setString(6, categorychange.getUserId());
				preparedStatement.setString(7, recid);
				preparedStatement.setString(8, "0");
				preparedStatement.setString(9, "0");
				preparedStatement.setDate(10, getCurrentTimeStamp());
     			preparedStatement.setString(11, categorychange.getClnPolCode());

				System.out.println("This is the card "+ preparedStatement);
				// execute insert SQL stetement
				preparedStatement.executeUpdate();

				System.out.println("Record is inserted into DBUSER table!");

			} catch (SQLException e) {
	 
				
				System.out.println(e.getMessage());
				throw new IllegalArgumentException(e.getMessage());
	 
			} finally {
	 
				if (preparedStatement != null) {
					try {
						preparedStatement.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
	 
				if (dbCategorychangeConnection != null) {
					try {
						dbCategorychangeConnection.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
	 
			}

		return recid;	
	}
	
	/*
	private String addCardreprintsMembersServiceDBAccess(String[] DBParams, int id, Cardreprint cardreprint, String CUSTOMERID, String country) {
		
		String itemId = Long.toString(get());
		String recid = Long.toString(get());
		
		System.out.println("HAVE BEEN LOOKING FOR YOU PRECIOUS!!!!!");
	       String[] SMART = getSMART_CODE(DBParams, CUSTOMERID);
	       Gson gson = new Gson();
	
	       HashMap REPRINTS_MAP =  gson.fromJson(SMART[36], new TypeToken<HashMap<String, String>>(){}.getType());

		// TODO Auto-generated method stub
			Connection dbCardreprintConnection = null;
			//Connection dbConnection = null;
			PreparedStatement preparedStatement = null;

			String insertTableSQL = "INSERT INTO "+SMART[4]+"."+SMART[23]+""
					+ "(" +
					" "+REPRINTS_MAP.get("ID")+"," +
					" "+REPRINTS_MAP.get("MEMBER_NO")+"," +
					" "+REPRINTS_MAP.get("STAFF_NO")+"," +
					" "+REPRINTS_MAP.get("REORDER_DATE")+"," +
					" "+REPRINTS_MAP.get("USER_ID")+"," +
					" "+REPRINTS_MAP.get("REORDER_REASON")+"," +
					" "+REPRINTS_MAP.get("CARD_SERIAL_NUMBER")+"," +
					" "+REPRINTS_MAP.get("PRINT_DATE")+"," +
					" "+REPRINTS_MAP.get("SURNAME")+"," +
					" "+REPRINTS_MAP.get("SECOND_NAME")+"," +
					" "+REPRINTS_MAP.get("THIRD_NAME")+"," +
					" "+REPRINTS_MAP.get("OTHER_NAMES")+"," +
					" "+REPRINTS_MAP.get("STATUS")+"," +
					" "+REPRINTS_MAP.get("INSERT_DATE")+"," +
					" "+REPRINTS_MAP.get("CARDREQUESTNO")+" " +
					")" +
					" VALUES"
					+ "( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			

	 
			try {

				dbCardreprintConnection = DBConnection.getConnection(DBParams);
				preparedStatement = dbCardreprintConnection.prepareStatement(insertTableSQL);
				preparedStatement.setString(1, recid);
				preparedStatement.setString(2, cardreprint.getMemberNumber());
				preparedStatement.setString(3, cardreprint.getStaffNumber());
				preparedStatement.setDate(4, cardreprint.getReorderDate());
				preparedStatement.setString(5, cardreprint.getUserId());
				preparedStatement.setString(6, cardreprint.getReorderReason());
				preparedStatement.setString(7, cardreprint.getCardSerialNumber());
				preparedStatement.setDate(8, cardreprint.getPrintDate());
				preparedStatement.setString(9, cardreprint.getSurname());
				preparedStatement.setString(10, cardreprint.getSecondName());
				preparedStatement.setString(11, cardreprint.getThirdName());
				preparedStatement.setString(12, cardreprint.getOtherNames());
				preparedStatement.setString(13, "0");
				preparedStatement.setDate(14, getCurrentTimeStamp());
				preparedStatement.setString(15, cardreprint.getCardRequestNumber());

				System.out.println("This is the card "+ preparedStatement);
				// execute insert SQL stetement
				preparedStatement .executeUpdate();
	 
				System.out.println("Record is inserted into DBUSER table!");

			} catch (SQLException e) {
	 
				System.out.println(e.getMessage());
				throw new IllegalArgumentException(e.getMessage());
	 
			} finally {
	 
				if (preparedStatement != null) {
					try {
						preparedStatement.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
	 
				if (dbCardreprintConnection != null) {
					try {
						dbCardreprintConnection.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
	 
			}

		return recid;	
	}
	*/
	
	
	private String addActivationsMembersServiceDBAccess(String[] DBParams, String id, Activation activation, String CUSTOMERID, String country) {
		
		String itemId = Long.toString(get());
		String recid = Long.toString(get());
		
		System.out.println("HAVE BEEN LOOKING FOR YOU PRECIOUS!!!!!");
	       String[] SMART = getSMART_CODE(DBParams, CUSTOMERID);
	       Gson gson = new Gson();
	       HashMap ACTIVATIONS_MAP =  gson.fromJson(SMART[32], new TypeToken<HashMap<String, String>>(){}.getType());

		// TODO Auto-generated method stub
			Connection dbActivationConnection = null;
			//Connection dbConnection = null;
			PreparedStatement preparedStatement = null;
			
			String insertTableSQL = "INSERT INTO "+SMART[4]+"."+SMART[19]+""
					+ "(" +
					" "+ACTIVATIONS_MAP.get("MEMBER_NO")+"," +
					" "+ACTIVATIONS_MAP.get("STATUS_CODE")+"," +
					" "+ACTIVATIONS_MAP.get("STATUS_DESC")+"," +
					" "+ACTIVATIONS_MAP.get("ANNIV")+"," +
					" "+ACTIVATIONS_MAP.get("STATUS_REASON")+"," +
					" "+ACTIVATIONS_MAP.get("USER_ID")+"," +
					" "+ACTIVATIONS_MAP.get("ID")+"," +
					" "+ACTIVATIONS_MAP.get("PICKED_STATUS")+"," +
					" "+ACTIVATIONS_MAP.get("INSERT_DATE")+"," +
					" "+ACTIVATIONS_MAP.get("STATUS_DATE")+" " +
					")" +
					" VALUES"
					+ "( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			try {

				dbActivationConnection = DBConnection.getConnection(DBParams);
				preparedStatement = dbActivationConnection.prepareStatement(insertTableSQL);
				preparedStatement.setString(1, activation.getMemberNumber());
				preparedStatement.setString(2, "10");
				preparedStatement.setString(3, "CARD ACTIVATION");
				preparedStatement.setString(4, activation.getAnniv());
				preparedStatement.setString(5, activation.getStatusReason());
				preparedStatement.setString(6, activation.getUserId());
				preparedStatement.setString(7, recid);
				preparedStatement.setString(8, "0");
				preparedStatement.setDate(9, getCurrentTimeStamp());
				preparedStatement.setDate(10, activation.getStatusDate());

				System.out.println("This is the activation "+ insertTableSQL);
				// execute insert SQL stetement
				preparedStatement .executeUpdate();

				System.out.println("Record is inserted into DBUSER table!");
				
				
	 
			} catch (SQLException e) {
	 
				System.out.println(e.getMessage());
				throw new IllegalArgumentException(e.getMessage());
	 
			} finally {
	 
				if (preparedStatement != null) {
					try {
						preparedStatement.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
	 
				if (dbActivationConnection != null) {
					try {
						dbActivationConnection.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
	 
			}

		return recid;	
	}
	

	private String addDeactivationsMembersServiceDBAccess(String[] DBParams, String id, Deactivation deactivation, String CUSTOMERID, String country) {
		
		String itemId = Long.toString(get());
		String recid = Long.toString(get());
		
		System.out.println("HAVE BEEN LOOKING FOR YOU PRECIOUS!!!!!");
	       String[] SMART = getSMART_CODE(DBParams, CUSTOMERID);
	       Gson gson = new Gson();

	       HashMap DEACTIVATIONS_MAP =  gson.fromJson(SMART[32], new TypeToken<HashMap<String, String>>(){}.getType());

		// TODO Auto-generated method stub
			Connection dbActivationConnection = null;
			//Connection dbConnection = null;
			PreparedStatement preparedStatement = null;
			
			String insertTableSQL = "INSERT INTO "+SMART[4]+"."+SMART[19]+""
					+ "(" +
					" "+DEACTIVATIONS_MAP.get("MEMBER_NO")+"," +
					" "+DEACTIVATIONS_MAP.get("STATUS_CODE")+"," +
					" "+DEACTIVATIONS_MAP.get("STATUS_DESC")+"," +
					" "+DEACTIVATIONS_MAP.get("ANNIV")+"," +
					" "+DEACTIVATIONS_MAP.get("STATUS_REASON")+"," +
					" "+DEACTIVATIONS_MAP.get("USER_ID")+"," +
					" "+DEACTIVATIONS_MAP.get("ID")+"," +
					" "+DEACTIVATIONS_MAP.get("PICKED_STATUS")+"," +
					" "+DEACTIVATIONS_MAP.get("INSERT_DATE")+"," +
					" "+DEACTIVATIONS_MAP.get("STATUS_DATE")+" " +
					")" +
					" VALUES"
					+ "( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			try {

				dbActivationConnection = DBConnection.getConnection(DBParams);
				preparedStatement = dbActivationConnection.prepareStatement(insertTableSQL);
				preparedStatement.setString(1, deactivation.getMemberNumber());
				preparedStatement.setString(2, "11");
				preparedStatement.setString(3, "CARD DEACTIVATION");
				preparedStatement.setString(4, deactivation.getAnniv());
				preparedStatement.setString(5, deactivation.getStatusReason());
				preparedStatement.setString(6, deactivation.getUserId());
				preparedStatement.setString(7, recid);
				preparedStatement.setString(8, "0");
				preparedStatement.setDate(9, getCurrentTimeStamp());
				preparedStatement.setDate(10, deactivation.getStatusDate());

				System.out.println("This is the deactivation "+ insertTableSQL);
				// execute insert SQL stetement
				preparedStatement .executeUpdate();

				System.out.println("Record is inserted into DBUSER table!");

			} catch (SQLException e) {
	 
				System.out.println(e.getMessage());
				throw new IllegalArgumentException(e.getMessage());
	 
			} finally {
	 
				if (preparedStatement != null) {
					try {
						preparedStatement.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
	 
				if (dbActivationConnection != null) {
					try {
						dbActivationConnection.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
	 
			}

		return recid;	
	}
	
	

	private String addFingerprintremovalsMembersServiceDBAccess(String[] DBParams, String id, Fingerprintremoval fingerprintremoval, String CUSTOMERID, String country) {
		
		String itemId = Long.toString(get());
		String recid = Long.toString(get());
		
		System.out.println("HAVE BEEN LOOKING FOR YOU PRECIOUS!!!!!");
	       String[] SMART = getSMART_CODE(DBParams, CUSTOMERID);
	       Gson gson = new Gson();

	       HashMap FINGERPRINTREMOVALS_MAP =  gson.fromJson(SMART[30], new TypeToken<HashMap<String, String>>(){}.getType());

		// TODO Auto-generated method stub
			Connection dbActivationConnection = null;
			//Connection dbConnection = null;
			PreparedStatement preparedStatement = null;
			
			String insertTableSQL = "INSERT INTO "+SMART[4]+"."+SMART[41]+""
					+ "(" +
					" "+FINGERPRINTREMOVALS_MAP.get("MEMBER_NO")+"," +
					" "+FINGERPRINTREMOVALS_MAP.get("STATUS_CODE")+"," +
					" "+FINGERPRINTREMOVALS_MAP.get("STATUS_DESC")+"," +
					" "+FINGERPRINTREMOVALS_MAP.get("STATUS_REASON")+"," +
					" "+FINGERPRINTREMOVALS_MAP.get("USER_ID")+"," +
					" "+FINGERPRINTREMOVALS_MAP.get("ID")+"," +
					" "+FINGERPRINTREMOVALS_MAP.get("INSERT_DATE")+"," +
					" "+FINGERPRINTREMOVALS_MAP.get("PICKED_STATUS")+"," +
					" "+FINGERPRINTREMOVALS_MAP.get("STATUS_DATE")+" " +
					")" +
					" VALUES"
					+ "( ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			try {

				dbActivationConnection = DBConnection.getConnection(DBParams);
				preparedStatement = dbActivationConnection.prepareStatement(insertTableSQL);
				preparedStatement.setString(1, fingerprintremoval.getMemberNumber());
				preparedStatement.setString(2, "19");
				preparedStatement.setString(3, "Finger Print Removal");
				preparedStatement.setString(4, fingerprintremoval.getStatusReason());
				preparedStatement.setString(5, fingerprintremoval.getUserId());
				preparedStatement.setString(6, recid);
				preparedStatement.setDate(7, getCurrentTimeStamp());
				preparedStatement.setString(8, "0");
				preparedStatement.setDate(9, fingerprintremoval.getStatusDate());

				System.out.println("This is the fingerprintremoval "+ insertTableSQL);
				// execute insert SQL stetement
				preparedStatement .executeUpdate();

				System.out.println("Record is inserted into DBUSER table!");
	 
			} catch (SQLException e) {
	 
				System.out.println(e.getMessage());
				throw new IllegalArgumentException(e.getMessage());
	 
			} finally {
	 
				if (preparedStatement != null) {
					try {
						preparedStatement.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
	 
				if (dbActivationConnection != null) {
					try {
						dbActivationConnection.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
	 
			}

		return recid;	
	}
	
	
	private String addMoneyadditionsMembersServiceDBAccess(String[] DBParams, String id, MoneyAddition moneyaddition, String CUSTOMERID, String country) {
		
		String itemId = Long.toString(get());
		String recid = Long.toString(get());
	    //String recid = itemId.substring(itemId.length() - 7);
	    
		System.out.println("HAVE BEEN LOOKING FOR YOU PRECIOUS!!!!!");
	       String[] SMART = getSMART_CODE(DBParams, CUSTOMERID);
	       Gson gson = new Gson();
	       HashMap MONEYADDITION_MAP =  gson.fromJson(SMART[29], new TypeToken<HashMap<String, String>>(){}.getType());

		// TODO Auto-generated method stub
			Connection dbMoneyadditionConnection = null;
			//Connection dbConnection = null;
			PreparedStatement preparedStatement = null;

			String insertTableSQL = "INSERT INTO "+SMART[4]+"."+SMART[15]+""
					+ "(" +
					" "+MONEYADDITION_MAP.get("INVOICE_DATE")+"," +
					" "+MONEYADDITION_MAP.get("CLN_POL_CODE")+"," +
					" "+MONEYADDITION_MAP.get("USER_ID")+"," +
					" "+MONEYADDITION_MAP.get("BENEFIT_CODE")+"," +
					" "+MONEYADDITION_MAP.get("ACTION_NAME")+"," +
					" "+MONEYADDITION_MAP.get("ACTION_CODE")+"," +
					" "+MONEYADDITION_MAP.get("INVOICE_NUMBER")+"," +
					" "+MONEYADDITION_MAP.get("PROVIDER_CODE")+"," +
					" "+MONEYADDITION_MAP.get("ANNIV")+"," +
					" "+MONEYADDITION_MAP.get("MEMBER_NUMBER")+"," +
					" "+MONEYADDITION_MAP.get("DATE_ENTERED")+"," +
					" "+MONEYADDITION_MAP.get("RETURN_REASON")+"," +
					" "+MONEYADDITION_MAP.get("RETURN_CODE")+"," +
					" "+MONEYADDITION_MAP.get("RETURNED_AMOUNT")+"," +
					" "+MONEYADDITION_MAP.get("SMART_BILL_ID")+"," +
					" "+MONEYADDITION_MAP.get("REC_ID")+"," +
					" "+MONEYADDITION_MAP.get("SMART_PICKED")+"," +
					" "+MONEYADDITION_MAP.get("INSERT_DATE")+"," +
					" "+MONEYADDITION_MAP.get("INVOICE_ID")+" " +
					
					")" +
					" VALUES"
					+ "( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			try {

				dbMoneyadditionConnection = DBConnection.getConnection(DBParams);
				preparedStatement = dbMoneyadditionConnection.prepareStatement(insertTableSQL);
				preparedStatement.setDate(1, moneyaddition.getInvoiceDate());
				preparedStatement.setString(2, moneyaddition.getClnPolCode());
				preparedStatement.setString(3, moneyaddition.getUserId());
				preparedStatement.setString(4, moneyaddition.getBenefitCode());
				preparedStatement.setString(5, moneyaddition.getActionName());
				preparedStatement.setString(6, moneyaddition.getActionCode());
				preparedStatement.setString(7, moneyaddition.getInvoiceNumber());
				preparedStatement.setString(8, moneyaddition.getProviderCode());
				preparedStatement.setString(9, moneyaddition.getAnniv());
				preparedStatement.setString(10, moneyaddition.getMemberNumber());
				preparedStatement.setDate(11, moneyaddition.getDateEntered());
				preparedStatement.setString(12, moneyaddition.getReturnReason());
				preparedStatement.setString(13, "16");
				preparedStatement.setString(14, moneyaddition.getReturnedAmount());
				preparedStatement.setString(15, moneyaddition.getSmartBillId());
				preparedStatement.setString(16, recid);
				preparedStatement.setString(17, "0");
				preparedStatement.setDate(18, getCurrentTimeStamp());
				preparedStatement.setString(19, moneyaddition.getInvoiceId());
				
				

				System.out.println("This is the card "+ preparedStatement);
				// execute insert SQL stetement
				preparedStatement .executeUpdate();

				System.out.println("Record is inserted into DBUSER table!");
				
				
	 
			} catch (SQLException e) {
	 
				System.out.println(e.getMessage());
				throw new IllegalArgumentException(e.getMessage());
	 
			} finally {
	 
				if (preparedStatement != null) {
					try {
						preparedStatement.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
	 
				if (dbMoneyadditionConnection != null) {
					try {
						dbMoneyadditionConnection.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
	 
			}

		return recid;	
	}
	
	

	
	private String addMoneyreductionsMembersServiceDBAccess(String[] DBParams, String id, MoneyReduction moneyReduction, String CUSTOMERID, String country) {
		
		String itemId = Long.toString(get());
		String recid = Long.toString(get());
		
		
		System.out.println("HAVE BEEN LOOKING FOR YOU PRECIOUS!!!!!");
	       String[] SMART = getSMART_CODE(DBParams, CUSTOMERID);
	       Gson gson = new Gson();

	       HashMap MONEYADDITION_MAP =  gson.fromJson(SMART[29], new TypeToken<HashMap<String, String>>(){}.getType());

		// TODO Auto-generated method stub
			Connection dbMoneyadditionConnection = null;
			//Connection dbConnection = null;
			PreparedStatement preparedStatement = null;

			String insertTableSQL = "INSERT INTO "+SMART[4]+"."+SMART[15]+""
					+ "(" +
					" "+MONEYADDITION_MAP.get("INVOICE_DATE")+"," +
					" "+MONEYADDITION_MAP.get("CLN_POL_CODE")+"," +
					" "+MONEYADDITION_MAP.get("USER_ID")+"," +
					" "+MONEYADDITION_MAP.get("BENEFIT_CODE")+"," +
					" "+MONEYADDITION_MAP.get("ACTION_NAME")+"," +
					" "+MONEYADDITION_MAP.get("ACTION_CODE")+"," +
					" "+MONEYADDITION_MAP.get("INVOICE_NUMBER")+"," +
					" "+MONEYADDITION_MAP.get("PROVIDER_CODE")+"," +
					" "+MONEYADDITION_MAP.get("ANNIV")+"," +
					" "+MONEYADDITION_MAP.get("MEMBER_NUMBER")+"," +
					" "+MONEYADDITION_MAP.get("DATE_ENTERED")+"," +
					" "+MONEYADDITION_MAP.get("RETURN_REASON")+"," +
					" "+MONEYADDITION_MAP.get("RETURN_CODE")+"," +
					" "+MONEYADDITION_MAP.get("RETURNED_AMOUNT")+"," +
					" "+MONEYADDITION_MAP.get("SMART_BILL_ID")+"," +
					" "+MONEYADDITION_MAP.get("REC_ID")+"," +
					" "+MONEYADDITION_MAP.get("SMART_PICKED")+"," +
					" "+MONEYADDITION_MAP.get("INSERT_DATE")+"," +
					" "+MONEYADDITION_MAP.get("INVOICE_ID")+" " +
					
					")" +
					" VALUES"
					+ "( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			try {

				dbMoneyadditionConnection = DBConnection.getConnection(DBParams);
				preparedStatement = dbMoneyadditionConnection.prepareStatement(insertTableSQL);
				preparedStatement.setDate(1, moneyReduction.getInvoiceDate());
				preparedStatement.setString(2, moneyReduction.getClnPolCode());
				preparedStatement.setString(3, moneyReduction.getUserId());
				preparedStatement.setString(4, moneyReduction.getBenefitCode());
				preparedStatement.setString(5, moneyReduction.getActionName());
				preparedStatement.setString(6, moneyReduction.getActionCode());
				preparedStatement.setString(7, moneyReduction.getInvoiceNumber());
				preparedStatement.setString(8, moneyReduction.getProviderCode());
				preparedStatement.setString(9, moneyReduction.getAnniv());
				preparedStatement.setString(10, moneyReduction.getMemberNumber());
				preparedStatement.setDate(11, moneyReduction.getDateEntered());
				preparedStatement.setString(12, moneyReduction.getReturnReason());
				preparedStatement.setString(13, "17");
				preparedStatement.setString(14, "-"+moneyReduction.getReturnedAmount());
				preparedStatement.setString(15, moneyReduction.getSmartBillId());
				preparedStatement.setString(16, recid);
				preparedStatement.setString(17, "0");
				preparedStatement.setDate(18, getCurrentTimeStamp());
				preparedStatement.setString(19, moneyReduction.getInvoiceId());

				System.out.println("This is the card "+ preparedStatement);
				// execute insert SQL stetement
				preparedStatement .executeUpdate();

	 
			} catch (SQLException e) {
	 
				System.out.println(e.getMessage());
				throw new IllegalArgumentException(e.getMessage());
	 
			} finally {
	 
				if (preparedStatement != null) {
					try {
						preparedStatement.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
	 
				if (dbMoneyadditionConnection != null) {
					try {
						dbMoneyadditionConnection.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
	 
			}

		return recid;	
	}
	

	private String updateMembersServiceDBAccess(String[] DBParams, String id,
			Member member, String medical_allocation_cover, String staff_number, String CUSTOMERID, String country) {
	     
		Gson gson = new Gson();
	    int key = 0;
	       
		String itemId = Long.toString(get());
		String recid = itemId;
		
		System.out.println("HAVE BEEN LOOKING FOR YOU PRECIOUS!!!!!");
	       String[] SMART = getSMART_CODE(DBParams, CUSTOMERID);

	       HashMap MEMBERS_MAP =  gson.fromJson(SMART[39], new TypeToken<HashMap<String, String>>(){}.getType());

		// TODO Auto-generated method stub
			Connection dbMemberConnection = null;
			//Connection dbConnection = null;
			PreparedStatement preparedStatement = null;

			String insertTableSQL = "INSERT INTO "+SMART[4]+"."+SMART[38]+""
					+ "(" +
					" "+MEMBERS_MAP.get("TITLE")+"," +
					" "+MEMBERS_MAP.get("DEPTNAME")+"," +
					" "+MEMBERS_MAP.get("STATION")+"," +
					" "+MEMBERS_MAP.get("CLN_CAT_CODE")+"," +
					" "+MEMBERS_MAP.get("CLN_UNIQUE_MEM_NUMBER")+"," +
					" "+MEMBERS_MAP.get("REC_ID")+"," +
					" "+MEMBERS_MAP.get("CLN_POL_ID")+"," +
					" "+MEMBERS_MAP.get("CLN_POL_CODE")+"," +
					" "+MEMBERS_MAP.get("CLN_COM_CODE")+"," +
					" "+MEMBERS_MAP.get("CLN_POL_NUMBER")+"," +
					" "+MEMBERS_MAP.get("USER_ID")+"," +
					" "+MEMBERS_MAP.get("FAMILY_CODE")+"," +
					" "+MEMBERS_MAP.get("MEM_TYPE")+"," +
					" "+MEMBERS_MAP.get("GENDER")+"," +
					" "+MEMBERS_MAP.get("NHIF_NUMBER")+"," +
					" "+MEMBERS_MAP.get("STAFF_NUMBER")+"," +
					" "+MEMBERS_MAP.get("MEMBERSHIP_NUMBER")+"," +
					" "+MEMBERS_MAP.get("KIN_NATID")+"," +
					" "+MEMBERS_MAP.get("KIN_EMAIL")+"," +
					" "+MEMBERS_MAP.get("KIN_TEL_NO")+"," +
					" "+MEMBERS_MAP.get("KIN_ONAMES")+"," +
					" "+MEMBERS_MAP.get("KIN_MNAME")+"," +
					" "+MEMBERS_MAP.get("KIN_FNAME")+"," +
					" "+MEMBERS_MAP.get("CUT_OFF_AGE")+"," +
					" "+MEMBERS_MAP.get("MEM_STATUS")+"," +
				    " "+MEMBERS_MAP.get("DOB")+"," +
					" "+MEMBERS_MAP.get("OTHER_NAMES")+"," +
					" "+MEMBERS_MAP.get("THIRD_NAME")+"," +
					" "+MEMBERS_MAP.get("SECOND_NAME")+"," +
					" "+MEMBERS_MAP.get("SURNAME")+"," +
					" "+MEMBERS_MAP.get("ID_NUMBER")+"," +
					" "+MEMBERS_MAP.get("COM_ID")+"," +
					" "+MEMBERS_MAP.get("POL_ID")+"," +
					" "+MEMBERS_MAP.get("OTHER_NUMBER")+", " +
					" "+MEMBERS_MAP.get("INSERT_DATE")+", " +
					" "+MEMBERS_MAP.get("STATUS")+" " +
					")" +
					" VALUES"
					+ "( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?  )";
	 
			try {

				dbMemberConnection = DBConnection.getConnection(DBParams);
				preparedStatement = dbMemberConnection.prepareStatement(insertTableSQL);
				preparedStatement.setString(1, member.getTitle());
				preparedStatement.setString(2, member.getDeptName());
				preparedStatement.setString(3, member.getStation());
				preparedStatement.setString(4, member.getClnCatCode());
				preparedStatement.setString(5, member.getClnUniqueMemNumber());
				preparedStatement.setString(6, recid);
				preparedStatement.setString(7, member.getClnPolId());
				preparedStatement.setString(8, member.getClnPolCode());
				preparedStatement.setString(9, member.getClnComCode());
				preparedStatement.setString(10, member.getClnPolNumber());
				preparedStatement.setString(11, member.getUserID());
				preparedStatement.setString(12, member.getFamilyCode());
				preparedStatement.setString(13, member.getMemType());
				preparedStatement.setString(14, member.getGender());
				preparedStatement.setString(15, member.getNhifNumber());
				preparedStatement.setString(16, member.getStaffNumber());
				preparedStatement.setString(17, member.getMembershipNumber());
				preparedStatement.setString(18, member.getKinNatId());
				preparedStatement.setString(19, member.getKinEmail());
				preparedStatement.setString(20, member.getKinTelNo());
				preparedStatement.setString(21, member.getKinONames());
				preparedStatement.setString(22, member.getKinMName());
				preparedStatement.setString(23, member.getKinFName());
				preparedStatement.setLong(24, member.getCutOffAge());
				preparedStatement.setLong(25, member.getMemStatus());
				preparedStatement.setDate(26, member.getDob());
				preparedStatement.setString(27, member.getOtherNames());
				preparedStatement.setString(28, member.getThirdName());
				preparedStatement.setString(29, member.getSecondName());
				preparedStatement.setString(30, member.getSurname());
				preparedStatement.setString(31, member.getIdNumber());
				preparedStatement.setString(32, member.getComId());
				preparedStatement.setString(33, member.getPolId());
				preparedStatement.setString(34, member.getOtherNumber());
				preparedStatement.setDate(35, getCurrentTimeStamp());
				preparedStatement.setInt(36, 0);
				// execute insert SQL stetement
				preparedStatement .executeUpdate();


				System.out.println("Record is inserted into DBUSER table!");
				
				
	 
			} catch (SQLException e) {
	 
				System.out.println(e.getMessage());
				throw new IllegalArgumentException(e.getMessage());			
	 
			} finally {
	 
				if (preparedStatement != null) {
					try {
						preparedStatement.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
	 
				if (dbMemberConnection != null) {
					try {
						dbMemberConnection.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
	 
			}
  
		return id;	
	}
	
	
	
	

	public void SingleMembersServiceDBAccess(String[] DBParams, String member_id, String CUSTOMERID, String country){
        Connection connection = null;
        PreparedStatement members_statement = null;
        PreparedStatement service_tags_statement = null;
        ResultSet members_resultSet = null;
        ResultSet service_tags_resultSet = null;
        String SINGLE_SQL_LIST = null;

        String[] SMART = getSMART_CODE(DBParams, CUSTOMERID);
        
        Gson gson = new Gson();
       HashMap MEMBERS_MAP =  gson.fromJson(SMART[34], new TypeToken<HashMap<String, String>>(){}.getType());
        
        SINGLE_SQL_LIST = "select * from ( Select * from "+SMART[4]+"."+SMART[9]+" where "+MEMBERS_MAP.get("REC_ID")+" = "+member_id+" order by join_date asc ) where rownum <= 50";

        try {
            connection = DBConnection.getConnection(DBParams);
            try {
				members_statement = connection.prepareStatement(SINGLE_SQL_LIST);
				members_resultSet = members_statement.executeQuery();
				
		
				 
				if (!members_resultSet.isBeforeFirst() ) {    
				    throw new IllegalArgumentException("No member with id "+member_id+ " was found");
				   }

				while (members_resultSet.next()) {
				      String rec_Id = members_resultSet.getString("rec_Id");

                      addMember(new Member(
                			 members_resultSet.getString("pol_Id"),
                 			 members_resultSet.getString("com_Id"),
                 			 members_resultSet.getString("id_Number"),
                 			 members_resultSet.getString("surname"),
                 			 members_resultSet.getString("second_Name"),
                 			 members_resultSet.getString("third_Name"),
                 			 members_resultSet.getString("other_Names"),
                 			 members_resultSet.getDate("dob"),
                 			 members_resultSet.getString("card_Serial_Number"),
                 			 members_resultSet.getDate("join_Date"),
                 			 members_resultSet.getDate("deact_Date"),
                 			 members_resultSet.getLong("mem_Status"),
                 			 members_resultSet.getDate("modification_Date"),
                 			 members_resultSet.getDate("actioned_Date"),
                 		     members_resultSet.getLong("cut_Off_Age"),
                 			 members_resultSet.getString("kin_FName"),
                 			 members_resultSet.getString("kin_MName"),
                 			 members_resultSet.getString("kin_ONames"),
                 			 members_resultSet.getString("kin_Tel_No"),
                 			 members_resultSet.getString("kin_Email"),
                 			 members_resultSet.getString("kin_NatId"),
                 			 members_resultSet.getBlob("photo"),
                 			 members_resultSet.getString("staff_Number"),
                 			 members_resultSet.getString("nhif_Number"),
                 			 members_resultSet.getString("gender"),
                 		     members_resultSet.getString("global_Id"),
                 			 members_resultSet.getString("membership_Number"),
                 			 members_resultSet.getString("mem_Type"),
                 			 members_resultSet.getString("family_Code"),
                 			 members_resultSet.getDate("scheme_Start_Date"),
                 			 members_resultSet.getString("user_ID"),
                 			 members_resultSet.getString("cln_Pol_Number"),
                 			 members_resultSet.getString("cln_Com_Code"),
                 			 members_resultSet.getString("cln_Pol_Code"),
                 			 members_resultSet.getString("cln_Pol_Id"),
                 			 members_resultSet.getString("status"),
                 			 members_resultSet.getDate("scheme_End_Date"),
                 			 rec_Id,
                 			 members_resultSet.getString("cln_Unique_Mem_Number"),
                 			 members_resultSet.getDate("insert_Date"),
                 			 members_resultSet.getString("cln_Cat_Code"),
                 			 members_resultSet.getString("station"),
                 			 members_resultSet.getString("deptName"),
                 			 members_resultSet.getString("title"),
                 			 members_resultSet.getDate("print_Date"),
                 			 members_resultSet.getString("other_Number"),
                  			 members_resultSet.getString("print_Card"),
                  			 members_resultSet.getString("cut_Off_Exemption"),
                  			 members_resultSet.getString("region"),
                  			 members_resultSet.getString("phone_No"),
                  			 members_resultSet.getString("email"),
                  			 members_resultSet.getString("insurer_Id"),
                  			 members_resultSet.getString("roaming_Enabled"),
                  			 members_resultSet.getString("roaming_Countries")
                         ), rec_Id);


				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } finally {
        	
            if (members_resultSet != null) try { members_resultSet.close(); } catch (SQLException ignore) {}
        	if (members_statement != null) try { members_statement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }
        
	}
	
	

	public void SingleMembersServiceDBAccessChanges(String[] DBParams, String member_id, String CUSTOMERID, String country){
        Connection connection = null;
        PreparedStatement members_statement = null;
        PreparedStatement service_tags_statement = null;
        ResultSet members_resultSet = null;
        ResultSet service_tags_resultSet = null;
        String SINGLE_SQL_LIST = null;

        String[] SMART = getSMART_CODE(DBParams, CUSTOMERID);
        
        Gson gson = new Gson();
       HashMap MEMBERS_MAP =  gson.fromJson(SMART[39], new TypeToken<HashMap<String, String>>(){}.getType());
        
        SINGLE_SQL_LIST = "select * from ( Select * from "+SMART[4]+"."+SMART[38]+" where "+MEMBERS_MAP.get("REC_ID")+" = "+member_id+" order by join_date asc ) where rownum <= 50";

        try {
            connection = DBConnection.getConnection(DBParams);
            try {
				members_statement = connection.prepareStatement(SINGLE_SQL_LIST);
				members_resultSet = members_statement.executeQuery();
				
		
				 
				if (!members_resultSet.isBeforeFirst() ) {    
				    throw new IllegalArgumentException("No member with id "+member_id+ " was found");
				   }

				while (members_resultSet.next()) {
				      String rec_Id = members_resultSet.getString("rec_Id");

                      addMember(new Member(
                			 members_resultSet.getString("pol_Id"),
                 			 members_resultSet.getString("com_Id"),
                 			 members_resultSet.getString("id_Number"),
                 			 members_resultSet.getString("surname"),
                 			 members_resultSet.getString("second_Name"),
                 			 members_resultSet.getString("third_Name"),
                 			 members_resultSet.getString("other_Names"),
                 			 members_resultSet.getDate("dob"),
                 			 members_resultSet.getString("card_Serial_Number"),
                 			 members_resultSet.getDate("join_Date"),
                 			 members_resultSet.getDate("deact_Date"),
                 			 members_resultSet.getLong("mem_Status"),
                 			 members_resultSet.getDate("modification_Date"),
                 			 members_resultSet.getDate("actioned_Date"),
                 		     members_resultSet.getLong("cut_Off_Age"),
                 			 members_resultSet.getString("kin_FName"),
                 			 members_resultSet.getString("kin_MName"),
                 			 members_resultSet.getString("kin_ONames"),
                 			 members_resultSet.getString("kin_Tel_No"),
                 			 members_resultSet.getString("kin_Email"),
                 			 members_resultSet.getString("kin_NatId"),
                 			 members_resultSet.getBlob("photo"),
                 			 members_resultSet.getString("staff_Number"),
                 			 members_resultSet.getString("nhif_Number"),
                 			 members_resultSet.getString("gender"),
                 		     members_resultSet.getString("global_Id"),
                 			 members_resultSet.getString("membership_Number"),
                 			 members_resultSet.getString("mem_Type"),
                 			 members_resultSet.getString("family_Code"),
                 			 members_resultSet.getDate("scheme_Start_Date"),
                 			 members_resultSet.getString("user_ID"),
                 			 members_resultSet.getString("cln_Pol_Number"),
                 			 members_resultSet.getString("cln_Com_Code"),
                 			 members_resultSet.getString("cln_Pol_Code"),
                 			 members_resultSet.getString("cln_Pol_Id"),
                 			 members_resultSet.getString("status"),
                 			 members_resultSet.getDate("scheme_End_Date"),
                 			 rec_Id,
                 			 members_resultSet.getString("cln_Unique_Mem_Number"),
                 			 members_resultSet.getDate("insert_Date"),
                 			 members_resultSet.getString("cln_Cat_Code"),
                 			 members_resultSet.getString("station"),
                 			 members_resultSet.getString("deptName"),
                 			 members_resultSet.getString("title"),
                 			 members_resultSet.getDate("print_Date"),
                 			 members_resultSet.getString("other_Number"),
                  			 members_resultSet.getString("print_Card"),
                  			 members_resultSet.getString("cut_Off_Exemption"),
                  			 members_resultSet.getString("region"),
                  			 members_resultSet.getString("phone_No"),
                  			 members_resultSet.getString("email"),
                  			 members_resultSet.getString("insurer_Id"),
                  			 members_resultSet.getString("roaming_Enabled"),
                  			 members_resultSet.getString("roaming_Countries")
                         ), rec_Id);


				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } finally {
        	
            if (members_resultSet != null) try { members_resultSet.close(); } catch (SQLException ignore) {}
        	if (members_statement != null) try { members_statement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }
        
	}
	
	public void SingleMembersDeleteServiceDBAccess(String[] DBParams, String member_id, String CUSTOMERID, String country){
       
		Connection connection = null;
		PreparedStatement preparedStatement = null;
        String[] SMART = getSMART_CODE(DBParams, CUSTOMERID);
        Gson gson = new Gson();
        HashMap MEMBERS_MAP =  gson.fromJson(SMART[34], new TypeToken<HashMap<String, String>>(){}.getType());
		String deleteSQL = " DELETE FROM "+SMART[4]+"."+SMART[9]+" where "+MEMBERS_MAP.get("REC_ID")+" =  ? AND STATUS = ?";
 
		try {
		    connection = DBConnection.getConnection(DBParams);
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, member_id);
			preparedStatement.setInt(2, 0);
 
			System.out.println(deleteSQL);
			// execute delete SQL stetement
			preparedStatement.executeUpdate();
 
			System.out.println("Record is deleted!");
 
		} catch (SQLException e) {
 
			System.out.println(e.getMessage());
			throw new IllegalArgumentException(e.getMessage());
 
		} finally {
 
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
 
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
 
		}
		
   
	}
	
	
	public void SearchMembersServiceDBAccess(String[] DBParams, String q, String CUSTOMERID, String country, int startindex, int maxresults, int status, String restrict,  String orderby){
		int lastindex  = startindex+maxresults;
		Connection connection = null;
        PreparedStatement members_statement = null;
        PreparedStatement service_tags_statement = null;
        ResultSet members_resultSet = null;
        ResultSet service_tags_resultSet = null;
        
       String[] SMART = getSMART_CODE(DBParams, CUSTOMERID);
       Gson gson = new Gson();
       HashMap MEMBERS_MAP =  gson.fromJson(SMART[34], new TypeToken<HashMap<String, String>>(){}.getType());


     
        final String SEARCH_SQL_LIST = " SELECT outer.*  FROM ( "+
        		" SELECT ROWNUM rn, inner.*  FROM ( "+  
        		" SELECT e.*  FROM "+SMART[4]+"."+SMART[9]+" e "+  
        		" WHERE "+MEMBERS_MAP.get("STATUS")+" = "+status+" AND "+
        		" ( "+
        		" "+MEMBERS_MAP.get("TITLE")+" LIKE '%"+q+"%' OR  "+ 
        		" "+MEMBERS_MAP.get("DEPTNAME")+" LIKE '%"+q+"%' OR  "+ 
        		" "+MEMBERS_MAP.get("STATION")+" LIKE '%"+q+"%' OR  "+ 
        		" "+MEMBERS_MAP.get("CLN_CAT_CODE")+" LIKE '%"+q+"%' OR  "+ 
        		" "+MEMBERS_MAP.get("CLN_UNIQUE_MEM_NUMBER")+" LIKE '%"+q+"%' OR  "+ 
        		" "+MEMBERS_MAP.get("REC_ID")+" LIKE '%"+q+"%' OR  "+ 
        		" "+MEMBERS_MAP.get("SCHEME_END_DATE")+" LIKE '%"+q+"%' OR  "+ 
        		" "+MEMBERS_MAP.get("CLN_POL_ID")+" LIKE '%"+q+"%' OR  "+ 
        		" "+MEMBERS_MAP.get("CLN_POL_CODE")+" LIKE '%"+q+"%' OR  "+ 
        		" "+MEMBERS_MAP.get("CLN_COM_CODE")+" LIKE '%"+q+"%' OR  "+ 
        		" "+MEMBERS_MAP.get("CLN_POL_NUMBER")+" LIKE '%"+q+"%' OR  "+ 
        		" "+MEMBERS_MAP.get("USER_ID")+" LIKE '%"+q+"%' OR  "+ 
        		" "+MEMBERS_MAP.get("SCHEME_START_DATE")+" LIKE '%"+q+"%' OR  "+ 
        		" "+MEMBERS_MAP.get("FAMILY_CODE")+" LIKE '%"+q+"%' OR  "+ 
        		" "+MEMBERS_MAP.get("MEM_TYPE")+" LIKE '%"+q+"%' OR  "+ 
        		" "+MEMBERS_MAP.get("GENDER")+" LIKE '%"+q+"%' OR  "+ 
        		" "+MEMBERS_MAP.get("NHIF_NUMBER")+" LIKE '%"+q+"%' OR  "+ 
        		" "+MEMBERS_MAP.get("STAFF_NUMBER")+" LIKE '%"+q+"%' OR  "+ 
        		" "+MEMBERS_MAP.get("MEMBERSHIP_NUMBER")+" LIKE '%"+q+"%' OR  "+ 
        		" "+MEMBERS_MAP.get("KIN_NATID")+" LIKE '%"+q+"%' OR  "+ 
        		" "+MEMBERS_MAP.get("KIN_EMAIL")+" LIKE '%"+q+"%' OR  "+ 
        		" "+MEMBERS_MAP.get("KIN_TEL_NO")+" LIKE '%"+q+"%' OR  "+ 
        		" "+MEMBERS_MAP.get("KIN_ONAMES")+" LIKE '%"+q+"%' OR  "+ 
        		" "+MEMBERS_MAP.get("KIN_MNAME")+" LIKE '%"+q+"%' OR  "+ 
        		" "+MEMBERS_MAP.get("KIN_FNAME")+" LIKE '%"+q+"%' OR  "+ 
        		" "+MEMBERS_MAP.get("CUT_OFF_AGE")+" LIKE '%"+q+"%' OR  "+ 
        		" "+MEMBERS_MAP.get("MEM_STATUS")+" LIKE '%"+q+"%' OR  "+ 
        		" "+MEMBERS_MAP.get("CARD_SERIAL_NUMBER")+" LIKE '%"+q+"%' OR  "+ 
        		" "+MEMBERS_MAP.get("DOB")+" LIKE '%"+q+"%' OR  "+ 
        		" "+MEMBERS_MAP.get("OTHER_NAMES")+" LIKE '%"+q+"%' OR  "+ 
        		" "+MEMBERS_MAP.get("THIRD_NAME")+" LIKE '%"+q+"%' OR  "+ 
        		" "+MEMBERS_MAP.get("SECOND_NAME")+" LIKE '%"+q+"%' OR  "+ 
        		" "+MEMBERS_MAP.get("SURNAME")+" LIKE '%"+q+"%' OR  "+ 
        		" "+MEMBERS_MAP.get("ID_NUMBER")+" LIKE '%"+q+"%' OR  "+ 
        		" "+MEMBERS_MAP.get("COM_ID")+" LIKE '%"+q+"%' OR  "+ 
        		" "+MEMBERS_MAP.get("POL_ID")+" LIKE '%"+q+"%' OR  "+ 
        		" "+MEMBERS_MAP.get("OTHER_NUMBER")+" LIKE '%"+q+"%'   "+ 
        		" "+MEMBERS_MAP.get("PRINT_CARD")+" LIKE '%"+q+"%' OR  "+ 
        		" "+MEMBERS_MAP.get("CUT_OFF_EXEMPTION")+" LIKE '%"+q+"%' OR  "+ 
        		" "+MEMBERS_MAP.get("REGION")+" LIKE '%"+q+"%' OR  "+ 
        		" "+MEMBERS_MAP.get("PHONE_NO")+" LIKE '%"+q+"%' OR  "+ 
        		" "+MEMBERS_MAP.get("EMAIL")+" LIKE '%"+q+"%' OR  "+ 
        		" "+MEMBERS_MAP.get("INSURER_ID")+" LIKE '%"+q+"%' OR  "+ 
        		" "+MEMBERS_MAP.get("ROAMING_ENABLED")+" LIKE '%"+q+"%' OR  "+ 
        		" "+MEMBERS_MAP.get("ROAMING_COUNTRIES")+" LIKE '%"+q+"%' OR  "+ 
        		" ) ORDER BY "+orderby+"  "+
        		" ) inner) outer  "+ 
        		" WHERE outer.rn >= "+startindex+" AND outer.rn < "+lastindex+" ";
    
        
        System.out.println(SEARCH_SQL_LIST);
        try {
        	
            connection = DBConnection.getConnection(DBParams);
            
            try {
				members_statement = connection.prepareStatement(SEARCH_SQL_LIST);
				
			    System.out.println("--------------------------------------HERE---------------------------------------");
			    System.out.println(SEARCH_SQL_LIST);
			    
				members_resultSet = members_statement.executeQuery();
				
				if (!members_resultSet.isBeforeFirst() ) {    
				    throw new IllegalArgumentException("Your search - "+q+" - did not match any member. "+"Suggestions:"+
				    		"Make sure all words are spelled correctly."+
				    		"Try different keywords."+
				    		"Try more general keywords.");
				   }
				

			    
			    
				while (members_resultSet.next()) {
				      String rec_Id = members_resultSet.getString("rec_Id");

                      addMember(new Member(
                			 members_resultSet.getString("pol_Id"),
                 			 members_resultSet.getString("com_Id"),
                 			 members_resultSet.getString("id_Number"),
                 			 members_resultSet.getString("surname"),
                 			 members_resultSet.getString("second_Name"),
                 			 members_resultSet.getString("third_Name"),
                 			 members_resultSet.getString("other_Names"),
                 			 members_resultSet.getDate("dob"),
                 			 members_resultSet.getString("card_Serial_Number"),
                 			 members_resultSet.getDate("join_Date"),
                 			 members_resultSet.getDate("deact_Date"),
                 			 members_resultSet.getLong("mem_Status"),
                 			 members_resultSet.getDate("modification_Date"),
                 			 members_resultSet.getDate("actioned_Date"),
                 		     members_resultSet.getLong("cut_Off_Age"),
                 			 members_resultSet.getString("kin_FName"),
                 			 members_resultSet.getString("kin_MName"),
                 			 members_resultSet.getString("kin_ONames"),
                 			 members_resultSet.getString("kin_Tel_No"),
                 			 members_resultSet.getString("kin_Email"),
                 			 members_resultSet.getString("kin_NatId"),
                 			 members_resultSet.getBlob("photo"),
                 			 members_resultSet.getString("staff_Number"),
                 			 members_resultSet.getString("nhif_Number"),
                 			 members_resultSet.getString("gender"),
                 		     members_resultSet.getString("global_Id"),
                 			 members_resultSet.getString("membership_Number"),
                 			 members_resultSet.getString("mem_Type"),
                 			 members_resultSet.getString("family_Code"),
                 			 members_resultSet.getDate("scheme_Start_Date"),
                 			 members_resultSet.getString("user_ID"),
                 			 members_resultSet.getString("cln_Pol_Number"),
                 			 members_resultSet.getString("cln_Com_Code"),
                 			 members_resultSet.getString("cln_Pol_Code"),
                 			 members_resultSet.getString("cln_Pol_Id"),
                 			 members_resultSet.getString("status"),
                 			 members_resultSet.getDate("scheme_End_Date"),
                 			 rec_Id,
                 			 members_resultSet.getString("cln_Unique_Mem_Number"),
                 			 members_resultSet.getDate("insert_Date"),
                 			 members_resultSet.getString("cln_Cat_Code"),
                 			 members_resultSet.getString("station"),
                 			 members_resultSet.getString("deptName"),
                 			 members_resultSet.getString("title"),
                 			 members_resultSet.getDate("print_Date"),
                 			 members_resultSet.getString("other_Number"),
                  			 members_resultSet.getString("print_Card"),
                  			 members_resultSet.getString("cut_Off_Exemption"),
                  			 members_resultSet.getString("region"),
                  			 members_resultSet.getString("phone_No"),
                  			 members_resultSet.getString("email"),
                  			 members_resultSet.getString("insurer_Id"),
                  			 members_resultSet.getString("roaming_Enabled"),
                  			 members_resultSet.getString("roaming_Countries")
                         ), rec_Id);


				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } finally {
        	
            if (members_resultSet != null) try { members_resultSet.close(); } catch (SQLException ignore) {}
        	//if (service_tags_resultSet != null) try { service_tags_resultSet.close(); } catch (SQLException ignore) {}
            if (members_statement != null) try { members_statement.close(); } catch (SQLException ignore) {}
          //  if (service_tags_statement != null) try { service_tags_statement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }
        
	}
	
	
	private String[] getSMART_CODE(String[] DBParams, String CUSTOMERID) {
		String[] SMART = new String[50];
        Connection connectionTB = null;
        PreparedStatement costomerTB_statement = null;
        ResultSet customerTB_resultSet = null;
        //SMART_CODE, CLIENT_DB, MEMBERS, MEM_DETS_MAP, MEMBERS_CHANGES, MEM_DETS_CHANGES_MAP
        final String CUSTOMERTB_SQL_LIST = "Select * from INTEG_USER.SMARTAPI_FIN_CLIENT_MAP where CLIENT_UNIQUE = "+CUSTOMERID+" ";

        try {
            connectionTB = DBConnection.getConnection(DBParams);
            try {
				costomerTB_statement = connectionTB.prepareStatement(CUSTOMERTB_SQL_LIST);
				customerTB_resultSet = costomerTB_statement.executeQuery();
				
				if (!customerTB_resultSet.isBeforeFirst() ) {    
				    throw new IllegalArgumentException("No Client with id "+CUSTOMERID+ " was found");
				   }
				
				while (customerTB_resultSet.next()) {		
					SMART[6] =  customerTB_resultSet.getString("SMART_CODE");
					SMART[4] =  customerTB_resultSet.getString("CLIENT_DB");
					SMART[9] =  customerTB_resultSet.getString("MEMBERS");
					SMART[15] =  customerTB_resultSet.getString("CLAIM_RETURNS");
					SMART[19] =  customerTB_resultSet.getString("MEM_CANCEL");
					SMART[23] =  customerTB_resultSet.getString("REPRINTS");
					SMART[29] =  customerTB_resultSet.getString("CLAIM_RETURNS_MAP");
					SMART[30] =  customerTB_resultSet.getString("FRINT_RMVL_MAP");
					SMART[32] =  customerTB_resultSet.getString("MEM_CANCEL_MAP");
					SMART[33] =  customerTB_resultSet.getString("MEM_CATG_CHANGE_MAP");
					SMART[34] =  customerTB_resultSet.getString("MEM_DETS_MAP");
					SMART[36] =  customerTB_resultSet.getString("REPRINTS_MAP");
					SMART[38] =  customerTB_resultSet.getString("MEMBERS_CHANGES");
					SMART[39] =  customerTB_resultSet.getString("MEM_DETS_CHANGES_MAP");
					SMART[40] =  customerTB_resultSet.getString("MEM_CATG_CHANGE");
					SMART[41] =  customerTB_resultSet.getString("FRINT_RMVL");
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } finally {
        	
            if (customerTB_resultSet != null) try { customerTB_resultSet.close(); } catch (SQLException ignore) {}
        	//if (service_tags_resultSet != null) try { service_tags_resultSet.close(); } catch (SQLException ignore) {}
            if (costomerTB_statement != null) try { costomerTB_statement.close(); } catch (SQLException ignore) {}
          //  if (service_tags_statement != null) try { service_tags_statement.close(); } catch (SQLException ignore) {}
            if (connectionTB != null) try { connectionTB.close(); } catch (SQLException ignore) {}
        }
        
        return SMART;
	}
	
	
	
	private String[] GET_MEMBER_DETAILS_PRINCIPLE_PHONE(String phone_no) {
		String[] resultArray = new String[70];

	    Connection connection = null;
	    PreparedStatement get_member_statement = null;
	    PreparedStatement service_tags_statement = null;
	    ResultSet get_member_resultSet = null;
	    ResultSet service_tags_resultSet = null;
	    //order by HOST_DATE asc
	    String SINGLE_SQL_LIST = "Select MEMBERSHIP_NUMBER, NAMES_AS_IS, MEM_ID, GLOBAL_ID, REASON, SMS_STATUS, PIN_NO, CARD_SERIAL_NUMBER from SMART.FIN_MEMBER_DETAILS where PHONE_NO ="+phone_no+" ";
	    System.out.println(SINGLE_SQL_LIST);
	    
		
		
	    try {
	        connection = DBConSmartBO.getConnection();
	       
	        try {
					get_member_statement = connection.prepareStatement(SINGLE_SQL_LIST);
					get_member_resultSet = get_member_statement.executeQuery();
					
					
					if (!get_member_resultSet.isBeforeFirst()) {    
						   // throw new IllegalArgumentException("There are no active MSG_OUT to send");
							String status_msg = "There are no active messages to send";
					
				     }
					
		
					while (get_member_resultSet.next()) {
						    resultArray[0] = get_member_resultSet.getString("GLOBAL_ID");
							resultArray[1] = get_member_resultSet.getString("MEM_ID");
							resultArray[2] = get_member_resultSet.getString("REASON");
							resultArray[3] = get_member_resultSet.getString("MEMBERSHIP_NUMBER");
							resultArray[4] = get_member_resultSet.getString("NAMES_AS_IS");
							resultArray[5] = get_member_resultSet.getString("SMS_STATUS");
							resultArray[6] = get_member_resultSet.getString("PIN_NO");
							resultArray[7] = get_member_resultSet.getString("CARD_SERIAL_NUMBER");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					

					
					resultArray[64] = "Dear customer, we are experiencing technical issues at the moment. We're working to resolve the issues as soon as possible. Please try again later";
					resultArray[65] = "503";
				}
	    } finally {
	    	
	        if (get_member_resultSet != null) try { get_member_resultSet.close(); } catch (SQLException ignore) {}
	        if (get_member_statement != null) try { get_member_statement.close(); } catch (SQLException ignore) {}
	        if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
	    }


		return resultArray;
	}


	private String[] GET_MEMBER_DETAILS_DEPENDANCE_PHONE(String phone_no) {
		String[] resultArray = new String[70];
			
	    Connection connection = null;
	    PreparedStatement get_member_dep_statement = null;
	    PreparedStatement service_tags_statement = null;
	    ResultSet get_member_dep_resultSet = null;
	    ResultSet service_tags_resultSet = null;
	    //order by HOST_DATE asc
	    String SINGLE_SQL_LIST = "Select MEMBER_NUMBER, NAMES_AS_IS, MEM_ID, GLOBAL_ID, REASON, SMS_STATUS, PIN_NO, CARD_SERIAL_NUMBER from SMART.FIN_MEMBER_DEP_DETAILS where PHONE_NO ="+phone_no+" ";
	    System.out.println(SINGLE_SQL_LIST);
	    
	    try {
	        connection = DBConSmartBO.getConnection();
	        try {
					get_member_dep_statement = connection.prepareStatement(SINGLE_SQL_LIST);
					get_member_dep_resultSet = get_member_dep_statement.executeQuery();
					
					
					if (!get_member_dep_resultSet.isBeforeFirst()) {    
						   // throw new IllegalArgumentException("There are no active MSG_OUT to send");
							String status_msg = "There are no active messages to send";
							
							
							resultArray[64] = "Dear customer, we are experiencing technical issues with your Smart mobile service. Please contact your scheme administrator or our call centre +254733320660, +254718222200 for any assistance.";
							resultArray[65] = "404";
				     }

					while (get_member_dep_resultSet.next()) {
						    resultArray[0] = get_member_dep_resultSet.getString("GLOBAL_ID");
							resultArray[1] = get_member_dep_resultSet.getString("MEM_ID");
							resultArray[2] = get_member_dep_resultSet.getString("REASON");
							resultArray[3] = get_member_dep_resultSet.getString("MEMBER_NUMBER");
							resultArray[4] = get_member_dep_resultSet.getString("NAMES_AS_IS");
							resultArray[5] = get_member_dep_resultSet.getString("SMS_STATUS");
							resultArray[6] = get_member_dep_resultSet.getString("PIN_NO");
							resultArray[7] = get_member_dep_resultSet.getString("CARD_SERIAL_NUMBER");
							
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					
					
					resultArray[64] = "Dear customer, we are experiencing technical issues at the moment. We're working to resolve the issues as soon as possible. Please try again later";
					resultArray[65] = "503";
					e.printStackTrace();
				}
	    } finally {
	    	
	        if (get_member_dep_resultSet != null) try { get_member_dep_resultSet.close(); } catch (SQLException ignore) {}
	        if (get_member_dep_statement != null) try { get_member_dep_statement.close(); } catch (SQLException ignore) {}
	        if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
	    }
	    
		return resultArray;
	}
	
	
	private String[] GET_MEMBER_BALANCE(String globalid, String memberid, String reason, String membership_number, String names_as_is, String phoneno, String user_status, String pin_no, String card_serial_no) {
		String[] resultArray_PLAN = new String[70];
	    Connection connection = null;
	    PreparedStatement get_member_plan_bal_statement = null;
	    PreparedStatement service_tags_statement = null;
	    ResultSet get_member_plan_bal_resultSet = null;
	    ResultSet service_tags_resultSet = null;
    
	    String SINGLE_SQL_LIST = "Select " +
	    		"OUT_P_OVERALL," +
	    		"OUT_P_DENTAL," +	    
	    		"OUT_P_OPTICAL_DENTAL," +
	    		"OUT_P_FRAMES," +
	    		"OUT_P_PRE_E_CHRONIC," +
	    		"OUT_P_CONGENITAL," +
	    		"OUT_P_MATERNITY," +
	    		"IMMUNIZATION," +
	    		"OUT_P_PAP_SMEAR," +
	    		"OUT_P_PSYCHIATRIC_ALMTS," +
	    		"OUT_P_HEALTH_CHECKUP," +
	    		"WORK_PLACE_PROGRAM," +
	    		"OUT_P_MEDICAL_APPLNCES," +
	    		"STAFF_CLINIC_BENEFITS," +
	    		"OUT_P_RESERVE," +
	    		"ACUTE_MEDICATION," +
	    		"CHRONIC_MEDICATION," +
	    		"IN_P_OVERALL," +
	    		"POST_HOSPITALIZATION," +
	    		"IN_P_PRE_CHRONIC_CONDITIONS," +
	    		"IN_P_HIV_RELATED_CONDITIONS," +
	    		"IN_P_CONGENITAL_CHILDBITH_NEO," +
	    		"IN_P_NON_ACCIDENTAL_DENTAL," +
	    		"IN_P_NON_ACCIDENTAL_OPTICAL," +
	    		"IN_P_ACCIDENTAL_DENTAL," +
	    		"IN_P_ACCIDENTAL_OPTICAL," +
	    		"IN_P_MATERNITY_COVER," +
	    		"FIRST_EVER_EMERGENCY_CESERIAN," +
	    		"ELECTIVE_CESERIAN_SECTION," +
	    		"IN_P_INTER_EXTER_PROSTHESES," +
	    		"IN_P_PHYSIOTHERAPY," +	    
	    		"IN_P_MAXILLOFACIAL_SURGERY," +
	    		"IN_P_PHYCH_PHYCHOTHERAPY," +
	    		"RADIOLOGY_PATHOLOGY," +
	    		"IN_P_DAYCARE_SUGERY," +
	    		"IN_P_ONCOLOGY_CANCER," +
	    		"IN_P_ORGAN_TRANSPLANT," +
	    		"IN_P_PRES_DRUGS_MATERIALS," +
	    		"IN_P_R_A_OVER_REF_TREAT," +
	    		"IN_P_EXTER_MEDICAL_APPLIANCES," +
	    		"IN_P_GYNAECOLOGICAL_SURGERY," +
	    		"KIDNEY_DIALYSIS," +
	    		"IN_P_ARTHRITIS_ASTHMA_THYROID," +
	    		"IN_P_CARDIOLOGY," +
	    		"IN_P_HYP_D_L_BLOOD_DISORDERS," +
	    		"IN_P_P_FAMILY_PLANNING," +
	    		"IN_P_HDU_AND_ICU," +
	    		"REHAB_DUE_TO_ACC_R_CASES," +
	    		"IN_P_RESERVE," +
	    		"IN_P_NON_ACC_R_MAX_SUGERY," +
	    		"ADDITIONAL_BENFIT," +
	    		"ADDITIONAL_BENFIT2," +
	    		"ADDITIONAL_BENFIT3," +
	    		"ADDITIONAL_BENFIT4," +
	    		"CASH," +
	    		"IN_P_GRAND_POOL," +
	    		"OUT_P_GRAND_POOL," +
	    		"OFFLINE_BILLING_BENEFIT," +
	    		"NHIF," +
	    		"NHIF2" +
	    		" from INTEG_USER.SMARTAPI_PLAN_MAP where STATUS= 1 ";
	    //where REASON ='"+reason+"' AND STATUS= 1
        System.out.println(SINGLE_SQL_LIST);

	    try {
	        connection = DBConSmart.getConnection();
	        try {
					get_member_plan_bal_statement = connection.prepareStatement(SINGLE_SQL_LIST);
					get_member_plan_bal_resultSet = get_member_plan_bal_statement.executeQuery();
					
					
					if (!get_member_plan_bal_resultSet.isBeforeFirst()) {    
						   // throw new IllegalArgumentException("There are no active MSG_OUT to send");
							String status_msg = "There are no plans available for the above reason";
					
				     }
					
					while (get_member_plan_bal_resultSet.next()) {
						resultArray_PLAN[0] = get_member_plan_bal_resultSet.getString("OUT_P_OVERALL");
						resultArray_PLAN[1] = get_member_plan_bal_resultSet.getString("OUT_P_DENTAL");
						resultArray_PLAN[2] = get_member_plan_bal_resultSet.getString("OUT_P_OPTICAL_DENTAL");
						resultArray_PLAN[3] = get_member_plan_bal_resultSet.getString("OUT_P_FRAMES");
						resultArray_PLAN[4] = get_member_plan_bal_resultSet.getString("OUT_P_PRE_E_CHRONIC");
						resultArray_PLAN[5] = get_member_plan_bal_resultSet.getString("OUT_P_CONGENITAL");
						resultArray_PLAN[6] = get_member_plan_bal_resultSet.getString("OUT_P_MATERNITY");
						resultArray_PLAN[7] = get_member_plan_bal_resultSet.getString("IMMUNIZATION");
						resultArray_PLAN[8] = get_member_plan_bal_resultSet.getString("OUT_P_PAP_SMEAR");
						resultArray_PLAN[9] = get_member_plan_bal_resultSet.getString("OUT_P_PSYCHIATRIC_ALMTS");
						resultArray_PLAN[10] = get_member_plan_bal_resultSet.getString("OUT_P_HEALTH_CHECKUP");
						resultArray_PLAN[11] = get_member_plan_bal_resultSet.getString("WORK_PLACE_PROGRAM");
						resultArray_PLAN[12] = get_member_plan_bal_resultSet.getString("OUT_P_MEDICAL_APPLNCES");
						resultArray_PLAN[13] = get_member_plan_bal_resultSet.getString("STAFF_CLINIC_BENEFITS");
						resultArray_PLAN[14] = get_member_plan_bal_resultSet.getString("OUT_P_RESERVE");
						resultArray_PLAN[15] = get_member_plan_bal_resultSet.getString("ACUTE_MEDICATION");
						resultArray_PLAN[16] = get_member_plan_bal_resultSet.getString("CHRONIC_MEDICATION");
						resultArray_PLAN[17] = get_member_plan_bal_resultSet.getString("IN_P_OVERALL");
						resultArray_PLAN[18] = get_member_plan_bal_resultSet.getString("POST_HOSPITALIZATION");
						resultArray_PLAN[19] = get_member_plan_bal_resultSet.getString("IN_P_PRE_CHRONIC_CONDITIONS");
						resultArray_PLAN[20] = get_member_plan_bal_resultSet.getString("IN_P_HIV_RELATED_CONDITIONS");
						resultArray_PLAN[21] = get_member_plan_bal_resultSet.getString("IN_P_CONGENITAL_CHILDBITH_NEO");
						resultArray_PLAN[22] = get_member_plan_bal_resultSet.getString("IN_P_NON_ACCIDENTAL_DENTAL");
						resultArray_PLAN[23] = get_member_plan_bal_resultSet.getString("IN_P_NON_ACCIDENTAL_OPTICAL");
						resultArray_PLAN[24] = get_member_plan_bal_resultSet.getString("IN_P_ACCIDENTAL_DENTAL");
						resultArray_PLAN[25] = get_member_plan_bal_resultSet.getString("IN_P_ACCIDENTAL_OPTICAL");
						resultArray_PLAN[26] = get_member_plan_bal_resultSet.getString("IN_P_MATERNITY_COVER");
						resultArray_PLAN[27] = get_member_plan_bal_resultSet.getString("FIRST_EVER_EMERGENCY_CESERIAN");
						resultArray_PLAN[28] = get_member_plan_bal_resultSet.getString("ELECTIVE_CESERIAN_SECTION");
						resultArray_PLAN[29] = get_member_plan_bal_resultSet.getString("IN_P_INTER_EXTER_PROSTHESES");
						resultArray_PLAN[30] = get_member_plan_bal_resultSet.getString("IN_P_PHYSIOTHERAPY");
						resultArray_PLAN[31] = get_member_plan_bal_resultSet.getString("IN_P_MAXILLOFACIAL_SURGERY");
						resultArray_PLAN[32] = get_member_plan_bal_resultSet.getString("IN_P_PHYCH_PHYCHOTHERAPY");
						resultArray_PLAN[33] = get_member_plan_bal_resultSet.getString("RADIOLOGY_PATHOLOGY");
						resultArray_PLAN[34] = get_member_plan_bal_resultSet.getString("IN_P_DAYCARE_SUGERY");
						resultArray_PLAN[35] = get_member_plan_bal_resultSet.getString("IN_P_ONCOLOGY_CANCER");
						resultArray_PLAN[36] = get_member_plan_bal_resultSet.getString("IN_P_ORGAN_TRANSPLANT");
						resultArray_PLAN[37] = get_member_plan_bal_resultSet.getString("IN_P_PRES_DRUGS_MATERIALS");
						resultArray_PLAN[38] = get_member_plan_bal_resultSet.getString("IN_P_R_A_OVER_REF_TREAT");
						resultArray_PLAN[39] = get_member_plan_bal_resultSet.getString("IN_P_EXTER_MEDICAL_APPLIANCES");
						resultArray_PLAN[40] = get_member_plan_bal_resultSet.getString("IN_P_GYNAECOLOGICAL_SURGERY");
						resultArray_PLAN[41] = get_member_plan_bal_resultSet.getString("KIDNEY_DIALYSIS");
						resultArray_PLAN[42] = get_member_plan_bal_resultSet.getString("IN_P_ARTHRITIS_ASTHMA_THYROID");
						resultArray_PLAN[43] = get_member_plan_bal_resultSet.getString("IN_P_CARDIOLOGY");
						resultArray_PLAN[44] = get_member_plan_bal_resultSet.getString("IN_P_HYP_D_L_BLOOD_DISORDERS");
						resultArray_PLAN[45] = get_member_plan_bal_resultSet.getString("IN_P_P_FAMILY_PLANNING");
						resultArray_PLAN[46] = get_member_plan_bal_resultSet.getString("IN_P_HDU_AND_ICU");
						resultArray_PLAN[47] = get_member_plan_bal_resultSet.getString("REHAB_DUE_TO_ACC_R_CASES");
						resultArray_PLAN[48] = get_member_plan_bal_resultSet.getString("IN_P_RESERVE");
						resultArray_PLAN[49] = get_member_plan_bal_resultSet.getString("IN_P_NON_ACC_R_MAX_SUGERY");
						resultArray_PLAN[50] = get_member_plan_bal_resultSet.getString("ADDITIONAL_BENFIT");
						resultArray_PLAN[51] = get_member_plan_bal_resultSet.getString("ADDITIONAL_BENFIT2");
						resultArray_PLAN[52] = get_member_plan_bal_resultSet.getString("ADDITIONAL_BENFIT3");
						resultArray_PLAN[53] = get_member_plan_bal_resultSet.getString("ADDITIONAL_BENFIT4");
						resultArray_PLAN[54] = get_member_plan_bal_resultSet.getString("CASH");
						resultArray_PLAN[55] = get_member_plan_bal_resultSet.getString("IN_P_GRAND_POOL");
						resultArray_PLAN[56] = get_member_plan_bal_resultSet.getString("OUT_P_GRAND_POOL");
						resultArray_PLAN[57] = get_member_plan_bal_resultSet.getString("OFFLINE_BILLING_BENEFIT");
						resultArray_PLAN[58] = get_member_plan_bal_resultSet.getString("NHIF");
						resultArray_PLAN[59] = get_member_plan_bal_resultSet.getString("NHIF2");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    } finally {
	    	
	        if (get_member_plan_bal_resultSet != null) try { get_member_plan_bal_resultSet.close(); } catch (SQLException ignore) {}
	        if (get_member_plan_bal_statement != null) try { get_member_plan_bal_statement.close(); } catch (SQLException ignore) {}
	        if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
	    }

	
		String[] resultArray_BALANCE = new String[70];	
	    Connection connection_balance = null;
	    PreparedStatement get_member_balance_bal_statement = null;
	    ResultSet get_member_balance_bal_resultSet = null;
	    

	    
	    String SINGLE_SQL_SUM_LIST = "Select " +
	    		resultArray_PLAN[0].trim() +" as OUT_P_OVERALL,"+
	    		resultArray_PLAN[1].trim() +" as OUT_P_DENTAL,"+
	    		resultArray_PLAN[2].trim() +" as OUT_P_OPTICAL_DENTAL,"+
	    		resultArray_PLAN[3].trim() +" as OUT_P_FRAMES,"+
	    		resultArray_PLAN[4].trim() +" as OUT_P_PRE_E_CHRONIC,"+
	    		resultArray_PLAN[5].trim() +" as OUT_P_CONGENITAL,"+
	    		resultArray_PLAN[6].trim() +" as OUT_P_MATERNITY,"+
	    		resultArray_PLAN[7].trim() +" as IMMUNIZATION,"+
	    		resultArray_PLAN[8].trim() +" as OUT_P_PAP_SMEAR,"+
	    		resultArray_PLAN[9].trim() +" as OUT_P_PSYCHIATRIC_ALMTS,"+
	    		resultArray_PLAN[10].trim() +" as OUT_P_HEALTH_CHECKUP,"+
	    		resultArray_PLAN[11].trim() +" as WORK_PLACE_PROGRAM,"+
	    		resultArray_PLAN[12].trim() +" as OUT_P_MEDICAL_APPLNCES,"+
	    		resultArray_PLAN[13].trim() +" as STAFF_CLINIC_BENEFITS,"+
	    		resultArray_PLAN[14].trim() +" as OUT_P_RESERVE,"+
	    		resultArray_PLAN[15].trim() +" as ACUTE_MEDICATION,"+
	    		resultArray_PLAN[16].trim() +" as CHRONIC_MEDICATION,"+
	    		resultArray_PLAN[17].trim() +" as IN_P_OVERALL,"+
	    		resultArray_PLAN[18].trim() +" as POST_HOSPITALIZATION,"+
	    		resultArray_PLAN[19].trim() +" as IN_P_PRE_CHRONIC_CONDITIONS,"+
	    		resultArray_PLAN[20].trim() +" as IN_P_HIV_RELATED_CONDITIONS,"+
	    		resultArray_PLAN[21].trim() +" as IN_P_CONGENITAL_CHILDBITH_NEO,"+
	    		resultArray_PLAN[22].trim() +" as IN_P_NON_ACCIDENTAL_DENTAL,"+
	    		resultArray_PLAN[23].trim() +" as IN_P_NON_ACCIDENTAL_OPTICAL,"+
	    		resultArray_PLAN[24].trim() +" as IN_P_ACCIDENTAL_DENTAL,"+
	    		resultArray_PLAN[25].trim() +" as IN_P_ACCIDENTAL_OPTICAL,"+
	    		resultArray_PLAN[26].trim() +" as IN_P_MATERNITY_COVER,"+
	    		resultArray_PLAN[27].trim() +" as FIRST_EVER_EMERGENCY_CESERIAN,"+
	    		resultArray_PLAN[28].trim() +" as ELECTIVE_CESERIAN_SECTION,"+
	    		resultArray_PLAN[29].trim() +" as IN_P_INTER_EXTER_PROSTHESES,"+
	    		resultArray_PLAN[30].trim() +" as IN_P_PHYSIOTHERAPY,"+
	    		resultArray_PLAN[31].trim() +" as IN_P_MAXILLOFACIAL_SURGERY,"+
	    		resultArray_PLAN[32].trim() +" as IN_P_PHYCH_PHYCHOTHERAPY,"+
	    		resultArray_PLAN[33].trim() +" as RADIOLOGY_PATHOLOGY,"+
	    		resultArray_PLAN[34].trim() +" as IN_P_DAYCARE_SUGERY,"+
	    		resultArray_PLAN[35].trim() +" as IN_P_ONCOLOGY_CANCER,"+
	    		resultArray_PLAN[36].trim() +" as IN_P_ORGAN_TRANSPLANT,"+
	    		resultArray_PLAN[37].trim() +" as IN_P_PRES_DRUGS_MATERIALS,"+
	    		resultArray_PLAN[38].trim() +" as IN_P_R_A_OVER_REF_TREAT,"+
	    		resultArray_PLAN[39].trim() +" as IN_P_EXTER_MEDICAL_APPLIANCES,"+
	    		resultArray_PLAN[40].trim() +" as IN_P_GYNAECOLOGICAL_SURGERY,"+
	    		resultArray_PLAN[41].trim() +" as KIDNEY_DIALYSIS,"+
	    		resultArray_PLAN[42].trim() +" as IN_P_ARTHRITIS_ASTHMA_THYROID,"+
	    		resultArray_PLAN[43].trim() +" as IN_P_CARDIOLOGY,"+
	    		resultArray_PLAN[44].trim() +" as IN_P_HYP_D_L_BLOOD_DISORDERS,"+
	    		resultArray_PLAN[45].trim() +" as IN_P_P_FAMILY_PLANNING,"+
	    		resultArray_PLAN[46].trim() +" as IN_P_HDU_AND_ICU,"+
	    		resultArray_PLAN[47].trim() +" as REHAB_DUE_TO_ACC_R_CASES,"+
	    		resultArray_PLAN[48].trim() +" as IN_P_RESERVE,"+
	    		resultArray_PLAN[49].trim() +" as IN_P_NON_ACC_R_MAX_SUGERY,"+
	    		resultArray_PLAN[51].trim() +" as ADDITIONAL_BENFIT,"+
	    		resultArray_PLAN[52].trim() +" as ADDITIONAL_BENFIT2,"+
	    		resultArray_PLAN[53].trim() +" as ADDITIONAL_BENFIT3,"+
	    		resultArray_PLAN[54].trim() +" as ADDITIONAL_BENFIT4,"+
	    		resultArray_PLAN[55].trim() +" as CASH,"+
	    		resultArray_PLAN[56].trim() +" as IN_P_GRAND_POOL,"+
	    		resultArray_PLAN[57].trim() +" as OUT_P_GRAND_POOL,"+
	    		resultArray_PLAN[58].trim() +" as OFFLINE_BILLING_BENEFIT,"+
	    		resultArray_PLAN[59].trim() +" as NHIF"+
	    				" from SMART.BENEFIT_TOTALS where GLOBAL_ID ='"+globalid+"'";

	    System.out.println(SINGLE_SQL_SUM_LIST);

	    try {
	        connection_balance = DBConSmartBO.getConnection();
	        try {
					get_member_balance_bal_statement = connection_balance.prepareStatement(SINGLE_SQL_SUM_LIST);
					get_member_balance_bal_resultSet = get_member_balance_bal_statement.executeQuery();
					

					if (!get_member_balance_bal_resultSet.isBeforeFirst()) {    
						   // throw new IllegalArgumentException("There are no active MSG_OUT to send");
							String status_msg = "There are no plans available for the above reason";
					
				     }

					while (get_member_balance_bal_resultSet.next()) {
						resultArray_BALANCE[0] = get_member_balance_bal_resultSet.getString("OUT_P_OVERALL");
						resultArray_BALANCE[1] = get_member_balance_bal_resultSet.getString("OUT_P_DENTAL");
						resultArray_BALANCE[2] = get_member_balance_bal_resultSet.getString("OUT_P_OPTICAL_DENTAL");
						resultArray_BALANCE[3] = get_member_balance_bal_resultSet.getString("OUT_P_FRAMES");
						resultArray_BALANCE[4] = get_member_balance_bal_resultSet.getString("OUT_P_PRE_E_CHRONIC");
						resultArray_BALANCE[5] = get_member_balance_bal_resultSet.getString("OUT_P_CONGENITAL");
						resultArray_BALANCE[6] = get_member_balance_bal_resultSet.getString("OUT_P_MATERNITY");
						resultArray_BALANCE[7] = get_member_balance_bal_resultSet.getString("IMMUNIZATION");
						resultArray_BALANCE[8] = get_member_balance_bal_resultSet.getString("OUT_P_PAP_SMEAR");
						resultArray_BALANCE[9] = get_member_balance_bal_resultSet.getString("OUT_P_PSYCHIATRIC_ALMTS");
						resultArray_BALANCE[10] = get_member_balance_bal_resultSet.getString("OUT_P_HEALTH_CHECKUP");
						resultArray_BALANCE[11] = get_member_balance_bal_resultSet.getString("WORK_PLACE_PROGRAM");
						resultArray_BALANCE[12] = get_member_balance_bal_resultSet.getString("OUT_P_MEDICAL_APPLNCES");
						resultArray_BALANCE[13] = get_member_balance_bal_resultSet.getString("STAFF_CLINIC_BENEFITS");
						resultArray_BALANCE[14] = get_member_balance_bal_resultSet.getString("OUT_P_RESERVE");
						resultArray_BALANCE[15] = get_member_balance_bal_resultSet.getString("ACUTE_MEDICATION");
						resultArray_BALANCE[16] = get_member_balance_bal_resultSet.getString("CHRONIC_MEDICATION");
						resultArray_BALANCE[17] = get_member_balance_bal_resultSet.getString("IN_P_OVERALL");
						resultArray_BALANCE[18] = get_member_balance_bal_resultSet.getString("POST_HOSPITALIZATION");
						resultArray_BALANCE[19] = get_member_balance_bal_resultSet.getString("IN_P_PRE_CHRONIC_CONDITIONS");
						resultArray_BALANCE[20] = get_member_balance_bal_resultSet.getString("IN_P_HIV_RELATED_CONDITIONS");
						resultArray_BALANCE[21] = get_member_balance_bal_resultSet.getString("IN_P_CONGENITAL_CHILDBITH_NEO");
						resultArray_BALANCE[22] = get_member_balance_bal_resultSet.getString("IN_P_NON_ACCIDENTAL_DENTAL");
						resultArray_BALANCE[23] = get_member_balance_bal_resultSet.getString("IN_P_NON_ACCIDENTAL_OPTICAL");
						resultArray_BALANCE[24] = get_member_balance_bal_resultSet.getString("IN_P_ACCIDENTAL_DENTAL");
						resultArray_BALANCE[25] = get_member_balance_bal_resultSet.getString("IN_P_ACCIDENTAL_OPTICAL");
						resultArray_BALANCE[26] = get_member_balance_bal_resultSet.getString("IN_P_MATERNITY_COVER");
						resultArray_BALANCE[27] = get_member_balance_bal_resultSet.getString("FIRST_EVER_EMERGENCY_CESERIAN");
						resultArray_BALANCE[28] = get_member_balance_bal_resultSet.getString("ELECTIVE_CESERIAN_SECTION");
						resultArray_BALANCE[29] = get_member_balance_bal_resultSet.getString("IN_P_INTER_EXTER_PROSTHESES");
						resultArray_BALANCE[30] = get_member_balance_bal_resultSet.getString("IN_P_PHYSIOTHERAPY");
						resultArray_BALANCE[31] = get_member_balance_bal_resultSet.getString("IN_P_MAXILLOFACIAL_SURGERY");
						resultArray_BALANCE[32] = get_member_balance_bal_resultSet.getString("IN_P_PHYCH_PHYCHOTHERAPY");
						resultArray_BALANCE[33] = get_member_balance_bal_resultSet.getString("RADIOLOGY_PATHOLOGY");
						resultArray_BALANCE[34] = get_member_balance_bal_resultSet.getString("IN_P_DAYCARE_SUGERY");
						resultArray_BALANCE[35] = get_member_balance_bal_resultSet.getString("IN_P_ONCOLOGY_CANCER");
						resultArray_BALANCE[36] = get_member_balance_bal_resultSet.getString("IN_P_ORGAN_TRANSPLANT");
						resultArray_BALANCE[37] = get_member_balance_bal_resultSet.getString("IN_P_PRES_DRUGS_MATERIALS");
						resultArray_BALANCE[38] = get_member_balance_bal_resultSet.getString("IN_P_R_A_OVER_REF_TREAT");
						resultArray_BALANCE[39] = get_member_balance_bal_resultSet.getString("IN_P_EXTER_MEDICAL_APPLIANCES");
						resultArray_BALANCE[40] = get_member_balance_bal_resultSet.getString("IN_P_GYNAECOLOGICAL_SURGERY");
						resultArray_BALANCE[41] = get_member_balance_bal_resultSet.getString("KIDNEY_DIALYSIS");
						resultArray_BALANCE[42] = get_member_balance_bal_resultSet.getString("IN_P_ARTHRITIS_ASTHMA_THYROID");
						resultArray_BALANCE[43] = get_member_balance_bal_resultSet.getString("IN_P_CARDIOLOGY");
						resultArray_BALANCE[44] = get_member_balance_bal_resultSet.getString("IN_P_HYP_D_L_BLOOD_DISORDERS");
						resultArray_BALANCE[45] = get_member_balance_bal_resultSet.getString("IN_P_P_FAMILY_PLANNING");
						resultArray_BALANCE[46] = get_member_balance_bal_resultSet.getString("IN_P_HDU_AND_ICU");
						resultArray_BALANCE[47] = get_member_balance_bal_resultSet.getString("REHAB_DUE_TO_ACC_R_CASES");
						resultArray_BALANCE[48] = get_member_balance_bal_resultSet.getString("IN_P_RESERVE");
						resultArray_BALANCE[49] = get_member_balance_bal_resultSet.getString("IN_P_NON_ACC_R_MAX_SUGERY");
						resultArray_BALANCE[51] = get_member_balance_bal_resultSet.getString("ADDITIONAL_BENFIT");
						resultArray_BALANCE[52] = get_member_balance_bal_resultSet.getString("ADDITIONAL_BENFIT2");
						resultArray_BALANCE[53] = get_member_balance_bal_resultSet.getString("ADDITIONAL_BENFIT3");
						resultArray_BALANCE[54] = get_member_balance_bal_resultSet.getString("ADDITIONAL_BENFIT4");
						resultArray_BALANCE[55] = get_member_balance_bal_resultSet.getString("CASH");
						resultArray_BALANCE[56] = get_member_balance_bal_resultSet.getString("IN_P_GRAND_POOL");
						resultArray_BALANCE[57] = get_member_balance_bal_resultSet.getString("OUT_P_GRAND_POOL");
						resultArray_BALANCE[58] = get_member_balance_bal_resultSet.getString("OFFLINE_BILLING_BENEFIT");
						resultArray_BALANCE[59] = get_member_balance_bal_resultSet.getString("NHIF");
						
						
						
						resultArray_BALANCE[60] = membership_number;
						resultArray_BALANCE[61] = names_as_is;
						resultArray_BALANCE[62] = phoneno;
						resultArray_BALANCE[63] = user_status;
						resultArray_BALANCE[64] = "messagesessesse";
						resultArray_BALANCE[65] = "200";
						resultArray_BALANCE[66] = pin_no;
						resultArray_BALANCE[67] = card_serial_no;
						
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    } finally {
	    	
	        if (get_member_balance_bal_resultSet != null) try { get_member_balance_bal_resultSet.close(); } catch (SQLException ignore) {}
	        if (get_member_balance_bal_statement != null) try { get_member_balance_bal_statement.close(); } catch (SQLException ignore) {}
	        if (connection_balance != null) try { connection_balance.close(); } catch (SQLException ignore) {}
	    }

	    
		return resultArray_BALANCE;


	}
	
	
	
	
	private String CalculateMemberMemnoBalanceServiceDBAccess(String memnos){
		String smart = "balance from mem no";
       
        
        return smart;
	}
	
	
	
	private String[] CalculateMemberPhoneBalanceServiceDBAccess(String phoneno){
		String smart = "balance from phone";
		String[] member_details = new String[70];
		 
		System.out.println("THIS IS THE PHONE NUMBER///////////////////: = "+phoneno);
		member_details = GET_MEMBER_DETAILS_PRINCIPLE_PHONE(phoneno);
		System.out.println("THIS IS THE FIRST MEMBER DETAILS||||||||||||||||||||||||: = "+member_details[0]);
		if((member_details[0] == null)||(member_details[0].isEmpty())){
		member_details = 	GET_MEMBER_DETAILS_DEPENDANCE_PHONE(phoneno);
		System.out.println("THIS IS THE SECOND MEMBER DETAILS////////////////////: = "+member_details[0]);
		}
		
		System.out.println("HERE WE GO?????????????????????????????????????????????");
		
		if(!member_details[0].isEmpty()){
		member_details = 	GET_MEMBER_BALANCE(member_details[0], member_details[1], member_details[2], member_details[3], member_details[4], phoneno, member_details[5], member_details[6], member_details[7]);
		}
	
		return member_details;
	}

	
	private int MEMBER_ACTIVATE(String phoneno, String MEMBERTABLE) {
		// TODO Auto-generated method stub
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		int output = 0;

        // String updateTableSQL = "UPDATE SMART."+MEMBERTABLE+" SET SMS_STATUS = ? "
		//		                  + " WHERE PHONE_NO = ?";
		
         String updateTableSQL = "UPDATE SMART."+MEMBERTABLE+" SET SMS_STATUS = ? "
				                  + " WHERE PHONE_NO = ?";

		try {
			dbConnection = DBConSmartBO.getConnection();
			preparedStatement = dbConnection.prepareStatement(updateTableSQL);
 
			preparedStatement.setString(1, "1");
			preparedStatement.setString(2, phoneno);

			System.out.println("HOW ARE YOU  "+updateTableSQL);
			// execute update SQL stetement
			output =  preparedStatement.executeUpdate();
 
			System.out.println("Record is updated to DBUSER table!");
 
		} catch (SQLException e) {
 
			System.out.println(e.getMessage());
			throw new IllegalArgumentException(e.getMessage());
 
		} finally {
 
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
 
			if (dbConnection != null) {
				try {
					dbConnection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
 
		} 
    
		return output;
	}
	
	private String[] ActivateMemberPhoneSMSServiceDBAccess(String phoneno){
		String smart = "balance from phone";
		String[] member_details = new String[10];
		  
		member_details = GET_MEMBER_DETAILS_PRINCIPLE_PHONE(phoneno);
		if(member_details[0] != null){
			
			if((member_details[5].equals("1")) || (member_details[5].equals("2"))){
				member_details[0]="ipsum";
				member_details[1]="opsum";
				member_details[2]="membershipnumber";
				member_details[3]="nameasis";
				member_details[4]=phoneno;
				member_details[5]=member_details[5];
				member_details[6]="ALREADY";
			}else{
				int output = 0;
				output = 	MEMBER_ACTIVATE(phoneno, "FIN_MEMBER_DETAILS");
				System.out.println("PLEASE PLEASE PLEASE PLEASE = "+output);
				if(output == 1){
					System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA "+output);
					member_details[0]="ipsum";
					member_details[1]="opsum";
					member_details[2]="membershipnumber";
					member_details[3]="nameasis";
					member_details[4]=phoneno;
					member_details[5]=member_details[5];
					member_details[6]="ACTIVATED";
				}else{
					System.out.println("QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ "+output);
					member_details[0]="ipsum";
					member_details[1]="opsum";
					member_details[2]="membershipnumber";
					member_details[3]="nameasis";
					member_details[4]=phoneno;
					member_details[5]=member_details[5];
					member_details[6]="NOTACTIVATED";
				}

			}
		
		}else{
			
			member_details = 	GET_MEMBER_DETAILS_DEPENDANCE_PHONE(phoneno);
			
			if(member_details[0] != null){

				if((member_details[5].equals("1")) || (member_details[5].equals("2"))){
					member_details[0]="ipsum";
					member_details[1]="opsum";
					member_details[2]="membershipnumber";
					member_details[3]="nameasis";
					member_details[4]=phoneno;
					member_details[5]=member_details[5];
					member_details[6]="ALREADY";
				}else{
					int output = 0;
					output = 	MEMBER_ACTIVATE(phoneno, "FIN_MEMBER_DEP_DETAILS");
					if(output == 1){
						member_details[0]="ipsum";
						member_details[1]="opsum";
						member_details[2]="membershipnumber";
						member_details[3]="nameasis";
						member_details[4]=phoneno;
						member_details[5]=member_details[5];
						member_details[6]="ACTIVATED";
					}else{
						member_details[0]="ipsum";
						member_details[1]="opsum";
						member_details[2]="membershipnumber";
						member_details[3]="nameasis";
						member_details[4]=phoneno;
						member_details[5]=member_details[5];
						member_details[6]="NOTACTIVATED";
					}

				}
				
				}else{
					member_details[0]="ipsum";
					member_details[1]="opsum";
					member_details[2]="membershipnumber";
					member_details[3]="nameasis";
					member_details[4]=phoneno;
					member_details[5]=member_details[5];
					member_details[6]="NOTACTIVATED";
				}
		}


        return member_details;
	}

	




	private String[] RejectMemberPhoneSMSServiceDBAccess(String phoneno){

		String smart = "balance from phone";
		String[] member_details = new String[10];
		  
		member_details = GET_MEMBER_DETAILS_PRINCIPLE_PHONE(phoneno);
		if(member_details[0] != null){
			
			if((member_details[5].equals("1")) || (member_details[5].equals("2"))){
				member_details[0]="ipsum";
				member_details[1]="opsum";
				member_details[2]="membershipnumber";
				member_details[3]="nameasis";
				member_details[4]=phoneno;
				member_details[5]=member_details[5];
				member_details[6]="ALREADY";
			}else{
				member_details[0]="ipsum";
				member_details[1]="opsum";
				member_details[2]="membershipnumber";
				member_details[3]="nameasis";
				member_details[4]=phoneno;
				member_details[5]=member_details[5];
				member_details[6]="REJECTED";
			}
		
		}else{
			
			member_details = 	GET_MEMBER_DETAILS_DEPENDANCE_PHONE(phoneno);
			
			if(member_details[0] != null){

				if((member_details[5].equals("1")) || (member_details[5].equals("2"))){
					member_details[0]="ipsum";
					member_details[1]="opsum";
					member_details[2]="membershipnumber";
					member_details[3]="nameasis";
					member_details[4]=phoneno;
					member_details[5]=member_details[5];
					member_details[6]="ALREADY";
				}else{
					member_details[0]="ipsum";
					member_details[1]="opsum";
					member_details[2]="membershipnumber";
					member_details[3]="nameasis";
					member_details[4]=phoneno;
					member_details[5]=member_details[5];
					member_details[6]="REJECTED";
				}
				
				}else{
					member_details[0]="ipsum";
					member_details[1]="opsum";
					member_details[2]="membershipnumber";
					member_details[3]="nameasis";
					member_details[4]=phoneno;
					member_details[5]=member_details[5];
					member_details[6]="NOTACTIVATED";
				}
		}

        return member_details;
	}
	
	
	private int MEMBER_DEACTIVATE(String phoneno, String MEMBERTABLE) {
		// TODO Auto-generated method stub
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		int output = 0;

        // String updateTableSQL = "UPDATE SMART."+MEMBERTABLE+" SET SMS_STATUS = ? "
		//		                  + " WHERE PHONE_NO = ?";
		
         String updateTableSQL = "UPDATE SMART."+MEMBERTABLE+" SET SMS_STATUS = ? "
				                  + " WHERE PHONE_NO = ?";

		try {
			dbConnection = DBConSmartBO.getConnection();
			preparedStatement = dbConnection.prepareStatement(updateTableSQL);
 
			preparedStatement.setString(1, "-1");
			preparedStatement.setString(2, phoneno);

			System.out.println("HOW ARE YOU  "+updateTableSQL);
			// execute update SQL stetement
			output =  preparedStatement.executeUpdate();
 
			System.out.println("Record is updated to DBUSER table!");
 
		} catch (SQLException e) {
 
			System.out.println(e.getMessage());
			throw new IllegalArgumentException(e.getMessage());
 
		} finally {
 
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
 
			if (dbConnection != null) {
				try {
					dbConnection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
 
		} 
    
		return output;
	}
	
	
	private String[] DeactivateMemberPhoneSMSServiceDBAccess(String phoneno){
		String smart = "balance from phone";
		String[] member_details = new String[10];
		  
		member_details = GET_MEMBER_DETAILS_PRINCIPLE_PHONE(phoneno);
		if(member_details[0] != null){
			
			if((member_details[5].equals("1")) || (member_details[5].equals("2"))){
				
				int output = 0;
				output = 	MEMBER_DEACTIVATE(phoneno, "FIN_MEMBER_DETAILS");
				System.out.println("PLEASE PLEASE PLEASE PLEASE = "+output);
				if(output == 1){
					System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA "+output);
					member_details[0]="ipsum";
					member_details[1]="opsum";
					member_details[2]="membershipnumber";
					member_details[3]="nameasis";
					member_details[4]=phoneno;
					member_details[5]=member_details[5];
					member_details[6]="DEACTIVATED";
				}else{
					System.out.println("QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ "+output);
					member_details[0]="ipsum";
					member_details[1]="opsum";
					member_details[2]="membershipnumber";
					member_details[3]="nameasis";
					member_details[4]=phoneno;
					member_details[5]=member_details[5];
					member_details[6]="NOTDEACTIVATED";
				}

			}else{

				member_details[0]="ipsum";
				member_details[1]="opsum";
				member_details[2]="membershipnumber";
				member_details[3]="nameasis";
				member_details[4]=phoneno;
				member_details[5]=member_details[5];
				member_details[6]="ALREADY";
				
			}
		
		}else{
			
			member_details = 	GET_MEMBER_DETAILS_DEPENDANCE_PHONE(phoneno);
			
			if(member_details[0] != null){

				if((member_details[5].equals("1")) || (member_details[5].equals("2"))){
					
					int output = 0;
					output = 	MEMBER_DEACTIVATE(phoneno, "FIN_MEMBER_DEP_DETAILS");
					if(output == 1){
						member_details[0]="ipsum";
						member_details[1]="opsum";
						member_details[2]="membershipnumber";
						member_details[3]="nameasis";
						member_details[4]=phoneno;
						member_details[5]=member_details[5];
						member_details[6]="DEACTIVATED";
					}else{
						member_details[0]="ipsum";
						member_details[1]="opsum";
						member_details[2]="membershipnumber";
						member_details[3]="nameasis";
						member_details[4]=phoneno;
						member_details[5]=member_details[5];
						member_details[6]="NOTDEACTIVATED";
					}

				}else{

					member_details[0]="ipsum";
					member_details[1]="opsum";
					member_details[2]="membershipnumber";
					member_details[3]="nameasis";
					member_details[4]=phoneno;
					member_details[5]=member_details[5];
					member_details[6]="ALREADY";
					
				}
				
				}else{
					member_details[0]="ipsum";
					member_details[1]="opsum";
					member_details[2]="membershipnumber";
					member_details[3]="nameasis";
					member_details[4]=phoneno;
					member_details[5]=member_details[5];
					member_details[6]="NOTACTIVATED";
				}
		}


        return member_details;
	}
	

	public List<Member> getMembers(String customerid, String country, int startindex, int maxresults, int status, String restrict,  String orderby) {
 
		String customertable = "";
		String customercountry = "";
		members.clear();
		RequestMap data = new RequestMap(customerid, country, startindex, maxresults, status, restrict,  orderby);
		MembersServiceDBAccess(data.getDBParams(), customerid, customercountry, startindex, maxresults, status, restrict, orderby);
		List<Member> allmembers = new ArrayList<Member>(members.values());

		return allmembers;	
	}

	public List<Member> getMembersChanges(String customerid, String country, int startindex, int maxresults, int status, String restrict,  String orderby) {
		 
		String customertable = "";
		String customercountry = "";
	    members.clear();
		RequestMap data = new RequestMap(customerid, country, startindex, maxresults, status, restrict,  orderby);
		MembersServiceDBAccessChanges(data.getDBParams(), customerid, customercountry, startindex, maxresults, status, restrict, orderby);
		List<Member> allmembers = new ArrayList<Member>(members.values());

		return allmembers;	
	}
	/*
	public List<Member> getSearchMembers(String q) {
		
		members.clear();
		q = q.toLowerCase();
		List<Member> matchingMembers = new ArrayList<Member>();
		
		for(Member p : members.values())
		{
			if(p.getMemberNames().toLowerCase().contains(q)){
				matchingMembers.add(p);
			}
		}
		
		return matchingMembers;
	}
	*/
	
	
	public Balance getMemberBalance(String text_code, String phonenos, String memnos) {

		String[] member_details = new String[70];

		member_details = CalculateMemberPhoneBalanceServiceDBAccess(phonenos);

		Balance balancedetails = new Balance(
				member_details[0],
				member_details[1],
				member_details[2], 
				member_details[3],
				member_details[4],
				member_details[5],
				member_details[6],
				member_details[7],
				member_details[8], 
				member_details[9],
				member_details[10],
				member_details[11],
				member_details[12],
				member_details[13],
				member_details[14], 
				member_details[15],
				member_details[16],
				member_details[17],
				member_details[18],
				member_details[19],
				member_details[20], 
				member_details[21],
				member_details[22],
				member_details[23],
				member_details[24],
				member_details[25],
				member_details[26], 
				member_details[27],
				member_details[28],
				member_details[29],
				member_details[30],
				member_details[31],
				member_details[32], 
				member_details[33],
				member_details[34],
				member_details[35],
				member_details[36],
				member_details[37],
				member_details[38], 
				member_details[39],
				member_details[40],
				member_details[41],
				member_details[42],
				member_details[43],
				member_details[44], 
				member_details[45],
				member_details[46],
				member_details[47],
				member_details[48],
				member_details[49],
				member_details[50], 
				member_details[51],
				member_details[52],
				member_details[53],
				member_details[54],
				member_details[55],
				member_details[56],
				member_details[57],
				member_details[58],
				member_details[60],
				member_details[61],
				member_details[62],
				member_details[63],
				member_details[64],
				member_details[65],
				member_details[66],
				member_details[67]
				);
		
		/*
		if(!(memnos.equals("0"))){

			memnos.trim();
			String[] allmemnos=memnos.split("\\|");
			String[] member_details = new String[10];
			String balance = "";
			for (String memno : allmemnos) { 
				//balance = CalculateMemberMemnoBalanceServiceDBAccess(memno);
				//balancedetails = new Balance(phoneno, member_details[0], member_details[0]);
			}
					
		}
		*/
        /*
		if(!(phonenos.equals("0"))){
			
			phonenos.trim();
			String[] allphonenos=phonenos.split("\\|");
			
			for (String phoneno : allphonenos) {
				
				
			
		      //  for(String str : member_details){
		      //      System.out.println(str+"  I AM IN");
		      //  }
		    
		        
				

						
			}
			

		}
		
		*/
		return balancedetails;

		}
	
	
	public Balance updateMemberActivate(String phonenos) {

		Balance balancedetails = null;
		if(!(phonenos.equals("0"))){
			
			phonenos.trim();
			String[] allphonenos=phonenos.split("\\|");
			String[] member_details = new String[10];
			for (String phoneno : allphonenos) { 
				member_details = ActivateMemberPhoneSMSServiceDBAccess(phoneno);
					//balancedetails = new Balance(member_details[4], member_details[0], member_details[1], member_details[2], member_details[3], member_details[5], member_details[6]);
					
					balancedetails = new Balance(
							member_details[0],
							member_details[1],
							member_details[2], 
							member_details[3],
							member_details[4],
							member_details[5],
							member_details[6],
							member_details[7],
							member_details[8], 
							member_details[9],
							member_details[10],
							member_details[11],
							member_details[12],
							member_details[13],
							member_details[14], 
							member_details[15],
							member_details[16],
							member_details[17],
							member_details[18],
							member_details[19],
							member_details[20], 
							member_details[21],
							member_details[22],
							member_details[23],
							member_details[24],
							member_details[25],
							member_details[26], 
							member_details[27],
							member_details[28],
							member_details[29],
							member_details[30],
							member_details[31],
							member_details[32], 
							member_details[33],
							member_details[34],
							member_details[35],
							member_details[36],
							member_details[37],
							member_details[38], 
							member_details[39],
							member_details[40],
							member_details[41],
							member_details[42],
							member_details[43],
							member_details[44], 
							member_details[45],
							member_details[46],
							member_details[47],
							member_details[48],
							member_details[49],
							member_details[50], 
							member_details[51],
							member_details[52],
							member_details[53],
							member_details[54],
							member_details[55],
							member_details[56],
							member_details[57],
							member_details[58],
							member_details[60],
							member_details[61],
							member_details[62],
							member_details[63],
							member_details[64],
							member_details[65],
							member_details[66],
							member_details[67]
							);
			}

		}
		return balancedetails;

		}
	
	
	public Balance updateMembeReject(String phonenos) {

		Balance balancedetails = null;
		if(!(phonenos.equals("0"))){
			
			phonenos.trim();
			String[] allphonenos=phonenos.split("\\|");
			String[] member_details = new String[10];
			for (String phoneno : allphonenos) { 
				member_details = RejectMemberPhoneSMSServiceDBAccess(phoneno);
					//balancedetails = new Balance(member_details[4], member_details[0], member_details[1], member_details[2], member_details[3], member_details[5], member_details[6]);
				
				balancedetails = new Balance(
						member_details[0],
						member_details[1],
						member_details[2], 
						member_details[3],
						member_details[4],
						member_details[5],
						member_details[6],
						member_details[7],
						member_details[8], 
						member_details[9],
						member_details[10],
						member_details[11],
						member_details[12],
						member_details[13],
						member_details[14], 
						member_details[15],
						member_details[16],
						member_details[17],
						member_details[18],
						member_details[19],
						member_details[20], 
						member_details[21],
						member_details[22],
						member_details[23],
						member_details[24],
						member_details[25],
						member_details[26], 
						member_details[27],
						member_details[28],
						member_details[29],
						member_details[30],
						member_details[31],
						member_details[32], 
						member_details[33],
						member_details[34],
						member_details[35],
						member_details[36],
						member_details[37],
						member_details[38], 
						member_details[39],
						member_details[40],
						member_details[41],
						member_details[42],
						member_details[43],
						member_details[44], 
						member_details[45],
						member_details[46],
						member_details[47],
						member_details[48],
						member_details[49],
						member_details[50], 
						member_details[51],
						member_details[52],
						member_details[53],
						member_details[54],
						member_details[55],
						member_details[56],
						member_details[57],
						member_details[58],
						member_details[60],
						member_details[61],
						member_details[62],
						member_details[63],
						member_details[64],
						member_details[65],
						member_details[66],
						member_details[67]
						);
			}

		}
		return balancedetails;

		}
	
	
	public Balance updateMembeDeactivate(String phonenos) {

		Balance balancedetails = null;
		if(!(phonenos.equals("0"))){
			
			phonenos.trim();
			String[] allphonenos=phonenos.split("\\|");
			String[] member_details = new String[10];
			for (String phoneno : allphonenos) { 
				member_details = DeactivateMemberPhoneSMSServiceDBAccess(phoneno);
				//	balancedetails = new Balance(member_details[4], member_details[0], member_details[1], member_details[2], member_details[3], member_details[5], member_details[6]);	
				
				balancedetails = new Balance(
						member_details[0],
						member_details[1],
						member_details[2], 
						member_details[3],
						member_details[4],
						member_details[5],
						member_details[6],
						member_details[7],
						member_details[8], 
						member_details[9],
						member_details[10],
						member_details[11],
						member_details[12],
						member_details[13],
						member_details[14], 
						member_details[15],
						member_details[16],
						member_details[17],
						member_details[18],
						member_details[19],
						member_details[20], 
						member_details[21],
						member_details[22],
						member_details[23],
						member_details[24],
						member_details[25],
						member_details[26], 
						member_details[27],
						member_details[28],
						member_details[29],
						member_details[30],
						member_details[31],
						member_details[32], 
						member_details[33],
						member_details[34],
						member_details[35],
						member_details[36],
						member_details[37],
						member_details[38], 
						member_details[39],
						member_details[40],
						member_details[41],
						member_details[42],
						member_details[43],
						member_details[44], 
						member_details[45],
						member_details[46],
						member_details[47],
						member_details[48],
						member_details[49],
						member_details[50], 
						member_details[51],
						member_details[52],
						member_details[53],
						member_details[54],
						member_details[55],
						member_details[56],
						member_details[57],
						member_details[58],
						member_details[60],
						member_details[61],
						member_details[62],
						member_details[63],
						member_details[64],
						member_details[65],
						member_details[66],
						member_details[67]
						);
			}

		}
		return balancedetails;

		}
	
	
	public List<Member> getSearchMembers(String q, String customerid, String country, int startindex, int maxresults, int status, String restrict,  String orderby) {

		members.clear();
		RequestMap data = new RequestMap(q, customerid, country, startindex, maxresults, status, restrict,  orderby);
		SearchMembersServiceDBAccess(data.getDBParams(), data.getQ(), data.getCustomerId(), data.getCountry(), data.getStartIndex(), data.getMaxResults(), data.getStatus(), data.getRestrict(), data.getOrderBy());
        List<Member> matchingMembers = new ArrayList<Member>(members.values());
		return matchingMembers;
	}

	

	public Member getMember(String id, String customerid, String country) throws IllegalArgumentException {
		
		members.clear();
		RequestMap data = new RequestMap(customerid, country);
		SingleMembersServiceDBAccess(data.getDBParams(), id, customerid, country);
		//CONNECT TO THE DB PULL DATA AND PUT IT INT THE VIEW
		
		Member p = members.get(id);
		if(p == null){
			throw new IllegalArgumentException("Could not find member with id:"+id);
		}
		return p;
	}
	
	public Member getMemberChanges(String id, String customerid, String country) throws IllegalArgumentException {
		
		members.clear();
		RequestMap data = new RequestMap(customerid, country);
		SingleMembersServiceDBAccessChanges(data.getDBParams(), id, customerid, country);
		//CONNECT TO THE DB PULL DATA AND PUT IT INT THE VIEW
		
		Member p = members.get(id);
		if(p == null){
			throw new IllegalArgumentException("Could not find member with id:"+id);
		}
		return p;
	}
	
	public int addBalance(Member member, String customerid, String country) throws IllegalArgumentException {
		
		
		if(members.containsValue(member)){
			throw new IllegalArgumentException("Member "+member+" already exists.");
		}
		
		//members.clear();
		RequestMap data = new RequestMap(customerid, country);
		int id = idGen.incrementAndGet();
		String test = "";
		members.put(test, member);
		
		return id;
	}
	
	public String addMember(Member member, String medical_allocation_cover,  String staff_number, String customerid, String country) throws IllegalArgumentException {

		members.clear();
		if(members.containsValue(member)){
			throw new IllegalArgumentException("Member "+member+" already exists.");
		}

		RequestMap data = new RequestMap(customerid, country);
		int clientid = 0;
		String id = "";
		id = addMembersServiceDBAccess(data.getDBParams(), clientid, member, medical_allocation_cover, staff_number, customerid, country);

		members.put(id, member);
		
		return id;
	}
	
	

	
	
	@Override
	public String updateMember(String id, Member member,
			String medical_allocation_cover, String staff_number,
			String customerid, String country) {
		// TODO Auto-generated method stub

		
		RequestMap data = new RequestMap(customerid, country);
		
		SingleMembersServiceDBAccess(data.getDBParams(), id, customerid, country);
		if(!members.containsKey(id)){
			throw new IllegalArgumentException("Unable to find member with id "+id);
		}
		
		members.clear();
	
        String memberid = "";
        memberid = updateMembersServiceDBAccess(data.getDBParams(), id, member,
        		medical_allocation_cover, staff_number,
        		customerid, country);
		members.put(memberid, member);

		
		return memberid;
	}


	public int addMemberReturns(Member member, String customerid, String country) throws IllegalArgumentException {
		
		
		if(members.containsValue(member)){
			throw new IllegalArgumentException("Member "+member+" already exists IN THE DB.");
		}
		
		//members.clear();
		RequestMap data = new RequestMap(customerid, country);
		int id = idGen.incrementAndGet();
		String test = "";
		members.put(test, member);
		
		return id;
	}
	

	public String addMember(Member member, String id) throws IllegalArgumentException {	
		/*
		if(members.containsValue(member)){
			throw new IllegalArgumentException("Member "+member+" already exists.");
		}
		*/
		//members.clear();
		members.put(id, member);
		return id;
	}
	
	/*
	public String addCardreprint(Cardreprint cardreprint, String rec_id) throws IllegalArgumentException {

		//cardreprints.clear();
		cardreprints.put(rec_id, cardreprint);
		return rec_id;
	}
	*/
	
	public String addActivation(Activation activation, String rec_id) throws IllegalArgumentException {
		/*
		if(activations.containsValue(activation)){
			throw new IllegalArgumentException("Activation "+activation+" already exists.");
		}
		*/
		//activations.clear();
		activations.put(rec_id, activation);
		return rec_id;
	}
	
	
	public String addDeactivation(Deactivation deactivation, String rec_id) throws IllegalArgumentException {
		/*
		if(deactivations.containsValue(deactivation)){
			throw new IllegalArgumentException("Deactivation "+deactivation+" already exists.");
		}
		*/
		//deactivations.clear();
		deactivations.put(rec_id, deactivation);
		return rec_id;
	}
	
	
	public String addFingerprint(Fingerprintremoval fingerprintremoval, String rec_id) throws IllegalArgumentException {
		/*
		if(deactivations.containsValue(deactivation)){
			throw new IllegalArgumentException("Deactivation "+deactivation+" already exists.");
		}
		*/
		//deactivations.clear();
		fingerprintremovals.put(rec_id, fingerprintremoval);
		return rec_id;
	}
	
	public String addMoneyaddition(MoneyAddition moneyaddition, String rec_id) throws IllegalArgumentException {
		/*
		if(moneyadditions.containsValue(moneyaddition)){
			throw new IllegalArgumentException("Moneyaddition "+moneyaddition+" already exists.");
		}
		*/
		//cardreprints.clear();
		moneyadditions.put(rec_id, moneyaddition);
		return rec_id;
	}

	public String addMoneyreduction(MoneyReduction moneyreducton, String rec_id) throws IllegalArgumentException {
		/*
		if(moneyreductions.containsValue(moneyreduction)){
			throw new IllegalArgumentException("Moneyreduction "+moneyreduction+" already exists.");
		}
		*/
		//moneyreductions.clear();
		moneyreductions.put(rec_id, moneyreducton);
		return rec_id;
	}
	

	public String addCategorychange(Categorychange categorychange, String rec_id) throws IllegalArgumentException {
		/*
		if(categorychanges.containsValue(categorychange)){
			throw new IllegalArgumentException("Categorychange "+categorychange+" already exists.");
		}
		*/
		//categorychanges.clear();
		categorychanges.put(rec_id, categorychange);
		return rec_id;
	}
	
	public void updateMember(String id, String customerid, String country) throws IllegalArgumentException {
		
		members.clear();
		RequestMap data = new RequestMap(customerid, country);
		SingleMembersServiceDBAccess(data.getDBParams(), id, customerid, country);
		//CONNECT TO THE DB, PULL DATA AND PUT IT INTO THE VIEW
		if(!members.containsKey(id)){
			throw new IllegalArgumentException("Unable to find member with id "+id);
		}
		System.out.println("The following member was updated"+id);
		
		//UPDATE THE VIEW THEN UPDATE THE DB
		//members.put(id);	
		
	}
	

	
	public void updateSwitchedMember(String id, String customerid, String country) throws IllegalArgumentException {
	    
		//members.clear();
		RequestMap data = new RequestMap(customerid, country);
		SingleMembersServiceDBAccess(data.getDBParams(), id, customerid, country);
		//CONNECT TO THE DB, PULL DATA AND PUT IT INTO THE VIEW
		if(!members.containsKey(id)){
			throw new IllegalArgumentException("Unable to find member with id "+id);
		}
		
		//UPDATE THE DB
		//members.put(id, member);	
	}

	public String deleteMember(String id, String customerid, String country) throws IllegalArgumentException {
		
		//members.clear();
		RequestMap data = new RequestMap(customerid, country);

		SingleMembersServiceDBAccess(data.getDBParams(), id, customerid, country);
		if(!members.containsKey(id)){
			throw new IllegalArgumentException("Unable to find member with id "+id);
		}
		SingleMembersDeleteServiceDBAccess(data.getDBParams(), id, customerid, country);

		return id;
	}



	@Override
	public String addActivateMember(Activation activation, String customerid,
			String country) {
		
		activations.clear();
		// TODO Auto-generated method stub
		if(activations.containsValue(activation)){
			throw new IllegalArgumentException("Activation "+activation+" already exists.");
		}
		
		RequestMap data = new RequestMap(customerid, country);
		String id = "";
             
		id = addActivationsMembersServiceDBAccess(data.getDBParams(), id, activation, customerid, country);

		activations.put(id, activation);

		return id;
	}



	@Override
	public List<Activation> getActivateMember(String customerid,
			String country, int startindex, int maxresults, String restrict,
			String orderby) {
		// TODO Auto-generated method stub
		
		String customertable = "";
		String customercountry = "";
		int status = 0;
		//members.clear();
		RequestMap data = new RequestMap(customerid, country, startindex, maxresults, status, restrict,  orderby);
		ActivateMembersServiceDBAccess(data.getDBParams(), customerid, customercountry, startindex, maxresults, status, restrict, orderby);
		List<Activation> allactivations = new ArrayList<Activation>(activations.values());

		return allactivations;	

	}



	@Override
	public int updateActivationsMember(int id, Activation activation,
			String customerid, String country) {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public int deleteActivationsMember(int id, String customerid, String country) {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public String addDeactivateMember(Deactivation deactivation,
			String customerid, String country) {
		// TODO Auto-generated method stub
		deactivations.clear();
		if(deactivations.containsValue(deactivation)){
			throw new IllegalArgumentException("Deactivation "+deactivation+" already exists.");
		}
		//
		RequestMap data = new RequestMap(customerid, country);
		String id = "";
             
		id = addDeactivationsMembersServiceDBAccess(data.getDBParams(), id, deactivation, customerid, country);
     	deactivations.put(id, deactivation);

		
		return id;
	}



	@Override
	public List<Deactivation> getDeactivationsMembers(String customerid,
			String country, int startindex, int maxresults, String restrict,
			String orderby) {
		// TODO Auto-generated method stub
		
		String customertable = "";
		String customercountry = "";
		int status = 0;
		//members.clear();
		RequestMap data = new RequestMap(customerid, country, startindex, maxresults, status, restrict,  orderby);
		DeactivateMembersServiceDBAccess(data.getDBParams(), customerid, customercountry, startindex, maxresults, status, restrict, orderby);
		List<Deactivation> alldeactivations = new ArrayList<Deactivation>(deactivations.values());

		return alldeactivations;	
	}



	@Override
	public int updateDeactivationsMember(int id, Deactivation deactivation,
			String customerid, String country) {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public int deleteDeactivationsMember(int id, String customerid,
			String country) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public String addCardreprintsMember(Cardreprint cardreprint,
			String customerid, String country) {
		cardreprints.clear();
		if(cardreprints.containsValue(cardreprint)){
			throw new IllegalArgumentException("Cardreprint "+cardreprint+" already exists.");
		}

		RequestMap data = new RequestMap(customerid, country);
		int id = 0;
        String reprintid = "";
		//reprintid = addCardreprintsMembersServiceDBAccess(data.getDBParams(), id, cardreprint, customerid, country);
		cardreprints.clear();
		cardreprints.put(reprintid, cardreprint);
		
		return reprintid;
	}
	
/*
	@Override
	public String addCardreprintsMember(Cardreprint cardreprint,
			String customerid, String country) {
		// TODO Auto-generated method stub
		cardreprints.clear();
		if(cardreprints.containsValue(cardreprint)){
			throw new IllegalArgumentException("Cardreprint "+cardreprint+" already exists.");
		}

		RequestMap data = new RequestMap(customerid, country);
		int id = 0;
        String reprintid = "";
		reprintid = addCardreprintsMembersServiceDBAccess(data.getDBParams(), id, cardreprint, customerid, country);
		cardreprints.clear();
		cardreprints.put(reprintid, cardreprint);
		
		return reprintid;
	}



	@Override
	public List<Cardreprint> getCardreprintsMembers(String customerid,
			String country, int startindex, int maxresults, String restrict,
			String orderby) {
		// TODO Auto-generated method stub
		
		String customertable = "";
		String customercountry = "";
		int status = 0;
		cardreprints.clear();
		RequestMap data = new RequestMap(customerid, country, startindex, maxresults, status, restrict,  orderby);
		CardreprintMembersServiceDBAccess(data.getDBParams(), customerid, customercountry, startindex, maxresults, status, restrict, orderby);

		List<Cardreprint> allcardreprints = new ArrayList<Cardreprint>(cardreprints.values());

		return allcardreprints;	

	}



	@Override
	public int updateCardreprintsMember(int id, Cardreprint cardreprint,
			String customerid, String country) {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public int deleteCardreprintsMember(int id, String customerid,
			String country) {
		// TODO Auto-generated method stub
		return 0;
	}
*/


	@Override
	public int addRenewalsMember(Renewal renewal, String customerid,
			String country) {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public List<Renewal> getRenewalsMembers(String customerid, String country,
			int startindex, int maxresults, String restrict, String orderby) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public int updateRenewalsMember(int id, Renewal renewal, String customerid,
			String country) {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public int deleteRenewalsMember(int id, String customerid, String country) {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public String addCategorychangeMember(Categorychange categorychange,
			String customerid, String country) {
		// TODO Auto-generated method stub
		categorychanges.clear();
		if(categorychanges.containsValue(categorychange)){
			throw new IllegalArgumentException("Categorychange "+categorychange+" already exists.");
		}
		//
		RequestMap data = new RequestMap(customerid, country);
		String id = "";

		id = addCategorychangesMembersServiceDBAccess(data.getDBParams(), id, categorychange, customerid, country);
		categorychanges.put(id, categorychange);
		
		return id;
	}



	@Override
	public List<Categorychange> getCategorychangeMembers(String customerid,
			String country, int startindex, int maxresults, String restrict,
			String orderby) {
		// TODO Auto-generated method stub
		
		String customertable = "";
		String customercountry = "";
		int status = 0;
		//members.clear();
		RequestMap data = new RequestMap(customerid, country, startindex, maxresults, status, restrict,  orderby);
		MoneyCategorychangeMembersServiceDBAccess(data.getDBParams(), customerid, customercountry, startindex, maxresults, status, restrict, orderby);

		List<Categorychange> allcategorychanges = new ArrayList<Categorychange>(categorychanges.values());

		return allcategorychanges;	
	}



	@Override
	public int updateCategorychangeMember(int id,
			Categorychange categorychange, String customerid, String country) {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public int deleteCategorychangeMember(int id, String customerid,
			String country) {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public String addFingerprintremovalMember(Fingerprintremoval fingerprintremoval,
			String customerid, String country) {
		// TODO Auto-generated method stub
		fingerprintremovals.clear();
		if(fingerprintremovals.containsValue(fingerprintremoval)){
			throw new IllegalArgumentException("fingerprintremoval "+fingerprintremoval+" already exists.");
		}
	
		RequestMap data = new RequestMap(customerid, country);
		String id = "";

		id = addFingerprintremovalsMembersServiceDBAccess(data.getDBParams(), id, fingerprintremoval, customerid, country);
		fingerprintremovals.put(id, fingerprintremoval);
		
		return id;
	}



	@Override
	public List<Fingerprintremoval> getFingerprintremovalMembers(String customerid,
			String country, int startindex, int maxresults, String restrict,
			String orderby) {
		// TODO Auto-generated method stub
		
		String customertable = "";
		String customercountry = "";
		int status = 0;
		//members.clear();
		RequestMap data = new RequestMap(customerid, country, startindex, maxresults, status, restrict,  orderby);
		FingerprintremovalMembersServiceDBAccess(data.getDBParams(), customerid, customercountry, startindex, maxresults, status, restrict, orderby);

		List<Fingerprintremoval> allfingerprintremovals = new ArrayList<Fingerprintremoval>(fingerprintremovals.values());

		return allfingerprintremovals;	
	}



	@Override
	public int updateFingerprintremovalMember(int id, Fingerprintremoval fingerprintremoval,
			String customerid, String country) {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public int deleteFingerprintremovalMember(int id, String customerid,
			String country) {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public String addMoneyadditionMember(MoneyAddition moneyaddition, String customerid,
			String country) {
		// TODO Auto-generated method stub
		moneyadditions.clear();
		if(moneyadditions.containsValue(moneyaddition)){
			throw new IllegalArgumentException("Money "+moneyaddition+" already exists.");
		}
		
		RequestMap data = new RequestMap(customerid, country);
		String id = "";
		
		id = addMoneyadditionsMembersServiceDBAccess(data.getDBParams(), id, moneyaddition, customerid, country);
		moneyadditions.put(id, moneyaddition);

		return id;
	}



	@Override
	public List<MoneyAddition> getMoneyadditionMembers(String customerid,
			String country, int startindex, int maxresults, String restrict,
			String orderby) {
		// TODO Auto-generated method stub
		
		String customertable = "";
		String customercountry = "";
		int status = 0;
		//members.clear();
		RequestMap data = new RequestMap(customerid, country, startindex, maxresults, status, restrict,  orderby);
		MoneyadditionMembersServiceDBAccess(data.getDBParams(), customerid, customercountry, startindex, maxresults, status, restrict, orderby);

		List<MoneyAddition> allmoneyadditions = new ArrayList<MoneyAddition>(moneyadditions.values());

		return allmoneyadditions;	
	}



	@Override
	public int updateMoneyadditionMember(int id, MoneyReduction moneyReduction,
			String customerid, String country) {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public int deleteMoneyadditionMember(int id, String customerid,
			String country) {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public String addMoneyreductionMember(MoneyReduction moneyreduction, String customerid,
			String country) {
		// TODO Auto-generated method stub
		
		moneyreductions.clear();
		if(moneyreductions.containsValue(moneyreduction)){
			throw new IllegalArgumentException("Money "+moneyreduction+" already exists.");
		}
		
		RequestMap data = new RequestMap(customerid, country);
		String id = "";
		
		id = addMoneyreductionsMembersServiceDBAccess(data.getDBParams(), id, moneyreduction, customerid, country);
		moneyreductions.put(id, moneyreduction);

		return id;
	}



	@Override
	public List<MoneyReduction> getMoneyreductionMembers(String customerid,
			String country, int startindex, int maxresults, String restrict,
			String orderby) {
		// TODO Auto-generated method stub
		
		String customertable = "";
		String customercountry = "";
		int status = 0;
		//members.clear();
		RequestMap data = new RequestMap(customerid, country, startindex, maxresults, status, restrict,  orderby);
		MoneyreductionMembersServiceDBAccess(data.getDBParams(), customerid, customercountry, startindex, maxresults, status, restrict, orderby);

		List<MoneyReduction> allmoneyreductions = new ArrayList<MoneyReduction>(moneyreductions.values());

		return allmoneyreductions;	

	}



	@Override
	public int updateMoneyreductionMember(int id, MoneyReduction moneyReduction,
			String customerid, String country) {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public int deleteMoneyreductionMember(int id, String customerid,
			String country) {
		// TODO Auto-generated method stub
		return 0;
	}


    public synchronized static long get(){
		  long current= System.currentTimeMillis();
	    return current++;
	}

	private  java.sql.Date getCurrentTimeStamp() {
	     java.util.Date utilDate = new java.util.Date();
	     java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
	     System.out.println("utilDate:" + utilDate);
	     System.out.println("sqlDate:" + sqlDate); 
	     return sqlDate;
	}













 }
