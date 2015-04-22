package org.springframework.security.oauth.api.model.healthcare.clients;

import java.sql.Date;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class BenefitCategory {
	
	//implements Serializable
	//private static final long serialVersionUID = -7788619177798333712L;
	private String id;
	
	//@NotNull
	//@NotEmpty	
	private String medCatg;
	private String smartMsplan;
	private String smartFsplan;
	private String smartCatNo;
	private String catDesc;
	private String inPatientOverall;
	private String outPatientOverall;
	private String policyNumber;
	private String autoReplenishInd;
	private String autoGrowthInd;
	private String userId;
	private String autoGrowthPct;
	private String clnCatCode;
	private String spendThreshold;
	private String spendThrespct;
	private String waitingPeriod;
	private Date dateAdded;
	private String cutOffInd;
	private String cutOffAge;
	private String clnPolCode;
	private String clnPolId;
	private String status;
	private String smartMplan;
	private String smartFplan;
	private String insurerId;
    
    
	public BenefitCategory() {
		
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

    public String getMedCatg() {
		return medCatg;
	}
	public void setMedCatg(String medCatg) {
		this.medCatg = medCatg;
	}
    public String getSmartMsplan() {
		return smartMsplan;
	}
	public void setSmartMsplan(String smartMsplan) {
		this.smartMsplan = smartMsplan;
	}
    public String getSmartFsplan() {
		return smartFsplan;
	}
	public void setSmartFsplan(String smartFsplan) {
		this.smartFsplan = smartFsplan;
	}
    public String getSmartCatNo() {
		return smartCatNo;
	}
	public void setSmartCatNo(String smartCatNo) {
		this.smartCatNo = smartCatNo;
	}
    public String getCatDesc() {
		return catDesc;
	}
	public void setCatDesc(String catDesc) {
		this.catDesc = catDesc;
	}
    public String getInPatientOverall() {
		return inPatientOverall;
	}
	public void setInPatientOverall(String inPatientOverall) {
		this.inPatientOverall =inPatientOverall;
	}
    public String getOutPatientOverall() {
		return outPatientOverall;
	}
	public void setOutPatientOverall(String outPatientOverall) {
		this.outPatientOverall = outPatientOverall;
	}
    public String getPolicyNumber() {
		return policyNumber;
	}
	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}
    public String getAutoReplenishInd() {
		return autoReplenishInd;
	}
	public void setAutoReplenishInd(String autoReplenishInd) {
		this.autoReplenishInd = autoReplenishInd;
	}
    public String getAutoGrowthInd() {
		return autoGrowthInd;
	}
	public void setAutoGrowthInd(String autoGrowthInd) {
		this.autoGrowthInd = autoGrowthInd;
	}
    public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
    public String getAutoGrowthPct() {
		return autoGrowthPct;
	}
	public void setAutoGrowthPct(String autoGrowthPct) {
		this.autoGrowthPct = autoGrowthPct;
	}
    public String getClnCatCode() {
		return clnCatCode;
	}
	public void setClnCatCode(String clnCatCode) {
		this.clnCatCode = clnCatCode;
	}
    public String getSpendThreshold() {
		return spendThreshold;
	}
	public void setSpendThreshold(String spendThreshold) {
		this.spendThreshold = spendThreshold;
	}
    public String getSpendThrespct() {
		return spendThrespct;
	}
	public void setSpendThrespct(String spendThrespct) {
		this.spendThrespct = spendThrespct;
	}
    public String getWaitingPeriod() {
		return waitingPeriod;
	}
	public void setWaitingPeriod(String waitingPeriod) {
		this.waitingPeriod = waitingPeriod;
	}
    public Date getDateAdded() {
		return dateAdded;
	}
	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}
    public String getCutOffInd() {
		return cutOffInd;
	}
	public void setCutOffInd(String cutOffInd) {
		this.cutOffInd = cutOffInd;
	}
    public String getCutOffAge() {
		return cutOffAge;
	}
	public void setCutOffAge(String cutOffAge) {
		this.cutOffAge = cutOffAge;
	}
    public String getClnPolCode() {
		return clnPolCode;
	}
	public void setClnPolCode(String clnPolCode) {
		this.clnPolCode = clnPolCode;
	}
    public String getClnPolId() {
		return clnPolId;
	}
	public void setClnPolId(String clnPolId) {
		this.clnPolId = clnPolId;
	}
    public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
    public String getSmartMplan() {
		return smartMplan;
	}
	public void setSmartMplan(String smartMplan) {
		this.smartMplan = smartMplan;
	}
    public String getSmartFplan() {
		return smartFplan;
	}
	public void setSmartFplan(String smartFplan) {
		this.smartFplan = smartFplan;
	}
    public String getInsurerId(){
		   return insurerId;
		}
	public void setInsurerId(String insurerId) {
	    this.insurerId = insurerId;
	}


	public BenefitCategory(
			String medCatg,
			String smartMsplan,
			String smartFsplan,
			String smartCatNo,
			String catDesc,
			String inPatientOverall,
			String outPatientOverall,
			String policyNumber,
			String autoReplenishInd,
			String autoGrowthInd,
			String userId,
			String autoGrowthPct,
			String clnCatCode,
			String spendThreshold,
			String spendThrespct,
			String waitingPeriod,
			Date dateAdded,
			String cutOffInd,
			String cutOffAge,
			String clnPolCode,
			String clnPolId,
			String status,
			String smartMplan,
			String smartFplan,
			String recId,
			String insurerId
			){
	this.medCatg = medCatg;
	this.smartMsplan = smartMsplan;
	this.smartFsplan = smartFsplan;
	this.smartCatNo = smartCatNo;
	this.catDesc = catDesc;
	this.inPatientOverall = inPatientOverall;
	this.outPatientOverall = outPatientOverall;
	this.policyNumber = policyNumber;
	this.autoReplenishInd = autoReplenishInd;
	this.autoGrowthInd = autoGrowthInd;
	this.userId = userId;
	this.autoGrowthPct = autoGrowthPct;
	this.clnCatCode = clnCatCode;
	this.spendThreshold = spendThreshold;
	this.spendThrespct = spendThrespct;
	this.waitingPeriod = waitingPeriod;
	this.dateAdded = dateAdded;
	this.cutOffInd = cutOffInd;
	this.cutOffAge = cutOffAge;
	this.clnPolCode = clnPolCode;
	this.clnPolId = clnPolId;
	this.status = status;
	this.smartMplan = smartMplan;
	this.smartFplan = smartFplan;
	this.id = recId;
	this.insurerId = insurerId;
	}

	@Override
	public String toString() {
		return String.format("category code=%s, user id=%s", this.clnCatCode, this.userId);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clnCatCode == null) ? 0 : clnCatCode.hashCode());
		return result;
	}

	@Override
	// Benefit_Category objects are equal if they have the same name.
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BenefitCategory other = (BenefitCategory) obj;
		if (clnCatCode == null) {
			if (other.clnCatCode != null)
				return false;
		} else if (!clnCatCode.equals(other.clnCatCode))
			return false;
		return true;
	}
	

	

}
