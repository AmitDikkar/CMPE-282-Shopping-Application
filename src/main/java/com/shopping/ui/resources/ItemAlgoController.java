/**
 * 
 */
package com.shopping.ui.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shopping.jdbc.JavaMysqlSelectExample;
import com.shopping.jdbc.item;;
/**
 * @author Amit
 *
 */
@Controller
public class ItemAlgoController {

/*	@RequestMapping(value = "/algos/{itemId}", method = RequestMethod.GET)
	public String getItemsAlgos(@PathVariable Long itemId, Model model){
		List<item> items = new ArrayList<item>();
		JavaMysqlSelectExample j = new JavaMysqlSelectExample();
		items = j.total(55);
		model.addAttribute("items", items);
		System.out.println("added model");
		return "algos_1";
	}*/
}
