package core.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import core.entities.Company;
import core.entities.Customer;
import core.exceptions.CouponSystemException;
import core.services.AdminService;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController extends ClientController {

	@Autowired
	private AdminService service;

	@RequestMapping(value = "/add/company", method = RequestMethod.POST)
	public ResponseEntity<?> addCompany(@RequestBody Company company) throws CouponSystemException {

		return new ResponseEntity<Company>(service.addCompany(company), HttpStatus.OK);

	}

	@RequestMapping(value = "/update/company", method = RequestMethod.PUT)
	public ResponseEntity<?> updateCompany(@RequestBody Company company) throws CouponSystemException {
		service.updateCompany(company);
		return new ResponseEntity<Company>(HttpStatus.OK);

	}

	@RequestMapping(value = "/delete/company/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteCompany(@PathVariable Integer id) throws CouponSystemException {

		return new ResponseEntity<Company>(service.deleteCompany(id), HttpStatus.OK);

	}

	@RequestMapping(value = "/get/company/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getOneCompany(@PathVariable Integer id) throws CouponSystemException {

		return new ResponseEntity<Company>(service.getOneCompany(id), HttpStatus.OK);

	}

	@RequestMapping(value = "/get/companies", method = RequestMethod.GET)
	public ResponseEntity<?> getAllCompanies() throws CouponSystemException {

		return new ResponseEntity<>(service.getAllComapnies(), HttpStatus.OK);

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

	@Override
	public boolean login(String email, String password) throws CouponSystemException {
		// TODO Auto-generated method stub
		return false;
	}

}
