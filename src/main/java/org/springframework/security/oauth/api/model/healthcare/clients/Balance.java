package org.springframework.security.oauth.api.model.healthcare.clients;


import java.util.HashMap;

public class Balance implements java.io.Serializable {
	
	/**
	 * 
	 */
	 private static final long serialVersionUID = 5127876373365828239L;
	 private int id;
	 private String OUT_PATIENT_OVERALL;
	 private String OUT_PATIENT_DENTAL; 
	 private String OUT_PATIENT_OPTICAL_DENTAL;
	 private String OUT_PATIENT_FRAMES;
	 private String OUT_PATIENT_PRE_E_CHRONIC;
	 private String OUT_PATIENT_CONGENITAL;
	 private String OUT_PATIENT_MATERNITY;
	 private String IMMUNIZATION;
	 private String OUT_PATIENT_PAP_SMEAR;
	 private String OUT_PATIENT_PSYCHIATRIC_ALMTS;
	 private String OUT_PATIENT_HEALTH_CHECKUP;
	 private String WORK_PLACE_PROGRAM;
	 private String OUT_PATIENT_MEDICAL_APPLNCES;
	 private String STAFF_CLINIC_BENEFITS;
	 private String OUT_PATIENT_RESERVE;
	 private String ACUTE_MEDICATION;
	 private String CHRONIC_MEDICATION;
	 private String IN_PATIENT_OVERALL;
	 private String POST_HOSPITALIZATION;
	 private String IN_PATIENT_PRE_CHRONIC_CONDITIONS;
	 private String IN_PATIENT_HIV_RELATED_CONDITIONS;
	 private String IN_PATIENT_CONGENITAL_CHILDBITH_NEO;
	 private String IN_PATIENT_NON_ACCIDENTAL_DENTAL;
	 private String IN_PATIENT_NON_ACCIDENTAL_OPTICAL;
	 private String IN_PATIENT_ACCIDENTAL_DENTAL;
	 private String IN_PATIENT_ACCIDENTAL_OPTICAL;
	 private String IN_PATIENT_MATERNITY_COVER;
	 private String FIRST_EVER_EMERGENCY_CESERIAN;
	 private String ELECTIVE_CESERIAN_SECTION;
	 private String IN_PATIENT_INTERNAL_EXTERNAL_PROSTHESES;
	 private String IN_PATIENT_PHYSIOTHERAPY; 
	 private String IN_PATIENT_MAXILLOFACIAL_SURGERY;
	 private String IN_PATIENT_PHYCHIATRY_PHYCHOTHERAPY;
	 private String RADIOLOGY_PATHOLOGY;
	 private String IN_PATIENT_DAYCARE_SUGERY;
	 private String IN_PATIENT_ONCOLOGY_CANCER;
	 private String IN_PATIENT_ORGAN_TRANSPLANT;
	 private String IN_PATIENT_PRESCRIPTION_DRUGS_MATERIALS;
	 private String IN_PATIENT_ROAD_AIR_OVERSEAS_REFERRAL_TREATMENT;
	 private String IN_PATIENT_EXTERNAL_MEDICAL_APPLIANCES;
	 private String IN_PATIENT_GYNAECOLOGICAL_SURGERY;
	 private String KIDNEY_DIALYSIS;
	 private String IN_PATIENT_ARTHRITIS_ASTHMA_THYROID;
	 private String IN_PATIENT_CARDIOLOGY;
	 private String IN_PATIENT_HYPERTENSIONS_DIABETES_LIPEDEMIA_BLOOD_DISORDERS;
	 private String IN_PATIENT_PATIENT_FAMILY_PLANNING;
	 private String IN_PATIENT_HDU_AND_ICU;
	 private String REHABILITATION_DUE_TO_ACCIDENTAL_RELATED_CASES;
	 private String IN_PATIENT_RESERVE;
	 private String IN_PATIENT_NON_ACCIDENTAL_RELATED_MAXILLOFACIAL_SUGERY;
	 private String ADDITIONAL_BENFIT;
	 private String ADDITIONAL_BENFIT2;
	 private String ADDITIONAL_BENFIT3;
	 private String ADDITIONAL_BENFIT4;
	 private String CASH;
	 private String IN_PATIENT_GRAND_POOL;
	 private String OUT_PATIENT_GRAND_POOL;
	 private String OFFLINE_BILLING_BENEFIT;
	 private String NHIF;
	 private String NHIF2;
	 
