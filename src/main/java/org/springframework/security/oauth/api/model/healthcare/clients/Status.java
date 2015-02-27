package org.springframework.security.oauth.api.model.healthcare.clients;

import java.sql.Date;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class Status {
	
	//implements Serializable
	//private static final long serialVersionUID = -7788619177798333712L;
	private int id;
	
	//@NotNull
	//@NotEmpty	
    private String groupCode;
    private String providerCode;
    private String memberNumber;
    private String memberNames;
    private Date invoiceDate;
    private long amount;  
    private Date dateReceived;
    private String invoiceNumber;

    
    
	public Status() {
		
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

    public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
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


	public Status(int provider_status_id, String policy_number, String provider_code, String membership_number, String member_name, Date transaction_date, Long amount, Date date_received, String smart_invoice_nr){
	this.id = provider_status_id;
	this.groupCode = policy_number;
	this.providerCode = provider_code;
	this.memberNumber = membership_number;
	this.memberNames = member_name;
	this.invoiceDate = transaction_date;
	this.amount = amount;
	this.dateReceived = date_received;
	this.invoiceNumber = smart_invoice_nr;

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
	// Status objects are equal if they have the same name.
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Status other = (Status) obj;
		if (memberNames == null) {
			if (other.memberNames != null)
				return false;
		} else if (!memberNames.equals(other.memberNames))
			return false;
		return true;
	}
	

	

}
