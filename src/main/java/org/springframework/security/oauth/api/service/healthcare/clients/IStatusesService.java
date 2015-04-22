package org.springframework.security.oauth.api.service.healthcare.clients;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.oauth.api.model.healthcare.clients.Status;
import org.springframework.security.oauth.api.model.healthcare.clients.Person;




public interface IStatusesService {

	public List<Status> getStatuses(String customerid, String country, int startindex, int maxresults, int status, String restrict,  String orderby);
	public List<Status> getSearchStatuses(String q, String customerid, String country, int startindex, int maxresults, int status, String restrict,  String orderby);
	public Status getStatus(int id, String customerid, String country);
	public int addStatus(Status status, String customerid, String country);
	public int addStatusReturns(Status status, String customerid, String country);
	public int addStatus(Status status, int id);
	public void updateStatus(int id, String customerid, String country);
	public void deleteStatus(int id, String customerid, String country);
	public void updateSwitchedStatus(int id, String customerid, String country);
	
}
