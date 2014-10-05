package com.shopping.ui.resources;

import java.net.URL;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.shopping.database.ProductCatalogCommands;
import com.shopping.database.ProductCatalogItem;

@Controller
public class HomeViewController {
	/**
	 * Simply selects the home view to render by returning its name.
	 * Returns our main page.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		System.out.println("Inside / GET");
		
		RestTemplate restTemplate = new RestTemplate();
		System.out.println("Calling /products GET");
		ResponseEntity<ProductCatalogItem[]> receivedList = restTemplate.getForEntity("http://localhost:8080/app/" + "api/products", ProductCatalogItem[].class);
		ProductCatalogItem[] listOfProducts =  receivedList.getBody();
		//add products list to model, to display it on UI.
		model.addAttribute("listOfProducts", listOfProducts);
		return "index";
	}
}