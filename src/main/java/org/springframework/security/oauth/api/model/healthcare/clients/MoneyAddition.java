package org.springframework.security.oauth.api.model.healthcare.clients;

import java.sql.Date;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.bind.annotation.RequestParam;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class MoneyAddition {
	
	//implements Serializable
	//private static final long serialVersionUID = -7788619177798333712L;
	private String id;
	
	//@NotNull
	//@NotEmpty	
	private String smartBillId;
	private String returnedAmount;
	private String returnCode;
	private String returnReason;
	private Date dateEntered;
	private String memberNumber;
	private String anniv;
	private String providerCode;
	private String invoiceNumber;
	private String actionCode;
	private String actionName;
	private String benefitCode;
	private String userId;
	private String recId;
	private String clnPolCode;
	private Date invoiceDate;
	private String invoiceId;
    
	public MoneyAddition() {
		
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
    public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getSmartBillId() {
		return smartBillId;
	}
	public void setSmartBillId(String smartBillId) {
		this.smartBillId = smartBillId;
	}
	public String getMemberNumber() {
		return memberNumber;
	}
	public void setMemberNumber(String memberNumber) {
		this.memberNumber = memberNumber;
	}
	public String getAnniv() {
		return anniv;
	}
	public void setAnniv(String anniv) {
		this.anniv = anniv;
	}
	public String getReturnedAmount() {
		return returnedAmount;
	}
	public void setReturnedAmount(String returnedAmount) {
		this.returnedAmount = returnedAmount;
	}
	public String getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	public String getReturnReason() {
		return returnReason;
	}
	public void setReturnReason(String returnReason) {
		this.returnReason = returnReason;
	}
	public Date getDateEntered() {
		return dateEntered;
	}
	public void setDateEntered(Date dateEntered) {
		this.dateEntered = dateEntered;
	}
	public String getProviderCode() {
		return providerCode;
	}
	public void setProviderCode(String providerCode) {
		this.providerCode = providerCode;
	}
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public String getActionCode() {
		return actionCode;
	}
	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public String getBenefitCode() {
		return benefitCode;
	}
	public void setBenefitCode(String benefitCode) {
		this.benefitCode = benefitCode;
	}
	public String getRecId() {
		return recId;
	}
	public void setRecId(String recId) {
		this.recId = recId;
	}
	public String getClnPolCode() {
		return clnPolCode;
	}
	public void setClnPolCode(String clnPolCode) {
		this.clnPolCode = clnPolCode;
	}
	public Date getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public String getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}
	

	public MoneyAddition(
			String smartBillId,
			String returnedAmount,
			String returnCode,
			String returnReason,
			Date dateEntered,
			String memberNumber,
			String anniv,
			String providerCode,
			String invoiceNumber,
			String actionCode,
			String actionName,
			String benefitCode,
			String userId,
			String recId,
			String clnPolCode,
			Date invoiceDate,
			String invoiceId
			){
	this.smartBillId = smartBillId;
	this.returnedAmount  = returnedAmount;
	this.returnCode  = returnCode;
	this.returnReason  = returnReason;
	this.dateEntered  = dateEntered;
	this.memberNumber  = memberNumber;
	this.anniv  = anniv;
	this.providerCode  = providerCode;
	this.invoiceNumber  = invoiceNumber;
	this.actionCode  = actionCode;
	this.actionName  = actionName;
	this.benefitCode  = benefitCode;
	this.userId  = userId;
	this.recId  = recId;
	this.id  = recId;
	this.clnPolCode  = clnPolCode;
	this.invoiceDate  = invoiceDate;
	this.invoiceId   = invoiceId;
	}

	@Override
	public String toString() {
		return String.format("User ID=%s, Membership Number=%s", this.userId, this.memberNumber);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((memberNumber == null) ? 0 : memberNumber.hashCode());
		return result;
	}

	@Override
	// Claim objects are equal if they have the same name.
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MoneyAddition other = (MoneyAddition) obj;
		if (memberNumber == null) {
			if (other.memberNumber != null)
				return false;
		} else if (!memberNumber.equals(other.memberNumber))
			return false;
		return true;
	}
	

	

}
