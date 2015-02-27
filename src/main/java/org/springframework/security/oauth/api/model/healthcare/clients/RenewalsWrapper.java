package org.springframework.security.oauth.api.model.healthcare.clients;

import java.util.List;

/**
 * A class to make our serialized data look cleaner, particularly xml.
 * @author mulama
 *
 */
public class RenewalsWrapper {
	
	private List<Renewal> renewals;
	
	public void setRenewals(List<Renewal> renewals) {
		this.renewals = renewals;
	}
	
	public List<Renewal> getRenewals(){
		return renewals;
	}
	
	public static RenewalsWrapper createNew(List<Renewal> renewals){
		RenewalsWrapper pw = new RenewalsWrapper();
		pw.setRenewals(renewals);
		return pw;
	}
}
