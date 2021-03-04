package core.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import core.services.CompanyService;

@RestController
@CrossOrigin
@RequestMapping("/company")
public class CompanyController extends ClientController {

	@Autowired
	private CompanyService service;

	@PostMapping("/add_coupon")
	private ResponseEntity<?> addCoupon() {
		return null;
	}

}
