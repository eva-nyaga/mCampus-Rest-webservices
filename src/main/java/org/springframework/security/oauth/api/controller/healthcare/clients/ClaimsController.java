
package org.springframework.security.oauth.api.controller.healthcare.clients;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth.api.model.healthcare.clients.ApiMessage;
import org.springframework.security.oauth.api.model.healthcare.clients.Claim;
import org.springframework.security.oauth.api.model.healthcare.clients.ClaimsWrapper;
import org.springframework.security.oauth.api.service.healthcare.clients.IClaimsService;
import org.springframework.security.oauth.api.model.healthcare.clients.ClaimService;
import org.springframework.security.oauth.api.model.healthcare.clients.ClaimServicesWrapper;
import org.springframework.security.oauth.api.service.healthcare.clients.IClaimServicesService;
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
public class ClaimsController {

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
	private IClaimsService claimsService;
	@Autowired
	private IClaimServicesService claimservicesService;


	/*
	######################################################################################################
	                           Start of CLAIMS
	#####################################################################################################
	 */

	@RequestMapping(value = "/claims", method = RequestMethod.GET)
	public ModelAndView getClaims(
			@RequestParam(value = "customerid") String customerid,
			@RequestParam(value = "country") String country,
			@RequestParam(value = "startindex", defaultValue = "1") int startindex,
			@RequestParam(value = "maxresults", defaultValue = "30") int maxresults,
			@RequestParam(value = "status", defaultValue = "0") int status,
			@RequestParam(value = "restrict", defaultValue = "") String restrict,
			@RequestParam(value = "orderby", defaultValue = "transaction_date asc") String orderby
			) {
		
		List<Claim> claims = claimsService.getClaims(customerid, country, startindex, maxresults, status, restrict, orderby);
		return SpringMVCUtils.getOutputModel(ClaimsWrapper.createNew(claims));
	}
	
	
	@RequestMapping(value = "/claims/{id}", method = RequestMethod.GET)
	public ModelAndView getClaimById(
			@PathVariable(value = "id") String id,
			@RequestParam(value = "customerid") String customerid,
			@RequestParam(value = "country") String country
			) {
		
			Claim p = claimsService.getClaim(id, customerid, country);
			return SpringMVCUtils.getOutputModel(p);
	}


	@RequestMapping(value = "/claimservices", method = RequestMethod.GET)
	public ModelAndView getClaimServices(
			@RequestParam(value = "customerid") String customerid,
			@RequestParam(value = "country") String country,
			@RequestParam(value = "startindex", defaultValue = "1") int startindex,
			@RequestParam(value = "maxresults", defaultValue = "30") int maxresults,
			@RequestParam(value = "status", defaultValue = "0") int status,
			@RequestParam(value = "restrict", defaultValue = "") String restrict,
			@RequestParam(value = "orderby", defaultValue = "claim_id asc") String orderby
			) {
		
		List<ClaimService> claimservices = claimservicesService.getClaimServices(customerid, country, startindex, maxresults, status, restrict, orderby);
		return SpringMVCUtils.getOutputModel(ClaimServicesWrapper.createNew(claimservices));
	}
	
	
	@RequestMapping(value = "/claimservices/{id}", method = RequestMethod.GET)
	public ModelAndView getClaimServicesById(
			@PathVariable(value = "id") String id,
			@RequestParam(value = "customerid") String customerid,
			@RequestParam(value = "country") String country
			) {
		
		ClaimService claimservices = claimservicesService.getClaimService(id, customerid, country);
		return SpringMVCUtils.getOutputModel(claimservices);
	}
	
	

	@RequestMapping(value = "/claimservices/claims/{id}", method = RequestMethod.GET)
	public ModelAndView getClaimServicesByClaimsId(
			@PathVariable(value = "id") String id,
			@RequestParam(value = "customerid") String customerid,
			@RequestParam(value = "country") String country
			) {
		
		List<ClaimService> claimservices = claimservicesService.getClaimServiceClaimId(id, customerid, country);
		return SpringMVCUtils.getOutputModel(ClaimServicesWrapper.createNew(claimservices));
	}

