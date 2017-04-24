/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com;


import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//import net.sf.jasperreports.engine.JRExporterParameter;
//import net.sf.jasperreports.engine.JasperCompileManager;
//import net.sf.jasperreports.engine.JasperExportManager;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.JasperReport;
//import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
//import net.sf.jasperreports.engine.export.JRXlsExporter;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.codehaus.jackson.map.JsonMappingException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Smart_user
 */
public class Utils {
   
   public static String title;
   public static Font dvs,dvsb;
   public static Map<Object, Object> marksdata = new HashMap<Object, Object>();
   public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    
   public static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
}
   
 
//    public static Window fixWin1(Window win1, String title, Layout lyt) {
//            win1 = new Window(title);
//            win1.setContent(lyt);
//            win1.setWidth(lyt.getWidth(),Sizeable.Unit.PIXELS);
//            win1.setHeight(lyt.getHeight()+30,Sizeable.Unit.PIXELS);
//            win1.setModal(true);
//            win1.center();
//            mv.addWindow(win1);
//        }
    
    
    
   
    public static String getFamcode(String memno) {
        //check if single family
        String famcode="";
        if(memno.contains("-")) {
            if(memno.contains("/"))
                famcode=memno.substring(0,memno.indexOf("-"));
            else
                famcode=memno.substring(0,memno.lastIndexOf("-"));
        }
        else
            famcode=memno;
        return famcode;
    }
    
   
    
      public static String fixDateDay(String dt) {
        dt=dt.substring(dt.indexOf("-")+1);
        String mn=dt.substring(0,dt.indexOf("-"));
        dt=dt.substring(dt.indexOf(" ")+1);
        String day=dt.substring(0,dt.indexOf("-"));
        String yr=dt.substring(dt.lastIndexOf("-")+1);
        
        dt=day;
        return dt;
    }
      
       public static String fixDateMonth(String dt) {
        dt=dt.substring(dt.indexOf("-")+1);
        String mn=dt.substring(0,dt.indexOf("-"));
        dt=dt.substring(dt.indexOf(" ")+1);
        String day=dt.substring(0,dt.indexOf("-"));
        String yr=dt.substring(dt.lastIndexOf("-")+1);
        
        dt=mn;
        return dt;
    }
    
    public static String fixDate(String dt) {
        dt=dt.substring(dt.indexOf(" ")+1);
        String mn=dt.substring(0,dt.indexOf(" "));
        dt=dt.substring(dt.indexOf(" ")+1);
        String day=dt.substring(0,dt.indexOf(" "));
        String yr=dt.substring(dt.lastIndexOf(" ")+1);
        if(mn.equalsIgnoreCase("jan"))
            mn="01";
        else if(mn.equalsIgnoreCase("feb"))
            mn="02";
        else if(mn.equalsIgnoreCase("mar"))
            mn="03";
        else if(mn.equalsIgnoreCase("apr"))
            mn="04";
        else if(mn.equalsIgnoreCase("may"))
            mn="05";
        else if(mn.equalsIgnoreCase("jun"))
            mn="06";
        else if(mn.equalsIgnoreCase("jul"))
            mn="07";
        else if(mn.equalsIgnoreCase("aug"))
            mn="08";
        else if(mn.equalsIgnoreCase("sep"))
            mn="09";
        else if(mn.equalsIgnoreCase("oct"))
            mn="10";
        else if(mn.equalsIgnoreCase("nov"))
            mn="11";
        else if(mn.equalsIgnoreCase("dec"))
            mn="12";
        dt=day+"/"+mn+"/"+yr;
        return dt;
    }
    
    public static String fixDateMonth(int mon){
        String month="";
        if(mon==1){
            month="January";
        }
        if(mon==2){
            month="February";
        }
        if(mon==3){
            month="March";
        }
        if(mon==4){
            month="April";
        }
        if(mon==5){
            month="May";
        }
        if(mon==6){
            month="June";
        }
        if(mon==7){
            month="July";
        }
        if(mon==8){
            month="August";
        }
        if(mon==9){
            month="September";
        }
        if(mon==10){
            month="October";
        }
        if(mon==11){
            month="November";
        }
        if(mon==12){
            month="December";
        }
        return month;
    }
    
	public static String fixDate1(String dt) {
        dt=dt.substring(dt.indexOf(" ")+1);
        String mn=dt.substring(0,dt.indexOf(" "));
        dt=dt.substring(dt.indexOf(" ")+1);
        String day=dt.substring(0,dt.indexOf(" "));
        String yr=dt.substring(dt.lastIndexOf(" ")+1);
        if(mn.equalsIgnoreCase("jan"))
            mn="01";
        else if(mn.equalsIgnoreCase("feb"))
            mn="02";
        else if(mn.equalsIgnoreCase("mar"))
            mn="03";
        else if(mn.equalsIgnoreCase("apr"))
            mn="04";
        else if(mn.equalsIgnoreCase("may"))
            mn="05";
        else if(mn.equalsIgnoreCase("jun"))
            mn="06";
        else if(mn.equalsIgnoreCase("jul"))
            mn="07";
        else if(mn.equalsIgnoreCase("aug"))
            mn="08";
        else if(mn.equalsIgnoreCase("sep"))
            mn="09";
        else if(mn.equalsIgnoreCase("oct"))
            mn="10";
        else if(mn.equalsIgnoreCase("nov"))
            mn="11";
        else if(mn.equalsIgnoreCase("dec"))
            mn="12";
        dt=yr+"-"+mn+"-"+day;
        return dt;
    }
        
        public static String fixDate2(String dt) {
        dt=dt.substring(dt.indexOf(" ")+1);
        String mn=dt.substring(0,dt.indexOf(" "));
        dt=dt.substring(dt.indexOf(" ")+1);
        String day=dt.substring(0,dt.indexOf(" "));
        String yr=dt.substring(dt.lastIndexOf(" ")+1);
        if(mn.equalsIgnoreCase("jan"))
            mn="01";
        else if(mn.equalsIgnoreCase("feb"))
            mn="02";
        else if(mn.equalsIgnoreCase("mar"))
            mn="03";
        else if(mn.equalsIgnoreCase("apr"))
            mn="04";
        else if(mn.equalsIgnoreCase("may"))
            mn="05";
        else if(mn.equalsIgnoreCase("jun"))
            mn="06";
        else if(mn.equalsIgnoreCase("jul"))
            mn="07";
        else if(mn.equalsIgnoreCase("aug"))
            mn="08";
        else if(mn.equalsIgnoreCase("sep"))
            mn="09";
        else if(mn.equalsIgnoreCase("oct"))
            mn="10";
        else if(mn.equalsIgnoreCase("nov"))
            mn="11";
        else if(mn.equalsIgnoreCase("dec"))
            mn="12";
        dt=day+"-"+mn+"-"+yr;
        return dt;
    }
    
      public static String fixDate3(String dt) {
        dt=dt.substring(dt.indexOf(" ")+1);
        String mn=dt.substring(0,dt.indexOf(" "));
        dt=dt.substring(dt.indexOf(" ")+1);
        String day=dt.substring(0,dt.indexOf(" "));
        String yr=dt.substring(dt.lastIndexOf(" ")+1);
        if(mn.equalsIgnoreCase("jan"))
            mn="01";
        else if(mn.equalsIgnoreCase("feb"))
            mn="02";
        else if(mn.equalsIgnoreCase("mar"))
            mn="03";
        else if(mn.equalsIgnoreCase("apr"))
            mn="04";
        else if(mn.equalsIgnoreCase("may"))
            mn="05";
        else if(mn.equalsIgnoreCase("jun"))
            mn="06";
        else if(mn.equalsIgnoreCase("jul"))
            mn="07";
        else if(mn.equalsIgnoreCase("aug"))
            mn="08";
        else if(mn.equalsIgnoreCase("sep"))
            mn="09";
        else if(mn.equalsIgnoreCase("oct"))
            mn="10";
        else if(mn.equalsIgnoreCase("nov"))
            mn="11";
        else if(mn.equalsIgnoreCase("dec"))
            mn="12";
        dt=yr+"-"+day+"-"+mn;
        return dt;
    }
      
        public static String fixDate4(String dt) {
        dt=dt.substring(dt.indexOf("-")+1);
        String mn=dt.substring(0,dt.indexOf("-"));
        dt=dt.substring(dt.indexOf(" ")+1);
        String day=dt.substring(0,dt.indexOf("-"));
        String yr=dt.substring(dt.lastIndexOf("-")+1);
     
        dt=yr+"-"+day+"-"+mn;
        return dt;
    }
    
    public static Date fixDate(int dd, int mm, int yy) {
        Date dt;
        Calendar cl=Calendar.getInstance();
        cl.set(Calendar.YEAR, yy);
        cl.set(Calendar.MONTH, mm);
        cl.set(Calendar.DATE, dd);
        dt=cl.getTime();
        return dt;
    }
    
    public static String fix_amount(int amount){
       String amt = NumberFormat.getNumberInstance(Locale.US).format(amount);
        
       return amt;
    }
    
    public static String fix_bamount(double amount){
       String amt = NumberFormat.getNumberInstance(Locale.US).format(amount);
        
       return amt;
    }
    public static String period(Date d1, Date d2){


        long diff = d2.getTime() - d1.getTime();
        long diffSeconds = diff / 1000 % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000);
        int diffInDays = (int) diff / (1000 * 60 * 60 * 24);
        
      
        
        String days = String.valueOf(diffInDays);
        String hrs = String.valueOf(diffHours);
        String min = String.valueOf(diffMinutes);
        String sec = String.valueOf(diffSeconds);
        String disp= days+"d"+hrs+"h"+min+"m"+sec+"s";
        
        return disp;
    }
    
     public static String period1(Date d1, Date d2){


        long diff = d2.getTime() - d1.getTime();
        long diffSeconds = diff / 1000 % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000);
        int diffInDays = (int) diff / (1000 * 60 * 60 * 24);
        int diffInYears=(int) diff / (1000* 60 * 60  * 24 * 365);

         if (diffHours  ==24){
            diffInDays = 1;
        }else if (diffHours > 24){
            diffInDays = (int) diffHours/24;
        }
        
         if (diffInDays == 365){
             diffInYears = 1;
         }else if (diffInDays >365){
             diffInYears = (int) diffInDays/365;
         }
         
       
        
       
       
        String yrs = String.valueOf(diffInYears);
        String days = String.valueOf(diffInDays);
        String hrs = String.valueOf(diffHours);
        String min = String.valueOf(diffMinutes);
        String sec = String.valueOf(diffSeconds);
        String disp= yrs+"Years "+days+"Days";
        
        return disp;
    }
     
    public static Date fixDate_string(String date) {
        
         int dd=Integer.parseInt(date.substring(0,date.indexOf("/")));
        int mm=Integer.parseInt(date.substring(date.indexOf("/")+1,date.lastIndexOf("/")))-1;
        int yy=Integer.parseInt(date.substring(date.lastIndexOf("/")+1));
        Date dt;
        Calendar cl=Calendar.getInstance();
        cl.set(Calendar.YEAR, yy);
        cl.set(Calendar.MONTH, mm);
        cl.set(Calendar.DATE, dd);
        dt=cl.getTime();
        return dt;
    }
    
     public static Date fixDate_string1(String date) {
        
         int yy=Integer.parseInt(date.substring(0,date.indexOf("-")));
        int mm=Integer.parseInt(date.substring(date.indexOf("-")+1,date.lastIndexOf("-")))-1;
        int dd=Integer.parseInt(date.substring(date.lastIndexOf("-")+1));
        Date dt;
        Calendar cl=Calendar.getInstance();
        cl.set(Calendar.YEAR, yy);
        cl.set(Calendar.MONTH, mm);
        cl.set(Calendar.DATE, dd);
        dt=cl.getTime();
        return dt;
    }
 
 
  public static String splitString(String toSplit){
      String splitPart;
      
      String[] firstPart = toSplit.split("\\.");
        splitPart= firstPart[0]; 
        System.out.println("split "+splitPart);
      return splitPart;
  }
  
   public static String splitStringAtText(String toSplit, String text){
      String splitPart=null;
      
 
        String[] bits = toSplit.split(text);
        for (String bit : bits) {
            System.out.println("'" + bit + "'");
            splitPart=bit;
        }
//      String[] firstPart = toSplit.split("\\"+text);
//        splitPart= firstPart[0]; 
        System.out.println("split "+splitPart);
      return splitPart;
  }
    
    public static String lst2string(List lstcols, int limit) {
        StringBuilder cols=new StringBuilder();
        for(int i=0; i<lstcols.size()&&i<limit&&limit>-1; i++)
        {
            if(i==0)
                cols.append("'").append(lstcols.get(i)).append("'");
            else
                cols.append(",'").append(lstcols.get(i)).append("'");
        }
        return cols.toString();
    }
    
    public static String lst2string_bt(List lstcols, int limit) {
        StringBuilder cols=new StringBuilder();
        for(int i=0; i<lstcols.size()&&i<limit&&limit>-1; i++)
        {
            if(i==0)
                cols.append("'").append(lstcols.get(i)+"-00").append("'");
            else
                cols.append(",'").append(lstcols.get(i)+"-00").append("'");
        }
        return cols.toString();
    }
    
  
    
    public static String writeXl(String fname, Map<String,List> hash) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dt=sdf.format(Calendar.getInstance().getTime());
        fname=fname.toLowerCase().replace(" ", "_")+" dash"+dt;
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Sheet1");
        List lstrows=new ArrayList();
        Iterator<String> iter = hash.keySet().iterator();
        while(iter.hasNext()) {
            String key=iter.next();
            if(!key.startsWith("*"))
            {
                lstrows = hash.get(key);
                lstrows.add(0,key);//Add column headers
                hash.put(key, lstrows);
            }
        }
        
        iter = hash.keySet().iterator();
        lstrows=new ArrayList();
        List lstcols=new ArrayList();
        while(iter.hasNext()) {
            String key=iter.next();
            if(!key.startsWith("*"))
            {
                if(lstrows.isEmpty())
                    lstrows = hash.get(key);
                lstcols.add(key);
            }
        }
        int rownum = lstrows.size();
