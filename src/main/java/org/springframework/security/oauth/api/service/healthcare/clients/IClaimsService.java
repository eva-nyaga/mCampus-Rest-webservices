package org.springframework.security.oauth.api.service.healthcare.clients;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.oauth.api.model.healthcare.clients.Claim;
import org.springframework.security.oauth.api.model.healthcare.clients.Person;




public interface IClaimsService {

	public List<Claim> getClaims(String customerid, String country, int startindex, int maxresults, int status, String restrict,  String orderby);
	public List<Claim> getSearchClaims(String q, String customerid, String country, int startindex, int maxresults, int status, String restrict,  String orderby);
	public Claim getClaim(String id, String customerid, String country);
	public String addClaim(Claim claim, String customerid, String country);
	public String addClaimReturns(Claim claim, String customerid, String country);
	public String addClaim(Claim claim, String id);
	public void updateClaim(String id, String customerid, String country);
	public void deleteClaim(String id, String customerid, String country);
	public String updateSwitchedClaim(String claimid, String customerid, String country);
	public String updateSwitchedClaims(String claimids, String customerid, String country);
	
}
