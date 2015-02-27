package org.springframework.security.oauth.api.controller.healthcare.clients;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth.api.model.healthcare.clients.Activation;
import org.springframework.security.oauth.api.model.healthcare.clients.ActivationsWrapper;
import org.springframework.security.oauth.api.model.healthcare.clients.ApiMessage;
import org.springframework.security.oauth.api.model.healthcare.clients.Balance;
import org.springframework.security.oauth.api.model.healthcare.clients.Cardreprint;
import org.springframework.security.oauth.api.model.healthcare.clients.Categorychange;
import org.springframework.security.oauth.api.model.healthcare.clients.CategorychangesWrapper;
import org.springframework.security.oauth.api.model.healthcare.clients.Deactivation;
import org.springframework.security.oauth.api.model.healthcare.clients.DeactivationsWrapper;
import org.springframework.security.oauth.api.model.healthcare.clients.Fingerprintremoval;
import org.springframework.security.oauth.api.model.healthcare.clients.FingerprintremovalsWrapper;
import org.springframework.security.oauth.api.model.healthcare.clients.Member;
import org.springframework.security.oauth.api.model.healthcare.clients.MembersWrapper;
import org.springframework.security.oauth.api.model.healthcare.clients.MoneyAddition;
import org.springframework.security.oauth.api.model.healthcare.clients.MoneyAdditionsWrapper;
import org.springframework.security.oauth.api.model.healthcare.clients.MoneyReduction;
import org.springframework.security.oauth.api.model.healthcare.clients.MoneyReductionsWrapper;
import org.springframework.security.oauth.api.model.healthcare.clients.Renewal;
import org.springframework.security.oauth.api.model.healthcare.clients.RenewalsWrapper;
import org.springframework.security.oauth.api.service.healthcare.clients.IMembersService;
import org.springframework.security.oauth.api.utils.SpringMVCUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;





import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.Collection;
import java.util.Iterator;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
/**
 * This class has the logic for acting on web requests.
 * 
 * @author Mulama
 *
 */
public class MembersController {

	/**
	 * A service that provides operations around managing people. Spring will
	 * automatically wire in an implementation of the IPeopleService interface.
	 * Since the only implementation is the PeopleService class, this will be
	 * used. The autowiring is done when the controller is loaded, which happens
	 * when the line <br />
	 * {@code <context:component-scan base-package="org.springframework.security.oauth" />}
	 * <br />
	 * is run in the spring-servlet.xml.
	 */
	@Autowired
	private IMembersService membersService;



	/*
	######################################################################################################
	                           Start of MEMBERS
	#####################################################################################################
	 */
		
	@RequestMapping(value = "/members/balance", method = RequestMethod.GET)
	public ModelAndView getMemberBalance(
			@RequestParam(value = "text_code", defaultValue = "0") String text_code,
			@RequestParam(value = "phoneno", defaultValue = "0") String phoneno,
			@RequestParam(value = "memberno", defaultValue = "0") String memberno
			) {

		Balance details = membersService.getMemberBalance(text_code, phoneno, memberno);
		return SpringMVCUtils.getOutputModel(details);
	}
	
	@RequestMapping(value = "/members/sms/activate", method = RequestMethod.PUT)
	public ModelAndView getMemberActivate(
			@RequestParam(value = "phoneno", defaultValue = "0") String phoneno
			) {

		Balance details = membersService.updateMemberActivate(phoneno);
		return SpringMVCUtils.getOutputModel(details);
	}
	
	@RequestMapping(value = "/members/sms/reject", method = RequestMethod.PUT)
	public ModelAndView getMemberReject(
			@RequestParam(value = "phoneno", defaultValue = "0") String phoneno
			) {

		Balance details = membersService.updateMembeReject(phoneno);
		return SpringMVCUtils.getOutputModel(details);
	}
	
	@RequestMapping(value = "/members/sms/deactivate", method = RequestMethod.PUT)
	public ModelAndView getMemberDeactivate(
			@RequestParam(value = "phoneno", defaultValue = "0") String phoneno
			) {

		Balance details = membersService.updateMembeDeactivate(phoneno);
		return SpringMVCUtils.getOutputModel(details);
	}
	

