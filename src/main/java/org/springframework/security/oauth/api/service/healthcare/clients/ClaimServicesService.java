package org.springframework.security.oauth.api.service.healthcare.clients;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.security.oauth.api.data.healthcare.clients.DBConnection;
import org.springframework.security.oauth.api.data.healthcare.clients.RequestMapIntegstaging;
import org.springframework.security.oauth.api.model.healthcare.clients.Claim;
import org.springframework.security.oauth.api.model.healthcare.clients.ClaimService;
import org.springframework.security.oauth.api.model.healthcare.clients.Person;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service("claimServicesService")
public class ClaimServicesService implements IClaimServicesService {
	
	private Map<String, ClaimService> claimServices = new HashMap<String, ClaimService>();
	private AtomicInteger idGen = new AtomicInteger();
	
	String guarded_cols[] = new String[] {
			"id",
			"created_at",
			"last_update"
	};

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
	

    public ClaimServicesService(){
    	
    }
    
	public void SingleClaimsServiceDBAccess(String[] DBParams, String id, String CUSTOMERID, String country){
		Connection connection = null;
        PreparedStatement claimServices_statement = null;
        PreparedStatement service_tags_statement = null;
        ResultSet claimServices_resultSet = null;
        ResultSet service_tags_resultSet = null;
        
        String[] SMART = getSMART_CODE(DBParams, CUSTOMERID);
        Gson gson = new Gson();
        HashMap CLAIMS_MAP =  gson.fromJson(SMART[45], new TypeToken<HashMap<String, String>>(){}.getType());
        
        final String SINGLE_SQL_LIST = "select * from ( Select * from "+SMART[1]+"."+SMART[2]+" where "+CLAIMS_MAP.get("PROVIDER_CLAIM_ID")+" = "+id+" order by transaction_date asc ) where rownum <= 50";

        try {
            connection = DBConnection.getConnection(DBParams);
            try {
				claimServices_statement = connection.prepareStatement(SINGLE_SQL_LIST);
				claimServices_resultSet = claimServices_statement.executeQuery();
				
				if (!claimServices_resultSet.isBeforeFirst() ) {    
				    throw new IllegalArgumentException("No claim with id "+id+ " was found");
				   }
				
				while (claimServices_resultSet.next()) {

				      String claimService_id = claimServices_resultSet.getString("claim_id");
				      String claim_central_id = claimServices_resultSet.getString("central_id");
                  addClaimService(new ClaimService(
                      claim_central_id,
                      claimService_id,
                      "",
                      claimServices_resultSet.getString("service_code_type"),
                      claimServices_resultSet.getString("service_code"),
                      "",
                      claimServices_resultSet.getString("qty"),
                      claimServices_resultSet.getString("amount"),
                      "",
                      claimServices_resultSet.getString("diagnosis_code"),
                      claimServices_resultSet.getString("diagnosis_description"),
                      "",
                      claimServices_resultSet.getDate("DATE_RECEIVED"),
                      claimServices_resultSet.getDate("DATE_RECEIVED"),
       			    claimServices_resultSet.getString("ADMIT_ID"),
 				        claimServices_resultSet.getString("PICKED_STATUS"),
 			    	    claimServices_resultSet.getDate("PICKED_DATE"),
 					    claimServices_resultSet.getString("PROVIDER_KEY"),
 					    claimServices_resultSet.getString("INVOICE_NR"),
 					    claimServices_resultSet.getString("INSURER_ID")
                     ), claim_central_id);


				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } finally {
        	
            if (claimServices_resultSet != null) try { claimServices_resultSet.close(); } catch (SQLException ignore) {}
            if (claimServices_statement != null) try { claimServices_statement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }
        
	}
	
	public void ClaimsServiceDBAccess(String[] DBParams, String CUSTOMERID, String customercountry, int startindex, int maxresults, int status, String restrict,  String orderby){
		  
		int lastindex  = startindex+maxresults;
		Connection connection = null;
        PreparedStatement claimServices_statement = null;
        PreparedStatement service_tags_statement = null;
        ResultSet claimServices_resultSet = null;
        ResultSet service_tags_resultSet = null;
        
        String[] SMART = getSMART_CODE(DBParams, CUSTOMERID);
        Gson gson = new Gson();
        
        HashMap CLAIMS_MAP =  gson.fromJson(SMART[45], new TypeToken<HashMap<String, String>>(){}.getType());
 
        try {
            connection = DBConnection.getConnection(DBParams);
            try {
            	String sql_statement = "SELECT outer.* "+
						  " FROM (SELECT ROWNUM rn, inner.* "+
			        		" FROM (  SELECT e.* "+
			        		" FROM "+SMART[1]+"."+SMART[44]+" e "+
			        		" WHERE "+CLAIMS_MAP.get("PICKED_STATUS")+" = "+status+" "+
			        		" ORDER BY "+orderby+") inner) outer "+
			        		" WHERE outer.rn >= "+startindex+" AND outer.rn < "+lastindex+"";
            	
            	System.out.println(sql_statement);
            	
            	System.out.println(sql_statement);
				claimServices_statement = connection.prepareStatement(sql_statement);
				claimServices_resultSet = claimServices_statement.executeQuery();
				
				if (!claimServices_resultSet.isBeforeFirst() ) {    
				    throw new IllegalArgumentException("All claims have already been processed");
				   }
				/*
				System.out.println("SELECT outer.* "+
						  " FROM (SELECT ROWNUM rn, inner.* "+
			        		" FROM (  SELECT e.* "+
			        		" FROM "+SMARTCODE+"_OWNER.STG_"+SMARTCODE+"_CLAIMS e "+
			        		" WHERE status = "+status+" "+
			        		" ORDER BY "+orderby+") inner) outer "+
			        		" WHERE outer.rn >= "+startindex+" AND outer.rn < "+lastindex+"");
				*/
				while (claimServices_resultSet.next()) {
	  
				      String claimService_id = claimServices_resultSet.getString("claim_id");
				      String claim_central_id = claimServices_resultSet.getString("central_id");
                    addClaimService(new ClaimService(
                        claim_central_id,
                        claimService_id,
                        "",
                        claimServices_resultSet.getString("service_code_type"),
                        claimServices_resultSet.getString("service_code"),
                        "",
                        claimServices_resultSet.getString("qty"),
                        claimServices_resultSet.getString("amount"),
                        "",
                        claimServices_resultSet.getString("diagnosis_code"),
                        claimServices_resultSet.getString("diagnosis_description"),
                        "",
                        claimServices_resultSet.getDate("DATE_RECEIVED"),
                        claimServices_resultSet.getDate("DATE_RECEIVED"),
         			    claimServices_resultSet.getString("ADMIT_ID"),
   				        claimServices_resultSet.getString("PICKED_STATUS"),
   			    	    claimServices_resultSet.getDate("PICKED_DATE"),
   					    claimServices_resultSet.getString("PROVIDER_KEY"),
   					    claimServices_resultSet.getString("INVOICE_NR"),
   					    claimServices_resultSet.getString("INSURER_ID")
                       ), claim_central_id);

                  // System.out.println("first claim"+claimServices_resultSet.getString("member_name"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } finally {
        	
            if (claimServices_resultSet != null) try { claimServices_resultSet.close(); } catch (SQLException ignore) {}
            if (claimServices_statement != null) try { claimServices_statement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }
        
	}
	
/*
	public void ClaimServicesServiceDBAccess(String[] DBParams, String CUSTOMERID, String customercountry){

		Connection connection = null;
        PreparedStatement claimServices_statement = null;
        PreparedStatement service_tags_statement = null;
        ResultSet claimServices_resultSet = null;
        ResultSet service_tags_resultSet = null;
        
        String[] SMART = getSMART_CODE(DBParams, CUSTOMERID);
        Gson gson = new Gson();
        HashMap CLAIMSERVICES_MAP =  gson.fromJson(SMART[45], new TypeToken<HashMap<String, String>>(){}.getType());

        String sql_statement ="";
    	
        try {
            connection = DBConnection.getConnection(DBParams);
            try {

				claimServices_statement = connection.prepareStatement(sql_statement);
				claimServices_resultSet = claimServices_statement.executeQuery();
				
				if (!claimServices_resultSet.isBeforeFirst() ) {    
				    throw new IllegalArgumentException("All claimServices have already been processed");
				   }

				while (claimServices_resultSet.next()) {
				      String claimService_id = claimServices_resultSet.getString("central_id");
                      addClaimService(new ClaimService(
                    	  claimService_id,
                          claimServices_resultSet.getString("claim_central_id"),
                          claimServices_resultSet.getString("encounter_type"),
                          claimServices_resultSet.getString("code_type"),
                          claimServices_resultSet.getString("code"),
                          claimServices_resultSet.getString("code_description"),
                          claimServices_resultSet.getString("quantity"),
                          claimServices_resultSet.getString("amount"),
                          claimServices_resultSet.getString("encounter_code"),
                          claimServices_resultSet.getString("diagnosis_code"),
                          claimServices_resultSet.getString("diagnosis_description"),
                          claimServices_resultSet.getString("card_serial"),
                   	      claimServices_resultSet.getDate("insert_date"),
                   	      claimServices_resultSet.getDate("received_date")
                         ), claimService_id);
    

                      
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } finally {
        	
            if (claimServices_resultSet != null) try { claimServices_resultSet.close(); } catch (SQLException ignore) {}
            if (claimServices_statement != null) try { claimServices_statement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }
        
	}
	*/
	

	public void SingleClaimServicesServiceDBAccess(String[] DBParams, String id, String CUSTOMERID, String country){
        Connection connection = null;
        PreparedStatement claimServices_statement = null;
        PreparedStatement service_tags_statement = null;
        ResultSet claimServices_resultSet = null;
        ResultSet service_tags_resultSet = null;
        
        String[] SMART = getSMART_CODE(DBParams, CUSTOMERID);
        Gson gson = new Gson();
        HashMap CLAIMS_MAP =  gson.fromJson(SMART[45], new TypeToken<HashMap<String, String>>(){}.getType());
        
        final String SINGLE_SQL_LIST = "SELECT * from "+SMART[1]+"."+SMART[44]+" WHERE CENTRAL_ID = "+id+" ORDER BY CENTRAL_ID ASC";

        System.out.println(SINGLE_SQL_LIST);
        
        try {
            connection = DBConnection.getConnection(DBParams);
            try {
				claimServices_statement = connection.prepareStatement(SINGLE_SQL_LIST);
				claimServices_resultSet = claimServices_statement.executeQuery();
				
				if (!claimServices_resultSet.isBeforeFirst() ) {    
				    throw new IllegalArgumentException("No Claim Service with Claim id "+id+ " was found");
				   }
				
				while (claimServices_resultSet.next()) {

				      String claimService_id = claimServices_resultSet.getString("claim_id");
				      String claim_central_id = claimServices_resultSet.getString("central_id");
                  addClaimService(new ClaimService(
                      claim_central_id,
                      claimService_id,
                      "",
                      claimServices_resultSet.getString("service_code_type"),
                      claimServices_resultSet.getString("service_code"),
                      "",
                      claimServices_resultSet.getString("qty"),
                      claimServices_resultSet.getString("amount"),
                      "",
                      claimServices_resultSet.getString("diagnosis_code"),
                      claimServices_resultSet.getString("diagnosis_description"),
                      "",
                      claimServices_resultSet.getDate("DATE_RECEIVED"),
                      claimServices_resultSet.getDate("DATE_RECEIVED"),
       			      claimServices_resultSet.getString("ADMIT_ID"),
 				      claimServices_resultSet.getString("PICKED_STATUS"),
 			    	  claimServices_resultSet.getDate("PICKED_DATE"),
 					  claimServices_resultSet.getString("PROVIDER_KEY"),
 					  claimServices_resultSet.getString("INVOICE_NR"),
 					  claimServices_resultSet.getString("INSURER_ID")
                     ), claim_central_id);

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } finally {
        	
            if (claimServices_resultSet != null) try { claimServices_resultSet.close(); } catch (SQLException ignore) {}
            if (claimServices_statement != null) try { claimServices_statement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }
        
	}
	
	
	public void SingleClaimServicesServiceClaimIdDBAccess(String[] DBParams, String id, String CUSTOMERID, String country){
        Connection connection = null;
        PreparedStatement claimServices_statement = null;
        PreparedStatement service_tags_statement = null;
        ResultSet claimServices_resultSet = null;
        ResultSet service_tags_resultSet = null;
        
        String[] SMART = getSMART_CODE(DBParams, CUSTOMERID);
        Gson gson = new Gson();
        HashMap CLAIMS_MAP =  gson.fromJson(SMART[45], new TypeToken<HashMap<String, String>>(){}.getType());
        
        final String SINGLE_SQL_LIST = "SELECT * from "+SMART[1]+"."+SMART[44]+" WHERE CLAIM_ID = "+id+" ORDER BY CENTRAL_ID ASC";

        System.out.println(SINGLE_SQL_LIST);
        
        try {
            connection = DBConnection.getConnection(DBParams);
            try {
				claimServices_statement = connection.prepareStatement(SINGLE_SQL_LIST);
				claimServices_resultSet = claimServices_statement.executeQuery();
				
				if (!claimServices_resultSet.isBeforeFirst() ) {    
				    throw new IllegalArgumentException("No Claim with id "+id+ " was found");
				   }
				
				while (claimServices_resultSet.next()) {

				      String claimService_id = claimServices_resultSet.getString("claim_id");
				      String claim_central_id = claimServices_resultSet.getString("central_id");
                  addClaimService(new ClaimService(
                      claim_central_id,
                      claimService_id,
                      "",
                      claimServices_resultSet.getString("service_code_type"),
                      claimServices_resultSet.getString("service_code"),
                      "",
                      claimServices_resultSet.getString("qty"),
                      claimServices_resultSet.getString("amount"),
                      "",
                      claimServices_resultSet.getString("diagnosis_code"),
                      claimServices_resultSet.getString("diagnosis_description"),
                      "",
                      claimServices_resultSet.getDate("DATE_RECEIVED"),
                      claimServices_resultSet.getDate("DATE_RECEIVED"),
       			      claimServices_resultSet.getString("ADMIT_ID"),
 				      claimServices_resultSet.getString("PICKED_STATUS"),
 			    	  claimServices_resultSet.getDate("PICKED_DATE"),
 					  claimServices_resultSet.getString("PROVIDER_KEY"),
 					  claimServices_resultSet.getString("INVOICE_NR"),
 					  claimServices_resultSet.getString("INSURER_ID")
                     ), claim_central_id);

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } finally {
        	
            if (claimServices_resultSet != null) try { claimServices_resultSet.close(); } catch (SQLException ignore) {}
            if (claimServices_statement != null) try { claimServices_statement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }
        
	}
	
	
	public void SearchClaimServicesServiceDBAccess(String[] DBParams, String q, String CUSTOMERID, String country, int startindex, int maxresults, int status, String restrict,  String orderby){
		int lastindex  = startindex+maxresults;
		Connection connection = null;
        PreparedStatement claimServices_statement = null;
        PreparedStatement service_tags_statement = null;
        ResultSet claimServices_resultSet = null;
        ResultSet service_tags_resultSet = null;
        
       String[] SMART = getSMART_CODE(DBParams, CUSTOMERID);
       Gson gson = new Gson();
       HashMap CLAIMSERVICES_MAP =  gson.fromJson(SMART[45], new TypeToken<HashMap<String, String>>(){}.getType());

        final String SEARCH_SQL_LIST = " SELECT outer.*  FROM ( "+
        		" SELECT ROWNUM rn, inner.*  FROM ( "+  
        		" SELECT e.*  FROM "+SMART[1]+"."+SMART[44]+" e "+  
        		" WHERE "+CLAIMSERVICES_MAP.get("STATUS")+" = "+status+" AND "+
        		" ( "+
        		" "+CLAIMSERVICES_MAP.get("PROVIDER_CLAIMSERVICE_ID")+" LIKE '%"+q+"%' OR  "+ 
        		" "+CLAIMSERVICES_MAP.get("POLICY_NUMBER")+"  LIKE '%"+q+"%' OR "+  
        		" "+CLAIMSERVICES_MAP.get("PROVIDER_CODE")+" LIKE '%"+q+"%' OR "+  
        		" "+CLAIMSERVICES_MAP.get("MEMBER_NAME")+" LIKE '%"+q+"%'  OR "+  
        		" "+CLAIMSERVICES_MAP.get("MEMBERSHIP_NUMBER")+" LIKE '%"+q+"%'  OR "+  
        		" "+CLAIMSERVICES_MAP.get("TRANSACTION_DATE")+" LIKE '%"+q+"%'  OR  "+ 
        		" "+CLAIMSERVICES_MAP.get("AMOUNT")+" LIKE '%"+q+"%'  OR  "+ 
        		" "+CLAIMSERVICES_MAP.get("DATE_RECEIVED")+" LIKE '%"+q+"%'  OR  "+ 
        		" "+CLAIMSERVICES_MAP.get("SMART_INVOICE_NR")+" LIKE '%"+q+"%'  OR "+  
        		" "+CLAIMSERVICES_MAP.get("PROVIDER_CLAIMSERVICE_ID")+" LIKE '%"+q+"%'  "+
        		" ) ORDER BY "+orderby+"  "+
        		" ) inner) outer  "+ 
        		" WHERE outer.rn >= "+startindex+" AND outer.rn < "+lastindex+" ";
    
        try {
        	
            connection = DBConnection.getConnection(DBParams);
            
            try {
				claimServices_statement = connection.prepareStatement(SEARCH_SQL_LIST);
				
			    System.out.println("--------------------------------------HERE---------------------------------------");
			    System.out.println(SEARCH_SQL_LIST);
			    
				claimServices_resultSet = claimServices_statement.executeQuery();
				
				if (!claimServices_resultSet.isBeforeFirst() ) {    
				    throw new IllegalArgumentException("Your search - "+q+" - did not match any claimService. "+"Suggestions:"+
				    		"Make sure all words are spelled correctly."+
				    		"Try different keywords."+
				    		"Try more general keywords.");
				   }
				

			    
			    
				while (claimServices_resultSet.next()) {
				      String claimService_id = claimServices_resultSet.getString("claim_id");
				      String claim_central_id = claimServices_resultSet.getString("central_id");
                  addClaimService(new ClaimService(
                      claim_central_id,
                      claimService_id,
                      "",
                      claimServices_resultSet.getString("service_code_type"),
                      claimServices_resultSet.getString("service_code"),
                      "",
                      claimServices_resultSet.getString("qty"),
                      claimServices_resultSet.getString("amount"),
                      "",
                      claimServices_resultSet.getString("diagnosis_code"),
                      claimServices_resultSet.getString("diagnosis_description"),
                      "",
                      claimServices_resultSet.getDate("DATE_RECEIVED"),
                      claimServices_resultSet.getDate("DATE_RECEIVED"),
       			    claimServices_resultSet.getString("ADMIT_ID"),
 				        claimServices_resultSet.getString("PICKED_STATUS"),
 			    	    claimServices_resultSet.getDate("PICKED_DATE"),
 					    claimServices_resultSet.getString("PROVIDER_KEY"),
 					    claimServices_resultSet.getString("INVOICE_NR"),
 					    claimServices_resultSet.getString("INSURER_ID")
                     ), claim_central_id);


				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } finally {
        	
            if (claimServices_resultSet != null) try { claimServices_resultSet.close(); } catch (SQLException ignore) {}
            if (claimServices_statement != null) try { claimServices_statement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }
        
	}
	
	
	private String[] getSMART_CODE(String[] DBParams, String CUSTOMERID) {
		String[] SMART = new String[50];
        Connection connectionTB = null;
        PreparedStatement costomerTB_statement = null;
        ResultSet customerTB_resultSet = null;
        final String CUSTOMERTB_SQL_LIST = "Select SMART_CODE, CLIENT_DB, SERVICES, SERVICES_MAP from INTEG_USER.SMARTAPI_FIN_CLIENT_MAP where CLIENT_UNIQUE = "+CUSTOMERID+" ";

        
        try {
            connectionTB = DBConnection.getConnection(DBParams);
            try {
				costomerTB_statement = connectionTB.prepareStatement(CUSTOMERTB_SQL_LIST);
				customerTB_resultSet = costomerTB_statement.executeQuery();
				
				if (!customerTB_resultSet.isBeforeFirst() ) {    
				    throw new IllegalArgumentException("No Client with id "+CUSTOMERID+ " was found");
				   }
				
				while (customerTB_resultSet.next()) {		
					SMART[0] =  customerTB_resultSet.getString("SMART_CODE");
					SMART[1] =  customerTB_resultSet.getString("CLIENT_DB");
					SMART[44] =  customerTB_resultSet.getString("SERVICES");
					SMART[45] =  customerTB_resultSet.getString("SERVICES_MAP");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } finally {
        	
            if (customerTB_resultSet != null) try { customerTB_resultSet.close(); } catch (SQLException ignore) {}
            if (costomerTB_statement != null) try { costomerTB_statement.close(); } catch (SQLException ignore) {}
            if (connectionTB != null) try { connectionTB.close(); } catch (SQLException ignore) {}
        }
        
        return SMART;
	}
	






	/*
	public List<ClaimService> getSearchClaimServices(String q) {
		
		claimServices.clear();
		q = q.toLowerCase();
		List<ClaimService> matchingClaimServices = new ArrayList<ClaimService>();
		
		for(ClaimService p : claimServices.values())
		{
			if(p.getMemberNames().toLowerCase().contains(q)){
				matchingClaimServices.add(p);
			}
		}
		
		return matchingClaimServices;
	}
	*/
	
	
	public List<ClaimService> getSearchClaimServices(String q, String customerid, String country, int startindex, int maxresults, int status, String restrict,  String orderby) {

		claimServices.clear();
		RequestMapIntegstaging data = new RequestMapIntegstaging(q, customerid, country, startindex, maxresults, status, restrict,  orderby);
		SearchClaimServicesServiceDBAccess(data.getDBParams(), data.getQ(), data.getCustomerId(), data.getCountry(), data.getStartIndex(), data.getMaxResults(), data.getStatus(), data.getRestrict(), data.getOrderBy());
        List<ClaimService> matchingClaimServices = new ArrayList<ClaimService>(claimServices.values());
		return matchingClaimServices;
	}

	

	public ClaimService getClaimService(String id, String customerid, String country) throws IllegalArgumentException {
		
		claimServices.clear();
		RequestMapIntegstaging data = new RequestMapIntegstaging(customerid, country);
		SingleClaimServicesServiceDBAccess(data.getDBParams(), id, customerid, country);
		//CONNECT TO THE DB PULL DATA AND PUT IT INT THE VIEW
		
		ClaimService p = claimServices.get(id);
		if(p == null){
			throw new IllegalArgumentException("Could not find Claim Service with id:"+id);
		}
		return p;
	}


	public List<ClaimService> getClaimServiceClaimId(String id, String customerid, String country) throws IllegalArgumentException {
		
		claimServices.clear();
		RequestMapIntegstaging data = new RequestMapIntegstaging(customerid, country);
		SingleClaimServicesServiceClaimIdDBAccess(data.getDBParams(), id, customerid, country);
		//CONNECT TO THE DB PULL DATA AND PUT IT INT THE VIEW
		/*
		ClaimService p = claimServices.get(id);
		if(p == null){
			throw new IllegalArgumentException("Could not find claimService with id:"+id);
		}
		return p;
		*/
		List<ClaimService> matchingClaimServices = new ArrayList<ClaimService>(claimServices.values());
		return matchingClaimServices;
	}
	
	
	public List<ClaimService> getClaimServices(String ids, String customerid, String country) {
		claimServices.clear();
		RequestMapIntegstaging data = new RequestMapIntegstaging(customerid, country);
		String[] IDsArrays = ids.replaceAll("\\s+","").split(",");
		for (int i = 0; i < IDsArrays.length; i++) {
			System.out.println("|"+IDsArrays[i]+"|");
		}
		
		for (int i = 0; i < IDsArrays.length; i++) {
			SingleClaimServicesServiceDBAccess(data.getDBParams(), IDsArrays[i], customerid, country);
		}

		List<ClaimService> allclaimServices = new ArrayList<ClaimService>(claimServices.values());
		return allclaimServices;
	}
	
	@Override
	public List<ClaimService> getClaimServices(String customerid, String country, int startindex, int maxresults, int status, String restrict, String orderby) {
		 
		claimServices.clear();
		String customertable = "";
		String customercountry = "";
		RequestMapIntegstaging data = new RequestMapIntegstaging(customerid, country, startindex, maxresults, status, restrict,  orderby);
		ClaimsServiceDBAccess(data.getDBParams(), customerid, customercountry, startindex, maxresults, status, restrict, orderby);
		List<ClaimService> allclaims = new ArrayList<ClaimService>(claimServices.values());
		return allclaims;	
	}
	
	public String addClaimService(ClaimService claimService, String customerid, String country) throws IllegalArgumentException {
		
		
		if(claimServices.containsValue(claimService)){
			throw new IllegalArgumentException("ClaimService "+claimService+" already exists.");
		}
		
		//claimServices.clear();
		RequestMapIntegstaging data = new RequestMapIntegstaging(customerid, country);
		int id = idGen.incrementAndGet();
		//claimServices.put(id, claimService);
		
		return "";
	}
	
	
	public int addClaimServiceReturns(ClaimService claimService, String customerid, String country) throws IllegalArgumentException {
		
		
		if(claimServices.containsValue(claimService)){
			throw new IllegalArgumentException("ClaimService "+claimService+" already exists IN THE DB.");
		}
		
		//claimServices.clear();
		RequestMapIntegstaging data = new RequestMapIntegstaging(customerid, country);
		int id = idGen.incrementAndGet();
		//claimServices.put(id, claimService);
		
		return id;
	}
	

	public String addClaimService(ClaimService claimService, String provider_claimService_id) throws IllegalArgumentException {	
		/*
		if(claimServices.containsValue(claimService)){
			throw new IllegalArgumentException("ClaimService "+claimService+" already exists.");
		}
		*/
		//claimServices.clear();
		claimServices.put(provider_claimService_id, claimService);
		return provider_claimService_id;
	}
	
	



	public void updateClaimService(int id, String customerid, String country) throws IllegalArgumentException {
		
		//claimServices.clear();
		RequestMapIntegstaging data = new RequestMapIntegstaging(customerid, country);
		//SingleClaimServicesServiceDBAccess(data.getDBParams(), id, customerid, country);
		//CONNECT TO THE DB, PULL DATA AND PUT IT INTO THE VIEW
		if(!claimServices.containsKey(id)){
			throw new IllegalArgumentException("Unable to find claimService with id "+id);
		}
		System.out.println("The following claimService was updated"+id);
		
		//UPDATE THE VIEW THEN UPDATE THE DB
		//claimServices.put(id);	
		
	}
	
	public void updateSwitchedClaimService(int id, String customerid, String country) throws IllegalArgumentException {
	    
		//claimServices.clear();
		RequestMapIntegstaging data = new RequestMapIntegstaging(customerid, country);
	//	SingleClaimServicesServiceDBAccess(data.getDBParams(), id, customerid, country);
		//CONNECT TO THE DB, PULL DATA AND PUT IT INTO THE VIEW
		if(!claimServices.containsKey(id)){
			throw new IllegalArgumentException("Unable to find claimService with id "+id);
		}
		
		//UPDATE THE DB
		//claimServices.put(id, claimService);	
	}

	public void deleteClaimService(int id, String customerid, String country) throws IllegalArgumentException {
		
		//claimServices.clear();
		RequestMapIntegstaging data = new RequestMapIntegstaging(customerid, country);
		//SingleClaimServicesServiceDBAccess(data.getDBParams(), id, customerid, country);
		if(!claimServices.containsKey(id)){
			throw new IllegalArgumentException("Unable to find claimService with id "+id);
		}
		
		//UPDATE THE DB
		//claimServices.remove(id);
		
	}



 }
