package org.springframework.security.oauth.api.model.healthcare.clients;

import java.sql.Date;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.bind.annotation.RequestParam;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class User {
	
	//implements Serializable
	private static final long serialVersionUID = -7788619177798333712L;
	
	private int id;
	//@NotNull
	//@NotEmpty	
	private String globalId;
	@NotEmpty
	public String memberNumber;
	private String insurerCode;
    private String email;
    private String phoneNo;
    private String password;
    private String pinNo;
    private String smsStatus;
    private Date dateCreated;
    private Date lastUpdate;

	public User() {
		
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGlobalId() {
		return globalId;
	}
	public void setGlobalID(String globalId) {
		this.globalId = globalId;
	}
	public String getMemberNumber() {
		return memberNumber;
	}
	public void setMemberNumber(String memberNumber) {
		this.memberNumber = memberNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPinNo() {
		return pinNo;
	}
	public void setPinNo(String pinNo) {
		this.pinNo = pinNo;
	}
	public String getSmsStatus() {
		return smsStatus;
	}
	public void setSmsStatus(String smsStatus) {
		this.smsStatus = smsStatus;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDate_created(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public Date getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public String getInsurerCode() {
		return insurerCode;
	}
	public void setInsurerCode(String insurerCode) {
		this.insurerCode = insurerCode;
	}

	public User(String globalId, String memberNumber, String insurerCode, String email, String phoneNo, String password, String pinNo, String smsStatus, Date dateCreated, Date lastUpdate){
		this.globalId = globalId;
		this.memberNumber = memberNumber;
		this.insurerCode = insurerCode;
		this.email = email;
	    this.phoneNo = phoneNo;
	    this.password = password;
	    this.pinNo = pinNo;
	    this.smsStatus = smsStatus;
	    this.dateCreated = dateCreated;
	    this.lastUpdate = lastUpdate;
	}

	@Override
	public String toString() {
		return String.format("Email =%s, global ID=%s", this.email, this.globalId);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((memberNumber == null) ? 0 : memberNumber.hashCode());
		return result;
	}

	@Override
	// Login objects are equal if they have the same name.
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (memberNumber == null) {
			if (other.memberNumber != null)
				return false;
		} else if (!memberNumber.equals(other.memberNumber))
			return false;
		return true;
	}
	

	

}
