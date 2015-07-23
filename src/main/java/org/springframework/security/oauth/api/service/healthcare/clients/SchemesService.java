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

import org.springframework.security.oauth.api.data.healthcare.clients.DBConSmartBO;
import org.springframework.security.oauth.api.data.healthcare.clients.DBConnection;
import org.springframework.security.oauth.api.data.healthcare.clients.RequestMapIntegstaging;
import org.springframework.security.oauth.api.data.healthcare.clients.RequestMapInteractive;
import org.springframework.security.oauth.api.model.healthcare.clients.Member;
import org.springframework.security.oauth.api.model.healthcare.clients.Scheme;
import org.springframework.security.oauth.api.model.healthcare.clients.Person;
import org.springframework.security.oauth.api.model.healthcare.clients.SchemeRenewal;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;




@Service("schemesService")
public class SchemesService implements ISchemesService {
	
	private Map<String, Scheme> schemes = new HashMap<String, Scheme>();
	private Map<String, SchemeRenewal> schemeRenewals = new HashMap<String, SchemeRenewal>();
	private Map<String, Scheme> schemeActivations = new HashMap<String, Scheme>();
	private Map<String, Scheme> schemeDeactivations = new HashMap<String, Scheme>();
	private Map<String, Scheme> schemeInsurerCodes = new HashMap<String, Scheme>();
	private AtomicInteger idGen = new AtomicInteger();
	