//        Connection con=db.getCon_integ();
//        Statement st=con.createStatement();
        //System.out.print("hash size.. "+hash.size());
        for(int i=0; i<rownum; i++) { //iterate rows
            Row row = sheet.createRow(i);
            int cellnum=0;
            int[] icols = new int[hash.size()];
            for(int j=0; j<icols.length; j++)
                icols[j]=j;
            
            for(int ix=0; ix<icols.length; ix++) //iterate cols
            {
                //System.out.print(i+","+ix);
                //Object objx : lstcols
                lstrows=hash.get(lstcols.get(icols[ix]).toString());
                Object obj=lstrows.get(i);
                Cell cell = row.createCell(cellnum);
                try{
                    cell.setCellValue(Double.parseDouble(obj.toString()));
                }catch(Exception e){
                    try{
                        cell.setCellValue(String.valueOf(obj));
                    }catch(Exception x){
                        try{
                            cell.setCellValue((java.util.Date) obj);
                        }catch(Exception ex){cell.setCellValue(String.valueOf(obj));}
                    }
                }
//                if (obj instanceof Date) {
//                    cell.setCellValue((Date) obj);
//                } else if (obj instanceof Boolean) {
//                    cell.setCellValue((Boolean) obj);
//                } else if (obj instanceof String) {
//                    cell.setCellValue((String) obj);
//                } else if (obj instanceof Double) {
//                    cell.setCellValue((Double) obj);
//                }
                cellnum++;
            }
        }
        //Calendar cl=Calendar.getInstance();
        //fname="C:\\xl\\"+fname+".xls";
        fname="/home/smart/Documents/gf_docs/"+fname+".xls";
        System.out.println(fname);
        FileOutputStream out = new FileOutputStream(new File(fname));
        workbook.write(out);
        out.close();
