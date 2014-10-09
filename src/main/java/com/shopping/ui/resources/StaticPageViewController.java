package com.shopping.ui.resources;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StaticPageViewController {

	@RequestMapping(value="/orderSuccess", method = RequestMethod.GET)
	public String orderSuccessful(Model model){
		/*
		model.addAttribute("title", "Congratulations.");
		model.addAttribute("description", "Congratulations!! Your order has been successfully placed. You can always check your"
				+ "order history in your account.");*/
		return "order_status";
	}
}
