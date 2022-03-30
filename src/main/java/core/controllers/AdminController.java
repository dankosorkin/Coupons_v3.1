package core.controllers;

import javax.websocket.server.PathParam;

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
@CrossOrigin("*")
@RequestMapping("/admin")
public class AdminController extends ClientController {

	@RequestMapping(value = "/add/company", method = RequestMethod.POST)
	public ResponseEntity<?> addCompany(@RequestHeader String token, @RequestBody Company company) {
		try {
			return ResponseEntity.ok(getService(token).addCompany(company));
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@RequestMapping(value = "/update/company", method = RequestMethod.PUT)
	public ResponseEntity<?> updateCompany(@RequestHeader String token, @RequestBody Company company) {
		try {
			return ResponseEntity.ok(getService(token).updateCompany(company));
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@RequestMapping(value = "/delete/company/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteCompany(@RequestHeader String token, @PathVariable Integer id) {
		try {
			return ResponseEntity.ok(getService(token).deleteCompany(id));
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@RequestMapping(value = "/get/company/id/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getOneCompany(@RequestHeader String token, @PathVariable Integer id) {
		try {
			return ResponseEntity.ok(getService(token).getOneCompany(id));
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@RequestMapping(value = "/get/company/name/{name}", method = RequestMethod.GET)
	public ResponseEntity<?> getOneCompany(@RequestHeader String token, @PathVariable String name) {
		try {
			return ResponseEntity.ok(getService(token).getCompanyByName(name));
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@RequestMapping(value = "/get/companies", method = RequestMethod.GET)
	public ResponseEntity<?> getAllCompanies(@RequestHeader String token) {
		try {
			return ResponseEntity.ok(getService(token).getAllCompanies());
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@RequestMapping(value = "/add/customer", method = RequestMethod.POST)
	public ResponseEntity<?> addCustomer(@RequestHeader String token, @RequestBody Customer customer) {
		try {
			return ResponseEntity.ok(getService(token).addCustomer(customer));
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@RequestMapping(value = "/update/customer", method = RequestMethod.PUT)
	public ResponseEntity<?> updateCustomer(@RequestHeader String token, @RequestBody Customer customer) {
		try {
			return ResponseEntity.ok(getService(token).updateCustomer(customer));
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@RequestMapping(value = "/delete/customer/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteCustomer(@RequestHeader String token, @PathVariable Integer id) {
		try {
			return ResponseEntity.ok(getService(token).deleteCustomer(id));
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@RequestMapping(value = "/get/customer/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getOneCustomer(@RequestHeader String token, @PathVariable Integer id) {
		try {
			return ResponseEntity.ok(getService(token).getOneCustomer(id));
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@RequestMapping(value = "/get/customer", method = RequestMethod.GET)
	public ResponseEntity<?> getOneCustomer(@RequestHeader String token,
			@PathParam(value = "firstName") String firstName, @PathParam(value = "lastName") String lastName) {
		try {
			return ResponseEntity.ok(getService(token).getCustomerByName(firstName, lastName));
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@RequestMapping(value = "/get/customers", method = RequestMethod.GET)
	public ResponseEntity<?> getAllCustomers(@RequestHeader String token) {
		try {
			return ResponseEntity.ok(getService(token).getAllCustomers());
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	protected AdminService getService(String token) throws CouponSystemException {
		return (AdminService) super.getService(token);
	}

}
