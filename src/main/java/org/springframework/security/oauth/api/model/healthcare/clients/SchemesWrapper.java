package org.springframework.security.oauth.api.model.healthcare.clients;

import java.util.List;

/**
 * A class to make our serialized data look cleaner, particularly xml.
 * @author mulama
 *
 */
public class SchemesWrapper {
	
	private List<Scheme> schemes;
	
	public void setSchemes(List<Scheme> schemes) {
		this.schemes = schemes;
	}
	
	public List<Scheme> getSchemes(){
		return schemes;
	}
	
	public static SchemesWrapper createNew(List<Scheme> schemes){
		SchemesWrapper pw = new SchemesWrapper();
		pw.setSchemes(schemes);
		return pw;
	}
}
