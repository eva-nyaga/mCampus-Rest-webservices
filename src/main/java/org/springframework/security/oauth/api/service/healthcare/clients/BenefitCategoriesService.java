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
import org.springframework.security.oauth.api.model.healthcare.clients.BenefitCategory;
import org.springframework.security.oauth.api.model.healthcare.clients.Member;
import org.springframework.security.oauth.api.model.healthcare.clients.Person;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;




@Service("benefit_categoriesService")
public class BenefitCategoriesService implements IBenefit_CategoriesService {
	
	private Map<String, BenefitCategory> benefit_categories = new HashMap<String, BenefitCategory>();
	private AtomicInteger idGen = new AtomicInteger();
	
	String guarded_cols[] = new String[] {
			"id",
			"created_at",
			"last_update"
	};
	/*
	String allowed_stg_benefit_categories_cols[] = new String[] {
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
			"providerBenefit_CategoryId",
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
	

    public BenefitCategoriesService(){
    	
    }
    

    
	public void Benefit_CategoriesServiceDBAccess(String[] DBParams, String CUSTOMERID, String customercountry, int startindex, int maxresults, int status, String restrict,  String orderby){
        int lastindex  = startindex+maxresults;
		Connection connection = null;
        PreparedStatement benefit_categories_statement = null;
        PreparedStatement service_tags_statement = null;
        ResultSet benefit_categories_resultSet = null;
        ResultSet service_tags_resultSet = null;
        
        String[] SMART = getSMART_CODE(DBParams, CUSTOMERID);
        Gson gson = new Gson();
        HashMap BENEFIT_CATEGORIES_MAP =  gson.fromJson(SMART[28], new TypeToken<HashMap<String, String>>(){}.getType());
 
        try {
            connection = DBConnection.getConnection(DBParams);
            try {
				benefit_categories_statement = connection.prepareStatement("SELECT outer.* "+
						  " FROM (SELECT ROWNUM rn, inner.* "+
			        		" FROM (  SELECT e.* "+
			        		" FROM "+SMART[4]+"."+SMART[12]+" e "+
			        		" WHERE "+BENEFIT_CATEGORIES_MAP.get("STATUS")+" = "+status+" "+
			        		" ORDER BY "+orderby+") inner) outer "+
			        		" WHERE outer.rn >= "+startindex+" AND outer.rn < "+lastindex+"");
				benefit_categories_resultSet = benefit_categories_statement.executeQuery();
				
				if (!benefit_categories_resultSet.isBeforeFirst() ) {    
				    throw new IllegalArgumentException("All benefit_categories have already been processed");
				   }
				/*
				System.out.println("SELECT outer.* "+
						  " FROM (SELECT ROWNUM rn, inner.* "+
			        		" FROM (  SELECT e.* "+
			        		" FROM "+SMARTCODE+"_OWNER.STG_"+SMARTCODE+"_BENEFIT_CATEGORIES e "+
			        		" WHERE status = "+status+" "+
			        		" ORDER BY "+orderby+") inner) outer "+
			        		" WHERE outer.rn >= "+startindex+" AND outer.rn < "+lastindex+"");
				*/
				while (benefit_categories_resultSet.next()) {
				      String rec_id = benefit_categories_resultSet.getString("rec_id");
                      addBenefit_Category(new BenefitCategory(
                    		  benefit_categories_resultSet.getString("med_catg"),
                    		  benefit_categories_resultSet.getString("smart_msplan"),
                    		  benefit_categories_resultSet.getString("smart_fsplan"),
                    		  benefit_categories_resultSet.getString("smart_cat_no"),
                    		  benefit_categories_resultSet.getString("cat_desc"),
                    		  benefit_categories_resultSet.getString("in_patient_overall"),
                    		  benefit_categories_resultSet.getString("out_patient_overall"),
                    		  benefit_categories_resultSet.getString("policy_number"),
                    		  benefit_categories_resultSet.getString("auto_replenish_ind"),
                    		  benefit_categories_resultSet.getString("auto_growth_ind"),
                    		  benefit_categories_resultSet.getString("user_id"),
                    		  benefit_categories_resultSet.getString("auto_growth_pct"),
                    		  benefit_categories_resultSet.getString("cln_cat_code"),
                    		  benefit_categories_resultSet.getString("spend_threshold"),
                    		  benefit_categories_resultSet.getString("spend_threspct"),
                    		  benefit_categories_resultSet.getString("waiting_period"),
                    		  benefit_categories_resultSet.getDate("date_added"),
                    		  benefit_categories_resultSet.getString("cut_off_ind"),
                    		  benefit_categories_resultSet.getString("cut_off_age"),
                    		  benefit_categories_resultSet.getString("cln_pol_code"),
                    		  benefit_categories_resultSet.getString("cln_pol_id"),
                    		  benefit_categories_resultSet.getString("status"),
                    		  benefit_categories_resultSet.getString("smart_mplan"),
                    		  benefit_categories_resultSet.getString("smart_fplan"),
                    		  rec_id,
                    		  benefit_categories_resultSet.getString("INSURER_ID")
                         ), rec_id);

                  // System.out.println("first benefit_category"+benefit_categories_resultSet.getString("member_name"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } finally {
        	
            if (benefit_categories_resultSet != null) try { benefit_categories_resultSet.close(); } catch (SQLException ignore) {}
        	//if (service_tags_resultSet != null) try { service_tags_resultSet.close(); } catch (SQLException ignore) {}
            if (benefit_categories_statement != null) try { benefit_categories_statement.close(); } catch (SQLException ignore) {}
          //  if (service_tags_statement != null) try { service_tags_statement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }
        
	}
	
	
	

