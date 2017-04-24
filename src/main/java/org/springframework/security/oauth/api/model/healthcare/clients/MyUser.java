package org.springframework.security.oauth.api.model.healthcare.clients;

import java.sql.Date;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.bind.annotation.RequestParam;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class MyUser {
	
	//implements Serializable
	private static final long serialVersionUID = -7788619177798333712L;
	
	
	//@NotNull
	//@NotEmpty	
	
	
    public String userId;
    public String programme;
    public String currentsem;
    public String replymessage;
    public int retakecount, changedpassword;
    
    
 

	public MyUser() {
		
	}

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUserId() {
        return userId;
    }

  
    public String getProgramme() {
        return programme;
    }

   
  
    public String getCurrentsem() {
        return currentsem;
    }

    public int getRetakecount() {
        return retakecount;
    }

    public String getReplymessage() {
        return replymessage;
    }

    public int getChangedpassword() {
        return changedpassword;
    }

    public void setChangedpassword(int changedpassword) {
        this.changedpassword = changedpassword;
    }

    
  
    public void setUserId(String userId) {
        this.userId = userId;
    }

  
    public void setProgramme(String programme) {
        this.programme = programme;
    }

  
    public void setCurrentsem(String currentsem) {
        this.currentsem = currentsem;
    }

    public void setRetakecount(int retakecount) {
        this.retakecount = retakecount;
    }

    public void setReplymessage(String replymessage) {
        this.replymessage = replymessage;
    }
    
    

        public MyUser(String userId, String programme, String currentsem, int retakecount, int changedpassword){
		this.userId = userId;
		this.programme = programme;
                this.currentsem=currentsem;
                this.retakecount=retakecount;
                this.changedpassword=changedpassword;
		
	}
        
          public MyUser(String replymessage){
		this.replymessage=replymessage;
		
	}
        
       
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		MyUser other = (MyUser) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	

	

}
