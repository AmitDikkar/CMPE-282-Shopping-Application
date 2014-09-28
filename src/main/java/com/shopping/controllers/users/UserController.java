/**
 * 
 */
package com.shopping.controllers.users;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shopping.domains.users.LoginForm;
import com.shopping.domains.users.RegistrationForm;

/**
 * @author Amit
 * Handles requests for the basic user operations.
 * 1. Registration
 * 2. Login
 */
@Controller
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	
	/**
	 * Returns registration page.
	 * @param model
	 * @return Registration page
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String showRegister(Model model){
		model.addAttribute("serverTime", "This is /register:GET");
		return "register";
	}
	
	
	/**
	 * Post a registration form and return login page upon success.
	 * @param registrationDetails
	 * @param model
	 * @return Registration page
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerUser(@ModelAttribute("joe") RegistrationForm registrationDetails, Model model){
		logger.info("Inside POST /register method");
		System.out.println("Inside POST /register method");
		System.out.println("Email Address is: " + registrationDetails.getInputEmail());
		System.out.println("Name is: " + registrationDetails.getInputFirstName());
		System.out.println("Last name is: " + registrationDetails.getInputLastName());
		System.out.println("Email Address is: " + registrationDetails.getInputEmail());
		
		model.addAttribute("mainMessage", "You are now registered, please login.");
		return "redirect:login";
	}
	
	/**
	 * Authenticate the user and go back to index page upon success.
	 * @param loginDetails
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginUser(@ModelAttribute("fres") LoginForm loginDetails, Model model){
		System.out.println("This is /login POST");
		System.out.println("Email Address is: " + loginDetails.getInputEmail());
		System.out.println("Password is: " + loginDetails.getInputEmail());
		return "redirect:/";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String testview(Model model){
		System.out.println("This is /login GET");
		return "login";
	}
}
