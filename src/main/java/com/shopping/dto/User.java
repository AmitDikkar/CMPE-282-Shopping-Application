/**
 * 
 */
package com.shopping.dto;

/**
 * @author Amit
 *
 */
public class User {
	
	private long userId;
	
	/* First name of the user */
	private String inputFirstName;
	
	/* Last Name of the user*/
	private String inputLastName;
	
	/* Email address of the user*/
	private String inputEmail;
	
	/* Password of the user*/
	private String inputPassword;

	/**
	 * @return the inputFirstName
	 */
	public String getInputFirstName() {
		return inputFirstName;
	}

	/**
	 * @param inputFirstName the inputFirstName to set
	 */
	public void setInputFirstName(String inputFirstName) {
		this.inputFirstName = inputFirstName;
	}

	/**
	 * @return the inputLastName
	 */
	public String getInputLastName() {
		return inputLastName;
	}

	/**
	 * @param inputLastName the inputLastName to set
	 */
	public void setInputLastName(String inputLastName) {
		this.inputLastName = inputLastName;
	}

	/**
	 * @return the inputEmail
	 */
	public String getInputEmail() {
		return inputEmail;
	}

	/**
	 * @param inputEmail the inputEmail to set
	 */
	public void setInputEmail(String inputEmail) {
		this.inputEmail = inputEmail;
	}

	/**
	 * @return the inputPassword
	 */
	public String getInputPassword() {
		return inputPassword;
	}

	/**
	 * @param inputPassword the inputPassword to set
	 */
	public void setInputPassword(String inputPassword) {
		this.inputPassword = inputPassword;
	}

	/**
	 * @return the userId
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
}
