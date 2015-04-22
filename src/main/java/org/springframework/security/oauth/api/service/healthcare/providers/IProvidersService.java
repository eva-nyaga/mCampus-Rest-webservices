package org.springframework.security.oauth.api.service.healthcare.providers;

import java.util.ArrayList;
import java.util.List;


import org.springframework.security.oauth.api.model.healthcare.clients.Person;
import org.springframework.security.oauth.api.model.healthcare.providers.Provider;




public interface IProvidersService {

	public List<Provider> getProviders(String customerid, String country, int startindex, int maxresults, int status, String restrict,  String orderby);
	public List<Provider> getSearchProviders(String q, String customerid, String country, int startindex, int maxresults, int status, String restrict,  String orderby);
	public Provider getProvider(String id, String customerid, String country);
	public String addProvider(Provider claim, String customerid, String country);
	public String addProviderReturns(Provider claim, String customerid, String country);
	public String addProvider(Provider claim, String id);
	public void updateProvider(String id, String customerid, String country);
	public void deleteProvider(String id, String customerid, String country);
	public void updateSwitchedProvider(String id, String customerid, String country);
	public List<Provider> getProvidersDefault(String customerid, String country, int startindex, int maxresults, int status, String restrict, String orderby);
	
}
