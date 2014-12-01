/**
 * 
 */
package com.shopping.database;

import java.net.UnknownHostException;

import org.jongo.Jongo;
import org.jongo.MongoCollection;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

/**
 * @author Amit
 *
 */
public class MongoDb {
	
	public MongoCollection usersCollection;
	
	public MongoCollection cartsCollection;
	
	public MongoCollection productsCollection;
	
	public MongoCollection usersTestCollection;
	
	public MongoCollection productsTestCollection;

	public MongoDb(){
		MongoClient client = null;
		try {
			client = new MongoClient(new ServerAddress("ds053310.mongolab.com",53310));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("!!!!Error in connection");
		}
		DB database = client.getDB("cmpe_282");
		String username = "team_282";
		String pwd = "team_282";
		char[] password = pwd.toCharArray();
		boolean auth = database.authenticate(username, password);
		Jongo jongo = new Jongo(database);
		cartsCollection = jongo.getCollection("carts");
		productsCollection = jongo.getCollection("products");
		usersCollection = jongo.getCollection("users");
		usersTestCollection = jongo.getCollection("usersTest");
		productsTestCollection = jongo.getCollection("productsTest");
	}
	
}
