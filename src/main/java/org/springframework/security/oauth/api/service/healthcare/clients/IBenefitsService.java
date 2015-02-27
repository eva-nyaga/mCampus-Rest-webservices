package org.springframework.security.oauth.api.service.healthcare.clients;

import java.util.ArrayList;
import java.util.List;
import org.springframework.security.oauth.api.model.healthcare.clients.Benefit;

public interface IBenefitsService {

	public List<Benefit> getBenefits(String customerid, String country, int startindex, int maxresults, int status, String restrict,  String orderby);
	public List<Benefit> getBenefitsDefault(String customerid, String country, int startindex, int maxresults, int status, String restrict, String orderby);
	public List<Benefit> getSearchBenefits(String q, String customerid, String country, int startindex, int maxresults, int status, String restrict,  String orderby);
	public Benefit getBenefit(String id, String customerid, String country);
	public String addBenefit(Benefit benefit, String customerid, String country);
	public String addBenefitReturns(Benefit benefit, String customerid, String country);
	public String addBenefit(Benefit benefit, String id);
	public void updateBenefit(String id, String customerid, String country);
	public void deleteBenefit(String id, String customerid, String country);
	public void updateSwitchedBenefit(String id, String customerid, String country);
	
	
}
