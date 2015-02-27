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
			//
			// Process the Ussd request
			if(StringUtils.isNotEmpty(ussdRequest.getsession_id())){
				//(!.isEmpty()) && (!ussdRequest.getsession_id().equals(null)) && (ussdRequest.getsession_id().length() > 0)
				//UssdResponse ussdResponse =ussdresponse.get(ussdRequest.getsession_id()); 
				

				// check the various request type
				if(StringUtils.isEmpty(ussdRequest.getussd_string().substring(1))){
					
					accesslevels.put(0, null);//checkbalance//payaccess/paycardaccess
					accesslevels.put(1, null);//OP//IP/DEBTAL//
					accesslevels.put(2, null);
					accesslevels.put(3, null);
					accesslevels.put(4, null);
					accesslevels.put(5, null);
					
					// Set the ussd response
			          addUssdResponse(new UssdResponse(
			        		  ussdRequest.getsession_id(),
			        		  null,
			        		  "CON Welcome to Smart Mobile Service.\n1. Check balance\n2. Request for card reprint\n3. Query for statement\n4. Report card as stolen or lost",
			        		  "Response",
			        		  accesslevels
			                 ), ussdRequest.getsession_id());
				}else{
					

					// response case studies
					if(StringUtils.isNotEmpty(ussdRequest.getussd_string().substring(1))){
						
						//String texts = "**9111#";
						//String texts = "*9111*1#";
						//String texts = "***************************9111*1*2*3*4*5*6*7*8*9*10#";
						//String texts = "*1*2*3*4*5#";
						//String texts = "1*2*3*4*5#";
						//String texts = "*9111*657567*2*3*4*5#";
						//String texts = "*91 11*1*2  *3*v  jg  hj*5#";
						//String texts = "*91 11*1*2  *3*v  jg  hj*5#";
						
						BlackHole hole = new BlackHole();
						HashMap<Integer, String> accesslevels = hole.BlackHole(ussdRequest.getussd_string().substring(1));
						
						StringTheory stringtheory = new StringTheory();
						String message = stringtheory.StringTheory(accesslevels, ussdRequest.getmsisdn());

						//accesslevels.get(0)
		                 addUssdResponse(new UssdResponse(
		                           ussdRequest.getsession_id(),
		                           null,
		                           message,
		                           "Release",
		                           accesslevels
		                        ), ussdRequest.getsession_id());

					}else{
	
						accesslevels.put(0, null);//checkbalance//payaccess/paycardaccess
						accesslevels.put(1, null);//OP//IP/DEBTAL//
						accesslevels.put(2, null);
						accesslevels.put(3, null);
						accesslevels.put(4, null);
						accesslevels.put(5, null);
						
                 addUssdResponse(new UssdResponse(
                           ussdRequest.getsession_id(),
                           null,
                           "Invalid option.",
                           "Release",
                           accesslevels
                        ), ussdRequest.getsession_id());
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
		
		
		if(StringUtils.isNotEmpty(ussdRequest.getsession_id())){
			
			System.out.println(ussdRequest.getsession_id());
			UssdResponse ussdResponse =ussdresponse.get(ussdRequest.getsession_id()); 
			if(ussdResponse == null)
			{
				return "Service is currently down, Please try again later.";
			}else{
				return ussdResponse.getmessage();
			}
			
		}else{
			    return "Smart Session ID missing.";
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
