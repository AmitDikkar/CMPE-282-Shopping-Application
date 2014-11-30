package com.shopping.ui.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.shoppin.dao.CartDAO;
import com.shoppin.dao.ProductDAO;
import com.shopping.config.DevConfiguration;
import com.shopping.database.CartItem;
import com.shopping.database.DummyCartItem;
import com.shopping.pojo.Cart;
import com.shopping.pojo.Product;

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
		
		/*CartViewController cc = new CartViewController();
		Cart cart = requestOrderedItems(userId);
		
		List<com.shopping.pojo.CartItem> orderedItems = cart.getCartItems();*/
		
		List<Product> listOfProducts = new ArrayList<Product>();
		
		List<com.shopping.pojo.CartItem> orderedItems = new CartDAO().getOrderByUserId(userId);
		
		//repeating products should be displayed only once.
		HashSet<Long> productIds = new HashSet<Long>();
		
		ProductDAO dao = new ProductDAO();
		for (com.shopping.pojo.CartItem i : orderedItems) {
			productIds.add(i.getProductId());
		}
		
		for(long productId : productIds){
			listOfProducts.add(dao.getById(productId));
		}
		
		System.out.println("hashset size" + productIds.size());
		System.out.println("list size"+ listOfProducts.size());
		
		//double ultimateTotal = cart.getTotalPrice();
		
		model.addAttribute("listOfCartItems", orderedItems);
		//model.addAttribute("ultimateTotal", ultimateTotal);
		model.addAttribute("productList", listOfProducts);
		model.addAttribute("userId", userId);
		
		return "myorders";
	}

	private Cart requestOrderedItems(int userId) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Cart> receivedList = restTemplate.getForEntity(conf.getBASE_URL() + "/api/orders?userId="+userId, Cart.class);
		Cart cart = receivedList.getBody();
		return cart;
	}
}
