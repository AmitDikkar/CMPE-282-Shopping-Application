/**
 * 
 */
package com.shopping.pojo;

import java.util.List;

/**
 * @author Amit
 *
 */
public class Product {
	
	/**
	 * Id of the product
	 */
	long productId;
	
	/** Name of the product **/
	String productName;
	
	/** Description of the product**/
	String productDescription;
	
	/** Price of the product**/
	double price;
	
	/** Most similar products **/
	List<Product> relatedProducts;

	int quantity;
	
	String category;
	
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
		this.productId = productId;
	}

	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return the productDescription
	 */
	public String getProductDescription() {
		return productDescription;
	}

	/**
	 * @param productDescription the productDescription to set
	 */
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the relatedProducts
	 */
	public List<Product> getRelatedProducts() {
		return relatedProducts;
	}

	/**
	 * @param relatedProducts the relatedProducts to set
	 */
	public void setRelatedProducts(List<Product> relatedProducts) {
		this.relatedProducts = relatedProducts;
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
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}
}
