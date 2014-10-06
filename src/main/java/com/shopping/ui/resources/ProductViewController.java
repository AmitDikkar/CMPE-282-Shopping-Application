package com.shopping.ui.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.shopping.database.ProductCatalogCommands;
import com.shopping.database.ProductCatalogItem;

@Controller
public class ProductViewController {
	
	//returns prduct details page of the given product id.
	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public String getProducts(@RequestParam(value="id", required=true) Long id, Model model){
		try {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<ProductCatalogItem> product = restTemplate.getForEntity("http://localhost:8080/app/" + "api/products/"+id, ProductCatalogItem.class);
			model.addAttribute("item", product.getBody());
			model.addAttribute("userxx", 1);
			System.out.println("Product received from RestTemplate is: " + product.getBody().getName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "product_details";
	}
}
