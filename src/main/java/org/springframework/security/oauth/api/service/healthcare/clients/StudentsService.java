/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.springframework.security.oauth.api.service.healthcare.clients;

import com.Utils;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.security.oauth.api.model.healthcare.clients.CourseworkMarks;
import org.springframework.security.oauth.api.model.healthcare.clients.CourseworkTimetable;
import org.springframework.security.oauth.api.model.healthcare.clients.Reply;
import org.springframework.security.oauth.api.model.healthcare.clients.UnitsModel;
import org.springframework.security.oauth.api.model.healthcare.clients.UnitsProgramme;
import org.springframework.stereotype.Service;
import org.json.JSONObject;
import org.springframework.security.oauth.api.model.healthcare.clients.AdminProgress;
import org.springframework.security.oauth.api.model.healthcare.clients.CampusList;
import org.springframework.security.oauth.api.model.healthcare.clients.ChangeCourse;
import org.springframework.security.oauth.api.model.healthcare.clients.CompletedUnits;
import org.springframework.security.oauth.api.model.healthcare.clients.DefermentRecords;
import org.springframework.security.oauth.api.model.healthcare.clients.ExamCard;
import org.springframework.security.oauth.api.model.healthcare.clients.FeeStatement;
import org.springframework.security.oauth.api.model.healthcare.clients.FeeStructure;
import org.springframework.security.oauth.api.model.healthcare.clients.PendingUnits;
import org.springframework.security.oauth.api.model.healthcare.clients.RetakesModel;
import org.springframework.security.oauth.api.model.healthcare.clients.UndoneUnits;

/**
 *
 * @author Eva
 */
@Service("studentsService")
public class StudentsService implements IStudentsService{

    public String common_url = "http://127.0.0.1:8090/resource/";
    private Map<String, UnitsModel> fetchunits = new HashMap<>();
    private Map<String,Reply> reply = new HashMap<>();
    private Map<String, UnitsProgramme> fetchprogrammes = new HashMap<>();
    private Map<String, CourseworkTimetable> fetchctimetables = new HashMap<>();
    private Map<String, CourseworkMarks> fetchcmarks = new HashMap<>();
    private Map<String, CompletedUnits> fetchcunits = new HashMap<>();
    private Map<String, PendingUnits> fetchpunits = new HashMap<>();
    private Map<String, UndoneUnits> fetchuunits = new HashMap<>();
    private Map<String, ExamCard> fetchecard = new HashMap<>();
    private Map<String, FeeStructure> fetchfstructure = new HashMap<>();
    private Map<String, FeeStatement> fetchfstatement = new HashMap<>();
    private Map<String, ChangeCourse> fetchcourses = new HashMap<>();
    private Map<String, AdminProgress> fetchprogress = new HashMap<>();
    private Map<String, CampusList> fetchcampuses = new HashMap<>();
    private Map<String, DefermentRecords> fetchdeferments = new HashMap<>();
    private Map<String, RetakesModel> fetchretakes = new HashMap<>();
    Map<String, Object> marksdata = new HashMap<String, Object>();
	

    
    
     public String addUnits(UnitsModel units, String id) throws IllegalArgumentException {	
		
		fetchunits.put(id, units);
		return id;
	}
     
     public String addReply(Reply myreply, String id) throws IllegalArgumentException {	
		
		reply.put(id, myreply);
		return id;
	}
	
      public String addProgramme(UnitsProgramme prog, String id) throws IllegalArgumentException {	
		
		fetchprogrammes.put(id, prog);
		return id;
	}
      
        public String addCourseworkTimeable(CourseworkTimetable ctt, String id) throws IllegalArgumentException {	
		
		fetchctimetables.put(id, ctt);
		return id;
	}
        
         public String addCourseworkMarks(CourseworkMarks cmarks, String id) throws IllegalArgumentException {	
		
		fetchcmarks.put(id, cmarks);
		return id;
	}
         
          public String addCompletedUnits(CompletedUnits cunits, String id) throws IllegalArgumentException {	
		
		fetchcunits.put(id, cunits);
		return id;
	}
          
           public String addPendingUnits(PendingUnits punits, String id) throws IllegalArgumentException {	
		
		fetchpunits.put(id, punits);
		return id;
	}
           
            public String addUndoneUnits(UndoneUnits uunits, String id) throws IllegalArgumentException {	
		
		fetchuunits.put(id, uunits);
		return id;
	}
            
             public String addExamCard(ExamCard ecard, String id) throws IllegalArgumentException {	
		
		fetchecard.put(id, ecard);
		return id;
	}
             
            public String addFeeStructure(FeeStructure fstructure, String id) throws IllegalArgumentException {	
		
		fetchfstructure.put(id, fstructure);
		return id;
	}
            
            public String addFeeStatement(FeeStatement fstatement, String id) throws IllegalArgumentException {	
		
		fetchfstatement.put(id, fstatement);
		return id;
	}
            
             public String addCourses(ChangeCourse courses, String id) throws IllegalArgumentException {	
		
		fetchcourses.put(id, courses);
		return id;
	}
	
               public String addProgress(AdminProgress progress, String id) throws IllegalArgumentException {	
		
		fetchprogress.put(id, progress);
		return id;
	}
               
               public String addCampus(CampusList campuses, String id) throws IllegalArgumentException {	
		
		fetchcampuses.put(id, campuses);
		return id;
	}
               
                public String addDeferments(DefermentRecords deferment, String id) throws IllegalArgumentException {	
		
		fetchdeferments.put(id, deferment);
		return id;
	}
                
                 public String addRetakes(RetakesModel retakes, String id) throws IllegalArgumentException {	
		
		fetchretakes.put(id, retakes);
		return id;
	}
	
	
	public static String getDbname(){
            String defaultValue = "default db";
            String dbname = PeopleService.prefs.get(PeopleService.PREF_NAME, defaultValue); 
        System.out.println("dbname "+dbname);
        return dbname;
        }
        
