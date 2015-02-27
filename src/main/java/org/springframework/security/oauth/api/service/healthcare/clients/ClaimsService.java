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
import org.springframework.security.oauth.api.data.healthcare.clients.RequestMap;
import org.springframework.security.oauth.api.model.healthcare.clients.Claim;
import org.springframework.security.oauth.api.model.healthcare.clients.Person;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;




@Service("claimsService")
public class ClaimsService implements IClaimsService {
	
	private Map<String, Claim> claims = new HashMap<String, Claim>();
	private AtomicInteger idGen = new AtomicInteger();
	
	String guarded_cols[] = new String[] {
			"id",
			"created_at",
			"last_update"
	};
	/*
	String allowed_stg_claims_cols[] = new String[] {
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
			"providerClaimId",
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
	

    public ClaimsService(){
    }
    

    
	public void ClaimsServiceDBAccess(String[] DBParams, String CUSTOMERID, String customercountry, int startindex, int maxresults, int status, String restrict,  String orderby){
        int lastindex  = startindex+maxresults;
		Connection connection = null;
        PreparedStatement claims_statement = null;
        PreparedStatement service_tags_statement = null;
        ResultSet claims_resultSet = null;
        ResultSet service_tags_resultSet = null;
        
        String[] SMART = getSMART_CODE(DBParams, CUSTOMERID);
        Gson gson = new Gson();
        HashMap CLAIMS_MAP =  gson.fromJson(SMART[26], new TypeToken<HashMap<String, String>>(){}.getType());
 
        try {
            connection = DBConnection.getConnection(DBParams);
            try {
            	String sql_statement = "SELECT outer.* "+
						  " FROM (SELECT ROWNUM rn, inner.* "+
			        		" FROM (  SELECT e.* "+
			        		" FROM "+SMART[1]+"."+SMART[2]+" e "+
			        		" WHERE "+CLAIMS_MAP.get("STATUS")+" = "+status+" "+
			        		" ORDER BY "+orderby+") inner) outer "+
			        		" WHERE outer.rn >= "+startindex+" AND outer.rn < "+lastindex+"";
            	
            	System.out.println(sql_statement);
				claims_statement = connection.prepareStatement(sql_statement);
				claims_resultSet = claims_statement.executeQuery();
				
				if (!claims_resultSet.isBeforeFirst() ) {    
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
				while (claims_resultSet.next()) {
				    String provider_claim_id = claims_resultSet.getString("provider_claim_id");

                      addClaim(new Claim(
            		      provider_claim_id,
                          claims_resultSet.getString("policy_number"),
                          claims_resultSet.getString("provider_code"),
                          claims_resultSet.getString("membership_number"),
                          claims_resultSet.getString("member_name"),
                          claims_resultSet.getDate("transaction_date"),
                          claims_resultSet.getLong("amount"),
                          claims_resultSet.getDate("date_received"),
                   	      claims_resultSet.getString("smart_invoice_nr"),
                   	      claims_resultSet.getString("benefit_code"),
                   	      claims_resultSet.getString("status"),
                   	      claims_resultSet.getString("cln_ben_clause_code"),
                   	      claims_resultSet.getString("card_serial"),
                   	      claims_resultSet.getDate("date_received")
                         ), provider_claim_id);

                  // System.out.println("first claim"+claims_resultSet.getString("member_name"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } finally {
        	
            if (claims_resultSet != null) try { claims_resultSet.close(); } catch (SQLException ignore) {}
            if (claims_statement != null) try { claims_statement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }
        
	}
	
	

	public void SingleClaimsServiceDBAccess(String[] DBParams, String id, String CUSTOMERID, String country){
        Connection connection = null;
        PreparedStatement claims_statement = null;
        PreparedStatement service_tags_statement = null;
        ResultSet claims_resultSet = null;
        ResultSet service_tags_resultSet = null;
        
        String[] SMART = getSMART_CODE(DBParams, CUSTOMERID);
        Gson gson = new Gson();
        HashMap CLAIMS_MAP =  gson.fromJson(SMART[26], new TypeToken<HashMap<String, String>>(){}.getType());
        
        final String SINGLE_SQL_LIST = "select * from ( Select * from "+SMART[1]+"."+SMART[2]+" where "+CLAIMS_MAP.get("PROVIDER_CLAIM_ID")+" = "+id+" order by transaction_date asc ) where rownum <= 50";

        try {
            connection = DBConnection.getConnection(DBParams);
            try {
				claims_statement = connection.prepareStatement(SINGLE_SQL_LIST);
				claims_resultSet = claims_statement.executeQuery();
				
				if (!claims_resultSet.isBeforeFirst() ) {    
				    throw new IllegalArgumentException("No claim with id "+id+ " was found");
				   }
				
				while (claims_resultSet.next()) {
				      String provider_claim_id = claims_resultSet.getString("provider_claim_id");                 
                      addClaim(new Claim(
            		      provider_claim_id,
                          claims_resultSet.getString("policy_number"),
                          claims_resultSet.getString("provider_code"),
                          claims_resultSet.getString("membership_number"),
                          claims_resultSet.getString("member_name"),
                          claims_resultSet.getDate("transaction_date"),
                          claims_resultSet.getLong("amount"),
                          claims_resultSet.getDate("date_received"),
                   	      claims_resultSet.getString("smart_invoice_nr"),
                   	      claims_resultSet.getString("benefit_code"),
                   	      claims_resultSet.getString("status"),
                   	      claims_resultSet.getString("cln_ben_clause_code"),
                   	      claims_resultSet.getString("card_serial"),
                   	      claims_resultSet.getDate("date_received")
                         ), provider_claim_id);

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } finally {
        	
            if (claims_resultSet != null) try { claims_resultSet.close(); } catch (SQLException ignore) {}
            if (claims_statement != null) try { claims_statement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }
        
	}
	
	
	public void SingleClaimsSwitchedServiceDBAccess(String[] DBParams, String id, String CUSTOMERID, String country){
		 
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
        
        String[] SMART = getSMART_CODE(DBParams, CUSTOMERID);
        Gson gson = new Gson();
        HashMap CLAIMS_MAP =  gson.fromJson(SMART[26], new TypeToken<HashMap<String, String>>(){}.getType());
        
         String updateTableSQL = "UPDATE "+SMART[1]+"."+SMART[2]+" SET STATUS = ? "
				                  + " WHERE PROVIDER_CLAIM_ID = ?";
 
         
		try {
			dbConnection = DBConnection.getConnection(DBParams);
			preparedStatement = dbConnection.prepareStatement(updateTableSQL);
 
			preparedStatement.setString(1, "1");
			preparedStatement.setString(2, id);
 
			// execute update SQL stetement
			preparedStatement.executeUpdate();
 
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
        
	}
	
	
	public void SearchClaimsServiceDBAccess(String[] DBParams, String q, String CUSTOMERID, String country, int startindex, int maxresults, int status, String restrict,  String orderby){
		int lastindex  = startindex+maxresults;
		Connection connection = null;
        PreparedStatement claims_statement = null;
        PreparedStatement service_tags_statement = null;
        ResultSet claims_resultSet = null;
        ResultSet service_tags_resultSet = null;
        
       String[] SMART = getSMART_CODE(DBParams, CUSTOMERID);
       Gson gson = new Gson();
       HashMap CLAIMS_MAP =  gson.fromJson(SMART[26], new TypeToken<HashMap<String, String>>(){}.getType());

        final String SEARCH_SQL_LIST = " SELECT outer.*  FROM ( "+
        		" SELECT ROWNUM rn, inner.*  FROM ( "+  
        		" SELECT e.*  FROM "+SMART[1]+"."+SMART[2]+" e "+  
        		" WHERE "+CLAIMS_MAP.get("STATUS")+" = "+status+" AND "+
        		" ( "+
        		" "+CLAIMS_MAP.get("PROVIDER_CLAIM_ID")+" LIKE '%"+q+"%' OR  "+ 
        		" "+CLAIMS_MAP.get("POLICY_NUMBER")+"  LIKE '%"+q+"%' OR "+  
        		" "+CLAIMS_MAP.get("PROVIDER_CODE")+" LIKE '%"+q+"%' OR "+  
        		" "+CLAIMS_MAP.get("MEMBER_NAME")+" LIKE '%"+q+"%'  OR "+  
        		" "+CLAIMS_MAP.get("MEMBERSHIP_NUMBER")+" LIKE '%"+q+"%'  OR "+  
        		" "+CLAIMS_MAP.get("TRANSACTION_DATE")+" LIKE '%"+q+"%'  OR  "+ 
        		" "+CLAIMS_MAP.get("AMOUNT")+" LIKE '%"+q+"%'  OR  "+ 
        		" "+CLAIMS_MAP.get("DATE_RECEIVED")+" LIKE '%"+q+"%'  OR  "+ 
        		" "+CLAIMS_MAP.get("SMART_INVOICE_NR")+" LIKE '%"+q+"%'  OR "+  
        		" "+CLAIMS_MAP.get("PROVIDER_CLAIM_ID")+" LIKE '%"+q+"%'  "+
        		" ) ORDER BY "+orderby+"  "+
        		" ) inner) outer  "+ 
        		" WHERE outer.rn >= "+startindex+" AND outer.rn < "+lastindex+" ";
    
        try {
        	
            connection = DBConnection.getConnection(DBParams);
            
            try {
				claims_statement = connection.prepareStatement(SEARCH_SQL_LIST);
				
			    System.out.println("--------------------------------------HERE---------------------------------------");
			    System.out.println(SEARCH_SQL_LIST);
			    
				claims_resultSet = claims_statement.executeQuery();
				
				if (!claims_resultSet.isBeforeFirst() ) {    
				    throw new IllegalArgumentException("Your search - "+q+" - did not match any claim. "+"Suggestions:"+
				    		"Make sure all words are spelled correctly."+
				    		"Try different keywords."+
				    		"Try more general keywords.");
				   }
				

			    
			    
				while (claims_resultSet.next()) {
				     String provider_claim_id = claims_resultSet.getString("provider_claim_id");
                      addClaim(new Claim(
            		      provider_claim_id,
                          claims_resultSet.getString("policy_number"),
                          claims_resultSet.getString("provider_code"),
                          claims_resultSet.getString("membership_number"),
                          claims_resultSet.getString("member_name"),
                          claims_resultSet.getDate("transaction_date"),
                          claims_resultSet.getLong("amount"),
                          claims_resultSet.getDate("date_received"),
                   	      claims_resultSet.getString("smart_invoice_nr"),
                   	      claims_resultSet.getString("benefit_code"),
                   	      claims_resultSet.getString("status"),
                   	      claims_resultSet.getString("cln_ben_clause_code"),
                   	      claims_resultSet.getString("card_serial"),
                   	      claims_resultSet.getDate("date_received")
                         ), provider_claim_id);

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } finally {
        	
            if (claims_resultSet != null) try { claims_resultSet.close(); } catch (SQLException ignore) {}
            if (claims_statement != null) try { claims_statement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }
        
	}
	
	
	private String[] getSMART_CODE(String[] DBParams, String CUSTOMERID) {
		String[] SMART = new String[27];
        Connection connectionTB = null;
        PreparedStatement costomerTB_statement = null;
        ResultSet customerTB_resultSet = null;
        final String CUSTOMERTB_SQL_LIST = "Select SMART_CODE, CLIENT_DB, CLAIMS, CLAIMS_MAP from INTEG_USER.SMARTAPI_FIN_CLIENT_MAP where CLIENT_UNIQUE = "+CUSTOMERID+" ";

        
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
					SMART[2] =  customerTB_resultSet.getString("CLAIMS");
					SMART[26] =  customerTB_resultSet.getString("CLAIMS_MAP");
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
	



	public List<Claim> getClaims(String customerid, String country, int startindex, int maxresults, int status, String restrict,  String orderby) {
 
		claims.clear();
		String customertable = "";
		String customercountry = "";
		
		RequestMap data = new RequestMap(customerid, country, startindex, maxresults, status, restrict,  orderby);
		ClaimsServiceDBAccess(data.getDBParams(), customerid, customercountry, startindex, maxresults, status, restrict, orderby);
		List<Claim> allclaims = new ArrayList<Claim>(claims.values());

		return allclaims;	
	}


	/*
	public List<Claim> getSearchClaims(String q) {
		
		claims.clear();
		q = q.toLowerCase();
		List<Claim> matchingClaims = new ArrayList<Claim>();
		
		for(Claim p : claims.values())
		{
			if(p.getMemberNames().toLowerCase().contains(q)){
				matchingClaims.add(p);
			}
		}
		
		return matchingClaims;
	}
	*/
	
	
	public List<Claim> getSearchClaims(String q, String customerid, String country, int startindex, int maxresults, int status, String restrict,  String orderby) {

		claims.clear();
		RequestMap data = new RequestMap(q, customerid, country, startindex, maxresults, status, restrict,  orderby);
		SearchClaimsServiceDBAccess(data.getDBParams(), data.getQ(), data.getCustomerId(), data.getCountry(), data.getStartIndex(), data.getMaxResults(), data.getStatus(), data.getRestrict(), data.getOrderBy());
        List<Claim> matchingClaims = new ArrayList<Claim>(claims.values());
		return matchingClaims;
	}

	

