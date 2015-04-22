package org.springframework.security.oauth.api.model.healthcare.clients;

import java.sql.Date;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class Transaction {
	
	//implements Serializable
	//private static final long serialVersionUID = -7788619177798333712L;
	private String      id;
	private Date        provDate;
	private Date        serverDate;
	private String      provId;
	private String      servPtId;
	private String      servPointId;
	private long        encounterAmt;
	private String      poolNr;
	private String      cardSerialNumber;
	private String      currencyId;
	private String      poolConversionRate;
	private String      polId;
	private String      localCurrencyId;
	private String      benefitId;
	private String      memberNumber;
	private long        balanceBefore;
	private long        balanceAfter;
	private String      claimsRefNumber;
	private String      provInvoiceNumber;
	private String      patientFileNumber;
	private String      preAuthNumber;
	private long        preAuthAmount;
	private String      transacTypeId;
	private String      counConversionRate;
	private String      dupOveride;
	private String      globalId;
	private String      diagCode;
	private String      dupInd;
	private String      country;
	private String      calcState;
	private String      overspendInd;
	private String      thresholdAttained;
	private Date        pointDate;
	private String      skspKey;
	private String        provdateOveride;
	private String      polstartOveride;
	private String      picked;
	private String      usecntInd;
	private String      trigSource;
	private String      timingSeq;
	private String      sourceTable;
	private String      pickedMs;
	private String      slinkCnt;
	private String      retCnt;
	private String      recalSeq;
	private String      recalClmCnt;
	private String      finClm;
	private String      pointClaimId;
	private String      processTimestamp;
	private String      callingProc;
	private String      modified;
	private Date        arriveDate;
	private String      otherNumber;
	private String      namesAsIs;
	private String      gender;
	private String      dob;
	private String      admitId;
	private String      status;
	private String      reason;
	private String      benefitDesc;
	private String      serviceType;
	private String      isReport;
	private Date        reportTime;
	private Date        logTime;
 
	public Transaction() {
		
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getProvDate() {
		return provDate;
	}
	public void setProvDate(Date provDate) {
		this.provDate = provDate;
	}
	public Date getServerDate() {
		return serverDate;
	}
	public void setServerDate(Date serverDate) {
		this.serverDate = serverDate;
	}
	public String getProvId() {
		return provId;
	}
	public void setProvId(String provId) {
		this.provId = provId;
	}
	public String getServPtId() {
		return servPtId;
	}
	public void setServPtId(String servPtId) {
		this.servPtId = servPtId;
	}
	public String getServPointId() {
		return servPointId;
	}
	public void setServPointId(String servPointId) {
		this.servPointId = servPointId;
	}
	public Long getEncounterAmt() {
		return encounterAmt;
	}
	public void setEncounterAmt(Long encounterAmt) {
		this.encounterAmt = encounterAmt;
	}
	public String getPoolNr() {
		return poolNr;
	}
	public void setPoolNr(String poolNr) {
		this.poolNr = poolNr;
	}
	public String getCardSerialNumber() {
		return cardSerialNumber;
	}
	public void setCardSerialNumber(String cardSerialNumber) {
		this.cardSerialNumber = cardSerialNumber;
	}
	public String getCurrencyId() {
		return currencyId;
	}
	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}
	public String getPoolConversionRate() {
		return poolConversionRate;
	}
	public void setPoolConversionRate(String poolConversionRate) {
		this.poolConversionRate = poolConversionRate;
	}
	public String getPolId() {
		return polId;
	}
	public void setPolId(String polId) {
		this.polId = polId;
	}
	public String getBenefitId() {
		return benefitId;
	}
	public void setBenefitId(String benefitId) {
		this.benefitId = benefitId;
	}
	public String getLocalCurrencyId() {
		return localCurrencyId;
	}
	public void setLocalCurrencyId(String localCurrencyId) {
		this.localCurrencyId = localCurrencyId;
	}
	public String getMemberNumber() {
		return memberNumber;
	}
	public void setMemberNumber(String memberNumber) {
		this.memberNumber = memberNumber;
	}
	public long getBalanceBefore() {
		return balanceBefore;
	}
	public void setBalanceBefore(long balanceBefore) {
		this.balanceBefore = balanceBefore;
	}
	public long getBalanceAfter() {
		return balanceAfter;
	}
	public void setBalanceAfter(long balanceAfter) {
		this.balanceAfter = balanceAfter;
	}
	public String getClaimsRefNumber() {
		return claimsRefNumber;
	}
	public void setClaimsRefNumber(String claimsRefNumber) {
		this.claimsRefNumber = claimsRefNumber;
	}
	public String getProvInvoiceNumber() {
		return provInvoiceNumber;
	}
	public void setProvInvoiceNumber(String provInvoiceNumber) {
		this.provInvoiceNumber = provInvoiceNumber;
	}
	public String getPatientFileNumber() {
		return patientFileNumber;
	}
	public void setPatientFileNumber(String patientFileNumber) {
		this.patientFileNumber = patientFileNumber;
	}
	public String getPreAuthNumber() {
		return preAuthNumber;
	}
	public void setPreAuthNumber(String preAuthNumber) {
		this.preAuthNumber = preAuthNumber;
	}
	public Long getPreAuthAmount() {
		return preAuthAmount;
	}
	public void setPreAuthAmount(Long preAuthAmount) {
		this.preAuthAmount = preAuthAmount;
	}
	public String getTransacTypeId() {
		return transacTypeId;
	}
	public void setTransacTypeId(String TransacTypeId) {
		this.transacTypeId = transacTypeId;
	}
	public String getCounConversionRate() {
		return counConversionRate;
	}
	public void setCounConversionRate(String counConversionRate) {
		this.counConversionRate = counConversionRate;
	}
	public String getDupOveride() {
		return dupOveride;
	}
	public void setDupOveride(String dupOveride) {
		this.dupOveride = dupOveride;
	}
	public String getGlobalId() {
		return globalId;
	}
	public void setGlobalId(String globalId) {
		this.globalId = globalId;
	}
	public String getDiagCode() {
		return diagCode;
	}
	public void setDiagCode(String diagCode) {
		this.diagCode = diagCode;
	}
	public String getDupInd() {
		return dupInd;
	}
	public void setDupInd(String dupInd) {
		this.dupInd = dupInd;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getcalcState() {
		return calcState;
	}
	public void setCalcState(String calcState) {
		this.calcState = calcState;
	}
	public String getOverspendInd() {
		return overspendInd;
	}
	public void setOverspendInd(String overspendInd) {
		this.overspendInd = overspendInd;
	}
	public String getThresholdAttained() {
		return thresholdAttained;
	}
	public void setThresholdAttained(String thresholdAttained) {
		this.thresholdAttained = thresholdAttained;
	}
	public Date getpointDate() {
		return pointDate;
	}
	public void setPointDate(String PointDate) {
		this.pointDate = pointDate;
	}
	public String getSkspKey() {
		return skspKey;
	}
	public void setSkspKey(String skspKey) {
		this.skspKey = skspKey;
	}
	public String getProvdateOveride() {
		return provdateOveride;
	}
	public void setProvdateOveride(String provdateOveride) {
		this.provdateOveride = provdateOveride;
	}
	public String getPolstartOveride() {
		return polstartOveride;
	}
	public void setPolstartOveride(String polstartOveride) {
		this.polstartOveride = polstartOveride;
	}
	public String getPicked() {
		return picked;
	}
	public void setPicked(String picked) {
		this.picked = picked;
	}
	public String getUsecntInd() {
		return usecntInd;
	}
	public void setUsecntInd(String usecntInd) {
		this.usecntInd = usecntInd;
	}
	public String getTrigSource() {
		return trigSource;
	}
	public void setTrigSource(String trigSource) {
		this.trigSource = trigSource;
	}
	public String getTimingSeq() {
		return timingSeq;
	}
	public void setTimingSeq(String timingSeq) {
		this.timingSeq = timingSeq;
	}
	public String getSourceTable() {
		return sourceTable;
	}
	public void setSourceTable(String sourceTable) {
		this.sourceTable = sourceTable;
	}
	public String getPickedMs() {
		return pickedMs;
	}
	public void setPickedMs(String pickedMs) {
		this.pickedMs = pickedMs;
	}
	public String getSlinkCnt() {
		return slinkCnt;
	}
	public void setSlinkCnt(String slinkCnt) {
		this.slinkCnt = slinkCnt;
	}
	public String getRetCnt() {
		return retCnt;
	}
	public void setRetCnt(String retCnt) {
		this.retCnt = retCnt;
	}
	public String getRecalSeq() {
		return recalSeq;
	}
	public void setRecalSeq(String recalSeq) {
		this.recalSeq = recalSeq;
	}
	public String getRecalClmCnt() {
		return recalClmCnt;
	}
	public void setRecalClmCnt(String recalClmCnt) {
		this.recalClmCnt = recalClmCnt;
	}
	public String getFinClm() {
		return finClm;
	}
	public void setFinClm(String finClm) {
		this.finClm = finClm;
	}
	public String getPointClaimId() {
		return pointClaimId;
	}
	public void setPointClaimId(String pointClaimId) {
		this.pointClaimId = pointClaimId;
	}
	public String getProcessTimestamp() {
		return processTimestamp;
	}
	public void setProcessTimestamp(String processTimestamp) {
		this.processTimestamp = processTimestamp;
	}
	public String getCallingProc() {
		return callingProc;
	}
	public void setCallingProc(String callingProc) {
		this.callingProc = callingProc;
	}
	public String getModified() {
		return modified;
	}
	public void setModified(String modified) {
		this.modified = modified;
	}
	public Date getArriveDate() {
		return arriveDate;
	}
	public void setArriveDate(Date arriveDate) {
		this.arriveDate = arriveDate;
	}
	public String getOtherNumber() {
		return otherNumber;
	}
	public void setOtherNumber(String otherNumber) {
		this.otherNumber = otherNumber;
	}
	public String getNamesAsIs() {
		return namesAsIs;
	}
	public void setNamesAsIs(String namesAsIs) {
		this.namesAsIs = namesAsIs;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getAdmitId() {
		return admitId;
	}
	public void setAdmitId(String admitId) {
		this.admitId = admitId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getBenefitDesc() {
		return benefitDesc;
	}
	public void setBenefitDesc(String benefitDesc) {
		this.benefitDesc = benefitDesc;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public String getIsReport() {
		return isReport;
	}
	public void setIsReport(String isReport) {
		this.isReport = isReport;
	}
	public Date getReportTime() {
		return reportTime;
	}
	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}
	public Date getLogTime() {
		return logTime;
	}
	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}
	

	public Transaction(
			Date        provDate,
			Date        serverDate,
			String      provId,
			String      servPtId,
			String      servPointId,
			long        encounterAmt,
			String      poolNr,
			String      cardSerialNumber,
			String      currencyId,
			String      poolConversionRate,
			String      polId,
			String      localCurrencyId,
			String      benefitId,
			String      memberNumber,
			long        balanceBefore,
			long        balanceAfter,
			String      claimsRefNumber,
			String      provInvoiceNumber,
			String      patientFileNumber,
			String      preAuthNumber,
			long        preAuthAmount,
			String      transacTypeId,
			String      counConversionRate,
			String      dupOveride,
			String      globalId,
			String      diagCode,
			String      dupInd,
			String      country,
			String      calcState,
			String      overspendInd,
			String      thresholdAttained,
			Date        pointDate,
			String      skspKey,
			String        provdateOveride,
			String      polstartOveride,
			String      picked,
			String      usecntInd,
			String      trigSource,
			String      timingSeq,
			String      sourceTable,
			String      pickedMs,
			String      slinkCnt,
			String      retCnt,
			String      recalSeq,
			String      recalClmCnt,
			String      finClm,
			String      pointClaimId,
			String      processTimestamp,
			String      callingProc,
			String      modified,
			Date        arriveDate,
			String      id,
			String      otherNumber,
			String      namesAsIs,
			String      gender,
			String      dob,
			String      admitId,
			String      status,
			String      reason,
			String      benefitDesc,
			String      serviceType,
			String      isReport,
			Date        reportTime,
			Date        logTime
			){
		this.id = id;
		this.provDate = provDate;
		this.serverDate = serverDate;
		this.provId = provId;
		this.servPtId = servPtId;
		this.servPointId = servPointId;
		this.encounterAmt = encounterAmt;
		this.poolNr = poolNr;
		this.cardSerialNumber = cardSerialNumber;
		this.currencyId = currencyId;
		this.poolConversionRate = poolConversionRate;
		this.polId = polId;
		this.localCurrencyId = localCurrencyId;
		this.benefitId = benefitId;
		this.memberNumber = memberNumber;
		this.balanceBefore = balanceBefore;
		this.balanceAfter = balanceAfter;
		this.claimsRefNumber = claimsRefNumber;
		this.provInvoiceNumber = provInvoiceNumber;
		this.patientFileNumber = patientFileNumber;
		this.preAuthNumber = preAuthNumber;
		this.preAuthAmount = preAuthAmount;
		this.transacTypeId = transacTypeId;
		this.counConversionRate = counConversionRate;
		this.dupOveride = dupOveride;
		this.globalId = globalId;
		this.diagCode = diagCode;
		this.dupInd = dupInd;
		this.country = country;
		this.calcState = calcState;
		this.overspendInd = overspendInd;
		this.thresholdAttained = thresholdAttained;
		this.pointDate = pointDate;
		this.skspKey = skspKey;
		this.provdateOveride = provdateOveride;
		this.polstartOveride = polstartOveride;
		this.picked = picked;
		this.usecntInd = usecntInd;
		this.trigSource = trigSource;
		this.timingSeq = timingSeq;
		this.sourceTable = sourceTable;
		this.pickedMs = pickedMs;
		this.slinkCnt = slinkCnt;
		this.retCnt = retCnt;
		this.recalSeq = recalSeq;
		this.recalClmCnt = recalClmCnt;
		this.finClm = finClm;
		this.pointClaimId = pointClaimId;
		this.processTimestamp = processTimestamp;
		this.callingProc = callingProc;
		this.modified = modified;
		this.arriveDate = arriveDate;
		this.otherNumber = otherNumber;
		this.namesAsIs = namesAsIs;
		this.gender = gender;
		this.dob = dob;
		this.admitId = admitId;
		this.status = status;
		this.reason = reason;
		this.benefitDesc = benefitDesc;
		this.serviceType = serviceType;
		this.isReport = isReport;
		this.reportTime = reportTime;
		this.logTime = logTime;

	}

	@Override
	public String toString() {
		return String.format("Claim Reference Number=%s, Member Number=%s", this.claimsRefNumber, this.memberNumber);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((claimsRefNumber == null) ? 0 : claimsRefNumber.hashCode());
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
		Transaction other = (Transaction) obj;
		if (claimsRefNumber == null) {
			if (other.claimsRefNumber != null)
				return false;
		} else if (!claimsRefNumber.equals(other.claimsRefNumber))
			return false;
		return true;
	}
	

	

}
