/**
 * 
 */
package com.shopping.controllers.products;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shopping.controllers.users.UserController;

/**
 * @author Amit
 *
 */
@Controller
@RequestMapping("/products")
public class ProductController {
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String getProducts(@RequestParam(value="name", required=true) String name, Model model){
		model.addAttribute("serverTime", "This is /register:GET");
		return "product_details";
	}
}
