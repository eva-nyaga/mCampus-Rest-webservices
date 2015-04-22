package org.springframework.security.oauth.api.model.healthcare.providers;

import java.sql.Date;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class Provider {
	
	//implements Serializable
	//private static final long serialVersionUID = -7788619177798333712L;
	private String id;
	
	//@NotNull
	//@NotEmpty	
	private String clnProvCode;
	private String clnDescription;
	private String smartProvCode;
	private String town;
	private String building;
	private String street;
	private String postalNumber;
	private String telNumber;
	private String faxNumber;
	private String emailAddress;
	private String contactPerson;
	private String sateliteProv;
	private String smartDescription;
	private String countryCode;
	
	public Provider() {
		
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
    public String getClnProvCode() {
		return clnProvCode;
	}
	public void setClnProvCode(String clnProvCode) {
		this.clnProvCode = clnProvCode;
	}
    public String getClnDescription() {
		return clnDescription;
	}
	public void setClnDescription(String clnDescription) {
		this.clnDescription = clnDescription;
	}
	public String getSmartProvCode() {
		return smartProvCode;
	}
	public void setSmartProvCode(String smartProvCode) {
		this.smartProvCode = smartProvCode;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getBuilding() {
		return building;
	}
	public void setBuilding(String building) {
		this.building = building;
	}

	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getPostalNumber() {
		return postalNumber;
	}
	public void setPostalNumber(String postalNumber) {
		this.postalNumber = postalNumber;
	}
	public String getTelNumber() {
		return telNumber;
	}
	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}
	public String getFaxNumber() {
		return faxNumber;
	}
	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	public String getSateliteProv() {
		return sateliteProv;
	}
	public void setSateliteProv(String sateliteProv) {
		this.sateliteProv = sateliteProv;
	}
	public String getSmartDescription() {
		return smartDescription;
	}
	public void setSmartDescription(String smartDescription) {
		this.smartDescription = smartDescription;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public Provider(
			String id,
			String clnProvCode,
			String clnDescription,
			String smartProvCode,
			String town,
			String building,
			String street,
			String postalNumber,
			String telNumber,
			String faxNumber,
			String emailAddress,
			String contactPerson,
			String sateliteProv,
			String smartDescription,
			String countryCode
			){
	this.clnProvCode = clnProvCode;
	this.clnDescription = clnDescription;
	this.smartProvCode = smartProvCode;
	this.id  = id;
	this.town = town;
	this.building = building;
	this.street = street;
	this.postalNumber = postalNumber;
	this.telNumber = telNumber;
	this.faxNumber = faxNumber;
	this.emailAddress = emailAddress;
	this.contactPerson = contactPerson;
	this.sateliteProv = sateliteProv;
	this.smartDescription = smartDescription;
	this.countryCode = countryCode;

	}

	@Override
	public String toString() {
		return String.format("client provider code=%s, smart provider code=%s", this.clnProvCode, this.smartProvCode);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clnProvCode == null) ? 0 : clnProvCode.hashCode());
		return result;
	}

	@Override
	// Provider objects are equal if they have the same name.
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Provider other = (Provider) obj;
		if (clnProvCode == null) {
			if (other.clnProvCode != null)
				return false;
		} else if (!clnProvCode.equals(other.clnProvCode))
			return false;
		return true;
	}
	

	

}
