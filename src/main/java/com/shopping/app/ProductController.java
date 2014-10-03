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
	
	
	@RequestMapping(value="/views/timepasspage", method = RequestMethod.GET)
	public String displayTimePassView(){
		return "timepasspage";
	}
}