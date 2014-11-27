package com.shopping.ui.resources;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shoppin.dao.UserDAO;
import com.shopping.pojo.User;

@Controller
public class UserViewController {

	/**
	 * Returns registration page.
	 * @param model
	 * @return Registration page
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String showRegister(Model model){
		model.addAttribute("This is /register:GET");
		return "register";
	}
	
	
	/**
	 * Returns login page.
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLogin(Model model){
		System.out.println("This is /login GET");
		/** insert new - Test**/
		/*
		User newUser = new User();
		newUser.setEmailId("amit.dikkar@gmail.com");
		newUser.setFirstName("Amit");
		newUser.setLastName("Dikkar");
		newUser.setPassword("password");
		UserDAO userDao = new UserDAO();
		userDao.insert(newUser);*/
		
		return "login";
	}
}
