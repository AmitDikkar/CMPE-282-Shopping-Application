/**
 * 
 */
package com.shopping.database;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

/**
 * @author Amit
 *
 */
public class DynamoDbConnection {
	private AmazonDynamoDBClient client;
	private DynamoDBMapper mapper;
	
	public DynamoDbConnection() throws Exception {
		createClient();
	}
	
	public void createClient() throws Exception {
        AWSCredentials credentials = new BasicAWSCredentials("AKIAIWB2ZNKYS5MHDXVQ", "BmNQHBa8s9BuJRQSe5ATUCIoi7zOPkm5TP/kgkY/");
        client = new AmazonDynamoDBClient(credentials);
        client.setRegion(Region.getRegion(Regions.US_WEST_2));
    }

	/**
	 * Returns DynamoDBMapper which will be helpful in scanning/Querying DynamoDB database.
	 * @return the mapper
	 */
	public DynamoDBMapper getMapper() {
		DynamoDBMapper mapper = new DynamoDBMapper(client);
		return mapper;
	}
	
}