	@RequestMapping(value = "/claims/search", method = { RequestMethod.POST,
			RequestMethod.GET })
	public ModelAndView getClaimsByName(
			@RequestParam(value = "q") String q,
			@RequestParam(value = "customerid") String customerid,
			@RequestParam(value = "country") String country,
			@RequestParam(value = "startindex", defaultValue = "1") int startindex,
			@RequestParam(value = "maxresults", defaultValue = "30") int maxresults,
			@RequestParam(value = "status", defaultValue = "0") int status,
			@RequestParam(value = "restrict", defaultValue = "") String restrict,
			@RequestParam(value = "orderby", defaultValue = "transaction_date asc") String orderby
			) {
		List<Claim> claims = claimsService.getSearchClaims(q, customerid, country, startindex, maxresults, status, restrict, orderby);
		return SpringMVCUtils.getOutputModel(ClaimsWrapper.createNew(claims));
	}
	
	

	@RequestMapping(value = "/claims/returns", method = RequestMethod.POST)
	public ModelAndView addClaimReturns(
			Claim claim,
			@RequestParam(value = "customerid") String customerid,
			@RequestParam(value = "country") String country
			) {

			String id = claimsService.addClaimReturns(claim, customerid, country);
			     return SpringMVCUtils.getOutputModel(new ApiMessage(
			  		"Claim added with id=" + id));
	}
	
	/**
	 * Updates a stored claim.
	 * 
	 * @param id
	 * @param claim
	 *            the new claim object.
	 * @return A claim object in a ModelAndView if successful, or an ApiMessage
	 *         in the ModelAndView if an error occured.
	 */

	@RequestMapping(value = "/claims/switched/{id}", method = RequestMethod.PUT)
	public ModelAndView updateSwitchedClaimById(
			@PathVariable(value = "id") String claimid,
			@RequestParam(value = "customerid") String customerid,
			@RequestParam(value = "country") String country
			) {
		
		String id = "";
		id = claimsService.updateSwitchedClaim(claimid, customerid, country);
		return SpringMVCUtils.getOutputModel(new ApiMessage("Claim with id "+id+" successfully switched."));
	}
	
	
	@RequestMapping(value = "/claims/switched", method = RequestMethod.PUT)
	public ModelAndView updateMultipleSwitchedClaimById(
			@RequestParam(value = "id") String claimids,
			@RequestParam(value = "customerid") String customerid,
			@RequestParam(value = "country") String country
			) {

		String ids = "";
		ids = claimsService.updateSwitchedClaims(claimids, customerid, country);
		return SpringMVCUtils.getOutputModel(new ApiMessage("Claim with ids "+ids+" successfully switched."));
	}

	
	
	
	/**
	 * 
	 * @param claim
	 *            the claim object built from the request body.
	 * @return a ModelAndView containing a single claim object. Or an
	 *         ApiMessage object if the claim was not valid, eg if name was
	 *         missing.
	 * @throws ApiException
	 *             if the claim is not valid.
	 */


	@RequestMapping(value = "/claims", method = RequestMethod.POST)
	public ModelAndView addClaim(
			Claim claim,
			@RequestParam(value = "customerid") String customerid,
			@RequestParam(value = "country") String country
			) {

			String id = claimsService.addClaim(claim, customerid, country);
			return SpringMVCUtils.getOutputModel(new ApiMessage(
					"Claim added with id=" + id));
	}
	

	@RequestMapping(value = "/claims/{id}", method = RequestMethod.DELETE)
	public ModelAndView deleteClaimById(
			@PathVariable(value = "id") String id,
			@RequestParam(value = "customerid") String customerid,
		    @RequestParam(value = "country") String country
			) {

			claimsService.deleteClaim(id, customerid, country);
			return SpringMVCUtils.getOutputModel(new ApiMessage("Claim with id "+id+" successfully deleted."));
	}

	
	/*
	######################################################################################################
	                           End of CLAIMS
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
