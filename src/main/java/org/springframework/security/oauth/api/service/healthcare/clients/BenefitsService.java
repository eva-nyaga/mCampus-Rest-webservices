package org.springframework.security.oauth.api.service.healthcare.clients;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.security.oauth.api.data.healthcare.clients.DBConnection;
import org.springframework.security.oauth.api.data.healthcare.clients.RequestMapIntegstaging;
import org.springframework.security.oauth.api.model.healthcare.clients.Benefit;
import org.springframework.security.oauth.api.model.healthcare.clients.Person;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service("benefitsService")
public class BenefitsService implements IBenefitsService {
	
	private Map<String, Benefit> benefits = new HashMap<String, Benefit>();
	private AtomicInteger idGen = new AtomicInteger();
	
	String guarded_cols[] = new String[] {
			"id",
			"created_at",
			"last_update"
	};
	/*
	String allowed_stg_benefits_cols[] = new String[] {
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
			"providerBenefitId",
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
	

    public BenefitsService(){
    }
    

    
	public void BenefitsServiceDBAccess(String[] DBParams, String CUSTOMERID, String customercountry, int startindex, int maxresults, int status, String restrict,  String orderby){
        int lastindex  = startindex+maxresults;
		Connection connection = null;
        PreparedStatement benefits_statement = null;
        PreparedStatement service_tags_statement = null;
        ResultSet benefits_resultSet = null;
        ResultSet service_tags_resultSet = null;
        
        String[] SMART = getSMART_CODE(DBParams, CUSTOMERID);
        Gson gson = new Gson();
        HashMap BENEFITS_MAP =  gson.fromJson(SMART[27], new TypeToken<HashMap<String, String>>(){}.getType());
 
        try {
            connection = DBConnection.getConnection(DBParams);
            try {
				benefits_statement = connection.prepareStatement("SELECT outer.* "+
						  " FROM (SELECT ROWNUM rn, inner.* "+
			        		" FROM (  SELECT e.* "+
			        		" FROM "+SMART[4]+"."+SMART[10]+" e "+
			        		" WHERE "+BENEFITS_MAP.get("STATUS")+" = "+status+" "+
			        		" ORDER BY "+orderby+") inner) outer "+
			        		" WHERE outer.rn >= "+startindex+" AND outer.rn < "+lastindex+"");
				
								
				benefits_resultSet = benefits_statement.executeQuery();
				
				if (!benefits_resultSet.isBeforeFirst() ) {    
				    throw new IllegalArgumentException("All benefits have already been processed");
				   }
				
				System.out.println("SELECT outer.* "+
						  " FROM (SELECT ROWNUM rn, inner.* "+
			        		" FROM (  SELECT e.* "+
			        		" FROM "+SMART[4]+"."+SMART[10]+" e "+
			        		" WHERE "+BENEFITS_MAP.get("STATUS")+" = "+status+" "+
			        		" ORDER BY "+orderby+") inner) outer "+
			        		" WHERE outer.rn >= "+startindex+" AND outer.rn < "+lastindex+"");
	
				while (benefits_resultSet.next()) {
	                String stg_benefit_id = 	benefits_resultSet.getString("rec_Id");

                      addBenefit(new Benefit(
                   	benefits_resultSet.getString("BENEFIT_DESC"),
                   	benefits_resultSet.getString("POLICY_NUMBER"),
                   	benefits_resultSet.getString("CAT_CODE"),
                   	benefits_resultSet.getString("BEN_TYPE_ID"),
                   	benefits_resultSet.getString("CO_PAY_AMT"),
                   	benefits_resultSet.getString("CO_PAY_PERCENT"),
                   	benefits_resultSet.getString("BEN_LINKED2_ID"),
                   	benefits_resultSet.getString("SUB_LIMIT_AMT"),
                   	benefits_resultSet.getString("LIMIT_AMT"),
                   	benefits_resultSet.getDate("DATE_ADDED"),
                   	benefits_resultSet.getString("GENDER_APPLICABILITY"),
                   	benefits_resultSet.getString("WAITING_PERIOD"),
                   	benefits_resultSet.getDate("EFFECTIVE_DATE"),
                   	benefits_resultSet.getString("SERVICE_TYPE"),
                   	benefits_resultSet.getString("MEM_ASSIGNED_BENEFIT"),
                   	benefits_resultSet.getString("SPEND_THRESHOLD"),
                   	benefits_resultSet.getString("SPEND_THRESPCT"),
                   	benefits_resultSet.getString("AUTO_REPLENISH_IND"),
                   	benefits_resultSet.getDate("BEN_END_DATE"),
                   	benefits_resultSet.getString("USER_ID"),
                   	benefits_resultSet.getString("CLN_BEN_CODE"),
                   	benefits_resultSet.getString("CLN_BEN_CLAUSE_CODE"),
                   	benefits_resultSet.getString("BEN_TYP_DESC"),
                   	benefits_resultSet.getString("BEN_LINKED2_CLNCODE"),
                   	benefits_resultSet.getString("AUTO_GROWTH_IND"),
                   	benefits_resultSet.getString("AUTO_GROWTH_PCT"),
                   	benefits_resultSet.getString("CLN_POL_CODE"),
                   	benefits_resultSet.getString("CLN_POL_ID"),
                   	stg_benefit_id,
                   	benefits_resultSet.getString("SMART_BEN_ID"),
                   	"",  	
                   	benefits_resultSet.getString("COPAY_IND"),
                   	benefits_resultSet.getString("AUTO_REPLENISH_LIMTYPE"),
                   	benefits_resultSet.getString("AUTO_REPLENISH_LIMIT"),
                   	benefits_resultSet.getString("AUTO_GROWTH_RATEIND"),
                   	benefits_resultSet.getString("AUTO_GROWTH_RATE"),
                   	benefits_resultSet.getString("AUTO_GROWTH_CEILING"),
                   	benefits_resultSet.getString("CUT_OFF_IND"),
                   	benefits_resultSet.getString("CUT_OFF_AGE"),
                   	benefits_resultSet.getString("INSURER_ID"),
                   	benefits_resultSet.getString("LAYERED_IND"),
                   	benefits_resultSet.getString("LAYER1_TYPE"),
                   	benefits_resultSet.getString("LAYER1_VALUE"),
                   	benefits_resultSet.getString("LAYER2_TYPE"),
                   	benefits_resultSet.getString("LAYER2_VALUE"),
                   	benefits_resultSet.getString("LAYER3_TYPE"),
                   	benefits_resultSet.getString("LAYER3_VALUE")
                         ), stg_benefit_id);

                  // System.out.println("first benefit"+benefits_resultSet.getString("member_name"));

                         
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } finally {
        	
            if (benefits_resultSet != null) try { benefits_resultSet.close(); } catch (SQLException ignore) {}
        	//if (service_tags_resultSet != null) try { service_tags_resultSet.close(); } catch (SQLException ignore) {}
            if (benefits_statement != null) try { benefits_statement.close(); } catch (SQLException ignore) {}
          //  if (service_tags_statement != null) try { service_tags_statement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }
        
	}
	
	
	
	public void BenefitsServiceDefaultDBAccess(String[] DBParams, String CUSTOMERID, String customercountry, int startindex, int maxresults, int status, String restrict,  String orderby){
        int lastindex  = startindex+maxresults;
		Connection connection = null;
        PreparedStatement benefits_statement = null;
        PreparedStatement service_tags_statement = null;
        ResultSet benefits_resultSet = null;
        ResultSet service_tags_resultSet = null;
        
        String[] SMART = getSMART_CODE(DBParams, CUSTOMERID);
        Gson gson = new Gson();
      //  HashMap CLAIMS_MAP =  gson.fromJson(SMART[43], new TypeToken<HashMap<String, String>>(){}.getType());
 
        try {
            connection = DBConnection.getConnection(DBParams);
            try {
            	//"+status+"
				benefits_statement = connection.prepareStatement("SELECT outer.* "+
						  " FROM (SELECT ROWNUM rn, inner.* "+
			        		" FROM (  SELECT e.* "+
			        		" FROM INTEG_USER.SMARTAPI_STANDARD_POOLS e "+
			        		" WHERE PICKED_STATUS = 1  "+
			        		" ORDER BY BENEFIT_CODE asc) inner) outer "+
			        		" WHERE outer.rn >= "+startindex+" AND outer.rn < "+lastindex+"");
				
				//"+status+"
				System.out.println("SELECT outer.* "+
						  " FROM (SELECT ROWNUM rn, inner.* "+
			        		" FROM (  SELECT e.* "+
			        		" FROM INTEG_USER.SMARTAPI_STANDARD_POOLS e "+
			        		" WHERE PICKED_STATUS = 1  "+
			        		" ORDER BY BENEFIT_CODE asc) inner) outer "+
			        		" WHERE outer.rn >= "+startindex+" AND outer.rn < "+lastindex+"");
				benefits_resultSet = benefits_statement.executeQuery();
				
				if (!benefits_resultSet.isBeforeFirst() ) {    
				    throw new IllegalArgumentException("All benefits have already been processed");
				   }
		
				while (benefits_resultSet.next()) {
					
	                String smart_benefit_id = 	benefits_resultSet.getString("benefit_code");
	                
                    addBenefit(new Benefit(
                 	benefits_resultSet.getString("BENEFIT_DESC"),
                 	"",
                 	"",
                 	"",
                 	"",
                 	"",
                 	"",
                 	"",
                 	"",
                 	benefits_resultSet.getDate("PICKED_DATE"),
                 	"",
                 	"",
                 	benefits_resultSet.getDate("PICKED_DATE"),
                 	"",
                 	"",
                 	"",
                 	"",
                 	"",
                 	benefits_resultSet.getDate("PICKED_DATE"),
                 	"",
                 	"",
                 	"",
                 	"",
                 	"",
                 	"",
                 	"",
                 	"",
                 	"",
                 	smart_benefit_id,
                 	smart_benefit_id,
                 	benefits_resultSet.getString("POINT_USAGE"),
                 	"",
                 	"",
                 	"",
                 	"",
                 	"",
                 	"",
                 	"",
                 	"",
                 	"",
                 	"",
                 	"",
                 	"",
                 	"",
                 	"",
                 	"",
                 	""
                       ), smart_benefit_id);
        
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } finally {
        	
            if (benefits_resultSet != null) try { benefits_resultSet.close(); } catch (SQLException ignore) {}
            if (benefits_statement != null) try { benefits_statement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }
        
	}


	public void SingleBenefitsServiceDBAccess(String[] DBParams, String benefit_id, String CUSTOMERID, String country){
        Connection connection = null;
        PreparedStatement benefits_statement = null;
        PreparedStatement service_tags_statement = null;
        ResultSet benefits_resultSet = null;
        ResultSet service_tags_resultSet = null;
        
        String[] SMART = getSMART_CODE(DBParams, CUSTOMERID);
        Gson gson = new Gson();
        HashMap BENEFITS_MAP =  gson.fromJson(SMART[27], new TypeToken<HashMap<String, String>>(){}.getType());
        
        final String SINGLE_SQL_LIST = "select * from ( Select * from "+SMART[4]+"."+SMART[10]+" where "+BENEFITS_MAP.get("REC_ID")+" = "+benefit_id+" order by date_added asc ) where rownum <= 50";

        try {
            connection = DBConnection.getConnection(DBParams);
            try {
				benefits_statement = connection.prepareStatement(SINGLE_SQL_LIST);
				benefits_resultSet = benefits_statement.executeQuery();
				
				if (!benefits_resultSet.isBeforeFirst() ) {    
				    throw new IllegalArgumentException("No benefit with id "+benefit_id+ " was found");
				   }
				
				while (benefits_resultSet.next()) {
	                String stg_benefit_id = 	benefits_resultSet.getString("rec_Id");

                    addBenefit(new Benefit(
                           	benefits_resultSet.getString("BENEFIT_DESC"),
                           	benefits_resultSet.getString("POLICY_NUMBER"),
                           	benefits_resultSet.getString("CAT_CODE"),
                           	benefits_resultSet.getString("BEN_TYPE_ID"),
                           	benefits_resultSet.getString("CO_PAY_AMT"),
                           	benefits_resultSet.getString("CO_PAY_PERCENT"),
                           	benefits_resultSet.getString("BEN_LINKED2_ID"),
                           	benefits_resultSet.getString("SUB_LIMIT_AMT"),
                           	benefits_resultSet.getString("LIMIT_AMT"),
                           	benefits_resultSet.getDate("DATE_ADDED"),
                           	benefits_resultSet.getString("GENDER_APPLICABILITY"),
                           	benefits_resultSet.getString("WAITING_PERIOD"),
                           	benefits_resultSet.getDate("EFFECTIVE_DATE"),
                           	benefits_resultSet.getString("SERVICE_TYPE"),
                           	benefits_resultSet.getString("MEM_ASSIGNED_BENEFIT"),
                           	benefits_resultSet.getString("SPEND_THRESHOLD"),
                           	benefits_resultSet.getString("SPEND_THRESPCT"),
                           	benefits_resultSet.getString("AUTO_REPLENISH_IND"),
                           	benefits_resultSet.getDate("BEN_END_DATE"),
                           	benefits_resultSet.getString("USER_ID"),
                           	benefits_resultSet.getString("CLN_BEN_CODE"),
                           	benefits_resultSet.getString("CLN_BEN_CLAUSE_CODE"),
                           	benefits_resultSet.getString("BEN_TYP_DESC"),
                           	benefits_resultSet.getString("BEN_LINKED2_CLNCODE"),
                           	benefits_resultSet.getString("AUTO_GROWTH_IND"),
                           	benefits_resultSet.getString("AUTO_GROWTH_PCT"),
                           	benefits_resultSet.getString("CLN_POL_CODE"),
                           	benefits_resultSet.getString("CLN_POL_ID"),
                           	stg_benefit_id,
                           	benefits_resultSet.getString("SMART_BEN_ID"),
                           	"",
                           	benefits_resultSet.getString("COPAY_IND"),
                           	benefits_resultSet.getString("AUTO_REPLENISH_LIMTYPE"),
                           	benefits_resultSet.getString("AUTO_REPLENISH_LIMIT"),
                           	benefits_resultSet.getString("AUTO_GROWTH_RATEIND"),
                           	benefits_resultSet.getString("AUTO_GROWTH_RATE"),
                           	benefits_resultSet.getString("AUTO_GROWTH_CEILING"),
                           	benefits_resultSet.getString("CUT_OFF_IND"),
                           	benefits_resultSet.getString("CUT_OFF_AGE"),
                           	benefits_resultSet.getString("INSURER_ID"),
                           	benefits_resultSet.getString("LAYERED_IND"),
                           	benefits_resultSet.getString("LAYER1_TYPE"),
                           	benefits_resultSet.getString("LAYER1_VALUE"),
                           	benefits_resultSet.getString("LAYER2_TYPE"),
                           	benefits_resultSet.getString("LAYER2_VALUE"),
                           	benefits_resultSet.getString("LAYER3_TYPE"),
                           	benefits_resultSet.getString("LAYER3_VALUE")
                       ), stg_benefit_id);

             
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } finally {
        	
            if (benefits_resultSet != null) try { benefits_resultSet.close(); } catch (SQLException ignore) {}
        	//if (service_tags_resultSet != null) try { service_tags_resultSet.close(); } catch (SQLException ignore) {}
            if (benefits_statement != null) try { benefits_statement.close(); } catch (SQLException ignore) {}
          //  if (service_tags_statement != null) try { service_tags_statement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }
        
	}
	
	
	private String addBenefitsServiceDBAccess(String[] DBParams, String id,
			Benefit benefit, String CUSTOMERID, String country) {
		
		
		System.out.println("HAVE BEEN LOOKING FOR YOU PRECIOUS!!!!!");
	       String[] SMART = getSMART_CODE(DBParams, CUSTOMERID);
	       Gson gson = new Gson();
	       String key = "";
	       HashMap MEMBERS_MAP =  gson.fromJson(SMART[27], new TypeToken<HashMap<String, String>>(){}.getType());

		// TODO Auto-generated method stub
			Connection dbMemberConnection = null;
			PreparedStatement preparedStatement = null;
			Connection dbBenefitConnection = null;
			PreparedStatement preparedBenefitStatement = null;
			String itemId = Long.toString(get());
		    String recid = itemId.substring(itemId.length() - 7);
			
	 
			String insertTableSQL = "INSERT INTO "+SMART[4]+"."+SMART[10]+""
					+ "(" +
					" "+MEMBERS_MAP.get("CLN_POL_ID")+"," +
					" "+MEMBERS_MAP.get("CLN_POL_CODE")+"," +
					" "+MEMBERS_MAP.get("AUTO_GROWTH_PCT")+"," +
					" "+MEMBERS_MAP.get("AUTO_GROWTH_IND")+"," +
					" "+MEMBERS_MAP.get("BEN_LINKED2_CLNCODE")+"," +
					" "+MEMBERS_MAP.get("BEN_TYP_DESC")+"," +
					" "+MEMBERS_MAP.get("CLN_BEN_CODE")+"," +
					" "+MEMBERS_MAP.get("CLN_BEN_CLAUSE_CODE")+"," +
					" "+MEMBERS_MAP.get("USER_ID")+"," +
					" "+MEMBERS_MAP.get("BEN_END_DATE")+"," +
					" "+MEMBERS_MAP.get("AUTO_REPLENISH_IND")+"," +
					" "+MEMBERS_MAP.get("SPEND_THRESPCT")+"," +
					" "+MEMBERS_MAP.get("SPEND_THRESHOLD")+"," +
					" "+MEMBERS_MAP.get("MEM_ASSIGNED_BENEFIT")+"," +
					" "+MEMBERS_MAP.get("SERVICE_TYPE")+"," +
					" "+MEMBERS_MAP.get("EFFECTIVE_DATE")+"," +
					" "+MEMBERS_MAP.get("WAITING_PERIOD")+"," +
					" "+MEMBERS_MAP.get("GENDER_APPLICABILITY")+"," +
					" "+MEMBERS_MAP.get("LIMIT_AMT")+"," +
					" "+MEMBERS_MAP.get("SUB_LIMIT_AMT")+"," +					
					" "+MEMBERS_MAP.get("BEN_LINKED2_ID")+"," +
					" "+MEMBERS_MAP.get("CO_PAY_PERCENT")+"," +
					" "+MEMBERS_MAP.get("CO_PAY_AMT")+"," +
					" "+MEMBERS_MAP.get("BEN_TYPE_ID")+"," +
					" "+MEMBERS_MAP.get("CAT_CODE")+"," +
					" "+MEMBERS_MAP.get("POLICY_NUMBER")+"," +
					" "+MEMBERS_MAP.get("BENEFIT_DESC")+"," +
					" "+MEMBERS_MAP.get("DATE_ADDED")+"," +
					" "+MEMBERS_MAP.get("REC_ID")+"," +
					" "+MEMBERS_MAP.get("STATUS")+", " +
					" "+MEMBERS_MAP.get("SMART_BEN_ID")+", " +
					" "+MEMBERS_MAP.get("COPAY_IND")+"," +
					" "+MEMBERS_MAP.get("AUTO_REPLENISH_LIMTYPE")+"," +
					" "+MEMBERS_MAP.get("AUTO_REPLENISH_LIMIT")+"," +
					" "+MEMBERS_MAP.get("AUTO_GROWTH_RATEIND")+"," +
					" "+MEMBERS_MAP.get("AUTO_GROWTH_RATE")+"," +
					" "+MEMBERS_MAP.get("AUTO_GROWTH_CEILING")+"," +
					" "+MEMBERS_MAP.get("CUT_OFF_IND")+", " +
					" "+MEMBERS_MAP.get("CUT_OFF_AGE")+", " +
					" "+MEMBERS_MAP.get("INSURER_ID")+", " +
					" "+MEMBERS_MAP.get("LAYERED_IND")+", " +
					" "+MEMBERS_MAP.get("LAYER1_TYPE")+", " +
					" "+MEMBERS_MAP.get("LAYER1_VALUE")+", " +
					" "+MEMBERS_MAP.get("LAYER2_TYPE")+", " +
					" "+MEMBERS_MAP.get("LAYER2_VALUE")+", " +
					" "+MEMBERS_MAP.get("LAYER3_TYPE")+", " +
					" "+MEMBERS_MAP.get("LAYER3_VALUE")+" " +
					")" +
					" VALUES"
					+ "( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			try {

               	
				dbMemberConnection = DBConnection.getConnection(DBParams);
				preparedStatement = dbMemberConnection.prepareStatement(insertTableSQL);
				preparedStatement.setString(1, benefit.getClnPolId());
				preparedStatement.setString(2, benefit.getClnPolCode());
				preparedStatement.setString(3, benefit.getAutoGrowthPct());
				preparedStatement.setString(4, benefit.getAutoGrowthInd());
				preparedStatement.setString(5, benefit.getBenLinked2Tqcode());
				preparedStatement.setString(6, benefit.getBenTypDesc());
				preparedStatement.setString(7, benefit.getClnBenCode());
				preparedStatement.setString(8, benefit.getClnBenClauseCode());
				preparedStatement.setString(9, benefit.getUserId());
				preparedStatement.setDate(10, benefit.getBenEndDate());
				preparedStatement.setString(11, benefit.getAutoReplenishInd());
				preparedStatement.setString(12, benefit.getSpendThrespct());
				preparedStatement.setString(13, benefit.getSpendThreshold());
				preparedStatement.setString(14, benefit.getMemAssignedBenefit());
				preparedStatement.setString(15, benefit.getServiceType());
				preparedStatement.setDate(16, benefit.getEffectiveDate());
				preparedStatement.setString(17, benefit.getWaitingPeriod());
				preparedStatement.setString(18, benefit.getGenderApplicability().toUpperCase());
				preparedStatement.setString(19, benefit.getLimitAmt());
				preparedStatement.setString(20, benefit.getSubLimitAmt());
				preparedStatement.setString(21, benefit.getBenLinked2Id());
				preparedStatement.setString(22, benefit.getCoPayPercent());
				preparedStatement.setString(23, benefit.getCoPayAmt());
				preparedStatement.setString(24, benefit.getBenTypeId());
				preparedStatement.setString(25, benefit.getCatCode());
				preparedStatement.setString(26, benefit.getPolicyNumber());
				preparedStatement.setString(27, benefit.getBenefitDesc());
				preparedStatement.setDate(28, getCurrentTimeStamp());
				preparedStatement.setString(29, recid);
				preparedStatement.setString(30, "0");
				preparedStatement.setString(31, benefit.getSmartBenId());
				preparedStatement.setString(32, benefit.getCopayInd());
				preparedStatement.setString(33, benefit.getAutoReplenishLimtype());
				preparedStatement.setString(34, benefit.getAutoReplenishLimit());
				preparedStatement.setString(35, benefit.getAutoGrowthRateInd());
				preparedStatement.setString(36, benefit.getAutoGrowthRate());
				preparedStatement.setString(37, benefit.getAutoGrowthCeiling());
				preparedStatement.setString(38, benefit.getCutOffInd());
				preparedStatement.setString(39, benefit.getCutOffAge());
				preparedStatement.setString(40, benefit.getInsurerId());
				preparedStatement.setString(41, benefit.getLayeredInd());
				preparedStatement.setString(42, benefit.getLayered1Type());
				preparedStatement.setString(43, benefit.getLayered1Value());
				preparedStatement.setString(44, benefit.getLayered2Type());
				preparedStatement.setString(45, benefit.getLayered2Value());
				preparedStatement.setString(46, benefit.getLayered3Type());
				preparedStatement.setString(47, benefit.getLayered3Value());
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
			
			
			String insertTableSQLMAPBENEFITS = "INSERT INTO "+SMART[4]+"."+SMART[46]+""
					+ "(" +
					" REC_ID," +
					" CLN_BEN_CODE," +
					" CLN_DESCRIPTION," +
					" SMART_POOL_NUMBER," +
					" SMART_DESC," +
					" INSURER_ID," +
					" PICKED_STATUS," +
					" INSERT_DATE" +
					")" +
					" VALUES"
					+ "( ?, ?, ?, ?, ?, ?, ?, ?)";
			
			
			try {
				dbBenefitConnection = DBConnection.getConnection(DBParams);
				preparedBenefitStatement = dbBenefitConnection.prepareStatement(insertTableSQLMAPBENEFITS);
				preparedBenefitStatement.setString(1, recid);
				preparedBenefitStatement.setString(2, benefit.getClnBenCode());
				preparedBenefitStatement.setString(3, benefit.getBenefitDesc());
				preparedBenefitStatement.setString(4, benefit.getSmartBenId());
				preparedBenefitStatement.setString(5, benefit.getBenTypDesc());
				preparedBenefitStatement.setString(6, benefit.getInsurerId());				
				preparedBenefitStatement.setString(7, "0");
				preparedBenefitStatement.setDate(8, getCurrentTimeStamp());

				// execute insert SQL stetement
				preparedBenefitStatement.executeUpdate();
		} catch (SQLException e) {
 
			System.out.println(e.getMessage());
			throw new IllegalArgumentException(e.getMessage());
 
		} finally {
 
			if (preparedBenefitStatement != null) {
				try {
					preparedBenefitStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
 
			if (dbBenefitConnection != null) {
				try {
					dbBenefitConnection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
 
		}

		return recid;	
	}
	
	
	
	
	public void SearchBenefitsServiceDBAccess(String[] DBParams, String q, String CUSTOMERID, String country, int startindex, int maxresults, int status, String restrict,  String orderby){
		int lastindex  = startindex+maxresults;
		Connection connection = null;
        PreparedStatement benefits_statement = null;
        PreparedStatement service_tags_statement = null;
        ResultSet benefits_resultSet = null;
        ResultSet service_tags_resultSet = null;
        
       String[] SMART = getSMART_CODE(DBParams, CUSTOMERID);
       Gson gson = new Gson();
       HashMap BENEFITS_MAP =  gson.fromJson(SMART[26], new TypeToken<HashMap<String, String>>(){}.getType());

       
       
       
       
       
       
       
       
   
     
        final String SEARCH_SQL_LIST = " SELECT outer.*  FROM ( "+
        		" SELECT ROWNUM rn, inner.*  FROM ( "+  
        		" SELECT e.*  FROM "+SMART[1]+"."+SMART[2]+" e "+  
        		" WHERE "+BENEFITS_MAP.get("STATUS")+" = "+status+" AND "+
        		" ( "+
        		" "+BENEFITS_MAP.get("PROVIDER_BENEFIT_ID")+" LIKE '%"+q+"%' OR  "+ 
        		" "+BENEFITS_MAP.get("POLICY_NUMBER")+"  LIKE '%"+q+"%' OR "+  
        		" "+BENEFITS_MAP.get("PROVIDER_CODE")+" LIKE '%"+q+"%' OR "+  
        		" "+BENEFITS_MAP.get("MEMBER_NAME")+" LIKE '%"+q+"%'  OR "+  
        		" "+BENEFITS_MAP.get("MEMBERSHIP_NUMBER")+" LIKE '%"+q+"%'  OR "+  
        		" "+BENEFITS_MAP.get("TRANSACTION_DATE")+" LIKE '%"+q+"%'  OR  "+ 
        		" "+BENEFITS_MAP.get("AMOUNT")+" LIKE '%"+q+"%'  OR  "+ 
        		" "+BENEFITS_MAP.get("DATE_RECEIVED")+" LIKE '%"+q+"%'  OR  "+ 
        		" "+BENEFITS_MAP.get("SMART_INVOICE_NR")+" LIKE '%"+q+"%'  OR "+  
        		" "+BENEFITS_MAP.get("PROVIDER_BENEFIT_ID")+" LIKE '%"+q+"%'  "+
        		" ) ORDER BY "+orderby+"  "+
        		" ) inner) outer  "+ 
        		" WHERE outer.rn >= "+startindex+" AND outer.rn < "+lastindex+" ";
    
        try {
        	
            connection = DBConnection.getConnection(DBParams);
            
            try {
				benefits_statement = connection.prepareStatement(SEARCH_SQL_LIST);
				
			    System.out.println("--------------------------------------HERE---------------------------------------");
			    System.out.println(SEARCH_SQL_LIST);
			    
				benefits_resultSet = benefits_statement.executeQuery();
				
				if (!benefits_resultSet.isBeforeFirst() ) {    
				    throw new IllegalArgumentException("Your search - "+q+" - did not match any benefit. "+"Suggestions:"+
				    		"Make sure all words are spelled correctly."+
				    		"Try different keywords."+
				    		"Try more general keywords.");
				   }
				

			    
			    
				while (benefits_resultSet.next()) {
	                String stg_benefit_id = 	benefits_resultSet.getString("rec_Id");

                    addBenefit(new Benefit(
                 	benefits_resultSet.getString("benefitDesc"),
                 	benefits_resultSet.getString("policyNumber"),
                 	benefits_resultSet.getString("catCode"),
                 	benefits_resultSet.getString("benTypeId"),
                 	benefits_resultSet.getString("coPayAmt"),
                 	benefits_resultSet.getString("coPayPercent"),
                 	benefits_resultSet.getString("benLinked2Id"),
                 	benefits_resultSet.getString("subLimitAmt"),
                 	benefits_resultSet.getString("limitAmt"),
                 	benefits_resultSet.getDate("dateAdded"),
                 	benefits_resultSet.getString("genderApplicability"),
                 	benefits_resultSet.getString("waitingPeriod"),
                 	benefits_resultSet.getDate("effectiveDate"),
                 	benefits_resultSet.getString("serviceType"),
                 	benefits_resultSet.getString("memAssignedBenefit"),
                 	benefits_resultSet.getString("spendThreshold"),
                 	benefits_resultSet.getString("spendThrespct"),
                 	benefits_resultSet.getString("autoReplenishInd"),
                 	benefits_resultSet.getDate("benEndDate"),
                 	benefits_resultSet.getString("userId"),
                 	benefits_resultSet.getString("clnBenCode"),
                 	benefits_resultSet.getString("clnBenClauseCode"),
                 	benefits_resultSet.getString("benTypDesc"),
                 	benefits_resultSet.getString("benLinked2CLNcode"),
                 	benefits_resultSet.getString("autoGrowthInd"),
                 	benefits_resultSet.getString("autoGrowthPct"),
                 	benefits_resultSet.getString("clnPolCode"),
                 	benefits_resultSet.getString("clnPolId"),
                 	stg_benefit_id,
                 	benefits_resultSet.getString("smartBenId"),
                 	"",
                   	benefits_resultSet.getString("COPAY_IND"),
                   	benefits_resultSet.getString("AUTO_REPLENISH_LIMTYPE"),
                   	benefits_resultSet.getString("AUTO_REPLENISH_LIMIT"),
                   	benefits_resultSet.getString("AUTO_GROWTH_RATEIND"),
                   	benefits_resultSet.getString("AUTO_GROWTH_RATE"),
                   	benefits_resultSet.getString("AUTO_GROWTH_CEILING"),
                   	benefits_resultSet.getString("CUT_OFF_IND"),
                   	benefits_resultSet.getString("CUT_OFF_AGE"),
                   	benefits_resultSet.getString("INSURER_ID"),
                   	benefits_resultSet.getString("LAYERED_IND"),
                   	benefits_resultSet.getString("LAYER1_TYPE"),
                   	benefits_resultSet.getString("LAYER1_VALUE"),
                   	benefits_resultSet.getString("LAYER2_TYPE"),
                   	benefits_resultSet.getString("LAYER2_VALUE"),
                   	benefits_resultSet.getString("LAYER3_TYPE"),
                   	benefits_resultSet.getString("LAYER3_VALUE")
                       ), stg_benefit_id);

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } finally {
        	
            if (benefits_resultSet != null) try { benefits_resultSet.close(); } catch (SQLException ignore) {}
        	//if (service_tags_resultSet != null) try { service_tags_resultSet.close(); } catch (SQLException ignore) {}
            if (benefits_statement != null) try { benefits_statement.close(); } catch (SQLException ignore) {}
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
					SMART[10] =  customerTB_resultSet.getString("BENEFITS");
					SMART[27] =  customerTB_resultSet.getString("BENEFITS_MAP");
					SMART[46] =  customerTB_resultSet.getString("M_BENEFITS");
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
	



	public List<Benefit> getBenefits(String customerid, String country, int startindex, int maxresults, int status, String restrict,  String orderby) {
 
		String customertable = "";
		String customercountry = "";
		benefits.clear();
		RequestMapIntegstaging data = new RequestMapIntegstaging(customerid, country, startindex, maxresults, status, restrict,  orderby);
		BenefitsServiceDBAccess(data.getDBParams(), customerid, customercountry, startindex, maxresults, status, restrict, orderby);
		List<Benefit> allbenefits = new ArrayList<Benefit>(benefits.values());

		System.out.println(benefits.values());
		return allbenefits;	
	}

	
	@Override
	public List<Benefit> getBenefitsDefault(String customerid, String country, int startindex, int maxresults, int status, String restrict, String orderby) {
		// TODO Auto-generated method stub
		 
		String customertable = "";
		String customercountry = "";
		benefits.clear();
		RequestMapIntegstaging data = new RequestMapIntegstaging(customerid, country, startindex, maxresults, status, restrict,  orderby);
		BenefitsServiceDefaultDBAccess(data.getDBParams(), customerid, customercountry, startindex, maxresults, status, restrict, orderby);
		List<Benefit> allbenefits = new ArrayList<Benefit>(benefits.values());

		System.out.println(benefits.values());
		return allbenefits;	
	}

	/*
	public List<Benefit> getSearchBenefits(String q) {
		
		benefits.clear();
		q = q.toLowerCase();
		List<Benefit> matchingBenefits = new ArrayList<Benefit>();
		
		for(Benefit p : benefits.values())
		{
			if(p.getMemberNames().toLowerCase().contains(q)){
				matchingBenefits.add(p);
			}
		}
		
		return matchingBenefits;
	}
	*/
	

