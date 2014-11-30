/**
 * 
 */
package com.shopping.pojo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Amit
 *
 */
public class Cart {

	/** To which user this cart belongs to. **/
	long userId;
	
	/** Id of the cart.**/
	long cartId;
	
	/** List of items that has been added to the cart.**/
	List<CartItem> cartItems = new ArrayList<CartItem>();
	
	/** Total price of all items that has been added to this cart.**/
	double totalPrice;
	
	/** If this cart has been processed for the order.
	 *  For one user there will be only one cart which is purchased.
	 * **/
	boolean isPurchased;

	/**
	 * @return the cartId
	 */
	public long getCartId() {
		return cartId;
	}

	/**
	 * @param cartId the cartId to set
	 */
	public void setCartId(long cartId) {
		this.cartId = cartId;
	}

	/**
	 * @return the cartItems
	 */
	public List<CartItem> getCartItems() {
		return cartItems;
	}

	/**
	 * @param cartItems the cartItems to set
	 */
	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	/**
	 * @return the totalPrice
	 */
	public double getTotalPrice() {
		return totalPrice;
	}

	/**
	 * @param totalPrice the totalPrice to set
	 */
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	/**
	 * @return the isPurchased
	 */
	public boolean isPurchased() {
		return isPurchased;
	}

	/**
	 * @param isPurchased the isPurchased to set
	 */
	public void setPurchased(boolean isPurchased) {
		this.isPurchased = isPurchased;
	}

	/**
	 * @return the userId
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	public void addToCart(CartItem cartItem) {
		
		//if item already exist do not add a new one. instead increase the quantity.
		for(int i=0; i< this.cartItems.size(); i++){
			if(this.cartItems.get(i).getProductId() == cartItem.getProductId()){
				this.cartItems.get(i).setQuantity(this.cartItems.get(i).getQuantity() + cartItem.getQuantity());
				computeTotalPrice();
				return;
			}
		}
		this.cartItems.add(cartItem);
		computeTotalPrice();
	}

	private void computeTotalPrice() {
		double totalPrice = 0;
		for (CartItem item : this.cartItems) {
			totalPrice += item.totalPrice;
		}
		this.totalPrice = totalPrice;
	}

	public void removeProduct(Long productId) {
		Iterator<CartItem> itr = this.cartItems.iterator();
		int i = 0;
		boolean listUpdated = false;
		while(itr.hasNext()){
			if(itr.next().getProductId() == productId){
				this.cartItems.remove(i);
				listUpdated = true;
				break;
			}
			i++;
		}
		
		if(listUpdated){
			computeTotalPrice();	
		}
	}
}
