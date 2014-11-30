/**
 * 
 */
package com.shopping.app;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shoppin.dao.CartDAO;
import com.shopping.database.CartCommands;
import com.shopping.database.CartItem;
import com.shopping.database.ProductCatalogItem;
import com.shopping.dto.AddToCartForm;
import com.shopping.dto.UpdateCartForm;
import com.shopping.pojo.Cart;

/**
 * @author Amit
 *
 */
@Controller
@RequestMapping(value="/api")
public class CartController {

	private static final Logger logger = LoggerFactory.getLogger(CartController.class);
	private final String CART_INCREASE_ACTION = "increase";
	private final String CART_DECREASE_ACTION = "decrease";
	
	private CartDAO cartDao = new CartDAO();
	
	/**
	 * Done - Returns all cart items added by the user.
	 * GET: /api/cart/userId
	 * @param userId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/cart/{userId}", method = RequestMethod.GET)
	public ResponseEntity<Cart> getCart(@PathVariable int userId, Model model){
		model.addAttribute("serverTime", "This is /register:GET");
		System.out.println("Inside /cart GET");
		System.out.println("user id is: " + userId);
		
		try {
			Cart cart = cartDao.getCartByUserId(userId);
			//if cart doesn't exist for the user send NO_CONTENT
			if(cart == null){
				System.out.println("cart was null");
				return new ResponseEntity<Cart>(HttpStatus.NO_CONTENT);
			}
			System.out.println("cart retrieved for user: " + cart.getUserId());
			System.out.println("returned it");
			return new ResponseEntity<Cart>(cart, HttpStatus.CREATED);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Cart>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Done - Adds item to the cart of the user.
	 * POST: /api/cart
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/cart", method = RequestMethod.POST)
	public ResponseEntity<String> addToCart(@RequestBody AddToCartForm cartItem){
		System.out.println("Inside /cart POST");
		System.out.println("quantity: " + cartItem.getQuantity());
		System.out.println("User id: " + cartItem.getUserId());
		System.out.println("Product id: " + cartItem.getProductId());
		CartCommands comm = null;
		try {
			cartDao.insert(cartItem.getProductId(), cartItem.getUserId(), cartItem.quantity);
			
			System.out.println("returning success");
			return new ResponseEntity<String>("Item Added", HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("returning error");
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Done-Removing product from the cart.
	 * @param productId Product to be removed from the cart
	 * @param userId user of this cart. 
	 * @return
	 */
	@RequestMapping(value = "/cart", method = RequestMethod.DELETE)
	public ResponseEntity<String> removeFromCart(@RequestParam("productId") Long productId, @RequestParam("userId") Long userId){
		System.out.println("Inside DELETE: /api/cart");
		System.out.println("Product ID received is: " + productId);
		System.out.println("User ID received is: " + userId);
		CartCommands comm = null;
		try {
			cartDao.removeProduct(userId, productId);
			return new ResponseEntity<String>("Item removed", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Item can not removed", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Removing this permanently. Not required.
	 * **/
	@RequestMapping(value="/cart", method=RequestMethod.PUT)
	public ResponseEntity<CartItem> updateQuantity(@RequestBody UpdateCartForm updateRequest){
		System.out.println("Inside PUT: api/cart");
		System.out.println("Card id received is: " + updateRequest.getCartId());
		System.out.println("Operation is: " + updateRequest.getAction());
		
		CartCommands comm = null;
		CartItem item = null;
		try {
			comm = new CartCommands();
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<CartItem>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(updateRequest.getAction().equals(this.CART_INCREASE_ACTION)){
			item = comm.increseQuantity(updateRequest.getCartId());
		}
		else if(updateRequest.getAction().equals(this.CART_DECREASE_ACTION)){
			item = comm.decreaseQuantity(updateRequest.getCartId());
		}
		else{
			return new ResponseEntity<CartItem>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<CartItem>(item, HttpStatus.OK);
	}
	
	private List<CartItem> addDummyCartItems(){
		CartItem item1 = new CartItem();
		item1.setProductId((long) 1);
		ProductCatalogItem p = new ProductCatalogItem();
		p.setName("item-1");
		p.setPrice(10);
		item1.setProduct(p);
		List<CartItem> ls = new ArrayList<CartItem>();
		ls.add(item1);
		return ls;
	}
}