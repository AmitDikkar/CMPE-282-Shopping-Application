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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shoppin.dao.CartDAO;
import com.shopping.database.CartCommands;
import com.shopping.database.CartItem;
import com.shopping.database.DummyCartItem;
import com.shopping.database.DumpyProductCatalogItem;
import com.shopping.database.ProductCatalogItem;
import com.shopping.dto.PlaceOrderForm;
import com.shopping.pojo.Cart;

/**
 * @author Amit
 *
 */
@Controller
@RequestMapping("/api")
public class OrderController {
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	private CartDAO cartDao = new CartDAO();

	@RequestMapping(value = "/payments", method = RequestMethod.GET)
	public String makePayment(@RequestParam(value="confirm", required=true) String confirm, Model model){
		logger.info("GET Request on /orders/payments " + "with confirm=" +confirm);
		model.addAttribute("serverTime", "This is /register:GET");
		return "payment";
	}

	//returns cart that has been already procesed.
	@RequestMapping(value="/orders", method=RequestMethod.GET)
	public ResponseEntity<List<com.shopping.pojo.CartItem>> getOrders(@RequestParam(value="userId", required=true) int userId, Model model){
		System.out.println("Inside /api/orders GET");
		System.out.println("User id received is: " + userId);
		CartCommands comm = null;
		try {
			//TODO remove after adding database call.
			/*comm = new CartCommands();
			List<CartItem> listOfOrders = comm.getOrderItemsFromUserId(userId);
			 */
			//List<DummyCartItem> listOfOrders = addDummyCartItems();
			List<com.shopping.pojo.CartItem> cart = cartDao.getOrderByUserId(userId);
			
			return new ResponseEntity<List<com.shopping.pojo.CartItem> >(cart, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<com.shopping.pojo.CartItem>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/** Place order and Process the cart.**/
	@RequestMapping(value="/orders", method=RequestMethod.POST)
	public ResponseEntity<Cart> placeOrder(@RequestBody PlaceOrderForm placeOrderForm, Model model){
		System.out.println("Inside /api/orders POST");
		System.out.println("User id received is: " + placeOrderForm.getUserId());
		//CartCommands comm = null;
		try {
			Cart cart = cartDao.placeOrder(placeOrderForm.getUserId());
			return new ResponseEntity<Cart>(cart, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Cart>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private List<DummyCartItem> addDummyCartItems(){
		DummyCartItem c = new DummyCartItem();
		DumpyProductCatalogItem p = new DumpyProductCatalogItem();
		p.setId((long) 1);
		p.setName("item-1");
		p.setPrice(10);
		c.setCartId((long) 1);
		c.setProduct(p);
		List<DummyCartItem> ls = new ArrayList<DummyCartItem>();
		ls.add(c);
		return ls;
	}
}