	@RequestMapping(value = "/members/activations", method = RequestMethod.POST)
	public ModelAndView activateMember(
			Activation activation,
			@RequestParam(value = "customerid") String customerid,
			@RequestParam(value = "country") String country
			) {

			String id = membersService.addActivateMember(activation, customerid, country);
			return SpringMVCUtils.getOutputModel(new ApiMessage(
					"Activation added with id=" + id));
	}
	
	@RequestMapping(value = "/members/activations", method = RequestMethod.GET)
	public ModelAndView getMemberActivations(
			@RequestParam(value = "customerid") String customerid,
			@RequestParam(value = "country") String country,
			@RequestParam(value = "startindex", defaultValue = "1") int startindex,
			@RequestParam(value = "maxresults", defaultValue = "30") int maxresults,
			@RequestParam(value = "restrict", defaultValue = "") String restrict,
			@RequestParam(value = "orderby", defaultValue = "insert_date asc") String orderby
			) {
		
		List<Activation> activations = membersService.getActivateMember(customerid, country, startindex, maxresults, restrict, orderby);
		return SpringMVCUtils.getOutputModel(ActivationsWrapper.createNew(activations));
	}
	
	
	@RequestMapping(value = "/members/activations/{id}", method = RequestMethod.PUT)
	public ModelAndView updateActivationsMemberById(
			@PathVariable(value = "id") int id,
			Activation activation,
			@RequestParam(value = "customerid") String customerid,
			@RequestParam(value = "country") String country
			) {

	    id = membersService.updateActivationsMember(id, activation, customerid, country);	
		return SpringMVCUtils.getOutputModel(new ApiMessage("Activation with id "+id+" successfully updated."));
	}
	
	
		@RequestMapping(value = "/members/activations/{id}", method = RequestMethod.DELETE)
	public ModelAndView deleteActivationsById(
			@PathVariable(value = "id") int id,
			@RequestParam(value = "customerid") String customerid,
			@RequestParam(value = "country") String country
			) {

			id = membersService.deleteActivationsMember(id, customerid, country);
			return SpringMVCUtils.getOutputModel(new ApiMessage("Activation with id "+id+" successfully deleted."));
	}
	
	@RequestMapping(value = "/members/deactivations", method = RequestMethod.POST)
	public ModelAndView deactivateMember(
			Deactivation deactivation,
			@RequestParam(value = "customerid") String customerid,
			@RequestParam(value = "country") String country
			) {

			String id = membersService.addDeactivateMember(deactivation, customerid, country);
			return SpringMVCUtils.getOutputModel(new ApiMessage(
					"Deactivation added with id=" + id));
	}
	
	@RequestMapping(value = "/members/deactivations", method = RequestMethod.GET)
	public ModelAndView getMemberDeactivations(
			@RequestParam(value = "customerid") String customerid,
			@RequestParam(value = "country") String country,
			@RequestParam(value = "startindex", defaultValue = "1") int startindex,
			@RequestParam(value = "maxresults", defaultValue = "30") int maxresults,
			@RequestParam(value = "restrict", defaultValue = "") String restrict,
			@RequestParam(value = "orderby", defaultValue = "insert_date asc") String orderby
			) {
		
		List<Deactivation> deactivations = membersService.getDeactivationsMembers(customerid, country, startindex, maxresults, restrict, orderby);
		return SpringMVCUtils.getOutputModel(DeactivationsWrapper.createNew(deactivations));
	}
	
	@RequestMapping(value = "/members/deactivations/{id}", method = RequestMethod.PUT)
	public ModelAndView updateDeactivationsMemberById(
			@PathVariable(value = "id") int id,
			Deactivation deactivation,
			@RequestParam(value = "customerid") String customerid,
			@RequestParam(value = "country") String country
			) {

		id = membersService.updateDeactivationsMember(id, deactivation, customerid, country);		
		return SpringMVCUtils.getOutputModel(new ApiMessage("Deactivation with id "+id+" successfully updated."));
	}
	
	
	@RequestMapping(value = "/members/deactivations/{id}", method = RequestMethod.DELETE)
	public ModelAndView deleteDeactivationsById(
			@PathVariable(value = "id") int id,
			@RequestParam(value = "customerid") String customerid,
			@RequestParam(value = "country") String country
			) {

			id = membersService.deleteDeactivationsMember(id, customerid, country);
			return SpringMVCUtils.getOutputModel(new ApiMessage("Deactivation with id "+id+" successfully deleted."));
	}


