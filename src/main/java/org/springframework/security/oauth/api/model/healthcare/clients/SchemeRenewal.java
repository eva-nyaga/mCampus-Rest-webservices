package org.springframework.security.oauth.api.model.healthcare.clients;

import java.sql.Date;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class SchemeRenewal {
	
	//implements Serializable
	//private static final long serialVersionUID = -7788619177798333712L;
	private String id;
	
	//@NotNull
	//@NotEmpty	
	private String cutOffInd;
	private String cutOffAge;
	private String invoicePt;
	private String invoiceContact;
	private String invContactEmail;
	private String invPtcontactTel;
	private String deliveryPoint;
	private String delContact;
	private String delContactEmail;
	private String delPtcontactTel;
	private String policyCurrencyId;
	private String capitationInd;
	private String capitationAmount;
	private String capFreqofUse;
	private String capWithinDuration;
	private String capConseqInd;
	private String polTypeId;
	private String userId;
	private String claimGracePeriod;
	private String spendThrespct;
	private String policyStatus;
	private Date modificationDate;
	private Date actionedDate;
	private String clnComCode;
	private String autoReplenishInd;
	private String clnPolType;
	private String clnPolCode;
	private String clnPolId;
	private String policyNumber;
	private String polName;
	private Date startDate;
	private Date endDate;
	private String comId;
	private String smartCode;
	private String policyConvRate;
	private String anniv;
	private String insurerId;
    
    
	public SchemeRenewal() {
		
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getInvoicePt() {
		return invoicePt;
	}
	public void setInvoicePt(String invoicePt) {
		this.invoicePt = invoicePt;
	}
	public String getInvoiceContact() {
		return invoiceContact;
	}
	public void setInvoiceContact(String invoiceContact) {
		this.invoiceContact = invoiceContact;
	}

	public String getClnPolCode() {
		return clnPolCode;
	}
	public void setClnPolCode(String clnPolCode) {
		this.clnPolCode = clnPolCode;
	}
	
	public String getInvContactEmail() {
		return invContactEmail;
	}
	public void setInvContactEmail(String invContactEmail) {
		this.invContactEmail = invContactEmail;
	}

	
	
	
	
	
	
	
	public String getInvPtcontactTel() {
		return invPtcontactTel;
	}
	public void setInvPtcontactTel(String invPtcontactTel) {
		this.invPtcontactTel = invPtcontactTel;
	}
	public String getDeliveryPoint() {
		return deliveryPoint;
	}
	public void setDeliveryPoint(String deliveryPoint) {
		this.deliveryPoint = deliveryPoint;
	}
	public String getDelContact() {
		return delContact;
	}
	public void setDelContact(String delContact) {
		this.delContact = delContact;
	}
	public String getDelContactEmail() {
		return delContactEmail;
	}
	public void setDelContactEmail(String delContactEmail) {
		this.delContactEmail = delContactEmail;
	}
	public String getDelPtcontactTel() {
		return delPtcontactTel;
	}
	public void setDelPtcontactTel(String delPtcontactTel) {
		this.delPtcontactTel = delPtcontactTel;
	}
	public String getPolicyCurrencyId() {
		return policyCurrencyId;
	}
	public void setPolicyCurrencyId(String policyCurrencyId) {
		this.policyCurrencyId = policyCurrencyId;
	}
	public String getCapitationInd() {
		return capitationInd;
	}
	public void setCapitationInd(String capitationInd) {
		this.capitationInd = capitationInd;
	}
	public String getCapitationAmount() {
		return capitationAmount;
	}
	public void setCapitationAmount(String capitationAmount) {
		this.capitationAmount = capitationAmount;
	}
	public String getCapFreqofUse() {
		return capFreqofUse;
	}
	public void setCapFreqofUse(String capFreqofUse) {
		this.capFreqofUse = capFreqofUse;
	}
	public String getCapWithinDuration() {
		return capWithinDuration;
	}
	public void setCapWithinDuration(String capWithinDuration) {
		this.capWithinDuration = capWithinDuration;
	}
	public String getCapConseqInd() {
		return capConseqInd;
	}
	public void setCapConseqInd(String capConseqInd) {
		this.capConseqInd = capConseqInd;
	}
	public String getPolTypeId() {
		return polTypeId;
	}
	public void setPolTypeId(String polTypeId) {
		this.polTypeId = polTypeId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getClaimGracePeriod() {
		return claimGracePeriod;
	}
	public void setClaimGracePeriod(String claimGracePeriod) {
		this.claimGracePeriod = claimGracePeriod;
	}
	public String getSpendThrespct() {
		return spendThrespct;
	}
	public void setSpendThrespct(String spendThrespct) {
		this.spendThrespct = spendThrespct;
	}
	public String getPolicyStatus() {
		return policyStatus;
	}
	public void setPolicyStatus(String policyStatus) {
		this.policyStatus = policyStatus;
	}
	public Date getModificationDate() {
		return modificationDate;
	}
	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}
	public Date getActionedDate() {
		return actionedDate;
	}
	public void setSctionedDate(Date actionedDate) {
		this.actionedDate = actionedDate;
	}
	public String getClnComCode() {
		return clnComCode;
	}
	public void setClnComCode(String clnComCode) {
		this.clnComCode = clnComCode;
	}

	public String getAutoReplenishInd() {
		return autoReplenishInd;
	}
	public void setAutoReplenishInd(String autoReplenishInd) {
		this.autoReplenishInd = autoReplenishInd;
	}

	public String getClnPolType() {
		return clnPolType;
	}
	public void setClnPolType(String clnPolType) {
		this.clnPolType = clnPolType;
	}

	public String getClnPolId() {
		return clnPolId;
	}
	public void setClnPolId(String clnPolId) {
		this.clnPolId = clnPolId;
	}

	public String getPolicyNumber() {
		return policyNumber;
	}
	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	public String getPolName() {
		return polName;
	}
	public void setPolName(String polName) {
		this.polName = polName;
	}

	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getComId() {
		return comId;
	}
	public void setComId(String comId) {
		this.comId = comId;
	}

	public String getSmartCode() {
		return smartCode;
	}
	public void setSmartCode(String smartCode) {
		this.smartCode = smartCode;
	}
	public String getPolicyConvRate() {
		return policyConvRate;
	}
	public void setPolicyConvRate(String policyConvRate) {
		this.policyConvRate = policyConvRate;
	}
	public String getAnniv() {
		return anniv;
	}
	public void setAnniv(String anniv) {
		this.anniv = anniv;
	}
	public String getInsurerId() {
		return insurerId;
	}
	public void setInsurerId(String insurerId) {
		this.insurerId = insurerId;
	}
	
	
	

	
	
	


	public SchemeRenewal(
			String cutOffInd,
			String cutOffAge,
			String invoicePt,
			String invoiceContact,
			String invContactEmail,
			String invPtcontactTel,
			String deliveryPoint,
			String delContact,
			String delContactEmail,
			String delPtcontactTel,
			String policyCurrencyId,
			String capitationInd,
			String capitationAmount,
			String capFreqofUse,
			String capWithinDuration,
			String capConseqInd,
			String polTypeId,
			String userId,
			String claimGracePeriod,
			String spendThrespct,
			String policyStatus,
			Date modificationDate,
			Date actionedDate,
			String clnComCode,
			String autoReplenishInd,
			String clnPolType,
			String clnPolCode,
			String clnPolId,
			String policyNumber,
			String polName,
			Date startDate,
			Date endDate,
			String comId,
			String smartCode,
			String policyConvRate,
			String anniv,
			String insurerId
			){
		this.cutOffInd = cutOffInd;
		this.cutOffAge = cutOffAge;
		this.invoicePt = invoicePt;
		this.invoiceContact = invoiceContact;
		this.invContactEmail = invContactEmail;
		this.invPtcontactTel = invPtcontactTel;
		this.deliveryPoint = deliveryPoint;
		this.delContact = delContact;
		this.delContactEmail = delContactEmail;
		this.delPtcontactTel = delPtcontactTel;
		this.policyCurrencyId = policyCurrencyId;
		this.capitationInd = capitationInd;
		this.capitationAmount =capitationAmount;
		this.capFreqofUse = capFreqofUse;
		this.capWithinDuration = capWithinDuration;
		this.capConseqInd = capConseqInd;
		this.polTypeId = polTypeId;
		this.userId = userId;
		this.claimGracePeriod = claimGracePeriod;
		this.spendThrespct = spendThrespct;
		this.policyStatus = policyStatus;
		this.modificationDate = modificationDate;
		this.actionedDate = actionedDate;
		this.clnComCode = clnComCode;
		this.autoReplenishInd = autoReplenishInd;
		this.clnPolType = clnPolType;
		this.clnPolCode = clnPolCode;
		this.clnPolId = clnPolId;
		this.policyNumber = policyNumber;
		this.polName = polName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.comId = comId;
		this.smartCode = smartCode;
		this.policyConvRate = policyConvRate;
		this.anniv = anniv;
		this.insurerId = insurerId;
	}

	@Override
	public String toString() {
		return String.format("client code=%s,policy id=%s", this.clnComCode, this.clnPolId);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clnComCode == null) ? 0 : clnComCode.hashCode());
		return result;
	}

	@Override
	// Scheme objects are equal if they have the same name.
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SchemeRenewal other = (SchemeRenewal) obj;
		if (clnComCode == null) {
			if (other.clnComCode != null)
				return false;
		} else if (!clnComCode.equals(other.clnComCode))
			return false;
		return true;
	}
	

	

}
