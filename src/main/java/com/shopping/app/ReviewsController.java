/**
 * 
 */
package com.shopping.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shoppin.dao.CartDAO;
import com.shopping.dto.ProductRatingForm;

/**
 * @author Amit
 *
 */
@Controller
@RequestMapping(value="/api")
public class ReviewsController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping(value="/review", method = RequestMethod.POST)
	public ResponseEntity<String> reviewProduct(@RequestBody ProductRatingForm review){
		CartDAO cartDao = new CartDAO();
		System.out.println("Inside /api/review POST");
		//System.out.println("cartId is:"+review.getCartId() + " rating is: " +review.getRating());
		long userId = review.getUserId();
		long productId = review.getProductId();
		int rating = review.getRating();
		
		cartDao.updateRating(userId, productId, rating);
		
		return new ResponseEntity<String>("Rated Successfully", HttpStatus.OK);
	}
}