   	private String pinNo;
	//@NotNull
	//@NotEmpty	
	private String names;
    private String memberNumber;
    private String phoneNumber;
    private String userStatus;
    private String status;
    private String message;

    



	public Balance() {
		
	}
	


   
   
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getOUT_PATIENT_OVERALL() {
		return OUT_PATIENT_OVERALL;
	}
	public void setOUT_PATIENT_OVERALL(String OUT_PATIENT_OVERALL) {
		this.OUT_PATIENT_OVERALL = OUT_PATIENT_OVERALL;
	}	
	public String getOUT_PATIENT_DENTAL() {
		return OUT_PATIENT_DENTAL;
	}
	public void setOUT_PATIENT_DENTAL(String OUT_PATIENT_DENTAL) {
		this.OUT_PATIENT_DENTAL = OUT_PATIENT_DENTAL;
	}	
	public String getOUT_PATIENT_OPTICAL_DENTAL() {
		return OUT_PATIENT_OPTICAL_DENTAL;
	}
	public void setOUT_PATIENT_OPTICAL_DENTAL(String OUT_PATIENT_OPTICAL_DENTAL) {
		this.OUT_PATIENT_OPTICAL_DENTAL = OUT_PATIENT_OPTICAL_DENTAL;
	}
	public String getOUT_PATIENT_FRAMES() {
		return OUT_PATIENT_FRAMES;
	}
	public void setOUT_PATIENT_FRAMES(String OUT_PATIENT_FRAMES) {
		this.OUT_PATIENT_FRAMES = OUT_PATIENT_FRAMES;
	}
	public String getOUT_PATIENT_PRE_E_CHRONIC() {
		return OUT_PATIENT_PRE_E_CHRONIC;
	}
	public void setOUT_PATIENT_PRE_E_CHRONIC(String OUT_PATIENT_PRE_E_CHRONIC) {
		this.OUT_PATIENT_PRE_E_CHRONIC = OUT_PATIENT_PRE_E_CHRONIC;
	}
	public String getOUT_PATIENT_MATERNITY() {
		return OUT_PATIENT_MATERNITY;
	}
	public String getOUT_PATIENT_CONGENITAL() {
		return OUT_PATIENT_CONGENITAL;
	}
	public void setOUT_PATIENT_CONGENITAL(String OUT_PATIENT_CONGENITAL) {
		this.OUT_PATIENT_CONGENITAL = OUT_PATIENT_CONGENITAL;
	}
	public void setOUT_PATIENT_MATERNITY(String OUT_PATIENT_MATERNITY) {
		this.OUT_PATIENT_MATERNITY = OUT_PATIENT_MATERNITY;
	}	
	public String getOUT_PATIENT_PAP_SMEAR() {
		return OUT_PATIENT_PAP_SMEAR;
	}
	public void setOUT_PATIENT_PAP_SMEAR(String OUT_PATIENT_PAP_SMEAR) {
		this.OUT_PATIENT_PAP_SMEAR = OUT_PATIENT_PAP_SMEAR;
	}	
	public String getOUT_PATIENT_PSYCHIATRIC_ALMTS() {
		return OUT_PATIENT_PSYCHIATRIC_ALMTS;
	}
	public void setOUT_PATIENT_PSYCHIATRIC_ALMTS(String OUT_PATIENT_PSYCHIATRIC_ALMTS) {
		this.OUT_PATIENT_PSYCHIATRIC_ALMTS = OUT_PATIENT_PSYCHIATRIC_ALMTS;
	}	
	
