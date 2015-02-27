package org.springframework.security.oauth.api.model.healthcare.clients;

import java.sql.Date;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class Scheme {
	
	//implements Serializable
	//private static final long serialVersionUID = -7788619177798333712L;
	private String id;
	
	//@NotNull
	//@NotEmpty	
	private String clnCode;
    private String companyName;
    private String polId; 
    private String alphaCount;
    private String clnPolCode;
    private String clnPolCodeClient;
    private String smartCode;

    
    private String clnPolType;
    private String clnPolId;
    private String policyNumber;
    private Date startDate;
    private Date endDate;
    private String comId;
    private String status;
    private String cutOffInd;
    private String cutOffAge;
    private String invoicePt;
    private String invoiceContact;
    private String invContactEmail;
    private String invPtcontactTel;
    private String deliveryPoint;
    private String delContact;
    private String delContactEmail;
    private String delPtcontactTel;
    private String policyCurrencyId;
    private String capitationInd;
    private String capitationAmount;
    private String capFreqofUse;
    private String capWithinDuration;
    private String capConseqInd;
    private String polTypeId;
    private Date dateAdded;
    private String userId;
    private String claimGracePeriod;
    private String spendThrespct;
    private String policyStatus;
    private Date modificationDate;
    private Date actionedDate;
    private String autoReplenishInd;

    private String anniv;
	private String countryCode;
	private String policyConvRate;
	private String transStatus;
	private String transStatusCode;
	private String insurerId;
    
    
	public Scheme() {
		
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	
    public String getClnCode() {
		return clnCode;
	}
	public void setClnCode(String clnCode) {
		this.clnCode = clnCode;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName =companyName;
	}
	public String getPolId() {
		return polId;
	}
	public void setPolId(String polId) {
		this.polId = polId;
	}
	public String getAlphaCount() {
		return alphaCount;
	}
	public void setAlphaCount(String alphaCount) {
		this.alphaCount = alphaCount;
	}

	public String getClnPolCode() {
		return clnPolCode;
	}
	public void setClnPolCode(String clnPolCode) {
		this.clnPolCode = clnPolCode;
	}
	
	public String getClnPolCodeClient() {
		return clnPolCodeClient;
	}
	public void setClnPolCodeClient(String clnPolCodeClient) {
		this.clnPolCodeClient = clnPolCodeClient;
	}
	
	public String getSmartCode() {
		return smartCode;
	}
	public void setSmartCode(String smartCode) {
		this.smartCode = smartCode;
	}


	    public String getClnPolType() {
		return clnPolType;
		 };
		 
		public String getClnPolId() {
		return clnPolId;
		 };

		public String getPolicyNumber() {
		return policyNumber; 
		};
		
		
		public Date getStartDate() {
		return startDate;
		 };
		 
		public Date getEndDate() {
		return endDate;
		 };
		 
		public String getComId() { 
		return comId;
		};
		
		public String getStatus() { 
		return status;
		};
		
		public String getCutOffInd() {
		return cutOffInd;
		 };
		 
		public String getCutOffAge() { 
		return cutOffAge;
		};
		
		public String getInvoicePt() { 
		return invoicePt;
		};
		
		public String getInvoiceContact() {
		return invoiceContact;
		 };
		 
		public String getInvContactEmail() {
		return invContactEmail;
		 };
		 
		public String getInvPtcontactTel() {
		return invPtcontactTel;
		 };
		 
		public String getDeliveryPoint() { 
		return deliveryPoint;
		};
		
		public String getDelContact() { 
		return delContact;
		};
		
		public String getDelContactEmail() {
		return delContactEmail;
		 };
		 
		public String getDelPtcontactTel() {
		return delPtcontactTel;
		 };
		 
		public String getPolicyCurrencyId() { 
		return policyCurrencyId;
		};
		
		public String getCapitationInd() {
		return capitationInd;
		 };
		 
		public String getCapitationAmount() {
		return capitationAmount;
		 };
		 
		public String getCapFreqofUse() { 
		return capFreqofUse;
		};
		
		public String getCapWithinDuration() {
		return capWithinDuration;
		 };
		 
		public String getCapConseqInd() { 
		return capConseqInd;
		};
		
		public String getPolTypeId() { 
		return polTypeId;
		};
		
		public Date getDateAdded() {
		return dateAdded;
		 };
		 
		public String getUserId() { 
		return userId;
		};
		
		public String getClaimGracePeriod() {
		return claimGracePeriod;
		 };
		 
		public String getSpendThrespct() {
		return spendThrespct;
		 };
		 
		public String getPolicyStatus() {
		return policyStatus;
		 };
		 
		public Date getModificationDate() {
		return modificationDate;
		 };
		 
		public Date getActionedDate() {
		return actionedDate;
		 };
		 
		public String getAutoReplenishInd() {
		return autoReplenishInd;
		 };
		 
		 
		    public void setClnPolType(String clnPolType) {
				this.clnPolType = clnPolType;
			 };
			 
			public void setClnPolId(String clnPolId) {
				this.clnPolId = clnPolId;
			 };
			 
			public void setPolicyNumber(String policyNumber) {
				this.policyNumber = policyNumber; 
			};
			
			public void setStartDate(Date startDate) {
				this.startDate = startDate;
			 };
			 
			public void setEndDate(Date endDate) {
				this.endDate = endDate;
			 };
			 
			public void setComId(String comId) { 
				this.comId = comId;
			};
			
			public void setStatus(String status) { 
				this.status = status;
			};
			
			public void setCutOffInd(String cutOffInd) {
				this.cutOffInd = cutOffInd;
			 };
			 
			public void setCutOffAge(String cutOffAge) { 
				this.cutOffAge = cutOffAge;
			};
			
			public void setInvoicePt(String invoicePt) { 
				this.invoicePt = invoicePt;
			};
			
			public void setInvoiceContact(String invoiceContact) {
				this.invoiceContact = invoiceContact;
			 };
			 
			public void setInvContactEmail(String invContactEmail) {
				this.invContactEmail = invContactEmail;
			 };
			 
			public void setInvPtcontactTel(String invPtcontactTel) {
				this.invPtcontactTel = invPtcontactTel;
			 };
			 
			public void setDeliveryPoint(String deliveryPoint) { 
				this.deliveryPoint = deliveryPoint;
			};
			
			public void setDelContact(String delContact) { 
				this.delContact = delContact;
			};
			
			public void setDelContactEmail(String delContactEmail) {
				this.delContactEmail = delContactEmail;
			 };
			 
			public void setDelPtcontactTel(String delPtcontactTel) {
				this.delPtcontactTel = delPtcontactTel;
			 };
			 
			public void setPolicyCurrencyId(String policyCurrencyId) { 
				this.policyCurrencyId = policyCurrencyId;
			};
			
			public void setCapitationInd(String capitationInd) {
				this.capitationInd = capitationInd;
			 };
			 
			public void setCapitationAmount(String capitationAmount) {
				this.capitationAmount = capitationAmount;
			 };
			 
			public void setCapFreqofUse(String capFreqofUse) { 
				this.capFreqofUse = capFreqofUse;
			};
			
			public void setCapWithinDuration(String capWithinDuration) {
				this.capWithinDuration = capWithinDuration;
			 };
			 
			public void setCapConseqInd(String capConseqInd) { 
				this.capConseqInd = capConseqInd;
			};
			
			public void setPolTypeId(String polTypeId) { 
				this.polTypeId = polTypeId;
			};
			
			public void setDateAdded(Date dateAdded) {
				this.dateAdded = dateAdded;
			 };
			 
			public void setUserId(String userId) { 
				this.userId = userId;
			};
			
			public void setClaimGracePeriod(String claimGracePeriod) {
				this.claimGracePeriod = claimGracePeriod;
			 };
			 
			public void setSpendThrespct(String spendThrespct) {
				this.spendThrespct = spendThrespct;
			 };
			 
			public void setPolicyStatus(String policyStatus) {
				this.policyStatus = policyStatus;
			 };
			 
			public void setModificationDate(Date modificationDate) {
				this.modificationDate = modificationDate;
			 };
			 
			public void setActionedDate(Date actionedDate) {
				this.actionedDate = actionedDate;
			 };
			 
			public void setAutoReplenishInd(String autoReplenishInd) {
				this.autoReplenishInd = autoReplenishInd;
			 };
			 
			  public String getAnniv(){
			  	   return anniv;
				}
			  public String getCountryCode(){
				   return countryCode;
				}
			  public String getPolicyConvRate(){
				   return policyConvRate;
				}
			  public String getTransStatus(){
				   return transStatus;
				}
			  public String getTransStatusCode(){
				   return transStatusCode;
				}
			  public String getInsurerId(){
				   return insurerId;
				}
			  	  
				public void setAnniv(String anniv) {
				    this.anniv = anniv;
				}
				public void setCountryCode(String countryCode) {
				    this.countryCode = countryCode;
				}
				public void setPolicyConvRate(String policyConvRate) {
				    this.policyConvRate = policyConvRate;
				}
				public void setTransStatus(String transStatus) {
				    this.transStatus = transStatus;
				}
				public void setTransStatusCode(String transStatusCode) {
				    this.transStatusCode = transStatusCode;
				}
				public void setInsurerId(String insurerId) {
				    this.insurerId = insurerId;
				}
		 

	public Scheme(
			String id, 
			String clnCode,
			String companyName,
			String polId, 
			String alphaCount,
			String clnPolCode,
			String clnPolCodeClient,
			String smartCode,
			String clnPolType,
			String clnPolId,
			String policyNumber,
			Date startDate,
			Date endDate,
			String comId,
			String status,
			String cutOffInd,
			String cutOffAge,
			String invoicePt,
			String invoiceContact,
			String invContactEmail,
			String invPtcontactTel,
			String deliveryPoint,
			String delContact,
			String delContactEmail,
			String delPtcontactTel,
			String policyCurrencyId,
			String capitationInd,
			String capitationAmount,
			String capFreqofUse,
			String capWithinDuration,
			String capConseqInd,
			String polTypeId,
			Date dateAdded,
			String userId,
			String claimGracePeriod,
			String spendThrespct,
			String policyStatus,
			Date modificationDate,
			Date actionedDate,
			String autoReplenishInd,
			String  anniv,
			String  countryCode,
			String  policyConvRate,
			String  transStatus,
			String  transStatusCode,
			String insurerId
			){
	this.id = polId;
	this.clnCode = clnCode;
	this.companyName = companyName;
	this.polId =  polId;
	this.alphaCount =  alphaCount;
	this.smartCode =  smartCode;
	this.clnPolCode =  clnPolCode;
	this.clnPolCodeClient = clnPolCodeClient;
	
	this.smartCode = smartCode;
	this.clnPolType = clnPolType;
	this.clnPolId = clnPolId;
	this.policyNumber = policyNumber;
	this.startDate = startDate;
	this.endDate = endDate;
	this.comId = comId;
	this.status = status;
	this.cutOffInd = cutOffInd;
	this.cutOffAge = cutOffAge;
	this.invoicePt = invoicePt;
	this.invoiceContact = invoiceContact;
	this.invContactEmail = invContactEmail;
	this.invPtcontactTel = invPtcontactTel;
	this.deliveryPoint = deliveryPoint;
	this.delContact = delContact;
	this.delContactEmail = delContactEmail;
	this.delPtcontactTel = delPtcontactTel;
	this.policyCurrencyId = policyCurrencyId;
	this.capitationInd = capitationInd;
	this.capitationAmount = capitationAmount;
	this.capFreqofUse = capFreqofUse;
	this.capWithinDuration = capWithinDuration;
	this.capConseqInd = capConseqInd;
	this.polTypeId = polTypeId;
	this.dateAdded = dateAdded;
	this.userId = userId;
	this.claimGracePeriod = claimGracePeriod;
	this.spendThrespct = spendThrespct;
	this.policyStatus = policyStatus;
	this.modificationDate = modificationDate;
	this.actionedDate = actionedDate;
	this.autoReplenishInd = autoReplenishInd;
	
	this.anniv = anniv;
	this.countryCode = countryCode;
	this.policyConvRate = policyConvRate;
	this.transStatus = transStatus;
	this.transStatusCode = transStatusCode;
	this.insurerId = insurerId;
	}

	@Override
	public String toString() {
		return String.format("client code=%s,company name=%s", this.clnCode, this.companyName);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clnCode == null) ? 0 : clnCode.hashCode());
		return result;
	}

	@Override
	// Scheme objects are equal if they have the same name.
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Scheme other = (Scheme) obj;
		if (clnCode == null) {
			if (other.clnCode != null)
				return false;
		} else if (!clnCode.equals(other.clnCode))
			return false;
		return true;
	}
	

	

}
