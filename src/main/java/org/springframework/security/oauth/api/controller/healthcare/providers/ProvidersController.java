
package org.springframework.security.oauth.api.controller.healthcare.providers;

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
import org.springframework.security.oauth.api.model.healthcare.providers.Provider;
import org.springframework.security.oauth.api.model.healthcare.providers.ProvidersWrapper;
import org.springframework.security.oauth.api.service.healthcare.providers.IProvidersService;
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
public class ProvidersController {

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
	private IProvidersService providersService;



	/*
	######################################################################################################
	                           Start of PROVIDERS
	#####################################################################################################
	 */

	@RequestMapping(value = "/providers", method = RequestMethod.GET)
	public ModelAndView getProviders(
			@RequestParam(value = "customerid") String customerid,
			@RequestParam(value = "country") String country,
			@RequestParam(value = "startindex", defaultValue = "1") int startindex,
			@RequestParam(value = "maxresults", defaultValue = "30") int maxresults,
			@RequestParam(value = "status", defaultValue = "0") int status,
			@RequestParam(value = "restrict", defaultValue = "") String restrict,
			@RequestParam(value = "orderby", defaultValue = "insert_date asc") String orderby
			) {
		
		List<Provider> providers = providersService.getProviders(customerid, country, startindex, maxresults, status, restrict, orderby);
		return SpringMVCUtils.getOutputModel(ProvidersWrapper.createNew(providers));
	}
	
	
	@RequestMapping(value = "/providers/defaults", method = RequestMethod.GET)
	public ModelAndView getProvidersDefault(
			@RequestParam(value = "customerid") String customerid,
			@RequestParam(value = "country") String country,
			@RequestParam(value = "startindex", defaultValue = "1") int startindex,
			@RequestParam(value = "maxresults", defaultValue = "30") int maxresults,
			@RequestParam(value = "status", defaultValue = "0") int status,
			@RequestParam(value = "restrict", defaultValue = "") String restrict,
			@RequestParam(value = "orderby", defaultValue = "insert_date asc") String orderby
			) {
		
		List<Provider> providers = providersService.getProvidersDefault(customerid, country, startindex, maxresults, status, restrict, orderby);
		return SpringMVCUtils.getOutputModel(ProvidersWrapper.createNew(providers));
	}
	
	
	
	@RequestMapping(value = "/providers/{id}", method = RequestMethod.GET)
	public ModelAndView getProviderById(
			@PathVariable(value = "id") String id,
			@RequestParam(value = "customerid") String customerid,
			@RequestParam(value = "country") String country
			) {
		
			Provider p = providersService.getProvider(id, customerid, country);
			return SpringMVCUtils.getOutputModel(p);
	}
	


	@RequestMapping(value = "/providers/search", method = { RequestMethod.POST,
			RequestMethod.GET })
	public ModelAndView getProvidersByName(
			@RequestParam(value = "q") String q,
			@RequestParam(value = "customerid") String customerid,
			@RequestParam(value = "country") String country,
			@RequestParam(value = "startindex", defaultValue = "1") int startindex,
			@RequestParam(value = "maxresults", defaultValue = "30") int maxresults,
			@RequestParam(value = "status", defaultValue = "0") int status,
			@RequestParam(value = "restrict", defaultValue = "") String restrict,
			@RequestParam(value = "orderby", defaultValue = "insert_date asc") String orderby
			) {
		List<Provider> providers = providersService.getSearchProviders(q, customerid, country, startindex, maxresults, status, restrict, orderby);
		return SpringMVCUtils.getOutputModel(ProvidersWrapper.createNew(providers));
	}
	
	

	@RequestMapping(value = "/providers/returns", method = RequestMethod.POST)
	public ModelAndView addProviderReturns(
			Provider provider,
			@RequestParam(value = "customerid") String customerid,
			@RequestParam(value = "country") String country
			) {

			String id = providersService.addProviderReturns(provider, customerid, country);
			     return SpringMVCUtils.getOutputModel(new ApiMessage(
			  		"Provider added with id=" + id));
	}
	
	/**
	 * Updates a stored provider.
	 * 
	 * @param id
	 * @param provider
	 *            the new provider object.
	 * @return A provider object in a ModelAndView if successful, or an ApiMessage
	 *         in the ModelAndView if an error occured.
	 */
	@RequestMapping(value = "/providers/{id}", method = RequestMethod.PUT)
	public ModelAndView updateProviderById(
			@PathVariable(value = "id") String id,
			@RequestParam(value = "customerid") String customerid,
			@RequestParam(value = "country") String country
			) {

		//provider.setProviderProviderId(id);
		providersService.updateProvider(id, customerid, country);

		return SpringMVCUtils.getOutputModel(new ApiMessage("Provider with id "+id+" successfully updated."));
	}
	
	
	
	
	

	
	
	@RequestMapping(value = "/providers/switched", method = RequestMethod.PUT)
	public ModelAndView updateSwitchedProviderById(
			@RequestParam(value = "id") String id,
			@RequestParam(value = "customerid") String customerid,
			@RequestParam(value = "country") String country
			) {

		//provider.setProviderProviderId(id);
		System.out.println("THIS IS THE PROVIDER THAT IS GOING TO BE SWITCHED "+id);
		
		//providersService.updateSwitchedProvider(id, customerid, country);

		return SpringMVCUtils.getOutputModel(new ApiMessage("Provider with id "+id+" successfully added."));
	}

	
	
	
	/**
	 * 
	 * @param provider
	 *            the provider object built from the request body.
	 * @return a ModelAndView containing a single provider object. Or an
	 *         ApiMessage object if the provider was not valid, eg if name was
	 *         missing.
	 * @throws ApiException
	 *             if the provider is not valid.
	 */


	@RequestMapping(value = "/providers", method = RequestMethod.POST)
	public ModelAndView addProvider(
			Provider provider,
			@RequestParam(value = "customerid") String customerid,
			@RequestParam(value = "country") String country
			) {

			String id = providersService.addProvider(provider, customerid, country);
			return SpringMVCUtils.getOutputModel(new ApiMessage("Provider added with id "+id+""));
	}
	

	@RequestMapping(value = "/providers/{id}", method = RequestMethod.DELETE)
	public ModelAndView deleteProviderById(
			@PathVariable(value = "id") String id,
			@RequestParam(value = "customerid") String customerid,
		    @RequestParam(value = "country") String country
			) {

			providersService.deleteProvider(id, customerid, country);

			return SpringMVCUtils.getOutputModel(new ApiMessage("Provider with id "+id+" successfully deleted."));
	}

	
	/*
	######################################################################################################
	                           End of PROVIDERS
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
