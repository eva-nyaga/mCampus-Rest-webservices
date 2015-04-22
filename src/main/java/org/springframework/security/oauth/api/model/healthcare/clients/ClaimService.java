package org.springframework.security.oauth.api.model.healthcare.clients;

import java.sql.Date;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class ClaimService {
	
    
	//implements Serializable
	//private static final long serialVersionUID = -7788619177798333712L;
	private String id;
	
	//@NotNull
	//@NotEmpty	
    private String claimId;
    private String serviceType;
    private String serviceCodeType;
    private String serviceCode;
    private String serviceDescription;
    private String quantity;
    private String amount;  
    private String diagnosisCodeType;
    private String diagnosisCode;
    private String diagnosisDescription;
    private Date receivedDate;
    private String admitId;
    private String pickedStatus;
    private Date pickedDate;
    private String providerKey;
    private String invoiceNr;
    private String insurerId;
    

    
	public ClaimService() {
		
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	
    public String getClaimId() {
		return claimId;
	}
	public void setClaimId(String claimId) {
		this.claimId = claimId;
	}
    public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public String getServiceCodeType() {
		return serviceCodeType;
	}
	public void setServiceCodeType(String serviceCodeType) {
		this.serviceCodeType = serviceCodeType;
	}
	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	public String getServiceDescription() {
		return serviceDescription;
	}
	public void setServiceDescription(String serviceDescription) {
		this.serviceDescription = serviceDescription;
	}
	public void setAdmitId(String admitId) {
		this.admitId = admitId;
	}
	public void setPickedStatus(String pickedStatus) {
		this.pickedStatus = pickedStatus;
	}
	public void setPickedDate(Date pickedDate) {
		this.pickedDate = pickedDate;
	}
	public void setProviderKey(String providerKey) {
		this.providerKey = providerKey;
	} 
	public void setInvoiceNr(String invoiceNr) {
		this.invoiceNr = invoiceNr;
	}
	public void setInsurerId(String insurerId) {
		this.insurerId = insurerId;
	}
	
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getDiagnosisCodeType() {
		return diagnosisCodeType;
	}
	public void setDiagnosisCodeType(String diagnosisCodeType) {
		this.diagnosisCodeType = diagnosisCodeType;
	}
	public String getDiagnosisCode() {
		return diagnosisCode;
	}
	public void setDiagnosisCode(String diagnosisCode) {
		this.diagnosisCode = diagnosisCode;
	}
	public String getDiagnosisDescription() {
		return diagnosisDescription;
	}
	public void setDiagnosisDescription(String diagnosisDescription) {
		this.diagnosisDescription = diagnosisDescription;
	}

	public Date getReceivedDate() {
		return receivedDate;
	}
	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}
	public String getAdmitId() {
		return admitId;
	}
	public String getPickedStatus() {
		return pickedStatus;
	}
	public Date getPickedDate() {
		return pickedDate;
	}
	public String getProviderKey() {
		return providerKey;
	}
	public String getInvoiceNr() {
		return invoiceNr;
	}
	public String getInsurerId() {
		return insurerId;
	}


	public ClaimService(
			String id,
			String claimId,
			String serviceType,
			String serviceCodeType,
			String serviceCode,
			String serviceDescription,
			String quantity,
			String amount,  
			String diagnosisCodeType,
			String diagnosisCode,
			String diagnosisDescription,
			String cardSerialNumber,
			Date insertDate,
			Date receivedDate,
			String admitId,
			String pickedStatus,
			Date pickedDate,
			String providerKey,
			String invoiceNr,
			String insurerId

			){
		this.id  = id;
		this.claimId  = claimId;
		this.serviceType  = serviceType;
		this.serviceCodeType  = serviceCodeType;
		this.serviceCode  = serviceCode;
		this.serviceDescription  = serviceDescription;
		this.quantity  = quantity;
		this.amount  = amount;  
		this.diagnosisCodeType  = diagnosisCodeType;
		this.diagnosisCode  = diagnosisCode;
		this.diagnosisDescription  = diagnosisDescription;

		this.receivedDate  = receivedDate;

	}

	@Override
	public String toString() {
		return String.format("Service Type=%s, Service Code Type=%s", this.serviceType, this.serviceCodeType);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		ClaimService other = (ClaimService) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

	

}
