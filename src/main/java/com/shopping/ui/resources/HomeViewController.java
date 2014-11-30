package com.shopping.ui.resources;

import java.net.URL;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.shoppin.dao.UserDAO;
import com.shopping.config.DevConfiguration;
import com.shopping.database.ProductCatalogCommands;
import com.shopping.database.ProductCatalogItem;
import com.shopping.pojo.User;

import org.springframework.web.bind.annotation.CookieValue;

@Controller
public class HomeViewController {
	/**
	 * Simply selects the home view to render by returning its name.
	 * Returns our main page.
	 */
	@Autowired DevConfiguration conf;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(@CookieValue(value = "userId", defaultValue = "-1") int firstCookie, Locale locale, Model model) {
		System.out.println("Inside /app/ GET");
		System.out.println("Received Cookie: " + firstCookie);
		
		//later we will put this code in the intercepter/filter.
		if(firstCookie == -1){
			System.out.println("Yes its hello");
			return "redirect:/login";
		}

		RestTemplate restTemplate = new RestTemplate();
		System.out.println("Calling /products GET");

		//ResponseEntity<ProductCatalogItem[]> receivedList = restTemplate.getForEntity("http://127.0.0.1:8080/api/products", ProductCatalogItem[].class);
		//ResponseEntity<ProductCatalogItem[]> receivedList = restTemplate.getForEntity(conf.getBASE_URL() +"/api/products", ProductCatalogItem[].class);
		//ProductCatalogItem[] listOfProducts =  receivedList.getBody();
		//model.addAttribute("listOfProducts", listOfProducts);
		
		//TODO: call new REST API to get Item-Item recommendation.
		ResponseEntity<ProductCatalogItem[]> list_1 = restTemplate.getForEntity(conf.getBASE_URL() +"/api/userbasedproducts?userId=1", ProductCatalogItem[].class);
		ProductCatalogItem[] userBasedRecoProducts =  list_1.getBody();
		
		//TODO: call new REST API to get User-User recommendation.
		ResponseEntity<ProductCatalogItem[]> list_2 = restTemplate.getForEntity(conf.getBASE_URL() +"/api/userbasedproducts?userId=1", ProductCatalogItem[].class);
		ProductCatalogItem[] itemBasedRecoProducts =  list_2.getBody();
		//add products list to model, to display it on UI.
		
		ResponseEntity<ProductCatalogItem[]> receivedList = restTemplate.getForEntity(conf.getBASE_URL() +"/api/products", ProductCatalogItem[].class);
		ProductCatalogItem[] listOfProducts = receivedList.getBody();
		
		ProductCatalogItem p = listOfProducts[0];
		System.out.println("inside homeview controller. list count is: " + listOfProducts.length + " 0the product is: " + p.getId());
		
		model.addAttribute("products", listOfProducts);
		model.addAttribute("userBasedProducts", userBasedRecoProducts);
		model.addAttribute("itemBasedProducts", itemBasedRecoProducts);
		
		return "index";
	}
}
