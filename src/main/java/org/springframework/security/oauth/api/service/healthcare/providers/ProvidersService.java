package org.springframework.security.oauth.api.service.healthcare.providers;

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

import org.springframework.security.oauth.api.model.healthcare.clients.Person;
import org.springframework.security.oauth.api.model.healthcare.clients.Scheme;
import org.springframework.security.oauth.api.model.healthcare.providers.Provider;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;




@Service("providersService")
public class ProvidersService implements IProvidersService {
	
	private Map<String, Provider> providers = new HashMap<String, Provider>();
	private AtomicInteger idGen = new AtomicInteger();
	
	String guarded_cols[] = new String[] {
			"id",
			"created_at",
			"last_update"
	};
	/*
	String allowed_stg_providers_cols[] = new String[] {
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
			"providerProviderId",
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
	

    public ProvidersService(){
    }
    
	private String addProvidersServiceDBAccess(String[] DBParams, String id,
			Provider provider, String CUSTOMERID, String country) {
		
		
		System.out.println("HAVE BEEN LOOKING FOR YOU PRECIOUS!!!!!");
	       String[] SMART = getSMART_CODE(DBParams, CUSTOMERID);
	       Gson gson = new Gson();

	       HashMap MEMBERS_MAP =  gson.fromJson(SMART[43], new TypeToken<HashMap<String, String>>(){}.getType());
	       HashMap M_PROVIDERS_MAP =  gson.fromJson(SMART[48], new TypeToken<HashMap<String, String>>(){}.getType());

			String itemId = Long.toString(get());
		    String recid = itemId.substring(itemId.length() - 7);
		// TODO Auto-generated method stub

			Connection dbMemberConnection = null;
			//Connection dbConnection = null;
			PreparedStatement preparedStatement = null;

			String insertTableSQL = "INSERT INTO "+SMART[4]+"."+SMART[21]+""
					+ "(" +
					" "+MEMBERS_MAP.get("REC_ID")+"," +
					" "+MEMBERS_MAP.get("CLN_PROV_CODE")+"," +
					" "+MEMBERS_MAP.get("CLN_DESCRIPTION")+"," +
					" "+MEMBERS_MAP.get("SMART_PROV_CODE")+"," +
					" "+MEMBERS_MAP.get("TOWN")+"," +
					" "+MEMBERS_MAP.get("BUILDING")+"," +
					" "+MEMBERS_MAP.get("STREET")+"," +
					" "+MEMBERS_MAP.get("POSTAL_NR")+"," +
					" "+MEMBERS_MAP.get("TEL_NR")+"," +
					" "+MEMBERS_MAP.get("INSERT_DATE")+"," +
					" "+MEMBERS_MAP.get("FAX_NR")+"," +
					" "+MEMBERS_MAP.get("EMAIL_ADDRESS")+"," +
					" "+MEMBERS_MAP.get("CONTACT_PERSON")+"," +
					" "+MEMBERS_MAP.get("SATELITE_PROV")+"," +
					" "+MEMBERS_MAP.get("PROVIDER_STATUS")+"," +
					" "+MEMBERS_MAP.get("SMART_DESCRIPTION")+"," +
					" "+MEMBERS_MAP.get("COUNTRY_CODE")+" " +
					")" +
					" VALUES"
					+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			System.out.println(insertTableSQL);
			
	 
			try {

				dbMemberConnection = DBConnection.getConnection(DBParams);
				preparedStatement = dbMemberConnection.prepareStatement(insertTableSQL);
				preparedStatement.setString(1, recid);
				preparedStatement.setString(2, provider.getClnProvCode());
				preparedStatement.setString(3, provider.getClnDescription());
				preparedStatement.setString(4, id);
				preparedStatement.setString(5, provider.getTown());
				preparedStatement.setString(6, provider.getBuilding());
				preparedStatement.setString(7, provider.getStreet());
				preparedStatement.setString(8, provider.getPostalNumber());
				preparedStatement.setString(9, provider.getTelNumber());
				preparedStatement.setDate(10, getCurrentTimeStamp());
				preparedStatement.setString(11, provider.getFaxNumber());
				preparedStatement.setString(12, provider.getEmailAddress());
				preparedStatement.setString(13, provider.getContactPerson());
				preparedStatement.setString(14, provider.getSateliteProv());
				preparedStatement.setString(15, "0");
				preparedStatement.setString(16, provider.getSmartDescription());
				preparedStatement.setString(17, provider.getCountryCode());
				// execute insert SQL stetement
				preparedStatement.executeUpdate();

				System.out.println("Record is inserted into DBUSER table!");

			} catch (SQLException e) {
	 
				System.out.println(e.getMessage());
				throw new IllegalArgumentException(e.getMessage());
	 
			}finally {
	 
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

			Connection dbMemberConnection2 = null;
			//Connection dbConnection = null;
			PreparedStatement preparedStatement2 = null;
			
			String insertTableSQL2 = "INSERT INTO "+SMART[4]+"."+SMART[47]+""
					+ "(" +
					" "+M_PROVIDERS_MAP.get("CLN_PROV_CODE")+"," +
					" "+M_PROVIDERS_MAP.get("CLN_DESCRIPTION")+"," +
					" "+M_PROVIDERS_MAP.get("SMART_PROV_CODE")+"," +
					" "+M_PROVIDERS_MAP.get("TOWN")+"," +
					" "+M_PROVIDERS_MAP.get("BUILDING")+"," +
					" "+M_PROVIDERS_MAP.get("STREET")+"," +
					" "+M_PROVIDERS_MAP.get("POSTAL_NR")+"," +
					" "+M_PROVIDERS_MAP.get("TEL_NR")+"," +
					" "+M_PROVIDERS_MAP.get("INSERT_DATE")+"," +
					" "+M_PROVIDERS_MAP.get("FAX_NR")+"," +
					" "+M_PROVIDERS_MAP.get("EMAIL_ADDRESS")+"," +
					" "+M_PROVIDERS_MAP.get("CONTACT_PERSON")+"," +
					" "+M_PROVIDERS_MAP.get("SATELITE_PROV")+"," +
					" "+M_PROVIDERS_MAP.get("STATUS")+"," +
					" "+M_PROVIDERS_MAP.get("SMART_DESCRIPTION")+"," +
					" "+M_PROVIDERS_MAP.get("COUNTRY_CODE")+" " +
					")" +
					" VALUES"
					+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			System.out.println(insertTableSQL2);
			
	 
			try {

				dbMemberConnection2 = DBConnection.getConnection(DBParams);
				preparedStatement2 = dbMemberConnection2.prepareStatement(insertTableSQL2);
				preparedStatement2.setString(1, provider.getClnProvCode());
				preparedStatement2.setString(2, provider.getClnDescription());
				preparedStatement2.setString(3, id);
				preparedStatement2.setString(4, provider.getTown());
				preparedStatement2.setString(5, provider.getBuilding());
				preparedStatement2.setString(6, provider.getStreet());
				preparedStatement2.setString(7, provider.getPostalNumber());
				preparedStatement2.setString(8, provider.getTelNumber());
				preparedStatement2.setDate(9, getCurrentTimeStamp());
				preparedStatement2.setString(10, provider.getFaxNumber());
				preparedStatement2.setString(11, provider.getEmailAddress());
				preparedStatement2.setString(12, provider.getContactPerson());
				preparedStatement2.setString(13, provider.getSateliteProv());
				preparedStatement2.setString(14, "0");
				preparedStatement2.setString(15, provider.getSmartDescription());
				preparedStatement2.setString(16, provider.getCountryCode());
				// execute insert SQL stetement
				preparedStatement2.executeUpdate();

				System.out.println("Record is inserted into DBUSER table!");

			} catch (SQLException e) {
	 
				System.out.println(e.getMessage());
				throw new IllegalArgumentException(e.getMessage());
	 
			}finally {
				if (preparedStatement2 != null) {
					try {
						preparedStatement2.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				if (dbMemberConnection2 != null) {
					try {
						dbMemberConnection2.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			

		return id;	
	}
	
    
	public void ProvidersServiceDBAccess(String[] DBParams, String CUSTOMERID, String customercountry, int startindex, int maxresults, int status, String restrict,  String orderby){
        int lastindex  = startindex+maxresults;
		Connection connection = null;
        PreparedStatement providers_statement = null;
        PreparedStatement service_tags_statement = null;
        ResultSet providers_resultSet = null;
        ResultSet service_tags_resultSet = null;
        
        String[] SMART = getSMART_CODE(DBParams, CUSTOMERID);
        Gson gson = new Gson();
        HashMap CLAIMS_MAP =  gson.fromJson(SMART[43], new TypeToken<HashMap<String, String>>(){}.getType());
 
        try {
            connection = DBConnection.getConnection(DBParams);
            try {
				providers_statement = connection.prepareStatement("SELECT outer.* "+
						  " FROM (SELECT ROWNUM rn, inner.* "+
			        		" FROM (  SELECT e.* "+
			        		" FROM "+SMART[4]+"."+SMART[21]+" e "+
			        		" WHERE "+CLAIMS_MAP.get("PROVIDER_STATUS")+" = "+status+" "+
			        		" ORDER BY "+orderby+") inner) outer "+
			        		" WHERE outer.rn >= "+startindex+" AND outer.rn < "+lastindex+"");
				
				System.out.println("SELECT outer.* "+
						  " FROM (SELECT ROWNUM rn, inner.* "+
			        		" FROM (  SELECT e.* "+
			        		" FROM "+SMART[4]+"."+SMART[21]+" e "+
			        		" WHERE "+CLAIMS_MAP.get("PROVIDER_STATUS")+" = "+status+" "+
			        		" ORDER BY "+orderby+") inner) outer "+
			        		" WHERE outer.rn >= "+startindex+" AND outer.rn < "+lastindex+"");
				providers_resultSet = providers_statement.executeQuery();
				
				if (!providers_resultSet.isBeforeFirst() ) {    
				    throw new IllegalArgumentException("All providers have already been processed");
				   }
		
				while (providers_resultSet.next()) {
				      String rec_id = providers_resultSet.getString("rec_id");

                      addProvider(new Provider(
                    		rec_id,
                    		providers_resultSet.getString("cln_prov_code"),
      						providers_resultSet.getString("cln_description"),
      						providers_resultSet.getString("smart_prov_code"),
      						providers_resultSet.getString("town"),
      						providers_resultSet.getString("building"),
      						providers_resultSet.getString("street"),
      						providers_resultSet.getString("postal_nr"),
      						providers_resultSet.getString("tel_nr"),
      						providers_resultSet.getString("fax_nr"),
      						providers_resultSet.getString("email_address"),
      						providers_resultSet.getString("contact_person"),
      						providers_resultSet.getString("satelite_prov"),
      						providers_resultSet.getString("smart_description"),
      						providers_resultSet.getString("country_code")
                         ), rec_id);
                      
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } finally {
        	
            if (providers_resultSet != null) try { providers_resultSet.close(); } catch (SQLException ignore) {}
            if (providers_statement != null) try { providers_statement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }
        
	}
	
	
	public void ProvidersServiceDefaultDBAccess(String[] DBParams, String CUSTOMERID, String customercountry, int startindex, int maxresults, int status, String restrict,  String orderby){
        int lastindex  = startindex+maxresults;
		Connection connection = null;
        PreparedStatement providers_statement = null;
        PreparedStatement service_tags_statement = null;
        ResultSet providers_resultSet = null;
        ResultSet service_tags_resultSet = null;
        
        String[] SMART = getSMART_CODE(DBParams, CUSTOMERID);
        Gson gson = new Gson();
        HashMap CLAIMS_MAP =  gson.fromJson(SMART[43], new TypeToken<HashMap<String, String>>(){}.getType());
 
        try {
            connection = DBConnection.getConnection(DBParams);
            try {
            	//"+status+"
				providers_statement = connection.prepareStatement("SELECT outer.* "+
						  " FROM (SELECT ROWNUM rn, inner.* "+
			        		" FROM (  SELECT e.* "+
			        		" FROM INTEG_USER.SMARTAPI_PROVIDERS e "+
			        		" WHERE PICKED_STATUS = 1  "+
			        		" ORDER BY smart_description asc) inner) outer "+
			        		" WHERE outer.rn >= "+startindex+" AND outer.rn < "+lastindex+"");
				
				//"+status+"
				System.out.println("SELECT outer.* "+
						  " FROM (SELECT ROWNUM rn, inner.* "+
			        		" FROM (  SELECT e.* "+
			        		" FROM INTEG_USER.SMARTAPI_PROVIDERS e "+
			        		" WHERE PICKED_STATUS = 1  "+
			        		" ORDER BY smart_description asc) inner) outer "+
			        		" WHERE outer.rn >= "+startindex+" AND outer.rn < "+lastindex+"");
				providers_resultSet = providers_statement.executeQuery();
				
				if (!providers_resultSet.isBeforeFirst() ) {    
				    throw new IllegalArgumentException("All providers have already been processed");
				   }
		
				while (providers_resultSet.next()) {
				      String smart_prov_code = providers_resultSet.getString("smart_prov_code");

                      addProvider(new Provider(
                    		"",
                    		"",
                    		"",
      						providers_resultSet.getString("smart_prov_code"),
      						"",
      						"",
      						"",
      						"",
      						"",
      						"",
      						"",
      						"",
      						"",
      						providers_resultSet.getString("smart_description"),
      						providers_resultSet.getString("country_code")
                         ), smart_prov_code);
                      
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } finally {
        	
            if (providers_resultSet != null) try { providers_resultSet.close(); } catch (SQLException ignore) {}
            if (providers_statement != null) try { providers_statement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }
        
	}

	

	public void SingleProvidersServiceDBAccess(String[] DBParams, String provider_id, String CUSTOMERID, String country){
        Connection connection = null;
        PreparedStatement providers_statement = null;
        PreparedStatement service_tags_statement = null;
        ResultSet providers_resultSet = null;
        ResultSet service_tags_resultSet = null;
        
        String[] SMART = getSMART_CODE(DBParams, CUSTOMERID);
        Gson gson = new Gson();
        HashMap CLAIMS_MAP =  gson.fromJson(SMART[43], new TypeToken<HashMap<String, String>>(){}.getType());
        
        final String SINGLE_SQL_LIST = "select * from ( Select * from "+SMART[4]+"."+SMART[21]+" where "+CLAIMS_MAP.get("REC_ID")+" = "+provider_id+" order by insert_date asc ) where rownum <= 50";

        try {
            connection = DBConnection.getConnection(DBParams);
            try {
				providers_statement = connection.prepareStatement(SINGLE_SQL_LIST);
				providers_resultSet = providers_statement.executeQuery();
				
				
				while (providers_resultSet.next()) {
				      String rec_id = providers_resultSet.getString("rec_id");
                  
				     addProvider(new Provider(
				    		    rec_id,
	                    		providers_resultSet.getString("cln_prov_code"),
	      						providers_resultSet.getString("cln_description"),
	      						providers_resultSet.getString("smart_prov_code"),
	      						providers_resultSet.getString("town"),
	      						providers_resultSet.getString("building"),
	      						providers_resultSet.getString("street"),
	      						providers_resultSet.getString("postal_nr"),
	      						providers_resultSet.getString("tel_nr"),
	      						providers_resultSet.getString("fax_nr"),
	      						providers_resultSet.getString("email_address"),
	      						providers_resultSet.getString("contact_person"),
	      						providers_resultSet.getString("satelite_prov"),
	      						providers_resultSet.getString("smart_description"),
	      						providers_resultSet.getString("country_code")
	                         ), rec_id);

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } finally {
        	
            if (providers_resultSet != null) try { providers_resultSet.close(); } catch (SQLException ignore) {}
            if (providers_statement != null) try { providers_statement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }
        
	}
	
	
	
	public void SearchProvidersServiceDBAccess(String[] DBParams, String q, String CUSTOMERID, String country, int startindex, int maxresults, int status, String restrict,  String orderby){
		int lastindex  = startindex+maxresults;
		Connection connection = null;
        PreparedStatement providers_statement = null;
        PreparedStatement service_tags_statement = null;
        ResultSet providers_resultSet = null;
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
				providers_statement = connection.prepareStatement(SEARCH_SQL_LIST);
				
			    System.out.println("--------------------------------------HERE---------------------------------------");
			    System.out.println(SEARCH_SQL_LIST);
			    
				providers_resultSet = providers_statement.executeQuery();
				
				if (!providers_resultSet.isBeforeFirst() ) {    
				    throw new IllegalArgumentException("Your search - "+q+" - did not match any provider. "+"Suggestions:"+
				    		"Make sure all words are spelled correctly."+
				    		"Try different keywords."+
				    		"Try more general keywords.");
				   }
				

			    
			    
				while (providers_resultSet.next()) {
				     String rec_id = providers_resultSet.getString("rec_id");

				     addProvider(new Provider(
				    		    rec_id,
	                    		providers_resultSet.getString("cln_prov_code"),
	      						providers_resultSet.getString("cln_description"),
	      						providers_resultSet.getString("smart_prov_code"),
	      						providers_resultSet.getString("town"),
	      						providers_resultSet.getString("building"),
	      						providers_resultSet.getString("street"),
	      						providers_resultSet.getString("postal_nr"),
	      						providers_resultSet.getString("tel_nr"),
	      						providers_resultSet.getString("fax_nr"),
	      						providers_resultSet.getString("email_address"),
	      						providers_resultSet.getString("contact_person"),
	      						providers_resultSet.getString("satelite_prov"),
	      						providers_resultSet.getString("smart_description"),
	      						providers_resultSet.getString("country_code")
	                         ), rec_id);

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } finally {
        	
            if (providers_resultSet != null) try { providers_resultSet.close(); } catch (SQLException ignore) {}
            if (providers_statement != null) try { providers_statement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }
        
	}
	
	
	private String[] getSMART_CODE(String[] DBParams, String CUSTOMERID) {
		String[] SMART = new String[60];
        Connection connectionTB = null;
        PreparedStatement costomerTB_statement = null;
        ResultSet customerTB_resultSet = null;
        final String CUSTOMERTB_SQL_LIST = "SELECT * FROM INTEG_USER.SMARTAPI_FIN_CLIENT_MAP where CLIENT_UNIQUE = "+CUSTOMERID+" ";

        
        try {
            connectionTB = DBConnection.getConnection(DBParams);
            try {
				costomerTB_statement = connectionTB.prepareStatement(CUSTOMERTB_SQL_LIST);
				customerTB_resultSet = costomerTB_statement.executeQuery();
				
				if (!customerTB_resultSet.isBeforeFirst() ) {    
				    throw new IllegalArgumentException("No Client with id "+CUSTOMERID+ " was found");
				   }
				
			    System.out.println(CUSTOMERTB_SQL_LIST);
				while (customerTB_resultSet.next()) {		
					SMART[6] =  customerTB_resultSet.getString("SMART_CODE");
					SMART[4] =  customerTB_resultSet.getString("CLIENT_DB");
					SMART[21] =  customerTB_resultSet.getString("PROVIDERS");
					SMART[43] =  customerTB_resultSet.getString("PROVIDERS_MAP");
					SMART[47] =  customerTB_resultSet.getString("M_PROVIDERS");
					SMART[48] =  customerTB_resultSet.getString("M_PROVIDERS_MAP");
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
	



	public List<Provider> getProviders(String customerid, String country, int startindex, int maxresults, int status, String restrict,  String orderby) {
 
		String customertable = "";
		String customercountry = "";
		providers.clear();
		RequestMap data = new RequestMap(customerid, country, startindex, maxresults, status, restrict,  orderby);
		ProvidersServiceDBAccess(data.getDBParams(), customerid, customercountry, startindex, maxresults, status, restrict, orderby);
		List<Provider> allproviders = new ArrayList<Provider>(providers.values());

		return allproviders;	
	}

	
	public List<Provider> getProvidersDefault(String customerid, String country, int startindex, int maxresults, int status, String restrict,  String orderby) {
		 
		String customertable = "";
		String customercountry = "";
		providers.clear();
		RequestMap data = new RequestMap(customerid, country, startindex, maxresults, status, restrict,  orderby);
		ProvidersServiceDefaultDBAccess(data.getDBParams(), customerid, customercountry, startindex, maxresults, status, restrict, orderby);
		List<Provider> allproviders = new ArrayList<Provider>(providers.values());

		return allproviders;	
	}
	/*
	public List<Provider> getSearchProviders(String q) {
		
		providers.clear();
		q = q.toLowerCase();
		List<Provider> matchingProviders = new ArrayList<Provider>();
		
		for(Provider p : providers.values())
		{
			if(p.getMemberNames().toLowerCase().contains(q)){
				matchingProviders.add(p);
			}
		}
		
		return matchingProviders;
	}
	*/
	
	
	public List<Provider> getSearchProviders(String q, String customerid, String country, int startindex, int maxresults, int status, String restrict,  String orderby) {

		providers.clear();
		RequestMap data = new RequestMap(q, customerid, country, startindex, maxresults, status, restrict,  orderby);
		SearchProvidersServiceDBAccess(data.getDBParams(), data.getQ(), data.getCustomerId(), data.getCountry(), data.getStartIndex(), data.getMaxResults(), data.getStatus(), data.getRestrict(), data.getOrderBy());
        List<Provider> matchingProviders = new ArrayList<Provider>(providers.values());
		return matchingProviders;
	}

	

