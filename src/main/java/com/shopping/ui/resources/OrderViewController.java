package com.shopping.ui.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.shopping.database.CartItem;

@Controller
public class OrderViewController {
	
	//get list of orders that user has placed so far
	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public String getProducts(@CookieValue(value="userId", defaultValue="-1") int userId, Model model){
		System.out.println("Inside /orders GET");
		
		if(userId == -1){
			return "login";
		}
		
		CartViewController cc = new CartViewController();
		CartItem[] orderedItems = requestOrderedItems(userId);
		float ultimateTotal = cc.getUltimateTotal(orderedItems); 
		model.addAttribute("listOfCartItems", orderedItems);
		model.addAttribute("ultimateTotal", ultimateTotal);
		return "myorders";
	}

	private CartItem[] requestOrderedItems(int userId) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<CartItem[]> receivedList = restTemplate.getForEntity("http://localhost:8080/app/" + "api/orders?userId="+userId, CartItem[].class);
		CartItem[] cartItems = receivedList.getBody();
		return cartItems;
	}
}
