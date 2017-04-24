package org.springframework.security.oauth.api.service.healthcare.clients;

import java.util.List;
import java.util.Map;

import org.springframework.security.oauth.api.model.healthcare.clients.Login;

import org.springframework.security.oauth.api.model.healthcare.clients.MyUser;
import org.springframework.security.oauth.api.model.healthcare.clients.Reply;
import org.springframework.security.oauth.ussd.model.UssdRequest;




public interface IPeopleService {
	
	public Map authLogin(String username, String Password);
        public List<MyUser> fetchUserDetails(String username, String password);
        public void fetchDatabase(String database);
       
}
