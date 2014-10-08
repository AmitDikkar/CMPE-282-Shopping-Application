/**
 * 
 */
package com.shopping.database;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

/**
 * @author Amit
 *
 */
@DynamoDBTable(tableName="Cart")
public class CartItem {

	private int userId;
	
	private Long cartId;
	
	private Long productId;
	
	private int quantity;

	private float totalPrice;
	
	private ProductCatalogItem product;
	/**
	 * @return the userId
	 */
	@DynamoDBHashKey(attributeName="UserId")
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
	@DynamoDBRangeKey(attributeName="CartId")
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
	@DynamoDBAttribute(attributeName="ProductId")
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
			ProductCatalogCommands comm = new ProductCatalogCommands();
			this.product = comm.getProductItemById(this.productId);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	/**
	 * @return the quantity
	 */
	@DynamoDBAttribute(attributeName="Quantity")
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@DynamoDBIgnore
	public float getTotalPrice() {
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
	@DynamoDBIgnore
	public ProductCatalogItem getProduct() {
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(ProductCatalogItem product) {
		this.product = product;
	}
}
