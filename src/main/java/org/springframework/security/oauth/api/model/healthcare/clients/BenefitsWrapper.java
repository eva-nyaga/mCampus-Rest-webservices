package org.springframework.security.oauth.api.model.healthcare.clients;

import java.util.List;

/**
 * A class to make our serialized data look cleaner, particularly xml.
 * @author mulama
 *
 */
public class BenefitsWrapper {
	
	private List<Benefit> benefits;
	
	public void setBenefits(List<Benefit> benefits) {
		this.benefits = benefits;
	}
	
	public List<Benefit> getBenefits(){
		return benefits;
	}
	
	public static BenefitsWrapper createNew(List<Benefit> benefits){
		BenefitsWrapper pw = new BenefitsWrapper();
		pw.setBenefits(benefits);
		return pw;
	}
}
