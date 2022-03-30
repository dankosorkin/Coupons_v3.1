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
import core.services.CompanyService;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/company")
public class CompanyController extends ClientController {

	@RequestMapping(value = "/add/coupon", method = RequestMethod.POST)
	private ResponseEntity<?> addCoupon(@RequestHeader String token, @RequestBody Coupon coupon) {
		try {
			return ResponseEntity.ok(getService(token).addCoupon(coupon));
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@RequestMapping(value = "/update/coupon", method = RequestMethod.PUT)
	private ResponseEntity<?> updateCoupon(@RequestHeader String token, @RequestBody Coupon coupon) {
		try {
			return ResponseEntity.ok(getService(token).updateCoupon(coupon));
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@RequestMapping(value = "/delete/coupon/{id}", method = RequestMethod.DELETE)
	private ResponseEntity<?> deleteCoupon(@RequestHeader String token, @PathVariable("id") Integer id) {
		try {
			return ResponseEntity.ok(getService(token).deleteCoupon(id));
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@RequestMapping(value = "/get/coupon/{id}", method = RequestMethod.GET)
	private ResponseEntity<?> getOneCoupon(@RequestHeader String token, @PathVariable("id") Integer id) {
		try {
			return ResponseEntity.ok(getService(token).getOneCoupon(id));
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

	protected CompanyService getService(String token) throws CouponSystemException {
		return (CompanyService) super.getService(token);
	}
}
