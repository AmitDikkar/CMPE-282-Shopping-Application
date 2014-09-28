/**
 * 
 */
package com.shopping.domains.users;

import org.springframework.beans.factory.annotation.Required;

/**
 * @author Amit
 * POJO for the registratio form.
 */

public class RegistrationForm {

	/* First name of the user */
	String inputFirstName;
	
	/* Last Name of the user*/
	String inputLastName;
	
	/* Email address of the user*/
	String inputEmail;
	
	/* Password of the user*/
	String inputPassword;

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
	
	
}
