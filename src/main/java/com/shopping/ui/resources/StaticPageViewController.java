package com.shopping.ui.resources;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shopping.database.CartItem;

@Controller
public class StaticPageViewController {

/*	@RequestMapping(value="/orderSuccess", method = RequestMethod.GET)
	public String orderSuccessful(Model model){
		
		model.addAttribute("title", "Congratulations.");
		model.addAttribute("description", "Congratulations!! Your order has been successfully placed. You can always check your"
				+ "order history in your account.");
		return "orderstatussuccess";
	}
	*/

	//get list of orders that user has placed so far
	@RequestMapping(value="/orderSuccess", method = RequestMethod.GET)
	public String getSuccessView(Model model){
		System.out.println("Inside /orderSucess GET");
		String statusTitle = "Congratulations";
		String statusDescription = "Your order has been placed.";
		model.addAttribute("statusTitle", statusTitle);
		model.addAttribute("statusDescription", statusDescription);
		return "orderstatussuccess";
	}
}