//        if(db.getCon_ora()==null)
//            db.newCon_ora();
//        Statement orast=db.getCon_ora().createStatement();
//        String q="update smart.log_emails set send_status=0,attachment_name='"+fname+"' where id="+emailid;
//        orast.executeUpdate(q);
        System.out.println("Excel written successfully..");
        return fname;
    }
    
    public static void fonts(){
       try {
           Font f = Font.createFont(Font.TRUETYPE_FONT, new File("dvs.ttf"));
           Font fb = Font.createFont(Font.TRUETYPE_FONT, new File("dvsb.ttf"));
           
           GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
           ge.registerFont(f);
           ge.registerFont(fb);
           
            dvs = new Font("Trebuchet", Font.PLAIN, 8);
            dvsb = new Font("Trebuchet", Font.BOLD, 8);
       } catch (FontFormatException ex) {
           Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
       } catch (IOException ex) {
           Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
       }
       
    }
    
    public static JSONArray convert( ResultSet rs ) throws SQLException, JSONException, JsonMappingException  {
  
    JSONArray json = new JSONArray();
    ResultSetMetaData rsmd = rs.getMetaData();

    while(rs.next()) {
      int numColumns = rsmd.getColumnCount();
      System.out.println("rows "+ numColumns);
      JSONObject obj = new JSONObject();

      for (int i=1; i<numColumns+1; i++) {
        String column_name = rsmd.getColumnName(i);

        if(rsmd.getColumnType(i)==java.sql.Types.ARRAY){
         obj.put(column_name, rs.getArray(column_name));
        }
        else if(rsmd.getColumnType(i)==java.sql.Types.BIGINT){
         obj.put(column_name, rs.getInt(column_name));
        }
        else if(rsmd.getColumnType(i)==java.sql.Types.BOOLEAN){
         obj.put(column_name, rs.getBoolean(column_name));
        }
        else if(rsmd.getColumnType(i)==java.sql.Types.BLOB){
         obj.put(column_name, rs.getBlob(column_name));
        }
        else if(rsmd.getColumnType(i)==java.sql.Types.DOUBLE){
         obj.put(column_name, rs.getDouble(column_name)); 
        }
        else if(rsmd.getColumnType(i)==java.sql.Types.FLOAT){
         obj.put(column_name, rs.getFloat(column_name));
        }
        else if(rsmd.getColumnType(i)==java.sql.Types.INTEGER){
         obj.put(column_name, rs.getInt(column_name));
        }
        else if(rsmd.getColumnType(i)==java.sql.Types.NVARCHAR){
         obj.put(column_name, rs.getNString(column_name));
        }
        else if(rsmd.getColumnType(i)==java.sql.Types.VARCHAR){
         obj.put(column_name, rs.getString(column_name));
        }
        else if(rsmd.getColumnType(i)==java.sql.Types.TINYINT){
         obj.put(column_name, rs.getInt(column_name));
        }
        else if(rsmd.getColumnType(i)==java.sql.Types.SMALLINT){
         obj.put(column_name, rs.getInt(column_name));
        }
        else if(rsmd.getColumnType(i)==java.sql.Types.DATE){
         obj.put(column_name, rs.getDate(column_name));
        }
        else if(rsmd.getColumnType(i)==java.sql.Types.TIMESTAMP){
        obj.put(column_name, rs.getTimestamp(column_name));   
        }
        else{
         obj.put(column_name, rs.getObject(column_name));
        }
      }

      json.put(obj);
    }

    return json;
  }
    
     public static JSONArray convertToJSON(ResultSet resultSet){
         JSONArray jsonArray = new JSONArray();
        JSONObject obj = new JSONObject();
         Map<Object, Object> marksdata = new HashMap<Object, Object>();
         try{
             List<List<String>> rowList = new LinkedList<List<String>>();
        ResultSetMetaData rsmeta=resultSet.getMetaData();
        int total_columns = resultSet.getMetaData().getColumnCount();
       
        do{
            for (int i = 1; i < total_columns; i++) {
               
              //  obj.put(rsmeta.getColumnLabel(i), resultSet.getObject(i));
               marksdata.put(rsmeta.getColumnLabel(i), resultSet.getObject(i));
            }
            //jsonArray.put(obj);
            jsonArray.put(marksdata);
            System.out.println("json obj "+obj);
            System.out.println("json array"+jsonArray);
            System.out.println("marksdata"+marksdata);
          //  }
        }
        while (resultSet.next()); 
       
            
            
            
        
                 }catch(SQLException sq){
                     sq.printStackTrace();
                 }
        return jsonArray;
    }
     
     public static List<HashMap<String,Object>> convertResultSetToList(ResultSet rs) throws SQLException {
    ResultSetMetaData md = rs.getMetaData();
    int columns = md.getColumnCount();
    List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
    rs.beforeFirst();
   int rows = rs.getRow();
   System.out.println("rows "+rows);
    while (rs.next()) {
        HashMap<String,Object> row = new HashMap<String, Object>(columns);
        for(int i=1; i<=columns; ++i) {
       row.put(md.getColumnName(i),rs.getObject(i));
        }
        list.add(row);
    }

    return list;
}
    
