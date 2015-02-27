package org.springframework.security.oauth.api.model.healthcare.clients;

import java.sql.Date;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.bind.annotation.RequestParam;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class Categorychange {
	
	//implements Serializable
	//private static final long serialVersionUID = -7788619177798333712L;
	private int id;
	
	//@NotNull
	//@NotEmpty	
    private String memberNumber;
    private String userId;
    private Date startDate;
    private String schemeYear;
    private String newGrade;
    private Date endDate;
    private String clnPolCode;
    private String recId;
    
	
	public Categorychange() {
		
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
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public String getMemberNumber() {
		return memberNumber;
	}
	public void setMemberNumber(String memberNumber) {
		this.memberNumber = memberNumber;
	}
	public String getSchemeYear() {
		return schemeYear;
	}
	public void setSchemeYear(String schemeYear) {
		this.schemeYear = schemeYear;
	}
	public String getNewGrade() {
		return newGrade;
	}
	public void setNewGrade(String newGrade) {
		this.newGrade = newGrade;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getClnPolCode() {
		return clnPolCode;
	}
	public void setClnPolCode(String clnPolCode) {
		this.clnPolCode = clnPolCode;
	}
	public String getRecId() {
		return recId;
	}
	public void setRecId(String recId) {
		this.recId = recId;
	}
	


	public Categorychange(   
			String memberNumber,
		    String userId,
		    Date startDate,
		    String schemeYear,
		    String newGrade,
		    Date endDate,
		    String clnPolCode,
		    String recId){
	this.memberNumber = memberNumber;
	this.userId = userId;
	this.startDate = startDate;
	this.schemeYear = schemeYear;
	this.newGrade = newGrade;
	this.endDate = endDate;
	this.clnPolCode = clnPolCode;
	this.recId = recId;
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
		Categorychange other = (Categorychange) obj;
		if (memberNumber == null) {
			if (other.memberNumber != null)
				return false;
		} else if (!memberNumber.equals(other.memberNumber))
			return false;
		return true;
	}
	

	

}
