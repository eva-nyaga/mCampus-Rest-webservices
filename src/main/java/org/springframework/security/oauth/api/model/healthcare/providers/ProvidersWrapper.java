package org.springframework.security.oauth.api.model.healthcare.providers;

import java.util.List;

/**
 * A class to make our serialized data look cleaner, particularly xml.
 * @author mulama
 *
 */
public class ProvidersWrapper {
	
	private List<Provider> providers;
	
	public void setProviders(List<Provider> providers) {
		this.providers = providers;
	}
	
	public List<Provider> getProviders(){
		return providers;
	}
	
	public static ProvidersWrapper createNew(List<Provider> providers){
		ProvidersWrapper pw = new ProvidersWrapper();
		pw.setProviders(providers);
		return pw;
	}
}
