package org.springframework.security.oauth.api.service.healthcare.clients;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.oauth.api.model.healthcare.clients.BenefitCategory;
import org.springframework.security.oauth.api.model.healthcare.clients.Person;




public interface IBenefit_CategoriesService {

	public List<BenefitCategory> getBenefit_Categories(String customerid, String country, int startindex, int maxresults, int status, String restrict,  String orderby);
	public List<BenefitCategory> getSearchBenefit_Categories(String q, String customerid, String country, int startindex, int maxresults, int status, String restrict,  String orderby);
	public BenefitCategory getBenefit_Category(String id, String customerid, String country);
	public String addBenefit_Category(BenefitCategory benefit_category, String customerid, String country);
	public String addBenefit_CategoryReturns(BenefitCategory benefit_category, String customerid, String country);
	public String addBenefit_Category(BenefitCategory benefit_category, String id);
	public void updateBenefit_Category(String id, String customerid, String country);
	public void deleteBenefit_Category(String id, String customerid, String country);
	public void updateSwitchedBenefit_Category(String id, String customerid, String country);
	
}
