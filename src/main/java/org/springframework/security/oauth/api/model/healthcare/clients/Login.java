package org.springframework.security.oauth.api.model.healthcare.clients;

import java.sql.Date;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.bind.annotation.RequestParam;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class Login {
	
	//implements Serializable
	private static final long serialVersionUID = -7788619177798333712L;
	
	private int id;
	//@NotNull
	//@NotEmpty	
    private String sessionId;
    private String globalId;
    private String appType;
    private Date timeCreated;
    private Date lastModified;
    private String accessToken;
    private String refreshToken;
    private String sessionActive;
    private String life;
    private String status;
    private String message;
    private String name;
    private String email;

	public Login() {
		
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
    public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getGlobalId() {
		return globalId;
	}
	public void setGlobalID(String globalId) {
		this.globalId = globalId;
	}
	public String getAppType() {
		return appType;
	}
	public void setAppType(String appType) {
		this.appType = appType;
	}
	public Date getTimeCreated() {
		return timeCreated;
	}
	public void setTimeCreated(Date timeCreated) {
		this.timeCreated = timeCreated;
	}
	public Date getLastModified() {
		return lastModified;
	}
	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public String getSessionActive() {
		return sessionActive;
	}
	public void setSessionActive(String sessionActive) {
		this.sessionActive = sessionActive;
	}
	public String getLife() {
		return life;
	}
	public void setLife(String life) {
		this.life = life;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public Login(String sessionId, String globalId, String appType, Date timeCreated, Date lastModified, String accessToken, String refreshToken, String sessionActive, String life, String status, String message, String name, String email){
	    this.sessionId = sessionId;
	    this.globalId = globalId;
	    this.appType = appType;
	    this.timeCreated = timeCreated;
	    this.lastModified = lastModified;
	    this.accessToken = accessToken;
	    this.refreshToken = refreshToken;
	    this.sessionActive = sessionActive;
	    this.life = life;
	    this.status = status;
	    this.message = message;
	    this.name = name;
	    this.email = email;
	}

	@Override
	public String toString() {
		return String.format("Session ID=%s, global ID=%s", this.sessionId, this.globalId);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sessionId == null) ? 0 : sessionId.hashCode());
		return result;
	}

	@Override
	// Login objects are equal if they have the same name.
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Login other = (Login) obj;
		if (sessionId == null) {
			if (other.sessionId != null)
				return false;
		} else if (!sessionId.equals(other.sessionId))
			return false;
		return true;
	}
	

	

}
