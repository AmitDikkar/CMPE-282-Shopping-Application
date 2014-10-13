/**
 * 
 */
package com.shopping.ui.resources;

import org.springframework.asm.util.TraceClassVisitor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
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
	public String getCart(@CookieValue (value="userId", defaultValue="-1") int userId, Model model){
		System.out.println("Inside /cart GET");
		System.out.println("user id is received in cookie is : " + userId);
		
		if(userId == -1){
			return "redirect:/login";
		}
		CartItem[] cartItems = requestCartItems(userId);
		
		float ultimateTotal = getUltimateTotal(cartItems); 
		
		System.out.println("Ultimate total is: " + ultimateTotal);
		model.addAttribute("listOfCartItems", cartItems);
		model.addAttribute("ultimateTotal", ultimateTotal);
		return "cart";
	}
	
	@RequestMapping(value="/reviewOrder", method = RequestMethod.GET)
	public String reviewOrder(@CookieValue(value="userId", required=true) int userId, Model model){
		System.out.println("Inside /app/reviewOrder, user Id received is: " + userId);
	
		if(userId == -1){
			return "redirect:/login";
		}
		
		CartItem[] cartItems = requestCartItems(userId);
		float ultimateTotal = getUltimateTotal(cartItems);
		model.addAttribute("listOfCartItems", cartItems);
		model.addAttribute("ultimateTotal", ultimateTotal);
		return "review_order";
	}
	
	float getUltimateTotal(CartItem[] cartItems) {
		float ultimateTotal = 0;
		for(CartItem item : cartItems){
			System.out.println("Cart Item:");
			System.out.println("product retrived is: " + item.getProduct().getCategory());
			System.out.println("---------------");
			System.out.println("Total Price is: " + item.getTotalPrice());
			ultimateTotal = ultimateTotal + item.getTotalPrice();
		}
		return ultimateTotal;
	}

	CartItem[] requestCartItems(int userId) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<CartItem[]> receivedList = restTemplate.getForEntity("http://127.0.0.1:8080/" + "api/cart/"+userId, CartItem[].class);
		CartItem[] cartItems = receivedList.getBody();
		return cartItems;
	}
}
