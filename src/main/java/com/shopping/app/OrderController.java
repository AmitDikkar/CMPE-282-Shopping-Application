/**
 * 
 */
package com.shopping.app;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shopping.database.CartCommands;
import com.shopping.database.CartItem;
import com.shopping.dto.PlaceOrderForm;

/**
 * @author Amit
 *
 */
@Controller
@RequestMapping("/api")
public class OrderController {
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	
	@RequestMapping(value = "/payments", method = RequestMethod.GET)
	public String makePayment(@RequestParam(value="confirm", required=true) String confirm, Model model){
		logger.info("GET Request on /orders/payments " + "with confirm=" +confirm);
		model.addAttribute("serverTime", "This is /register:GET");
		return "payment";
	}
	
	//returns list of items that is in the order items into order.
	@RequestMapping(value="/orders", method=RequestMethod.GET)
	public ResponseEntity<List<CartItem>> getOrders(@RequestParam(value="userId", required=true) int userId, Model model){
		System.out.println("Inside /api/orders GET");
		System.out.println("User id received is: " + userId);
		CartCommands comm = null;
		try {
			comm = new CartCommands();
			List<CartItem> listOfOrders = comm.getOrderItemsFromUserId(userId);
			return new ResponseEntity<List<CartItem>>(listOfOrders, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<CartItem>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/orders", method=RequestMethod.POST)
	public ResponseEntity<List<CartItem>> placeOrder(@RequestBody PlaceOrderForm placeOrderForm, Model model){
		System.out.println("Inside /api/orders POST");
		System.out.println("User id received is: " + placeOrderForm.getUserId());
		CartCommands comm = null;
		try {
			comm = new CartCommands();
			List<CartItem> listOfOrders = comm.placeOrder(placeOrderForm.getUserId());
			return new ResponseEntity<List<CartItem>>(listOfOrders, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<CartItem>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
