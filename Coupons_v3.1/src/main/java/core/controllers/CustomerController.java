package core.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import core.exceptions.CouponSystemException;
import core.services.CustomerService;

@RestController
@CrossOrigin
@RequestMapping("/customer")
public class CustomerController extends ClientController {

	@Autowired
	private CustomerService service;

	@PostMapping("/add_coupon")
	public void addCoupon() {

	}

	@Override
	public boolean login(String email, String password) throws CouponSystemException {
		// TODO Auto-generated method stub
		return false;
	}

}
