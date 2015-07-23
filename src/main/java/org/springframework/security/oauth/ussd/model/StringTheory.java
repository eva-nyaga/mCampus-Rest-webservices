package org.springframework.security.oauth.ussd.model;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth.api.model.healthcare.clients.Balance;
import org.springframework.security.oauth.api.model.healthcare.clients.ClaimService;
import org.springframework.security.oauth.api.model.healthcare.clients.ENIGMA;
import org.springframework.security.oauth.api.model.healthcare.clients.Member;
import org.springframework.security.oauth.api.model.healthcare.clients.Scheme;
import org.springframework.security.oauth.api.service.healthcare.clients.IMembersService;
import org.springframework.security.oauth.api.service.healthcare.clients.MembersService;
import org.springframework.security.oauth.api.service.healthcare.clients.SchemesService;

/**
 * A wrapper class for showing a message to the
 * 
 * @author mulama
 * 
 */
public class StringTheory {

	public StringTheory() {

	}

	private String message;
	MembersService membersService = new MembersService();
	SchemesService schemesService = new SchemesService();
	List<Member> membersmsussdverifications  = new ArrayList();

	public String StringTheory(HashMap<Integer, String> accesslevels,
			String PhoneNumber) {
		// TODO Auto-generated method stub
		String message = "";

		HashMap<Integer, String> select_options = new HashMap<Integer, String>();

		select_options.put(1, "OUTPATIENT OVERALL");
		select_options.put(2, "INPATIENT OVERALL");
		select_options.put(3, "OUTPATIENT DENTAL");
		select_options.put(4, "OUTPATIENT OPTICAL");
		select_options.put(5, "OUTPATIENT MATERNITY");
		select_options.put(6, "INPATIENT MATERNITY COVER");
		select_options.put(7, "OUTPATIENT FRAMES");
		select_options.put(8, "OUTPATIENT COUNSELING");
		select_options.put(9, "OUTPATIENT PAP SMEAR");
		select_options.put(10, "OUTPATIENT PSA TEST");
		select_options.put(11, "OUTPATIENT AMBULANCE SERVICES");
		select_options.put(12, "OUTPATIENT RESERVE");
		select_options.put(13, "OUTPATIENT CIRCUMCISION");
		select_options.put(14, "STAFF CLINIC BENEFIT");
		select_options.put(15, "POST HOSPITALIZATION");
		select_options.put(16, "IMMUNIZATION VACCINES");
		select_options.put(17, "INPATIENT CONGENITAL CHILDBITH NEO");
		select_options.put(18, "INPATIENT NON ACCIDENTAL DENTAL");
		select_options.put(19, "INPATIENT NON ACCIDENTAL OPTICAL");
		select_options.put(20, "INPATIENT OPTHALMOLOGY");
		select_options.put(21, "INPATIENT INTERNAL EXTERNAL PROSTHESES");
		select_options.put(22, "INPATIENT PHYSIOTHERAPY");
		select_options.put(23, "INPATIENT MRI CT SCANS");
		select_options.put(24, "INPATIENT ONCOLOGY CANCER");
		select_options.put(24, "INPATIENT ORGAN TRANSPLANT");
		select_options.put(26, "INPATIENT PRESCRIPTION DRUGS MATERIALS");
		select_options.put(27, "INPATIENT OVERSEAS REFERRAL TREATMENT");
		select_options.put(28, "INPATIENT MAXILLOFACIAL SURGERY");
		select_options.put(29, "INPATIENT GYNAECOLOGICAL SURGERY");
		select_options
				.put(30,
						"INPATIENT MAJOR ORGAN TRANSPLANT EXCLUDING COST OF ORGAN DONOR");
		select_options.put(31, "INPATIENT HEALTH CHECKUPS");
		select_options.put(32, "INPATIENT RESERVE");
		select_options.put(33, "FIRST EMERGENCY CEASEREAN");
		select_options.put(34, "PSYCHIATRIC TREATMENT REHABILIATION");
		select_options.put(35, "PREEXISTING CHRONIC CONDITIONS");
		select_options.put(36, "HIV RELATED CONDITIONS");
		select_options.put(37, "ANTE POST NATAL CARE");
		select_options.put(38, "WORK PLACE PROGRAM WELLNESS PROGRAM");
		select_options.put(39, "EXCESS OF LOSS");
		select_options.put(40, "ARTHRITIS ASTHMA THYROID DISEASE");
		select_options.put(41, "CARDIOLOGY");
		select_options.put(42, "DAY CARE SURGERY");
		select_options.put(43,
				"HYPERTENSIONS DIABETES LIPEDEMIA BLOOD DISORDERS");
		select_options.put(44, "PATIENT FAMILY PLANNING");
		select_options.put(45, "KIDNEY DIALYSIS");
		select_options.put(46, "HDU AND ICU");
		select_options.put(47, "ACUTE MEDICATION");
		select_options.put(48, "ELECTIVE CEASEREAN SECTION");
		select_options.put(49, "CASH");
		select_options.put(50, "OUT PATIENT GRAND POOL");
		select_options.put(51, "INPATIENT GRAND POOL");
		select_options.put(52, "OFFLINE BILLING");
		select_options.put(53, "RESERVED POOL NR");

		// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		if ((accesslevels.get(0) != null)
				&& (accesslevels.get(0).equals("1"))) {
			// //////////////////////////////////////////////////////
	               Boolean BInsurercode = false;
	               Boolean Bmembershipnumber = false;
	               Boolean Mphonenumberexist = false;
	        
	               Mphonenumberexist = membersService.checkPhoneSMSUSSDVerification(PhoneNumber);
	               
	               if(Mphonenumberexist == true){
	            	   message = "CON Dear Customer, your phone number:"+PhoneNumber+" has already been registered.\n0  -  Main Menu .\n99 -  Exit";
	               }else{

				    message = "CON To use this service, you must first agree to the Terms and Conditions in the manual provided by your scheme administrator.\n1. Agree\n2. Decline";
					  
					if ((accesslevels.get(1) != null) && (accesslevels.get(1).equals("1"))) {

					message = "CON Please enter your Insurer code.";

					if (StringUtils.isNotEmpty(accesslevels.get(2)) || StringUtils.isNotEmpty(accesslevels.get(3)) || StringUtils.isNotEmpty(accesslevels.get(4))) {
						
						int counter = 0;
						List<Scheme> s = null;
						Member x = null;
						
						if(StringUtils.isNotEmpty(accesslevels.get(2)) && containsSmartSchemeCode(schemesService.getSmartInsurerCode(accesslevels.get(2)), accesslevels.get(2))){
							counter = 2;
							s = schemesService.getSmartInsurerCode(accesslevels.get(counter));
						}else if(StringUtils.isNotEmpty(accesslevels.get(3)) && containsSmartSchemeCode(schemesService.getSmartInsurerCode(accesslevels.get(3)), accesslevels.get(3))){
							counter = 3;
							s = schemesService.getSmartInsurerCode(accesslevels.get(counter));
						}else if(StringUtils.isNotEmpty(accesslevels.get(4)) && containsSmartSchemeCode(schemesService.getSmartInsurerCode(accesslevels.get(4)), accesslevels.get(4))){
							counter = 4;
							s = schemesService.getSmartInsurerCode(accesslevels.get(counter));
						}
						
						
						if(counter != 0){

							String smartInsurercode = "";
							for(Scheme scheme: s) {
								if(scheme.getSmartCode().equals(accesslevels.get(counter))){
									smartInsurercode = scheme.getSmartCode();		
									break;
								}	
							}
							
							//System.out.println("MEMBER NUMBER "+accesslevels.get(membercounter));
							if (smartInsurercode.equals(accesslevels.get(counter))) {
								BInsurercode = true;

								message = "CON Please enter your membership number.";
								
								if (StringUtils.isNotEmpty(accesslevels.get(counter + 1)) || StringUtils.isNotEmpty(accesslevels.get(counter + 2)) || StringUtils.isNotEmpty(accesslevels.get(counter + 3))) {
									
									int memberexit = 0;
									int membercounter = 0;
									Member m = null;
									if(StringUtils.isNotEmpty(accesslevels.get(counter + 1)) && containsMemberNumber(membersService.getMemberSMSUSSDVerification(accesslevels.get(counter + 1), smartInsurercode), smartInsurercode, accesslevels.get(counter + 1))){
										membercounter = counter + 1;
									}else if(StringUtils.isNotEmpty(accesslevels.get(counter + 2)) && containsMemberNumber(membersService.getMemberSMSUSSDVerification(accesslevels.get(counter + 2), smartInsurercode), smartInsurercode, accesslevels.get(counter + 2))){
										membercounter = counter + 2;
									}else if(StringUtils.isNotEmpty(accesslevels.get(counter + 3)) && containsMemberNumber(membersService.getMemberSMSUSSDVerification(accesslevels.get(counter + 3), smartInsurercode), smartInsurercode, accesslevels.get(counter + 3))){
										membercounter = counter + 3;
									}else if(StringUtils.isNotEmpty(accesslevels.get(counter + 4))){
										//membercounter = counter + 3;
										memberexit = 4;
									}
									
									if(membercounter != 0){
	
										String smartcode = null;
										String membershipnumber = null;
										String cardserial = null;
										String membersmsussd = null;
										membersmsussdverifications = membersService.getMemberSMSUSSDVerification(accesslevels.get(membercounter), smartInsurercode);
										for(Member member: membersmsussdverifications) {
											membershipnumber = member.getMembershipNumber();
											smartcode = member.getSmartCode();	
											cardserial = member.getCardSerialNumber();
											membersmsussd  = member.getSmsStatus();
											break;
										}

										if(membershipnumber.equals(accesslevels.get(membercounter)) && (smartcode.equals(smartInsurercode))){

											if(StringUtils.equals(membersmsussd, "0") || StringUtils.isBlank(membersmsussd)){
	
												//System.out.println(updateTableSQLDependant);
												int tempPin = 1111;
												int smartpinno = membersService.RegMemberUSSDSMS(PhoneNumber,
														membershipnumber,
														cardserial, 
														tempPin);
								
													if (smartpinno == 0) {
															message = "END Request failed! Please contact our call centre +254733320660, +254718222200 for any assistance.";
														} else {
															message = "CON You have been registered successfully,\nPinNo: 1111\nMain Menu > Settings > Change pin, for a more secure SMART PIN. \n0 - Main Menu";
														}

											}else{
												
												message = "CON Dear Customer, Membership number:"+membershipnumber+" Insurer code:"+smartcode+" had already been registered .\n0  -  Main Menu .\n99 -  Exit";
												
											}
											

										} else {

											message = "CON Dear Customer, Membership number:"+membershipnumber+" Insurer code:"+smartcode+" Not Found.  Please enter your Membership number again!";

										}
									

									}else{
										
										message = "CON You entered an invalid Member Number , Please try again!";
										System.out.println(memberexit);
										if (memberexit==4) {
											message = "END 4You have already tried 3 times. Please contact your scheme administrator or our call centre +254733320660, +254718222200 for any assistance.";
										}
										
									}
									
									



								}
									
								

								

							} else {

								message = "CON You entered an invalid Insurer Code , Please try again!";
								if (StringUtils.isNotEmpty(accesslevels.get(3)) && (BInsurercode==false)) {
									message = "END 2You have already tried 3 times. Please contact your scheme administrator or our call centre +254733320660, +254718222200 for any assistance.";
								}

							}
							
						} else {

							message = "CON You entered an invalid Insurer Code , Please try again!";
							if (StringUtils.isNotEmpty(accesslevels.get(4))) {
								message = "END 1You have already tried 3 times. Please contact your scheme administrator or our call centre +254733320660, +254718222200 for any assistance.";
							}

						}

					}
					
				}else if ((accesslevels.get(1) != null) && (accesslevels.get(1).equals("2"))){
					
				message = "END Thank you for using the Smart Applications Mobile Service.";
				
				}
					
	         }

		   } 

