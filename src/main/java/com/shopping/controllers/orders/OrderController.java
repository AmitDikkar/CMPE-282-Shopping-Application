/**
 * 
 */
package com.shopping.controllers.orders;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shopping.controllers.products.ProductController;

/**
 * @author Amit
 *
 */
@Controller
//@RequestMapping("/orders")
public class OrderController {
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public String getProducts(@RequestParam(value="name", required=true) String name, Model model){
		model.addAttribute("serverTime", "This is /register:GET");
		return "order";
	}
	
	@RequestMapping(value = "/payments", method = RequestMethod.GET)
	public String makePayment(@RequestParam(value="confirm", required=true) String confirm, Model model){
		logger.info("GET Request on /orders/payments " + "with confirm=" +confirm);
		model.addAttribute("serverTime", "This is /register:GET");
		return "payment";
	}
}
