package org.springframework.security.oauth.api.data.healthcare.clients;

public class RequestMapIntegstaging {

	 private static final long serialVersionUID = -6470090944414208496L;
	 private String q;
	 private String customerid;
	 private String country;
	 private String memberno;
	 private int startindex;
	 private int maxresults;
	 private int status;
	 private String restrict;
	 private String orderby;
	 private String[] DBParams;


	 
	  public RequestMapIntegstaging(String q, String customerid, String country, int startindex,
				int maxresults, int status, String restrict, String orderby) {
			
		        this.q = q;
			    this.customerid = customerid;
			    this.country = country;
			    this.startindex = startindex;
			    this.maxresults = maxresults;
			    this.status = status;
			    this.restrict = restrict;
			    this.orderby = orderby;
			    setMaxResultsManager();
			    setDBParamsManager(country);
	  }
		
	  public RequestMapIntegstaging(String customerid, String country, int startindex,
			int maxresults, int status, String restrict, String orderby) {
		
		    this.customerid = customerid;
		    this.country = country;
		    this.startindex = startindex;
		    this.maxresults = maxresults;
		    this.status = status;
		    this.restrict = restrict;
		    this.orderby = orderby;
		    setMaxResultsManager();
		    setDBParamsManager(country);
		    
	   }
	  
	  public RequestMapIntegstaging(String q, String memberno, String country, int startindex, int maxresults, String orderby) {
			
	        this.q = q;
	        this.memberno = memberno;
		    this.country = country;
		    this.startindex = startindex;
		    this.maxresults = maxresults;
		    this.orderby = orderby;
		    setMaxResultsManager();
		    setDBParamsManager(country);
}
	
	
	  public RequestMapIntegstaging(String customerid, String country) {
		
		    this.customerid = customerid;
		    this.country = country;
		    setDBParamsManager(country);
	    
	  }

	  public String[] getDBParams() {
		//setDBParamsManager();
	    return this.DBParams;
	    
	  }

      public void setDBParams(String[] DBParams) {
	  
	    this.DBParams = DBParams;
	    
	  }
  
	  public String getQ() {
		    return this.q;
		  }

	  public void setQ(String q) {
		    this.q = q;
		  }
	  
	  public String getCustomerId() {
		    return this.customerid;
		  }

	  public void setCustomerId(String customerid) {
		    this.customerid = customerid;
		  }
	
	  public String getCountry() {
		    return this.country;
		  }

	  public void setCountry(String country) {
		    this.country = country;
		    setDBParamsManager(country);
		  }
	
	  public int getStartIndex() {
		    return this.startindex;
		  }

	  public void setStartIndex(int startindex) {
		    this.startindex = startindex;
		  }
	
	  public int getMaxResults() {
		    return this.maxresults;
		  }

	  public void setMaxResults(int maxresults) {
		    this.maxresults = maxresults;
		    setMaxResultsManager();
		  }
	  public void setMaxResultsManager() {
		    int maxresults = this.maxresults;
		    if((maxresults != 0) && (maxresults>100)){
		    	this.maxresults = 100;
		    }
		  }
	  public int getStatus() {
		    return this.status;
		  }

	  public void setStatus(int status) {
		    this.status = status;
		  }
	  
	  public String getRestrict() {
		    return this.restrict;
		  }

	  public void setRestrict(String restrict) {
		    this.restrict = restrict;
		  }
	  
	  public String getOrderBy() {
		    return this.orderby;
		  }

	  public void setOrderBy(String orderby) {
		    this.orderby = orderby;
		  }
	  
	  public String getMemberNo() {
		    return this.memberno;
		  }
	  
	  public void setMemberNo(String memberno) {
		    this.memberno = memberno;
		  }

	  public void setDBParamsManager(String country) {
		  
		  country.toLowerCase();
		  String[] DBP = new String[10];
	      switch (country) {
            case "kenya":  
          	  DBP[0] = "integstaging";
          	  DBP[1] = "jdbc:oracle:thin:@//192.180.3.14:1521/"+DBP[0];
          	  DBP[2] = "INTERACTIVE";
          	  DBP[3] = "Int3ract123";
            	/*
             DBP[0] = "smart";
             DBP[1] = "jdbc:oracle:thin:@192.180.3.3:1521:"+DBP[0];
             DBP[2] = "SMART";
             DBP[3] = "smart_123";
             */
                   break;
            case "abacus":  
        	  DBP[0] = "integstaging";
        	  DBP[1] = "jdbc:oracle:thin:@//192.180.3.14:1521/"+DBP[0];
        	  DBP[2] = "INTERACTIVE";
        	  DBP[3] = "Int3ract123";
                   break;
            case "uganda":  
        	  DBP[0] = "";
        	  DBP[1] = "";
        	  DBP[2] = "";
        	  DBP[3] = "";
                   break;
            case "tanzania":
      	      DBP[0] = "";
      	      DBP[1] = "";
      	      DBP[2] = "";
      	      DBP[3] = "";
                   break;
            case "southsudan":
      	      DBP[0] = "";
      	      DBP[1] = "";
      	      DBP[2] = "";
      	      DBP[3] = "";
                   break;
            case "southafrica":
      	      DBP[0] = "";
      	      DBP[1] = "";
      	      DBP[2] = "";
      	      DBP[3] = "";
                   break;
            case "zambia":
      	      DBP[0] = "";
      	      DBP[1] = "";
      	      DBP[2] = "";
      	      DBP[3] = "";
                   break;
            case "nigeria":
      	      DBP[0] = "";
      	      DBP[1] = "";
      	      DBP[2] = "";
      	      DBP[3] = "";
                   break;
            default:
        	  DBP[0] = "integstaging";
        	  DBP[1] = "jdbc:oracle:thin:@//192.180.3.14:1521/"+DBP[0];
        	  DBP[2] = "INTERACTIVE";
        	  DBP[3] = "Int3ract123";
                   break;
             }
	      
	     this.DBParams = DBP;

	 }
	  


}
