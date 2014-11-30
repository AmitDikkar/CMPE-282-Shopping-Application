/**
 * 
 */
package com.shopping.database;

/**
 * @author Amit
 *
 */
public class CartItem {

	private int userId;
	
	private Long cartId;
	
	private Long productId;
	
	private int quantity;

	private double totalPrice;
	
	private ProductCatalogItem product;
	
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
		System.out.println("setter");
		this.productId = productId;
		try {
			//ProductCatalogCommands comm = new ProductCatalogCommands();
			//this.product = comm.getProductItemById(this.productId);
		} catch (Exception e) {
			e.printStackTrace();
		} 
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

	public double getTotalPrice() {
		ProductCatalogCommands comm;
		try {
			comm = new ProductCatalogCommands();
			this.totalPrice = comm.getProductItemById(this.productId).getPrice() * this.quantity;
			System.out.println("Object: Total price value in setter: " + this.totalPrice);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	/**
	 * @return the product
	 */
	public ProductCatalogItem getProduct() {
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(ProductCatalogItem product) {
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