	@RequestMapping(value = "/members/cardreprints", method = RequestMethod.POST)
	public ModelAndView addMember(
			Cardreprint  cardreprint,
			@RequestParam(value = "customerid") String customerid,
			@RequestParam(value = "country") String country
			) {

		String id = membersService.addCardreprintsMember(cardreprint, customerid, country);
		return SpringMVCUtils.getOutputModel(new ApiMessage(
				"Cardreprint added with id=" + customerid));
	}
	
	/*
	@RequestMapping(value = "/members/cardreprints", method = RequestMethod.GET)
	public ModelAndView getMemberCardreprints(
			@RequestParam(value = "customerid") String customerid,
			@RequestParam(value = "country") String country,
			@RequestParam(value = "startindex", defaultValue = "1") int startindex,
			@RequestParam(value = "maxresults", defaultValue = "30") int maxresults,
			@RequestParam(value = "restrict", defaultValue = "") String restrict,
			@RequestParam(value = "orderby", defaultValue = "insert_date asc") String orderby
			) {
		
		List<Cardreprint> cardreprints = membersService.getCardreprintsMembers(customerid, country, startindex, maxresults, restrict, orderby);
		return SpringMVCUtils.getOutputModel(CardreprintsWrapper.createNew(cardreprints));
	}
	
	
	@RequestMapping(value = "/members/cardreprints/{id}", method = RequestMethod.PUT)
	public ModelAndView updateCardreprintsMemberById(
			@PathVariable(value = "id") int id,
			Cardreprint  cardreprint,
			@RequestParam(value = "customerid") String customerid,
			@RequestParam(value = "country") String country
			) {

		id = membersService.updateCardreprintsMember(id, cardreprint, customerid, country);	
		return SpringMVCUtils.getOutputModel(new ApiMessage("Cardreprint with id "+id+" successfully updated."));
	}
	
	
	@RequestMapping(value = "/members/cardreprints/{id}", method = RequestMethod.DELETE)
	public ModelAndView deleteCardreprintsById(
			@PathVariable(value = "id") int id,
			@RequestParam(value = "customerid") String customerid,
		    @RequestParam(value = "country") String country
			) {

			id = membersService.deleteCardreprintsMember(id, customerid, country);
			return SpringMVCUtils.getOutputModel(new ApiMessage("Cardreprint with id "+id+" successfully deleted."));
	}
*/
	
	@RequestMapping(value = "/members/renewals", method = RequestMethod.POST)
	public ModelAndView renewalsMember(
			Renewal renewal,
			@RequestParam(value = "customerid") String customerid,
			@RequestParam(value = "country") String country
			) {

			int id = membersService.addRenewalsMember(renewal, customerid, country);
			return SpringMVCUtils.getOutputModel(new ApiMessage(
					"Renewal added with id=" + id));
	}
	
	@RequestMapping(value = "/members/renewals", method = RequestMethod.GET)
	public ModelAndView getMemberRenewals(
			@RequestParam(value = "customerid") String customerid,
			@RequestParam(value = "country") String country,
			@RequestParam(value = "startindex", defaultValue = "1") int startindex,
			@RequestParam(value = "maxresults", defaultValue = "30") int maxresults,
			@RequestParam(value = "restrict", defaultValue = "") String restrict,
			@RequestParam(value = "orderby", defaultValue = "transaction_date asc") String orderby
			) {
		
		List<Renewal> renewals = membersService.getRenewalsMembers(customerid, country, startindex, maxresults, restrict, orderby);
		return SpringMVCUtils.getOutputModel(RenewalsWrapper.createNew(renewals));
	}
	
	@RequestMapping(value = "/members/renewals/{id}", method = RequestMethod.PUT)
	public ModelAndView updateRenewalsMemberById(
			@PathVariable(value = "id") int id,
			Renewal renewal,
			@RequestParam(value = "customerid") String customerid,
			@RequestParam(value = "country") String country
			) {
		
		id = membersService.updateRenewalsMember(id, renewal, customerid, country);		
		return SpringMVCUtils.getOutputModel(new ApiMessage("Renewal with id "+id+" successfully updated."));
	}
	
	
		@RequestMapping(value = "/members/renewals/{id}", method = RequestMethod.DELETE)
	public ModelAndView deleteRenewalsById(
			@PathVariable(value = "id") int id,
			@RequestParam(value = "customerid") String customerid,
		    @RequestParam(value = "country") String country
			) {

			id = membersService.deleteRenewalsMember(id, customerid, country);
			return SpringMVCUtils.getOutputModel(new ApiMessage("Renewal with id "+id+" successfully deleted."));
	}
		