	public List<Benefit> getSearchBenefits(String q, String customerid, String country, int startindex, int maxresults, int status, String restrict,  String orderby) {

		benefits.clear();
		RequestMapIntegstaging data = new RequestMapIntegstaging(q, customerid, country, startindex, maxresults, status, restrict,  orderby);
		SearchBenefitsServiceDBAccess(data.getDBParams(), data.getQ(), data.getCustomerId(), data.getCountry(), data.getStartIndex(), data.getMaxResults(), data.getStatus(), data.getRestrict(), data.getOrderBy());
        List<Benefit> matchingBenefits = new ArrayList<Benefit>(benefits.values());
		return matchingBenefits;
	}

	

	public Benefit getBenefit(String id, String customerid, String country) throws IllegalArgumentException {
		
		benefits.clear();
		RequestMapIntegstaging data = new RequestMapIntegstaging(customerid, country);
		SingleBenefitsServiceDBAccess(data.getDBParams(), id, customerid, country);
		//CONNECT TO THE DB PULL DATA AND PUT IT INT THE VIEW
		Benefit p = benefits.get(id);
		if(p == null){
			throw new IllegalArgumentException("Could not find benefit with id:"+id);
		}
		return p;
	}

	
	public String addBenefit(Benefit benefit, String customerid, String country) throws IllegalArgumentException {

		benefits.clear();
		RequestMapIntegstaging data = new RequestMapIntegstaging(customerid, country);
		if(benefits.containsValue(benefit)){
			throw new IllegalArgumentException("Benefit "+benefit+" already exists.");
		}
		
		String id = "";
		id = addBenefitsServiceDBAccess(data.getDBParams(), id, benefit, customerid, country);

		benefits.put(id, benefit);
		
		return id;
	}
	
	
	public String addBenefitReturns(Benefit benefit, String customerid, String country) throws IllegalArgumentException {
		
		
		if(benefits.containsValue(benefit)){
			throw new IllegalArgumentException("Benefit "+benefit+" already exists IN THE DB.");
		}
		
		//benefits.clear();
		RequestMapIntegstaging data = new RequestMapIntegstaging(customerid, country);
		
		//int id = idGen.incrementAndGet();
		String id = "";
		benefits.put(id, benefit);
		
		return id;
	}
	

