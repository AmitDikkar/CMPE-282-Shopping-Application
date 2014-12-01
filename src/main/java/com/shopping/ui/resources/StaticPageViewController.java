package com.shopping.ui.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shopping.database.CartItem;
import com.shopping.database.ProductCatalogItem;
import com.shopping.jdbc.AlgoRequest;
import com.shopping.jdbc.JavaMysqlSelectExample;
import com.shopping.jdbc.item;

@Controller
public class StaticPageViewController {

/*	@RequestMapping(value="/orderSuccess", method = RequestMethod.GET)
	public String orderSuccessful(Model model){
		
		model.addAttribute("title", "Congratulations.");
		model.addAttribute("description", "Congratulations!! Your order has been successfully placed. You can always check your"
				+ "order history in your account.");
		return "orderstatussuccess";
	}
	*/

	//get list of orders that user has placed so far
	@RequestMapping(value="/orderSuccess", method = RequestMethod.GET)
	public String getSuccessView(Model model){
		System.out.println("Inside /orderSucess GET");
		String statusTitle = "Congratulations";
		String statusDescription = "Your order has been placed.";
		model.addAttribute("statusTitle", statusTitle);
		model.addAttribute("statusDescription", statusDescription);
		return "orderstatussuccess";
	}
	
	@RequestMapping(value = "/algos", method = RequestMethod.GET)
	public String getItemsAlgos(@RequestParam(value="itemId", required=true) long itemId, Model model){
		List<item> items = new ArrayList<item>();
		JavaMysqlSelectExample j = new JavaMysqlSelectExample();
		items = j.total((int)itemId);
		System.out.println("item is: " + items.get(0).item);
		model.addAttribute("items", items);
		System.out.println("added model");
		return "algos_1";
	}
	
	@RequestMapping(value = "/algos", method = RequestMethod.POST)
	public String getAlgosForUser(@ModelAttribute AlgoRequest req){
		System.out.println("item id received" + req.getItemId());
		return "redirect:/algos?itemId=" + req.getItemId();
	}
}
