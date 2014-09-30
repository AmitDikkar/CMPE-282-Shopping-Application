/**
 * 
 */
package com.shopping.database;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

/**
 * @author Amit
 *
 */
@DynamoDBTable(tableName="ProductCatalog")
public class ProductCatalogItem {

	private Long id;
	private String name;
	private String category;
	private String description;
	private Integer price;
	private Integer quantity;
	
	/**
	 * @return the id
	 */
	@DynamoDBHashKey(attributeName="Id") 
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	@DynamoDBAttribute(attributeName="Name")
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the category
	 */
	@DynamoDBRangeKey(attributeName="Category")
	public String getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	/**
	 * @return the description
	 */
	@DynamoDBAttribute(attributeName="Description") 
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the price
	 */
	@DynamoDBAttribute(attributeName="Price")
	public Integer getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(Integer price) {
		this.price = price;
	}
	/**
	 * @return the quantity
	 */
	@DynamoDBAttribute(attributeName="Quantity")
	public Integer getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	/* Decrements quantity of the product.*/
	public void decrementQuantity() {
		quantity = quantity - 1;
	}
	
	/* Increments quantity of the product.*/
	public void incrementQuantity() {
		quantity = quantity + 1;
	}	
}