	public Provider getProvider(String id, String customerid, String country) throws IllegalArgumentException {
		
		providers.clear();
		RequestMap data = new RequestMap(customerid, country);
		SingleProvidersServiceDBAccess(data.getDBParams(), id, customerid, country);
		//CONNECT TO THE DB PULL DATA AND PUT IT INT THE VIEW
		Provider p = providers.get(id);
		if(p == null){
			throw new IllegalArgumentException("Could not find provider with id:"+id);
		}
		return p;
	}

	
	public String addProvider(Provider provider, String customerid, String country) throws IllegalArgumentException {
		
		providers.clear();
		RequestMap data = new RequestMap(customerid, country);
		SingleProvidersServiceDBAccess(data.getDBParams(), provider.getClnProvCode(), customerid, country);
		
		if(providers.containsValue(provider)){
			throw new IllegalArgumentException("Provider "+provider+" already exists.");
		}

		String id = "";
		String itemId = Long.toString(get());
	    String polid = itemId.substring(itemId.length() - 10);
	 

		id = addProvidersServiceDBAccess(data.getDBParams(), polid, provider, customerid, country);
		providers.put(id, provider);
		return id;
	}
	
	
	public String addProviderReturns(Provider provider, String customerid, String country) throws IllegalArgumentException {
		
		
		if(providers.containsValue(provider)){
			throw new IllegalArgumentException("Provider "+provider+" already exists IN THE DB.");
		}
		
		//providers.clear();
		RequestMap data = new RequestMap(customerid, country);
		//int id = idGen.incrementAndGet();
		
		//providers.put(id, provider);
		
		return "";
	}
	

