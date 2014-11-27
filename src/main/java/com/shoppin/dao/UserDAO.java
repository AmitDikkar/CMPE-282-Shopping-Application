/**
 * 
 */
package com.shoppin.dao;

import org.jongo.MongoCollection;

import com.shopping.database.MongoDb;
import com.shopping.pojo.User;

/**
 * @author Amit
 *
 */
public class UserDAO {

	public static MongoCollection collection;
	
	public UserDAO(){
		collection = new MongoDb().usersCollection;
	}
	
	
	/**
	 * Insert the given user object in database.
	 * @param newUser a user to be created.
	 */
	public void insert(User newUser){
		collection.insert(newUser);
	}

	/**
	 * Check if user is authentic or not.
	 * @param inputEmail
	 * @param inputPassword
	 * @return true: if authentic
	 * 		   false: if not authentic
	 */
	public boolean isAuthentic(String inputEmail, String inputPassword) {
		String query = "{emailId:'" + inputEmail + "'}";
		User dbDetails = collection.findOne(query).as(User.class);
		if(dbDetails == null){
			return false;
		}
		
		System.out.println("database pwd: " + dbDetails.getPassword());
		
		if (dbDetails.getPassword().equals(inputPassword)) {
			return true;
		} else {
			return false;
		}
	}


	public User getByEmail(String inputEmail) {
		String query = "{emailId:'" + inputEmail + "'}";
		User dbDetails = collection.findOne(query).as(User.class);
		return dbDetails;
	}
}
