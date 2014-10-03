package com.shopping.ui.resources;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String testview(Model model){
		System.out.println("This is /login GET");
		return "login";
	}
}
