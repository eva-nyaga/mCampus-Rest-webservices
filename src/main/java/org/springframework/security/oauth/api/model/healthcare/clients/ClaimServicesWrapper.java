package org.springframework.security.oauth.api.model.healthcare.clients;

import java.util.List;

/**
 * A class to make our serialized data look cleaner, particularly xml.
 * @author mulama
 *
 */
public class ClaimServicesWrapper {
	
	private List<ClaimService> claimservices;
	
	public void setClaimServices(List<ClaimService> claimservices) {
		this.claimservices = claimservices;
	}
	
	public List<ClaimService> getClaimServices(){
		return claimservices;
	}
	
	public static ClaimServicesWrapper createNew(List<ClaimService> claimServices){
		ClaimServicesWrapper pw = new ClaimServicesWrapper();
		pw.setClaimServices(claimServices);
		return pw;
	}
	
}