	public Claim getClaim(String id, String customerid, String country) throws IllegalArgumentException {
		
		claims.clear();
		RequestMap data = new RequestMap(customerid, country);
		SingleClaimsServiceDBAccess(data.getDBParams(), id, customerid, country);
		//CONNECT TO THE DB PULL DATA AND PUT IT INT THE VIEW
		Claim p = claims.get(id);
		if(p == null){
			throw new IllegalArgumentException("Could not find claim with id:"+id);
		}
		return p;
	}

	
	public String addClaim(Claim claim, String customerid, String country) throws IllegalArgumentException {
		
		/*
		if(claims.containsValue(claim)){
			throw new IllegalArgumentException("Claim "+claim+" already exists.");
		}
		*/
		//claims.clear();
		RequestMap data = new RequestMap(customerid, country);
		int id = idGen.incrementAndGet();
		//claims.put(id, claim);
		
		String test = "";
		return test;
	}
	
	
	public String addClaimReturns(Claim claim, String customerid, String country) throws IllegalArgumentException {
		
		
		if(claims.containsValue(claim)){
			throw new IllegalArgumentException("Claim "+claim+" already exists IN THE DB.");
		}
		
		//claims.clear();
		RequestMap data = new RequestMap(customerid, country);
		int id = idGen.incrementAndGet();
		//claims.put(id, claim);
		
		String test = "";
		return test;
	}
	

