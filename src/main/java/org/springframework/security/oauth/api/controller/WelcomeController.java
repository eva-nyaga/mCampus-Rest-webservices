package org.springframework.security.oauth.api.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.security.oauth.api.model.healthcare.clients.MyUser;
import org.springframework.security.oauth.api.model.healthcare.clients.Reply;
import org.springframework.security.oauth.api.service.healthcare.clients.IPeopleService;
import org.springframework.security.oauth.api.utils.SpringMVCUtils;
import org.springframework.security.oauth.ussd.model.UssdRequest;
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


@Controller
/**
 * This class has the logic for acting on web requests.
 * 
 * @author Eva
 *
 */
public class WelcomeController {

	@Autowired
	private IPeopleService peopleService;




	@RequestMapping(value = "/hello")
	public ResponseEntity<String> hello() {
		return new ResponseEntity<String>("<h1>Hello!</h1>", HttpStatus.OK);
	}

	@RequestMapping(value = "/welcome", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ResponseEntity<String> welcomeGET(
			@RequestParam(required = false, value = "name") String name) {

		String welcome = "";
		if (name != null && name.length() > 0) {
			welcome = "<h1>Welcome " + name + "!</h1>";
		} else {
			welcome = "Welcome!";
		}

		return new ResponseEntity<String>(welcome, HttpStatus.OK);
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET, produces = "application/json")
	public ModelAndView getMemberDeactivations(
			@RequestParam(value = "stdId") String stdId,
			@RequestParam(value = "password") String password,
                        @RequestParam(value = "database") String database
			) {

                        peopleService.fetchDatabase(database);
                      List<MyUser>  p = peopleService.fetchUserDetails(stdId, password);
                return SpringMVCUtils.getOutputModel(p);
        
        }
        
  
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
