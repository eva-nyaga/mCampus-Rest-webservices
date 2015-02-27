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
import org.springframework.security.oauth.api.model.healthcare.clients.Status;
import org.springframework.security.oauth.api.model.healthcare.clients.Person;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;




@Service("statusesService")
public class StatusesService implements IStatusesService {
	
	private Map<Integer, Status> statuses = new HashMap<Integer, Status>();
	private AtomicInteger idGen = new AtomicInteger();
	
	String guarded_cols[] = new String[] {
			"id",
			"created_at",
			"last_update"
	};
	/*
	String allowed_stg_statuses_cols[] = new String[] {
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
			"providerStatusId",
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
	

    public StatusesService(){
    }
    

    
	public void StatusesServiceDBAccess(String[] DBParams, String CUSTOMERID, String customercountry, int startindex, int maxresults, int status, String restrict,  String orderby){
        int lastindex  = startindex+maxresults;
		Connection connection = null;
        PreparedStatement statuses_statement = null;
        PreparedStatement service_tags_statement = null;
        ResultSet statuses_resultSet = null;
        ResultSet service_tags_resultSet = null;
        
        String[] SMART = getSMART_CODE(DBParams, CUSTOMERID);
        Gson gson = new Gson();
        HashMap STATUSES_MAP =  gson.fromJson(SMART[26], new TypeToken<HashMap<String, String>>(){}.getType());
 
        try {
            connection = DBConnection.getConnection(DBParams);
            try {
				statuses_statement = connection.prepareStatement("SELECT outer.* "+
						  " FROM (SELECT ROWNUM rn, inner.* "+
			        		" FROM (  SELECT e.* "+
			        		" FROM "+SMART[1]+"."+SMART[2]+" e "+
			        		" WHERE "+STATUSES_MAP.get("STATUS")+" = "+status+" "+
			        		" ORDER BY "+orderby+") inner) outer "+
			        		" WHERE outer.rn >= "+startindex+" AND outer.rn < "+lastindex+"");
				statuses_resultSet = statuses_statement.executeQuery();
				
				if (!statuses_resultSet.isBeforeFirst() ) {    
				    throw new IllegalArgumentException("All statuses have already been processed");
				   }
				/*
				System.out.println("SELECT outer.* "+
						  " FROM (SELECT ROWNUM rn, inner.* "+
			        		" FROM (  SELECT e.* "+
			        		" FROM "+SMARTCODE+"_OWNER.STG_"+SMARTCODE+"_STATUSES e "+
			        		" WHERE status = "+status+" "+
			        		" ORDER BY "+orderby+") inner) outer "+
			        		" WHERE outer.rn >= "+startindex+" AND outer.rn < "+lastindex+"");
				*/
				while (statuses_resultSet.next()) {
				     int provider_status_id = statuses_resultSet.getInt("provider_status_id");
					 int statusId = idGen.incrementAndGet();
				     int transactionId = idGen.incrementAndGet();

                      addStatus(new Status(
            		      provider_status_id,
                          statuses_resultSet.getString("policy_number"),
                          statuses_resultSet.getString("provider_code"),
                          statuses_resultSet.getString("membership_number"),
                          statuses_resultSet.getString("member_name"),
                          statuses_resultSet.getDate("transaction_date"),
                          statuses_resultSet.getLong("amount"),
                          statuses_resultSet.getDate("date_received"),
                   	      statuses_resultSet.getString("smart_invoice_nr")
                         ), provider_status_id);

                  // System.out.println("first status"+statuses_resultSet.getString("member_name"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } finally {
        	
            if (statuses_resultSet != null) try { statuses_resultSet.close(); } catch (SQLException ignore) {}
        	//if (service_tags_resultSet != null) try { service_tags_resultSet.close(); } catch (SQLException ignore) {}
            if (statuses_statement != null) try { statuses_statement.close(); } catch (SQLException ignore) {}
          //  if (service_tags_statement != null) try { service_tags_statement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }
        
	}
	
	