	@RequestMapping(value = "/members/categorychange", method = RequestMethod.POST)
	public ModelAndView categorychangeMember(
			Categorychange categorychange,		
			@RequestParam(value = "customerid") String customerid,
			@RequestParam(value = "country") String country
			) {

			String id = membersService.addCategorychangeMember(categorychange, customerid, country);
			return SpringMVCUtils.getOutputModel(new ApiMessage(
					"Categorychange added with id=" + id));
	}
	
	@RequestMapping(value = "/members/categorychange", method = RequestMethod.GET)
	public ModelAndView getMemberCategorychange(
			@RequestParam(value = "customerid") String customerid,
			@RequestParam(value = "country") String country,
			@RequestParam(value = "startindex", defaultValue = "1") int startindex,
			@RequestParam(value = "maxresults", defaultValue = "30") int maxresults,
			@RequestParam(value = "restrict", defaultValue = "") String restrict,
			@RequestParam(value = "orderby", defaultValue = "transaction_date asc") String orderby
			) {
		
		List<Categorychange> categorychanges = membersService.getCategorychangeMembers(customerid, country, startindex, maxresults, restrict, orderby);
		return SpringMVCUtils.getOutputModel(CategorychangesWrapper.createNew(categorychanges));
	}
	
	@RequestMapping(value = "/members/categorychange/{id}", method = RequestMethod.PUT)
	public ModelAndView updateCategorychangeMemberById(
			@PathVariable(value = "id") int id,
			Categorychange categorychange,	
			@RequestParam(value = "customerid") String customerid,
			@RequestParam(value = "country") String country
			) {

		id = membersService.updateCategorychangeMember(id, categorychange, customerid, country);		
		return SpringMVCUtils.getOutputModel(new ApiMessage("Categorychange with id "+id+" successfully updated."));
	}
	
	
		@RequestMapping(value = "/members/categorychange/{id}", method = RequestMethod.DELETE)
	public ModelAndView deleteCategorychangeById(
			@PathVariable(value = "id") int id,
			@RequestParam(value = "customerid") String customerid,
		    @RequestParam(value = "country") String country
			) {

			id = membersService.deleteCategorychangeMember(id, customerid, country);
			return SpringMVCUtils.getOutputModel(new ApiMessage("Categorychange with id "+id+" successfully deleted."));
	}
		
	@RequestMapping(value = "/members/fingerprintremoval", method = RequestMethod.POST)
	public ModelAndView fingerprintremovalMember(
			Fingerprintremoval fingerprintremoval,
			@RequestParam(value = "customerid") String customerid,
			@RequestParam(value = "country") String country
			) {

			String id = membersService.addFingerprintremovalMember(fingerprintremoval, customerid, country);
			return SpringMVCUtils.getOutputModel(new ApiMessage(
					"Fingerprint removal added with id=" + id));
	}
	
	@RequestMapping(value = "/members/fingerprintremoval", method = RequestMethod.GET)
	public ModelAndView getMemberFingerprintremoval(
			@RequestParam(value = "customerid") String customerid,
			@RequestParam(value = "country") String country,
			@RequestParam(value = "startindex", defaultValue = "1") int startindex,
			@RequestParam(value = "maxresults", defaultValue = "30") int maxresults,
			@RequestParam(value = "restrict", defaultValue = "") String restrict,
			@RequestParam(value = "orderby", defaultValue = "transaction_date asc") String orderby
			) {
		
		List<Fingerprintremoval> fingerprintremovals = membersService.getFingerprintremovalMembers(customerid, country, startindex, maxresults, restrict, orderby);
		return SpringMVCUtils.getOutputModel(FingerprintremovalsWrapper.createNew(fingerprintremovals));
	}
	
