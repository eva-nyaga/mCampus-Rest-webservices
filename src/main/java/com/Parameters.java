/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.scribe.model.Response;

/**
 *
 * @author Eva
 */
public class Parameters {
    
     public String fixWhere_sch(String clientid, String polid, String sname, String status, String stype) {
        String q="";
        
    if(!clientid.equals("0"))
            q+=" and b.client_id="+clientid;
        if(!polid.equals("0"))
            q+=" and b.pol_id="+polid;
        if(!sname.equals("null"))
            q+=" and upper(pol_name) like '%"+sname.toUpperCase()+"%'";
        if(!status.equals("0"))
        {
            if(status.equals("1"))
                q+=" and b.status=1";
            
            else if(status.equals("2"))
                q+=" and b.status=2";
            
            else if (status.equals("3"))
                q+=" and b.status=3";
            else
                q+=" and b.status=4";
        }
        if(!stype.equals("0"))
            q+=" and b.pol_type_id="+stype;
//        else 
//            q=" ";
        return q;
    }
     
       public String fixWhere_mem(String clientid, String polid, String memno,String memnames, String status)
    {
        String q = "";
        if(!clientid.equals("0"))
            q = (new StringBuilder()).append(q).append(" and cust_id=").append(clientid).toString();
        if(!polid.equals("0"))
            q = (new StringBuilder()).append(q).append(" and pol_id1=").append(polid).toString();
        if(!memno.equals("null")){
            q+= " and upper(member_number) like '"+memno.toUpperCase()+"%'";
            System.out.println("q "+q);
        }
        if(!memnames.equals("null"))
            q+= " and upper(names_as_is) like '%"+memnames.toUpperCase()+"%'";
        if(!status.equals("0")){
            if(status.equals("1"))
                q+= " and mem_status in (1,5)";
            else
                q = (new StringBuilder()).append(q).append(" and mem_status not in (1,5)").toString();
        }
        return q;
    }

           public String fixWhere_client(String preffix)
    {
        String q = "";
        if(preffix.equals("null"))
            q=" ";
        else if(!preffix.equals("null"))
             q=" where smart_code='"+preffix+"' ";
        
        return q;
    }

     public  JsonObject getFormattedResult(ResultSet rs) {
    List<JSONObject> resList = new ArrayList<JSONObject>();
     JsonObject jsonResponse = new JsonObject();
     JsonArray data = new JsonArray();
  //  JSONArray resArr = new JSONArray();
    try {
        // get column names
        ResultSetMetaData rsMeta = rs.getMetaData();
        int columnCnt = rsMeta.getColumnCount();
        List<String> columnNames = new ArrayList<String>();
        for(int i=1;i<=columnCnt;i++) {
            columnNames.add(rsMeta.getColumnName(i).toUpperCase());
        }

        while(rs.next()) { // convert each object to an human readable JSON object
            JSONObject obj = new JSONObject();
            for(int i=1;i<=columnCnt;i++) {
                String key = columnNames.get(i - 1);
                String value = rs.getString(i);
                obj.put(key, value);
            JsonArray row = new JsonArray();
            row.add(new JsonPrimitive(value));
            data.add(row);
            }
            resList.add(obj);
       	
        jsonResponse.add("payload", data);
     //   jsonResponse.remove("\\\\");
        System.out.println(jsonResponse);
         //   resArr=new JSONArray(obj);
           
            
        }
    } catch(Exception e) {
        e.printStackTrace();
    } finally {
        try {
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    return jsonResponse;
}
     
       public  JSONArray getFormattedResult2(ResultSet rs) {
    List<JSONObject> resList = new ArrayList<JSONObject>();
    JSONArray resArr = new JSONArray();
    try {
        // get column names
        ResultSetMetaData rsMeta = rs.getMetaData();
        int columnCnt = rsMeta.getColumnCount();
        List<String> columnNames = new ArrayList<String>();
        for(int i=1;i<=columnCnt;i++) {
            columnNames.add(rsMeta.getColumnName(i).toUpperCase());
        }

        while(rs.next()) { // convert each object to an human readable JSON object
            JSONObject obj = new JSONObject();
            JsonArray data = new JsonArray();
            String key="";
            for(int i=1;i<=columnCnt;i++) {
                 key = columnNames.get(i - 1);
                String value = rs.getString(i);
                obj.put(key, value);
                JsonArray row = new JsonArray();
            row.add(new JsonPrimitive(value));
            data.add(row);
            }
            resList.add(obj);
            String notes = obj.getString(key);
            resArr=new JSONArray(notes);
            
            JsonObject jsonResponse = new JsonObject();	
        jsonResponse.add("aaData", data);
           
            
        }
    } catch(Exception e) {
        e.printStackTrace();
    } finally {
        try {
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    return resArr;
}
       
//    
//        public  List<JSONObject> getFormattedResult(ResultSet rs) {
//    List<JSONObject> resList = new ArrayList<JSONObject>();
//    try {
//        // get column names
//        ArrayList<String> items = new ArrayList<>();
//        ResultSetMetaData rsMeta = rs.getMetaData();
//        int columnCnt = rsMeta.getColumnCount();
//        List<String> columnNames = new ArrayList<String>();
//        for(int i=1;i<=columnCnt;i++) {
//            columnNames.add(rsMeta.getColumnName(i).toUpperCase());
//        }
//
//        while(rs.next()) { // convert each object to an human readable JSON object
//            JSONObject obj = new JSONObject();
//            for(int i=1;i<=columnCnt;i++) {
//                String key = columnNames.get(i - 1);
//                String value = rs.getString(i);
//                items.add(key+":"+value+"");
//                obj.put("payload", items);
//                obj.put("success", true);
//            }
//            resList.add(obj);
//            String reslist = resList.toString().replaceAll("\\\\", "");
//            System.out.println(reslist);
//            
//          
//            
//        }
//    } catch(Exception e) {
//        e.printStackTrace();
//    } finally {
//        try {
//            rs.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//    return resList;
//}
   
        
//      public  JSONArray getFormattedResult2(ResultSet rs) {
//   
//          JSONArray jsonArray = new JSONArray();
//        
//          try{
//    while (rs.next()) {
//            int total_rows = rs.getMetaData().getColumnCount();
//            JSONObject obj = new JSONObject();
//            for (int i = 0; i < total_rows; i++) {
//                obj.put(rs.getMetaData().getColumnLabel(i + 1)
//                        .toLowerCase(), rs.getObject(i + 1));
//                jsonArray.put(obj);
//            }
//        }
//          }catch(Exception e) {
//        e.printStackTrace();
//    } finally {
//        try {
//            rs.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//        return jsonArray;
//}
    
}
