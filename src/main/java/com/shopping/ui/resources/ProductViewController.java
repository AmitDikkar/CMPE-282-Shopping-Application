package com.shopping.ui.resources;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shopping.database.ProductCatalogCommands;
import com.shopping.database.ProductCatalogItem;

@Controller
public class ProductViewController {
	
	//returns our main product details page.
	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public String getProducts(@RequestParam(value="id", required=true) Long id, Model model){
		model.addAttribute("serverTime", "This is /register:GET");
		System.out.println("Id received is" + id);
		try {
			ProductCatalogItem item = new ProductCatalogCommands().getProductItemById(id);
			model.addAttribute("item", item);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "product_details";
	}
}
