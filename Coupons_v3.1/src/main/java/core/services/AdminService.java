package core.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.entities.Company;
import core.entities.Customer;
import core.exceptions.CouponSystemException;

/*
 * The class described business logic for system administrator methods
 */
@Service
@Transactional
public class AdminService extends ClientService {

	private String email = "admin";
	private String password = "admin1234";

	/**
	 * Login method for administrator using hard coded credentials for learning
	 * purpose only; real system will check credentials stored in database.
	 */
	@Override
	public boolean login(String email, String password) {
		return this.email == email && this.password == password;
	}

	/**
	 * The method adds new company to the database and return the instance after
	 * assigning its id.
	 * 
	 * @param {@link Company} company
	 * @return {@link Company} company
	 * @throws CouponSystemException
	 */
	public Company addCompany(Company company) throws CouponSystemException {
		if (companyRepository.findByNameAndEmail(company.getName(), company.getEmail()) == null) {
			companyRepository.save(company);
			return company;
		} else {
			throw new CouponSystemException("add company failed: already exists");
		}
	}

	/**
	 * The method updates company email and password in the database; returns true
	 * or throws exception.
	 * 
	 * @param {@link Company} company
	 * @return boolean
	 * @throws CouponSystemException
	 */
	public boolean updateCompany(Company company) throws CouponSystemException {
		Optional<Company> opt = companyRepository.findById(company.getId());
		if (opt.isPresent()) {
			Company companyDB = opt.get();
			companyDB.setEmail(company.getEmail());
			companyDB.setPassword(company.getPassword());
			companyRepository.save(companyDB);
			return true;
		}

		throw new CouponSystemException("update company failed: not found");
	}

	/**
	 * The method delete specific company from database cascading all company
	 * coupons.
	 * 
	 * @param Integer id
	 * @return {@link Company} company
	 * @throws CouponSystemException
	 */
	public Company deleteCompany(Integer id) throws CouponSystemException {
		Optional<Company> opt = companyRepository.findById(id);
		if (opt.isPresent()) {
			companyRepository.delete(opt.get());
			return opt.get();
		}
		throw new CouponSystemException("delete company failed: not found");

	}

	/**
	 * The method returns specific company from database using its id.
	 * 
	 * @param Integer id
	 * @return {@link Company} company
	 * @throws CouponSystemException
	 */
	public Company getOneCompany(Integer id) throws CouponSystemException {
		Optional<Company> opt = companyRepository.findById(id);
		if (opt.isPresent())
			return opt.get();
		throw new CouponSystemException("get company failed: not found");
	}

	/**
	 * The method returns collection of all companies from database.
	 * 
	 * @return List<Company> companies
	 * @throws CouponSystemException
	 */
	public List<Company> getAllComapnies() throws CouponSystemException {
		List<Company> companies = companyRepository.findAll();
		if (companies != null)
			return companies;
		throw new CouponSystemException("get all companies failed: empty list");
	}

	/**
	 * The method adds new customer to the database and return the instance after
	 * assigning its id.
	 * 
	 * @param {@link Customer} customer
	 * @return {@link Customer} customer
	 * @throws CouponSystemException
	 */
	public Customer addCustomer(Customer customer) throws CouponSystemException {
		if (customerRepository.findByEmail(customer.getEmail()) == null)
			return customerRepository.save(customer);
		throw new CouponSystemException("add customer failed: already exists");
	}

	/**
	 * The method updates customer details in the database; returns true or throws
	 * exception.
	 * 
	 * @param {@link Customer} customer
	 * @return boolean
	 * @throws CouponSystemException
	 */
	public boolean updateCustomer(Customer customer) throws CouponSystemException {
		Optional<Customer> opt = customerRepository.findById(customer.getId());
		if (opt.isPresent()) {
			Customer customerDB = opt.get();
			customerDB.setFirstName(customer.getFirstName());
			customerDB.setLastName(customer.getLastName());
			customerDB.setEmail(customer.getEmail());
			customerDB.setPassword(customer.getPassword());
			customerRepository.save(customerDB);
			return true;
		}
		throw new CouponSystemException("update customer failed: not found");
	}

	/**
	 * The method delete specific customer from database cascading all his
	 * purchases.
	 * 
	 * @param Integer id
	 * @return {@link Customer} customer
	 * @throws CouponSystemException
	 */
	public Customer deleteCustomer(Integer id) throws CouponSystemException {
		Optional<Customer> opt = customerRepository.findById(id);
		if (opt.isPresent()) {
			customerRepository.delete(opt.get());
			return opt.get();
		}
		throw new CouponSystemException("delete customer failed: not found");
	}

	/**
	 * The method returns specific customer from database using its id.
	 * 
	 * @param Integer id
	 * @return {@link Customer} customer
	 * @throws CouponSystemException
	 */
	public Customer getOneCustomer(Integer id) throws CouponSystemException {
		Optional<Customer> opt = customerRepository.findById(id);
		if (opt.isPresent())
			return opt.get();
		else
			throw new CouponSystemException("get customer failed: not found");
	}

	/**
	 * The method returns collection of all customers from database.
	 * 
	 * @return List<Customer> customers
	 * @throws CouponSystemException
	 */
	public List<Customer> getAllCustomers() throws CouponSystemException {
		List<Customer> companies = customerRepository.findAll();
		if (companies != null)
			return companies;
		throw new CouponSystemException("get all customers: empty list");
	}

}
