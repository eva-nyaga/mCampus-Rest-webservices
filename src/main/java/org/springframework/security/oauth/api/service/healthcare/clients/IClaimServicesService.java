package org.springframework.security.oauth.api.service.healthcare.clients;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.oauth.api.model.healthcare.clients.ClaimService;
import org.springframework.security.oauth.api.model.healthcare.clients.Person;




public interface IClaimServicesService {

	public List<ClaimService> getClaimServices(String customerid, String country, String restrict);
	public List<ClaimService> getSearchClaimServices(String q, String customerid, String country, int startindex, int maxresults, int status, String restrict,  String orderby);
	public ClaimService getClaimService(String id, String customerid, String country);
	public String addClaimService(ClaimService claimService, String customerid, String country);
	public int addClaimServiceReturns(ClaimService claimService, String customerid, String country);
	public String addClaimService(ClaimService claimService, String id);
	public void updateClaimService(int id, String customerid, String country);
	public void deleteClaimService(int id, String customerid, String country);
	public void updateSwitchedClaimService(int id, String customerid, String country);
	public List<ClaimService> getClaimServices(String customerid, String country, int startindex, int maxresults, int status, String restrict, String orderby);
	public List<ClaimService> getClaimServiceClaimId(String id, String customerid, String country);
	
}
