/**
 * 
 */
package com.shopping.ui.resources;

import org.springframework.asm.util.TraceClassVisitor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.shopping.database.CartItem;

/**
 * @author Amit
 *
 */
@Controller
public class CartViewController {

	/**
	 * Returns Cart details page for the particular user.
	 * @param userId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public String getCart(@RequestParam (value="id", required=true) int userId, Model model){
		model.addAttribute("serverTime", "This is /register:GET");
		System.out.println("Inside /cart GET");
		System.out.println("user id is: " + userId);
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<CartItem[]> receivedList = restTemplate.getForEntity("http://localhost:8080/app/" + "api/cart/"+userId, CartItem[].class);
		CartItem[] cartItems = receivedList.getBody();
		System.out.println("Below are the cart items of " + userId + " user");
		for(CartItem item : cartItems){
			System.out.println("Cart Item:");
			System.out.println(item.getProductId());
		}
		model.addAttribute("items", cartItems);
		return "cart";
	}
}
