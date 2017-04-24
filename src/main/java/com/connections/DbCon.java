/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import org.springframework.security.oauth.api.service.healthcare.clients.PeopleService;

/**
 *
 * @author Eva
 */
public class DbCon {

    
    private static Connection conMs_ret;
    private static boolean lockedRet;
    private static String database;
    
   
    public static void fixCon_ret() throws Exception{
        database= PeopleService.Database;
        
        try{
            conMs_ret.close();
        }catch(Exception e){
            Class.forName ("net.sourceforge.jtds.jdbc.Driver");
        }
        //local
   // conMs_ret = DriverManager.getConnection("jdbc:jtds:sqlserver://127.0.0.1:1433/"+database, "sa", "eva@coretech");
   // System.out.println("Database "+ database);
    //server
     conMs_ret = DriverManager.getConnection("jdbc:jtds:sqlserver://127.0.0.1:1433/"+database, "mcampus", "mcampus123");
    System.out.println("Database "+ database);
     //public ip
    // conMs_ret = DriverManager.getConnection("jdbc:jtds:sqlserver://41.206.40.194/:1433/"+database, "mcampus", "mcampus123");
    // System.out.println("Database "+ database);
      
    }
    
       
    
    public static Connection getConMs_ret(boolean isrefresh) {
        try{
            if(isrefresh||conMs_ret==null||conMs_ret.isClosed())
                fixCon_ret();
        }catch(Exception x){System.err.println("fixCon_ret().. "+x.getMessage());}
        return conMs_ret;
    }

   

    public static boolean isLockedRet() {
        return lockedRet;
    }

    public static void setLockedRet(boolean lockedRet) {
        DbCon.lockedRet = lockedRet;
    }
	
	
    
}