	@RequestMapping(value = "/members/fingerprintremoval/{id}", method = RequestMethod.PUT)
	public ModelAndView updateFingerprintremovalMemberById(
			@PathVariable(value = "id") int id,
			Fingerprintremoval fingerprintremoval,
			@RequestParam(value = "customerid") String customerid,
			@RequestParam(value = "country") String country
			) {
		
		id = membersService.updateFingerprintremovalMember(id, fingerprintremoval, customerid, country);	
		return SpringMVCUtils.getOutputModel(new ApiMessage("Fingerprint removal with id "+id+" successfully updated."));
	}
	
	
		@RequestMapping(value = "/members/fingerprintremoval/{id}", method = RequestMethod.DELETE)
	public ModelAndView deleteFingerprintremovalById(
			@PathVariable(value = "id") int id,
			@RequestParam(value = "customerid") String customerid,
		    @RequestParam(value = "country") String country
			) {

			id = membersService.deleteFingerprintremovalMember(id, customerid, country);
			return SpringMVCUtils.getOutputModel(new ApiMessage("Fingerprint removal with id "+id+" successfully deleted."));
	}
	
	@RequestMapping(value = "/members/moneyaddition", method = RequestMethod.POST)
	public ModelAndView moneyadditionMember(
            MoneyAddition moneyAddition,
			@RequestParam(value = "customerid") String customerid,
			@RequestParam(value = "country") String country
			
			) {

			String id = membersService.addMoneyadditionMember(moneyAddition, customerid, country);
			return SpringMVCUtils.getOutputModel(new ApiMessage(
					"Money addition added with id=" + id));
	}
	
	
	
	@RequestMapping(value = "/members/moneyaddition", method = RequestMethod.GET)
	public ModelAndView getMemberMoneyaddition(
			@RequestParam(value = "customerid") String customerid,
			@RequestParam(value = "country") String country,
			@RequestParam(value = "startindex", defaultValue = "1") int startindex,
			@RequestParam(value = "maxresults", defaultValue = "30") int maxresults,
			@RequestParam(value = "restrict", defaultValue = "") String restrict,
			@RequestParam(value = "orderby", defaultValue = "insert_date asc") String orderby
			) {
		
		List<MoneyAddition> moneyadditions = membersService.getMoneyadditionMembers(customerid, country, startindex, maxresults, restrict, orderby);
		return SpringMVCUtils.getOutputModel(MoneyAdditionsWrapper.createNew(moneyadditions));
	}
	
	
	@RequestMapping(value = "/members/moneyaddition/{id}", method = RequestMethod.PUT)
	public ModelAndView updateMoneyadditionMemberById(
			@PathVariable(value = "id") int id,
			MoneyReduction moneyReduction,
			@RequestParam(value = "customerid") String customerid,
			@RequestParam(value = "country") String country
			) {

		
		id = membersService.updateMoneyadditionMember(id, moneyReduction, customerid, country);	
		return SpringMVCUtils.getOutputModel(new ApiMessage("Money addition with id "+id+" successfully updated."));
	}
	
	
		@RequestMapping(value = "/members/moneyaddition/{id}", method = RequestMethod.DELETE)
	public ModelAndView deleteMoneyadditionById(
			@PathVariable(value = "id") int id,
			@RequestParam(value = "customerid") String customerid,
		    @RequestParam(value = "country") String country
			) {

			id = membersService.deleteMoneyadditionMember(id, customerid, country);
			return SpringMVCUtils.getOutputModel(new ApiMessage("Money addition with id "+id+" successfully deleted."));
	}
		
	@RequestMapping(value = "/members/moneyreduction", method = RequestMethod.POST)
	public ModelAndView moneyreductionMember(
			MoneyReduction moneyReduction,
			@RequestParam(value = "customerid") String customerid,
			@RequestParam(value = "country") String country
			) {

			String id = membersService.addMoneyreductionMember(moneyReduction, customerid, country);
			return SpringMVCUtils.getOutputModel(new ApiMessage(
					"Money reduction added with id=" + id));
	}
	
	@RequestMapping(value = "/members/moneyreduction", method = RequestMethod.GET)
	public ModelAndView getMemberMoneyreduction(
			@RequestParam(value = "customerid") String customerid,
			@RequestParam(value = "country") String country,
			@RequestParam(value = "startindex", defaultValue = "1") int startindex,
			@RequestParam(value = "maxresults", defaultValue = "30") int maxresults,
			@RequestParam(value = "restrict", defaultValue = "") String restrict,
			@RequestParam(value = "orderby", defaultValue = "insert_date asc") String orderby
			) {
		
		List<MoneyReduction> moneyreductions = membersService.getMoneyreductionMembers(customerid, country, startindex, maxresults, restrict, orderby);
		return SpringMVCUtils.getOutputModel(MoneyReductionsWrapper.createNew(moneyreductions));
	}
	
