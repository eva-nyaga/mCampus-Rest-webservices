package org.springframework.security.oauth.api.model.healthcare.clients;

import java.util.List;

/**
 * A class to make our serialized data look cleaner, particularly xml.
 * @author mulama
 *
 */
public class MoneyAdditionsWrapper {
	
	private List<MoneyAddition> moneyadditions;
	
	public void setMoneyAdditions(List<MoneyAddition> moneyadditions) {
		this.moneyadditions = moneyadditions;
	}
	
	public List<MoneyAddition> getMoneyAdditions(){
		return moneyadditions;
	}
	
	public static MoneyAdditionsWrapper createNew(List<MoneyAddition> moneyadditions){
		MoneyAdditionsWrapper pw = new MoneyAdditionsWrapper();
		pw.setMoneyAdditions(moneyadditions);
		return pw;
	}
}
