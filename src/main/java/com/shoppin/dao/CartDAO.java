/**
 * 
 */
package com.shoppin.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jongo.MongoCollection;
import org.jongo.MongoCursor;

import com.shopping.database.MongoDb;
import com.shopping.pojo.Cart;
import com.shopping.pojo.CartItem;
import com.shopping.pojo.Product;
import com.shopping.pojo.User;

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

		Cart c = createOrUpdateCart(cartItem, userId);
		return c;
	}

	/**
	 * Creates new cart and insert - if this user doesn't have a cart.
	 * Updates existing item list of cart - if this user already have a cart.
	 * @param cartItem
	 * @param userId
	 * @return
	 */
	private Cart createOrUpdateCart(CartItem cartItem, long userId){
		String query = "{userId:" + userId + ", isPurchased:"+ false + "}";
		System.out.println("**query is: " + query);
		Cart c = collection.findOne(query).as(Cart.class);
		//if cart doesn't exist for this user. then create one.
		if(c == null){
			System.out.println("cart doesn't exist adding new one.");
			c = new Cart();
			c.setUserId(userId);
			c.addToCart(cartItem);
			collection.insert(c);
			return c;
		}
		else{
			System.out.println("cart exists. Updating.");
			c.addToCart(cartItem);
			collection.update(query).with(c);
			System.out.println("Updated.");
			return c;
		}
	}

	public List<com.shopping.pojo.CartItem> getCartItemsByUserId(int userId) {
		String query = "{userId:" + userId + ", isPurchased:"+ false + "}";

		//find the cart of this user.
		Cart cart = collection.findOne(query).as(Cart.class);
		//if it doesn't exists, send blank list.
		if(cart == null){
			return new ArrayList<CartItem>();
		}

		List<CartItem> cartItems = cart.getCartItems();
		return cartItems;
	}

	public Cart getCartByUserId(int userId) {
		String query = "{userId:" + userId + ", isPurchased:"+ false + "}";

		//find the cart of this user.
		Cart cart = collection.findOne(query).as(Cart.class);
		return cart;
	}

	public void removeProduct(Long userId, Long productId) {
		String query = "{userId:" + userId + ", isPurchased:"+ false + "}";
		Cart cart = collection.findOne(query).as(Cart.class);
		cart.removeProduct(productId);
		collection.update(query).with(cart);
	}

	public List<CartItem> getOrderByUserId(int userId) {
		String query = "{userId:" + userId + ", isPurchased:"+ true + "}";
		List<CartItem> cartItems =  new ArrayList<CartItem>();
		MongoCursor<Cart> cartCursor = collection.find(query).as(Cart.class);
		
		Iterator<Cart> cartItr = cartCursor.iterator();
		while(cartItr.hasNext()){
			cartItems.addAll(cartItr.next().getCartItems());
		}
		return cartItems;
	}

	public Cart placeOrder(int userId) {
		String query = "{userId:" + userId + ", isPurchased:"+ false + "}";
		Cart cart = collection.findOne(query).as(Cart.class);
		cart.setPurchased(true);
		collection.update(query).with(cart);
		return cart;
	}

	public void updateRating(long userId, long productId, int rating) {
		String query = "{userId:" + userId + ", isPurchased:"+ true + "}";
		MongoCursor<Cart> cart = collection.find(query).as(Cart.class);
		Iterator<Cart> itr = cart.iterator();
		while(itr.hasNext()){
			Cart c = itr.next();
			Iterator<CartItem> cartItemitr = c.getCartItems().iterator();
			int i = 0;
			while(cartItemitr.hasNext()){
				long id = cartItemitr.next().getProductId();
				if(id == productId){
					c.getCartItems().get(i).setRating(rating);
					return;
				}
				i++;
			}
		}
	}
}