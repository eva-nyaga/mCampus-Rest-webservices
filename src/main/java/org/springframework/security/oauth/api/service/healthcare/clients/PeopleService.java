package org.springframework.security.oauth.api.service.healthcare.clients;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.oauth.api.model.healthcare.clients.Claim;
import org.springframework.security.oauth.api.model.healthcare.clients.Person;
import org.springframework.security.oauth.ussd.model.BlackHole;
import org.springframework.security.oauth.ussd.model.StringTheory;
import org.springframework.security.oauth.ussd.model.UssdRequest;
import org.springframework.security.oauth.ussd.model.UssdResponse;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;


@Service("peopleService")
public class PeopleService implements IPeopleService {
	
	private Map<Integer, Person> people = new HashMap<Integer, Person>();
	private Map<String, UssdResponse> ussdresponse = new HashMap<String, UssdResponse>();
	private HashMap<Integer, String> accesslevels = new HashMap<Integer, String>();
	private AtomicInteger idGen = new AtomicInteger();


	public PeopleService(){
	}
	
	@Override
	public String processUssd(UssdRequest ussdRequest) {
		// TODO Auto-generated method stub
		// Set the output character encoding
		
		
		try{
			// Process the Ussd request
			if(StringUtils.isNotEmpty(ussdRequest.getSESSION_ID())){

				// check the various request type
				if(StringUtils.isEmpty(ussdRequest.getUSSD_STRING()) || (ussdRequest.getUSSD_STRING().equals("0")) || (ussdRequest.getUSSD_STRING().equals("0*")) || (ussdRequest.getUSSD_STRING().equals("*0")) || (ussdRequest.getUSSD_STRING().equals("*")) || (ussdRequest.getUSSD_STRING().equals("")) || (ussdRequest.getUSSD_STRING().equals(" "))){
					
					System.out.println(ussdRequest.getUSSD_STRING());
					accesslevels.put(0, null);//checkbalance//payaccess/paycardaccess
					accesslevels.put(1, null);//OP//IP/DEBTAL//
					accesslevels.put(2, null);
					accesslevels.put(3, null);
					accesslevels.put(4, null);
					accesslevels.put(5, null);
					
					// Set the ussd response
			          addUssdResponse(new UssdResponse(
			        		  ussdRequest.getSESSION_ID(),
			        		  null,
			        		  "CON Welcome to Smart Mobile Service.\n1. Registration\n2. Check balance\n3. Check member status\n4. Request statement\n5. Check scheme status\n6. Settings\n7. Admin",
			        		  "Response",
			        		  accesslevels
			                 ), ussdRequest.getSESSION_ID());
				}else{

					// response case studies
					if(StringUtils.isNotEmpty(ussdRequest.getUSSD_STRING())){
						
						//String texts = "**9111#";
						//String texts = "*9111*1#";
						//String texts = "***************************9111*1*2*3*4*5*6*7*8*9*10#";
						//String texts = "*1*2*3*4*5#";
						//String texts = "1*2*3*4*5#";
						//String texts = "*9111*657567*2*3*4*5#";
						//String texts = "*91 11*1*2  *3*v  jg  hj*5#";
						//String texts = "*91 11*1*2  *3*v  jg  hj*5#";
						
						if(ussdRequest.getUSSD_STRING().equals("99")){
							
							String message = "END Dear customer, Thank you for using the Smart Mobile Service."; 
			                 addUssdResponse(new UssdResponse(
			                           ussdRequest.getSESSION_ID(),
			                           null,
			                           message,
			                           "Release",
			                           accesslevels
			                        ), ussdRequest.getSESSION_ID());
						}else{

							BlackHole hole = new BlackHole();
							System.out.println("THE BEGINNING");
							HashMap<Integer, String> accesslevels = hole.BlackHole(ussdRequest.getUSSD_STRING());
							System.out.println("THE END");
							StringTheory stringtheory = new StringTheory();
							String message = stringtheory.StringTheory(accesslevels, ussdRequest.getMSISDN());

			                 addUssdResponse(new UssdResponse(
			                           ussdRequest.getSESSION_ID(),
			                           null,
			                           message,
			                           "Release",
			                           accesslevels
			                        ), ussdRequest.getSESSION_ID());
						}
						

					}else{
	
						accesslevels.put(0, null);//checkbalance//payaccess/paycardaccess
						accesslevels.put(1, null);//OP//IP/DEBTAL//
						accesslevels.put(2, null);
						accesslevels.put(3, null);
						accesslevels.put(4, null);
						accesslevels.put(5, null);
						
                 addUssdResponse(new UssdResponse(
                           ussdRequest.getSESSION_ID(),
                           null,
                           "Invalid option.",
                           "Release",
                           accesslevels
                        ), ussdRequest.getSESSION_ID());
     				}
					
				}
				
			}else{
				
				accesslevels.put(0, null);//checkbalance//payaccess/paycardaccess
				accesslevels.put(1, null);//OP//IP/DEBTAL//
				accesslevels.put(2, null);
				accesslevels.put(3, null);
				accesslevels.put(4, null);
				accesslevels.put(5, null);
				
				int id = idGen.incrementAndGet();
                addUssdResponse(new UssdResponse(
                		"",
                        null,
                        "Session ID missing.",
                        "Release",
                        accesslevels
                     ), "");
			}
			
			
			//System.out.println("Ussd response : " +ussdResponse.toString());
			//String responseJson = gson.toJson(ussdResponse);
			//out.print(responseJson);
			//System.out.println(ussdresponse.toString());
			
		}
		catch(Exception e){
			// Send at least a message to the user in case of errors
			try{
				System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAATLEAST AN ERROR");
				Gson gson = new Gson();
				UssdResponse ussdResponse = new UssdResponse();
				ussdResponse.setmessage("Invalid request");
				ussdResponse.setType("Release");
				String responseJson = gson.toJson(ussdResponse);
				System.out.println(responseJson);
									
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
			e.printStackTrace();
		}
		finally{
			//out.close();
		}	
		
		
		if(StringUtils.isNotEmpty(ussdRequest.getSESSION_ID())){
			
			System.out.println(ussdRequest.getSESSION_ID());
			UssdResponse ussdResponse =ussdresponse.get(ussdRequest.getSESSION_ID()); 
			if(ussdResponse == null)
			{
				return "END Service is currently down 'Null', Please try again later.";
			}else{
				return ussdResponse.getmessage();
			}
			
		}else{
			    return "END Smart Session ID missing.";
		}
		
		
	}



	private String addUssdResponse(UssdResponse ussdResponse, String sessionId) {
		// TODO Auto-generated method stub
		
		if(ussdresponse.containsValue(ussdResponse)){
			//throw new IllegalArgumentException("Ussd Response "+ussdResponse+" already exists.");
			//ussdresponse.clear();
		}
		
		ussdresponse.put(sessionId, ussdResponse);
		return sessionId;
	}
	
	public List<Person> getPeople() {
		return new ArrayList<Person>(people.values());
	}

	public List<Person> getPeople(String name) {
		
		name = name.toLowerCase();
		List<Person> matchingPeople = new ArrayList<Person>();
		
		for(Person p : people.values())
		{
			if(p.getName().toLowerCase().contains(name)){
				matchingPeople.add(p);
			}
		}
		
		return matchingPeople;
	}

	public Person getPerson(int id) throws IllegalArgumentException {
		Person p = people.get(id);
		if(p == null)
		{
			throw new IllegalArgumentException("Could not find person with id:"+id);
		}
		return p;
	}

	public int addPerson(Person person) throws IllegalArgumentException {
		
		if(people.containsValue(person)){
			throw new IllegalArgumentException("Person "+person+" already exists.");
		}
		
		int id = idGen.incrementAndGet();
		person.setId(id);
		people.put(id, person);
		
		return id;
	}

	public void updatePerson(int id, Person person)
			throws IllegalArgumentException {
			
		if(!people.containsKey(id)){
			throw new IllegalArgumentException("Unable to find person with id "+id);
		}
		
		people.put(id, person);
		
	}

	public void deletePerson(int id) throws IllegalArgumentException {
		if(!people.containsKey(id)){
			throw new IllegalArgumentException("Unable to find person with id "+id);
		}
		
		people.remove(id);
		
	}



}
