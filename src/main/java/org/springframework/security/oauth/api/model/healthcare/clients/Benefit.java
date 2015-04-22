package org.springframework.security.oauth.api.model.healthcare.clients;

import java.sql.Date;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class Benefit {
	
	//implements Serializable
	//private static final long serialVersionUID = -7788619177798333712L;
	private String id;
	
	//@NotNull
	//@NotEmpty	
    private String smartBenId;
    private String benefitDesc;
    private String policyNumber;
    private String catCode;
    private String benTypeId;
    private String coPayAmt;
    private String coPayPercent;
    private String benLinked2Id;
    private String subLimitAmt;
    private String limitAmt;
    private Date dateAdded;
    private String genderApplicability;
    private String waitingPeriod;
    private Date effectiveDate;
    private String serviceType;
    private String memAssignedBenefit;
    private String spendThreshold;
    private String spendThrespct;
    private String autoReplenishInd;
    private Date benEndDate;
    private String userId;
    private String clnBenCode;
    private String clnBenClauseCode;
    private String benTypDesc;
    private String benLinked2Tqcode;
    private String autoGrowthInd;
    private String autoGrowthPct;
    private String clnPolCode;
    private String clnPolId;
    private String status;
    private String recId;
    private String pointUsage;
    
    private String copayInd;
    private String autoReplenishLimtype;
    private String autoReplenishLimit;
    private String autoGrowthRateInd;
    private String autoGrowthRate;
    private String autoGrowthCeiling;
    private String cutOffInd;
    private String cutOffAge;
    private String insurerId;
    private String layeredInd;
    private String layered1Type;
    private String layered1Value;
    private String layered2Type;
    private String layered2Value;
    private String layered3Type;
    private String layered3Value;
    
	public Benefit() {
		
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
    public String getSmartBenId() {
		return smartBenId;
	}
	public void setSmartBenId(String smartBenId) {
		this.smartBenId = smartBenId;
	}
    public String getBenefitDesc() {
		return benefitDesc;
	}
	public void setBenefitDesc(String benefitDesc) {
		this.benefitDesc = benefitDesc;
	}
    public String getPolicyNumber() {
		return policyNumber;
	}
	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}
    public String getCatCode() {
		return catCode;
	}
	public void setCatCode(String catCode) {
		this.catCode = catCode;
	}
    public String getBenTypeId() {
		return benTypeId;
	}
	public void setBenTypeId(String benTypeId) {
		this.benTypeId = benTypeId;
	}
    public String getCoPayAmt() {
		return coPayAmt;
	}
	public void setCoPayAmt(String coPayAmt) {
		this.coPayAmt = coPayAmt;
	}
    public String getCoPayPercent() {
		return coPayPercent;
	}
	public void setCoPayPercent(String coPayPercent) {
		this.coPayPercent = coPayPercent;
	}
    public String getBenLinked2Id() {
		return benLinked2Id;
	}
	public void setBenLinked2Id(String benLinked2Id) {
		this.benLinked2Id = benLinked2Id;
	}
    public String getSubLimitAmt() {
		return subLimitAmt;
	}
	public void setSubLimitAmt(String subLimitAmt) {
		this.subLimitAmt = subLimitAmt;
	}
    public String getLimitAmt() {
		return limitAmt;
	}
	public void setLimitAmt(String limitAmt) {
		this.limitAmt = limitAmt;
	}
    public Date getDateAdded() {
		return dateAdded;
	}
	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}
    public String getGenderApplicability() {
		return genderApplicability;
	}
	public void setGenderApplicability(String genderApplicability) {
		this.genderApplicability = genderApplicability;
	}
    public String getWaitingPeriod() {
		return waitingPeriod;
	}
	public void setWaitingPeriod(String waitingPeriod) {
		this.waitingPeriod = waitingPeriod;
	}
    public Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
    public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
    public String getMemAssignedBenefit() {
		return memAssignedBenefit;
	}
	public void setMemAssignedBenefit(String memAssignedBenefit) {
		this.memAssignedBenefit = memAssignedBenefit;
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
    public String getAutoReplenishInd() {
		return autoReplenishInd;
	}
	public void setAutoReplenishInd(String autoReplenishInd) {
		this.autoReplenishInd = autoReplenishInd;
	}
    public Date getBenEndDate() {
		return benEndDate;
	}
	public void setBenEndDate(Date benEndDate) {
		this.benEndDate = benEndDate;
	}
    public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
    public String getClnBenCode() {
		return clnBenCode;
	}
	public void setClnBenCode(String clnBenCode) {
		this.clnBenCode = clnBenCode;
	}
    public String getClnBenClauseCode() {
		return clnBenClauseCode;
	}
	public void setClnBenClauseCode(String clnBenClauseCode) {
		this.clnBenClauseCode = clnBenClauseCode;
	}
    public String getBenTypDesc() {
		return benTypDesc;
	}
	public void setBenTypDesc(String benTypDesc) {
		this.benTypDesc = benTypDesc;
	}
    public String getBenLinked2Tqcode() {
		return benLinked2Tqcode;
	}
	public void setBenLinked2Tqcode(String benLinked2Tqcode) {
		this.benLinked2Tqcode = benLinked2Tqcode;
	}
    public String getAutoGrowthInd() {
		return autoGrowthInd;
	}
	public void setAutoGrowthInd(String autoGrowthInd) {
		this.autoGrowthInd = autoGrowthInd;
	}
    public String getAutoGrowthPct() {
		return autoGrowthPct;
	}
	public void setAutoGrowthPct(String autoGrowthPct) {
		this.autoGrowthPct = autoGrowthPct;
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
    public String getRecId() {
		return recId;
	}
	public void setRecId(String recId) {
		this.recId = recId;
	}
    public String getPointUsage() {
		return pointUsage;
	}
	public void setPointUsage(String pointUsage) {
		this.pointUsage = pointUsage;
	}
	public void setLayeredInd(String layeredInd) {
		this.layeredInd = layeredInd;
	}
	
    public String getLayeredInd() {
		return layeredInd;
    }
    public String getCopayInd() {
		return copayInd;
    }
    public String getAutoReplenishLimtype() {
		return autoReplenishLimtype;
    }
    public String getAutoReplenishLimit() {
		return autoReplenishLimit;
    }
    public String getAutoGrowthRateInd() {
		return autoGrowthRateInd;
    }
    public String getAutoGrowthRate() {
		return autoGrowthRate;
    }
    public String getAutoGrowthCeiling() {
		return autoGrowthCeiling;
    }
    public String getCutOffInd() {
		return cutOffInd;
    }
    public String getCutOffAge() {
		return cutOffAge;
    }
	
    public void setCopayInd(String copayInd) {
		this.copayInd  = copayInd;
    }
    public void setAutoReplenishLimtype(String autoReplenishLimtype) {
		this.autoReplenishLimtype  =  autoReplenishLimtype;
    }
    public void setAutoReplenishLimit(String autoReplenishLimit) {
		this.autoReplenishLimit  =  autoReplenishLimit;
    }
    public void setAutoGrowthRateInd(String autoGrowthRateInd) {
		this.autoGrowthRateInd  =  autoGrowthRateInd;
    }
    public void setAutoGrowthRate(String autoGrowthRate) {
		this.autoGrowthRate  =  autoGrowthRate;
    }
    public void setAutoGrowthCeiling(String autoGrowthCeiling) {
		this.autoGrowthCeiling  =  autoGrowthCeiling;
    }
    public void setCutOffInd(String cutOffInd) {
		this.cutOffInd  =  cutOffInd;
    }
    public void setCutOffAge(String cutOffAge) {
		this.cutOffAge  =  cutOffAge;
    }
	
	public String getInsurerId() {
		return insurerId;
	}
	public void setInsurerId(String insurerId) {
		this.insurerId = insurerId;
	}
	public String getLayered1Type() {
		return layered1Type;
	}
	public void setLayered1Type(String layered1Type) {
		this.layered1Type = layered1Type;
	}
	public String getLayered1Value() {
		return layered1Value;
	}
	public void setLayered1Value(String layered1Value) {
		this.layered1Value = layered1Value;
	}
	public String getLayered2Type() {
		return layered2Type;
	}
	public void setLayered2Type(String layered2Type) {
		this.layered2Type = layered2Type;
	}
	public String getLayered2Value() {
		return layered2Value;
	}
	public void setLayered2Value(String layered2Value) {
		this.layered2Value = layered2Value;
	}
	public String getLayered3Type() {
		return layered3Type;
	}
	public void setLayered3Type(String layered3Type) {
		this.layered3Type = layered3Type;
	}
	public String getLayered3Value() {
		return layered3Value;
	}
	public void setLayered3Value(String layered3Value) {
		this.layered3Value = layered3Value;
	}


	public Benefit(
			String benefitDesc,
			String policyNumber,
			String catCode,
			String benTypeId,
			String coPayAmt,
			String coPayPercent,
			String benLinked2Id,
			String subLimitAmt,
			String limitAmt,
			Date dateAdded,
			String genderApplicability,
			String waitingPeriod,
			Date effectiveDate,
			String serviceType,
			String memAssignedBenefit,
			String spendThreshold,
			String spendThrespct,
			String autoReplenishInd,
			Date benEndDate,
			String userId,
			String clnBenCode,
			String clnBenClauseCode,
			String benTypDesc,
			String benLinked2Tqcode,
			String autoGrowthInd,
			String autoGrowthPct,
			String clnPolCode,
			String clnPolId,
			String recId,
			String smartBenId,
			String pointUsage,
			String copayInd,
			String autoReplenishLimtype,
			String autoReplenishLimit,
			String autoGrowthRateInd,
			String autoGrowthRate,
			String autoGrowthCeiling,
			String cutOffInd,
			String cutOffAge,
			String insurerId,
			String layeredInd,
		    String layered1Type,
		    String layered1Value,
		    String layered2Type,
		    String layered2Value,
		    String layered3Type,
		    String layered3Value
			){
		this.smartBenId = smartBenId;
		this.benefitDesc = benefitDesc;
		this.policyNumber = policyNumber;
		this.catCode = catCode;
		this.benTypeId = benTypeId;
		this.coPayAmt = coPayAmt;
		this.coPayPercent = coPayPercent;
		this.benLinked2Id = benLinked2Id;
		this.subLimitAmt = subLimitAmt;
		this.limitAmt = limitAmt;
		this.dateAdded = dateAdded;
		this.genderApplicability = genderApplicability;
		this.waitingPeriod = waitingPeriod;
		this.effectiveDate = effectiveDate;
		this.serviceType = serviceType;
		this.memAssignedBenefit = memAssignedBenefit;
		this.spendThreshold = spendThreshold;
		this.spendThrespct = spendThrespct;
		this.autoReplenishInd = autoReplenishInd;
		this.benEndDate = benEndDate;
		this.userId = userId;
		this.clnBenCode = clnBenCode;
		this.clnBenClauseCode = clnBenClauseCode;
		this.benTypDesc = benTypDesc;
		this.benLinked2Tqcode = benLinked2Tqcode;
		this.autoGrowthInd = autoGrowthInd;
		this.autoGrowthPct = autoGrowthPct;
		this.clnPolCode = clnPolCode;
		this.clnPolId = clnPolId;
		this.status = status;
		this.recId = recId;
		this.id = recId;
		this.pointUsage = pointUsage;
		
		this.copayInd = copayInd;
		this.autoReplenishLimtype = autoReplenishLimtype;
		this.autoReplenishLimit = autoReplenishLimit;
		this.autoGrowthRateInd = autoGrowthRateInd;
		this.autoGrowthRate = autoGrowthRate;
		this.autoGrowthCeiling = autoGrowthCeiling;
		this.cutOffInd = cutOffInd;
		this.cutOffAge = cutOffAge;
		this.insurerId = insurerId;
		this.layeredInd = layeredInd;
	    this.layered1Type = layered1Type;
	    this.layered1Value = layered1Value;
	    this.layered2Type = layered2Type;
	    this.layered2Value = layered2Value;
	    this.layered3Type = layered3Type;
	    this.layered3Value = layered3Value;
	}


	

	@Override
	public String toString() {
		return String.format("name=%s, benetit type id=%s", this.recId, this.benTypeId);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((recId == null) ? 0 : recId.hashCode());
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
		Benefit other = (Benefit) obj;
		if (recId == null) {
			if (other.recId != null)
				return false;
		} else if (!recId.equals(other.recId))
			return false;
		return true;
		
	}
	

	

}
