/**
 * 
 */
package org.springframework.security.oauth.ussd.model;

/**
 * @author mulama
 *
 */
public class UssdRequest {

    private String session_id;
    private String Type;
    private String message;
    private String network;
    private Integer Sequence;
    private String ClientState;
    private String service_code;
    private String msisdn;
    private String ussd_string;
    
    /**
     * 
     */
    public UssdRequest() {
    	
    }

    public UssdRequest(String session_id, String service_code,
			String msisdn, String ussd_string) {
		// TODO Auto-generated constructor stub
    	this.session_id = session_id;
    	this.service_code = service_code;
    	this.msisdn = msisdn;
    	this.ussd_string = ussd_string;
    	
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
     * @return the network
     */
    public String getnetwork() {
        return network;
    }

    /**
     * @param network the network to set
     */
    public void setnetwork(String network) {
        this.network = network;
    }

    /**
     * @return the sequence
     */
    public Integer getSequence() {
        return Sequence;
    }

    /**
     * @param sequence the sequence to set
     */
    public void setSequence(Integer sequence) {
        this.Sequence = sequence;
    }

    /**
     * @return the clientState
     */
    public String getClientState() {
        return ClientState;
    }

    /**
     * @param clientState the clientState to set
     */
    public void setClientState(String clientState) {
        this.ClientState = clientState;
    }

    /**
     * @return the service_code
     */
    public String getservice_code() {
	return service_code;
    }

    /**
     * @param service_code the service_code to set
     */
    public void setservice_code(String service_code) {
	this.service_code = service_code;
    }

    
    /**
     * @return the msisdn
     */
    public String getmsisdn() {
	return msisdn;
    }

    /**
     * @param msisdn the msisdn to set
     */
    public void setmsisdn(String msisdn) {
	this.msisdn = msisdn;
    }
    
    
    /**
     * @return the msisdn
     */
    public String getussd_string() {
	return ussd_string;
    }

    /**
     * @param ussd_string the ussd_string to set
     */
    public void setussd_string(String ussd_string) {
	this.ussd_string = ussd_string;
    }
    
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((msisdn == null) ? 0 : msisdn.hashCode());
		return result;
	}
    
    /*
	@Override
	public String toString() {
		return String
				.format("UssdRequest [Mobile=%s, session_id=%s, Type=%s, message=%s, network=%s, Sequence=%s, ClientState=%s, service_code=%s, msisdn=%s, ussd_string=%s]",
						Mobile, session_id, Type, message, network, Sequence,
						ClientState, service_code, msisdn, ussd_string);
	}
	*/

}
