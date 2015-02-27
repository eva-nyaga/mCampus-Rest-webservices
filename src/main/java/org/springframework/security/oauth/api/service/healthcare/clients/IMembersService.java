package org.springframework.security.oauth.api.service.healthcare.clients;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.security.oauth.api.model.healthcare.clients.Activation;
import org.springframework.security.oauth.api.model.healthcare.clients.Balance;
import org.springframework.security.oauth.api.model.healthcare.clients.Cardreprint;
import org.springframework.security.oauth.api.model.healthcare.clients.Categorychange;
import org.springframework.security.oauth.api.model.healthcare.clients.Deactivation;
import org.springframework.security.oauth.api.model.healthcare.clients.Fingerprintremoval;
import org.springframework.security.oauth.api.model.healthcare.clients.Member;
import org.springframework.security.oauth.api.model.healthcare.clients.MoneyAddition;
import org.springframework.security.oauth.api.model.healthcare.clients.MoneyReduction;
import org.springframework.security.oauth.api.model.healthcare.clients.Renewal;





public interface IMembersService {

	public List<Member> getMembers(String customerid, String country, int startindex, int maxresults, int status, String restrict,  String orderby);
	public List<Member> getSearchMembers(String q, String customerid, String country, int startindex, int maxresults, int status, String restrict,  String orderby);
	public Member getMember(String id, String customerid, String country);
	public String addMember(Member member, String medical_allocation_cover,  String staff_number, String customerid, String country);
	public int addMemberReturns(Member member, String customerid, String country);
	public String addMember(Member member, String id);
	public void updateMember(String id, String customerid, String country);
	public String deleteMember(String id, String customerid, String country);
	public void updateSwitchedMember(String id, String customerid, String country);
	public Balance getMemberBalance(String text_code, String phonenos, String memnos);
	
	
	
	
	public String addActivateMember(Activation activation, String customerid, String country);
	public List<Activation> getActivateMember(String customerid, String country, int startindex, int maxresults, String restrict, String orderby);
	public int updateActivationsMember(int id, Activation activation, String customerid, String country);
	public int deleteActivationsMember(int id, String customerid, String country);
	public String addDeactivateMember(Deactivation deactivation, String customerid, String country);
	public List<Deactivation> getDeactivationsMembers(String customerid, String country, int startindex, int maxresults, String restrict, String orderby);
	public int updateDeactivationsMember(int id, Deactivation deactivation, String customerid, String country);
	public int deleteDeactivationsMember(int id, String customerid, String country);
    public int addRenewalsMember(Renewal renewal, String customerid, String country);
	public List<Renewal> getRenewalsMembers(String customerid, String country, int startindex, int maxresults, String restrict, String orderby);
	public int updateRenewalsMember(int id, Renewal renewal, String customerid, String country);
	public int deleteRenewalsMember(int id, String customerid, String country);
	public String addCategorychangeMember(Categorychange categorychange, String customerid, String country);
	public List<Categorychange> getCategorychangeMembers(String customerid, String country, int startindex, int maxresults, String restrict, String orderby);
	public int updateCategorychangeMember(int id, Categorychange categorychange, String customerid, String country);
	public int deleteCategorychangeMember(int id, String customerid, String country);
	public String addFingerprintremovalMember(Fingerprintremoval fingerprintremoval, String customerid, String country);
	public List<Fingerprintremoval> getFingerprintremovalMembers(String customerid, String country, int startindex, int maxresults, String restrict, String orderby);
	public int updateFingerprintremovalMember(int id, Fingerprintremoval fingerprintremoval, String customerid, String country);
	public int deleteFingerprintremovalMember(int id, String customerid, String country);
	public String addMoneyadditionMember(MoneyAddition moneyAddition, String customerid, String country);
	public List<MoneyAddition> getMoneyadditionMembers(String customerid, String country, int startindex, int maxresults, String restrict, String orderby);
	public int updateMoneyadditionMember(int id, MoneyReduction moneyReduction, String customerid, String country);
	public int deleteMoneyadditionMember(int id, String customerid, String country);
	public String addMoneyreductionMember(MoneyReduction moneyReduction, String customerid, String country);
	public List<MoneyReduction> getMoneyreductionMembers(String customerid, String country, int startindex, int maxresults, String restrict, String orderby);
	public int updateMoneyreductionMember(int id, MoneyReduction moneyReduction, String customerid, String country);
	public int deleteMoneyreductionMember(int id, String customerid, String country);
	public String updateMember(String id, Member member,  String medical_allocation_cover, String staff_number, String customerid, String country);
	public List<Member> getMembersChanges(String customerid, String country, int startindex, int maxresults, int status, String restrict, String orderby);
	public Member getMemberChanges(String id, String customerid, String country);
	public String addCardreprintsMember(Cardreprint cardreprint, String customerid, String country);
	public Balance updateMemberActivate(String phoneno);
	public Balance updateMembeReject(String phoneno);
	public Balance updateMembeDeactivate(String phoneno);


	
}
