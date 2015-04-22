package org.springframework.security.oauth.api.service.healthcare.clients;

import java.util.List;

import org.springframework.security.oauth.api.model.healthcare.clients.Person;
import org.springframework.security.oauth.ussd.model.UssdRequest;


public interface IPeopleService {
	
	
	public List<Person> getPeople();
	public List<Person> getPeople(String name);
	public Person getPerson(int id);
	public int addPerson(Person person);
	public void updatePerson(int id, Person person);
	public void deletePerson(int id);
	public String processUssd(UssdRequest ussd);
}
