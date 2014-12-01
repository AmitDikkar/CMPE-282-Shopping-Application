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
	
	private String gender;
	
	private int age;
	
	private String genre;
	
	private String artist;

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

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return the genre
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * @param genre the genre to set
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}

	/**
	 * @return the artist
	 */
	public String getArtist() {
		return artist;
	}

	/**
	 * @param artist the artist to set
	 */
	public void setArtist(String artist) {
		this.artist = artist;
	}
	
}
