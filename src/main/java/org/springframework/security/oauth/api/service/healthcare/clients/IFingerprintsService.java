package org.springframework.security.oauth.api.service.healthcare.clients;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.oauth.api.model.healthcare.clients.Fingerprintremoval;
import org.springframework.security.oauth.api.model.healthcare.clients.Person;




public interface IFingerprintsService {

	public List<Fingerprintremoval> getFingerprints(String customerid, String country, int startindex, int maxresults, int status, String restrict,  String orderby);
	public List<Fingerprintremoval> getSearchFingerprints(String q, String customerid, String country, int startindex, int maxresults, int status, String restrict,  String orderby);
	public Fingerprintremoval getFingerprint(int id, String customerid, String country);
	public int addFingerprint(Fingerprintremoval fingerprintremoval, String customerid, String country);
	public int addFingerprintReturns(Fingerprintremoval fingerprintremoval, String customerid, String country);
	public int addFingerprint(Fingerprintremoval fingerprintremoval, int id);
	public void updateFingerprint(int id, String customerid, String country);
	public void deleteFingerprint(int id, String customerid, String country);
	public void updateSwitchedFingerprint(int id, String customerid, String country);
	
}
