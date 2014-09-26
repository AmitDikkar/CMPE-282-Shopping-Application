/**
 * 
 */
package com.shopping.controllers.users;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Amit
 * Handles requests for the basic user operations.
 * 1. Registration
 * 2. Login
 */
@Controller
@RequestMapping("/users")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(@RequestParam(value="name", required=true) String name, Model model){
		model.addAttribute("serverTime", "This is /register:GET");
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model){
		model.addAttribute("serverTime", "This is /login: GET");
		return "login";
	}
	
	@RequestMapping(value = "/testview", method = RequestMethod.GET)
	public String testview(Model model){
		model.addAttribute("serverTime", "This is /Test: GET");
		return "test";
	}
}
