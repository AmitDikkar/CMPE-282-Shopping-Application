/**
 * 
 */
package com.shopping.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shopping.database.RdsConnection;
import com.shopping.database.UserTable;
import com.shopping.domains.users.LoginForm;
import com.shopping.domains.users.RegistrationForm;
import com.shopping.dto.User;

/**
 * @author Amit
 * Handles requests for the basic user operations.
 * 1. Registration
 * 2. Login
 */
@Controller
@RequestMapping(value="/api")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	/**
	 * Post a registration form and return login page upon success.
	 * @param registrationDetails
	 * @param model
	 * @return Registration page
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<User> registerUser(@RequestBody User registrationDetails){
		logger.info("Inside POST /register method");
		System.out.println("Inside POST /register method");
		System.out.println("Email Address is: " + registrationDetails.getInputEmail());
		System.out.println("Name is: " + registrationDetails.getInputFirstName());
		System.out.println("Last name is: " + registrationDetails.getInputLastName());
		System.out.println("Email Address is: " + registrationDetails.getInputPassword());

/*		UserTable tb = new UserTable();
		tb.insertUserRecord(registrationDetails);*/
		//model.addAttribute("mainMessage", "You are now registered, please login.");
		return new ResponseEntity<User>(registrationDetails, HttpStatus.CREATED);
	}
	
	/**
	 * Authenticate the user and go back to index page upon success.
	 * @param loginDetails
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<String> loginUser(@RequestBody LoginForm loginDetails, Model model){
		System.out.println("This is /login POST");
		System.out.println("Email Address is: " + loginDetails.getInputEmail());
		System.out.println("Password is: " + loginDetails.getInputPassword());
		
		boolean isAuthentic = new UserTable().isAuthentic(loginDetails);
		if(!isAuthentic){
			//model.addAttribute( "auth_message","Authentication Failed");
			System.out.println("No, user not authentic");
			return new ResponseEntity<String>("Invalid credentials", HttpStatus.FORBIDDEN);
		}
		else{
			System.out.println("Yes, user is authentic");
			return new ResponseEntity<String>(HttpStatus.OK);
		}
	}
}