	@RequestMapping(value = "/members/moneyreduction/{id}", method = RequestMethod.PUT)
	public ModelAndView updateMoneyreductionMemberById(
			@PathVariable(value = "id") int id,
			MoneyReduction moneyReduction,
			@RequestParam(value = "customerid") String customerid,
			@RequestParam(value = "country") String country
			) {
		
		id = membersService.updateMoneyreductionMember(id, moneyReduction, customerid, country);	
		return SpringMVCUtils.getOutputModel(new ApiMessage("Money reduction with id "+id+" successfully updated."));
	}
	
	
		@RequestMapping(value = "/members/moneyreduction/{id}", method = RequestMethod.DELETE)
	public ModelAndView deleteMoneyreductionById(
			@PathVariable(value = "id") int id,
			@RequestParam(value = "customerid") String customerid,
		    @RequestParam(value = "country") String country
			) {

			id = membersService.deleteMoneyreductionMember(id, customerid, country);
			return SpringMVCUtils.getOutputModel(new ApiMessage("Money reduction with id "+id+" successfully deleted."));
	}
		
	/**
	 * 
	 * @param member
	 *            the member object built from the request body.
	 * @return a ModelAndView containing a single member object. Or an
	 *         ApiMessage object if the member was not valid, eg if name was
	 *         missing.
	 * @throws ApiException
	 *             if the member is not valid.
	 */


	@RequestMapping(value = "/members", method = RequestMethod.POST)
	public ModelAndView addMember(
			Member member,
			@RequestParam(value = "medicalAllocationCover") String medical_allocation_cover,
			@RequestParam(value = "staffNumber") String staff_number,
			@RequestParam(value = "customerid") String customerid,
			@RequestParam(value = "country") String country
			) {

			String id = membersService.addMember(member, medical_allocation_cover,  staff_number, customerid, country);

			return SpringMVCUtils.getOutputModel(new ApiMessage(
					"Member added with id=" + id));
	}
	
	@RequestMapping(value = "/members", method = RequestMethod.GET)
	public ModelAndView getMembers(
			@RequestParam(value = "customerid") String customerid,
			@RequestParam(value = "country") String country,
			@RequestParam(value = "startindex", defaultValue = "1") int startindex,
			@RequestParam(value = "maxresults", defaultValue = "30") int maxresults,
			@RequestParam(value = "status", defaultValue = "1") int status,
			@RequestParam(value = "restrict", defaultValue = "") String restrict,
			@RequestParam(value = "orderby", defaultValue = "join_date asc") String orderby
			) {
		  
		List<Member> members = membersService.getMembers(customerid, country, startindex, maxresults, status, restrict, orderby);
		return SpringMVCUtils.getOutputModel(MembersWrapper.createNew(members));
	}
	
	
	@RequestMapping(value = "/members/{id}", method = RequestMethod.GET)
	public ModelAndView getMemberById(
			@PathVariable(value = "id") String id,
			@RequestParam(value = "customerid") String customerid,
			@RequestParam(value = "country") String country
			) {
		
			Member p = membersService.getMember(id, customerid, country);
			return SpringMVCUtils.getOutputModel(p);
	}
	
	
	
	@RequestMapping(value = "/members/changes", method = RequestMethod.GET)
	public ModelAndView getMembersChanges(
			@RequestParam(value = "customerid") String customerid,
			@RequestParam(value = "country") String country,
			@RequestParam(value = "startindex", defaultValue = "1") int startindex,
			@RequestParam(value = "maxresults", defaultValue = "30") int maxresults,
			@RequestParam(value = "status", defaultValue = "0") int status,
			@RequestParam(value = "restrict", defaultValue = "") String restrict,
			@RequestParam(value = "orderby", defaultValue = "join_date asc") String orderby
			) {
		  
		List<Member> members = membersService.getMembersChanges(customerid, country, startindex, maxresults, status, restrict, orderby);
		return SpringMVCUtils.getOutputModel(MembersWrapper.createNew(members));
	}
	
	
	@RequestMapping(value = "/members/changes/{id}", method = RequestMethod.GET)
	public ModelAndView getMemberByChangesId(
			@PathVariable(value = "id") String id,
			@RequestParam(value = "customerid") String customerid,
			@RequestParam(value = "country") String country
			) {
		
			Member p = membersService.getMemberChanges(id, customerid, country);
			return SpringMVCUtils.getOutputModel(p);
	}


