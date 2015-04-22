package org.springframework.security.oauth.api.model.healthcare.clients;

import java.util.List;

/**
 * A class to make our serialized data look cleaner, particularly xml.
 * @author mulama
 *
 */
public class CodefilesWrapper {
	
	private List<Codefile> codefiles;
	
	public void setCodefiles(List<Codefile> codefiles) {
		this.codefiles = codefiles;
	}
	
	public List<Codefile> getCodefiles(){
		return codefiles;
	}
	
	public static CodefilesWrapper createNew(List<Codefile> codefiles){
		CodefilesWrapper pw = new CodefilesWrapper();
		pw.setCodefiles(codefiles);
		return pw;
	}
}