	public String addProvider(Provider provider, String id) throws IllegalArgumentException {	
		/*
		if(providers.containsValue(provider)){
			throw new IllegalArgumentException("Provider "+provider+" already exists.");
		}
		*/
		//providers.clear();
		providers.put(id, provider);
		return id;
	}
	
	



	public void updateProvider(String id, String customerid, String country) throws IllegalArgumentException {
		
		//providers.clear();
		RequestMap data = new RequestMap(customerid, country);
		SingleProvidersServiceDBAccess(data.getDBParams(), id, customerid, country);
		//CONNECT TO THE DB, PULL DATA AND PUT IT INTO THE VIEW
		if(!providers.containsKey(id)){
			throw new IllegalArgumentException("Unable to find provider with id "+id);
		}
		System.out.println("The following provider was updated"+id);
		
		//UPDATE THE VIEW THEN UPDATE THE DB
		//providers.put(id);	
		
	}
	
	public void updateSwitchedProvider(String id, String customerid, String country) throws IllegalArgumentException {
	    
		//providers.clear();
		RequestMap data = new RequestMap(customerid, country);
		SingleProvidersServiceDBAccess(data.getDBParams(), id, customerid, country);
		//CONNECT TO THE DB, PULL DATA AND PUT IT INTO THE VIEW
		if(!providers.containsKey(id)){
			throw new IllegalArgumentException("Unable to find provider with id "+id);
		}
		
		//UPDATE THE DB
		//providers.put(id, provider);	
	}

	public void deleteProvider(String id, String customerid, String country) throws IllegalArgumentException {
		
		//providers.clear();
		RequestMap data = new RequestMap(customerid, country);
		SingleProvidersServiceDBAccess(data.getDBParams(), id, customerid, country);
		if(!providers.containsKey(id)){
			throw new IllegalArgumentException("Unable to find provider with id "+id);
		}
		
		//UPDATE THE DB
		//providers.remove(id);
		
	}


	private  java.sql.Date getCurrentTimeStamp() {

	     java.util.Date utilDate = new java.util.Date();
	     java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
	     System.out.println("utilDate:" + utilDate);
	     System.out.println("sqlDate:" + sqlDate);
	     
	     return sqlDate;
	}
	
	  public synchronized static long get(){
		  long current= System.currentTimeMillis();
	    return current++;
	    }
 }
