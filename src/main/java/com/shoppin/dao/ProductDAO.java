/**
 * 
 */
package com.shoppin.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jongo.MongoCollection;
import org.jongo.MongoCursor;

import com.shopping.database.MongoDb;
import com.shopping.pojo.Product;

/**
 * @author Amit
 *
 */
public class ProductDAO {

	public static MongoCollection collection;
	
	public ProductDAO(){
		collection = new MongoDb().productsCollection;	
	}
	
	/**
	 * Inserts new product in "products" collection.
	 * @param newProduct
	 */
	public void insert(Product newProduct){
		collection.insert(newProduct);
	}

	/**
	 * Finds the product by id.
	 * @param id
	 * @return
	 */
	public Product getById(Long id) {
		String query = "{productId:" + id + "}";
		Product dbDetails = collection.findOne(query).as(Product.class);
		return dbDetails;
	}

	/**
	 * Returns only first 6 records.
	 * TODO: return further 6 records.
	 * **/
	public List<Product> getAllProducts() {
		List<Product> products = new ArrayList<Product>();
		MongoCursor<Product> productsCursor = collection.find().limit(6).as(Product.class);
		System.out.println("cursor count: " + productsCursor.count());
		Iterator<Product> itr = productsCursor.iterator();
		
		while(itr.hasNext()){
			products.add(itr.next());
		}
		System.out.println("prodcuts count: " + products.size());
		return products;
	}
}