	private String addBenefit_categoriesServiceDBAccess(String[] DBParams, String id,
			BenefitCategory benefit_category, String CUSTOMERID, String country) {
		
		
		System.out.println("HAVE BEEN LOOKING FOR YOU PRECIOUS!!!!!");
		 
		 
	       String[] SMART = getSMART_CODE(DBParams, CUSTOMERID);
	       Gson gson = new Gson();
	
	       HashMap BENEFIT_CATEGORIES_MAP =  gson.fromJson(SMART[28], new TypeToken<HashMap<String, String>>(){}.getType());

			String itemId = Long.toString(get());
		    String recid = itemId.substring(itemId.length() - 7);
		    
	       System.out.println(SMART[28]);
	       System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
	       System.out.println(BENEFIT_CATEGORIES_MAP.get("MED_CATG"));
		// TODO Auto-generated method stub
			Connection dbMemberConnection = null;
			//Connection dbConnection = null;
			PreparedStatement preparedStatement = null;
	 
			String insertTableSQL = "INSERT INTO "+SMART[4]+"."+SMART[12]+""
					+ "(" +
					" "+BENEFIT_CATEGORIES_MAP.get("MED_CATG")+"," +
					" "+BENEFIT_CATEGORIES_MAP.get("SMART_MSPLAN")+"," +
					" "+BENEFIT_CATEGORIES_MAP.get("SMART_FSPLAN")+"," +
					" "+BENEFIT_CATEGORIES_MAP.get("SMART_CAT_NO")+"," +
					" "+BENEFIT_CATEGORIES_MAP.get("CAT_DESC")+"," +
					" "+BENEFIT_CATEGORIES_MAP.get("IN_PATIENT_OVERALL")+"," +
					" "+BENEFIT_CATEGORIES_MAP.get("OUT_PATIENT_OVERALL")+"," +
					" "+BENEFIT_CATEGORIES_MAP.get("POLICY_NUMBER")+"," +
					" "+BENEFIT_CATEGORIES_MAP.get("AUTO_REPLENISH_IND")+"," +
					" "+BENEFIT_CATEGORIES_MAP.get("AUTO_GROWTH_IND")+"," +
					" "+BENEFIT_CATEGORIES_MAP.get("USER_ID")+"," +
					" "+BENEFIT_CATEGORIES_MAP.get("AUTO_GROWTH_PCT")+"," +
					" "+BENEFIT_CATEGORIES_MAP.get("CLN_CAT_CODE")+"," +
					" "+BENEFIT_CATEGORIES_MAP.get("SPEND_THRESHOLD")+"," +
					" "+BENEFIT_CATEGORIES_MAP.get("SPEND_THRESPCT")+"," +
					" "+BENEFIT_CATEGORIES_MAP.get("WAITING_PERIOD")+"," +
					" "+BENEFIT_CATEGORIES_MAP.get("DATE_ADDED")+"," +
					" "+BENEFIT_CATEGORIES_MAP.get("CUT_OFF_IND")+"," +
					" "+BENEFIT_CATEGORIES_MAP.get("CUT_OFF_AGE")+"," +
					" "+BENEFIT_CATEGORIES_MAP.get("CLN_POL_CODE")+"," +
					" "+BENEFIT_CATEGORIES_MAP.get("CLN_POL_ID")+"," +
					" "+BENEFIT_CATEGORIES_MAP.get("STATUS")+"," +
					" "+BENEFIT_CATEGORIES_MAP.get("SMART_MPLAN")+"," +
					" "+BENEFIT_CATEGORIES_MAP.get("REC_ID")+"," +
					" "+BENEFIT_CATEGORIES_MAP.get("SMART_FPLAN")+"," +
					" "+BENEFIT_CATEGORIES_MAP.get("INSURER_ID")+" " +
					")" +
					" VALUES"
					+ "( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	 
			
			
			try {

				dbMemberConnection = DBConnection.getConnection(DBParams);
				preparedStatement = dbMemberConnection.prepareStatement(insertTableSQL);
				preparedStatement.setString(1, benefit_category.getMedCatg());
				preparedStatement.setString(2, benefit_category.getSmartMsplan());
				preparedStatement.setString(3, benefit_category.getSmartFsplan());
				preparedStatement.setString(4, benefit_category.getSmartCatNo());
				preparedStatement.setString(5, benefit_category.getCatDesc());
				preparedStatement.setString(6, benefit_category.getInPatientOverall());
				preparedStatement.setString(7, benefit_category.getOutPatientOverall());
				preparedStatement.setString(8, benefit_category.getPolicyNumber());
				preparedStatement.setString(9, benefit_category.getAutoReplenishInd());
				preparedStatement.setString(10, benefit_category.getAutoGrowthInd());
				preparedStatement.setString(11, benefit_category.getUserId());
				preparedStatement.setString(12, benefit_category.getAutoGrowthPct());
				preparedStatement.setString(13, benefit_category.getClnCatCode());
				preparedStatement.setString(14, benefit_category.getSpendThreshold());
				preparedStatement.setString(15, benefit_category.getSpendThrespct());
				preparedStatement.setString(16, benefit_category.getWaitingPeriod());
				preparedStatement.setDate(17, getCurrentTimeStamp());
				preparedStatement.setString(18, benefit_category.getCutOffInd());
				preparedStatement.setString(19, benefit_category.getCutOffAge());
				preparedStatement.setString(20, benefit_category.getClnPolCode());
				preparedStatement.setString(21, benefit_category.getClnPolId());
				preparedStatement.setString(22, "0");
				preparedStatement.setString(23, benefit_category.getSmartMplan());
				preparedStatement.setString(24, recid);
				preparedStatement.setString(25, benefit_category.getSmartFplan());
				preparedStatement.setString(26, benefit_category.getInsurerId());

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
	

	public void SingleBenefit_CategoriesServiceDBAccess(String[] DBParams, String id, String CUSTOMERID, String country){
        Connection connection = null;
        PreparedStatement benefit_categories_statement = null;
        PreparedStatement service_tags_statement = null;
        ResultSet benefit_categories_resultSet = null;
        ResultSet service_tags_resultSet = null;
        
        String[] SMART = getSMART_CODE(DBParams, CUSTOMERID);
        Gson gson = new Gson();
        HashMap BENEFIT_CATEGORIES_MAP =  gson.fromJson(SMART[28], new TypeToken<HashMap<String, String>>(){}.getType());
        
        final String SINGLE_SQL_LIST = "select * from ( Select * from "+SMART[4]+"."+SMART[12]+" where "+BENEFIT_CATEGORIES_MAP.get("REC_ID")+" = "+id+" order by date_added asc ) where rownum <= 50";

        try {
            connection = DBConnection.getConnection(DBParams);
            try {
				benefit_categories_statement = connection.prepareStatement(SINGLE_SQL_LIST);
				benefit_categories_resultSet = benefit_categories_statement.executeQuery();
				
				if (!benefit_categories_resultSet.isBeforeFirst() ) {    
				    throw new IllegalArgumentException("No benefit_category with id "+id+ " was found");
				   }
				
				while (benefit_categories_resultSet.next()) {
					String rec_id = benefit_categories_resultSet.getString("rec_id");

                      addBenefit_Category(new BenefitCategory(
                    		  benefit_categories_resultSet.getString("med_catg"),
                    		  benefit_categories_resultSet.getString("smart_msplan"),
                    		  benefit_categories_resultSet.getString("smart_fsplan"),
                    		  benefit_categories_resultSet.getString("smart_cat_no"),
                    		  benefit_categories_resultSet.getString("cat_desc"),
                    		  benefit_categories_resultSet.getString("in_patient_overall"),
                    		  benefit_categories_resultSet.getString("out_patient_overall"),
                    		  benefit_categories_resultSet.getString("policy_number"),
                    		  benefit_categories_resultSet.getString("auto_replenish_ind"),
                    		  benefit_categories_resultSet.getString("auto_growth_ind"),
                    		  benefit_categories_resultSet.getString("user_id"),
                    		  benefit_categories_resultSet.getString("auto_growth_pct"),
                    		  benefit_categories_resultSet.getString("cln_cat_code"),
                    		  benefit_categories_resultSet.getString("spend_threshold"),
                    		  benefit_categories_resultSet.getString("spend_threspct"),
                    		  benefit_categories_resultSet.getString("waiting_period"),
                    		  benefit_categories_resultSet.getDate("date_added"),
                    		  benefit_categories_resultSet.getString("cut_off_ind"),
                    		  benefit_categories_resultSet.getString("cut_off_age"),
                    		  benefit_categories_resultSet.getString("cln_pol_code"),
                    		  benefit_categories_resultSet.getString("cln_pol_id"),
                    		  benefit_categories_resultSet.getString("status"),
                    		  benefit_categories_resultSet.getString("smart_mplan"),
                    		  benefit_categories_resultSet.getString("smart_fplan"),
                    		  rec_id,
                    		  benefit_categories_resultSet.getString("INSURER_ID")
                         ), rec_id);

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } finally {
        	
            if (benefit_categories_resultSet != null) try { benefit_categories_resultSet.close(); } catch (SQLException ignore) {}
        	//if (service_tags_resultSet != null) try { service_tags_resultSet.close(); } catch (SQLException ignore) {}
            if (benefit_categories_statement != null) try { benefit_categories_statement.close(); } catch (SQLException ignore) {}
          //  if (service_tags_statement != null) try { service_tags_statement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }
        
	}
	
	
	
	public void SearchBenefit_CategoriesServiceDBAccess(String[] DBParams, String q, String CUSTOMERID, String country, int startindex, int maxresults, int status, String restrict,  String orderby){
		int lastindex  = startindex+maxresults;
		Connection connection = null;
        PreparedStatement benefit_categories_statement = null;
        PreparedStatement service_tags_statement = null;
        ResultSet benefit_categories_resultSet = null;
        ResultSet service_tags_resultSet = null;
        
       String[] SMART = getSMART_CODE(DBParams, CUSTOMERID);
       Gson gson = new Gson();
       HashMap BENEFIT_CATEGORIES_MAP =  gson.fromJson(SMART[28], new TypeToken<HashMap<String, String>>(){}.getType());

       
       
       
       
       
       
       
       
   
     
        final String SEARCH_SQL_LIST = " SELECT outer.*  FROM ( "+
        		" SELECT ROWNUM rn, inner.*  FROM ( "+  
        		" SELECT e.*  FROM "+SMART[4]+"."+SMART[12]+" e "+  
        		" WHERE "+BENEFIT_CATEGORIES_MAP.get("STATUS")+" = "+status+" AND "+
        		" ( "+
        		" "+BENEFIT_CATEGORIES_MAP.get("PROVIDER_BENEFIT_CATEGORY_ID")+" LIKE '%"+q+"%' OR  "+ 
        		" "+BENEFIT_CATEGORIES_MAP.get("POLICY_NUMBER")+"  LIKE '%"+q+"%' OR "+  
        		" "+BENEFIT_CATEGORIES_MAP.get("PROVIDER_CODE")+" LIKE '%"+q+"%' OR "+  
        		" "+BENEFIT_CATEGORIES_MAP.get("MEMBER_NAME")+" LIKE '%"+q+"%'  OR "+  
        		" "+BENEFIT_CATEGORIES_MAP.get("MEMBERSHIP_NUMBER")+" LIKE '%"+q+"%'  OR "+  
        		" "+BENEFIT_CATEGORIES_MAP.get("TRANSACTION_DATE")+" LIKE '%"+q+"%'  OR  "+ 
        		" "+BENEFIT_CATEGORIES_MAP.get("AMOUNT")+" LIKE '%"+q+"%'  OR  "+ 
        		" "+BENEFIT_CATEGORIES_MAP.get("DATE_RECEIVED")+" LIKE '%"+q+"%'  OR  "+ 
        		" "+BENEFIT_CATEGORIES_MAP.get("SMART_INVOICE_NR")+" LIKE '%"+q+"%'  OR "+  
        		" "+BENEFIT_CATEGORIES_MAP.get("PROVIDER_BENEFIT_CATEGORY_ID")+" LIKE '%"+q+"%'  "+
        		" ) ORDER BY "+orderby+"  "+
        		" ) inner) outer  "+ 
        		" WHERE outer.rn >= "+startindex+" AND outer.rn < "+lastindex+" ";
    
        try {
        	
            connection = DBConnection.getConnection(DBParams);
            
            try {
				benefit_categories_statement = connection.prepareStatement(SEARCH_SQL_LIST);
				
			    System.out.println("--------------------------------------HERE---------------------------------------");
			    System.out.println(SEARCH_SQL_LIST);
			    
				benefit_categories_resultSet = benefit_categories_statement.executeQuery();
				
				if (!benefit_categories_resultSet.isBeforeFirst() ) {    
				    throw new IllegalArgumentException("Your search - "+q+" - did not match any benefit_category. "+"Suggestions:"+
				    		"Make sure all words are spelled correctly."+
				    		"Try different keywords."+
				    		"Try more general keywords.");
				   }
				

			    
			    
				while (benefit_categories_resultSet.next()) {
					String rec_id = benefit_categories_resultSet.getString("rec_id");

					 int benefit_categoryId = idGen.incrementAndGet();
				     int transactionId = idGen.incrementAndGet();
				     String serviceDescription= "twertret";//service Description
				     String serviceCode= "097090";//service Code
				     String serviceAmount= "3434";//service Amount
				     String quantity= "6";
				     String diagnosis= "jkfkfgkh";
				     String serviceType= "ghkgk"; //-  consumable  or service
				     String serviceCodeType= "56546";  //- nappi / rpl
				     String serviceDate= "2/3/2014"; 
  
                      addBenefit_Category(new BenefitCategory(
                    		  benefit_categories_resultSet.getString("med_catg"),
                    		  benefit_categories_resultSet.getString("smart_msplan"),
                    		  benefit_categories_resultSet.getString("smart_fsplan"),
                    		  benefit_categories_resultSet.getString("smart_cat_no"),
                    		  benefit_categories_resultSet.getString("cat_desc"),
                    		  benefit_categories_resultSet.getString("in_patient_overall"),
                    		  benefit_categories_resultSet.getString("out_patient_overall"),
                    		  benefit_categories_resultSet.getString("policy_number"),
                    		  benefit_categories_resultSet.getString("auto_replenish_ind"),
                    		  benefit_categories_resultSet.getString("auto_growth_ind"),
                    		  benefit_categories_resultSet.getString("user_id"),
                    		  benefit_categories_resultSet.getString("auto_growth_pct"),
                    		  benefit_categories_resultSet.getString("cln_cat_code"),
                    		  benefit_categories_resultSet.getString("spend_threshold"),
                    		  benefit_categories_resultSet.getString("spend_threspct"),
                    		  benefit_categories_resultSet.getString("waiting_period"),
                    		  benefit_categories_resultSet.getDate("date_added"),
                    		  benefit_categories_resultSet.getString("cut_off_ind"),
                    		  benefit_categories_resultSet.getString("cut_off_age"),
                    		  benefit_categories_resultSet.getString("cln_pol_code"),
                    		  benefit_categories_resultSet.getString("cln_pol_id"),
                    		  benefit_categories_resultSet.getString("status"),
                    		  benefit_categories_resultSet.getString("smart_mplan"),
                    		  benefit_categories_resultSet.getString("smart_fplan"),
                    		  rec_id,
                    		  benefit_categories_resultSet.getString("INSURER_ID")
                         ), rec_id);

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } finally {
        	
            if (benefit_categories_resultSet != null) try { benefit_categories_resultSet.close(); } catch (SQLException ignore) {}
        	//if (service_tags_resultSet != null) try { service_tags_resultSet.close(); } catch (SQLException ignore) {}
            if (benefit_categories_statement != null) try { benefit_categories_statement.close(); } catch (SQLException ignore) {}
          //  if (service_tags_statement != null) try { service_tags_statement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }
        
	}
	
	
	private String[] getSMART_CODE(String[] DBParams, String CUSTOMERID) {
		String[] SMART = new String[50];
        Connection connectionTB = null;
        PreparedStatement costomerTB_statement = null;
        ResultSet customerTB_resultSet = null;
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
					SMART[12] =  customerTB_resultSet.getString("BENEFITS_CATG");
					SMART[28] =  customerTB_resultSet.getString("BENEFITS_CATG_MAP");				
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
	



	public List<BenefitCategory> getBenefit_Categories(String customerid, String country, int startindex, int maxresults, int status, String restrict,  String orderby) {
 
		String customertable = "";
		String customercountry = "";
		benefit_categories.clear();
		RequestMap data = new RequestMap(customerid, country, startindex, maxresults, status, restrict,  orderby);
		Benefit_CategoriesServiceDBAccess(data.getDBParams(), customerid, customercountry, startindex, maxresults, status, restrict, orderby);
		List<BenefitCategory> allbenefit_categories = new ArrayList<BenefitCategory>(benefit_categories.values());

		return allbenefit_categories;	
	}


	/*
	public List<Benefit_Category> getSearchBenefit_Categories(String q) {
		
		benefit_categories.clear();
		q = q.toLowerCase();
		List<Benefit_Category> matchingBenefit_Categories = new ArrayList<Benefit_Category>();
		
		for(Benefit_Category p : benefit_categories.values())
		{
			if(p.getMemberNames().toLowerCase().contains(q)){
				matchingBenefit_Categories.add(p);
			}
		}
		
		return matchingBenefit_Categories;
	}
	*/
	
	
	public List<BenefitCategory> getSearchBenefit_Categories(String q, String customerid, String country, int startindex, int maxresults, int status, String restrict,  String orderby) {

		benefit_categories.clear();
		RequestMap data = new RequestMap(q, customerid, country, startindex, maxresults, status, restrict,  orderby);
		SearchBenefit_CategoriesServiceDBAccess(data.getDBParams(), data.getQ(), data.getCustomerId(), data.getCountry(), data.getStartIndex(), data.getMaxResults(), data.getStatus(), data.getRestrict(), data.getOrderBy());
        List<BenefitCategory> matchingBenefit_Categories = new ArrayList<BenefitCategory>(benefit_categories.values());
		return matchingBenefit_Categories;
	}

	

	public BenefitCategory getBenefit_Category(String id, String customerid, String country) throws IllegalArgumentException {
		
		benefit_categories.clear();
		RequestMap data = new RequestMap(customerid, country);
		SingleBenefit_CategoriesServiceDBAccess(data.getDBParams(), id, customerid, country);
		//CONNECT TO THE DB PULL DATA AND PUT IT INT THE VIEW
		BenefitCategory p = benefit_categories.get(id);
		if(p == null){
			throw new IllegalArgumentException("Could not find benefit_category with id:"+id);
		}
		return p;
	}

	
	public String addBenefit_Category(BenefitCategory benefit_category, String customerid, String country) throws IllegalArgumentException {
		benefit_categories.clear();
		
		if(benefit_categories.containsValue(benefit_category)){
			throw new IllegalArgumentException("Benefit category "+benefit_category+" already exists.");
		}
		//members.clear();
		RequestMap data = new RequestMap(customerid, country);
		String id = "";

		id = addBenefit_categoriesServiceDBAccess(data.getDBParams(), id, benefit_category, customerid, country);
		benefit_categories.put(id, benefit_category);
		
		return id;
	}
	
	
	public String addBenefit_CategoryReturns(BenefitCategory benefit_category, String customerid, String country) throws IllegalArgumentException {
		
		benefit_categories.clear();
		if(benefit_categories.containsValue(benefit_category)){
			throw new IllegalArgumentException("Benefit_Category "+benefit_category+" already exists IN THE DB.");
		}
		
		//benefit_categories.clear();
		RequestMap data = new RequestMap(customerid, country);
		int id = idGen.incrementAndGet();
		String test = "";
		benefit_categories.put(test, benefit_category);
		
		return test;
	}
	

	public String addBenefit_Category(BenefitCategory benefit_category, String rec_id) throws IllegalArgumentException {	
		/*
		if(benefit_categories.containsValue(benefit_category)){
			throw new IllegalArgumentException("Benefit_Category "+benefit_category+" already exists.");
		}
		*/
		//benefit_categories.clear();
		benefit_categories.put(rec_id, benefit_category);
		return rec_id;
	}



	public void updateBenefit_Category(String id, String customerid, String country) throws IllegalArgumentException {
		
		//benefit_categories.clear();
		RequestMap data = new RequestMap(customerid, country);
		//SingleBenefit_CategoriesServiceDBAccess(data.getDBParams(), id, customerid, country);
		//CONNECT TO THE DB, PULL DATA AND PUT IT INTO THE VIEW
		if(!benefit_categories.containsKey(id)){
			throw new IllegalArgumentException("Unable to find benefit_category with id "+id);
		}
		System.out.println("The following benefit_category was updated"+id);
		
		//UPDATE THE VIEW THEN UPDATE THE DB
		//benefit_categories.put(id);	
		
	}
	
	public void updateSwitchedBenefit_Category(String id, String customerid, String country) throws IllegalArgumentException {
	    
		//benefit_categories.clear();
		RequestMap data = new RequestMap(customerid, country);
		//SingleBenefit_CategoriesServiceDBAccess(data.getDBParams(), id, customerid, country);
		//CONNECT TO THE DB, PULL DATA AND PUT IT INTO THE VIEW
		if(!benefit_categories.containsKey(id)){
			throw new IllegalArgumentException("Unable to find benefit_category with id "+id);
		}
		
		//UPDATE THE DB
		//benefit_categories.put(id, benefit_category);	
	}

	public void deleteBenefit_Category(String id, String customerid, String country) throws IllegalArgumentException {
		
		//benefit_categories.clear();
		RequestMap data = new RequestMap(customerid, country);
		//SingleBenefit_CategoriesServiceDBAccess(data.getDBParams(), id, customerid, country);
		if(!benefit_categories.containsKey(id)){
			throw new IllegalArgumentException("Unable to find benefit_category with id "+id);
		}
		
		//UPDATE THE DB
		//benefit_categories.remove(id);
		
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
