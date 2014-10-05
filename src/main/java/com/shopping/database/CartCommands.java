/**
 * 
 */
package com.shopping.database;

import java.util.List;
import java.util.UUID;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.shopping.dto.AddToCartForm;

/**
 * @author Amit
 *
 */
/**
 * @author Amit
 *
 */
public class CartCommands {

	/* A connection object which can give you a Object mapper.*/
	private DynamoDbConnection conn = null;

	public CartCommands() throws Exception{
		this.conn = new DynamoDbConnection();
	}

	
	/**
	 * Saves Item into the cart. - DynamoDB.
	 * @param form
	 */
	public void saveCartItem(AddToCartForm form) {
		DynamoDBMapper mapper = this.conn.getMapper();
		CartItem item = new CartItem();
		item.setCartId(UUID.randomUUID().getLeastSignificantBits());
		item.setProductId(form.getProductId());
		item.setQuantity(form.getQuantity());
		item.setUserId(form.getUserId());
		mapper.save(item);
	}

	
	/**
	 * Returns list of all items that are in cart for that user.
	 * @param userId
	 * @return
	 */
	public List<CartItem> getCartItemsFromUserId(int userId){
		//Declare mapper
		DynamoDBMapper mapper = this.conn.getMapper();

		//Create hash key values which to form a query
		CartItem hashKeyValues = new CartItem();
		hashKeyValues.setUserId(userId);

		//Form a query
		DynamoDBQueryExpression<CartItem> queryExpression = new DynamoDBQueryExpression<CartItem>()
				.withHashKeyValues(hashKeyValues);
		queryExpression.setHashKeyValues(hashKeyValues);

		//execute that query
		List<CartItem> itemList = mapper.query(CartItem.class, queryExpression);

		for (int i = 0; i < itemList.size(); i++) {
			System.out.println(itemList.get(i).getUserId());
			System.out.println(itemList.get(i).getCartId());
			System.out.println(itemList.get(i).getProductId());
			System.out.println(itemList.get(i).getQuantity());
		}
		
		//return the first item (we will have only one item)
		return itemList;
	}
}