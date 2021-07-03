package core.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import core.exceptions.CouponSystemException;
import core.services.HomeService;

@RestController
@CrossOrigin("*")
public class HomeController {

	@Autowired
	private HomeService service;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ResponseEntity<?> getAll() {
		try {
			return ResponseEntity.ok(this.service.findAll());
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

}
