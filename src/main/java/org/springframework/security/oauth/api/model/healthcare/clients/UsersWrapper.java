package org.springframework.security.oauth.api.model.healthcare.clients;

import java.util.List;

/**
 * A class to make our serialized data look cleaner, particularly xml.
 * @author Eva
 *
 */
public class UsersWrapper {
	
	private List<MyUser> users;
	
	public void setUsers(List<MyUser> users) {
		this.users = users;
	}
	
	public List<MyUser> getUsers(){
		return users;
	}
	
	public static UsersWrapper createNew(List<MyUser> users){
		UsersWrapper pw = new UsersWrapper();
		pw.setUsers(users);
		return pw;
	}
}
