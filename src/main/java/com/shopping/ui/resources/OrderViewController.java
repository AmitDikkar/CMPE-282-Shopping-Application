package com.shopping.ui.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.shopping.config.DevConfiguration;
import com.shopping.database.CartItem;
import com.shopping.database.DummyCartItem;

@Controller
public class OrderViewController {
	
	@Autowired DevConfiguration conf;
	
	//get list of orders that user has placed so far
	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public String getProducts(@CookieValue(value="userId", defaultValue="-1") int userId, Model model){
		System.out.println("Inside /orders GET");
		
		if(userId == -1){
			return "login";
		}
		
		CartViewController cc = new CartViewController();
		DummyCartItem[] orderedItems = requestOrderedItems(userId);
		
		//float ultimateTotal = cc.getUltimateTotal(orderedItems); 
		model.addAttribute("listOfCartItems", orderedItems);
		//model.addAttribute("ultimateTotal", ultimateTotal);
		model.addAttribute("ultimateTotal", 0);
		return "myorders";
	}

	private DummyCartItem[] requestOrderedItems(int userId) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<DummyCartItem[]> receivedList = restTemplate.getForEntity(conf.getBASE_URL() + "/api/orders?userId="+userId, DummyCartItem[].class);
		DummyCartItem[] cartItems = receivedList.getBody();
		return cartItems;
	}
}