        public String fetchDatabase(String database) {
       
        String db=null;
        if(database.equals("TUC_DB"))
            db="TANGAZA_UNIVERSITY";
        else if(database.equals("TAITA"))
            db="TAITATAVETA UNIVERSITY COLLEGE";
        
        
        return db;

    }
     
    
    @Override
    public List<UnitsModel> getUnits(String semester) {
System.out.println("connecting ms..");
        try {
            com.connections.DbCon.fixCon_ret();
            System.out.println("ms conn: done");
            System.out.println(com.connections.DbCon.getConMs_ret(true));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
            
		fetchunits.clear();
                
                    fetchUnits(semester);
		List<UnitsModel> allschemes = new ArrayList<UnitsModel>(fetchunits.values());

		return allschemes;    
    
    }
    
     public void fetchUnits(String semester) {
         
         Statement st=null;
         ResultSet rs=null;
           String id="";
         String q = " Select row_number() over(partition by [Stage Code] order by Code asc) as RowId,Code, [Stage Code], Desription, [Credit Hours] from (\n" +
                    "select distinct  Code, [Stage Code], Desription, [Credit Hours]\n" +
                    "   from [dbo].["+getDbname()+"$Units_Subjects]\n" +
                    "  where [Stage Code] = '"+semester+"' and Desription is not null) x";
         System.out.println(q);
         String ele = "SELECT distinct [Reg_ Transacton ID], [Stage] ,[Unit] ,[Description] , [Credit Hours] FROM [dbo].["+getDbname()+"$Student Units] where\n" +
                    "[Stage] = 'ELE'";
         
        try {
            st = com.connections.DbCon.getConMs_ret(false).createStatement();
            rs = st.executeQuery(q);
            
            while (rs.next()) {
                
                 id =rs.getString("RowId");
                   addUnits(new UnitsModel(
                           rs.getString("Desription"), 
                           rs.getString("Code"),
                           rs.getString("Stage Code"),
                           rs.getDouble("Credit Hours")
                   ), id);
               
                      
            }
         
            
        } catch (Exception x) {
            x.printStackTrace();
        } finally {
            try {
                st.close();
            } catch (Exception ex) {
            }
            try {
                rs.close();
            } catch (Exception ex) {
            }
        }
       
    }
     
    
    @Override
    public List<Reply> saveUnitsRegistration(String stdId, String programme, String stage, String unit) {
        String rep="", code="";
        int insertstatus=0;
        Random rand = new Random();

       int  n = rand.nextInt(10000) + 1;
       String reg="REG-"+n;
        Statement st=null;
        try {
            
       
             String insert = "INSERT INTO [dbo].["+getDbname()+"$Student Units]\n" +
"           ([Programme],[Stage],[Unit],[Semester],[Reg_ Transacton ID],[Student No_],[ENo]\n" +
"           ,[Register for],[Unit Type],[Taken],[Exempted],[Attendance],[Allow Supplementary]\n" +
"           ,[Sat Supplementary],[Repeat Unit],[Remarks],[Unit Stage],[Failed],[Course Type]\n" +
"           ,[Audit],[Status],[No_ Of Units],[Project Status],[Final Score],[Created by]\n" +
"           ,[Edited By],[Date created],[Date Edited],[Total Marks] ,[System Created]\n" +
"           ,[Multiple],[Entry No_] ,[Student Class],[System Taken],[Repeat Marks]\n" +
"           ,[Re-Take],[Proposal Status],[Proposal Date],[Senate-Proposal],[Research]\n" +
"           ,[Senate-Research],[Examiners],[Defense],[Category Code],[Progress Report]\n" +
"           ,[Progress Date],[Defence OutCome],[Description],[Student Type],[Marks Status]\n" +
"           ,[Result Status],[Exam Period],[Exam Status],[Grade],[Supp Taken],[Actual Fees]\n" +
"           ,[Units Reg_ Status],[Reversed],[Unit Name],[CF Score],[Ignore in Cumm  Average]\n" +
"           ,[Attachment Unit],[Academic Year],[Student Code],[Examiner1] ,[Examiner2]\n" +
"           ,[Examiner3],[Examiner4],[Show],[Unit Points],[Credit Hours],[Supervisor]\n" +
"           ,[Released Results],[Grade Acquired])\n" +
"     VALUES\n" +
"           ('"+programme+"','"+stage+"','"+unit+"','Test'\n" +
"           ,'"+reg+"','"+stdId+"',1 ,1\n" +
"           ,0,1,0,0.0 ,0,0,0,'Test',' '\n" +
"           ,0,0,0,1,1,1,0,'Application','Application',current_timestamp ,current_timestamp\n" +
"           ,0,0,0,0,' ',0,0,0 ,0,current_timestamp,current_timestamp,current_timestamp\n" +
"           ,current_timestamp,current_timestamp,current_timestamp,' ',0\n" +
"           ,current_timestamp,0,' ',0,0,' ',' ' ,0,' ',0,0,0 ,0,' ',0 ,0\n" +
"           ,0,' ',' ',' ',' ',' ',' ',0,0,0,' ',0 ,' ')";
             
                
                System.out.println(insert);
                com.connections.DbCon.fixCon_ret();
             st=com.connections.DbCon.getConMs_ret(false).createStatement();
                insertstatus = st.executeUpdate(insert);
                if(insertstatus == 1){
                	
                    code="1";
                   rep= "Registration Submitted Successfully";
                    }else{
                    code="2";
                    	rep= "Unable to submit request, please try again later.";
                    }
                
	
        } catch (Exception x) {
            x.printStackTrace();
        }
       reply.clear();
        addReply(new Reply(code,rep), String.valueOf(insertstatus));
    List<Reply> allschemes = new ArrayList<Reply>(reply.values());
    
        return allschemes;
        
    }

    @Override
    public List<UnitsProgramme> getUnitsProgrammes(String stagecode, String desc) {
System.out.println("connecting ms..");
        try {
            com.connections.DbCon.fixCon_ret();
            System.out.println("ms conn: done");
            System.out.println(com.connections.DbCon.getConMs_ret(true));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
            
		
        
		fetchprogrammes.clear();
                
	 
                   fetchProgrammes(stagecode,desc);
		List<UnitsProgramme> allschemes = new ArrayList<UnitsProgramme>(fetchprogrammes.values());

		return allschemes;        
    }
    
     public void fetchProgrammes(String stagecode, String desc) {
         Statement st=null;
         ResultSet rs=null;
           String id="";
   
         String q = "select a.[Entry No], a.[Programme Code] ,b.Description from [dbo].["+getDbname()+"$Units_Subjects] a\n" +
                    " join [dbo].["+getDbname()+"$Programme] b on a.[Programme Code]= b.Code \n" +
                    " where [Stage Code] = '"+stagecode+"' and Desription = '"+desc+"'";
         System.out.println(q);
           
       
     
        try {
            st = com.connections.DbCon.getConMs_ret(false).createStatement();
            rs = st.executeQuery(q);
            
            while (rs.next()) {
                
                 id =rs.getString("Entry No");
                   addProgramme(new UnitsProgramme(
                           rs.getString("Entry No"),
                           rs.getString("Programme Code"),
                           rs.getString("Description")
                           
                   ), id);
               
                      
            }
         
            
        } catch (Exception x) {
            x.printStackTrace();
        } finally {
            try {
               st.close();
            } catch (Exception ex) {
            }
            try {
                rs.close();
            } catch (Exception ex) {
            }
        }
        
    }

    @Override
    public List<CourseworkTimetable> getCourseworkTimetable(String day, String stage, String programme) {
System.out.println("connecting ms..");
        try {
            com.connections.DbCon.fixCon_ret();
            System.out.println("ms conn: done");
            System.out.println(com.connections.DbCon.getConMs_ret(true));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
            
		fetchctimetables.clear();
                
                   fetchCtimetables(day,stage,programme);
		List<CourseworkTimetable> allschemes = new ArrayList<CourseworkTimetable>(fetchctimetables.values());

		return allschemes;        
    
    }
    
     public void fetchCtimetables(String day, String stage, String programme) {
         Statement st=null;
         ResultSet rs=null;
           String id="";
           
         String q = "select distinct a.Unit, a.[Lecture Room], b.Description from [dbo].["+getDbname()+"$Time Table] a join\n" +
                    "  [dbo].["+getDbname()+"$Student Units] b on a.Unit = b.Unit\n" +
                    "  where  a.[Day of Week] = '"+day.toUpperCase()+"' \n" +
                    "  AND a.Stage = '"+stage+"' and a.Programme='"+programme+"' and a.[Room Type]=0";
         System.out.println(q);
           
       
     
        try {
            st = com.connections.DbCon.getConMs_ret(false).createStatement();
            rs = st.executeQuery(q);
            
            while (rs.next()) {
                
                 id =rs.getString("Unit");
                   addCourseworkTimeable(new CourseworkTimetable(
                           rs.getString("Lecture Room"),
                           rs.getString("Description"),
                           rs.getString("Unit")
                           
                   ), id);
               
                      
            }
         
            
        } catch (Exception x) {
            x.printStackTrace();
        } finally {
            try {
                st.close();
            } catch (Exception ex) {
            }
            try {
                rs.close();
            } catch (Exception ex) {
            }
        }
        
     
                
       
    }

    @Override
    public List<CourseworkMarks> getCourseworkMarks(String stdId, String resit) {
System.out.println("connecting ms..");
        try {
            com.connections.DbCon.fixCon_ret();
            System.out.println("ms conn: done");
            System.out.println(com.connections.DbCon.getConMs_ret(true));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
            
		fetchcmarks.clear();
                
                   fetchCmarks(stdId,resit);
		List<CourseworkMarks> allschemes = new ArrayList<CourseworkMarks>(fetchcmarks.values());

		return allschemes;      
    }
    
    @Override
    public JSONArray fetchCmarks(String stdId, String resit) {
          JSONArray json=new JSONArray();
          Connection con = com.connections.DbCon.getConMs_ret(false);
          ResultSet rs=null;
          CallableStatement callableStatement=null;
           
         String q = "EXECUTE dbo.getCourseworkMarks '"+stdId+"',"+resit+";";
         System.out.println(q);
           
        try {
            
 callableStatement = con.prepareCall("{call dbo.ReadEmployees(?, ?)}",ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_READ_ONLY);
           
            callableStatement.setString(1,stdId);
            callableStatement.setInt(2,Integer.valueOf(resit));
            
            rs = callableStatement.executeQuery();
            System.out.println("exec "+rs.next());
         
		
               json=Utils.convertToJSON(rs);
       
            
        } catch (Exception x) {
            x.printStackTrace();
        } finally {
            try {
                callableStatement.close();
            } catch (Exception ex) {
            }
            try {
                rs.close();
            } catch (Exception ex) {
            }
        }
        
     return json;
                
       
    }

    @Override
    public List<CompletedUnits> getCompletedUnits(String stdId, String resit) {
System.out.println("connecting ms..");
        try {
            com.connections.DbCon.fixCon_ret();
            System.out.println("ms conn: done");
            System.out.println(com.connections.DbCon.getConMs_ret(true));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
            
		
        
		fetchcunits.clear();
                
	 
                    fetchCUnits(stdId,resit);
		List<CompletedUnits> allschemes = new ArrayList<CompletedUnits>(fetchcunits.values());

		return allschemes;     
    }
    
     public void fetchCUnits(String stdId, String resit) {
         Statement st=null;
         ResultSet rscunits=null ,rstmarks=null, rsumarks=null ;
           String ttunits="", ttmarks="", year="", course="", unitname="", grade="", gpv="", id="", nid="", avgmarks="", avggrade="";
           int marks=0;
           
       String cunits = "select distinct count(*) RecordsPerGroup,\n" +
                        "    COUNT(*) OVER () AS TotalRecords from [dbo].["+getDbname()+"$Exam Results]"
               + " WHERE [Student No_]='"+stdId+"' and [Re-Sited]="+resit+" group by Unit";

       String tmarks = "select newId() as id, FORMAT(CAST(sum([Score])AS DECIMAL(9,6)), 'g15') total_marks from [dbo].["+getDbname()+"$Exam Results]"
                        + " WHERE [Student No_]='"+stdId+"' and  [Re-Sited]="+resit+"";
       
       String umarks ="select  newId() as id, w.[Student No_],  y.[Academic Year], y.Course,y.Unit,y.UnitName,w.marks, y.GPV,  w.[Re-Sited] from (\n" +
                        "SELECT  Unit,\n" +
                        "        Programme, [Student No_] ,[Re-Sited],\n" +
                        "        FORMAT(CAST(SUM(Score)  AS DECIMAL(9,6)), 'g15') marks\n" +
                        "FROM    [dbo].["+getDbname()+"$Exam Results]\n" +
                        "GROUP BY    Unit,\n" +
                        "            Programme,[Student No_], [Re-Sited]) w join\n" +
                        "			(select distinct  a.Unit,  FORMAT(CAST(a.[GPA Points] AS DECIMAL(9,6)), 'g15') GPV,\n" +
                        "  b.Description Course, c.Desription UnitName, d.[Academic Year] from [dbo].["+getDbname()+"$Exam Results] a join [dbo].["+getDbname()+"$Programme] b\n" +
                        "  on a.Programme=b.Code join [dbo].["+getDbname()+"$Units_Subjects] c on a.Unit=c.Code\n" +
                        "  join [dbo].["+getDbname()+"$Course Registration] d\n" +
                        "  on a.[Student No_]=d.[Student No_] where a.[Student No_] = '00002S') y\n" +
                        "  \n" +
                        "  on w.Unit=y.Unit\n" +
                        "  where w.[Student No_]='"+stdId+"' and w.[Re-Sited]="+resit+"";
       
     System.out.println(umarks);
        try {
            st = com.connections.DbCon.getConMs_ret(false).createStatement();
            rscunits = st.executeQuery(cunits);
            
            while (rscunits.next()){
                ttunits = rscunits.getString("TotalRecords");
               
            }
            System.out.println("ttunits "+ttunits);
            
            rstmarks=st.executeQuery(tmarks);
            
            while(rstmarks.next()){
                ttmarks=rstmarks.getString("total_marks");
                nid=rstmarks.getString("id");
            }
            System.out.println("ttmarks "+ttmarks);
            
         
            
             avgmarks=String.valueOf(Integer.valueOf(ttmarks)/Integer.valueOf(ttunits));
              if(Integer.valueOf(avgmarks)>=70)
                    avggrade="A";
                else if(Integer.valueOf(avgmarks)<70 && Integer.valueOf(avgmarks)>=60)
                    avggrade="B";
                else if(Integer.valueOf(avgmarks)<60 && Integer.valueOf(avgmarks)>=50)
                    avggrade="C";
                else if(Integer.valueOf(avgmarks)<50 && Integer.valueOf(avgmarks)>=40)
                    avggrade="D";
                else if(Integer.valueOf(avgmarks)<40)
                    avggrade="E";
            
           
           
             rsumarks=st.executeQuery(umarks);
            while(rsumarks.next()){
                year=rsumarks.getString("Academic Year");
                course=rsumarks.getString("Course");
                unitname=rsumarks.getString("UnitName");
                marks=Integer.valueOf(rsumarks.getString("marks"));
                if(marks>=70)
                    grade="A";
                else if(marks<70 && marks>=60)
                    grade="B";
                else if(marks<60 && marks>=50)
                    grade="C";
                else if(marks<50 && marks>=40)
                    grade="D";
                else if(marks<40)
                    grade="E";
                
                gpv=rsumarks.getString("GPV");
                id=rsumarks.getString("id");
                
                addCompletedUnits(new CompletedUnits(
                        year,
                        course,
                        unitname,
                        String.valueOf(marks),
                        grade,
                        gpv,
                        ttunits,
                        ttmarks,
                        avgmarks,
                        avggrade
                ), id);

            }
           
            
        } catch (Exception x) {
            x.printStackTrace();
        } finally {
            try {
                st.close();
            } catch (Exception ex) {
            }
            try {
               rscunits.close();
               rstmarks.close();
               rsumarks.close();
            } catch (Exception ex) {
            }
        }
        
    }

    @Override
    public List<PendingUnits> getRetakeUnits(String stdId, String programme) {
System.out.println("connecting ms..");
        try {
            com.connections.DbCon.fixCon_ret();
            System.out.println("ms conn: done");
            System.out.println(com.connections.DbCon.getConMs_ret(true));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
            
		
        
		fetchpunits.clear();
                
	 
                    fetchRUnits(stdId,programme);
		List<PendingUnits> allschemes = new ArrayList<PendingUnits>(fetchpunits.values());

		return allschemes;     
    }
    
   public void fetchRUnits(String stdId, String programme) {
         Statement st=null;
         ResultSet rsretake=null;
           String year="", id="";
           
          
         String retake = "select distinct newId() id, a.Unit, a.Remarks, b.Desription UnitName,c.[Academic Year],\n" +
                        " FORMAT(CAST(b.[Credit Hours] AS DECIMAL(9,6)), 'g15') as credits from [dbo].["+getDbname()+"$Student Units] a \n" +
                        "join [dbo].["+getDbname()+"$Units_Subjects] b on a.Unit=b.Code\n" +
                        "join [dbo].["+getDbname()+"$Course Registration] c on a.[Student No_]=c.[Student No_]\n" +
                        "   where a.[Student No_] = '"+stdId+"' and a.Failed=1";
         
         
         
         System.out.println(retake);
           
       
     
        try {
            st = com.connections.DbCon.getConMs_ret(false).createStatement();
            rsretake = st.executeQuery(retake);
            
            while (rsretake.next()) {
                
              
                 id =rsretake.getString("id");
                 year=rsretake.getString("Academic Year");
                   addPendingUnits(new PendingUnits(
                           rsretake.getString("Academic Year"),
                           rsretake.getString("Unit"),
                           rsretake.getString("UnitName"),
                           rsretake.getString("credits"),
                           rsretake.getString("Remarks")
                           
                   ), id);
               
                      
            }
           
        } catch (Exception x) {
            x.printStackTrace();
        } finally {
            try {
                st.close();
            } catch (Exception ex) {
            }
            try {
                rsretake.close();
            } catch (Exception ex) {
            }
        }
        
    }
    @Override
    public List<UndoneUnits> getUndoneUnits(String stdId, String programme) {
System.out.println("connecting ms..");
        try {
            com.connections.DbCon.fixCon_ret();
            System.out.println("ms conn: done");
            System.out.println(com.connections.DbCon.getConMs_ret(true));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
            
		
        
		fetchuunits.clear();
                
	 
                    fetchUUnits(stdId,programme);
		List<UndoneUnits> allschemes = new ArrayList<UndoneUnits>(fetchuunits.values());

		return allschemes;     
    }
     public void fetchUUnits(String stdId, String programme) {
         Statement st=null;
         ResultSet rsundone=null;
           String year="", id="";
           
          
       
         
         String undone="SELECT DISTINCT newId() id, a.Code, a.[Programme Code], a.Desription, FORMAT(CAST(a.[Credit Hours] AS DECIMAL(9,6)), 'g15') as credits\n" +
                        "FROM  [dbo].["+getDbname()+"$Units_Subjects] a\n" +
                        "WHERE NOT EXISTS (SELECT *\n" +
                        " FROM [dbo].["+getDbname()+"$Student Units] b\n" +
                        " WHERE a.Code = b.Unit)\n" +
                        " and a.[Programme Code] = '"+programme+"'";
         
         System.out.println(undone);
           
       
     
        try {
            st = com.connections.DbCon.getConMs_ret(false).createStatement();
           
            
             rsundone = st.executeQuery(undone);
            
            while (rsundone.next()) {
               
                
                 id =rsundone.getString("id");
                   addUndoneUnits(new UndoneUnits(
                           year,
                           rsundone.getString("Code"),
                           rsundone.getString("Desription"),
                           rsundone.getString("credits")
                           
                   ), id);
               
                      
            }
        
            
        } catch (Exception x) {
            x.printStackTrace();
        } finally {
            try {
            st.close();
            } catch (Exception ex) {
            }
            try {
                rsundone.close();
            } catch (Exception ex) {
            }
        }
        
    }

    @Override
    public List<CourseworkTimetable> getExamTimetable(String day, String stage, String programme) {
System.out.println("connecting ms..");
        try {
            com.connections.DbCon.fixCon_ret();
            System.out.println("ms conn: done");
            System.out.println(com.connections.DbCon.getConMs_ret(true));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
            
		fetchctimetables.clear();
                
                   fetchEtimetables(day,stage,programme);
		List<CourseworkTimetable> allschemes = new ArrayList<CourseworkTimetable>(fetchctimetables.values());

		return allschemes;       
    
    }
    
    public void fetchEtimetables(String day, String stage, String programme) {
         Statement st=null;
         ResultSet rs=null;
           String id="";
           
         String q = "select distinct a.Unit, a.[Lecture Room], b.Description from [dbo].["+getDbname()+"$Time Table] a join\n" +
                    "  [dbo].["+getDbname()+"$Student Units] b on a.Unit = b.Unit\n" +
                    "  where  a.[Day of Week] = '"+day.toUpperCase()+"' \n" +
                    "  AND a.Stage = '"+stage+"' and a.Programme='"+programme+"' and a.[Room Type]=1";
         System.out.println(q);
           
       
     
        try {
            st = com.connections.DbCon.getConMs_ret(false).createStatement();
            rs = st.executeQuery(q);
            
            while (rs.next()) {
                
                 id =rs.getString("Unit");
                   addCourseworkTimeable(new CourseworkTimetable(
                           rs.getString("Lecture Room"),
                           rs.getString("Description"),
                           rs.getString("Unit")
                           
                   ), id);
               
                      
            }
         
            
        } catch (Exception x) {
            x.printStackTrace();
        } finally {
            try {
               st.close();
            } catch (Exception ex) {
            }
            try {
                rs.close();
            } catch (Exception ex) {
            }
        }
        
     
    }

    @Override
    public List<ExamCard> getExamCard(String stdId, String stage, String programme) {
System.out.println("connecting ms..");
        try {
            com.connections.DbCon.fixCon_ret();
            System.out.println("ms conn: done");
            System.out.println(com.connections.DbCon.getConMs_ret(true));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
            
		fetchecard.clear();
                
	 
                    fetchExamCards(stdId, stage,programme);
		List<ExamCard> allschemes = new ArrayList<ExamCard>(fetchecard.values());

		return allschemes;       
    
    }
    
     public void fetchExamCards(String stdId, String stage, String programme) {
         Statement st=null;
         ResultSet rsbal=null, rsunits=null;
           String id="", amount="", paid="", atte;
           Double bal=0.0;
           int att=0;
           
           String balance="  select  FORMAT(CAST( sum([Amount (LCY)]) AS DECIMAL(15,6)), 'g15') "
                            + "amount, FORMAT(CAST( sum([Credit Amount (LCY)]) AS DECIMAL(15,6)), 'g15')\n" +
                            "   paid from  [dbo].["+getDbname()+"$Detailed Cust_ Ledg_ Entry]\n" +
                            "  where [Customer No_]='"+stdId+"'";
           
           String units = " select Distinct newId() id, a.Unit, a.Programme, a.Stage, FORMAT(CAST(a.Attendance AS DECIMAL(9,6)), 'g15') as attendance, b.Desription\n" +
                            "   from [dbo].["+getDbname()+"$Student Units] a\n" +
                            "   join [dbo].["+getDbname()+"$Units_Subjects] b on a.Unit=b.Code\n" +
                            "  where a.[Student No_]='"+stdId+"' and a.Stage='"+stage+"' and a.Programme='"+programme+"'"
                             + " and b.[Programme Code]='"+programme+"'";
         
         System.out.println(balance);
         System.out.println(units);
           
       
     
        try {
            st = com.connections.DbCon.getConMs_ret(false).createStatement();
            rsbal = st.executeQuery(balance);
            
            while (rsbal.next()) {
                amount=rsbal.getString("amount");
                paid=rsbal.getString("paid");
                
            }
            bal=Double.valueOf(amount)-Double.valueOf(paid);
         System.out.println("bal "+ bal);
       //  if(bal<=10000){
             rsunits = st.executeQuery(units);
             
            while (rsunits.next()){
                att=Integer.valueOf(rsunits.getString("attendance"));
                System.out.println("att "+att);
                    if(att<33){
                    System.out.println("att "+att);
                    id=rsunits.getString("id");
                    addExamCard(new ExamCard(
                            rsunits.getString("Unit"),
                            rsunits.getString("Desription")
                    ), id);
                }
            } 
             
       //  }

            
        } catch (Exception x) {
            x.printStackTrace();
        } finally {
            try {
                st.close();
            } catch (Exception ex) {
            }
            try {
                rsbal.close();
                rsunits.close();
            } catch (Exception ex) {
            }
        }
        
     
                
       
    }

    @Override
    public List<FeeStructure> getFeeStructure(String programme, String stage) {
System.out.println("connecting ms..");
        try {
            com.connections.DbCon.fixCon_ret();
            System.out.println("ms conn: done");
            System.out.println(com.connections.DbCon.getConMs_ret(true));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
            
		
        
		fetchfstructure.clear();
                
	 
                    fetchStructure(programme, stage);
		List<FeeStructure> allschemes = new ArrayList<FeeStructure>(fetchfstructure.values());

		return allschemes;       
    
    }
    
     public void fetchStructure(String programme, String stage) {
         Statement st=null;
         ResultSet rs=null;
           String year="", id="";
           
          
       
         String q="  select newId() id, [Programme Code], [Stage Code], [Settlemet Type], \n" +
                    "  FORMAT(CAST([Break Down] AS DECIMAL(15,6)), 'g15') Amount from  \n" +
                    "  [dbo].["+getDbname()+"$Fee By Stage] where [Programme Code]='"+programme+"'"
                 + "and [Stage Code]='"+stage+"'";
         
         
         System.out.println(q);
           
       
     
        try {
            st = com.connections.DbCon.getConMs_ret(false).createStatement();
           
            
             rs = st.executeQuery(q);
            
            while (rs.next()) {
               
                
                 id =rs.getString("id");
                   addFeeStructure(new FeeStructure(
                          rs.getString("Programme Code"),
                          rs.getString("Stage Code"),
                          rs.getString("Settlemet Type"),
                          rs.getString("Amount")
                           
                   ), id);
               
                      
            }
        
            
        } catch (Exception x) {
            x.printStackTrace();
        } finally {
            try {
                st.close();
            } catch (Exception ex) {
            }
            try {
                rs.close();
            } catch (Exception ex) {
            }
        }
        
     
                
       
    }

    @Override
    public List<FeeStatement> getFeeStatement(String stdId) {
System.out.println("connecting ms..");
        try {
            com.connections.DbCon.fixCon_ret();
            System.out.println("ms conn: done");
            System.out.println(com.connections.DbCon.getConMs_ret(true));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
            
		
        
		fetchfstatement.clear();
                
	 
                    fetchStatement(stdId);
		List<FeeStatement> allschemes = new ArrayList<FeeStatement>(fetchfstatement.values());

		return allschemes;      
    
    }
    
    public void fetchStatement(String stdId) {
         Statement st=null;
         ResultSet rs=null, rs1=null;
           String year="", id="", balance="";
           
          
       
         String q="select newId() id, cast([Posting Date] as date) [Posting Date] , [Document No_] doctype, [Document Type] docno, \n" +
                    "  FORMAT(CAST([Debit Amount (LCY)] AS DECIMAL(15,6)), 'g15') [Debit Amount], FORMAT(CAST([Credit Amount (LCY)]AS DECIMAL(15,6)), 'g15') [Credit Amount] \n" +
                    "  FROM [dbo].["+getDbname()+"$Detailed Cust_ Ledg_ Entry]\n" +
                    "  where [Customer No_]='"+stdId+"'";
         
         String bal =  "select FORMAT(CAST((sum([Debit Amount (LCY)])-sum([Credit Amount (LCY)]))AS DECIMAL(15,6)), 'g15') as balance\n" +
                        "   FROM [dbo].["+getDbname()+"$Detailed Cust_ Ledg_ Entry]\n" +
                        "  where [Customer No_]='"+stdId+"'";
         
         
         System.out.println(q);
          System.out.println(bal);
           
       
     
        try {
            st = com.connections.DbCon.getConMs_ret(false).createStatement();
           
            
             
             rs1=st.executeQuery(bal);
             
             while (rs1.next()){
                 balance=rs1.getString("balance");
             }
            rs1.close();
            
            rs = st.executeQuery(q);
            while (rs.next()) {
               
                
                 id =rs.getString("id");
                   addFeeStatement(new FeeStatement(
                          rs.getString("doctype"),
                          rs.getString("docno"),
                          rs.getString("Debit Amount"),
                          rs.getString("Credit Amount"),
                          rs.getDate("Posting Date"),
                          balance
                           
                   ), id);
               
                      
            }
        
            
        } catch (Exception x) {
            x.printStackTrace();
        } finally {
            try {
                st.close();
            } catch (Exception ex) {
            }
            try {
                rs.close();
            } catch (Exception ex) {
            }
        }
        
     
    }

    @Override
    public List<ChangeCourse> getCourses(String programme) {
System.out.println("connecting ms..");
        try {
            com.connections.DbCon.fixCon_ret();
            System.out.println("ms conn: done");
            System.out.println(com.connections.DbCon.getConMs_ret(true));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
            
		
        
		fetchcourses.clear();
                
	 
                    fetchCourses(programme);
		List<ChangeCourse> allschemes = new ArrayList<ChangeCourse>(fetchcourses.values());

		return allschemes;   
    }
    
     public void fetchCourses(String programme) {
         Statement st=null;
         ResultSet rsall=null,rscur=null;
           String cur="", id="", intake="";
           
          String allcourses="select Code, Description from [dbo].["+getDbname()+"$Programme]";
          
          String curcourse="select Code, Description from [dbo].["+getDbname()+"$Programme] where Code='"+programme+"'";
          System.out.println(allcourses);
          System.out.println(curcourse);
           
       Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH)+1;
        int nxtyear=c.get(Calendar.YEAR)+1;
        String mon=Utils.fixDateMonth(month);
            if(month>=8){
                intake="January "+nxtyear+" Intake";
            }else if(month<8||month==1){
                intake="September "+year+" Intake";
            }
    
        try {
            st = com.connections.DbCon.getConMs_ret(false).createStatement();
           
            
             rscur = st.executeQuery(curcourse);
             
             while(rscur.next()){
                 cur=rscur.getString("Description");
             }
            
              rsall = st.executeQuery(allcourses);
            while (rsall.next()) {
               
                
                 id =rsall.getString("Code");
                   addCourses(new ChangeCourse(
                          rsall.getString("Description"),
                          cur,
                          rsall.getString("Code"),
                          intake
                         
                           
                   ), id);
               
                      
            }
        
            
        } catch (Exception x) {
            x.printStackTrace();
        } finally {
            try {
                st.close();
            } catch (Exception ex) {
            }
            try {
               rsall.close();
               rscur.close();
            } catch (Exception ex) {
            }
        }
        
     
                
       
    }

    @Override
    public List<Reply> saveAdminRequest(String stdId, String requisition_type, String programme_desc, 
            String stage, String deferment_period, String start_date) {
        String rep="", code="";
        int insertstatus=0, coursetranser=0, defermentstatus=0, campustransfer=0,count=0,status=0;
        Random rand = new Random();
      
       int  n = rand.nextInt(10000) + 1;
       String reg="SREQ"+n;
        Statement stinsert=null,strecords=null;
        ResultSet rs=null;
        try {
            //deferment
            String defermentrecords="select Status, [Deferement Period]\n" +
"      ,[Status Change Date] from [dbo].["+getDbname()+"$Customer] where No_='"+stdId+"'";
            
            //course transfer
            String courserecords="select  count(*) count from [dbo].["+getDbname()+"$Student Requisitions]where [Student No]='"+stdId+"' \n" +
                             "and [Requisition Type]="+requisition_type+"";
            
            //insert all
            String insertall="INSERT INTO [dbo].["+getDbname()+"$Student Requisitions]\n" +
"           ([Code],[Student No],[Requisition Type],[Date],[Status],[Programme],[Semester],[Stage],"
                    + "[Responsibility Center]\n,[Effective Date],[Last Date Attended],[Return Semester])\n" +
"            VALUES('"+reg+"','"+stdId+"',"+requisition_type+",current_timestamp,0,'"+programme_desc+"',"
                    + "'"+stage+"','"+stage+"','',current_timestamp,current_timestamp,'')";
            
            String updatedeferment=" update [TUC_DB].[dbo].[TANGAZA_UNIVERSITY$Customer] set Status=4, [Deferement Period]='"+deferment_period+"',\n" +
"	  [Status Change Date]= '"+start_date+"' where No_='"+stdId+"'";
            
            String updatecoursetransfer="update [dbo].["+getDbname()+"$Customer] set Status=10, [Transfer to]='"+programme_desc+"'"
                    + "where No_='"+stdId+"'";
            
            String updatecampustransfer="update [dbo].["+getDbname()+"$Customer] set Status=10, [Transfer to No_]='"+programme_desc+"'"
                    + "where No_='"+stdId+"'";
              
                 System.out.println(insertall);
                 
                
                com.connections.DbCon.fixCon_ret();
             stinsert=com.connections.DbCon.getConMs_ret(false).createStatement();
             strecords=com.connections.DbCon.getConMs_ret(false).createStatement();
            
             if(requisition_type.equals("38")){
                  rs=strecords.executeQuery(courserecords);
                  System.out.println(courserecords);
                  System.out.println(updatecoursetransfer);
             while(rs.next()){
             count=rs.getInt("count");
             }
             if(count==0){
                insertstatus = stinsert.executeUpdate(insertall);
                coursetranser = stinsert.executeUpdate(updatecoursetransfer);
                if(insertstatus == 1 && coursetranser==1){
                	
                    code="1";
                   rep= "Registration Submitted Successfully";
                    }else{
                    code="2";
                    	rep= "Unable to submit request, please try again later.";
                    }
             }else if(count>0){
                 code="3";
                    	rep= "A similar request has already been made.";
             }
                
             }else if(requisition_type.equals("41")){
                  rs=strecords.executeQuery(defermentrecords);
                  System.out.println(defermentrecords);
                  System.out.println(updatedeferment);
             while(rs.next()){
             status=rs.getInt("Status");
             System.out.println("Status "+status);
             }
             if(status==0){
                 insertstatus = stinsert.executeUpdate(insertall);
                 defermentstatus=stinsert.executeUpdate(updatedeferment);
                if(insertstatus == 1 && defermentstatus==1 && status!=4){
                	
                    code="1";
                   rep= "Registration Submitted Successfully";
                    }else{
                    code="2";
                    	rep= "Unable to submit request, please try again later.";
                    }
             }else{
                  code="3";
                    	rep= "A deferment requisition under this student has already been submited.";
             }
             }else  if(requisition_type.equals("37")){
                  rs=strecords.executeQuery(courserecords);
                  System.out.println(courserecords);
                  System.out.println(updatecoursetransfer);
             while(rs.next()){
             count=rs.getInt("count");
             }
             if(count==0){
                insertstatus = stinsert.executeUpdate(insertall);
                campustransfer = stinsert.executeUpdate(updatecampustransfer);
                if(insertstatus == 1 && campustransfer==1){
                	
                    code="1";
                   rep= "Registration Submitted Successfully";
                    }else{
                    code="2";
                    	rep= "Unable to submit request, please try again later.";
                    }
             }else if(count>0){
                 code="3";
                    	rep= "A similar request has already been made.";
             }
                
             }
                
            
        } catch (Exception x) {
            x.printStackTrace();
        }finally {
            try {
               stinsert.close();
               strecords.close();
            } catch (Exception ex) {
            }
            try {
                rs.close();
            } catch (Exception ex) {
            }
        }
       reply.clear();
        addReply(new Reply(code,rep), String.valueOf(insertstatus));
    List<Reply> allschemes = new ArrayList<Reply>(reply.values());
    
        return allschemes;
            }

    @Override
    public List<AdminProgress> getAdminProgress(String stdId, String requisition_type) {
System.out.println("connecting ms..");
        try {
            com.connections.DbCon.fixCon_ret();
            System.out.println("ms conn: done");
            System.out.println(com.connections.DbCon.getConMs_ret(true));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
            
		
        
		fetchprogress.clear();
                
	 
                    fetchProgress(stdId,requisition_type);
		List<AdminProgress> allschemes = new ArrayList<AdminProgress>(fetchprogress.values());

		return allschemes;      
    
    }
    
     public void fetchProgress(String stdId, String requisition_type) {
         Statement st=null;
         ResultSet rscourse=null;
           String  id="";
           
          String courseprogress="select distinct newId() id, a.Programme,a.Status, b.Description from [dbo].[TANGAZA_UNIVERSITY$Student Requisitions] a\n" +
                                "join [dbo].["+getDbname()+"$Programme] b\n" +
                                "on a.Programme=b.Code where a.[Student No]='"+stdId+"' and a.[Requisition Type]="+requisition_type+""  ;
    
          String deffermentProgress="select distinct newId() id, a.[Deferement Period],a.[Status Change Date], b.Status from [dbo].["+getDbname()+"$Customer] a\n" +
                                    "join [dbo].["+getDbname()+"$Student Requisitions] b\n" +
                                    "on a.No_=b.[Student No] where a.No_='"+stdId+"' and b.[Requisition Type]="+requisition_type+"";
    
          String campusprogress = "select newId() id, a.Programme,a.Status, b.Code, b.Name, c.[Transfer to No_] from [dbo].["+getDbname()+"$Student Requisitions] a\n" +
                                    "join [dbo].["+getDbname()+"$Dimension Value] b on a.Programme=b.Code\n" +
                                    "join [dbo].["+getDbname()+"$Customer] c on  a.Programme= c.[Transfer to No_]\n" +
                                    "where c.No_='"+stdId+"' and b.[Global Dimension No_]=1 and a.[Requisition Type]="+requisition_type+"";
    
        try {
            st = com.connections.DbCon.getConMs_ret(false).createStatement();
           
            if(requisition_type.equals("37")){
            rscourse=st.executeQuery(campusprogress);
             while (rscourse.next()) {
               
                
                 id =rscourse.getString("id");
                   addProgress(new AdminProgress(
                          rscourse.getString("Name"),
                          rscourse.getString("Status")
                        
                          
                   ), id);
               
                      
            }
            }
             if(requisition_type.equals("38")){
            rscourse=st.executeQuery(courseprogress);
             while (rscourse.next()) {
               
                
                 id =rscourse.getString("id");
                   addProgress(new AdminProgress(
                          rscourse.getString("Description"),
                          rscourse.getString("Status")
                        
                          
                   ), id);
               
                      
            }
             }
              if(requisition_type.equals("41")){
            rscourse=st.executeQuery(deffermentProgress);
             while (rscourse.next()) {
               
                
                 id =rscourse.getString("id");
                   addProgress(new AdminProgress(
                          rscourse.getString("Deferement Period"),
                          rscourse.getString("Status")
                        
                          
                   ), id);
               
                      
            }
              }
         
        } catch (Exception x) {
            x.printStackTrace();
        } finally {
            try {
                st.close();
            } catch (Exception ex) {
            }
            try {
                rscourse.close();
            } catch (Exception ex) {
            }
        }
        
       
    }

    @Override
    public List<CampusList> getCampuses() {
System.out.println("connecting ms..");
        try {
            com.connections.DbCon.fixCon_ret();
            System.out.println("ms conn: done");
            System.out.println(com.connections.DbCon.getConMs_ret(true));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
            
		fetchcampuses.clear();
                
	 
                    fetchCampuses();
		List<CampusList> allschemes = new ArrayList<CampusList>(fetchcampuses.values());

		return allschemes;    

    }
    
    public void fetchCampuses() {
         Statement st=null;
         ResultSet rs=null;
           String  id="";
           
       String campuses="select Code, Name from [dbo].["+getDbname()+"$Dimension Value] where [Global Dimension No_]=1";
        try {
            st = com.connections.DbCon.getConMs_ret(false).createStatement();
           
            rs=st.executeQuery(campuses);
            
            while (rs.next()) {
               
                
                 id =rs.getString("Code");
                   addCampus(new CampusList(
                          rs.getString("Code"),
                          rs.getString("Name")
                   ), id);
               
                      
            }
        
            
        } catch (Exception x) {
            x.printStackTrace();
        } finally {
            try {
                st.close();
            } catch (Exception ex) {
            }
            try {
                rs.close();
            } catch (Exception ex) {
            }
        }
        
    }

    @Override
    public List<DefermentRecords> getDefermentRecords(String stdId) {
System.out.println("connecting ms..");
        try {
            com.connections.DbCon.fixCon_ret();
            System.out.println("ms conn: done");
            System.out.println(com.connections.DbCon.getConMs_ret(true));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
            
		
        
		fetchdeferments.clear();
                
	 
                    fetchDeferments(stdId);
		List<DefermentRecords> allschemes = new ArrayList<DefermentRecords>(fetchdeferments.values());

		return allschemes;      
    
    }
    
    public void fetchDeferments(String stdId) {
         Statement st=null;
         ResultSet rs=null;
           String  id="";
           
       String records="select Status, [Deferement Period]\n" +
                        ",[Status Change Date] from [dbo].["+getDbname()+"$Customer] where No_='"+stdId+"'";
        try {
            st = com.connections.DbCon.getConMs_ret(false).createStatement();
           
            rs=st.executeQuery(records);
            
            while (rs.next()) {
               
                
                 id =rs.getString("Status");
                   addDeferments(new DefermentRecords(
                          rs.getString("Status"),
                          rs.getString("Deferement Period"),
                           rs.getDate("Status Change Date")
                   ), id);
               
                      
            }
        

            
        } catch (Exception x) {
            x.printStackTrace();
        } finally {
            try {
                st.close();
            } catch (Exception ex) {
            }
            try {
                rs.close();
            } catch (Exception ex) {
            }
        }
        
     
                
       
    }

    @Override
    public List<Reply> extendDeferment(String stdId, String period) {
        String rep="", code="";
        int updatestatus=0;
  
        Statement st=null;
        try {
            
       
             String update = "update [dbo].["+getDbname()+"$Customer] set [Deferement Period]='"+period+"' where No_='"+stdId+"'";
             
                
                System.out.println(update);
                com.connections.DbCon.fixCon_ret();
             st=com.connections.DbCon.getConMs_ret(false).createStatement();
                updatestatus = st.executeUpdate(update);
                if(updatestatus == 1){
                	
                    code="1";
                   rep= "Request Submitted Successfully";
                    }else{
                    code="2";
                    	rep= "Unable to submit request, please try again later.";
                    }
                
	
                
            
        } catch (Exception x) {
            x.printStackTrace();
        } finally {
            try {
                st.close();
            } catch (Exception ex) {
            }
           
        }
       reply.clear();
        addReply(new Reply(
                code,
                rep
        ), String.valueOf(updatestatus));
        
    List<Reply> allschemes = new ArrayList<Reply>(reply.values());
    
        return allschemes;    }

    @Override
    public List<RetakesModel> getRetakeCount(String stdId) {
System.out.println("connecting ms..");
        try {
            com.connections.DbCon.fixCon_ret();
            System.out.println("ms conn: done");
            System.out.println(com.connections.DbCon.getConMs_ret(true));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
            
		fetchretakes.clear();
                
                    fetchRetakes(stdId);
		List<RetakesModel> allschemes = new ArrayList<RetakesModel>(fetchretakes.values());

		return allschemes;     
    
    }
    
     public void fetchRetakes(String stdId) {
         Statement st=null;
         ResultSet rs=null;
           String  id="";
           
       String records="  select count(*) count from  [dbo].["+getDbname()+"$Exam Results] where [Student No_]='"+stdId+"' and [Re-Sited]=1";
        try {
            st = com.connections.DbCon.getConMs_ret(false).createStatement();
           
            rs=st.executeQuery(records);
            
            while (rs.next()) {
               
                
                 id =rs.getString("count");
                   addRetakes(new RetakesModel(
                          rs.getInt("count")
                   ), id);
               
                      
            }
        

            
        } catch (Exception x) {
            x.printStackTrace();
        } finally {
            try {
                st.close();
            } catch (Exception ex) {
            }
            try {
                rs.close();
            } catch (Exception ex) {
            }
        }
        
     
                
       
    }

    @Override
    public List<Reply> changePassword(String stdId, String newpassword) {
        String rep="", code="";
        int updatestatus=0;
        Random rand = new Random();

       int  n = rand.nextInt(10000) + 1;
       String reg="REG-"+n;
        Statement st=null;
        try {
            
       
             String update = "update [dbo].["+getDbname()+"$Customer] "
                     + "set Password = '"+newpassword+"', [Changed Password]=1 where No_='"+stdId+"'";
             
                
                System.out.println(update);
                com.connections.DbCon.fixCon_ret();
             st=com.connections.DbCon.getConMs_ret(false).createStatement();
                updatestatus = st.executeUpdate(update);
                if(updatestatus == 1){
                	
                    code="1";
                   rep= "Password Changed";
                    }else{
                    code="2";
                    	rep= "Unable to change password, please try again later.";
                    }
                
	
        } catch (Exception x) {
            x.printStackTrace();
        }
       reply.clear();
        addReply(new Reply(code,rep), String.valueOf(updatestatus));
    List<Reply> allschemes = new ArrayList<Reply>(reply.values());
    
        return allschemes;
            }

  
     
    
    
}
