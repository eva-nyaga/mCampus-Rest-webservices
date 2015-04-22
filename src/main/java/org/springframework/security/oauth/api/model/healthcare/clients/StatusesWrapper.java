package org.springframework.security.oauth.api.model.healthcare.clients;

import java.util.List;

/**
 * A class to make our serialized data look cleaner, particularly xml.
 * @author mulama
 *
 */
public class StatusesWrapper {
	
	private List<Status> statuses;
	
	public void setStatuses(List<Status> statuses) {
		this.statuses = statuses;
	}
	
	public List<Status> getStatuses(){
		return statuses;
	}
	
	public static StatusesWrapper createNew(List<Status> statuses){
		StatusesWrapper pw = new StatusesWrapper();
		pw.setStatuses(statuses);
		return pw;
	}
}
