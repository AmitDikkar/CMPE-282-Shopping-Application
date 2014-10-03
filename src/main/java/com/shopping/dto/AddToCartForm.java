/**
 * 
 */
package com.shopping.dto;

/**
 * @author Amit
 *
 */
public class AddToCartForm {

	Long produtId;
	int userId;
	int quantity;
	/**
	 * @return the produtId
	 */
	public Long getProdutId() {
		return produtId;
	}
	/**
	 * @param produtId the produtId to set
	 */
	public void setProdutId(Long produtId) {
		this.produtId = produtId;
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
	
}
