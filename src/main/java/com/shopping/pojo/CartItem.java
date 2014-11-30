/**
 * 
 */
package com.shopping.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shoppin.dao.ProductDAO;

/**
 * @author Amit
 *
 *	This is an important class to send it to the 
 */
public class CartItem {

	/** User who has added this item to the cart.
	 *  Also, user who has rated this product.
	 * **/
	long userId;
	
	/** Id of this cart item**/
	long cartItemId;
	
	/** Count of how many times this item has been added**/
	int quantity;
	
	/** product id**/
	long productId;
	
	/** rating given to the product **/
	int rating;
	
	/** Price of products**/
	double totalPrice;
	
	@JsonIgnore
	Product product;

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

	/**
	 * @return the cartItemId
	 */
	public long getCartItemId() {
		return cartItemId;
	}

	/**
	 * @param cartItemId the cartItemId to set
	 */
	public void setCartItemId(long cartItemId) {
		this.cartItemId = cartItemId;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
		ProductDAO productDao = new ProductDAO();
		double productPrice = productDao.getById(this.productId).getPrice();
		this.totalPrice = productPrice * this.quantity;
	}

	/**
	 * @return the productId
	 */
	public long getProductId() {
		return productId;
	}

	/**
	 * @param productId the productId to set
	 */
	public void setProductId(long productId) {
		ProductDAO productDao = new ProductDAO();
		this.product = productDao.getById(productId);
		this.productId = productId;
	}

	/**
	 * @return the rating
	 */
	public int getRating() {
		return rating;
	}

	/**
	 * @param rating the rating to set
	 */
	public void setRating(int rating) {
		this.rating = rating;
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
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}
}
