/**
 * 
 */
package com.shopping.app;

import java.util.ArrayList;
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
import com.shoppin.dao.ProductDAO;
import com.shoppin.dao.UserDAO;
import com.shopping.database.ProductCatalogCommands;
import com.shopping.database.ProductCatalogItem;
import com.shopping.pojo.Product;

/**
 * @author Amit
 *
 */
@Controller
@RequestMapping(value="/api")
public class ProductController {
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	ProductDAO productDao = new ProductDAO();
	
	/**
	 * Done-Returns available products for sale. only 6 products are returned.
	 * Mapping for GET: /api/products
	 * @return
	 */
	@RequestMapping(value="/products", method = RequestMethod.GET)
	public ResponseEntity<List<ProductCatalogItem>> getAllProducts(){
		System.out.println("Called /app/api/products GET");

		List<ProductCatalogItem> listOfProducts = new ArrayList<ProductCatalogItem>();

		try {
			/*listOfProducts = new ProductCatalogCommands().getProductItems();*/
			List<Product> ls = productDao.getAllProducts();
			listOfProducts = castToProductCatalogItems(ls);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("returning list of products. Count is: " + listOfProducts.size());
		return new ResponseEntity<List<ProductCatalogItem>>(listOfProducts, HttpStatus.OK);
	}

	/**
	 * Done-Mapping for GET /api/products?id=104
	 * Returns the requested product.
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/products/{id}", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ProductCatalogItem> getProducts(@PathVariable Long id, Model model){
		System.out.println("Id received is" + id);
		ProductCatalogItem item = new ProductCatalogItem();

		Product product = productDao.getById(id);
		
		if(product == null){
			System.out.println("this is null");
			return new ResponseEntity<ProductCatalogItem>(HttpStatus.NO_CONTENT);
		}
		
		item.setDescription(product.getProductDescription());
		item.setName(product.getProductName());
		item.setId(product.getProductId());
		item.setPrice(product.getPrice());
		item.setQuantity(product.getQuantity());
		item.setCategory(product.getCategory());
		
		return new ResponseEntity<ProductCatalogItem>(item, HttpStatus.OK);
	}

	/**
	 * Done-Creates a new product.
	 * @param newItem
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/products", method = RequestMethod.POST)
	public ResponseEntity<ProductCatalogItem> createProduct(@RequestBody ProductCatalogItem newItem, Model model) throws Exception{

		System.out.println("Inside /api/products POST, new item is: " +newItem.getId());
		Product newProduct = new Product();
		newProduct.setPrice(newItem.getPrice());
		newProduct.setProductDescription(newItem.getDescription());
		newProduct.setProductName(newItem.getName());
		newProduct.setProductId(8524);
		newProduct.setCategory(newItem.getCategory());
		
		productDao.insert(newProduct);

		return new ResponseEntity<ProductCatalogItem>(newItem, HttpStatus.CREATED);
	}

	@RequestMapping(value="/userbasedproducts")
	public ResponseEntity<List<ProductCatalogItem>> getUserBasedProducts(@RequestParam(value="userId", required=true) long userId, Model model) {

		//TODO do mahout based

/*		ProductCatalogItem item1 = new ProductCatalogItem();
		item1.setId((long)1);
		item1.setName("item-1");
		item1.setPrice(10);

		ProductCatalogItem item2 = new ProductCatalogItem();
		item2.setId((long)1);
		item2.setName("item-1");
		item2.setPrice(10);

		List<ProductCatalogItem> items = new ArrayList<ProductCatalogItem>();
		items.add(item1);
		items.add(item2);*/
		UserDAO userDao = new UserDAO();
		List<Product> lsProducts = userDao.getRecommendedProducts(userId);
		
		if(lsProducts == null){
			return new ResponseEntity<List<ProductCatalogItem>>(HttpStatus.NO_CONTENT);
		}
		List<ProductCatalogItem> items = castToProductCatalogItems(lsProducts);
		
		return new ResponseEntity<List<ProductCatalogItem>>(items, HttpStatus.OK);
	}

	@RequestMapping(value="/itembasedproducts")
	public ResponseEntity<List<ProductCatalogItem>> getItemBasedProducts(@RequestParam(value="userId", required=true) int userId, Model model) {

		//TODO do mahout based

		ProductCatalogItem item1 = new ProductCatalogItem();
		item1.setId((long)1);
		item1.setName("item-1");
		item1.setPrice(10);

		ProductCatalogItem item2 = new ProductCatalogItem();
		item2.setId((long)1);
		item2.setName("item-1");
		item2.setPrice(10);

		List<ProductCatalogItem> items = new ArrayList<ProductCatalogItem>();
		items.add(item1);
		items.add(item2);
		return new ResponseEntity<List<ProductCatalogItem>>(items, HttpStatus.OK);
	}
	
	public List<ProductCatalogItem> castToProductCatalogItems(List<Product> products) {
		List<ProductCatalogItem> items = new ArrayList<ProductCatalogItem>();
		for (Product product : products) {
			ProductCatalogItem item = new ProductCatalogItem();
			item.setId(product.getProductId());
			item.setName(product.getProductName());
			item.setPrice(product.getPrice());
			item.setQuantity(product.getQuantity());
			item.setDescription(product.getProductDescription());
			item.setCategory(product.getCategory());
			items.add(item);
		}
		return items;
	}
}