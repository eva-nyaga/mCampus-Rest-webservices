package org.springframework.security.oauth.api.model.healthcare.clients;

import java.util.List;

/**
 * A class to make our serialized data look cleaner, particularly xml.
 * @author mulama
 *
 */
public class DeactivationsWrapper {
	
	private List<Deactivation> deactivations;
	
	public void setDeactivations(List<Deactivation> deactivations) {
		this.deactivations = deactivations;
	}
	
	public List<Deactivation> getDeactivations(){
		return deactivations;
	}
	
	public static DeactivationsWrapper createNew(List<Deactivation> deactivations){
		DeactivationsWrapper pw = new DeactivationsWrapper();
		pw.setDeactivations(deactivations);
		return pw;
	}
}
