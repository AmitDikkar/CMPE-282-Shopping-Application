/**
 * 
 */
package com.shoppin.dao;

import org.jongo.MongoCollection;

import com.shopping.database.MongoDb;
import com.shopping.pojo.Cart;
import com.shopping.pojo.CartItem;
import com.shopping.pojo.Product;

/**
 * @author Amit
 *
 */
public class CartDAO {
public static MongoCollection collection;
	
	public CartDAO(){
		collection = new MongoDb().cartsCollection;
	}
	
	public Cart insert(long productId, long userId, int quantity){
		
		//create new cart item
		CartItem cartItem = new CartItem();
		cartItem.setProductId(productId);
		cartItem.setQuantity(quantity);
		cartItem.setUserId(userId);
		cartItem.setCartItemId(101);
		collection.insert(cartItem);
		
		//TODO if cart already present don't add it.
		Cart cart = new Cart();
		cart.setUserId(userId);
		cart.addToCart(cartItem);
		
		collection.insert(cart);
		return cart;
	}
}