//    public static void jasperReport(String userFormFile){
//       
//        ActionResponse response = new ActionResponse() {
//
//            @Override
//            public void sendRedirect(String string) throws IOException {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//
//            @Override
//            public void sendRedirect(String string, String string1) throws IOException {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//
//            @Override
//            public void setWindowState(WindowState ws) throws WindowStateException {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//
//            @Override
//            public void setPortletMode(PortletMode pm) throws PortletModeException {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//
//            @Override
//            public void setRenderParameters(Map<String, String[]> maps) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//
//            @Override
//            public void setRenderParameter(String string, String string1) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//
//            @Override
//            public void setRenderParameter(String string, String[] strings) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//
//            @Override
//            public void setEvent(QName qname, Serializable srlzbl) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//
//            @Override
//            public void setEvent(String string, Serializable srlzbl) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//
//            @Override
//            public Map<String, String[]> getRenderParameterMap() {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//
//            @Override
//            public PortletMode getPortletMode() {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//
//            @Override
//            public WindowState getWindowState() {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//
//            @Override
//            public void removePublicRenderParameter(String string) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//
//            @Override
//            public void addProperty(String string, String string1) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//
//            @Override
//            public void setProperty(String string, String string1) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//
//            @Override
//            public String encodeURL(String string) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//
//            @Override
//            public String getNamespace() {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//
//            @Override
//            public void addProperty(Cookie cookie) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//
//            @Override
//            public void addProperty(String string, Element elmnt) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//
//            @Override
//            public Element createElement(String string) throws DOMException {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//        };
//    // Clean the ActionResponse information.
//        // PortletUtils.clearAllRenderParameters(response);
//         
//         try {
// 
//                JasperReport report;
//            Map paramMap = new HashMap();
//            report = JasperCompileManager.compileReport("C:\\reportTemplate\\reportExample.jrxml");
//
//           // String userFormFile = "C:\\reportTemplate\\Simple_Report.pdf";
//            
//            List list = new ArrayList();
//
//           JRBeanCollectionDataSource dataSourceWebContent =new JRBeanCollectionDataSource(list);
//            JasperPrint print = JasperFillManager.fillReport(report, paramMap,dataSourceWebContent);
//
//            ByteArrayOutputStream outputByteArray = new ByteArrayOutputStream();
//
//           OutputStream outputfile = new FileOutputStream(new File(userFormFile));
//
//           JRXlsExporter exporter = new JRXlsExporter();
//
//           exporter.setParameter(JRExporterParameter.JASPER_PRINT, print); // Optional
//           exporter.exportReport();
//
//           outputfile.write(outputByteArray.toByteArray());
//
//            JasperExportManager.exportReportToPdfStream(print, outputfile);
//
//            //response.setRenderParameter("action", "searchProductsBegin");
//           
//       
//           
//        } catch(Exception e){
//            System.out.println("Error ");            
//            e.printStackTrace();
//       }
//      }
//
//    
//    
//   
    private static final Logger LOG = Logger.getLogger(Utils.class.getName());
             
    
}