	public String getOUT_PATIENT_HEALTH_CHECKUP() {
		return OUT_PATIENT_HEALTH_CHECKUP;
	}
	public void setOUT_PATIENT_HEALTH_CHECKUP(String OUT_PATIENT_HEALTH_CHECKUP) {
		this.OUT_PATIENT_HEALTH_CHECKUP = OUT_PATIENT_HEALTH_CHECKUP;
	}	
	public String getWORK_PLACE_PROGRAM() {
		return WORK_PLACE_PROGRAM;
	}
	public void setWORK_PLACE_PROGRAM(String WORK_PLACE_PROGRAM) {
		this.WORK_PLACE_PROGRAM = WORK_PLACE_PROGRAM;
	}	
	public String getOUT_PATIENT_MEDICAL_APPLNCES() {
		return OUT_PATIENT_MEDICAL_APPLNCES;
	}
	public void setOUT_PATIENT_MEDICAL_APPLNCES(String OUT_PATIENT_MEDICAL_APPLNCES) {
		this.OUT_PATIENT_MEDICAL_APPLNCES = OUT_PATIENT_MEDICAL_APPLNCES;
	}	
	public String getSTAFF_CLINIC_BENEFITS() {
		return STAFF_CLINIC_BENEFITS;
	}
	public void setSTAFF_CLINIC_BENEFITS(String STAFF_CLINIC_BENEFITS) {
		this.STAFF_CLINIC_BENEFITS = STAFF_CLINIC_BENEFITS;
	}	
	public String getOUT_PATIENT_RESERVE() {
		return OUT_PATIENT_RESERVE;
	}
	public void setOUT_PATIENT_RESERVE(String OUT_PATIENT_RESERVE) {
		this.OUT_PATIENT_RESERVE = OUT_PATIENT_RESERVE;
	}
	public String getACUTE_MEDICATION() {
		return ACUTE_MEDICATION;
	}
	public void setACUTE_MEDICATION(String ACUTE_MEDICATION) {
		this.ACUTE_MEDICATION = ACUTE_MEDICATION;
	}
	public String getCHRONIC_MEDICATION() {
		return CHRONIC_MEDICATION;
	}
	public void setCHRONIC_MEDICATION(String CHRONIC_MEDICATION) {
		this.CHRONIC_MEDICATION = CHRONIC_MEDICATION;
	}
	public String getIN_PATIENT_OVERALL() {
		return IN_PATIENT_OVERALL;
	}
	public void setIN_PATIENT_OVERALL(String IN_PATIENT_OVERALL) {
		this.IN_PATIENT_OVERALL = IN_PATIENT_OVERALL;
	}	
	public String getPOST_HOSPITALIZATION() {
		return POST_HOSPITALIZATION;
	}
	public void setPOST_HOSPITALIZATION(String POST_HOSPITALIZATION) {
		this.POST_HOSPITALIZATION = POST_HOSPITALIZATION;
	}
	public String getIN_PATIENT_PRE_CHRONIC_CONDITIONS() {
		return IN_PATIENT_PRE_CHRONIC_CONDITIONS;
	}
	public void setIN_PATIENT_PRE_CHRONIC_CONDITIONS(String IN_PATIENT_PRE_CHRONIC_CONDITIONS) {
		this.IN_PATIENT_PRE_CHRONIC_CONDITIONS = IN_PATIENT_PRE_CHRONIC_CONDITIONS;
	}
	public String getIN_PATIENT_HIV_RELATED_CONDITIONS() {
		return IN_PATIENT_HIV_RELATED_CONDITIONS;
	}
	public void setIN_PATIENT_HIV_RELATED_CONDITIONS(String IN_PATIENT_HIV_RELATED_CONDITIONS) {
		this.IN_PATIENT_HIV_RELATED_CONDITIONS = IN_PATIENT_HIV_RELATED_CONDITIONS;
	}
	public String getIN_PATIENT_CONGENITAL_CHILDBITH_NEO() {
		return IN_PATIENT_CONGENITAL_CHILDBITH_NEO;
	}
	public void setIN_PATIENT_CONGENITAL_CHILDBITH_NEO(String IN_PATIENT_CONGENITAL_CHILDBITH_NEO) {
		this.IN_PATIENT_CONGENITAL_CHILDBITH_NEO = IN_PATIENT_CONGENITAL_CHILDBITH_NEO;
	}
	public String getIN_PATIENT_NON_ACCIDENTAL_DENTAL() {
		return IN_PATIENT_NON_ACCIDENTAL_DENTAL;
	}
	public void setIN_PATIENT_NON_ACCIDENTAL_DENTAL(String IN_PATIENT_NON_ACCIDENTAL_DENTAL) {
		this.IN_PATIENT_NON_ACCIDENTAL_DENTAL = IN_PATIENT_NON_ACCIDENTAL_DENTAL;
	}	
	public String getIN_PATIENT_NON_ACCIDENTAL_OPTICAL() {
		return IN_PATIENT_NON_ACCIDENTAL_OPTICAL;
	}
	public void setIN_PATIENT_NON_ACCIDENTAL_OPTICAL(String IN_PATIENT_NON_ACCIDENTAL_OPTICAL) {
		this.IN_PATIENT_NON_ACCIDENTAL_OPTICAL = IN_PATIENT_NON_ACCIDENTAL_OPTICAL;
	}
	public String getIN_PATIENT_ACCIDENTAL_DENTAL() {
		return IN_PATIENT_ACCIDENTAL_DENTAL;
	}
	public void setIN_PATIENT_ACCIDENTAL_DENTAL(String IN_PATIENT_ACCIDENTAL_DENTAL) {
		this.IN_PATIENT_ACCIDENTAL_DENTAL = IN_PATIENT_ACCIDENTAL_DENTAL;
	}	
	public String getIN_PATIENT_ACCIDENTAL_OPTICAL() {
		return IN_PATIENT_ACCIDENTAL_OPTICAL;
	}
	public void setIN_PATIENT_ACCIDENTAL_OPTICAL(String IN_PATIENT_ACCIDENTAL_OPTICAL) {
		this.IN_PATIENT_NON_ACCIDENTAL_OPTICAL = IN_PATIENT_NON_ACCIDENTAL_OPTICAL;
	}
	public String getIN_PATIENT_MATERNITY_COVER() {
		return IN_PATIENT_MATERNITY_COVER;
	}
	public void setIN_PATIENT_MATERNITY_COVER(String IN_PATIENT_MATERNITY_COVER) {
		this.IN_PATIENT_MATERNITY_COVER = IN_PATIENT_MATERNITY_COVER;
	}
	public String getFIRST_EVER_EMERGENCY_CESERIAN() {
		return FIRST_EVER_EMERGENCY_CESERIAN;
	}
	public void setFIRST_EVER_EMERGENCY_CESERIAN(String FIRST_EVER_EMERGENCY_CESERIAN) {
		this.FIRST_EVER_EMERGENCY_CESERIAN = FIRST_EVER_EMERGENCY_CESERIAN;
	}	
	public String getELECTIVE_CESERIAN_SECTION() {
		return ELECTIVE_CESERIAN_SECTION;
	}
	public void setELECTIVE_CESERIAN_SECTION(String ELECTIVE_CESERIAN_SECTION) {
		this.ELECTIVE_CESERIAN_SECTION = ELECTIVE_CESERIAN_SECTION;
	}	
	public String getIN_PATIENT_INTERNAL_EXTERNAL_PROSTHESES() {
		return IN_PATIENT_INTERNAL_EXTERNAL_PROSTHESES;
	}
	public void setIN_PATIENT_INTERNAL_EXTERNAL_PROSTHESES(String IN_PATIENT_INTERNAL_EXTERNAL_PROSTHESES) {
		this.IN_PATIENT_INTERNAL_EXTERNAL_PROSTHESES = IN_PATIENT_INTERNAL_EXTERNAL_PROSTHESES;
	}	
	public String getIN_PATIENT_PHYSIOTHERAPY() {
		return IN_PATIENT_PHYSIOTHERAPY;
	}
	public void setIN_PATIENT_PHYSIOTHERAPY(String IN_PATIENT_PHYSIOTHERAPY) {
		this.IN_PATIENT_PHYSIOTHERAPY = IN_PATIENT_PHYSIOTHERAPY;
	}
	public String getIN_PATIENT_MAXILLOFACIAL_SURGERY() {
		return IN_PATIENT_MAXILLOFACIAL_SURGERY;
	}
	public void setIN_PATIENT_MAXILLOFACIAL_SURGERY(String IN_PATIENT_MAXILLOFACIAL_SURGERY) {
		this.IN_PATIENT_MAXILLOFACIAL_SURGERY = IN_PATIENT_MAXILLOFACIAL_SURGERY;
	}		
	public String getIN_PATIENT_PHYCHIATRY_PHYCHOTHERAPY() {
		return IN_PATIENT_PHYCHIATRY_PHYCHOTHERAPY;
	}
	public void setIN_PATIENT_PHYCHIATRY_PHYCHOTHERAPY(String IN_PATIENT_PHYCHIATRY_PHYCHOTHERAPY) {
		this.IN_PATIENT_PHYCHIATRY_PHYCHOTHERAPY = IN_PATIENT_PHYCHIATRY_PHYCHOTHERAPY;
	}
	public String getRADIOLOGY_PATHOLOGY() {
		return RADIOLOGY_PATHOLOGY;
	}
	public void setRADIOLOGY_PATHOLOGY(String RADIOLOGY_PATHOLOGY) {
		this.RADIOLOGY_PATHOLOGY = RADIOLOGY_PATHOLOGY;
	}
	public String getIN_PATIENT_DAYCARE_SUGERY() {
		return IN_PATIENT_DAYCARE_SUGERY;
	}
	public void setIN_PATIENT_DAYCARE_SUGERY(String IN_PATIENT_DAYCARE_SUGERY) {
		this.IN_PATIENT_DAYCARE_SUGERY = IN_PATIENT_DAYCARE_SUGERY;
	}
	public String getIN_PATIENT_ONCOLOGY_CANCER() {
		return IN_PATIENT_ONCOLOGY_CANCER;
	}
	public void setIN_PATIENT_ONCOLOGY_CANCER(String IN_PATIENT_ONCOLOGY_CANCER) {
		this.IN_PATIENT_ONCOLOGY_CANCER = IN_PATIENT_ONCOLOGY_CANCER;
	}	
	public String getIN_PATIENT_ORGAN_TRANSPLANT() {
		return IN_PATIENT_ORGAN_TRANSPLANT;
	}
	public void setIN_PATIENT_ORGAN_TRANSPLANT(String IN_PATIENT_ORGAN_TRANSPLANT) {
		this.IN_PATIENT_ORGAN_TRANSPLANT = IN_PATIENT_ORGAN_TRANSPLANT;
	}	
	public String getIN_PATIENT_PRESCRIPTION_DRUGS_MATERIALS() {
		return IN_PATIENT_PRESCRIPTION_DRUGS_MATERIALS;
	}
	public void setIN_PATIENT_PRESCRIPTION_DRUGS_MATERIALS(String IN_PATIENT_PRESCRIPTION_DRUGS_MATERIALS) {
		this.IN_PATIENT_PRESCRIPTION_DRUGS_MATERIALS = IN_PATIENT_PRESCRIPTION_DRUGS_MATERIALS;
	}	
	public String getIN_PATIENT_ROAD_AIR_OVERSEAS_REFERRAL_TREATMENT() {
		return IN_PATIENT_ROAD_AIR_OVERSEAS_REFERRAL_TREATMENT;
	}
	public void setIN_PATIENT_ROAD_AIR_OVERSEAS_REFERRAL_TREATMENT(String IN_PATIENT_ROAD_AIR_OVERSEAS_REFERRAL_TREATMENT) {
		this.IN_PATIENT_ROAD_AIR_OVERSEAS_REFERRAL_TREATMENT = IN_PATIENT_ROAD_AIR_OVERSEAS_REFERRAL_TREATMENT;
	}	
	public String getIN_PATIENT_EXTERNAL_MEDICAL_APPLIANCES() {
		return IN_PATIENT_EXTERNAL_MEDICAL_APPLIANCES;
	}
	public void setIN_PATIENT_EXTERNAL_MEDICAL_APPLIANCES(String IN_PATIENT_EXTERNAL_MEDICAL_APPLIANCES) {
		this.IN_PATIENT_EXTERNAL_MEDICAL_APPLIANCES = IN_PATIENT_EXTERNAL_MEDICAL_APPLIANCES;
	}
	public String getIN_PATIENT_GYNAECOLOGICAL_SURGERY() {
		return IN_PATIENT_GYNAECOLOGICAL_SURGERY;
	}
	public void setIN_PATIENT_GYNAECOLOGICAL_SURGERY(String IN_PATIENT_GYNAECOLOGICAL_SURGERY) {
		this.IN_PATIENT_GYNAECOLOGICAL_SURGERY = IN_PATIENT_GYNAECOLOGICAL_SURGERY;
	}
	public String getKIDNEY_DIALYSIS() {
		return KIDNEY_DIALYSIS;
	}
	public void setKIDNEY_DIALYSIS(String KIDNEY_DIALYSIS) {
		this.KIDNEY_DIALYSIS = KIDNEY_DIALYSIS;
	}	
	public String getIN_PATIENT_ARTHRITIS_ASTHMA_THYROID() {
		return IN_PATIENT_ARTHRITIS_ASTHMA_THYROID;
	}
	public void setIN_PATIENT_ARTHRITIS_ASTHMA_THYROID(String IN_PATIENT_ARTHRITIS_ASTHMA_THYROID) {
		this.IN_PATIENT_ARTHRITIS_ASTHMA_THYROID = IN_PATIENT_ARTHRITIS_ASTHMA_THYROID;
	}	
	public String getIN_PATIENT_CARDIOLOGY() {
		return IN_PATIENT_CARDIOLOGY;
	}
	public void setIN_PATIENT_CARDIOLOGY(String IN_PATIENT_CARDIOLOGY) {
		this.IN_PATIENT_CARDIOLOGY = IN_PATIENT_CARDIOLOGY;
	}	
	public String getIN_PATIENT_HYPERTENSIONS_DIABETES_LIPEDEMIA_BLOOD_DISORDERS() {
		return IN_PATIENT_HYPERTENSIONS_DIABETES_LIPEDEMIA_BLOOD_DISORDERS;
	}
	public void setIN_PATIENT_HYPERTENSIONS_DIABETES_LIPEDEMIA_BLOOD_DISORDERS(String IN_PATIENT_HYPERTENSIONS_DIABETES_LIPEDEMIA_BLOOD_DISORDERS) {
		this.IN_PATIENT_HYPERTENSIONS_DIABETES_LIPEDEMIA_BLOOD_DISORDERS = IN_PATIENT_HYPERTENSIONS_DIABETES_LIPEDEMIA_BLOOD_DISORDERS;
	}	
	public String getIN_PATIENT_PATIENT_FAMILY_PLANNING() {
		return IN_PATIENT_PATIENT_FAMILY_PLANNING;
	}
	public void setIN_PATIENT_PATIENT_FAMILY_PLANNING(String IN_PATIENT_PATIENT_FAMILY_PLANNING) {
		this.IN_PATIENT_PATIENT_FAMILY_PLANNING = IN_PATIENT_PATIENT_FAMILY_PLANNING;
	}
	public String getIN_PATIENT_HDU_AND_ICU() {
		return IN_PATIENT_HDU_AND_ICU;
	}
	public void setIN_PATIENT_HDU_AND_ICU(String IN_PATIENT_HDU_AND_ICU) {
		this.IN_PATIENT_HDU_AND_ICU = IN_PATIENT_HDU_AND_ICU;
	}	
	public String getREHABILITATION_DUE_TO_ACCIDENTAL_RELATED_CASES() {
		return REHABILITATION_DUE_TO_ACCIDENTAL_RELATED_CASES;
	}
	public void setREHABILITATION_DUE_TO_ACCIDENTAL_RELATED_CASES(String REHABILITATION_DUE_TO_ACCIDENTAL_RELATED_CASES) {
		this.REHABILITATION_DUE_TO_ACCIDENTAL_RELATED_CASES = REHABILITATION_DUE_TO_ACCIDENTAL_RELATED_CASES;
	}	
	public String getIN_PATIENT_RESERVE() {
		return IN_PATIENT_RESERVE;
	}
	public void setIN_PATIENT_RESERVE(String IN_PATIENT_RESERVE) {
		this.IN_PATIENT_RESERVE = IN_PATIENT_RESERVE;
	}	
	public String getIN_PATIENT_NON_ACCIDENTAL_RELATED_MAXILLOFACIAL_SUGERY() {
		return IN_PATIENT_NON_ACCIDENTAL_RELATED_MAXILLOFACIAL_SUGERY;
	}
	public void setIN_PATIENT_NON_ACCIDENTAL_RELATED_MAXILLOFACIAL_SUGERY(String IN_PATIENT_NON_ACCIDENTAL_RELATED_MAXILLOFACIAL_SUGERY) {
		this.IN_PATIENT_NON_ACCIDENTAL_RELATED_MAXILLOFACIAL_SUGERY = IN_PATIENT_NON_ACCIDENTAL_RELATED_MAXILLOFACIAL_SUGERY;
	}	
	public String getADDITIONAL_BENFIT() {
		return ADDITIONAL_BENFIT;
	}
	public void setADDITIONAL_BENFIT(String ADDITIONAL_BENFIT) {
		this.ADDITIONAL_BENFIT = ADDITIONAL_BENFIT;
	}
	public String getADDITIONAL_BENFIT2() {
		return ADDITIONAL_BENFIT2;
	}
	public void setADDITIONAL_BENFIT2(String ADDITIONAL_BENFIT2) {
		this.ADDITIONAL_BENFIT2 = ADDITIONAL_BENFIT2;
	}
	public String getADDITIONAL_BENFIT3() {
		return ADDITIONAL_BENFIT3;
	}
	public void setADDITIONAL_BENFIT4(String ADDITIONAL_BENFIT4) {
		this.ADDITIONAL_BENFIT4 = ADDITIONAL_BENFIT4;
	}
	public String getCASH() {
		return CASH;
	}
	public void setCASH(String CASH) {
		this.CASH = CASH;
	}
	public String getIN_PATIENT_GRAND_POOL() {
		return IN_PATIENT_GRAND_POOL;
	}
	public void setIN_PATIENT_GRAND_POOL(String IN_PATIENT_GRAND_POOL) {
		this.IN_PATIENT_GRAND_POOL = IN_PATIENT_GRAND_POOL;
	}	
	public String getOUT_PATIENT_GRAND_POOL() {
		return OUT_PATIENT_GRAND_POOL;
	}
	public void setOUT_PATIENT_GRAND_POOL(String OUT_PATIENT_GRAND_POOL) {
		this.OUT_PATIENT_GRAND_POOL = OUT_PATIENT_GRAND_POOL;
	}
	public String getOFFLINE_BILLING_BENEFIT() {
		return OFFLINE_BILLING_BENEFIT;
	}
	public void setOFFLINE_BILLING_BENEFIT(String OFFLINE_BILLING_BENEFIT) {
		this.OFFLINE_BILLING_BENEFIT = OFFLINE_BILLING_BENEFIT;
	}	
	public String getNHIF() {
		return NHIF;
	}
	public void setNHIF(String NHIF) {
		this.NHIF = NHIF;
	}
	public String getNHIF2() {
		return NHIF2;
	}
	public void setNHIF2(String NHIF2) {
		this.NHIF2 = NHIF2;
	}
	public String getMemberNumber() {
		return memberNumber;
	}
	public void setMemberNumber(String memberNumber) {
		this.memberNumber = memberNumber;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getNames() {
		return names;
	}
	public void setNames(String names) {
		this.names = names;
	}
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getPinNo() {
		return pinNo;
	}
	public void setPinNo(String pinNo) {
		this.pinNo = pinNo;
	}
	
	

	public Balance(String string, String string2, String string3,
			String string4, String string5, String string6, String string7,
			String string8, String string9, String string10, String string11,
			String string12, String string13, String string14, String string15,
			String string16, String string17, String string18, String string19,
			String string20, String string21, String string22, String string23,
			String string24, String string25, String string26, String string27,
			String string28, String string29, String string30, String string31,
			String string32, String string33, String string34, String string35,
			String string36, String string37, String string38, String string39,
			String string40, String string41, String string42, String string43,
			String string44, String string45, String string46, String string47,
			String string48, String string49, String string50, String string51,
			String string52, String string53, String string54, String string55, String string56, String string57, String string58, String string59, String string60, String string61,
			String string62, String string63, String string64, String string65, String string66) {
		
		// TODO Auto-generated constructor stub
		this.OUT_PATIENT_OVERALL = string;
		this.OUT_PATIENT_DENTAL = string2; 
		this.OUT_PATIENT_OPTICAL_DENTAL = string3;
		this.OUT_PATIENT_FRAMES = string4;
		this.OUT_PATIENT_PRE_E_CHRONIC = string5;
		this.OUT_PATIENT_CONGENITAL = string6;
		this.OUT_PATIENT_MATERNITY = string7;
		this.IMMUNIZATION = string8;
		this.OUT_PATIENT_PAP_SMEAR = string9;
		this.OUT_PATIENT_PSYCHIATRIC_ALMTS = string10;
		this.OUT_PATIENT_HEALTH_CHECKUP = string11;
		this.WORK_PLACE_PROGRAM = string12;
		this.OUT_PATIENT_MEDICAL_APPLNCES = string13;
		this.STAFF_CLINIC_BENEFITS = string14;
		this.OUT_PATIENT_RESERVE = string15;
		this.ACUTE_MEDICATION = string16;
		this.CHRONIC_MEDICATION = string17;
		this.IN_PATIENT_OVERALL = string18;
		this.POST_HOSPITALIZATION = string19;
		this.IN_PATIENT_PRE_CHRONIC_CONDITIONS = string20;
		this.IN_PATIENT_HIV_RELATED_CONDITIONS = string21;
		this.IN_PATIENT_CONGENITAL_CHILDBITH_NEO = string22;
		this.IN_PATIENT_NON_ACCIDENTAL_DENTAL = string23;
		this.IN_PATIENT_NON_ACCIDENTAL_OPTICAL = string24;
		this.IN_PATIENT_ACCIDENTAL_DENTAL = string25;
		this.IN_PATIENT_ACCIDENTAL_OPTICAL = string26;
		this.IN_PATIENT_MATERNITY_COVER = string27;
		this.FIRST_EVER_EMERGENCY_CESERIAN = string28;
		this.ELECTIVE_CESERIAN_SECTION = string29;
		this.IN_PATIENT_INTERNAL_EXTERNAL_PROSTHESES = string30;
		this.IN_PATIENT_PHYSIOTHERAPY = string31; 
		this.IN_PATIENT_MAXILLOFACIAL_SURGERY = string32;
		this.IN_PATIENT_PHYCHIATRY_PHYCHOTHERAPY = string33;
		this.RADIOLOGY_PATHOLOGY = string34;
		this.IN_PATIENT_DAYCARE_SUGERY = string35;
		this.IN_PATIENT_ONCOLOGY_CANCER = string36;
		this.IN_PATIENT_ORGAN_TRANSPLANT = string37;
		this.IN_PATIENT_PRESCRIPTION_DRUGS_MATERIALS = string38;
		this.IN_PATIENT_ROAD_AIR_OVERSEAS_REFERRAL_TREATMENT = string39;
		this.IN_PATIENT_EXTERNAL_MEDICAL_APPLIANCES = string40;
		this.IN_PATIENT_GYNAECOLOGICAL_SURGERY = string41;
		this.KIDNEY_DIALYSIS = string42;
		this.IN_PATIENT_ARTHRITIS_ASTHMA_THYROID = string43;
		this.IN_PATIENT_CARDIOLOGY = string44;
		this.IN_PATIENT_HYPERTENSIONS_DIABETES_LIPEDEMIA_BLOOD_DISORDERS = string45;
		this.IN_PATIENT_PATIENT_FAMILY_PLANNING = string46;
		this.IN_PATIENT_HDU_AND_ICU = string47;
		this.REHABILITATION_DUE_TO_ACCIDENTAL_RELATED_CASES = string48;
		this.IN_PATIENT_RESERVE = string49;
		this.IN_PATIENT_NON_ACCIDENTAL_RELATED_MAXILLOFACIAL_SUGERY = string50;
		this.ADDITIONAL_BENFIT = string51;
		this.ADDITIONAL_BENFIT2 = string52;
		this.ADDITIONAL_BENFIT3 = string53;
		this.ADDITIONAL_BENFIT4 = string54;
		this.CASH = string55;
		this.IN_PATIENT_GRAND_POOL = string56;
		this.OUT_PATIENT_GRAND_POOL = string57;
		this.OFFLINE_BILLING_BENEFIT = string58;
		this.NHIF = string59;

    	this.memberNumber = string60;
    	this.names =  string61;
    	this.phoneNumber = string62;
	    this.userStatus =  string63;
	    this.message =  string64;
	    this.status =  string65;
	    this.pinNo = string66;
		
	}





	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		return result;
	}

	@Override
	// Balance objects are equal if they have the same name.
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Balance other = (Balance) obj;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		return true;
	}
	
	
	
	

	

}
