package org.springframework.security.oauth.api.model.healthcare.clients;

import java.sql.Date;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.bind.annotation.RequestParam;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class Renewal {
	
	//implements Serializable
	//private static final long serialVersionUID = -7788619177798333712L;
	private int id;
	
	//@NotNull
	//@NotEmpty	
    private String memberNumber;
    private Date startDate;
    private Date endDate;

    
	public Renewal() {
		
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMemberNumber() {
		return memberNumber;
	}
	public void setMemberNumber(String memberNumber) {
		this.memberNumber = memberNumber;
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



	public Renewal(    
			String memberNumber,
		    Date startDate,
		    Date endDate){
	this.memberNumber = memberNumber;
	this.startDate = startDate;
	this.endDate = endDate;
	}

	@Override
	public String toString() {
		return String.format("Start Date=%s, Membership Number=%s", this.startDate, this.memberNumber);
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
		Renewal other = (Renewal) obj;
		if (memberNumber == null) {
			if (other.memberNumber != null)
				return false;
		} else if (!memberNumber.equals(other.memberNumber))
			return false;
		return true;
	}
	

	

}
