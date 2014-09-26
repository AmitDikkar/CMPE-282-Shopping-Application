/**
 * 
 */
package com.shopping.controllers.carts;

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
@RequestMapping("/carts")
public class CartController {

	private static final Logger logger = LoggerFactory.getLogger(CartController.class);
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String getCart(@RequestParam(value="name", required=true) String name, Model model){
		model.addAttribute("serverTime", "This is /register:GET");
		return "cart";
	}
}
