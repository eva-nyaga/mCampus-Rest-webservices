package org.springframework.security.oauth.api.model.healthcare.clients;

import java.util.List;

/**
 * A class to make our serialized data look cleaner, particularly xml.
 * @author mulama
 *
 */
public class ActivationsWrapper {
	
	private List<Activation> activations;
	
	public void setActivations(List<Activation> activations) {
		this.activations = activations;
	}
	
	public List<Activation> getActivations(){
		return activations;
	}
	
	public static ActivationsWrapper createNew(List<Activation> activations){
		ActivationsWrapper pw = new ActivationsWrapper();
		pw.setActivations(activations);
		return pw;
	}
}
