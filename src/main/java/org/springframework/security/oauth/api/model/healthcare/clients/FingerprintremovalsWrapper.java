package org.springframework.security.oauth.api.model.healthcare.clients;

import java.util.List;

/**
 * A class to make our serialized data look cleaner, particularly xml.
 * @author mulama
 *
 */
public class FingerprintremovalsWrapper {
	
	private List<Fingerprintremoval> fingerprintremovals;
	
	public void setFingerprintremovals(List<Fingerprintremoval> fingerprintremovals) {
		this.fingerprintremovals = fingerprintremovals;
	}
	
	public List<Fingerprintremoval> getFingerprintremovals(){
		return fingerprintremovals;
	}
	
	public static FingerprintremovalsWrapper createNew(List<Fingerprintremoval> fingerprintremovals){
		FingerprintremovalsWrapper pw = new FingerprintremovalsWrapper();
		pw.setFingerprintremovals(fingerprintremovals);
		return pw;
	}
}
