package org.springframework.security.oauth.api.model.healthcare.clients;

import java.util.List;

/**
 * A class to make our serialized data look cleaner, particularly xml.
 * @author mulama
 *
 */
public class BenefitCategoriesWrapper {
	
	private List<BenefitCategory> benefitCategories;
	
	public void setBenefitCategories(List<BenefitCategory> benefitCategories) {
		this.benefitCategories = benefitCategories;
	}
	
	public List<BenefitCategory> getBenefitCategories(){
		return benefitCategories;
	}
	
	public static BenefitCategoriesWrapper createNew(List<BenefitCategory> benefitCategories){
		BenefitCategoriesWrapper pw = new BenefitCategoriesWrapper();
		pw.setBenefitCategories(benefitCategories);
		return pw;
	}
}