	String guarded_cols[] = new String[] {
			"id",
			"created_at",
			"last_update"
	};
	/*
	String allowed_stg_schemes_cols[] = new String[] {
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
			"providerSchemeId",
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
	

    public SchemesService(){
    }
    

    
	private String addSchemesServiceDBAccess(String[] DBParams, String id,
			Scheme scheme, String CUSTOMERID, String country) {
		
	       String[] SMART = getSMART_CODE(DBParams, CUSTOMERID);
	       Gson gson = new Gson();

	       
	       System.out.println("HERE "+SMART[35]);
	       HashMap MEMBERS_MAP =  gson.fromJson(SMART[35], new TypeToken<HashMap<String, String>>(){}.getType());
	       System.out.println("AFTER "+MEMBERS_MAP.get("POL_NAME"));
		// TODO Auto-generated method stub
			Connection dbMemberConnection = null;
			Connection dbSchemeConnection = null;
			
			//Connection dbConnection = null;
			PreparedStatement preparedStatement = null;
	 
		//	"":"COMPANY_NAME","":"STATUS","":"ALPHACOUNT","":"CLN_POL_CODE","":"SMART_CODE2","":"CLN_CODE","":"POL_ID"}
	
			String insertTableSQL = "INSERT INTO "+SMART[4]+"."+SMART[20]+""
					+ "(" +
					" "+MEMBERS_MAP.get("CLN_CODE")+"," +
					" "+MEMBERS_MAP.get("POL_NAME")+"," +
					" "+MEMBERS_MAP.get("CLN_POL_ID")+"," +
					" "+MEMBERS_MAP.get("STATUS")+"," +
					" "+MEMBERS_MAP.get("CLN_POL_CODE_CLIENT")+"," +
					" "+MEMBERS_MAP.get("CLN_POL_CODE")+"," +
					" "+MEMBERS_MAP.get("SMART_CODE")+"," +
					" "+MEMBERS_MAP.get("CLN_POL_TYPE")+"," +
					" "+MEMBERS_MAP.get("POLICY_NUMBER")+"," +
					" "+MEMBERS_MAP.get("START_DATE")+"," +
					" "+MEMBERS_MAP.get("END_DATE")+"," +
					" "+MEMBERS_MAP.get("COM_ID")+"," +
					" "+MEMBERS_MAP.get("POLICY_STATUS")+"," +
					" "+MEMBERS_MAP.get("CUT_OFF_IND")+"," +
					" "+MEMBERS_MAP.get("CUT_OFF_AGE")+"," +
					" "+MEMBERS_MAP.get("INVOICE_PT")+"," +
					" "+MEMBERS_MAP.get("INVOICE_CONTACT")+"," +
					" "+MEMBERS_MAP.get("INV_CONTACT_EMAIL")+"," +
					" "+MEMBERS_MAP.get("INV_PTCONTACT_TEL")+"," +
					" "+MEMBERS_MAP.get("DELIVERY_POINT")+"," +
					" "+MEMBERS_MAP.get("DEL_CONTACT")+"," +
					" "+MEMBERS_MAP.get("DEL_CONTACT_EMAIL")+"," +
					" "+MEMBERS_MAP.get("DEL_PTCONTACT_TEL")+"," +
					" "+MEMBERS_MAP.get("POLICY_CURRENCY_ID")+"," +
					" "+MEMBERS_MAP.get("CAPITATION_IND")+"," +
					" "+MEMBERS_MAP.get("CAPITATION_AMOUNT")+"," +
					" "+MEMBERS_MAP.get("CAP_FREQOF_USE")+"," +
					" "+MEMBERS_MAP.get("CAP_WITHIN_DURATION")+"," +
					" "+MEMBERS_MAP.get("CAP_CONSEQ_IND")+"," +
					" "+MEMBERS_MAP.get("POL_TYPE_ID")+"," +
					" "+MEMBERS_MAP.get("DATE_ADDED")+"," +
					" "+MEMBERS_MAP.get("USER_ID")+"," +
					" "+MEMBERS_MAP.get("CLAIM_GRACE_PERIOD")+"," +
					" "+MEMBERS_MAP.get("SPEND_THRESPCT")+"," +
					" "+MEMBERS_MAP.get("MODIFICATION_DATE")+"," +
					" "+MEMBERS_MAP.get("ACTIONED_DATE")+"," +
					" "+MEMBERS_MAP.get("REC_ID")+"," +
					" "+MEMBERS_MAP.get("AUTO_REPLENISH_IND")+", " +
					" "+MEMBERS_MAP.get("ANNIV")+", " +
					" "+MEMBERS_MAP.get("COUNTRY_CODE")+", " +
					" "+MEMBERS_MAP.get("POLICY_CONV_RATE")+", " +
					" "+MEMBERS_MAP.get("TRANS_STATUS")+", " +
					" "+MEMBERS_MAP.get("TRANS_STATUS_CODE")+", " +
					" "+MEMBERS_MAP.get("INSURER_ID")+" " +
					")" +
					" VALUES"
					+ "( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
			
			
			System.out.println(insertTableSQL);
			
	 
			try {

				dbMemberConnection = DBConnection.getConnection(DBParams);
				preparedStatement = dbMemberConnection.prepareStatement(insertTableSQL);
				preparedStatement.setString(1, scheme.getClnCode());
				preparedStatement.setString(2, scheme.getCompanyName());
				preparedStatement.setString(3, scheme.getClnPolId());
				preparedStatement.setString(4, "0");
				preparedStatement.setString(5, scheme.getClnPolCodeClient());
				preparedStatement.setString(6, scheme.getClnPolCode());
				preparedStatement.setString(7, scheme.getSmartCode());
				preparedStatement.setString(8, scheme.getClnPolType());
				preparedStatement.setString(9, scheme.getPolicyNumber());
				preparedStatement.setDate(10, scheme.getStartDate());
				preparedStatement.setDate(11, scheme.getEndDate());
				preparedStatement.setString(12, scheme.getComId());
				preparedStatement.setString(13, scheme.getPolicyStatus());
				preparedStatement.setString(14, scheme.getCutOffInd());
				preparedStatement.setString(15, scheme.getCutOffAge());
				preparedStatement.setString(16, scheme.getInvoicePt());
				preparedStatement.setString(17, scheme.getInvoiceContact());
				preparedStatement.setString(18, scheme.getInvContactEmail());
				preparedStatement.setString(19, scheme.getInvPtcontactTel());
				preparedStatement.setString(20, scheme.getDeliveryPoint());
				preparedStatement.setString(21, scheme.getDelContact());
				preparedStatement.setString(22, scheme.getDelContactEmail());
				preparedStatement.setString(23, scheme.getDelPtcontactTel());
				preparedStatement.setString(24, scheme.getPolicyCurrencyId());
				preparedStatement.setString(25, scheme.getCapitationInd());
				preparedStatement.setString(26, scheme.getCapitationAmount());
				preparedStatement.setString(27, scheme.getCapFreqofUse());
				preparedStatement.setString(28, scheme.getCapWithinDuration());
				preparedStatement.setString(29, scheme.getCapConseqInd());
				preparedStatement.setString(30, scheme.getPolTypeId());
				preparedStatement.setDate(31, scheme.getDateAdded());
				preparedStatement.setString(32, scheme.getUserId());
				preparedStatement.setString(33, scheme.getClaimGracePeriod());
				preparedStatement.setString(34, scheme.getSpendThrespct());
				preparedStatement.setDate(35, scheme.getModificationDate());
				preparedStatement.setDate(36, scheme.getActionedDate());
				preparedStatement.setString(37, id);
				preparedStatement.setString(38, scheme.getAutoReplenishInd());
			
				preparedStatement.setString(39, scheme.getAnniv());
				preparedStatement.setString(40, scheme.getCountryCode());
				preparedStatement.setString(41, scheme.getPolicyConvRate());
				preparedStatement.setString(42, scheme.getTransStatus());
				preparedStatement.setString(43, scheme.getTransStatusCode());
				preparedStatement.setString(44, scheme.getInsurerId());
				// execute insert SQL stetement
				preparedStatement .executeUpdate();

				//ResultSet keys = preparedStatement.getGeneratedKeys();  
				  
				//keys.next();  
				//key = keys.getInt(1);  
				//keys.close(); 
				
				
	 
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
			
			/*
			String insertTableSchemesSQL = "INSERT INTO AAR_OWNER.MAP_AAR_COMPANIES"
					+ "(" +
					" CLN_CODE," +
					" COMPANY_NAME," +
					" POL_ID," +
					" STATUS," +
					" SMART_CODE2," +
					" ALPHACOUNT," +
					" CLN_POL_CODE," +
					" CLN_POL_CODE_CLIENT" +
					")" +
					" VALUES"
					+ "( ?, ?, ?, ?, ?, ?, ?, ? )";

			System.out.println(insertTableSchemesSQL);

			try {

				dbSchemeConnection = DBConnection.getConnection(DBParams);
				preparedStatement = dbSchemeConnection.prepareStatement(insertTableSchemesSQL);
				preparedStatement.setString(1, scheme.getClnCode());
				preparedStatement.setString(2, scheme.getCompanyName());
				preparedStatement.setString(3, scheme.getClnPolId());
				preparedStatement.setString(4, scheme.getPolicyStatus());
				preparedStatement.setString(5, scheme.getSmartCode());
				preparedStatement.setString(6, "");
				preparedStatement.setString(7, scheme.getClnPolCode());
				preparedStatement.setString(8, scheme.getClnPolCodeClient());
				
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
	 
				if (dbSchemeConnection != null) {
					try {
						dbSchemeConnection.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
	 
			}
          */
		return id;	
	}
	
	
	private String RenewalsSchemesServiceDBAccess(String[] DBParams, String id,
			SchemeRenewal schemeRenewal, String CUSTOMERID, String country) {
		// TODO Auto-generated method stub
		
		System.out.println("HAVE BEEN LOOKING FOR YOU PRECIOUS!!!!!");
	       String[] SMART = getSMART_CODE(DBParams, CUSTOMERID);
	       Gson gson = new Gson();
	   
	       
	       System.out.println("HERE "+SMART[35]);
	       HashMap MEMBERS_MAP =  gson.fromJson(SMART[35], new TypeToken<HashMap<String, String>>(){}.getType());
	       System.out.println("AFTER "+MEMBERS_MAP.get("pol_Name"));
		// TODO Auto-generated method stub
			Connection dbMemberConnection = null;
			//Connection dbConnection = null;
			PreparedStatement preparedStatement = null;
	 
		//	"":"COMPANY_NAME","":"STATUS","":"ALPHACOUNT","":"CLN_POL_CODE","":"SMART_CODE2","":"CLN_CODE","":"POL_ID"}
	

			  
			//"+SMART[4]+"."+SMART[20]+"
			  
			String insertTableSQL = "INSERT INTO AAR_OWNER.STG_AAR_POLICY_ANNIV"
					+ "(" +
					" START_DATE," +
					" END_DATE," +
					" POLICY_CURRENCY_ID," +
					" POLICY_CONV_RATE," +
					" USER_ID," +
					" POLICY_STATUS," +
					" CORP_ID," +
					" REC_ID," +
					" ANNIV," +
					" PICK_STATUS," +
					" MODIFICATION_DATE," +
					" ACTIONED_DATE," +
					" TRANS_STATUS," +
					" TRANS_STATUS_CODE," +
					" INSURER_ID" +
					")" +
					" VALUES"
					+ "( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			System.out.println(insertTableSQL);
			
			String itemId = Long.toString(get());
		    String recid = itemId.substring(itemId.length() - 10);
			
		    
			try {


			    
				dbMemberConnection = DBConnection.getConnection(DBParams);
				preparedStatement = dbMemberConnection.prepareStatement(insertTableSQL);
				preparedStatement.setDate(1, schemeRenewal.getStartDate());
				preparedStatement.setDate(2, schemeRenewal.getEndDate());
				preparedStatement.setString(3, schemeRenewal.getPolicyCurrencyId());
				preparedStatement.setString(4, schemeRenewal.getPolicyConvRate());
				preparedStatement.setString(5, schemeRenewal.getUserId());
				preparedStatement.setString(6, schemeRenewal.getPolicyStatus());
				preparedStatement.setString(7, schemeRenewal.getClnPolCode());
				preparedStatement.setString(8, recid);
				preparedStatement.setString(9, schemeRenewal.getAnniv());
				preparedStatement.setString(10, "0");
				preparedStatement.setDate(11, getCurrentTimeStamp());
				preparedStatement.setDate(12, getCurrentTimeStamp());
				preparedStatement.setString(13, "Renewal");
				preparedStatement.setString(14, "3");
				preparedStatement.setString(15, schemeRenewal.getInsurerId());				

				// execute insert SQL stetement

				
			preparedStatement.executeUpdate();

				//ResultSet keys = preparedStatement.getGeneratedKeys();  
				  
				//keys.next();  
				//key = keys.getInt(1);  
				//keys.close(); 
		
	 
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

	
	private String ActivationsSchemesServiceDBAccess(String[] DBParams, String id, Scheme schemeActivation, String CUSTOMERID,
			String country) {
		// TODO Auto-generated method stub
		
		System.out.println("HAVE BEEN LOOKING FOR YOU PRECIOUS!!!!!");
	       String[] SMART = getSMART_CODE(DBParams, CUSTOMERID);
	       Gson gson = new Gson();

	       System.out.println("HERE "+SMART[35]);
	       HashMap MEMBERS_MAP =  gson.fromJson(SMART[35], new TypeToken<HashMap<String, String>>(){}.getType());
	       System.out.println("AFTER "+MEMBERS_MAP.get("pol_Name"));
		// TODO Auto-generated method stub
			Connection dbMemberConnection = null;
			//Connection dbConnection = null;
			PreparedStatement preparedStatement = null;
	 
		//	"":"COMPANY_NAME","":"STATUS","":"ALPHACOUNT","":"CLN_POL_CODE","":"SMART_CODE2","":"CLN_CODE","":"POL_ID"}
	
			String insertTableSQL = "INSERT INTO "+SMART[4]+"."+SMART[20]+""
					+ "(" +
					" "+MEMBERS_MAP.get("REC_ID")+"," +
					" "+MEMBERS_MAP.get("CORP_ID")+"," +
					" "+MEMBERS_MAP.get("POLICY_NUMBER")+"," +
					" "+MEMBERS_MAP.get("STATUS")+"," +
					" "+MEMBERS_MAP.get("ANNIV")+"," +
					" "+MEMBERS_MAP.get("PICKED_STATUS")+"," +
					" "+MEMBERS_MAP.get("PICKED_DATE")+"," +
					" "+MEMBERS_MAP.get("INSURER_ID")+" " +
					")" +
					" VALUES"
					+ "( ?, ?, ?, ?, ?, ?, ?, ?)";
			
			System.out.println(insertTableSQL);
			
			String itemId = Long.toString(get());
		    String recid = itemId.substring(itemId.length() - 10);

			try {

				dbMemberConnection = DBConnection.getConnection(DBParams);
				preparedStatement = dbMemberConnection.prepareStatement(insertTableSQL);
				preparedStatement.setString(1, schemeActivation.getCutOffInd());
				preparedStatement.setString(2, schemeActivation.getCutOffAge());
				preparedStatement.setString(3, schemeActivation.getInvoicePt());
				preparedStatement.setString(4, schemeActivation.getInvoiceContact());
				preparedStatement.setString(5, schemeActivation.getInvContactEmail());
				preparedStatement.setString(6, schemeActivation.getInvPtcontactTel());
				preparedStatement.setString(7, schemeActivation.getDeliveryPoint());
				preparedStatement.setString(8, schemeActivation.getDelContact());
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
			
			
			
			
			
			   System.out.println("HAVE BEEN LOOKING FOR YOU PRECIOUS!!!!!");
		       String[] SMART2 = getSMART_CODE(DBParams, CUSTOMERID);
		       Gson gson2 = new Gson();
		       
		       System.out.println("HERE "+SMART2[35]);
		       HashMap MEMBERS_MAP2 =  gson2.fromJson(SMART2[35], new TypeToken<HashMap<String, String>>(){}.getType());
		       System.out.println("AFTER "+MEMBERS_MAP2.get("pol_Name"));
			// TODO Auto-generated method stub
			   Connection dbMemberConnection2 = null;
				//Connection dbConnection = null;
			   PreparedStatement preparedStatement2 = null;
		 
			//	"":"COMPANY_NAME","":"STATUS","":"ALPHACOUNT","":"CLN_POL_CODE","":"SMART_CODE2","":"CLN_CODE","":"POL_ID"}
		
			   String insertTableSQL2 = "INSERT INTO "+SMART2[4]+"."+SMART2[20]+""
						+ "(" +
						" "+MEMBERS_MAP2.get("START_DATE")+"," +
						" "+MEMBERS_MAP2.get("END_DATE")+"," +
						" "+MEMBERS_MAP2.get("PICK_STATUS")+"," +
						" "+MEMBERS_MAP2.get("POLICY_CURRENCY_ID")+"," +
						" "+MEMBERS_MAP2.get("POLICY_CONV_RATE")+"," +
						" "+MEMBERS_MAP2.get("DATE_ADDED")+"," +
						" "+MEMBERS_MAP2.get("USER_ID")+"," +
						" "+MEMBERS_MAP2.get("POLICY_STATUS")+"," +
						" "+MEMBERS_MAP2.get("MODIFICATION_DATE")+"," +
						" "+MEMBERS_MAP2.get("ACTIONED_DATE")+"," +
						" "+MEMBERS_MAP2.get("CORP_ID")+"," +
						" "+MEMBERS_MAP2.get("TRANS_STATUS")+"," +
						" "+MEMBERS_MAP2.get("REC_ID")+"," +
						" "+MEMBERS_MAP2.get("TRANS_STATUS_CODE")+"," +
						" "+MEMBERS_MAP2.get("ANNIV")+"," +
						" "+MEMBERS_MAP2.get("INSURER_ID")+" " +
						")" +
						" VALUES"
						+ "( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				
				System.out.println(insertTableSQL2);
				
				String itemId2 = Long.toString(get());
			    String recid2 = itemId2.substring(itemId2.length() - 10);
		
				try {

					dbMemberConnection2 = DBConnection.getConnection(DBParams);
					preparedStatement2 = dbMemberConnection2.prepareStatement(insertTableSQL2);
					preparedStatement2.setString(1, schemeActivation.getCutOffInd());
					preparedStatement2.setString(2, schemeActivation.getCutOffAge());
					preparedStatement2.setString(3, schemeActivation.getInvoicePt());
					preparedStatement2.setString(4, schemeActivation.getInvoiceContact());
					preparedStatement2.setString(5, schemeActivation.getInvContactEmail());
					preparedStatement2.setString(6, schemeActivation.getInvPtcontactTel());
					preparedStatement2.setString(7, schemeActivation.getDeliveryPoint());
					preparedStatement2.setString(8, schemeActivation.getDelContact());
					preparedStatement2.setString(9, schemeActivation.getCutOffInd());
					preparedStatement2.setString(10, schemeActivation.getCutOffAge());
					preparedStatement2.setString(11, schemeActivation.getInvoicePt());
					preparedStatement2.setString(12, schemeActivation.getInvoiceContact());
					preparedStatement2.setString(13, schemeActivation.getInvContactEmail());
					preparedStatement2.setString(14, schemeActivation.getInvPtcontactTel());
					preparedStatement2.setString(15, schemeActivation.getDeliveryPoint());
					preparedStatement2.setString(16, schemeActivation.getDelContact());

					// execute insert SQL stetement

				    preparedStatement2.executeUpdate();

					System.out.println("Record is inserted into DBUSER table!");
	
				} catch (SQLException e) {
					System.out.println(e.getMessage());
					throw new IllegalArgumentException(e.getMessage());
		 
				} finally {
		 
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
	
	
	  private String DeactivationsSchemesServiceDBAccess(String[] DBParams, String polid, Scheme schemeDeactivation, String CUSTOMERID,
			String country) {
			// TODO Auto-generated method stub

			return null;	
	}
	  
	  
	public void SchemesServiceDBAccess(String[] DBParams, String CUSTOMERID, String customercountry, int startindex, int maxresults, int status, String restrict,  String orderby){
        if(maxresults>100){
        	maxresults = 100;
        }
        
		int lastindex  = startindex+maxresults;
		Connection connection = null;
        PreparedStatement schemes_statement = null;
        PreparedStatement service_tags_statement = null;
        ResultSet schemes_resultSet = null;
        ResultSet service_tags_resultSet = null;
        

        String[] SMART = getSMART_CODE(DBParams, CUSTOMERID);
        Gson gson = new Gson();
        HashMap SCHEMES_MAP =  gson.fromJson(SMART[35], new TypeToken<HashMap<String, String>>(){}.getType());

        try {
        	String sql_statment = "SELECT outer.* "+
					  " FROM (SELECT ROWNUM rn, inner.* "+
		        		" FROM (  SELECT e.* "+
		        		" FROM "+SMART[4]+"."+SMART[20]+" e "+
		        		" WHERE "+SCHEMES_MAP.get("STATUS")+" = "+status+" "+
		        		" ORDER BY "+orderby+") inner) outer "+
		        		" WHERE outer.rn >= "+startindex+" AND outer.rn < "+lastindex+"";
        	System.out.println(sql_statment);
            connection = DBConnection.getConnection(DBParams);
            try {
				schemes_statement = connection.prepareStatement(sql_statment);
				
				

				schemes_resultSet = schemes_statement.executeQuery();
				
				if (!schemes_resultSet.isBeforeFirst() ) {    
				    throw new IllegalArgumentException("All schemes have already been processed");
				   }
				/*
				System.out.println("SELECT outer.* "+
						  " FROM (SELECT ROWNUM rn, inner.* "+
			        		" FROM (  SELECT e.* "+
			        		" FROM "+SMARTCODE+"_OWNER.STG_"+SMARTCODE+"_SCHEMES e "+
			        		" WHERE status = "+status+" "+
			        		" ORDER BY "+orderby+") inner) outer "+
			        		" WHERE outer.rn >= "+startindex+" AND outer.rn < "+lastindex+"");
				*/
				while (schemes_resultSet.next()) {
			
				     String  clnCode = schemes_resultSet.getString("CLN_CODE");
                      addScheme(new Scheme(
  							schemes_resultSet.getString("CLN_CODE"), 
   							schemes_resultSet.getString("CLN_CODE"),
   							schemes_resultSet.getString("pol_Name"),
   							schemes_resultSet.getString("cln_pol_Id"), 
   							"",
   							schemes_resultSet.getString("cln_Pol_Code"),
   							schemes_resultSet.getString("cln_Pol_Code_Client"),
   							schemes_resultSet.getString("smart_Code"),
   							schemes_resultSet.getString("cln_Pol_Type"),
   							schemes_resultSet.getString("cln_Pol_Id"),
   							schemes_resultSet.getString("policy_Number"),
   							schemes_resultSet.getDate("start_Date"),
   							schemes_resultSet.getDate("end_Date"),
   							schemes_resultSet.getString("com_Id"),
   							schemes_resultSet.getString("status"),
   							schemes_resultSet.getString("cut_Off_Ind"),
   							schemes_resultSet.getString("cut_Off_Age"),
   							schemes_resultSet.getString("invoice_Pt"),
   							schemes_resultSet.getString("invoice_Contact"),
   							schemes_resultSet.getString("inv_Contact_Email"),
   							schemes_resultSet.getString("inv_Ptcontact_Tel"),
   							schemes_resultSet.getString("delivery_Point"),
   							schemes_resultSet.getString("del_Contact"),
   							schemes_resultSet.getString("del_Contact_Email"),
   							schemes_resultSet.getString("del_Ptcontact_Tel"),
   							schemes_resultSet.getString("policy_Currency_Id"),
   							schemes_resultSet.getString("capitation_Ind"),
   							schemes_resultSet.getString("capitation_Amount"),
   							schemes_resultSet.getString("cap_Freqof_Use"),
   							schemes_resultSet.getString("cap_Within_Duration"),
   							schemes_resultSet.getString("cap_Conseq_Ind"),
   							schemes_resultSet.getString("pol_Type_Id"),
   							schemes_resultSet.getDate("date_Added"),
   							schemes_resultSet.getString("user_Id"),
   							schemes_resultSet.getString("claim_Grace_Period"),
   							schemes_resultSet.getString("spend_Threspct"),
   							schemes_resultSet.getString("policy_Status"),
   							schemes_resultSet.getDate("modification_Date"),
   							schemes_resultSet.getDate("actioned_Date"),
   							schemes_resultSet.getString("auto_Replenish_Ind"),
   							
   							
   							schemes_resultSet.getString("anniv"),
   							schemes_resultSet.getString("country_Code"),
   							schemes_resultSet.getString("policy_Conv_Rate"),
   							schemes_resultSet.getString("trans_Status"),
   							schemes_resultSet.getString("trans_Status_Code"),
   							schemes_resultSet.getString("INSURER_ID")
  							), clnCode);

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } finally {
        	
            if (schemes_resultSet != null) try { schemes_resultSet.close(); } catch (SQLException ignore) {}
            if (schemes_statement != null) try { schemes_statement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }
        
	}
	
	
	public void SinglePolicySchemesServiceDBAccess(String[] DBParams, String scheme_id, String CUSTOMERID, String country){
        Connection connection = null;
        PreparedStatement schemes_statement = null;
        PreparedStatement service_tags_statement = null;
        ResultSet schemes_resultSet = null;
        ResultSet service_tags_resultSet = null;

        String[] SMART = getSMART_CODE(DBParams, CUSTOMERID);
        Gson gson = new Gson();
        HashMap SCHEMES_MAP =  gson.fromJson(SMART[35], new TypeToken<HashMap<String, String>>(){}.getType());
        
        final String SINGLE_SQL_LIST = "select * from ( Select * from "+SMART[4]+"."+SMART[20]+" where "+SCHEMES_MAP.get("REC_ID")+" = "+scheme_id+" order by pol_name asc ) where rownum <= 50";

        System.out.println(SINGLE_SQL_LIST);
        try {
            connection = DBConnection.getConnection(DBParams);
            try {
				schemes_statement = connection.prepareStatement(SINGLE_SQL_LIST);
				schemes_resultSet = schemes_statement.executeQuery();
				
				while (schemes_resultSet.next()) {
				     String  clnCode = schemes_resultSet.getString("rec_Id");
                     addScheme(new Scheme(
 							schemes_resultSet.getString("CLN_CODE"), 
   							schemes_resultSet.getString("CLN_CODE"),
   							schemes_resultSet.getString("pol_Name"),
   							schemes_resultSet.getString("cln_pol_Id"), 
   							"",
   							schemes_resultSet.getString("cln_Pol_Code"),
   							schemes_resultSet.getString("cln_Pol_Code_Client"),
   							schemes_resultSet.getString("smart_Code"),
   							schemes_resultSet.getString("cln_Pol_Type"),
   							schemes_resultSet.getString("cln_Pol_Id"),
   							schemes_resultSet.getString("policy_Number"),
   							schemes_resultSet.getDate("start_Date"),
   							schemes_resultSet.getDate("end_Date"),
   							schemes_resultSet.getString("com_Id"),
   							schemes_resultSet.getString("status"),
   							schemes_resultSet.getString("cut_Off_Ind"),
   							schemes_resultSet.getString("cut_Off_Age"),
   							schemes_resultSet.getString("invoice_Pt"),
   							schemes_resultSet.getString("invoice_Contact"),
   							schemes_resultSet.getString("inv_Contact_Email"),
   							schemes_resultSet.getString("inv_Ptcontact_Tel"),
   							schemes_resultSet.getString("delivery_Point"),
   							schemes_resultSet.getString("del_Contact"),
   							schemes_resultSet.getString("del_Contact_Email"),
   							schemes_resultSet.getString("del_Ptcontact_Tel"),
   							schemes_resultSet.getString("policy_Currency_Id"),
   							schemes_resultSet.getString("capitation_Ind"),
   							schemes_resultSet.getString("capitation_Amount"),
   							schemes_resultSet.getString("cap_Freqof_Use"),
   							schemes_resultSet.getString("cap_Within_Duration"),
   							schemes_resultSet.getString("cap_Conseq_Ind"),
   							schemes_resultSet.getString("pol_Type_Id"),
   							schemes_resultSet.getDate("date_Added"),
   							schemes_resultSet.getString("user_Id"),
   							schemes_resultSet.getString("claim_Grace_Period"),
   							schemes_resultSet.getString("spend_Threspct"),
   							schemes_resultSet.getString("policy_Status"),
   							schemes_resultSet.getDate("modification_Date"),
   							schemes_resultSet.getDate("actioned_Date"),
   							schemes_resultSet.getString("auto_Replenish_Ind"),
   							
   							schemes_resultSet.getString("anniv"),
   							schemes_resultSet.getString("country_Code"),
   							schemes_resultSet.getString("policy_Conv_Rate"),
   							schemes_resultSet.getString("trans_Status"),
   							schemes_resultSet.getString("trans_Status_Code"),
   							schemes_resultSet.getString("INSURER_ID")
 							), clnCode);

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } finally {
        	
            if (schemes_resultSet != null) try { schemes_resultSet.close(); } catch (SQLException ignore) {}
            if (schemes_statement != null) try { schemes_statement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }
        
	}
	
	

	public void SingleSchemesServiceDBAccess(String[] DBParams, String scheme_id, String CUSTOMERID, String country){
        Connection connection = null;
        PreparedStatement schemes_statement = null;
        PreparedStatement service_tags_statement = null;
        ResultSet schemes_resultSet = null;
        ResultSet service_tags_resultSet = null;
        
        String[] SMART = getSMART_CODE(DBParams, CUSTOMERID);
        Gson gson = new Gson();
        
        HashMap SCHEMES_MAP =  gson.fromJson(SMART[35], new TypeToken<HashMap<String, String>>(){}.getType());
        final String SINGLE_SQL_LIST = "select * from ( Select * from "+SMART[4]+"."+SMART[20]+" where "+SCHEMES_MAP.get("CLN_CODE")+" = '"+scheme_id+"' order by pol_name asc ) where rownum <= 50";
        System.out.println(SINGLE_SQL_LIST);
        try {
            connection = DBConnection.getConnection(DBParams);
            try {
				schemes_statement = connection.prepareStatement(SINGLE_SQL_LIST);
				schemes_resultSet = schemes_statement.executeQuery();
				
				while (schemes_resultSet.next()) {
				     String  clnCode = schemes_resultSet.getString("CLN_CODE");
                     addScheme(new Scheme(
 							schemes_resultSet.getString("CLN_CODE"), 
   							schemes_resultSet.getString("CLN_CODE"),
   							schemes_resultSet.getString("pol_Name"),
   							schemes_resultSet.getString("cln_pol_Id"), 
   							"",
   							schemes_resultSet.getString("cln_Pol_Code"),
   							schemes_resultSet.getString("cln_Pol_Code_Client"),
   							schemes_resultSet.getString("smart_Code"),
   							schemes_resultSet.getString("cln_Pol_Type"),
   							schemes_resultSet.getString("cln_Pol_Id"),
   							schemes_resultSet.getString("policy_Number"),
   							schemes_resultSet.getDate("start_Date"),
   							schemes_resultSet.getDate("end_Date"),
   							schemes_resultSet.getString("com_Id"),
   							schemes_resultSet.getString("status"),
   							schemes_resultSet.getString("cut_Off_Ind"),
   							schemes_resultSet.getString("cut_Off_Age"),
   							schemes_resultSet.getString("invoice_Pt"),
   							schemes_resultSet.getString("invoice_Contact"),
   							schemes_resultSet.getString("inv_Contact_Email"),
   							schemes_resultSet.getString("inv_Ptcontact_Tel"),
   							schemes_resultSet.getString("delivery_Point"),
   							schemes_resultSet.getString("del_Contact"),
   							schemes_resultSet.getString("del_Contact_Email"),
   							schemes_resultSet.getString("del_Ptcontact_Tel"),
   							schemes_resultSet.getString("policy_Currency_Id"),
   							schemes_resultSet.getString("capitation_Ind"),
   							schemes_resultSet.getString("capitation_Amount"),
   							schemes_resultSet.getString("cap_Freqof_Use"),
   							schemes_resultSet.getString("cap_Within_Duration"),
   							schemes_resultSet.getString("cap_Conseq_Ind"),
   							schemes_resultSet.getString("pol_Type_Id"),
   							schemes_resultSet.getDate("date_Added"),
   							schemes_resultSet.getString("user_Id"),
   							schemes_resultSet.getString("claim_Grace_Period"),
   							schemes_resultSet.getString("spend_Threspct"),
   							schemes_resultSet.getString("policy_Status"),
   							schemes_resultSet.getDate("modification_Date"),
   							schemes_resultSet.getDate("actioned_Date"),
   							schemes_resultSet.getString("auto_Replenish_Ind"),
   							
   							schemes_resultSet.getString("anniv"),
   							schemes_resultSet.getString("country_Code"),
   							schemes_resultSet.getString("policy_Conv_Rate"),
   							schemes_resultSet.getString("trans_Status"),
   							schemes_resultSet.getString("trans_Status_Code"),
   							schemes_resultSet.getString("INSURER_ID")
 							), clnCode);

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } finally {
        	
            if (schemes_resultSet != null) try { schemes_resultSet.close(); } catch (SQLException ignore) {}
            if (schemes_statement != null) try { schemes_statement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }
        
	}
	
	
	
	public void SearchSchemesServiceDBAccess(String[] DBParams, String q, String CUSTOMERID, String country, int startindex, int maxresults, int status, String restrict,  String orderby){
		int lastindex  = startindex+maxresults;
		Connection connection = null;
        PreparedStatement schemes_statement = null;
        PreparedStatement service_tags_statement = null;
        ResultSet schemes_resultSet = null;
        ResultSet service_tags_resultSet = null;
        
       String[] SMART = getSMART_CODE(DBParams, CUSTOMERID);
       Gson gson = new Gson();
       HashMap SCHEMES_MAP =  gson.fromJson(SMART[26], new TypeToken<HashMap<String, String>>(){}.getType());

        final String SEARCH_SQL_LIST = " SELECT outer.*  FROM ( "+
        		" SELECT ROWNUM rn, inner.*  FROM ( "+  
        		" SELECT e.*  FROM "+SMART[1]+"."+SMART[2]+" e "+  
        		" WHERE "+SCHEMES_MAP.get("STATUS")+" = "+status+" AND "+
        		" ( "+
        		" "+SCHEMES_MAP.get("PROVIDER_SCHEME_ID")+" LIKE '%"+q+"%' OR  "+ 
        		" "+SCHEMES_MAP.get("POLICY_NUMBER")+"  LIKE '%"+q+"%' OR "+  
        		" "+SCHEMES_MAP.get("PROVIDER_CODE")+" LIKE '%"+q+"%' OR "+  
        		" "+SCHEMES_MAP.get("MEMBER_NAME")+" LIKE '%"+q+"%'  OR "+  
        		" "+SCHEMES_MAP.get("MEMBERSHIP_NUMBER")+" LIKE '%"+q+"%'  OR "+  
        		" "+SCHEMES_MAP.get("TRANSACTION_DATE")+" LIKE '%"+q+"%'  OR  "+ 
        		" "+SCHEMES_MAP.get("AMOUNT")+" LIKE '%"+q+"%'  OR  "+ 
        		" "+SCHEMES_MAP.get("DATE_RECEIVED")+" LIKE '%"+q+"%'  OR  "+ 
        		" "+SCHEMES_MAP.get("SMART_INVOICE_NR")+" LIKE '%"+q+"%'  OR "+  
        		" "+SCHEMES_MAP.get("PROVIDER_SCHEME_ID")+" LIKE '%"+q+"%'  "+
        		" ) ORDER BY "+orderby+"  "+
        		" ) inner) outer  "+ 
        		" WHERE outer.rn >= "+startindex+" AND outer.rn < "+lastindex+" ";
    
        System.out.println(SEARCH_SQL_LIST);
        
        try {
        	
            connection = DBConnection.getConnection(DBParams);
            
            try {
				schemes_statement = connection.prepareStatement(SEARCH_SQL_LIST);
				
			    System.out.println("--------------------------------------HERE---------------------------------------");
			    System.out.println(SEARCH_SQL_LIST);
			    
				schemes_resultSet = schemes_statement.executeQuery();
				
				if (!schemes_resultSet.isBeforeFirst() ) {    
				    throw new IllegalArgumentException("Your search - "+q+" - did not match any scheme. "+"Suggestions:"+
				    		"Make sure all words are spelled correctly."+
				    		"Try different keywords."+
				    		"Try more general keywords.");
				   }
				

			    
			    
				while (schemes_resultSet.next()) {
				     String clnCode = schemes_resultSet.getString("CLN_CODE");
					 int schemeId = idGen.incrementAndGet();
				     int transactionId = idGen.incrementAndGet();
                     addScheme(new Scheme(
    							schemes_resultSet.getString("CLN_CODE"), 
       							schemes_resultSet.getString("CLN_CODE"),
       							schemes_resultSet.getString("pol_Name"),
       							schemes_resultSet.getString("cln_pol_Id"), 
       							"",
       							schemes_resultSet.getString("cln_Pol_Code"),
       							schemes_resultSet.getString("cln_Pol_Code_Client"),
       							schemes_resultSet.getString("smart_Code"),
       							schemes_resultSet.getString("cln_Pol_Type"),
       							schemes_resultSet.getString("cln_Pol_Id"),
       							schemes_resultSet.getString("policy_Number"),
       							schemes_resultSet.getDate("start_Date"),
       							schemes_resultSet.getDate("end_Date"),
       							schemes_resultSet.getString("com_Id"),
       							schemes_resultSet.getString("status"),
       							schemes_resultSet.getString("cut_Off_Ind"),
       							schemes_resultSet.getString("cut_Off_Age"),
       							schemes_resultSet.getString("invoice_Pt"),
       							schemes_resultSet.getString("invoice_Contact"),
       							schemes_resultSet.getString("inv_Contact_Email"),
       							schemes_resultSet.getString("inv_Ptcontact_Tel"),
       							schemes_resultSet.getString("delivery_Point"),
       							schemes_resultSet.getString("del_Contact"),
       							schemes_resultSet.getString("del_Contact_Email"),
       							schemes_resultSet.getString("del_Ptcontact_Tel"),
       							schemes_resultSet.getString("policy_Currency_Id"),
       							schemes_resultSet.getString("capitation_Ind"),
       							schemes_resultSet.getString("capitation_Amount"),
       							schemes_resultSet.getString("cap_Freqof_Use"),
       							schemes_resultSet.getString("cap_Within_Duration"),
       							schemes_resultSet.getString("cap_Conseq_Ind"),
       							schemes_resultSet.getString("pol_Type_Id"),
       							schemes_resultSet.getDate("date_Added"),
       							schemes_resultSet.getString("user_Id"),
       							schemes_resultSet.getString("claim_Grace_Period"),
       							schemes_resultSet.getString("spend_Threspct"),
       							schemes_resultSet.getString("policy_Status"),
       							schemes_resultSet.getDate("modification_Date"),
       							schemes_resultSet.getDate("actioned_Date"),
       							schemes_resultSet.getString("auto_Replenish_Ind"),
       							
       							schemes_resultSet.getString("anniv"),
       							schemes_resultSet.getString("country_Code"),
       							schemes_resultSet.getString("policy_Conv_Rate"),
       							schemes_resultSet.getString("trans_Status"),
       							schemes_resultSet.getString("trans_Status_Code"),
       							schemes_resultSet.getString("INSURER_ID")
 							), clnCode);

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } finally {
        	
            if (schemes_resultSet != null) try { schemes_resultSet.close(); } catch (SQLException ignore) {}
        	//if (service_tags_resultSet != null) try { service_tags_resultSet.close(); } catch (SQLException ignore) {}
            if (schemes_statement != null) try { schemes_statement.close(); } catch (SQLException ignore) {}
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
					SMART[11] =  customerTB_resultSet.getString("COMPANIES");
					SMART[20] =  customerTB_resultSet.getString("POLICY");
					SMART[35] =  customerTB_resultSet.getString("POLICY_MAP");
					SMART[42] =  customerTB_resultSet.getString("COMPANIES_MAP");

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
	
	
	public void SmartInsurerCodeServiceDBAccess(String[] DBParams, String insurer){
	       
		schemeInsurerCodes.clear();
	    Connection connection = null;
	    PreparedStatement get_member_statement = null;
	    PreparedStatement service_tags_statement = null;
	    ResultSet get_member_resultSet = null;
	    ResultSet service_tags_resultSet = null;
	    String SINGLE_SQL_LIST = null;

	    try {
	        connection = DBConSmartBO.getConnection();
	       
		    SINGLE_SQL_LIST = "SELECT DISTINCT SMART_CODE, POL_ID FROM SMART.FIN_POLICY_DETAILS";
		    System.out.println(SINGLE_SQL_LIST);
	        try {
					get_member_statement = connection.prepareStatement(SINGLE_SQL_LIST);
					get_member_resultSet = get_member_statement.executeQuery();
					while (get_member_resultSet.next()) {
						
						      String rec_Id = get_member_resultSet.getString("POL_ID");
						      addSmartInsurerCode(
		                    		 new Scheme(
		                    				    rec_Id,
		                    				    rec_Id,
		                    					get_member_resultSet.getString("SMART_CODE")
		                    					)
		                    		  , rec_Id);

					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        
	    } finally {
	    	
	        if (get_member_resultSet != null) try { get_member_resultSet.close(); } catch (SQLException ignore) {}
	        if (get_member_statement != null) try { get_member_statement.close(); } catch (SQLException ignore) {}
	        if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
	    }
	
	}




	public List<Scheme> getSchemes(String customerid, String country, int startindex, int maxresults, int status, String restrict,  String orderby) {
 
		String customertable = "";
		String customercountry = "";
		schemes.clear();
		RequestMapIntegstaging data = new RequestMapIntegstaging(customerid, country, startindex, maxresults, status, restrict,  orderby);
		SchemesServiceDBAccess(data.getDBParams(), customerid, customercountry, startindex, maxresults, status, restrict, orderby);
		List<Scheme> allschemes = new ArrayList<Scheme>(schemes.values());

		return allschemes;	
	}


	/*
	public List<Scheme> getSearchSchemes(String q) {
		
		schemes.clear();
		q = q.toLowerCase();
		List<Scheme> matchingSchemes = new ArrayList<Scheme>();
		
		for(Scheme p : schemes.values())
		{
			if(p.getMemberNames().toLowerCase().contains(q)){
				matchingSchemes.add(p);
			}
		}
		
		return matchingSchemes;
	}
	*/
	
	
	public List<Scheme> getSearchSchemes(String q, String customerid, String country, int startindex, int maxresults, int status, String restrict,  String orderby) {

		schemes.clear();
		RequestMapIntegstaging data = new RequestMapIntegstaging(q, customerid, country, startindex, maxresults, status, restrict,  orderby);
		SearchSchemesServiceDBAccess(data.getDBParams(), data.getQ(), data.getCustomerId(), data.getCountry(), data.getStartIndex(), data.getMaxResults(), data.getStatus(), data.getRestrict(), data.getOrderBy());
        List<Scheme> matchingSchemes = new ArrayList<Scheme>(schemes.values());
		return matchingSchemes;
	}

	

	public Scheme getScheme(String id, String customerid, String country) throws IllegalArgumentException {
		
		schemes.clear();
		RequestMapIntegstaging data = new RequestMapIntegstaging(customerid, country);
		SinglePolicySchemesServiceDBAccess(data.getDBParams(), id, customerid, country);
		//CONNECT TO THE DB PULL DATA AND PUT IT INT THE VIEW
		Scheme p = schemes.get(id);
		if(p == null){
			throw new IllegalArgumentException("Could not find scheme with id:"+id);
		}
		return p;
	}

	
	public String addScheme(Scheme scheme, String customerid, String country) throws IllegalArgumentException {

		schemes.clear();
		RequestMapIntegstaging data = new RequestMapIntegstaging(customerid, country);
		SingleSchemesServiceDBAccess(data.getDBParams(), scheme.getClnCode(), customerid, country);
		
		if(schemes.containsValue(scheme)){
			throw new IllegalArgumentException("Scheme "+scheme+" already exists.");
		}
    
		String id = "";
		String itemId = Long.toString(get());
	    String polid = itemId.substring(itemId.length() - 10);

		id = addSchemesServiceDBAccess(data.getDBParams(), polid, scheme, customerid, country);
		schemes.put(id, scheme);
		return id;
	}
	
	
	public String addSchemeReturns(Scheme scheme, String customerid, String country) throws IllegalArgumentException {
		
		
		if(schemes.containsValue(scheme)){
			throw new IllegalArgumentException("Scheme "+scheme+" already exists IN THE DB.");
		}
		
		//schemes.clear();
		RequestMapIntegstaging data = new RequestMapIntegstaging(customerid, country);
		String id = "";
		
		schemes.put(id, scheme);
		
		return id;
	}
	

	public String addScheme(Scheme scheme, String id) throws IllegalArgumentException {	
		/*
		if(schemes.containsValue(scheme)){
			throw new IllegalArgumentException("Scheme "+scheme+" already exists.");
		}
		*/
		//schemes.clear();
		schemes.put(id, scheme);
		return id;
	}
	
	
	public String addSmartInsurerCode(Scheme scheme, String id) throws IllegalArgumentException {	

		schemeInsurerCodes.put(id, scheme);
		return id;
	}
	
	
	public void updateScheme(String id, String customerid, String country) throws IllegalArgumentException {
		
		schemes.clear();
		RequestMapIntegstaging data = new RequestMapIntegstaging(customerid, country);
		SingleSchemesServiceDBAccess(data.getDBParams(), id, customerid, country);
		//CONNECT TO THE DB, PULL DATA AND PUT IT INTO THE VIEW
		if(!schemes.containsKey(id)){
			throw new IllegalArgumentException("Unable to find scheme with id "+id);
		}
		System.out.println("The following scheme was updated"+id);
		
		//UPDATE THE VIEW THEN UPDATE THE DB
		//schemes.put(id);	
		
	}
	
	
	public String updateRenewalsScheme(String polid, SchemeRenewal schemeRenewal, String customerid, String country) throws IllegalArgumentException {

		schemeRenewals.clear();
		RequestMapIntegstaging data = new RequestMapIntegstaging(customerid, country);
		//SingleSchemesServiceDBAccess(data.getDBParams(), polid, customerid, country);
		
		if(schemeRenewals.containsValue(schemeRenewal)){
			throw new IllegalArgumentException("Scheme Renewals "+schemeRenewal+" already exists.");
		}

		String id = "";
		id = RenewalsSchemesServiceDBAccess(data.getDBParams(), polid, schemeRenewal, customerid, country);

		schemeRenewals.put(id, schemeRenewal);
		
		return id;	
	}
	

	public void updateSwitchedScheme(String id, String customerid, String country) throws IllegalArgumentException {
	    
		//schemes.clear();
		RequestMapIntegstaging data = new RequestMapIntegstaging(customerid, country);
		SingleSchemesServiceDBAccess(data.getDBParams(), id, customerid, country);
		//CONNECT TO THE DB, PULL DATA AND PUT IT INTO THE VIEW
		if(!schemes.containsKey(id)){
			throw new IllegalArgumentException("Unable to find scheme with id "+id);
		}
		
		//UPDATE THE DB
		//schemes.put(id, scheme);	
	}

	public void deleteScheme(String id, String customerid, String country) throws IllegalArgumentException {
		
		schemes.clear();
		RequestMapIntegstaging data = new RequestMapIntegstaging(customerid, country);
		SingleSchemesServiceDBAccess(data.getDBParams(), id, customerid, country);
		if(!schemes.containsKey(id)){
			throw new IllegalArgumentException("Unable to find scheme with id "+id);
		}
		
		//UPDATE THE DB
		//schemes.remove(id);
		
	}
	

	@Override
	public String updateActivationsScheme(String polid, Scheme schemeActivation, String customerid, String country) throws IllegalArgumentException {

		schemeActivations.clear();
		RequestMapIntegstaging data = new RequestMapIntegstaging(customerid, country);
		//SingleSchemesServiceDBAccess(data.getDBParams(), polid, customerid, country);
		
		if(schemeActivations.containsValue(schemeActivation)){
			throw new IllegalArgumentException("Scheme activation "+schemeActivation+" already exists.");
		}

		String id = "";
		id = ActivationsSchemesServiceDBAccess(data.getDBParams(), polid, schemeActivation, customerid, country);

		schemeActivations.put(id, schemeActivation);
		
		return id;	
	}

	@Override
	public String updateDeactivationsScheme(String polid, Scheme schemeDeactivation, String customerid, String country) throws IllegalArgumentException {

		schemeDeactivations.clear();
		RequestMapIntegstaging data = new RequestMapIntegstaging(customerid, country);
		//SingleSchemesServiceDBAccess(data.getDBParams(), polid, customerid, country);
		
		if(schemeDeactivations.containsValue(schemeDeactivation)){
			throw new IllegalArgumentException("Scheme deactivations "+schemeDeactivation+" already exists.");
		}

		String id = "";
		id = DeactivationsSchemesServiceDBAccess(data.getDBParams(), polid, schemeDeactivation, customerid, country);

		schemeDeactivations.put(id, schemeDeactivation);
		
		return id;	
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



	public List<Scheme> getSmartInsurerCode(String insurer) {
		// TODO Auto-generated method stub
		schemeInsurerCodes.clear();
		RequestMapInteractive data = new RequestMapInteractive("ussd", "kenya");
		SmartInsurerCodeServiceDBAccess(data.getDBParams(), insurer);
		List<Scheme> allsmartinsurercodes = new ArrayList<Scheme>(schemeInsurerCodes.values());
		return allsmartinsurercodes;
	}



 }
