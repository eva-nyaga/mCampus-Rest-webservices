package org.springframework.security.oauth.api.model.healthcare.clients;

import java.sql.Date;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class Claim {
	
	//implements Serializable
	//private static final long serialVersionUID = -7788619177798333712L;
	private String id;
	
	//@NotNull
	//@NotEmpty	
    private String schemeCode;
    private String providerCode;
    private String memberNumber;
    private String benefitId;
    private String memberNames;
    private Date invoiceDate;
    private long amount;  
    private Date dateReceived;
    private String invoiceNumber;
    private String status;
    private String clnBenClauseCode;
    private String cardSerialNumber;
    private Date insertDate;
 
	public Claim() {
		
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	
    public String getSchemeCode() {
		return schemeCode;
	}
	public void setSchemeCode(String schemeCode) {
		this.schemeCode = schemeCode;
	}
    public String getBenefitId() {
		return benefitId;
	}
	public void setBenefitId(String benefitId) {
		this.benefitId = benefitId;
	}
	public String getProviderCode() {
		return providerCode;
	}
	public void setProviderCode(String providerCode) {
		this.providerCode = providerCode;
	}
	public String getMemberNumber() {
		return memberNumber;
	}
	public void setMemberNumber(String memberNumber) {
		this.memberNumber = memberNumber;
	}
	public String getMemberNames() {
		return memberNames;
	}
	public void setMemberNames(String memberNames) {
		this.memberNames = memberNames;
	}

	public String getCardSerialNumber() {
		return cardSerialNumber;
	}
	public void setCardSerialNumber(String cardSerialNumber) {
		this.cardSerialNumber = cardSerialNumber;
	}
	public Date getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
	public Date getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	public Date getDateReceived() {
		return dateReceived;
	}
	public void setDateReceived(Date dateReceived) {
		this.dateReceived = dateReceived;
	}
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getClnBenClauseCode() {
		return clnBenClauseCode;
	}
	public void setClnBenClauseCode(String clnBenClauseCode) {
		this.clnBenClauseCode = clnBenClauseCode;
	}

	public Claim(String provider_claim_id, String policy_number, String provider_code, String membership_number, String member_name, Date transaction_date, Long amount, Date date_received, String smart_invoice_nr, String benefitId, String status, String clnBenClauseCode, String cardSerialNumber, Date insertDate){
	this.id = provider_claim_id;
	this.schemeCode = policy_number;
	this.providerCode = provider_code;
	this.memberNumber = membership_number;
	this.memberNames = member_name;
	this.invoiceDate = transaction_date;
	this.amount = amount;
	this.dateReceived = date_received;
	this.invoiceNumber = smart_invoice_nr;
	this.benefitId = benefitId;
	this.status = status;
	this.clnBenClauseCode = clnBenClauseCode;
	this.cardSerialNumber  = cardSerialNumber;
	this.insertDate  = insertDate;

	}

	@Override
	public String toString() {
		return String.format("name=%s, organisation=%s", this.memberNames, this.memberNumber);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((memberNames == null) ? 0 : memberNames.hashCode());
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
		Claim other = (Claim) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

	

}
