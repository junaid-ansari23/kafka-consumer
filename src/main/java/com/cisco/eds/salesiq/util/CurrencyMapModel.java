/**
 * 
 */
package com.cisco.eds.salesiq.util;

/**
 * @author juansari
 *
 */
public class CurrencyMapModel{
	String currencyCode;
	Double conversionRate;
	/**
	 * @return the currencyCode
	 */
	public String getCurrencyCode() {
		return currencyCode;
	}
	/**
	 * @param currencyCode the currencyCode to set
	 */
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	/**
	 * @return the conversionRate
	 */
	public Double getConversionRate() {
		return conversionRate;
	}
	/**
	 * @param conversionRate the conversionRate to set
	 */
	public void setConversionRate(Double conversionRate) {
		this.conversionRate = conversionRate;
	}
	
} 
