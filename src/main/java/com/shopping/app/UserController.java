/**
 * 
 */
package com.shopping.app;

import java.text.Normalizer.Form;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

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

import com.shoppin.dao.UserDAO;
import com.shopping.database.RdsConnection;
import com.shopping.database.UserTable;
import com.shopping.domains.users.LoginForm;
import com.shopping.domains.users.RegistrationForm;
import com.shopping.dto.LayoutData;
import com.shopping.dto.ShipmentForm;
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

	private UserDAO userDAO = new UserDAO();
	/**
	 * DONE-Post a registration form and return login page upon success.
	 * @param registrationDetails
	 * @param model
	 * @return Registered user
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<User> registerUser(@RequestBody User registrationDetails){

		System.out.println("Inside POST /register method");
		
		com.shopping.pojo.User newUser = new com.shopping.pojo.User();
		newUser.setEmailId(registrationDetails.getInputEmail());
		newUser.setPassword(registrationDetails.getInputPassword());
		newUser.setUserId(registrationDetails.getUserId());
		newUser.setFirstName(registrationDetails.getInputFirstName());
		newUser.setLastName(registrationDetails.getInputLastName());
		
		userDAO.insert(newUser);
		
		return new ResponseEntity<User>(registrationDetails, HttpStatus.CREATED);
	}
	
	/**
	 * DONE- Authenticate the user and go back to index page upon success.
	 * @param loginDetails
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<LayoutData> loginUser(@RequestBody LoginForm loginDetails, Model model, HttpServletResponse response){
		
		System.out.println("This is /login POST");
		/*System.out.println("Email Address is: " + loginDetails.getInputEmail());
		System.out.println("Password is: " + loginDetails.getInputPassword());
		*/
		boolean isAuthentic = userDAO.isAuthentic(loginDetails.getInputEmail(), loginDetails.getInputPassword());
		
		System.out.println("is authentic: " + isAuthentic);
		
		com.shopping.pojo.User newUser = userDAO.getByEmail(loginDetails.getInputEmail());
		LayoutData commonData = new LayoutData();
		
		if(newUser!=null){
			commonData.setUserId(newUser.getUserId());
			commonData.setUserName(newUser.getFirstName());
		}
		else{
			return new ResponseEntity<LayoutData>(HttpStatus.BAD_REQUEST);
		}
		
		if(isAuthentic == false){
			System.out.println("No, user not authentic");
			return new ResponseEntity<LayoutData>(commonData, HttpStatus.FORBIDDEN);
		}
		else{
			System.out.println("Yes, user is authentic");
			return new ResponseEntity<LayoutData>(commonData, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/shipping", method = RequestMethod.POST)
	public ResponseEntity<ShipmentForm> shipProdcuct(@RequestBody ShipmentForm shippingDetails){
		System.out.println("Shipping order is received");
		return new ResponseEntity<ShipmentForm>(shippingDetails, HttpStatus.CREATED);
	}
}
