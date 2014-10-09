/**
 * 
 */
package com.shopping.database;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.datamodeling.ScanResultPage;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
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
		item.setIsOrdered(form.getIsOrdered());
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
				.withHashKeyValues(hashKeyValues)
				.withQueryFilterEntry("IsOrdered", 
						new Condition()
				.withComparisonOperator(ComparisonOperator.EQ)
				.withAttributeValueList(new AttributeValue().withN("0")));
		//add query filter
			
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

	public void removeItemFromCart(Long cartId) {
		DynamoDBMapper mapper = this.conn.getMapper();
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		Map<String, Condition> scanFilter = new HashMap<String, Condition>();
		Condition scanCondition = new Condition()
		    .withComparisonOperator(ComparisonOperator.EQ.toString())
		    .withAttributeValueList(new AttributeValue().withN(cartId.toString()));
		
		scanFilter.put("CartId", scanCondition);
        
		scanExpression.setScanFilter(scanFilter);
		System.out.println("Going to retrieve item.");
		PaginatedScanList<CartItem> items = mapper.scan(CartItem.class, scanExpression);
		System.out.println("Number of items retrived. " + items.size());
		System.out.println("Going to remove a cartItem with productId: " + items.get(0).getProductId());
		mapper.delete(items.get(0));
		
	}
	
	public CartItem increseQuantity(Long cartId){
		DynamoDBMapper mapper = this.conn.getMapper();
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		Map<String, Condition> scanFilter = new HashMap<String, Condition>();
		Condition scanCondition = new Condition()
		    .withComparisonOperator(ComparisonOperator.EQ.toString())
		    .withAttributeValueList(new AttributeValue().withN(cartId.toString()));
		
		scanFilter.put("CartId", scanCondition);
		scanExpression.setScanFilter(scanFilter);
		PaginatedScanList<CartItem> items = mapper.scan(CartItem.class, scanExpression);
		CartItem item = items.get(0);
		item.setQuantity(item.getQuantity() + 1);
		mapper.save(item);
		return item;
	}
	
	//returns list of items ordered so far by the user
	public CartItem decreaseQuantity(Long cartId){
		DynamoDBMapper mapper = this.conn.getMapper();
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		Map<String, Condition> scanFilter = new HashMap<String, Condition>();
		Condition scanCondition = new Condition()
		    .withComparisonOperator(ComparisonOperator.EQ.toString())
		    .withAttributeValueList(new AttributeValue().withN(cartId.toString()));
		
		scanFilter.put("CartId", scanCondition);
		scanExpression.setScanFilter(scanFilter);
		PaginatedScanList<CartItem> items = mapper.scan(CartItem.class, scanExpression);
		CartItem item = items.get(0);
		item.setQuantity(item.getQuantity() - 1);
		mapper.save(item);
		return item;
	}
	
	public List<CartItem> getOrderItemsFromUserId(int userId){
		//Declare mapper
		DynamoDBMapper mapper = this.conn.getMapper();

		//Create hash key values which to form a query
		CartItem hashKeyValues = new CartItem();
		hashKeyValues.setUserId(userId);

		//Form a query
		DynamoDBQueryExpression<CartItem> queryExpression = new DynamoDBQueryExpression<CartItem>()
				.withHashKeyValues(hashKeyValues)
				.withQueryFilterEntry("IsOrdered", 
						new Condition()
				.withComparisonOperator(ComparisonOperator.EQ)
				.withAttributeValueList(new AttributeValue().withN("1")));
		//add query filter
			
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
	
	public List<CartItem> placeOrder(int userId){
		List<CartItem> cartItems = getCartItemsFromUserId(userId);
		DynamoDBMapper mapper = this.conn.getMapper();
		for (CartItem cartItem : cartItems){
			cartItem.setIsOrdered(1);
			mapper.save(cartItem);
		}
		return cartItems;
	}
}