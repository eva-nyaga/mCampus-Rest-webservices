/**
 * 
 */
package org.springframework.security.oauth.ussd.model;

/**
 * @author mulama
 *
 */
public class UssdRequest {

    private String SESSION_ID;
    private String Type;
    private String message;
    private String network;
    private Integer Sequence;
    private String ClientState;
    private String SERVICE_CODE;
    private String MSISDN;
    private String USSD_STRING;
    
    /**
     * 
     */
    public UssdRequest() {
    	
    }

    public UssdRequest(String SESSION_ID, String SERVICE_CODE,
			String MSISDN, String USSD_STRING) {
		// TODO Auto-generated constructor stub
    	this.SESSION_ID = SESSION_ID;
    	this.SERVICE_CODE = SERVICE_CODE;
    	this.MSISDN = MSISDN;
    	this.USSD_STRING = USSD_STRING;
	}

    /**
     * @return the session_id
     */
    public String getSESSION_ID() {
        return SESSION_ID;
    }


    /**
     * @param session_id the session_id to set
     */
    public void setSESSION_ID(String SESSION_ID) {
        this.SESSION_ID = SESSION_ID;
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
    
      public String getSERVICE_CODE() {
    	  return SERVICE_CODE;
      }
    

    /**
     * @param service_code the service_code to set
     */
    
      public void setSERVICE_CODE(String SERVICE_CODE) {
    	  this.SERVICE_CODE = SERVICE_CODE;	  
      }

    
    /**
     * @return the MSISDN
     */
    public String getMSISDN() {
	    return MSISDN;
    }

    /**
     * @param MSISDN the MSISDN to set
     */
    public void setMSISDN(String MSISDN) {
    	this.MSISDN = MSISDN;
    }
    
    
    /**
     * @return the MSISDN
     */
    public String getUSSD_STRING() {
    	return USSD_STRING;
    }

    /**
     * @param USSD_STRING the USSD_STRING to set
     */
    public void setUSSD_STRING(String USSD_STRING) {

    	String texts =null;
		int endIndex = USSD_STRING.length();
		int cursor = 0;
    	if(USSD_STRING.lastIndexOf("*0")!=-1){
    		//USSD_STRING = USSD_STRING.replace("*0", "*0*");
    		int startIndex = USSD_STRING.lastIndexOf("*0");
    		cursor = startIndex+2;
    		System.out.println("INITIAL"+USSD_STRING);
			texts = USSD_STRING.substring(cursor, endIndex);
			System.out.println("FINAL"+texts);
    	}else if(USSD_STRING.lastIndexOf("*99")!=-1){
    		texts = "99";
    	}else{
    		texts = USSD_STRING;
    	}	

	this.USSD_STRING = texts;
    }
    
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((MSISDN == null) ? 0 : MSISDN.hashCode());
		return result;
	}
    
    /*
	@Override
	public String toString() {
		return String
				.format("UssdRequest [Mobile=%s, session_id=%s, Type=%s, message=%s, network=%s, Sequence=%s, ClientState=%s, service_code=%s, MSISDN=%s, USSD_STRING=%s]",
						Mobile, session_id, Type, message, network, Sequence,
						ClientState, service_code, MSISDN, USSD_STRING);
	}
	*/

}
