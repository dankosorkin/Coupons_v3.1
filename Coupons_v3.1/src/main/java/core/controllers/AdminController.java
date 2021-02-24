package core.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import core.entities.Company;
import core.exceptions.CouponSystemException;
import core.services.AdminService;

@RestController
@CrossOrigin
@RequestMapping("/administrator")
public class AdminController extends ClientController {

	@Autowired
	private AdminService service;

	@GetMapping("/add_company")
	public ResponseEntity<?> addCompany(Company company) {
		return null;
	}

	@Override
	public boolean login(String email, String password) throws CouponSystemException {
		// TODO Auto-generated method stub
		return false;
	}

}