	public void SingleStatusesServiceDBAccess(String[] DBParams, int status_id, String CUSTOMERID, String country){
        Connection connection = null;
        PreparedStatement statuses_statement = null;
        PreparedStatement service_tags_statement = null;
        ResultSet statuses_resultSet = null;
        ResultSet service_tags_resultSet = null;
        
        String[] SMART = getSMART_CODE(DBParams, CUSTOMERID);
        Gson gson = new Gson();
        HashMap STATUSES_MAP =  gson.fromJson(SMART[26], new TypeToken<HashMap<String, String>>(){}.getType());
        
        final String SINGLE_SQL_LIST = "select * from ( Select * from "+SMART[1]+"."+SMART[2]+" where "+STATUSES_MAP.get("PROVIDER_STATUS_ID")+" = "+status_id+" order by transaction_date asc ) where rownum <= 50";

        try {
            connection = DBConnection.getConnection(DBParams);
            try {
				statuses_statement = connection.prepareStatement(SINGLE_SQL_LIST);
				statuses_resultSet = statuses_statement.executeQuery();
				
				if (!statuses_resultSet.isBeforeFirst() ) {    
				    throw new IllegalArgumentException("No status with id "+status_id+ " was found");
				   }
				
				while (statuses_resultSet.next()) {
				      int provider_status_id = statuses_resultSet.getInt("provider_status_id");

					 int statusId = idGen.incrementAndGet();
				     int transactionId = idGen.incrementAndGet();

                      addStatus(new Status(
            		      provider_status_id,
                          statuses_resultSet.getString("policy_number"),
                          statuses_resultSet.getString("provider_code"),
                          statuses_resultSet.getString("membership_number"),
                          statuses_resultSet.getString("member_name"),
                          statuses_resultSet.getDate("transaction_date"),
                          statuses_resultSet.getLong("amount"),
                          statuses_resultSet.getDate("date_received"),
                   	      statuses_resultSet.getString("smart_invoice_nr")
                         ), provider_status_id);

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } finally {
        	
            if (statuses_resultSet != null) try { statuses_resultSet.close(); } catch (SQLException ignore) {}
        	//if (service_tags_resultSet != null) try { service_tags_resultSet.close(); } catch (SQLException ignore) {}
            if (statuses_statement != null) try { statuses_statement.close(); } catch (SQLException ignore) {}
          //  if (service_tags_statement != null) try { service_tags_statement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }
        
	}
	
	
	
	public void SearchStatusesServiceDBAccess(String[] DBParams, String q, String CUSTOMERID, String country, int startindex, int maxresults, int status, String restrict,  String orderby){
		int lastindex  = startindex+maxresults;
		Connection connection = null;
        PreparedStatement statuses_statement = null;
        PreparedStatement service_tags_statement = null;
        ResultSet statuses_resultSet = null;
        ResultSet service_tags_resultSet = null;
        
       String[] SMART = getSMART_CODE(DBParams, CUSTOMERID);
       Gson gson = new Gson();
       HashMap STATUSES_MAP =  gson.fromJson(SMART[26], new TypeToken<HashMap<String, String>>(){}.getType());

       
       
       
       
       
       
       
       
   
     
        final String SEARCH_SQL_LIST = " SELECT outer.*  FROM ( "+
        		" SELECT ROWNUM rn, inner.*  FROM ( "+  
        		" SELECT e.*  FROM "+SMART[1]+"."+SMART[2]+" e "+  
        		" WHERE "+STATUSES_MAP.get("STATUS")+" = "+status+" AND "+
        		" ( "+
        		" "+STATUSES_MAP.get("PROVIDER_STATUS_ID")+" LIKE '%"+q+"%' OR  "+ 
        		" "+STATUSES_MAP.get("POLICY_NUMBER")+"  LIKE '%"+q+"%' OR "+  
        		" "+STATUSES_MAP.get("PROVIDER_CODE")+" LIKE '%"+q+"%' OR "+  
        		" "+STATUSES_MAP.get("MEMBER_NAME")+" LIKE '%"+q+"%'  OR "+  
        		" "+STATUSES_MAP.get("MEMBERSHIP_NUMBER")+" LIKE '%"+q+"%'  OR "+  
        		" "+STATUSES_MAP.get("TRANSACTION_DATE")+" LIKE '%"+q+"%'  OR  "+ 
        		" "+STATUSES_MAP.get("AMOUNT")+" LIKE '%"+q+"%'  OR  "+ 
        		" "+STATUSES_MAP.get("DATE_RECEIVED")+" LIKE '%"+q+"%'  OR  "+ 
        		" "+STATUSES_MAP.get("SMART_INVOICE_NR")+" LIKE '%"+q+"%'  OR "+  
        		" "+STATUSES_MAP.get("PROVIDER_STATUS_ID")+" LIKE '%"+q+"%'  "+
        		" ) ORDER BY "+orderby+"  "+
        		" ) inner) outer  "+ 
        		" WHERE outer.rn >= "+startindex+" AND outer.rn < "+lastindex+" ";
    
        try {
        	
            connection = DBConnection.getConnection(DBParams);
            
            try {
				statuses_statement = connection.prepareStatement(SEARCH_SQL_LIST);
				
			    System.out.println("--------------------------------------HERE---------------------------------------");
			    System.out.println(SEARCH_SQL_LIST);
			    
				statuses_resultSet = statuses_statement.executeQuery();
				
				if (!statuses_resultSet.isBeforeFirst() ) {    
				    throw new IllegalArgumentException("Your search - "+q+" - did not match any status. "+"Suggestions:"+
				    		"Make sure all words are spelled correctly."+
				    		"Try different keywords."+
				    		"Try more general keywords.");
				   }
				

			    
			    
				while (statuses_resultSet.next()) {
				      int provider_status_id = statuses_resultSet.getInt("provider_status_id");

					 int statusId = idGen.incrementAndGet();
				     int transactionId = idGen.incrementAndGet();
   
                      addStatus(new Status(
            		      provider_status_id,
                          statuses_resultSet.getString("policy_number"),
                          statuses_resultSet.getString("provider_code"),
                          statuses_resultSet.getString("membership_number"),
                          statuses_resultSet.getString("member_name"),
                          statuses_resultSet.getDate("transaction_date"),
                          statuses_resultSet.getLong("amount"),
                          statuses_resultSet.getDate("date_received"),
                   	      statuses_resultSet.getString("smart_invoice_nr")
                         ), provider_status_id);

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } finally {
        	
            if (statuses_resultSet != null) try { statuses_resultSet.close(); } catch (SQLException ignore) {}
        	//if (service_tags_resultSet != null) try { service_tags_resultSet.close(); } catch (SQLException ignore) {}
            if (statuses_statement != null) try { statuses_statement.close(); } catch (SQLException ignore) {}
          //  if (service_tags_statement != null) try { service_tags_statement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }
        
	}
	
	
	private String[] getSMART_CODE(String[] DBParams, String CUSTOMERID) {
		String[] SMART = new String[27];
        Connection connectionTB = null;
        PreparedStatement costomerTB_statement = null;
        ResultSet customerTB_resultSet = null;
        final String CUSTOMERTB_SQL_LIST = "Select SMART_CODE, CLIENT_DB, STATUSES, STATUSES_MAP from INTEG_USER.SMARTAPI_FIN_CLIENT_MAP where CLIENT_UNIQUE = "+CUSTOMERID+" ";

        
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
					SMART[2] =  customerTB_resultSet.getString("STATUSES");
					SMART[26] =  customerTB_resultSet.getString("STATUSES_MAP");
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
	



	public List<Status> getStatuses(String customerid, String country, int startindex, int maxresults, int status, String restrict,  String orderby) {
 
		String customertable = "";
		String customercountry = "";
		//statuses.clear();
		RequestMap data = new RequestMap(customerid, country, startindex, maxresults, status, restrict,  orderby);
		StatusesServiceDBAccess(data.getDBParams(), customertable, customercountry, startindex, maxresults, status, restrict, orderby);
		List<Status> allstatuses = new ArrayList<Status>(statuses.values());

		return allstatuses;	
	}


	/*
	public List<Status> getSearchStatuses(String q) {
		
		statuses.clear();
		q = q.toLowerCase();
		List<Status> matchingStatuses = new ArrayList<Status>();
		
		for(Status p : statuses.values())
		{
			if(p.getMemberNames().toLowerCase().contains(q)){
				matchingStatuses.add(p);
			}
		}
		
		return matchingStatuses;
	}
	*/
	
	
	public List<Status> getSearchStatuses(String q, String customerid, String country, int startindex, int maxresults, int status, String restrict,  String orderby) {

		statuses.clear();
		RequestMap data = new RequestMap(q, customerid, country, startindex, maxresults, status, restrict,  orderby);
		SearchStatusesServiceDBAccess(data.getDBParams(), data.getQ(), data.getCustomerId(), data.getCountry(), data.getStartIndex(), data.getMaxResults(), data.getStatus(), data.getRestrict(), data.getOrderBy());
        List<Status> matchingStatuses = new ArrayList<Status>(statuses.values());
		return matchingStatuses;
	}

	

	public Status getStatus(int id, String customerid, String country) throws IllegalArgumentException {
		
		statuses.clear();
		RequestMap data = new RequestMap(customerid, country);
		SingleStatusesServiceDBAccess(data.getDBParams(), id, customerid, country);
		//CONNECT TO THE DB PULL DATA AND PUT IT INT THE VIEW
		Status p = statuses.get(id);
		if(p == null){
			throw new IllegalArgumentException("Could not find status with id:"+id);
		}
		return p;
	}

	
	public int addStatus(Status status, String customerid, String country) throws IllegalArgumentException {
		
		
		if(statuses.containsValue(status)){
			throw new IllegalArgumentException("Status "+status+" already exists.");
		}
		
		//statuses.clear();
		RequestMap data = new RequestMap(customerid, country);
		int id = idGen.incrementAndGet();
		statuses.put(id, status);
		
		return id;
	}
	
	
	public int addStatusReturns(Status status, String customerid, String country) throws IllegalArgumentException {
		
		
		if(statuses.containsValue(status)){
			throw new IllegalArgumentException("Status "+status+" already exists IN THE DB.");
		}
		
		//statuses.clear();
		RequestMap data = new RequestMap(customerid, country);
		int id = idGen.incrementAndGet();
		statuses.put(id, status);
		
		return id;
	}
	

	public int addStatus(Status status, int id) throws IllegalArgumentException {	
		/*
		if(statuses.containsValue(status)){
			throw new IllegalArgumentException("Status "+status+" already exists.");
		}
		*/
		//statuses.clear();
		statuses.put(id, status);
		return id;
	}


	public void updateStatus(int id, String customerid, String country) throws IllegalArgumentException {
		
		//statuses.clear();
		RequestMap data = new RequestMap(customerid, country);
		SingleStatusesServiceDBAccess(data.getDBParams(), id, customerid, country);
		//CONNECT TO THE DB, PULL DATA AND PUT IT INTO THE VIEW
		if(!statuses.containsKey(id)){
			throw new IllegalArgumentException("Unable to find status with id "+id);
		}
		System.out.println("The following status was updated"+id);
		
		//UPDATE THE VIEW THEN UPDATE THE DB
		//statuses.put(id);	
		
	}
	
	public void updateSwitchedStatus(int id, String customerid, String country) throws IllegalArgumentException {
	    
		//statuses.clear();
		RequestMap data = new RequestMap(customerid, country);
		SingleStatusesServiceDBAccess(data.getDBParams(), id, customerid, country);
		//CONNECT TO THE DB, PULL DATA AND PUT IT INTO THE VIEW
		if(!statuses.containsKey(id)){
			throw new IllegalArgumentException("Unable to find status with id "+id);
		}
		
		//UPDATE THE DB
		//statuses.put(id, status);	
	}

	public void deleteStatus(int id, String customerid, String country) throws IllegalArgumentException {
		
		//statuses.clear();
		RequestMap data = new RequestMap(customerid, country);
		SingleStatusesServiceDBAccess(data.getDBParams(), id, customerid, country);
		if(!statuses.containsKey(id)){
			throw new IllegalArgumentException("Unable to find status with id "+id);
		}
		
		//UPDATE THE DB
		//statuses.remove(id);
		
	}


 }