	public String addClaim(Claim claim, String id) throws IllegalArgumentException {	
		
		if(claims.containsValue(claim)){
			throw new IllegalArgumentException("Claim "+claim+" already exists.");
		}
		
		claims.put(id, claim);
		return id;
	}
	
	



	public void updateClaim(String id, String customerid, String country) throws IllegalArgumentException {
		
		//claims.clear();
		RequestMap data = new RequestMap(customerid, country);
		SingleClaimsServiceDBAccess(data.getDBParams(), id, customerid, country);
		//CONNECT TO THE DB, PULL DATA AND PUT IT INTO THE VIEW
		if(!claims.containsKey(id)){
			throw new IllegalArgumentException("Unable to find claim with id "+id);
		}
		System.out.println("The following claim was updated"+id);
		
		//UPDATE THE VIEW THEN UPDATE THE DB
		//claims.put(id);	
		
	}
	
	public String updateSwitchedClaim(String id, String customerid, String country) throws IllegalArgumentException {
	    
		claims.clear();
		RequestMap data = new RequestMap(customerid, country);
		SingleClaimsServiceDBAccess(data.getDBParams(), id, customerid, country);
		//CONNECT TO THE DB, PULL DATA AND PUT IT INTO THE VIEW
		if(!claims.containsKey(id)){
			throw new IllegalArgumentException("Unable to find claim with id "+id);
		}

		List<Claim> allclaims = new ArrayList<Claim>(claims.values());	
		for (Claim listItem : allclaims) {
			
			System.out.println("I EXPECTED YOU TO WORK "+listItem.getStatus());
		    if(listItem.getStatus().equals("1")){
				throw new IllegalArgumentException("Claim with id "+id+" has already been switched");
			}
		}
		
		SingleClaimsSwitchedServiceDBAccess(data.getDBParams(), id, customerid, country);	
		return id;
	}