			// //////////////////////////////////////////////////////

		// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


		else if ((accesslevels.get(0) != null) && (accesslevels.get(0).equals("2"))) {
			Boolean Mphonenumberexist = false;
            Mphonenumberexist = membersService.checkPhoneSMSUSSDVerification(PhoneNumber);
            
            if(Mphonenumberexist == true){
			// ////////////////////////////////////////////////
			message = "CON Please select benefit option: ";
			Iterator it = select_options.entrySet().iterator();
			int counter = 0;
			while (it.hasNext() && counter < 5) {
				Map.Entry pairs = (Map.Entry) it.next();
				message = message + "\n" + pairs.getKey() + ". "
						+ pairs.getValue().toString().substring(0,1).toUpperCase() + pairs.getValue().toString().substring(1).toLowerCase();
				counter++;
			}

			// Check balance equals("1")
			if ((accesslevels.get(1) != null)
					&& (!accesslevels.get(1).isEmpty())) {

				message = "CON Please enter your SMART PIN.";

				if ((accesslevels.get(2) != null)
						&& (accesslevels.get(2).equals("1"))) {

					if (("LEVEL3").equals("1")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					}
					if (("LEVEL3").equals("2")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					} else if (("LEVEL3").equals("3")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					} else {
					}

				} else if ((accesslevels.get(2) != null)
						&& (accesslevels.get(2).equals("2"))) {

					if (("LEVEL3").equals("1")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					}
					if (("LEVEL3").equals("2")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					} else if (("LEVEL3").equals("3")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					} else {
					}

				} else if ((accesslevels.get(2) != null)
						&& (accesslevels.get(2).equals("3"))) {

					if (("LEVEL3").equals("1")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					}
					if (("LEVEL3").equals("2")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					} else if (("LEVEL3").equals("3")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					} else {
					}

				} else {
					// HERE IS WHERE SAFARICOM IS FAILING
					BlackHole blackhole = new BlackHole();
					HashMap<Integer, String> select_map = blackhole
							.selectMapConvr(accesslevels.get(1));
					
					if (StringUtils.isNotEmpty(accesslevels.get(2))) {

						Balance x = membersService.getMemberBalancePhoneNumber(
								null, PhoneNumber, null);

						if (StringUtils.isNotEmpty(x.getStatus())
								&& (x.getStatus().equals("200"))) {

							if (StringUtils.isNotEmpty(x.getSmsStatus())) {

								if ((x.getSmsStatus().equals("1")) || (x.getSmsStatus().equals("2")) || (x.getSmsStatus().equals("3")) || (x.getSmsStatus().equals("4"))) {

									String regex = "(\\d)(?=(\\d{3})+$)";
									
									if (x.getPinNo()
											.equals(accesslevels.get(2))
											|| x.getPinNo().equals(
													accesslevels.get(3))
											|| x.getPinNo().equals(
													accesslevels.get(4))) {

										message = "CON Member No:"
												+ x.getMemberNumber()
												+ ", cover ends:"
												+ x.getEndDate()
												+ ". Balance as at "
												+ getCurrentSMSTimeStamp()
												+ " is";
										for (Integer key : select_map.keySet()) {

											if ((select_map.get(key)
													.equals("1"))) {
												message = message
														+ " "
														+ 
select_options.get(1).toString().substring(0,1).toUpperCase() + select_options.get(1).toString().substring(1).toLowerCase()
														+ " "
														+ x.getOUT_PATIENT_OVERALL().replaceAll(regex, "$1,")
														+ "Ksh; ";
											}
											if ((select_map.get(key)
													.equals("2"))) {
												message = message
														+ " "
														+ 
select_options.get(2).toString().substring(0,1).toUpperCase() + select_options.get(2).toString().substring(1).toLowerCase()

														+ " "
														+ x.getIN_PATIENT_OVERALL().replaceAll(regex, "$1,")
														+ " KSH; ";
											}
											if ((select_map.get(key)
													.equals("3"))) {
												message = message
														+ " "
														+ 
select_options.get(3).toString().substring(0,1).toUpperCase() + select_options.get(3).toString().substring(1).toLowerCase()
														+ " "
														+ x.getOUT_PATIENT_DENTAL().replaceAll(regex, "$1,")
														+ " KSH; ";
											}
											if ((select_map.get(key)
													.equals("4"))) {
												message = message
														+ " "
														+ 
select_options.get(4).toString().substring(0,1).toUpperCase() + select_options.get(4).toString().substring(1).toLowerCase()
														+ " "
														+ x.getOUT_PATIENT_OPTICAL_DENTAL().replaceAll(regex, "$1,")
														+ " KSH; ";
											}
											if ((select_map.get(key)
													.equals("5"))) {
												message = message
														+ " "
														+ 
select_options.get(5).toString().substring(0,1).toUpperCase() + select_options.get(5).toString().substring(1).toLowerCase()
														+ " "
														+ x.getOUT_PATIENT_MATERNITY().replaceAll(regex, "$1,")
														+ " KSH; ";
											}

										}

												message = message
												+ "\n0 - Main Menu \n99 - Exit";

									} else {

										message = "CON You entered an invalid SMART PIN, Please try again!";
										if (StringUtils.isNotEmpty(accesslevels
												.get(5))) {
											message = "END You have already tried 3 times. Please contact your scheme administrator or our call centre +254733320660, +254718222200 for any assistance.";
										}

									}

								} else if ((x.getSmsStatus().equals("0"))) {

									message = "CON Dear customer, welcome to Smart Mobile Service. 1 - Accept Terms & Conditions of service, 2 - Decline.";

								} else if (x.getSmsStatus().equals("-1")) {

									message = "CON Dear customer, your Smart Mobile Service has been blocked. Please contact your scheme administrator or our call centre +254733320660, +254718222200 for any assistance.";

								}

							} else {
								message = "END Dear customer, Your User status isn't stored in Smart. Please contact our call centre +254733320660, +254718222200 for any assistance.";
							}

						} else {
							message = "END Dear customer, Service is currently down. Please contact our call centre +254733320660, +254718222200 for any assistance.";
						}

						if (((accesslevels.get(3) != null) && (accesslevels
								.get(3).equals("99")))
								|| ((accesslevels.get(4) != null)
										&& (accesslevels.get(4).equals("99")) || ((accesslevels
										.get(5) != null) && (accesslevels
										.get(5).equals("99"))))) {
							message = "END Dear customer, Thank you for using the Smart Mobile Service.";
						}

					}

				}

			} else if ((accesslevels.get(1) != null)
					&& (accesslevels.get(1).equals("2"))) {

				if ((accesslevels.get(2) != null)
						&& (accesslevels.get(2).equals("1"))) {

					if (("LEVEL3").equals("1")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					}
					if (("LEVEL3").equals("2")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					} else if (("LEVEL3").equals("3")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					} else {
					}

				} else if ((accesslevels.get(2) != null)
						&& (accesslevels.get(2).equals("2"))) {

					if (("LEVEL3").equals("1")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					}
					if (("LEVEL3").equals("2")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					} else if (("LEVEL3").equals("3")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					} else {
					}

				} else if ((accesslevels.get(2) != null)
						&& (accesslevels.get(2).equals("3"))) {

					if (("LEVEL3").equals("1")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					}
					if (("LEVEL3").equals("2")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					} else if (("LEVEL3").equals("3")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					} else {
					}

				} else {
				}

			} else if ((accesslevels.get(1) != null)
					&& (accesslevels.get(1).equals("3"))) {

				if ((accesslevels.get(2) != null)
						&& (accesslevels.get(2).equals("1"))) {

					if (("LEVEL3").equals("1")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					}
					if (("LEVEL3").equals("2")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					} else if (("LEVEL3").equals("3")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					} else {
					}

				} else if ((accesslevels.get(2) != null)
						&& (accesslevels.get(2).equals("2"))) {

					if (("LEVEL3").equals("1")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					}
					if (("LEVEL3").equals("2")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					} else if (("LEVEL3").equals("3")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					} else {
					}

				} else if ((accesslevels.get(2) != null)
						&& (accesslevels.get(2).equals("3"))) {

					if (("LEVEL3").equals("1")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					}
					if (("LEVEL3").equals("2")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					} else if (("LEVEL3").equals("3")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					} else {
					}

				} else {
				}

			} else {
			}

			System.out.println("__________________________yyyyyy_" + message);
			System.out.println("QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ");
            }else{
            	message = "CON Dear Customer, your phone number:"+PhoneNumber+" is not registered. Main Menu > Register, to register your phone number. \n0 - Main Menu \n99 - Exit";
            	
            }
			
			// //////////////////////////////////////////////////////
		}

		// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		else if ((accesslevels.get(0) != null)
				&& (accesslevels.get(0).equals("3"))) {
			
            Boolean Mphonenumberexist = false;
            Mphonenumberexist = membersService.checkPhoneSMSUSSDVerification(PhoneNumber);
            
            if(Mphonenumberexist == true){
			// ////////////////////////////////////////////////
			message = "CON Please enter your SMART PIN.";

			if (StringUtils.isNotEmpty(accesslevels.get(1))) {

				Balance x = membersService.getMemberBalancePhoneNumber(null,
						PhoneNumber, null);

				if (x.getPinNo().equals(accesslevels.get(1))
						|| x.getPinNo().equals(accesslevels.get(2))
						|| x.getPinNo().equals(accesslevels.get(3))) {
		
					System.out.println("wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww "+x.getSmsStatus());
					String memberstatus = "";
					
					if ((x.getUserStatus()).equals("1")
							|| (x.getUserStatus()).equals("5")) {
						memberstatus = "Active";
					} else {
						memberstatus = "Inactive";
					}
              
					StringBuffer stringbf = new StringBuffer();
				      Matcher m = Pattern.compile("([a-z])([a-z]*)",
				      Pattern.CASE_INSENSITIVE).matcher(x.getNames());
				      while (m.find()) {
				         m.appendReplacement(stringbf, 
				         m.group(1).toUpperCase() + m.group(2).toLowerCase());
				      }
				      
					message = "CON Dear " + m.appendTail(stringbf).toString()
							+ ", member No:" + x.getMemberNumber()
							+ ", card serial:" + x.getCardSerial()
							+ ", member status:" + memberstatus
							+ ".\n0 - Main Menu \n99 - Exit";

				} else {

					message = "CON You entered an invalid SMART PIN, Please try again!";
					if (StringUtils.isNotEmpty(accesslevels.get(4))) {
						message = "END You have already tried 3 times. Please contact your scheme administrator or our call centre +254733320660, +254718222200 for any assistance.";
					}

				}

				if (("LEVEL2").equals("1")) {

					if (("LEVEL3").equals("1")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					}
					if (("LEVEL3").equals("2")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					} else if (("LEVEL3").equals("3")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					} else {
					}

				}

			} else if ((accesslevels.get(1) != null)
					&& (accesslevels.get(1).equals("2"))) {
				message = "END Thank you for using the Smart Mobile Service.";
			}

			// //////////////////////////////////////////////////////
            }else{
            	message = "CON Dear Customer, your phone number:"+PhoneNumber+" is not registered. Main Menu > Register, to register your phone number. \n0 - Main Menu \n99 - Exit";
            	
            }

		}

		// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		else if ((accesslevels.get(0) != null)
				&& (accesslevels.get(0).equals("4"))) {
            Boolean Mphonenumberexist = false;
            Mphonenumberexist = membersService.checkPhoneSMSUSSDVerification(PhoneNumber);
            if(Mphonenumberexist == true){
			// ////////////////////////////////////////////////
			message = "CON Please enter your SMART PIN.";

			if (StringUtils.isNotEmpty(accesslevels.get(1))) {

				Balance x = membersService.getMemberBalancePhoneNumber(null,
						PhoneNumber, null);

				if (x.getPinNo().equals(accesslevels.get(1))
						|| x.getPinNo().equals(accesslevels.get(2))
						|| x.getPinNo().equals(accesslevels.get(3))) {

					String smartreceipt = membersService
							.addUSSDHealthCareCenters(PhoneNumber,
									x.getMemberNumber(), x.getNames(),
									x.getCardSerial());
					message = "CON " +smartreceipt+ "\n0 - Main Menu \n99 - Exit";

				} else {

					message = "CON You have entered the wrong PIN. Please try again";
					if (StringUtils.isNotEmpty(accesslevels.get(4))) {
						message = "END You have already tried 3 times. Please contact your scheme administrator or our call centre +254733320660, +254718222200 for any assistance.";
					}

				}

				if ((accesslevels.get(2) != null)
						&& (accesslevels.get(2).equals("1"))) {

					if (("LEVEL3").equals("1")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					}
					if (("LEVEL3").equals("2")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					} else if (("LEVEL3").equals("3")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					} else {
					}

				} else if ((accesslevels.get(2) != null)
						&& (accesslevels.get(2).equals("2"))) {

					if (("LEVEL3").equals("1")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					}
					if (("LEVEL3").equals("2")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					} else if (("LEVEL3").equals("3")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					} else {
					}

				} else if ((accesslevels.get(2) != null)
						&& (accesslevels.get(2).equals("3"))) {

					if (("LEVEL3").equals("1")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					}
					if (("LEVEL3").equals("2")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					} else if (("LEVEL3").equals("3")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					} else {
					}

				} else {
				}

			} else if ((accesslevels.get(1) != null)
					&& (accesslevels.get(1).equals("2"))) {

				if ((accesslevels.get(2) != null)
						&& (accesslevels.get(2).equals("1"))) {

					if (("LEVEL3").equals("1")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					}
					if (("LEVEL3").equals("2")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					} else if (("LEVEL3").equals("3")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					} else {
					}

				} else if ((accesslevels.get(2) != null)
						&& (accesslevels.get(2).equals("2"))) {

					if (("LEVEL3").equals("1")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					}
					if (("LEVEL3").equals("2")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					} else if (("LEVEL3").equals("3")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					} else {
					}

				} else if ((accesslevels.get(2) != null)
						&& (accesslevels.get(2).equals("3"))) {

					if (("LEVEL3").equals("1")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					}
					if (("LEVEL3").equals("2")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					} else if (("LEVEL3").equals("3")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					} else {
					}

				} else {
				}

			} else if ((accesslevels.get(1) != null)
					&& (accesslevels.get(1).equals("3"))) {

				if ((accesslevels.get(2) != null)
						&& (accesslevels.get(2).equals("1"))) {

					if (("LEVEL3").equals("1")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					}
					if (("LEVEL3").equals("2")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					} else if (("LEVEL3").equals("3")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					} else {
					}

				} else if ((accesslevels.get(2) != null)
						&& (accesslevels.get(2).equals("2"))) {

					if (("LEVEL3").equals("1")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					}
					if (("LEVEL3").equals("2")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					} else if (("LEVEL3").equals("3")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					} else {
					}

				} else if ((accesslevels.get(2) != null)
						&& (accesslevels.get(2).equals("3"))) {

					if (("LEVEL3").equals("1")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					}
					if (("LEVEL3").equals("2")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					} else if (("LEVEL3").equals("3")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					} else {
					}

				} else {
				}

			} else {
			}
			// //////////////////////////////////////////////////////
            }else{
            	message = "CON Dear Customer, your phone number:"+PhoneNumber+" is not registered. Main Menu > Register, to register your phone number. \n0 - Main Menu \n99 - Exit";
            	
            }

		}

		// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		else if ((accesslevels.get(0) != null)
				&& (accesslevels.get(0).equals("5"))) {
				
            Boolean Mphonenumberexist = false;
            Mphonenumberexist = membersService.checkPhoneSMSUSSDVerification(PhoneNumber);
            if(Mphonenumberexist == true){
			// ////////////////////////////////////////////////
			message = "CON Please enter your SMART PIN.";

			if (StringUtils.isNotEmpty(accesslevels.get(1))) {

				Balance x = membersService.getSchemeBalancePhoneNumber(null,
						PhoneNumber, null);

				if (x.getPinNo().equals(accesslevels.get(1))
						|| x.getPinNo().equals(accesslevels.get(2))
						|| x.getPinNo().equals(accesslevels.get(3))) {

					String memberstatus = "";
					if ((x.getUserStatus()).equals("1")
							|| (x.getUserStatus()).equals("5")) {
						memberstatus = "Active";
					} else {
						memberstatus = "Inactive";
					}

					StringBuffer stringbf = new StringBuffer();
				      Matcher m = Pattern.compile("([a-z])([a-z]*)",
				      Pattern.CASE_INSENSITIVE).matcher(x.getNames());
				      while (m.find()) {
				         m.appendReplacement(stringbf, 
				         m.group(1).toUpperCase() + m.group(2).toLowerCase());
				      }
				      
					message = "CON Scheme name:" + m.appendTail(stringbf).toString()
							+ ", insurer code:" + x.getSmartCode()
							+ ", end date:" + x.getEndDate()
							+ ", status:" + memberstatus
							+ ".\n0 - Main Menu \n99 - Exit";

				} else {

					message = "CON You entered an invalid SMART PIN, Please try again!";
					if (StringUtils.isNotEmpty(accesslevels.get(4))) {
						message = "END You have already tried 3 times. Please contact your scheme administrator or our call centre +254733320660, +254718222200 for any assistance.";
					}

				}

			} else if ((accesslevels.get(1) != null)
					&& (accesslevels.get(1).equals("2"))) {

				message = "CON Thank you for using the Smart Mobile Service.";

				if ((accesslevels.get(2) != null)
						&& (accesslevels.get(2).equals("1"))) {

					if (("LEVEL3").equals("1")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					}
					if (("LEVEL3").equals("2")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					} else if (("LEVEL3").equals("3")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					} else {
					}

				} else if ((accesslevels.get(2) != null)
						&& (accesslevels.get(2).equals("2"))) {

					if (("LEVEL3").equals("1")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					}
					if (("LEVEL3").equals("2")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					} else if (("LEVEL3").equals("3")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					} else {
					}

				} else if ((accesslevels.get(2) != null)
						&& (accesslevels.get(2).equals("3"))) {

					if (("LEVEL3").equals("1")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					}
					if (("LEVEL3").equals("2")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					} else if (("LEVEL3").equals("3")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					} else {
					}

				} else {
				}

			} else if ((accesslevels.get(1) != null)
					&& (accesslevels.get(1).equals("3"))) {

				if ((accesslevels.get(2) != null)
						&& (accesslevels.get(2).equals("1"))) {

					if (("LEVEL3").equals("1")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					}
					if (("LEVEL3").equals("2")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					} else if (("LEVEL3").equals("3")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					} else {
					}

				} else if ((accesslevels.get(2) != null)
						&& (accesslevels.get(2).equals("2"))) {

					if (("LEVEL3").equals("1")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					}
					if (("LEVEL3").equals("2")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					} else if (("LEVEL3").equals("3")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					} else {
					}

				} else if ((accesslevels.get(2) != null)
						&& (accesslevels.get(2).equals("3"))) {

					if (("LEVEL3").equals("1")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					}
					if (("LEVEL3").equals("2")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					} else if (("LEVEL3").equals("3")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					} else {
					}

				} else {
				}

			} else {
			}
			// //////////////////////////////////////////////////////
            }else{
            	message = "CON Dear Customer, your phone number:"+PhoneNumber+" is not registered. Main Menu > Register, to register your phone number. \n0 - Main Menu \n99 - Exit";
            	
            }

		}

		// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		else if ((accesslevels.get(0) != null)
				&& (accesslevels.get(0).equals("6"))) {
			
            Boolean Mphonenumberexist = false;
            Mphonenumberexist = membersService.checkPhoneSMSUSSDVerification(PhoneNumber);
            if(Mphonenumberexist == true){

				message = "CON Please select an option.\n1. Change pin number\n2. SMS settings";
				
				if((accesslevels.get(1) != null)
						&& (accesslevels.get(1).equals("1"))) {
					
					Balance x = membersService.getMemberBalancePhoneNumber(null,
							PhoneNumber, null);

					message = "CON Please enter your Current Pin Number";

					if (StringUtils.isNotEmpty(accesslevels.get(2))) {

						if (x.getPinNo().equals(accesslevels.get(2))
								|| x.getPinNo().equals(accesslevels.get(3))
								|| x.getPinNo().equals(accesslevels.get(4))) {

							message = "CON Please enter your New Pin Number";

							int counter = 0;
							if (x.getPinNo().equals(accesslevels.get(4))) {
								counter = 5;
							} else if (x.getPinNo().equals(accesslevels.get(3))) {
								counter = 4;
							} else if (x.getPinNo().equals(accesslevels.get(2))) {
								counter = 3;
							}

							if ((accesslevels.get(counter) != null)
									&& (counter != 0)) {

								String smartpinno = membersService
										.ChangeSmartPinNo(PhoneNumber,
												x.getMemberNumber(),
												x.getNames(),
												x.getCardSerial(),
												accesslevels.get(counter));
								if (smartpinno == null) {
									message = "END Request failed! Please contact our call centre +254733320660, +254718222200 for any assistance.";
								} else {
									message = "CON Your Pin Number has been changed successfully.\n0 - Main Menu \n99 - Exit";
								}

							}

						} else {

							message = "CON You entered an invalid SMART PIN, Please try again!";

							if (StringUtils.isNotEmpty(accesslevels.get(5))) {
								message = "END You have already tried 3 times. Please contact your scheme administrator or our call centre +254733320660, +254718222200 for any assistance.";
							}

						}

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					}
					if (("LEVEL3").equals("2")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					} else if (("LEVEL3").equals("3")) {

						if (("LEVEL4").equals("1")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("2")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else if (("LEVEL4").equals("3")) {

							if (("LEVEL5").equals("1")) {

							} else if (("LEVEL5").equals("2")) {

							} else if (("LEVEL5").equals("3")) {

							} else {
							}

						} else {
						}

					} else {
					}

		 }else if((accesslevels.get(1) != null)
					&& (accesslevels.get(1).equals("2"))) {
			    String currentsettings = null;
			    String CSswitch = null;
			    CSswitch = membersService.checkPhoneSMSUSSDStatus(PhoneNumber);
						
				if(StringUtils.equals(CSswitch, "1")){
					currentsettings = "Notifications disabled";
				}else if(StringUtils.equals(CSswitch, "2")){
					currentsettings = "When I use my card";
				}else if(StringUtils.equals(CSswitch, "3")){
					currentsettings = "Family member uses card";
				}else if(StringUtils.equals(CSswitch, "4")){
					currentsettings = "Any family member uses card";
				}else{
					currentsettings = "Notifications disabled";
				}
				
				message = "CON "+currentsettings+". Change current settings?\n0. No\n1. Yes";				
				if((accesslevels.get(2) != null)
						&& (accesslevels.get(2).equals("1"))) {
					
					message = "CON Notification settings:\n1. Notifications disabled\n2. When I use my card\n3. Family member uses card\n4. Any family member uses card";
						
					if((accesslevels.get(3) != null)
							&& (accesslevels.get(3).equals("1"))) {

						int smartpinno = membersService.ChangeMemberUSSDSMS(PhoneNumber, accesslevels.get(3));
						if (smartpinno == 0) {
								message = "END Request failed! Please contact our call centre +254733320660, +254718222200 for any assistance.";
							} else {
								message = "CON Account notifications successfully disabled. \n0 - Main Menu \n99 - Exit";
							}

					}else if((accesslevels.get(3) != null)
							&& (accesslevels.get(3).equals("2"))) {
						
						int smartpinno = membersService.ChangeMemberUSSDSMS(PhoneNumber, accesslevels.get(3));
						if (smartpinno == 0) {
								message = "END Request failed! Please contact our call centre +254733320660, +254718222200 for any assistance.";
							} else {
								message = "CON Account successfully updated. You will be notified whenever your card is used.\n0 - Main Menu \n99 - Exit";
							}

					}else if((accesslevels.get(3) != null)
							&& (accesslevels.get(3).equals("3"))) {

						int smartpinno = membersService.ChangeMemberUSSDSMS(PhoneNumber, accesslevels.get(3));
						if (smartpinno == 0) {
								message = "END Request failed! Please contact our call centre +254733320660, +254718222200 for any assistance.";
							} else {
								message = "CON Account successfully updated. You will only be notified whenever a family member uses card.\n0 - Main Menu \n99 - Exit";
							}

					}else if((accesslevels.get(3) != null)
							&& (accesslevels.get(3).equals("4"))) {
						
						int smartpinno = membersService.ChangeMemberUSSDSMS(PhoneNumber, accesslevels.get(3));
						if (smartpinno == 0) {
								message = "END Request failed! Please contact our call centre +254733320660, +254718222200 for any assistance.";
							} else {
								message = "CON Account successfully updated. You will be notified whenever you or a family member uses card.\n0 - Main Menu \n99 - Exit";
							}

					}   
					
					
				}

		

			}

			// //////////////////////////////////////////////////////
            }else{
            	message = "CON Dear Customer, your phone number:"+PhoneNumber+" is not registered. Main Menu > Register, to register your phone number. \n0 - Main Menu \n99 - Exit";
            	
            }
			

		}

		// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		else if ((accesslevels.get(0) != null)
				&& (accesslevels.get(0).equals("7"))) {
			
			Map<String,String> memberverify = new HashMap<String,String>();
			String smartInsurercode = "";
            memberverify = membersService.checkPhoneAdminSMSUSSDVerification(PhoneNumber);
                     
         if(StringUtils.equals(memberverify.get("PHONENUMBEREXIST"), "true") && (memberverify.get("DASHUSER").contains("."))){
        	 smartInsurercode = memberverify.get("DASHUSER").substring(0, memberverify.get("DASHUSER").indexOf('.'));
        	 message = "CON Please select an option.\n1. Member administration\n2. Pre-authorization";
		     
		 if((accesslevels.get(1) != null)
						&& (accesslevels.get(1).equals("1"))) {
			 
					message = "CON Please select an option\n1. Check member status\n2. Check allocation\n3. Check balance\n4. Debit or Credit\n5. Activate or Deactivate \n6. Remove fingerprint";
					
					if((accesslevels.get(2) != null)
							&& (accesslevels.get(2).equals("1"))) {

						//////////////////////////////////////////////////////////////////////////////////////////////////////
						/////////////////////////////////////////////////////////////////////////////////////////////////////
						int counter = 0;
						message = "CON Please enter the member number ";

						
                          if(StringUtils.isNotEmpty(accesslevels.get(3)) || StringUtils.isNotEmpty(accesslevels.get(4)) || StringUtils.isNotEmpty(accesslevels.get(5))) {

							int memberexit = 0;
							int membercounter = 0;
							Member m = null;
							
							if(StringUtils.isNotEmpty(accesslevels.get(3)) && containsMemberNumber(membersService.getMemberSMSUSSDVerification(accesslevels.get(3), smartInsurercode), smartInsurercode, accesslevels.get(3))){
								membercounter = 3;
							}else if(StringUtils.isNotEmpty(accesslevels.get(4)) && containsMemberNumber(membersService.getMemberSMSUSSDVerification(accesslevels.get(4), smartInsurercode), smartInsurercode, accesslevels.get(4))){
								membercounter = 4;
							}else if(StringUtils.isNotEmpty(accesslevels.get(5)) && containsMemberNumber(membersService.getMemberSMSUSSDVerification(accesslevels.get(5), smartInsurercode), smartInsurercode, accesslevels.get(5))){
								membercounter = 5;
							}

							if(membercounter != 0){
								String smartcode = null;
								String membershipnumber = null;
								String cardserial = null;
								String membersmsussd = null;
								String globalid = null;
								membersmsussdverifications = membersService.getMemberSMSUSSDVerification(accesslevels.get(membercounter), smartInsurercode);
								for(Member member: membersmsussdverifications) {
									membershipnumber = member.getMembershipNumber();
									smartcode = member.getSmartCode();	
									cardserial = member.getCardSerialNumber();
									membersmsussd  = member.getSmsStatus();
									globalid = member.getGlobalId();
									break;
								}

								Balance x = membersService.getMemberBalanceGlobalID(null,
										globalid, null);
						
									System.out.println("wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww "+x.getSmsStatus());
									String memberstatus = "";
									
									if ((x.getUserStatus()).equals("1")
											|| (x.getUserStatus()).equals("5")) {
										memberstatus = "Active";
									} else {
										memberstatus = "Inactive";
									}
				              
									StringBuffer stringbf = new StringBuffer();
								      Matcher matcher = Pattern.compile("([a-z])([a-z]*)",
								      Pattern.CASE_INSENSITIVE).matcher(x.getNames());
								      while (matcher.find()) {
								         matcher.appendReplacement(stringbf, 
								         matcher.group(1).toUpperCase() + matcher.group(2).toLowerCase());
								      }
								      
									message = "CON member name:" + matcher.appendTail(stringbf).toString()
											+ ", member No:" + x.getMemberNumber()
											+ ", card serial:" + x.getCardSerial()
											+ ", member status:" + memberstatus
											+ ".\n0 - Main Menu \n99 - Exit";

							}else{
								message = "CON You entered an invalid Member Number , Please try again!";
								if (memberexit==6) {
									message = "END You have already tried 3 times. Please contact your scheme administrator or our call centre +254733320660, +254718222200 for any assistance.";
								}
								
							}

						}
						
						/////////////////////////////////////////////////////////////////////////////////////////////////////
						////////////////////////////////////////////////////////////////////////////////////////////////////
	
					}else if((accesslevels.get(2) != null)
							&& (accesslevels.get(2).equals("2"))) {
						
						//////////////////////////////////////////////////////////////////////////////////////////////////////
						/////////////////////////////////////////////////////////////////////////////////////////////////////
						int counter = 0;
						message = "CON Please enter the member number ";
                          if(StringUtils.isNotEmpty(accesslevels.get(3)) || StringUtils.isNotEmpty(accesslevels.get(4)) || StringUtils.isNotEmpty(accesslevels.get(5))) {

							int memberexit = 0;
							int membercounter = 0;
							Member m = null;
							String regex = "(\\d)(?=(\\d{3})+$)";
							if(StringUtils.isNotEmpty(accesslevels.get(3)) && containsMemberNumber(membersService.getMemberSMSUSSDVerification(accesslevels.get(3), smartInsurercode), smartInsurercode, accesslevels.get(3))){
								membercounter = 3;
							}else if(StringUtils.isNotEmpty(accesslevels.get(4)) && containsMemberNumber(membersService.getMemberSMSUSSDVerification(accesslevels.get(4), smartInsurercode), smartInsurercode, accesslevels.get(4))){
								membercounter = 4;
							}else if(StringUtils.isNotEmpty(accesslevels.get(5)) && containsMemberNumber(membersService.getMemberSMSUSSDVerification(accesslevels.get(5), smartInsurercode), smartInsurercode, accesslevels.get(5))){
								membercounter = 5;
							}

							if(membercounter != 0){
								String smartcode = null;
								String membershipnumber = null;
								String cardserial = null;
								String membersmsussd = null;
								String globalid = null;
								String reason = null;
								membersmsussdverifications = membersService.getMemberSMSUSSDVerification(accesslevels.get(membercounter), smartInsurercode);
								for(Member member: membersmsussdverifications) {
									membershipnumber = member.getMembershipNumber();
									smartcode = member.getSmartCode();	
									cardserial = member.getCardSerialNumber();
									membersmsussd  = member.getSmsStatus();
									globalid = member.getGlobalId();
									reason = member.getReason();
									break;
								}

								message = "CON ";
								for(Entry<String, String> pool : membersService.GET_MEMBER_ALLOCATION(reason).entrySet()){
									message +=pool.getKey().substring(0,1).toUpperCase()+pool.getKey().substring(1).toLowerCase()+": ";
									message +=pool.getValue().replaceAll(regex, "$1,")+" Ksh,"+"\n";
								}
							    if(message.endsWith(",")) {
							    	message= message.substring(0, message.length() - 1);
							     }
								message +="0 - Main Menu \n99 - Exit";
								
							}else{
								
								message = "CON You entered an invalid Member Number , Please try again!";
								System.out.println(memberexit);
								if (memberexit==6) {
									message = "END You have already tried 3 times. Please contact your scheme administrator or our call centre +254733320660, +254718222200 for any assistance.";
								}
								
							}
						}
						/////////////////////////////////////////////////////////////////////////////////////////////////////
						////////////////////////////////////////////////////////////////////////////////////////////////////
                          
					}else if((accesslevels.get(2) != null)
							&& (accesslevels.get(2).equals("3"))) {
						
						//////////////////////////////////////////////////////////////////////////////////////////////////////
						/////////////////////////////////////////////////////////////////////////////////////////////////////
						int counter = 0;
						String regex = "(\\d)(?=(\\d{3})+$)";
						message = "CON Please enter the member number ";
                          if(StringUtils.isNotEmpty(accesslevels.get(3)) || StringUtils.isNotEmpty(accesslevels.get(4)) || StringUtils.isNotEmpty(accesslevels.get(5))) {

							int memberexit = 0;
							int membercounter = 0;
							Member m = null;
							
							if(StringUtils.isNotEmpty(accesslevels.get(3)) && containsMemberNumber(membersService.getMemberSMSUSSDVerification(accesslevels.get(3), smartInsurercode), smartInsurercode, accesslevels.get(3))){
								membercounter = 3;
							}else if(StringUtils.isNotEmpty(accesslevels.get(4)) && containsMemberNumber(membersService.getMemberSMSUSSDVerification(accesslevels.get(4), smartInsurercode), smartInsurercode, accesslevels.get(4))){
								membercounter = 4;
							}else if(StringUtils.isNotEmpty(accesslevels.get(5)) && containsMemberNumber(membersService.getMemberSMSUSSDVerification(accesslevels.get(5), smartInsurercode), smartInsurercode, accesslevels.get(5))){
								membercounter = 5;
							}

							if(membercounter != 0){
								String smartcode = null;
								String membershipnumber = null;
								String cardserial = null;
								String membersmsussd = null;
								String globalid = null;
								membersmsussdverifications = membersService.getMemberSMSUSSDVerification(accesslevels.get(membercounter), smartInsurercode);
								for(Member member: membersmsussdverifications) {
									membershipnumber = member.getMembershipNumber();
									smartcode = member.getSmartCode();	
									cardserial = member.getCardSerialNumber();
									membersmsussd  = member.getSmsStatus();
									globalid = member.getGlobalId();
									break;
								}
								
								Balance x = membersService.getMemberBalanceGlobalID(
										null, globalid, null);						
								// ////////////////////////////////////////////////

						message = "CON Member No:"
								+ x.getMemberNumber()
								+ ". Balance as at "
								+ getCurrentSMSTimeStamp()
								+ " is "
								+ 
                   select_options.get(1).toString().substring(0,1).toUpperCase() + select_options.get(1).toString().substring(1).toLowerCase()
								+ ":"
                   				+ x.getOUT_PATIENT_OVERALL().replaceAll(regex, "$1,")
								+ "Ksh, "+
							
								select_options.get(2).toString().substring(0,1).toUpperCase() + select_options.get(2).toString().substring(1).toLowerCase()
                                + ":"
								+ x.getIN_PATIENT_OVERALL().replaceAll(regex, "$1,")
								+ " KSH, "
								+ 
								select_options.get(3).toString().substring(0,1).toUpperCase() + select_options.get(3).toString().substring(1).toLowerCase()
								+ ":"
								+ x.getOUT_PATIENT_DENTAL().replaceAll(regex, "$1,")
								+ " KSH, "
								+ 
								select_options.get(4).toString().substring(0,1).toUpperCase() + select_options.get(4).toString().substring(1).toLowerCase()
								+ ":"
								+ x.getOUT_PATIENT_OPTICAL_DENTAL().replaceAll(regex, "$1,")
								+ " KSH, "
								+ 
								select_options.get(5).toString().substring(0,1).toUpperCase() + select_options.get(5).toString().substring(1).toLowerCase()
								+ ":"
								+ x.getOUT_PATIENT_MATERNITY().replaceAll(regex, "$1,")
								+ " KSH. "
								+ "\n0 - Main Menu \n99 - Exit";

							}else{
								
								message = "CON You entered an invalid Member Number , Please try again!";
								System.out.println(memberexit);
								if (memberexit==6) {
									message = "END You have already tried 3 times. Please contact your scheme administrator or our call centre +254733320660, +254718222200 for any assistance.";
								}
								
							}
						}
						/////////////////////////////////////////////////////////////////////////////////////////////////////
						////////////////////////////////////////////////////////////////////////////////////////////////////
                          
					}else if((accesslevels.get(2) != null)
							&& (accesslevels.get(2).equals("4"))) {
						
						//////////////////////////////////////////////////////////////////////////////////////////////////////
						/////////////////////////////////////////////////////////////////////////////////////////////////////
						int counter = 0;
						message = "CON Please enter the member number ";
                          if(StringUtils.isNotEmpty(accesslevels.get(3)) || StringUtils.isNotEmpty(accesslevels.get(4)) || StringUtils.isNotEmpty(accesslevels.get(5))) {

							int memberexit = 0;
							int membercounter = 0;
							Member m = null;
							
							if(StringUtils.isNotEmpty(accesslevels.get(3)) && containsMemberNumber(membersService.getMemberSMSUSSDVerification(accesslevels.get(3), smartInsurercode), smartInsurercode, accesslevels.get(3))){
								membercounter = 3;
							}else if(StringUtils.isNotEmpty(accesslevels.get(4)) && containsMemberNumber(membersService.getMemberSMSUSSDVerification(accesslevels.get(4), smartInsurercode), smartInsurercode, accesslevels.get(4))){
								membercounter = 4;
							}else if(StringUtils.isNotEmpty(accesslevels.get(5)) && containsMemberNumber(membersService.getMemberSMSUSSDVerification(accesslevels.get(5), smartInsurercode), smartInsurercode, accesslevels.get(5))){
								membercounter = 5;
							}

							if(membercounter != 0){
								String smartcode = null;
								String membershipnumber = null;
								String cardserial = null;
								String membersmsussd = null;
								String globalid = null;
								String reason = null;
								String now_polid = null;
								String benid1 = null;
								String medcode = null;
								String medplan = null;
								String patname = null;
								List<String> rowValues = new ArrayList<String>();
								membersmsussdverifications = membersService.getMemberSMSUSSDVerification(accesslevels.get(membercounter), smartInsurercode);
								for(Member member: membersmsussdverifications) {
									membershipnumber = member.getMembershipNumber();
									smartcode = member.getSmartCode();	
									cardserial = member.getCardSerialNumber();
									membersmsussd  = member.getSmsStatus();
									globalid = member.getGlobalId();
									reason = member.getReason();
									now_polid = member.getPolId();
									medcode = smartcode;
									medplan = member.getReason().replace(smartcode,"");
									patname = member.getSecondName() + member.getThirdName() + member.getSurname();
									break;
								}

								message = "CON Please select an option.\n1. Debit member account\n2. Credit member account";
								if((accesslevels.get(4) != null)
										&& (accesslevels.get(4).equals("1"))) {
									
									message = "CON Please enter the amount.";
									
									if((accesslevels.get(5) != null)) {
										
										message = "CON Please enter the transaction date.";
										
										if((accesslevels.get(6) != null)) {
											
											message = "CON Please enter the invoice number.";
											
											if((accesslevels.get(7) != null)) {
										
												String stgmessage = "CON Please select the benefit.\n";
												for(Entry<String, String> pool : membersService.GET_MEMBER_BENEFIT_POOL_NUMBERS(reason).entrySet()){
													rowValues.add(pool.getKey());
												    stgmessage +=pool.getKey()+": ";
												    stgmessage +=pool.getValue().substring(0,1).toUpperCase() + pool.getValue().substring(1).toLowerCase()+"\n";
												}
												message = stgmessage;
												if(StringUtils.isNotEmpty(accesslevels.get(8)) && containsPoolNumber(accesslevels.get(8), rowValues) || StringUtils.isNotEmpty(accesslevels.get(9)) && containsPoolNumber(accesslevels.get(9), rowValues) || StringUtils.isNotEmpty(accesslevels.get(10)) && containsPoolNumber(accesslevels.get(10), rowValues)){
													if(StringUtils.isNotEmpty(accesslevels.get(8))){
														benid1 = accesslevels.get(8);
													}else if(StringUtils.isNotEmpty(accesslevels.get(9))){
														benid1 = accesslevels.get(9);
													}else if(StringUtils.isNotEmpty(accesslevels.get(10))){
														benid1 = accesslevels.get(10);
													}
													
													message = membersService.SaveReimbursement(accesslevels.get(4), now_polid, reason, accesslevels.get(5), cardserial, benid1, membershipnumber, "USSD: "+memberverify.get("DASHUSER"), benid1, medcode, medplan, patname, accesslevels.get(6), "Requested by scheme administrator", benid1);
													          
												}
											
											}
 
										} 
										
									}

									
								}else if((accesslevels.get(4) != null)
										&& (accesslevels.get(4).equals("2"))) {
									
									String stgmessage = "CON Please choose an id\n";
									for(Entry<String, String> pool : membersService.GET_MEMBER_BENEFIT_POOL_NUMBERS(reason).entrySet()){
									    stgmessage +=pool.getKey()+":";
									    stgmessage +=pool.getValue().substring(0,1).toUpperCase() + pool.getValue().substring(1).toLowerCase()+"\n";
									}
									message = stgmessage;
									
									
								} 
								
							}else{
								
								message = "CON You entered an invalid Member Number , Please try again!";
								System.out.println(memberexit);
								if (memberexit==6) {
									message = "END You have already tried 3 times. Please contact your scheme administrator or our call centre +254733320660, +254718222200 for any assistance.";
								}
								
							}
						}
						/////////////////////////////////////////////////////////////////////////////////////////////////////
						////////////////////////////////////////////////////////////////////////////////////////////////////
                          
					}else if((accesslevels.get(2) != null)
							&& (accesslevels.get(2).equals("5"))) {
						
						//////////////////////////////////////////////////////////////////////////////////////////////////////
						/////////////////////////////////////////////////////////////////////////////////////////////////////
						int counter = 0;
						message = "CON Please enter the member number ";
                          if(StringUtils.isNotEmpty(accesslevels.get(3)) || StringUtils.isNotEmpty(accesslevels.get(4)) || StringUtils.isNotEmpty(accesslevels.get(5))) {

							int memberexit = 0;
							int membercounter = 0;
							Member m = null;
							
							if(StringUtils.isNotEmpty(accesslevels.get(3)) && containsMemberNumber(membersService.getMemberSMSUSSDVerification(accesslevels.get(3), smartInsurercode), smartInsurercode, accesslevels.get(3))){
								membercounter = 3;
							}else if(StringUtils.isNotEmpty(accesslevels.get(4)) && containsMemberNumber(membersService.getMemberSMSUSSDVerification(accesslevels.get(4), smartInsurercode), smartInsurercode, accesslevels.get(4))){
								membercounter = 4;
							}else if(StringUtils.isNotEmpty(accesslevels.get(5)) && containsMemberNumber(membersService.getMemberSMSUSSDVerification(accesslevels.get(5), smartInsurercode), smartInsurercode, accesslevels.get(5))){
								membercounter = 5;
							}

							if(membercounter != 0){
								String smartcode = null;
								String membershipnumber = null;
								String cardserial = null;
								String membersmsussd = null;
								String globalid = null;
								String now_polid = null;
								membersmsussdverifications = membersService.getMemberSMSUSSDVerification(accesslevels.get(membercounter), smartInsurercode);
								for(Member member: membersmsussdverifications) {
									membershipnumber = member.getMembershipNumber();
									smartcode = member.getSmartCode();	
									cardserial = member.getCardSerialNumber();
									membersmsussd  = member.getSmsStatus();
									globalid = member.getGlobalId();
									now_polid = member.getPolId();
									break;
								}
								
								message = "CON Please select an option.\n1. Activate \n2. Deactivate";
								if((accesslevels.get(4) != null)
										&& (accesslevels.get(4).equals("1"))) {
									
									message = "CON "+membersService.saveStatusChange(now_polid, membershipnumber, "1", "USSD: "+memberverify.get("DASHUSER"), "Requested by scheme administrator", cardserial)+".\n0  -  Main Menu .\n99 -  Exit";
								}else if((accesslevels.get(4) != null)
										&& (accesslevels.get(4).equals("2"))) {
									
									message = "CON "+membersService.saveStatusChange(now_polid, membershipnumber, "0", "USSD: "+memberverify.get("DASHUSER"), "Requested by scheme administrator", cardserial)+".\n0  -  Main Menu .\n99 -  Exit";				
								} 				
		
							}else{
								
								message = "CON You entered an invalid Member Number , Please try again!";
								System.out.println(memberexit);
								if (memberexit==6) {
									message = "END You have already tried 3 times. Please contact your scheme administrator or our call centre +254733320660, +254718222200 for any assistance.";
								}
								
							}
						}
						/////////////////////////////////////////////////////////////////////////////////////////////////////
						////////////////////////////////////////////////////////////////////////////////////////////////////
                          
					}else if((accesslevels.get(2) != null)
							&& (accesslevels.get(2).equals("6"))) {
						
						//////////////////////////////////////////////////////////////////////////////////////////////////////
						/////////////////////////////////////////////////////////////////////////////////////////////////////
						int counter = 0;
						message = "CON Please enter the member number ";
                          if(StringUtils.isNotEmpty(accesslevels.get(3)) || StringUtils.isNotEmpty(accesslevels.get(4)) || StringUtils.isNotEmpty(accesslevels.get(5))) {

							int memberexit = 0;
							int membercounter = 0;
							Member m = null;
							
							if(StringUtils.isNotEmpty(accesslevels.get(3)) && containsMemberNumber(membersService.getMemberSMSUSSDVerification(accesslevels.get(3), smartInsurercode), smartInsurercode, accesslevels.get(3))){
								membercounter = 3;
							}else if(StringUtils.isNotEmpty(accesslevels.get(4)) && containsMemberNumber(membersService.getMemberSMSUSSDVerification(accesslevels.get(4), smartInsurercode), smartInsurercode, accesslevels.get(4))){
								membercounter = 4;
							}else if(StringUtils.isNotEmpty(accesslevels.get(5)) && containsMemberNumber(membersService.getMemberSMSUSSDVerification(accesslevels.get(5), smartInsurercode), smartInsurercode, accesslevels.get(5))){
								membercounter = 5;
							}

							if(membercounter != 0){
								String smartcode = null;
								String membershipnumber = null;
								String cardserial = null;
								String membersmsussd = null;
								String globalid = null;
								String now_polid = null;
								membersmsussdverifications = membersService.getMemberSMSUSSDVerification(accesslevels.get(membercounter), smartInsurercode);
								for(Member member: membersmsussdverifications) {
									membershipnumber = member.getMembershipNumber();
									smartcode = member.getSmartCode();	
									cardserial = member.getCardSerialNumber();
									membersmsussd  = member.getSmsStatus();
									globalid = member.getGlobalId();
									now_polid = member.getPolId();
									break;
								}

								message = "CON "+membersService.saveFpRemoval(now_polid, membershipnumber, "USSD", "Requested by scheme administrator", memberverify.get("DASHUSER"), cardserial)+".\n0  -  Main Menu .\n99 -  Exit";
								System.out.println(message);
								
							}else{
								
								message = "CON You entered an invalid Member Number , Please try again!";
								System.out.println(memberexit);
								if (memberexit==6) {
									message = "END You have already tried 3 times. Please contact your scheme administrator or our call centre +254733320660, +254718222200 for any assistance.";
								}
								
							}
						}
						/////////////////////////////////////////////////////////////////////////////////////////////////////
						////////////////////////////////////////////////////////////////////////////////////////////////////
                          
					}				
		 }else if((accesslevels.get(1) != null)
					&& (accesslevels.get(1).equals("2"))) {
			        //message = "CON Please select an option.\n1. Check scheme status\n2. Activation\n3. Deactivation\n4. Renewal";
			        message = "CON Pre-authorization, still in development";
			
		 }



			// //////////////////////////////////////////////////////
            }else{
            	message = "CON Dear Customer, your phone number:"+PhoneNumber+" is not registered as a Scheme Administrator. Please contact our call centre +254733320660, +254718222200 for any assistance.";
            	
            }
			

		}
		// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		else {
		}

		return message;

	}

	private boolean containsPoolNumber(String enteredpool, List<String> pools) {
		
		System.out.println("WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW");
		   for (String pool : pools) {
			   
			   System.out.println("ENTERED POOL"+enteredpool);
			   System.out.println("ORIGINAL"+pool);
	        if (enteredpool.equals(pool)){
	            return true;
	        } 
	    }
		return false;
	}

	private String getCurrentSMSTimeStamp() {
		java.util.Date today = new java.util.Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		return formatter.format(today.getTime());
	}
	
	boolean containsSmartSchemeCode(List<Scheme> p, String insurer) {
	    for (Scheme scheme : p) {
	        if (scheme.getSmartCode().equals(insurer)) {
	            return true;
	        }
	    }
	    return false;
	}
	
	boolean containsMemberNumber(List<Member> p, String smartcode, String number) {
	    for (Member member : p) {
	        if (member.getSmartCode().equals(smartcode) && member.getMembershipNumber().equals(number)) {
	            return true;
	        }
	    }
	    return false;
	}
}
