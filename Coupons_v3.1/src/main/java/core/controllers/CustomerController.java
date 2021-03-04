package core.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import core.services.CustomerService;

@RestController
@CrossOrigin
@RequestMapping("/customer")
public class CustomerController extends ClientController {

	@Autowired
	private CustomerService service;

	@RequestMapping(value = "/purchase", method = RequestMethod.PUT)
	public void purchaseCoupon() {

	}

}
