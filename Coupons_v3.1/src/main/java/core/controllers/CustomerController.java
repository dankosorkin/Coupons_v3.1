package core.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import core.entities.Category;
import core.entities.Coupon;
import core.exceptions.CouponSystemException;
import core.services.CustomerService;

@RestController
@CrossOrigin("*")
@RequestMapping("/customer")
public class CustomerController extends ClientController {

	@RequestMapping(value = "/purchase/coupon", method = RequestMethod.POST)
	private ResponseEntity<?> purchaseCoupon(@RequestHeader String token, @RequestBody Coupon coupon) {
		try {
			return ResponseEntity.ok(getService(token).addPurchase(coupon));
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@RequestMapping(value = "/purchase/delete/{id}", method = RequestMethod.DELETE)
	private ResponseEntity<?> deletePurchase(@RequestHeader String token, @PathVariable("id") Integer id) {
		try {
			return ResponseEntity.ok(getService(token).deletePurchase(id));
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@RequestMapping(value = "/get/coupon/{id}", method = RequestMethod.GET)
	private ResponseEntity<?> getCoupon(@RequestHeader String token, @PathVariable("id") Integer id) {
		try {
			return ResponseEntity.ok(getService(token).getCoupon(id));
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@RequestMapping(value = "/get/coupons", method = RequestMethod.GET)
	private ResponseEntity<?> getAllCoupons(@RequestHeader String token) {
		try {
			return ResponseEntity.ok(getService(token).getAllCoupons());
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@RequestMapping(value = "/get/coupons/category/{category}", method = RequestMethod.GET)
	private ResponseEntity<?> getAllByCategory(@RequestHeader String token, @PathVariable("category") String category) {
		try {
			return ResponseEntity.ok(getService(token).getAllByCategory(Category.valueOf(category)));
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@RequestMapping(value = "/get/coupons/price/{price}", method = RequestMethod.GET)
	private ResponseEntity<?> getAllByPrice(@RequestHeader String token, @PathVariable("price") Double price) {
		try {
			return ResponseEntity.ok(getService(token).getAllByPrice(price));
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@RequestMapping(value = "/get/details", method = RequestMethod.GET)
	private ResponseEntity<?> getDetails(@RequestHeader String token) {
		try {
			return ResponseEntity.ok(getService(token).getDetails());
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	protected CustomerService getService(String token) throws CouponSystemException {
		return (CustomerService) super.getService(token);
	}

}
