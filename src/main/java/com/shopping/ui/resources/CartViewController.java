/**
 * 
 */
package com.shopping.ui.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.asm.util.TraceClassVisitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.shoppin.dao.CartDAO;
import com.shoppin.dao.ProductDAO;
import com.shoppin.dao.UserDAO;
import com.shopping.config.DevConfiguration;
import com.shopping.database.CartItem;
import com.shopping.pojo.Cart;
import com.shopping.pojo.Product;

/**
 * @author Amit
 *
 */
@Controller
public class CartViewController {

	@Autowired DevConfiguration conf;
	
	/**
	 * Returns Cart details page for the particular user.
	 * @param userId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public String getCart(@CookieValue (value="userId", defaultValue="-1") int userId, Model model){
		System.out.println("Inside /cart GET");
		System.out.println("**user id is received in cookie is : " + userId);
		List<Product> productList = new ArrayList<Product>();
		
		if(userId == -1){
			return "redirect:/login";
		}
		
		//using dao object. not calling api here.
		Cart cart = requestCart(userId);
		if(cart == null){
			//model.addAttribute("productList", productList);
			return "redirect:/";
		}
		
		System.out.println("Got the cart item");
		
		List<com.shopping.pojo.CartItem> cartItems = cart.getCartItems();
		
		System.out.println("list obtained: count is: " + cartItems.size());
		
		double ultimateTotal = cart.getTotalPrice();
		
		
		System.out.println("Ultimate total is: " + ultimateTotal);
		
		ProductDAO productDao = new ProductDAO();
		
		for(com.shopping.pojo.CartItem i : cartItems){
			System.out.println("@@ " + i.getProductId());
			productList.add(productDao.getById(i.getProductId()));
		}
		
		model.addAttribute("cart", cart);
		model.addAttribute("listOfCartItems", cartItems);
		model.addAttribute("ultimateTotal", ultimateTotal);
		model.addAttribute("productList", productList);
		System.out.println("returning cart");
		
		return "cart";
	}
	
	@RequestMapping(value="/reviewOrder", method = RequestMethod.GET)
	public String reviewOrder(@CookieValue(value="userId", required=true) int userId, Model model){
		System.out.println("Inside /app/reviewOrder, user Id received is: " + userId);
	
		if(userId == -1){
			return "redirect:/login";
		}
		
		Cart cart = requestCart(userId);
		List<com.shopping.pojo.CartItem> cartItems = cart.getCartItems();
		
		ProductDAO productDao = new ProductDAO();
		List<Product> productList = new ArrayList<Product>();
		
		for(com.shopping.pojo.CartItem i : cartItems){
			System.out.println("@@ " + i.getProductId());
			productList.add(productDao.getById(i.getProductId()));
		}
		
		double ultimateTotal = cart.getTotalPrice();
		
		model.addAttribute("cart", cart);
		model.addAttribute("listOfCartItems", cartItems);
		model.addAttribute("ultimateTotal", ultimateTotal);
		model.addAttribute("productList", productList);
		
		return "review_order";
	}
	
/*	float getUltimateTotal(CartItem[] cartItems) {
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
*/
	Cart requestCart(int userId) {
		RestTemplate restTemplate = new RestTemplate();
		System.out.println("calling the API");
		String myUrl = conf.getBASE_URL()+ "/api/cart/"+userId;
		//ResponseEntity<Cart> a = restTemplate.getForEntity(myUrl, Cart.class);
		Cart cart = new Cart();
		CartDAO cartDao = new CartDAO();
		
		cart = cartDao.getCartByUserId(userId);
	    /*ResponseEntity<Cart> a = restTemplate.getForEntity(myUrl, Cart.class);
		Cart cart = a.getBody();
		System.out.println("User Id from the aPI is: " + cart.getUserId());
		*/return cart;
	}
}
