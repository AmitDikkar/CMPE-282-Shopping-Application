/**
 * 
 */
package com.shopping.app;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.amazonaws.services.cloudfront.model.Method;
import com.shopping.database.ProductCatalogCommands;
import com.shopping.database.ProductCatalogItem;

/**
 * @author Amit
 *
 */
@Controller
@RequestMapping(value="/api")
public class ProductController {
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	

	/*@RequestMapping(value="/timepass", method = RequestMethod.GET)
	public @ResponseBody TimePassClass getTimePass(@RequestParam(value="name", required=true) String name){
		TimePassClass tp = new TimePassClass();
		tp.setSampleInt(25);
		tp.setSampleString("amit");
		return tp;
	}*/
	
	/**
	 * Mapping for GET: /api/products
	 * @return
	 */
	@RequestMapping(value="/products", method = RequestMethod.GET)
	public ResponseEntity<List<ProductCatalogItem>> getAllProducts(){
		//Populate the list of available products.
		System.out.println("Called /products GET");
		List<ProductCatalogItem> listOfProducts = null;
		try {
			listOfProducts = new ProductCatalogCommands().getProductItems();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("returning list of products");
		return new ResponseEntity<List<ProductCatalogItem>>(listOfProducts, HttpStatus.OK);
	}
	
	/**
	 * Mapping for GET /api/products?id=104
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/products/{id}", method=RequestMethod.GET)
	public @ResponseBody ProductCatalogItem getProducts(@PathVariable Long id, Model model){
		System.out.println("Id received is" + id);
		ProductCatalogItem item = null;
		try {
			item = new ProductCatalogCommands().getProductItemById(id);
			return item;
		} catch (Exception e) {
			e.printStackTrace();
			return item;
		}
	}
	
	@RequestMapping(value="/products", method = RequestMethod.POST)
	public ResponseEntity<ProductCatalogItem> createProduct(@RequestBody ProductCatalogItem newItem, Model model) throws Exception{
		System.out.println("Inside /api/products POST, new item is: " +newItem.getId());
		ProductCatalogCommands comm = new ProductCatalogCommands();
		//no need to check if product already exists, if exists it simply overrides it.
		comm.addNewProduct(newItem);
		return new ResponseEntity<ProductCatalogItem>(newItem, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/views/timepasspage", method = RequestMethod.GET)
	public String displayTimePassView(){
		return "timepasspage";
	}
}