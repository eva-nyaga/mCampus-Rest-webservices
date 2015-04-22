package org.springframework.security.oauth.api.service.healthcare.clients;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.oauth.api.model.healthcare.clients.Scheme;
import org.springframework.security.oauth.api.model.healthcare.clients.SchemeRenewal;

public interface ISchemesService {

	public List<Scheme> getSchemes(String customerid, String country, int startindex, int maxresults, int status, String restrict,  String orderby);
	public List<Scheme> getSearchSchemes(String q, String customerid, String country, int startindex, int maxresults, int status, String restrict,  String orderby);
	public Scheme getScheme(String id, String customerid, String country);
	public String addScheme(Scheme scheme, String customerid, String country);
	public String addSchemeReturns(Scheme scheme, String customerid, String country);
	public String addScheme(Scheme scheme, String id);
	public void updateScheme(String id, String customerid, String country);
	public void deleteScheme(String id, String customerid, String country);
	public void updateSwitchedScheme(String id, String customerid, String country);
	public String updateRenewalsScheme(String id, SchemeRenewal schemeRenewal,
			String customerid, String country);
	public String updateActivationsScheme(String polid, Scheme schemeActivation, String customerid, String country);
	public String updateDeactivationsScheme(String polid, Scheme schemeDeactivation, String customerid, String country);
	
}
