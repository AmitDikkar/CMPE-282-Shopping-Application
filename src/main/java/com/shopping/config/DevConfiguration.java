/**
 * 
 */
package com.shopping.config;

/**
 * @author Amit
 *
 */
public class DevConfiguration {
	
	private String supportEmail;
	
	private String BASE_URL;
	
	private String MONGODB_CONNECTION_STRING;
	
	/**
	 * @return the supportEmail
	 */
	public String getSupportEmail() {
		return supportEmail;
	}

	/**
	 * @param supportEmail the supportEmail to set
	 */
	public void setSupportEmail(String supportEmail) {
		this.supportEmail = supportEmail;
	}

	/**
	 * @return the bASE_URL
	 */
	public String getBASE_URL() {
		return BASE_URL;
	}

	/**
	 * @param bASE_URL the bASE_URL to set
	 */
	public void setBASE_URL(String bASE_URL) {
		BASE_URL = bASE_URL;
	}

	/**
	 * @return the mONGODB_CONNECTION_STRING
	 */
	public String getMONGODB_CONNECTION_STRING() {
		return MONGODB_CONNECTION_STRING;
	}

	/**
	 * @param mONGODB_CONNECTION_STRING the mONGODB_CONNECTION_STRING to set
	 */
	public void setMONGODB_CONNECTION_STRING(String mONGODB_CONNECTION_STRING) {
		MONGODB_CONNECTION_STRING = mONGODB_CONNECTION_STRING;
	}
}
