package org.springframework.security.oauth.api.model.healthcare.clients;

import java.util.List;

/**
 * A class to make our serialized data look cleaner, particularly xml.
 * @author mulama
 *
 */
public class MoneyReductionsWrapper {
	
	private List<MoneyReduction> moneyreductions;
	
	public void setMoneyReductions(List<MoneyReduction> moneyreductions) {
		this.moneyreductions = moneyreductions;
	}
	
	public List<MoneyReduction> getMoneyReductions(){
		return moneyreductions;
	}
	
	public static MoneyReductionsWrapper createNew(List<MoneyReduction> moneyreductions){
		MoneyReductionsWrapper pw = new MoneyReductionsWrapper();
		pw.setMoneyReductions(moneyreductions);
		return pw;
	}
}
