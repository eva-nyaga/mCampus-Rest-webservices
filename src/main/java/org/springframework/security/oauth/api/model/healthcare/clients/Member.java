package org.springframework.security.oauth.api.model.healthcare.clients;

import java.sql.Blob;
import java.sql.Date;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class Member {
	
	//implements Serializable
	//private static final long serialVersionUID = -7788619177798333712L;
	private String id;
	
	//@NotNull
	//@NotEmpty	
    private String polId;
    private String comId;
    private String idNumber;
    private String surname;
    private String secondName;
    private String thirdName;
    private String otherNames;
    private Date dob;
    private String cardSerialNumber;
    private Date joinDate;
    private Date deactDate;
    private long memStatus;
    private Date modificationDate;
    private Date actionedDate;
    private long cutOffAge;
    private String kinFName;
    private String kinMName;
    private String kinONames;
    private String kinTelNo;
    private String kinEmail;
    private String kinNatId;
    private Blob photo;
    private String staffNumber;
    private String nhifNumber;
    private String gender;
    private String globalId;
    private String membershipNumber;
    private String memType;
    private String familyCode;
    private Date schemeStartDate;
    private String userID;
    private String clnPolNumber;
    private String clnComCode;
    private String clnPolCode;
    private String clnPolId;
    private String status;
    private Date schemeEndDate;
    private String clnUniqueMemNumber;
    private Date insertDate;
    private String clnCatCode;
    private String station;
    private String deptName;
    private String title;
    private Date printDate;
    private String otherNumber;
    
    private String printCard;
    private String cutOffExemption;
    private String region;
    private String phoneNo;
    private String email;
    private String insurerId;
    private String roamingEnabled;
    private String roamingCountries;

	public Member() {
		
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
    public String getPolId() {
		return polId;
	}
	public void setPolId(String polId) {
		this.polId = polId;
	}
	public String getComId() {
		return comId;
	}
	public void setComId(String comId) {
		this.comId = comId;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getSecondName() {
		return secondName;
	}
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	public String getThirdName() {
		return thirdName;
	}
	public void setThirdName(String thirdName) {
		this.thirdName = thirdName;
	}
	public String getOtherNames() {
		return otherNames;
	}
	public void setOtherNames(String otherNames) {
		this.otherNames = otherNames;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
    public String getCardSerialNumber() {
		return cardSerialNumber;
	}
	public void setCardSerialNumber(String cardSerialNumber) {
		this.cardSerialNumber = cardSerialNumber;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	public Date getDeactDate() {
		return deactDate;
	}
	public void setDeactDate(Date deactDate) {
		this.deactDate = deactDate;
	}
	public long getMemStatus() {
		return memStatus;
	}
	public void setMemStatus(long memStatus) {
		this.memStatus = memStatus;
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
	public void setActionedDate(Date actionedDate) {
		this.actionedDate = actionedDate;
	}
	public long getCutOffAge() {
		return cutOffAge;
	}
	public void setCutOffAge(long cutOffAge) {
		this.cutOffAge = cutOffAge;
	}
	public String getKinFName() {
		return kinFName;
	}
	public void setKinFName(String kinFName) {
		this.kinFName = kinFName;
	}
    public String getKinMName() {
		return kinMName;
	}
	public void setKinMName(String kinMName) {
		this.kinMName = kinMName;
	}
	public String getKinONames() {
		return kinONames;
	}
	public void setKinONames(String kinONames) {
		this.kinONames = kinONames;
	}
	public String getKinTelNo() {
		return kinTelNo;
	}
	public void setKinTelNo(String kinTelNo) {
		this.kinTelNo = kinTelNo;
	}
	public String getKinEmail() {
		return kinEmail;
	}
	public void setKinEmail(String kinEmail) {
		this.kinEmail = kinEmail;
	}

	public String getKinNatId() {
		return kinNatId;
	}
	public void setInvoiceDate(String kinNatId) {
		this.kinNatId = kinNatId;
	}
	public Blob getPhoto() {
		return photo;
	}
	public void setPhoto(Blob photo) {
		this.photo = photo;
	}
	public String getStaffNumber() {
		return staffNumber;
	}
	public void setStaffNumber(String staffNumber) {
		this.staffNumber = staffNumber;
	}
	public String getNhifNumber() {
		return nhifNumber;
	}
	public void setNhifNumber(String nhifNumber) {
		this.nhifNumber = nhifNumber;
	}
    public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getGlobalId() {
		return globalId;
	}
	public void setGlobalId(String globalId) {
		this.globalId = globalId;
	}
	public String getMembershipNumber() {
		return membershipNumber;
	}
	public void setMembershipNumber(String membershipNumber) {
		this.membershipNumber = membershipNumber;
	}
	public String getMemType() {
		return memType;
	}
	public void setMemType(String memType) {
		this.memType = memType;
	}
	public String getFamilyCode() {
		return familyCode;
	}
	public void setFamilyCode(String familyCode) {
		this.familyCode = familyCode;
	}
	public Date getSchemeStartDate() {
		return schemeStartDate;
	}
	public void setSchemeStartDate(Date schemeStartDate) {
		this.schemeStartDate = schemeStartDate;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getClnPolNumber() {
		return clnPolNumber;
	}
	public void setClnPolNumber(String clnPolNumber) {
		this.clnPolNumber = clnPolNumber;
	}
    public String getClnComCode() {
		return clnComCode;
	}
	public void setClnComCode(String clnComCode) {
		this.clnComCode = clnComCode;
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

	public Date getSchemeEndDate() {
		return schemeEndDate;
	}
	public void setSchemeEndDate(Date schemeEndDate) {
		this.schemeEndDate = schemeEndDate;
	}
	public String getClnUniqueMemNumber() {
		return clnUniqueMemNumber;
	}
	public void setClnUniqueMemNumber(String clnUniqueMemNumber) {
		this.clnUniqueMemNumber = clnUniqueMemNumber;
	}
	public Date getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
	
	
	
    public String getClnCatCode() {
		return clnCatCode;
	}
	public void setClnCatCode(String clnCatCode) {
		this.clnCatCode = clnCatCode;
	}
	public String getStation() {
		return station;
	}
	public void setStation(String station) {
		this.station = station;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public Date getPrintDate() {
		return printDate;
	}
	public void setPrintDate(Date printDate) {
		this.printDate = printDate;
	}
	public String getOtherNumber() {
		return otherNumber;
	}
	public void setOtherNumber(String otherNumber) {
		this.otherNumber = otherNumber;
	}
	
	
	public String getPrintCard() {
		return printCard;
	}
	public String getCutOffExemption() {
		return cutOffExemption;
	}
	public String getRegion() {
		return region;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public String getEmail() {
		return email;
	}
	public String getInsurerId() {
		return insurerId;
	}
	public String getRoamingEnabled() {
		return roamingEnabled;
	}
	public String getRoamingCountries() {
		return roamingCountries;
	}
	
	public void setPrintCard(String printCard) {
		this.printCard = printCard;
	}
	public void setCutOffExemption(String cutOffExemption) {
		this.cutOffExemption = cutOffExemption;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setInsurerId(String insurerId) {
		this.insurerId = insurerId;
	}
	public void setRoamingEnabled(String roamingEnabled) {
		this.roamingEnabled = roamingEnabled;
	}
	public void setRoamingCountries(String roamingCountries) {
		this.roamingCountries = roamingCountries;
	}
	
	public Member(
			 String polId,
			 String comId,
			 String idNumber,
			 String surname,
			 String secondName,
			 String thirdName,
			 String otherNames,
			 Date dob,
			 String cardSerialNumber,
			 Date joinDate,
			 Date deactDate,
			 long memStatus,
			 Date modificationDate,
			 Date actionedDate,
			 long cutOffAge,
			 String kinFName,
			 String kinMName,
			 String kinONames,
			 String kinTelNo,
			 String kinEmail,
			 String kinNatId,
			 Blob photo,
			 String staffNumber,
			 String nhifNumer,
			 String gender,
			 String globalId,
			 String membershipNumber,
			 String memType,
			 String familyCode,
			 Date schemeStartDate,
			 String userID,
			 String clnPolNumber,
			 String clnComCode,
			 String clnPolCode,
			 String clnPolId,
			 String status,
			 Date schemeEndDate,
			 String recId,
			 String clnUniqueMemNumber,
			 Date insertDate,
			 String clnCatCode,
			 String station,
			 String deptName,
			 String title,
			 Date printDate,
			 String otherNumber,
			 
			 String printCard,
			 String cutOffExemption,
			 String region,
			 String phoneNo,
			 String email,
			 String insurerId,
			 String roamingEnabled,
			 String roamingCountries
			){
		this.polId =  polId;
		this.comId = comId;
		this.idNumber = idNumber;
		this.surname = surname;
		this.secondName = secondName;
		this.thirdName = thirdName;
		this.otherNames = otherNames;
		this.dob = dob;
		this.cardSerialNumber = cardSerialNumber;
		this.joinDate = joinDate;
		this.deactDate = deactDate;
		this.memStatus = memStatus;
		this.modificationDate = modificationDate;
		this.actionedDate = actionedDate;
		this.cutOffAge = cutOffAge;
		this.kinFName = kinFName;
		this.kinMName = kinMName;
		this.kinONames = kinONames;
		this.kinTelNo = kinTelNo;
		this.kinEmail = kinEmail;
		this.kinNatId = kinNatId;
		this.photo = photo;
		this.staffNumber = staffNumber;
		this.nhifNumber = nhifNumber;
		this.gender = gender;
		this.globalId = globalId;
		this.membershipNumber = membershipNumber;
		this.memType = memType;
		this.familyCode = familyCode;
		this.schemeStartDate = schemeStartDate;
		this.userID = userID;
		this.clnPolNumber = clnPolNumber;
		this.clnComCode = clnComCode;
		this.clnPolCode = clnPolCode;
		this.clnPolId = clnPolId;
		this.status = status;
		this.schemeEndDate = schemeEndDate;
		this.id = recId;
		this.clnUniqueMemNumber = clnUniqueMemNumber;
		this.insertDate = insertDate;
		this.clnCatCode = clnCatCode;
		this.station = station;
		this.deptName = deptName;
		this.title = title;
		this.printDate = printDate;
		this.otherNumber = otherNumber;
		this.printCard = printCard;
		this.cutOffExemption = cutOffExemption;
		this.region = region;
		this.phoneNo = phoneNo;
		this.email = email;
		this.insurerId = insurerId;
		this.roamingEnabled = roamingEnabled;
		this.roamingCountries = roamingCountries;
	}


	@Override
	public String toString() {
		return String.format(" Surname=%s, Member Number=%s", this.surname, this.membershipNumber);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((membershipNumber == null) ? 0 : membershipNumber.hashCode());
		return result;
	}

	@Override
	// Member objects are equal if they have the same name.
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Member other = (Member) obj;
		if (membershipNumber == null) {
			if (other.membershipNumber != null)
				return false;
		} else if (!membershipNumber.equals(other.membershipNumber))
			return false;
		return true;
	}
	

	

}
