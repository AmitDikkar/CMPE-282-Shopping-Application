package com.shopping.ui.resources;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrderViewController {
	
	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public String getProducts(@RequestParam(value="userId", required=true) String name, Model model){
		model.addAttribute("serverTime", "This is /register:GET");
		return "order";
	}
}
