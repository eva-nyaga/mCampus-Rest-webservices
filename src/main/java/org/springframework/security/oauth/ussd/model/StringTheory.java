package org.springframework.security.oauth.ussd.model;


import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth.api.model.healthcare.clients.Balance;
import org.springframework.security.oauth.api.model.healthcare.clients.ENIGMA;
import org.springframework.security.oauth.api.service.healthcare.clients.IMembersService;
import org.springframework.security.oauth.api.service.healthcare.clients.MembersService;


/**
 * A wrapper class for showing a message to the 
 * @author mulama
 *
 */
public class StringTheory {
	

	
	public  StringTheory() {
	}
	
	private String message;
	
	public String StringTheory(HashMap<Integer, String> accesslevels, String PhoneNumber) {
		// TODO Auto-generated method stub
        String message = "";
        
        HashMap<Integer, String> select_options = new HashMap<Integer, String>();
        
        select_options.put(1, "OUT PATIENT OVERALL");
        select_options.put(2, "IN PATIENT OVERALL");
        select_options.put(3, "OUT PATIENT DENTAL");
        select_options.put(4, "OUT PATIENT OPTICAL DENTAL");
        select_options.put(5, "OUT PATIENT MATERNITY");
        select_options.put(6, "IN PATIENT MATERNITY COVER");
        select_options.put(7, "OUT PATIENT FRAMES");
        select_options.put(8, "OUT PATIENT COUNSELING");
        select_options.put(9, "OUT PATIENT PAP SMEAR");
        select_options.put(10, "OUT PATIENT PSA TEST");
        select_options.put(11, "OUT PATIENT AMBULANCE SERVICES");
        select_options.put(12, "OUT PATIENT RESERVE");
        select_options.put(13, "OUT PATIENT CIRCUMCISION");
        select_options.put(14, "STAFF CLINIC BENEFIT");
        select_options.put(15, "POST HOSPITALIZATION");
        select_options.put(16, "IMMUNIZATION VACCINES");
        select_options.put(17, "IN PATIENT CONGENITAL CHILDBITH NEO");
        select_options.put(18, "IN PATIENT NON ACCIDENTAL DENTAL");
        select_options.put(19, "IN PATIENT NON ACCIDENTAL OPTICAL");
        select_options.put(20, "IN PATIENT OPTHALMOLOGY");
        select_options.put(21, "IN PATIENT INTERNAL EXTERNAL PROSTHESES");
        select_options.put(22, "IN PATIENT PHYSIOTHERAPY"); 
        select_options.put(23, "IN PATIENT MRI CT SCANS");
        select_options.put(24, "IN PATIENT ONCOLOGY CANCER");
        select_options.put(24, "IN PATIENT ORGAN TRANSPLANT");
        select_options.put(26, "IN PATIENT PRESCRIPTION DRUGS MATERIALS");
        select_options.put(27, "IN PATIENT OVERSEAS REFERRAL TREATMENT");
        select_options.put(28, "IN PATIENT MAXILLOFACIAL SURGERY");
        select_options.put(29, "IN PATIENT GYNAECOLOGICAL SURGERY");
        select_options.put(30, "IN PATIENT MAJOR ORGAN TRANSPLANT EXCLUDING COST OF ORGAN DONOR");
        select_options.put(31, "IN PATIENT HEALTH CHECKUPS");
        select_options.put(32, "IN PATIENT RESERVE");
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
        select_options.put(43, "HYPERTENSIONS DIABETES LIPEDEMIA BLOOD DISORDERS");
        select_options.put(44, "PATIENT FAMILY PLANNING");
        select_options.put(45, "KIDNEY DIALYSIS");
        select_options.put(46, "HDU AND ICU");
        select_options.put(47, "ACUTE MEDICATION");
        select_options.put(48, "ELECTIVE CEASEREAN SECTION");
        select_options.put(49, "CASH");
        select_options.put(50, "OUT PATIENT GRAND POOL");
        select_options.put(51, "IN PATIENT GRAND POOL");
        select_options.put(52, "OFFLINE BILLING");
        select_options.put(53, "RESERVED POOL NR");

		if((accesslevels.get(0) != null)&&(accesslevels.get(0).equals("1"))){
		    //////////////////////////////////////////////////
			message = "CON Please select an option: ";
		    Iterator it = select_options.entrySet().iterator();
		    int counter =0;
		    while (it.hasNext() && counter<5) {
		        Map.Entry pairs = (Map.Entry)it.next();
		        //System.out.println(pairs.getKey() + " = " + pairs.getValue());
		        message = message + "\n"+pairs.getKey()+". "+pairs.getValue();
		       // it.remove(); 
		        counter++;
		    }
			

			
			//Check balance  equals("1")
			if((accesslevels.get(1) != null)&&(!accesslevels.get(1).isEmpty())){
				
				message = "CON Please enter your Smart Mobile Service PIN.";
				

				
				if((accesslevels.get(2) != null)&&(accesslevels.get(2).equals("1"))){
				
				      if(("LEVEL3").equals("1")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }if(("LEVEL3").equals("2")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }else if(("LEVEL3").equals("3")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }else{
				      }
					  
				}else if((accesslevels.get(2) != null)&&(accesslevels.get(2).equals("2"))){
				
				      if(("LEVEL3").equals("1")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }if(("LEVEL3").equals("2")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }else if(("LEVEL3").equals("3")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }else{
				      }
					  
				}else if((accesslevels.get(2) != null)&&(accesslevels.get(2).equals("3"))){
				
				      if(("LEVEL3").equals("1")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }if(("LEVEL3").equals("2")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }else if(("LEVEL3").equals("3")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }else{
				      }
					  
				}else{
					
					BlackHole blackhole = new BlackHole();
					HashMap<Integer, String> select_map = blackhole.selectMapConvr(accesslevels.get(1));

				     
					if((accesslevels.get(2) != null)&&(!accesslevels.get(2).isEmpty())){

						String text_code = "test";
						String memnos = "test";
						
						
						MembersService membersService = new MembersService();
						Balance x = membersService.getMemberBalance(text_code, PhoneNumber, memnos);
									
						System.out.println("----------------------------------------------"+x.getStatus());

                    if((!x.getStatus().isEmpty())&&(x.getStatus() !=null)&&(x.getStatus().equals("200"))){
                    	
						if((!x.getUserStatus().isEmpty())&&(x.getUserStatus() !=null)){

							if((x.getUserStatus().equals("1"))||(x.getUserStatus().equals("2"))){
	
								/*
					            byte[] bytesData = x.getPinNo().getBytes();
					            String decodedtext = null;
					            
					            try {
									decodedtext = new ENIGMA().decrypt(bytesData);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
								(decodedtext.equals(accesslevels.get(2).trim()))
								*/
					            

								if(x.getPinNo().equals(accesslevels.get(2).trim())){
									
									message = "END Current balance as at "+getCurrentSMSTimeStamp()+" is";
									  for(Integer key: select_map.keySet()){

										  //select_map.get(key)
										  if((select_map.get(key).equals("1"))  ){
											  message = message+" "+select_options.get(1)+" "+x.getOUT_PATIENT_OVERALL()+" KSH,";  
										  } 
										  if((select_map.get(key).equals("2"))  ){
											  message = message+" "+select_options.get(2)+" "+x.getIN_PATIENT_OVERALL()+" KSH,";  
										  } 
										  if((select_map.get(key).equals("3"))  ){
											  message = message+" "+select_options.get(3)+" "+x.getOUT_PATIENT_DENTAL()+" KSH,";  
										  } 
										  if((select_map.get(key).equals("4"))  ){
											  message = message+" "+select_options.get(4)+" "+x.getOUT_PATIENT_OPTICAL_DENTAL()+" KSH,";  
										  } 
										  if((select_map.get(key).equals("5"))  ){
											  message = message+" "+select_options.get(5)+" "+x.getOUT_PATIENT_MATERNITY()+" KSH,";  
										  } 

								     }
									  
									 
									   
								     
								     
								}else{
									message="CON You have entered the wrong PIN. Please try again";
								}
								
							}else if((x.getUserStatus().equals("0"))){
							
								message="CON Dear customer, welcome to Smart Mobile Service. 1 - Accept Terms & Conditions of service, 2 - Reject.";
								
							}else if(x.getUserStatus().equals("-1")){
								
								message="CON Dear customer, your Smart Mobile Service has been blocked. Please contact your scheme administrator or our call centre +254733320660, +254718222200 for any assistance.";
								
							}
							
						}	
						
                    }else{
                    	//thos is the message
                    	message = x.getMessage();
                    }
						

					
											
					}

					
				}
				
				
				
			}else if((accesslevels.get(1) != null)&&(accesslevels.get(1).equals("2"))){
				
				if((accesslevels.get(2) != null)&&(accesslevels.get(2).equals("1"))){
				
				      if(("LEVEL3").equals("1")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }if(("LEVEL3").equals("2")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }else if(("LEVEL3").equals("3")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }else{
				      }
					  
				}else if((accesslevels.get(2) != null)&&(accesslevels.get(2).equals("2"))){
				
				      if(("LEVEL3").equals("1")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }if(("LEVEL3").equals("2")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }else if(("LEVEL3").equals("3")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }else{
				      }
					  
				}else if((accesslevels.get(2) != null)&&(accesslevels.get(2).equals("3"))){
				
				      if(("LEVEL3").equals("1")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }if(("LEVEL3").equals("2")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }else if(("LEVEL3").equals("3")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }else{
				      }
					  
				}else{
				}
				
			}else if((accesslevels.get(1) != null)&&(accesslevels.get(1).equals("3"))){
				
				if((accesslevels.get(2) != null)&&(accesslevels.get(2).equals("1"))){
				
				      if(("LEVEL3").equals("1")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }if(("LEVEL3").equals("2")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }else if(("LEVEL3").equals("3")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }else{
				      }
					  
				}else if((accesslevels.get(2) != null)&&(accesslevels.get(2).equals("2"))){
				
				      if(("LEVEL3").equals("1")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }if(("LEVEL3").equals("2")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }else if(("LEVEL3").equals("3")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }else{
				      }
					  
				}else if((accesslevels.get(2) != null)&&(accesslevels.get(2).equals("3"))){
				
				      if(("LEVEL3").equals("1")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }if(("LEVEL3").equals("2")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }else if(("LEVEL3").equals("3")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }else{
				      }
					  
				}else{
				}
				
			}else{	
			}
			
			System.out.println("__________________________yyyyyy_"+message);
			System.out.println("QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ");
		////////////////////////////////////////////////////////	
		}	
		
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		else if((accesslevels.get(0) != null)&&(accesslevels.get(0).equals("2"))){
		    //////////////////////////////////////////////////
			//Check balance
			if((accesslevels.get(1) != null)&&(accesslevels.get(1).equals("1"))){
				
				if((accesslevels.get(2) != null)&&(accesslevels.get(2).equals("1"))){
				
				      if(("LEVEL3").equals("1")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }if(("LEVEL3").equals("2")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }else if(("LEVEL3").equals("3")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }else{
				      }
					  
				}else if((accesslevels.get(2) != null)&&(accesslevels.get(2).equals("2"))){
				
				      if(("LEVEL3").equals("1")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }if(("LEVEL3").equals("2")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }else if(("LEVEL3").equals("3")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }else{
				      }
					  
				}else if((accesslevels.get(2) != null)&&(accesslevels.get(2).equals("3"))){
				
				      if(("LEVEL3").equals("1")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }if(("LEVEL3").equals("2")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }else if(("LEVEL3").equals("3")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }else{
				      }
					  
				}else{
				}
				
			}else if((accesslevels.get(1) != null)&&(accesslevels.get(1).equals("2"))){
				
				if((accesslevels.get(2) != null)&&(accesslevels.get(2).equals("1"))){
				
				      if(("LEVEL3").equals("1")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }if(("LEVEL3").equals("2")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }else if(("LEVEL3").equals("3")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }else{
				      }
					  
				}else if((accesslevels.get(2) != null)&&(accesslevels.get(2).equals("2"))){
				
				      if(("LEVEL3").equals("1")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }if(("LEVEL3").equals("2")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }else if(("LEVEL3").equals("3")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }else{
				      }
					  
				}else if((accesslevels.get(2) != null)&&(accesslevels.get(2).equals("3"))){
				
				      if(("LEVEL3").equals("1")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }if(("LEVEL3").equals("2")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }else if(("LEVEL3").equals("3")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }else{
				      }
					  
				}else{
				}
				
			}else if((accesslevels.get(1) != null)&&(accesslevels.get(1).equals("3"))){
				
				if((accesslevels.get(2) != null)&&(accesslevels.get(2).equals("1"))){
				
				      if(("LEVEL3").equals("1")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }if(("LEVEL3").equals("2")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }else if(("LEVEL3").equals("3")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }else{
				      }
					  
				}else if((accesslevels.get(2) != null)&&(accesslevels.get(2).equals("2"))){
				
				      if(("LEVEL3").equals("1")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }if(("LEVEL3").equals("2")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }else if(("LEVEL3").equals("3")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }else{
				      }
					  
				}else if((accesslevels.get(2) != null)&&(accesslevels.get(2).equals("3"))){
				
				      if(("LEVEL3").equals("1")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }if(("LEVEL3").equals("2")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }else if(("LEVEL3").equals("3")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }else{
				      }
					  
				}else{
				}
				
			}else{	
			}
		////////////////////////////////////////////////////////	
			
		}
		
		
		
		
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		
		else if((accesslevels.get(0) != null)&&(accesslevels.get(0).equals("3"))){
		    //////////////////////////////////////////////////
			//Check balance
			if((accesslevels.get(1) != null)&&(accesslevels.get(1).equals("1"))){
				
				if((accesslevels.get(2) != null)&&(accesslevels.get(2).equals("1"))){
				
				      if(("LEVEL3").equals("1")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }if(("LEVEL3").equals("2")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }else if(("LEVEL3").equals("3")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }else{
				      }
					  
				}else if((accesslevels.get(2) != null)&&(accesslevels.get(2).equals("2"))){
				
				      if(("LEVEL3").equals("1")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }if(("LEVEL3").equals("2")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }else if(("LEVEL3").equals("3")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }else{
				      }
					  
				}else if((accesslevels.get(2) != null)&&(accesslevels.get(2).equals("3"))){
				
				      if(("LEVEL3").equals("1")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }if(("LEVEL3").equals("2")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }else if(("LEVEL3").equals("3")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }else{
				      }
					  
				}else{
				}
				
			}else if((accesslevels.get(1) != null)&&(accesslevels.get(1).equals("2"))){
				
				if((accesslevels.get(2) != null)&&(accesslevels.get(2).equals("1"))){
				
				      if(("LEVEL3").equals("1")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }if(("LEVEL3").equals("2")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }else if(("LEVEL3").equals("3")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }else{
				      }
					  
				}else if((accesslevels.get(2) != null)&&(accesslevels.get(2).equals("2"))){
				
				      if(("LEVEL3").equals("1")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }if(("LEVEL3").equals("2")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }else if(("LEVEL3").equals("3")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }else{
				      }
					  
				}else if((accesslevels.get(2) != null)&&(accesslevels.get(2).equals("3"))){
				
				      if(("LEVEL3").equals("1")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }if(("LEVEL3").equals("2")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }else if(("LEVEL3").equals("3")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }else{
				      }
					  
				}else{
				}
				
			}else if((accesslevels.get(1) != null)&&(accesslevels.get(1).equals("3"))){
				
				if((accesslevels.get(2) != null)&&(accesslevels.get(2).equals("1"))){
				
				      if(("LEVEL3").equals("1")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }if(("LEVEL3").equals("2")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }else if(("LEVEL3").equals("3")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }else{
				      }
					  
				}else if((accesslevels.get(2) != null)&&(accesslevels.get(2).equals("2"))){
				
				      if(("LEVEL3").equals("1")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }if(("LEVEL3").equals("2")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }else if(("LEVEL3").equals("3")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }else{
				      }
					  
				}else if((accesslevels.get(2) != null)&&(accesslevels.get(2).equals("3"))){
				
				      if(("LEVEL3").equals("1")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }if(("LEVEL3").equals("2")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }else if(("LEVEL3").equals("3")){
					       				
				            if(("LEVEL4").equals("1")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("2")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else if(("LEVEL4").equals("3")){
							
							  		 if(("LEVEL5").equals("1")){
					  
				                     }else if(("LEVEL5").equals("2")){
					  
				                     }else if(("LEVEL5").equals("3")){
					  
				                     }else{
				                     }
					  
				            }else{
				            }
					  
				      }else{
				      }
					  
				}else{
				}
				
			}else{	
			}
		////////////////////////////////////////////////////////	
			
		}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		else{
			}
		
		
		
		return message;
		
		
	}
	
	private  String getCurrentSMSTimeStamp() {
		java.util.Date today = new java.util.Date();
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(today.getTime());
	}
}
