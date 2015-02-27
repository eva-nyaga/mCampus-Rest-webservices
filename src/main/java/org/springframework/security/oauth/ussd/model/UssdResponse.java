/**
 * 
 */
package org.springframework.security.oauth.ussd.model;

import java.util.HashMap;

/**
 * @author mulama
 *
 */
public class UssdResponse {

    private String Type;
    private String message;
    private String ClientState;
    private String session_id;
    private HashMap<Integer, String> accesslevels = new HashMap<Integer, String>();
    
    /**
     * 
     */
    public UssdResponse() {
    	
    }

    public UssdResponse(String session_id, String clientState,
			String message, String type, HashMap<Integer, String> accesslevels) {
		// TODO Auto-generated constructor stub
    	this.session_id = session_id;
    	this.ClientState = clientState;
    	this.message = message;
    	this.Type = type;
    	this.accesslevels = accesslevels;
    	
	}
    
    /**
     * @return the session_id
     */
    public String getsession_id() {
        return session_id;
    }

    /**
     * @param session_id the session_id to set
     */
    public void setsession_id(String session_id) {
        this.session_id = session_id;
    }
    
    /**
     * @return the type
     */
    public String getType() {
        return Type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.Type = type;
    }

    /**
     * @return the message
     */
    public String getmessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setmessage(String message) {
        this.message = message;
    }

    /**
     * @return the clientState
     */
    public String getClientState() {
        return ClientState;
    }

    /**
     * @param clientState the clientState to set
     * @throws Exception 
     */
    public void setClientState(String clientState){
    	this.ClientState = clientState;
    }
    
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((session_id == null) ? 0 : session_id.hashCode());
		return result;
	}
    /*
	@Override
	public String toString() {
		return String.format(
				"UssdResponse [Type=%s, message=%s, ClientState=%s, session_id=%s]", Type,
				message, ClientState, session_id);
	}
	*/

}
