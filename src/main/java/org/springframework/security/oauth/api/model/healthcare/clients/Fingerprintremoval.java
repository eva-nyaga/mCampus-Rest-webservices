package org.springframework.security.oauth.api.model.healthcare.clients;

import java.sql.Date;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.bind.annotation.RequestParam;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class Fingerprintremoval {
	
	//implements Serializable
	//private static final long serialVersionUID = -7788619177798333712L;
	private int id;
	
	//@NotNull
	//@NotEmpty	
    private String memberNumber;
    private String statusCode;
    private String statusDesc;
    private String userId;
    private String statusReason;
    private Date statusDate;
    

	public Fingerprintremoval() {
		
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
    public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	public String getMemberNumber() {
		return memberNumber;
	}
	public void setMemberNumber(String memberNumber) {
		this.memberNumber = memberNumber;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public Date getStatusDate() {
		return statusDate;
	}
	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}
	public String getStatusReason() {
		return statusReason;
	}
	public void setStatusReason(String statusReason) {
		this.statusReason = statusReason;
	}
	


	public Fingerprintremoval(String memberNumber, String userId, String statusDesc, String statusCode, String statusReason, Date statusDate){
	this.memberNumber = memberNumber;
	this.userId = userId;
	this.statusDesc = statusDesc;
	this.statusCode = statusCode;
	this.statusReason = statusReason;
	this.statusDate = statusDate;
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
		Fingerprintremoval other = (Fingerprintremoval) obj;
		if (memberNumber == null) {
			if (other.memberNumber != null)
				return false;
		} else if (!memberNumber.equals(other.memberNumber))
			return false;
		return true;
	}
	

	

}
