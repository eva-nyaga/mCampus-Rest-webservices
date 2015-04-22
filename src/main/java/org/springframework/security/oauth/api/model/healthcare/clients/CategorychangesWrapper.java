package org.springframework.security.oauth.api.model.healthcare.clients;

import java.util.List;

/**
 * A class to make our serialized data look cleaner, particularly xml.
 * @author mulama
 *
 */
public class CategorychangesWrapper {
	
	private List<Categorychange> categorychanges;
	
	public void setCategorychanges(List<Categorychange> categorychanges) {
		this.categorychanges = categorychanges;
	}
	
	public List<Categorychange> getCategorychanges(){
		return categorychanges;
	}
	
	public static CategorychangesWrapper createNew(List<Categorychange> categorychanges){
		CategorychangesWrapper pw = new CategorychangesWrapper();
		pw.setCategorychanges(categorychanges);
		return pw;
	}
}
