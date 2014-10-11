package com.shopping.ui.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
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
	public String getProducts(@CookieValue(value="userId", defaultValue="-1") int userId, @RequestParam(value="id", required=true) Long id, Model model){
		if(userId == -1){
			System.out.println("Redirecting");
			return "redirect:/login";
		}
		
		try {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<ProductCatalogItem> product = restTemplate.getForEntity("http://localhost:8080/app/" + "api/products/"+id, ProductCatalogItem.class);
			model.addAttribute("item", product.getBody());
			System.out.println("Product received from RestTemplate is: " + product.getBody().getName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "product_details";
	}
	
	@RequestMapping(value="/newProduct", method = RequestMethod.GET)
	public String addNewProductView(@CookieValue(value="userId", defaultValue="-1") int userId, Model model){
		if(userId == -1){
			return "redirect:/login";
		}
		System.out.println("Inside /newProduct GET");
		return "new_product";
	}
}
