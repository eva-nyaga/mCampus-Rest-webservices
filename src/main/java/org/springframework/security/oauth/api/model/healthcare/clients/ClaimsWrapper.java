package org.springframework.security.oauth.api.model.healthcare.clients;

import java.util.List;

/**
 * A class to make our serialized data look cleaner, particularly xml.
 * @author mulama
 *
 */
public class ClaimsWrapper {
	
	private List<Claim> claims;
	
	public void setClaims(List<Claim> claims) {
		this.claims = claims;
	}
	
	public List<Claim> getClaims(){
		return claims;
	}
	
	public static ClaimsWrapper createNew(List<Claim> claims){
		ClaimsWrapper pw = new ClaimsWrapper();
		pw.setClaims(claims);
		return pw;
	}
}
