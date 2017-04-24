package org.springframework.security.oauth.api.service.healthcare.clients;


import com.connections.DbCon;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.springframework.security.oauth.api.data.healthcare.clients.DBConnection;
import org.springframework.security.oauth.api.model.healthcare.clients.Login;
import org.springframework.security.oauth.api.model.healthcare.clients.MyUser;
import org.springframework.security.oauth.ussd.model.UssdResponse;
import org.springframework.stereotype.Service;
import java.sql.Statement;
import java.util.prefs.Preferences;
import org.glassfish.jersey.client.oauth2.OAuth2ClientSupport;
import org.glassfish.jersey.client.oauth2.OAuth2CodeGrantFlow;
import org.glassfish.jersey.client.oauth2.TokenResult;

import org.springframework.security.oauth.api.model.healthcare.clients.Reply;


@Service("peopleService")
public class PeopleService implements IPeopleService {
	private Map<String, Login> logins = new HashMap<String, Login>();
	
        private Map<String, MyUser> myuser = new HashMap<String, MyUser>();
        private Map<String,Reply> reply = new HashMap<String,Reply>();
        public  static Preferences prefs = Preferences.userNodeForPackage(org.springframework.security.oauth.api.service.healthcare.clients.PeopleService.class);
        public static String PREF_NAME="";
        public String smartInsurercode = "", slogin="0", replyMessage="",now_user="", now_module;
        public static String Database="";
    String adminmudule = "";
    Boolean isloggedin = false;

	public PeopleService(){
		
	}

	public Map authLogin(String username, String password) throws IllegalArgumentException {
		return null;
	}
        
           public String addUser(MyUser user, String id) throws IllegalArgumentException {	
		
		myuser.put(id, user);
		return id;
	}
           public String addReply(Reply myreply, String id) throws IllegalArgumentException {	
		
		reply.put(id, myreply);
		return id;
	}
           
           @Override
    public void fetchDatabase(String database) {
       
        Database=database;
        String db=null;
        if(database.equals("TUC_DB"))
            db="TANGAZA_UNIVERSITY";
        else if(database.equals("TAITA"))
            db="TAITATAVETA UNIVERSITY COLLEGE";
        
         PREF_NAME = "db_name";

        // Set the value of the preference
        String pref_value = db;
        prefs.put(PREF_NAME, pref_value);

      
        
    }
       
	
             
         @Override
	public List<MyUser> fetchUserDetails(String stdId, String password) {
        String defaultValue = "default db";
        String dbname = prefs.get(PREF_NAME, defaultValue); // "a string"
        System.out.println("dbname "+dbname);
        
            List<MyUser> allschemes = new ArrayList<MyUser>();
          try {
            System.out.println("connecting ms..");
            com.connections.DbCon.fixCon_ret();
            
           
            System.out.println("ms connected..");
			
           
       
         
          slogin="0";
        String q="select Password from dbo.["+dbname+"$Customer] where No_='"+stdId+"'";
        System.out.println(q);
        Statement st=DbCon.getConMs_ret(true).createStatement();
        ResultSet rs=st.executeQuery(q);
        if (rs.next())
        {
            
            if(rs.getString("Password")==null)
               
                slogin="reg";
                //is_reg=true;
            else if(rs.getString("Password").equals(password))
            {
                slogin="1";
                //isauth=true;
                now_user=stdId;
            }
        }else if(rs.wasNull()){
            slogin="0";
        }
    
    } catch (Exception ex) {
        ex.printStackTrace();
     }
                        if(slogin.equals("reg"))
                        replyMessage = "display confirm password window ";
                        else
                        {
                            if(slogin.equals("1"))
                            {
                               
                           myuser.clear();     
                              fetchStudentDetails(stdId);
                              
		allschemes = new ArrayList<MyUser>(myuser.values());

		
                            }
                            else if(slogin.equals("0")){
                               replyMessage = "Wrong password";
                               myuser.clear();     
                              noDetailFound();
                              
		allschemes = new ArrayList<MyUser>(myuser.values());
                
                        }
                      
	}
                         return allschemes;	
        }

       


   public void fetchStudentDetails(String stdId){
       String defaultValue = "default db";
        String dbname = prefs.get(PREF_NAME, defaultValue); // "a string"
        System.out.println("dbname "+dbname);
        
    SecureRandom random = new SecureRandom();
    byte bytes[] = new byte[20];
    random.nextBytes(bytes);
    String token = bytes.toString();
      System.out.println("token "+token);
       ResultSet rs=null, rs1=null, rs2=null; 
      String current_sem="", now_stdId="", programme="";
      int changedpas=0;
      int rcount=0;
       try{
        String selectuser = "select * from [dbo].["+dbname+"$Course Registration] where [Student No_] ='" + stdId + "'";
        String retakecount=" select count(*) count from  [dbo].["+dbname+"$Exam Results] where [Student No_]='"+stdId+"' and [Re-Sited]=1";                   
        String pass="select [Changed Password] from dbo.["+dbname+"$Customer] where No_='"+stdId+"'";
        System.out.println(retakecount);
        System.out.println(pass);
        
        
        Statement st = DbCon.getConMs_ret(true).createStatement(); 
                    rs2=st.executeQuery(pass);
                    while(rs2.next()){
                    changedpas=rs2.getInt("Changed Password");
                    }
                    
                    rs1=st.executeQuery(retakecount);
                                  
                                 while(rs1.next()){
                                      rcount=rs1.getInt("count");
                                 }
                               rs1.close();
                           if(rcount>=0){
                              rs=st.executeQuery(selectuser);            
                           
                            while (rs.next()){
                                 now_stdId = rs.getString("Student No_");
                                programme=rs.getString("Programme");
                                current_sem=rs.getString("Stage");
                                
                                 
         addUser(new MyUser(
                                
                                now_stdId,
                                programme,
                                current_sem,
                                rcount,
                                changedpas
                 
                                
                                
         ),now_stdId);
                            }   
              
                            }
                            
                            
       }catch(Exception sql){
           sql.printStackTrace();
       }
       
      
   }
   
   public void noDetailFound(){
      
         addUser(new MyUser(
                 "Student Not Found")
                 , "1");
       
   }   
              
                            
      
   

//   public String generateToken(){
//       String code="", state="";
//      OAuth2CodeGrantFlow.Builder builder =
//      OAuth2ClientSupport.authorizationCodeGrantFlowBuilder(clientId,
//                            "https://example.com/oauth/authorization",
//                            "https://example.com/oauth/token");
//     OAuth2CodeGrantFlow flow = builder
//    .property(OAuth2CodeGrantFlow.Phase.AUTHORIZATION, "readOnly", "true")
//    .scope("contact")
//    .build();
//String authorizationUri = flow.start();
//
// 
//
//final TokenResult result = flow.finish(code, state);
//System.out.println("Access Token: " + result.toString());
//       
//return result.toString();
//   }

    

  
      


}
