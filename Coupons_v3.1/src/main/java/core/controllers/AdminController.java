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

import core.entities.Company;
import core.entities.Customer;
import core.exceptions.CouponSystemException;
import core.services.AdminService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class AdminController extends ClientController {

	private AdminService service;

	@RequestMapping(value = "/add/company", method = RequestMethod.POST)
	public ResponseEntity<?> addCompany(@RequestHeader String token, @RequestBody Company company) {
		try {
			service = (AdminService) super.getService(token);
			return ResponseEntity.ok(service.addCompany(company));
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	@RequestMapping(value = "/update/company", method = RequestMethod.PUT)
	public ResponseEntity<?> updateCompany(@RequestHeader String token, @RequestBody Company company) {
		try {
			service = (AdminService) super.getService(token);
			return ResponseEntity.ok(service.updateCompany(company));
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	@RequestMapping(value = "/delete/company/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteCompany(@RequestHeader String token, @PathVariable Integer id) {
		try {
			service = (AdminService) super.getService(token);
			return ResponseEntity.ok(service.deleteCompany(id));
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	@RequestMapping(value = "/get/company/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getOneCompany(@RequestHeader String token, @PathVariable Integer id) {
		try {
			service = (AdminService) super.getService(token);
			return ResponseEntity.ok(service.getOneCompany(id));
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	@RequestMapping(value = "/get/companies", method = RequestMethod.GET)
	public ResponseEntity<?> getAllCompanies(@RequestHeader String token) throws CouponSystemException {
		try {
			service = (AdminService) super.getService(token);
			return ResponseEntity.ok(service.getAllCompanies());
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	@RequestMapping(value = "/add/customer", method = RequestMethod.POST)
	public ResponseEntity<?> addCustomer(@RequestBody Customer customer) throws CouponSystemException {

		return new ResponseEntity<Customer>(service.addCustomer(customer), HttpStatus.OK);

	}

	@RequestMapping(value = "/update/customer", method = RequestMethod.PUT)
	public ResponseEntity<?> updateCustomer(@RequestBody Customer customer) throws CouponSystemException {
		service.updateCustomer(customer);
		return new ResponseEntity<>(HttpStatus.OK);

	}

	@RequestMapping(value = "/delete/customer/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteCustomer(@PathVariable Integer id) throws CouponSystemException {

		return new ResponseEntity<Customer>(service.deleteCustomer(id), HttpStatus.OK);

	}

	@RequestMapping(value = "/get/customer/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getOneCustomer(@PathVariable Integer id) throws CouponSystemException {

		return new ResponseEntity<Customer>(service.getOneCustomer(id), HttpStatus.OK);

	}

	@RequestMapping(value = "/get/customers", method = RequestMethod.GET)
	public ResponseEntity<?> getAllCustomers() throws CouponSystemException {

		return new ResponseEntity<>(service.getAllCustomers(), HttpStatus.OK);

	}

}
