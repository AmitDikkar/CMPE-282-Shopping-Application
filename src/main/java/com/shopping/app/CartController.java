/**
 * 
 */
package com.shopping.app;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shopping.database.CartCommands;
import com.shopping.database.CartItem;
import com.shopping.dto.AddToCartForm;

/**
 * @author Amit
 *
 */
@Controller
public class CartController {

	private static final Logger logger = LoggerFactory.getLogger(CartController.class);
	
	//Returns our main cart page. for the given user id.
	@RequestMapping(value = "/views/cart", method = RequestMethod.GET)
	public String getCart(@RequestParam(value="id", required=true) int id, Model model){
		model.addAttribute("serverTime", "This is /register:GET");
		System.out.println("Inside /cart GET");
		System.out.println("user id is: " + id);
		
		CartCommands comm = null;
		try {
			comm = new CartCommands();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<CartItem> listOfItems = comm.getCartItemsFromUserId(id);
		return "cart";
	}
	
	@RequestMapping(value = "/cart", method = RequestMethod.POST)
	public ResponseEntity<String>  addToCart(AddToCartForm request, Model model){
		System.out.println("Add to cart requst.product id: " + request.getQuantity());
		System.out.println("Add to cart requst.product id: " + request.getUserId());
		System.out.println("Add to cart requst.product id: " + request.getProdutId());
		CartCommands comm = null;
		try {
			comm = new CartCommands();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//comm.saveCartItem(request);
		
		return new ResponseEntity<String>("Item Added", HttpStatus.CREATED);
	}
}