	public String addBenefit(Benefit benefit, String stg_benefit_id) throws IllegalArgumentException {	
		
		if(benefits.containsValue(benefit)){
			throw new IllegalArgumentException("Benefit "+benefit+" already exists.");
		}
		//benefits.clear();
		benefits.put(stg_benefit_id, benefit);
		return stg_benefit_id;
	}



	public void updateBenefit(String id, String customerid, String country) throws IllegalArgumentException {
		
		//benefits.clear();
		RequestMapIntegstaging data = new RequestMapIntegstaging(customerid, country);
		//SingleBenefitsServiceDBAccess(data.getDBParams(), id, customerid, country);
		//CONNECT TO THE DB, PULL DATA AND PUT IT INTO THE VIEW
		if(!benefits.containsKey(id)){
			throw new IllegalArgumentException("Unable to find benefit with id "+id);
		}
		System.out.println("The following benefit was updated"+id);
		
		//UPDATE THE VIEW THEN UPDATE THE DB
		//benefits.put(id);	
		
	}
	
	public void updateSwitchedBenefit(String id, String customerid, String country) throws IllegalArgumentException {
	    
		//benefits.clear();
		RequestMapIntegstaging data = new RequestMapIntegstaging(customerid, country);
		//SingleBenefitsServiceDBAccess(data.getDBParams(), id, customerid, country);
		//CONNECT TO THE DB, PULL DATA AND PUT IT INTO THE VIEW
		if(!benefits.containsKey(id)){
			throw new IllegalArgumentException("Unable to find benefit with id "+id);
		}
		
		//UPDATE THE DB
		//benefits.put(id, benefit);	
	}

	public void deleteBenefit(String id, String customerid, String country) throws IllegalArgumentException {
		
		//benefits.clear();
		RequestMapIntegstaging data = new RequestMapIntegstaging(customerid, country);
		//SingleBenefitsServiceDBAccess(data.getDBParams(), id, customerid, country);
		if(!benefits.containsKey(id)){
			throw new IllegalArgumentException("Unable to find benefit with id "+id);
		}
		
		//UPDATE THE DB
		//benefits.remove(id);
		
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
