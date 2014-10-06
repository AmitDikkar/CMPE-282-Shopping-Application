/**
 * 
 */
package com.shopping.database;

import java.util.List;

import com.shopping.database.ProductCatalogItem;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;

/**
 * @author Amit
 *
 */
public class ProductCatalogCommands {
	
	/* A connection object which can give you a Object mapper.*/
	private DynamoDbConnection conn = null;
	
	public ProductCatalogCommands() throws Exception{
		this.conn = new DynamoDbConnection();
	}
	
	
	/**
	 * Returns a list of products which are currently available in the DynamoDB table.
	 * Returns only those products which has Quantity > 0.
	 * @return list of products
	 */
	public List<ProductCatalogItem> getProductItems() {
		//Declare mapper
		DynamoDBMapper mapper = this.conn.getMapper();
		
		//Declare a filter
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		scanExpression.addFilterCondition("Quantity", 
				new Condition()
		.withComparisonOperator(ComparisonOperator.GT)
		.withAttributeValueList(new AttributeValue().withN("0")));

		//Execute
		List<ProductCatalogItem> listOfItems = mapper.scan(ProductCatalogItem.class, scanExpression);

		//Print item names those are retrieved.
		for (ProductCatalogItem item : listOfItems) {
			System.out.println(item.getName());
		}
		return listOfItems;
	}

	//Done
	public List<ProductCatalogItem> getProductItemsByCategory(String category) {

		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		DynamoDBMapper mapper = this.conn.getMapper();

		scanExpression.addFilterCondition("Category", 
				new Condition()
		.withComparisonOperator(ComparisonOperator.EQ)
		.withAttributeValueList(new AttributeValue().withS(category)));

		List<ProductCatalogItem> listOfItems = mapper.scan(ProductCatalogItem.class, scanExpression);

		for (ProductCatalogItem item : listOfItems) {
			System.out.println(item.getName());
		}
		return listOfItems;
	}
	
	//Done
	public ProductCatalogItem getProductItemById(Long id) {
		//Declare mapper
		DynamoDBMapper mapper = this.conn.getMapper();

		//Create hash key values which to form a query
		ProductCatalogItem hashKeyValues = new ProductCatalogItem();
		hashKeyValues.setId(id);

		//Form a query
		DynamoDBQueryExpression<ProductCatalogItem> queryExpression = new DynamoDBQueryExpression<ProductCatalogItem>()
				.withHashKeyValues(hashKeyValues);
		queryExpression.setHashKeyValues(hashKeyValues);

		//execute that query
		List<ProductCatalogItem> itemList = mapper.query(ProductCatalogItem.class, queryExpression);

/*		for (int i = 0; i < itemList.size(); i++) {
			System.out.println(itemList.get(i).getName());
			System.out.println(itemList.get(i).getCategory());
		}*/

		//return the first item (we will have only one item)
		return itemList.get(0);
	}


	//Done
	public void decrementProductQuantity(Long id) {
		DynamoDBMapper mapper = this.conn.getMapper();
		ProductCatalogItem item = getProductItemById(id);
		item.decrementQuantity();
		mapper.save(item);
	}

	//Done
	public void incrementProductQuantity(Long id){
		DynamoDBMapper mapper = this.conn.getMapper();
		ProductCatalogItem item = getProductItemById(id);
		item.incrementQuantity();
		mapper.save(item);
	}

	//Done
	public void addNewProduct(ProductCatalogItem item){
		DynamoDBMapper mapper = this.conn.getMapper();
		mapper.save(item);
	}
}
