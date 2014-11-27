/**
 * 
 */
package com.shopping.pojo;

import java.util.List;

/**
 * @author Amit
 *
 */
public class User {
	
	/** user id of this user**/
	long userId;
	
	/** first name of the user**/
	String firstName;
	
	/** Last name of the user**/
	String lastName;
	
	/** Email ID of the user**/
	String emailId;
	
	/** Password of the user**/
	String password;
	
	/** Recommended Products for the user**/
	List<Product> recommendedProducts;

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

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the recommendedProducts
	 */
	public List<Product> getRecommendedProducts() {
		return recommendedProducts;
	}

	/**
	 * @param recommendedProducts the recommendedProducts to set
	 */
	public void setRecommendedProducts(List<Product> recommendedProducts) {
		this.recommendedProducts = recommendedProducts;
	}
	
}
