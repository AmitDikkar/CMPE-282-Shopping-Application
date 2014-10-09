/**
 * 
 */
package com.shopping.dto;

/**
 * @author Amit
 *
 */
public class AddToCartForm {

	public Long productId;
	
	public int userId;
	
	public int quantity;
	
	public int isOrdered;
	
	/**
	 * @return the productId
	 */
	public Long getProductId() {
		return productId;
	}
	/**
	 * @param productId the productId to set
	 */
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
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
	}
	/**
	 * @return the isOrdred
	 */
	public int getIsOrdered() {
		return isOrdered;
	}
	/**
	 * @param isOrdred the isOrdred to set
	 */
	public void setIsOrdred(int isOrdered) {
		this.isOrdered = isOrdered;
	}
}
