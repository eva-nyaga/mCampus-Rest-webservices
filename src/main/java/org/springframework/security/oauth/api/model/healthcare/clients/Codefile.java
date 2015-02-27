package org.springframework.security.oauth.api.model.healthcare.clients;

import java.sql.Date;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class Codefile {
	
	private String REC_ID;	
	private String MEDICALAID_CODE;	
	private String MEDICALAID_PLAN;	
	private String POOL_NUMBER;	
	private String POOL_DESCRIPTION;	
	private String ALLOCATION;	
	private String FAMILY;	
	private String POOL_LINK;	
	private String BENEFIT_TYPE;	
	private String BENEFIT;	
	private String POINTUSAGE;	
	private String POOL_COPAY;	
	private String POOLSTART_DATE;	
	private String POOLSTART_MONTH;	
	private String BANKNR;	
	private String POINT_TYPE;	
	private String SERVICE_TYPE;	
	private String DATE_CHANGED;	
	private String USER_NAME;	
	private String MEM_ASSIGNED;	
	private String GENDER;	
	private String MUST_PAY;	
	private String PREAUTH;	
	private String OWNER;
    

	public Codefile() {
		
	}

	public String getREC_ID() { 
		return REC_ID;	
	}	
	public String getMEDICALAID_CODE() {
		return MEDICALAID_CODE;	
	}	
	public String getMEDICALAID_PLAN() {
	    return MEDICALAID_PLAN;		
	}	
	public String getPOOL_NUMBER() {
	    return POOL_NUMBER;		
	}	
	public String getPOOL_DESCRIPTION() {
	    return POOL_DESCRIPTION;		
	}	
	public String getALLOCATION() {
	    return ALLOCATION;		
	}	
	public String getFAMILY() {
		return FAMILY;	
	}	
	public String getPOOL_LINK() {
		return POOL_LINK;	
	}	
	public String getBENEFIT_TYPE() {
		return BENEFIT_TYPE;	
	}	
	public String getBENEFIT() {
		return BENEFIT;	
	}	
	public String getPOINTUSAGE() {
		return POINTUSAGE;	
	}	
	public String getPOOL_COPAY() {
		return POOL_COPAY;	
	}	
	public String getPOOLSTART_DATE() {
		return POOLSTART_DATE;	
	}	
	public String getPOOLSTART_MONTH() {
		return POOLSTART_MONTH;	
	}	
	public String getBANKNR() {
		return BANKNR;	
	}	
	public String getPOINT_TYPE() {
		return POINT_TYPE;	
	}	
	public String getSERVICE_TYPE() {
		return SERVICE_TYPE;	
	}	
	public String getDATE_CHANGED() {
		return DATE_CHANGED;	
	}	
	public String getUSER_NAME() {
		return USER_NAME;	
	}	
	public String getMEM_ASSIGNED() {
		return MEM_ASSIGNED;	
	}	
	public String getGENDER() {
		return GENDER;	
	}	
	public String getMUST_PAY() {
		return MUST_PAY;	
	}	
	public String getPREAUTH() {
		return PREAUTH;	
	}	
	public String getOWNER() {
		return OWNER;	
	}
	
	
	public void setREC_ID(String REC_ID) {
        this.REC_ID = REC_ID;
	}
	public void setMEDICALAID_CODE(String MEDICALAID_CODE) {
        this.MEDICALAID_CODE = MEDICALAID_CODE;
	}
	public void setMEDICALAID_PLAN(String MEDICALAID_PLAN) {
        this.MEDICALAID_PLAN = MEDICALAID_PLAN;
	}
	public void setPOOL_NUMBER(String POOL_NUMBER) {
        this.POOL_NUMBER = POOL_NUMBER;
	}
	public void setPOOL_DESCRIPTION(String POOL_DESCRIPTION) {
        this.POOL_DESCRIPTION = POOL_DESCRIPTION;
	}
	public void setALLOCATION(String ALLOCATION) {
        this.ALLOCATION = ALLOCATION;
	}
	public void setFAMILY(String FAMILY) {
        this.FAMILY = FAMILY;
	}
	public void setPOOL_LINK(String POOL_LINK) {
        this.POOL_LINK = POOL_LINK;
	}
	public void setBENEFIT_TYPE(String BENEFIT_TYPE) {
        this.BENEFIT_TYPE = BENEFIT_TYPE;
	}
	public void setBENEFIT(String BENEFIT) {
        this.BENEFIT = BENEFIT;
	}
	public void setPOINTUSAGE(String POINTUSAGE) {
        this.POINTUSAGE = POINTUSAGE;
	}
	public void setPOOL_COPAY(String POOL_COPAY) {
        this.POOL_COPAY = POOL_COPAY;
	}
	public void setPOOLSTART_DATE(String POOLSTART_DATE) {
        this.POOLSTART_DATE = POOLSTART_DATE;
	}
	public void setPOOLSTART_MONTH(String POOLSTART_MONTH) {
        this.POOLSTART_MONTH = POOLSTART_MONTH;
	}
	public void setBANKNR(String BANKNR) {
        this.BANKNR = BANKNR;
	}
	public void setPOINT_TYPE(String POINT_TYPE) {
        this.POINT_TYPE = POINT_TYPE;
	}
	public void setSERVICE_TYPE(String SERVICE_TYPE) {
        this.SERVICE_TYPE = SERVICE_TYPE;
	}
	public void setDATE_CHANGED(String DATE_CHANGED) {
        this.DATE_CHANGED = DATE_CHANGED;
	}
	public void setUSER_NAME(String USER_NAME) {
        this.USER_NAME = USER_NAME;
	}
	public void setMEM_ASSIGNED(String MEM_ASSIGNED) {
        this.MEM_ASSIGNED = MEM_ASSIGNED;
	}
	public void setGENDER(String GENDER) {
        this.GENDER = GENDER;
	}
	public void setMUST_PAY(String MUST_PAY) {
        this.MUST_PAY = MUST_PAY;
	}
	public void setPREAUTH(String PREAUTH) {
        this.PREAUTH = PREAUTH;
	}
	public void setOWNER() {
        this.OWNER = OWNER;
	}



	public Codefile(
			String  REC_ID,
			String  MEDICALAID_CODE,
			String  MEDICALAID_PLAN,
			String  POOL_NUMBER,
			String  POOL_DESCRIPTION,
			String  ALLOCATION,
			String  FAMILY,
			String  POOL_LINK,
			String  BENEFIT_TYPE,
			String  BENEFIT,
			String  POINTUSAGE,
			String  POOL_COPAY,
			String  POOLSTART_DATE,
			String  POOLSTART_MONTH,
			String  BANKNR,
			String  POINT_TYPE,
			String  SERVICE_TYPE,
			String  DATE_CHANGED,
			String  USER_NAME,
			String  MEM_ASSIGNED,
			String  GENDER,
			String  MUST_PAY,
			String  PREAUTH,
			String  OWNER

			){

		this.REC_ID = REC_ID;
		this.MEDICALAID_CODE = MEDICALAID_CODE;
		this.MEDICALAID_PLAN = MEDICALAID_PLAN;
		this.POOL_NUMBER = POOL_NUMBER;
		this.POOL_DESCRIPTION = POOL_DESCRIPTION;
		this.ALLOCATION = ALLOCATION;
		this.FAMILY = FAMILY;
		this.POOL_LINK = POOL_LINK;
		this.BENEFIT_TYPE = BENEFIT_TYPE;
		this.BENEFIT = BENEFIT;
		this.POINTUSAGE = POINTUSAGE;
		this.POOL_COPAY = POOL_COPAY;
		this.POOLSTART_DATE = POOLSTART_DATE;
		this.POOLSTART_MONTH = POOLSTART_MONTH;
		this.BANKNR = BANKNR;
		this.POINT_TYPE = POINT_TYPE;
		this.SERVICE_TYPE = SERVICE_TYPE;
		this.DATE_CHANGED = DATE_CHANGED;
		this.USER_NAME = USER_NAME;
		this.MEM_ASSIGNED = MEM_ASSIGNED;
		this.GENDER = GENDER;
		this.MUST_PAY = MUST_PAY;
		this.PREAUTH = PREAUTH;
		this.OWNER = OWNER;
	}


	

	@Override
	public String toString() {
		return String.format("Medical Aid Code=%s, Medical Aid Plan=%s", this.MEDICALAID_CODE, this.MEDICALAID_PLAN);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((REC_ID == null) ? 0 : REC_ID.hashCode());
		return result;
	}

	@Override
	// Benefit objects are equal if they have the same name.
	public boolean equals(Object obj) {
	
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Codefile other = (Codefile) obj;
		if (REC_ID == null) {
			if (other.REC_ID != null)
				return false;
		} else if (!REC_ID.equals(other.REC_ID))
			return false;
		return true;
		
	}
	

	

}