	public String updateSwitchedClaims(String ids, String customerid, String country) throws IllegalArgumentException {
	    
		claims.clear();
		RequestMap data = new RequestMap(customerid, country);
		String[] IDsArrays = ids.replaceAll("\\s+","").split(",");
		for (int i = 0; i < IDsArrays.length; i++) {
			System.out.println("|"+IDsArrays[i]+"|");
		}
		
		for (int i = 0; i < IDsArrays.length; i++) {
			
			SingleClaimsServiceDBAccess(data.getDBParams(), IDsArrays[i], customerid, country);
			if(!claims.containsKey(IDsArrays[i])){
				throw new IllegalArgumentException("Unable to find claim with id "+IDsArrays[i]);
			}
			
		List<Claim> allclaims = new ArrayList<Claim>(claims.values());	
		for (Claim listItem : allclaims) {
			if(listItem.getStatus().equals("1")){
					throw new IllegalArgumentException("Claim with id "+IDsArrays[i]+" has already been switched");
			  }
		   }
		
		SingleClaimsSwitchedServiceDBAccess(data.getDBParams(), IDsArrays[i], customerid, country);	
		
		}

		return ids.replaceAll("\\s+","");
	}
	public void deleteClaim(String id, String customerid, String country) throws IllegalArgumentException {
		
		//claims.clear();
		RequestMap data = new RequestMap(customerid, country);
		SingleClaimsServiceDBAccess(data.getDBParams(), id, customerid, country);
		if(!claims.containsKey(id)){
			throw new IllegalArgumentException("Unable to find claim with id "+id);
		}
		
		//UPDATE THE DB
		//claims.remove(id);
		
	}


 }
