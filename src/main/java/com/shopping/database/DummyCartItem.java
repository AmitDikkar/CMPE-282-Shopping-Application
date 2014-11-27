/**
 * 
 */
package com.shopping.database;

/**
 * @author Amit
 *
 */
public class DummyCartItem {
	private int userId;
	
	private Long cartId;
	
	private Long productId;
	
	private int quantity;

	private float totalPrice;
	
	private DumpyProductCatalogItem product;
	
	private int isOrdered;

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
	 * @return the cartId
	 */
	public Long getCartId() {
		return cartId;
	}

	/**
	 * @param cartId the cartId to set
	 */
	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

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
	 * @return the totalPrice
	 */
	public float getTotalPrice() {
		return totalPrice;
	}

	/**
	 * @param totalPrice the totalPrice to set
	 */
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	/**
	 * @return the product
	 */
	public DumpyProductCatalogItem getProduct() {
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(DumpyProductCatalogItem product) {
		this.product = product;
	}

	/**
	 * @return the isOrdered
	 */
	public int getIsOrdered() {
		return isOrdered;
	}

	/**
	 * @param isOrdered the isOrdered to set
	 */
	public void setIsOrdered(int isOrdered) {
		this.isOrdered = isOrdered;
	}
}