	@RequestMapping(value = "/members/search", method = { RequestMethod.POST,
			RequestMethod.GET })
	public ModelAndView getMembersByName(
			@RequestParam(value = "q") String q,
			@RequestParam(value = "customerid") String customerid,
			@RequestParam(value = "country") String country,
			@RequestParam(value = "startindex", defaultValue = "1") int startindex,
			@RequestParam(value = "maxresults", defaultValue = "30") int maxresults,
			@RequestParam(value = "status", defaultValue = "0") int status,
			@RequestParam(value = "restrict", defaultValue = "") String restrict,
			@RequestParam(value = "orderby", defaultValue = "join_date asc") String orderby
			) {
		List<Member> members = membersService.getSearchMembers(q, customerid, country, startindex, maxresults, status, restrict, orderby);
		return SpringMVCUtils.getOutputModel(MembersWrapper.createNew(members));
	}

	
	/**
	 * Updates a stored member.
	 * 
	 * @param id
	 * @param member
	 *            the new member object.
	 * @return A member object in a ModelAndView if successful, or an ApiMessage
	 *         in the ModelAndView if an error occured.
	 */
	@RequestMapping(value = "/members/{id}", method = RequestMethod.PUT)
	public ModelAndView updateMemberById(
			@PathVariable(value = "id") String id,
			Member member,
			@RequestParam(value = "medicalAllocationCover") String medical_allocation_cover,
			@RequestParam(value = "staffNumber") String staff_number,
			@RequestParam(value = "customerid") String customerid,
			@RequestParam(value = "country") String country
			) {

		id = membersService.updateMember(id, member, medical_allocation_cover,  staff_number, customerid, country);
		return SpringMVCUtils.getOutputModel(new ApiMessage("Member with id "+id+" successfully updated."));
	}

	
	@RequestMapping(value = "/members/{id}", method = RequestMethod.DELETE)
	public ModelAndView deleteMemberById(
			@PathVariable(value = "id") String id,
			@RequestParam(value = "customerid") String customerid,
		    @RequestParam(value = "country") String country
			) {

			id = membersService.deleteMember(id, customerid, country);
			return SpringMVCUtils.getOutputModel(new ApiMessage("Member with id "+id+" successfully deleted."));
	}
	
	
	/*
	######################################################################################################
	                           End of MEMBERS
	#####################################################################################################
	 */


	/**
	 * Handles exceptions of the class ApiException that are thrown by controller methods. Sets the response status code to 400 BAD_REQUEST.
	 * @param e
	 * @param response
	 * @return
	 */
	@ExceptionHandler(IllegalArgumentException.class)
	public ModelAndView handleApiException(IllegalArgumentException e,
			HttpServletResponse response) {

		// set the response status code to indicate the request was bad.
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		return SpringMVCUtils.getOutputModel(new ApiMessage(e.getMessage()));
	}
	
  

	/**
	 * Shows a more descriptive error message to the user when a submitted
	 * object fails validation.
	 * 
	 * @param e
	 *            the MethodArgumentNotValidException that we are handling.
	 * @return A ModelAndView containing a single ApiMessage object. We also
	 *         alter the response to have the status code 400.
	 */
	@ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
	public ModelAndView handleBindingException(
			MethodArgumentNotValidException e, HttpServletResponse response) {

		// Build a list of all the validation errors to show to the user.
		// WARNING this may not be a good idea on a production website because
		// it may expose internal details such as the fact you are using Java,
		// JSR-303, etc. A generic BAD_REQUEST error would probably be better.
		String errors = buildErrorString(e.getBindingResult().getAllErrors());

		// set the response status code to indicate the request was bad.
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		return SpringMVCUtils.getOutputModel(new ApiMessage(errors));
	}

	/**
	 * Concatenates the validation errors from the allErrors param into a single
	 * string for display to a user.
	 * 
	 * @param allErrors
	 * @return
	 */
	private String buildErrorString(List<ObjectError> allErrors) {
		StringBuilder b = new StringBuilder();

		b.append(allErrors.get(0));

		// append any remaining errors
		for (int i = 1; i < allErrors.size(); i++) {
			b.append("\n");
			b.append(String.format("%s - %s", allErrors.get(i).getObjectName(),
					allErrors.get(i).getDefaultMessage()));
		}

		return b.toString();
	}
}
