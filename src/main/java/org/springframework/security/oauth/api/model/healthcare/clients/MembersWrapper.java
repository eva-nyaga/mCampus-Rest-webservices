package org.springframework.security.oauth.api.model.healthcare.clients;

import java.util.List;

/**
 * A class to make our serialized data look cleaner, particularly xml.
 * @author mulama
 *
 */
public class MembersWrapper {
	
	private List<Member> members;
	
	public void setMembers(List<Member> members) {
		this.members = members;
	}
	
	public List<Member> getMembers(){
		return members;
	}
	
	public static MembersWrapper createNew(List<Member> members){
		MembersWrapper pw = new MembersWrapper();
		pw.setMembers(members);
		return pw;
	}
}
