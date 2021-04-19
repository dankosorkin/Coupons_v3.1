package core.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import core.entities.Company;
import core.entities.Customer;
import core.exceptions.CouponSystemException;
import core.login.ClientType;
import core.login.LoginManager;
import core.services.AdminService;

@Component
public class AdminTest {

	private static final String email = "admin";
	private static final String password = "admin1234";

	@Autowired
	private LoginManager manager;
	private AdminService service;

	public AdminTest() {
	}

	public void login() throws CouponSystemException {
		service = (AdminService) manager.login(email, password, ClientType.ADMINISTRATOR);

		System.out.println();
		System.out.println("========== Admin test ==========");
	}

	public void addCompanies() throws CouponSystemException {
		System.out.println(">>> Add companies");
		Company c1 = service.addCompany(new Company("Apple", "apple@mail", "pass1234"));
		Company c2 = service.addCompany(new Company("Sony", "sony@mail", "pass1234"));
		Company c3 = service.addCompany(new Company("LG", "lg@mail", "pass1234"));
		Company c4 = service.addCompany(new Company("Samsung", "samsung@mail", "pass1234"));
		Company c5 = service.addCompany(new Company("Philips", "philips@mail", "pass1234"));

		System.out.println(c1);
		System.out.println(c2);
		System.out.println(c3);
		System.out.println(c4);
		System.out.println(c5);

		System.out.println();
	}

	public void updateCompany(int id) throws CouponSystemException {
		System.out.println(">>> Update company");
		Company company = service.getOneCompany(id);

		System.out.println("Before: " + service.getOneCompany(company.getId()));
		company.setName("SonyInc");
		company.setEmail("new_sony@mail");
		company.setPassword("sony1234");
		if (service.updateCompany(company))
			System.out.println("After: " + service.getOneCompany(company.getId()));
		System.out.println();
	}

	public void deleteCompany(int id) throws CouponSystemException {
		System.out.println(">>> Delete company");
		Company deletedCompany;
		deletedCompany = service.deleteCompany(id);
		System.out.println("Deleted company: " + deletedCompany);
		System.out.println();
	}

	public void getCompany(int id) throws CouponSystemException {
		System.out.println(">>> Get one company");
		System.out.println(service.getOneCompany(id));
		System.out.println();
	}

	public void getCompanies() throws CouponSystemException {
		System.out.println(">>> List of companies");
		List<Company> companies;
		companies = service.getAllCompanies();
		if (companies != null) {
			for (Company company : companies) {
				System.out.println(company);
			}
		} else {
			System.out.println("*** List of companies is empty ***");
		}
		System.out.println();
	}

	public void addCustomers() throws CouponSystemException {
		System.out.println(">>> Add customers");
		Customer cs1 = service.addCustomer(new Customer("Avi", "Aaa", "avi@mail", "avi1234"));
		Customer cs2 = service.addCustomer(new Customer("Beny", "Bbb", "beny@mail", "beny1234"));
		Customer cs3 = service.addCustomer(new Customer("Eldad", "Ccc", "eldad@mail", "eldad1234"));
		Customer cs4 = service.addCustomer(new Customer("Dana", "Ddd", "dana@mail", "dana1234"));
		Customer cs5 = service.addCustomer(new Customer("Or", "Eee", "or@mail", "or1234"));

		System.out.println(cs1);
		System.out.println(cs2);
		System.out.println(cs3);
		System.out.println(cs4);
		System.out.println(cs5);

		System.out.println();
	}

	public void updateCustomer(int id) throws CouponSystemException {
		System.out.println(">>> Update customer");
		Customer customer;
		customer = service.getOneCustomer(id);
		System.out.println("Before: " + customer);
		customer.setFirstName("Yosi");
		customer.setLastName("Yyy");
		customer.setEmail("yosi@mail");
		customer.setPassword("yosi1234");
		if (service.updateCustomer(customer))
			System.out.println("After: " + service.getOneCustomer(customer.getId()));
		System.out.println();
	}

	public void deleteCustomer(int id) throws CouponSystemException {
		System.out.println(">>> Delete customer");
		Customer customer;
		customer = service.deleteCustomer(id);
		System.out.println("Deleted customer: " + customer);
		System.out.println();
	}

	public void getCustomer(int id) throws CouponSystemException {
		System.out.println(">>> Get one customer");
		System.out.println(service.getOneCustomer(id));
		System.out.println();
	}

	public void getCustomers() throws CouponSystemException {
		System.out.println(">>> List of customers");
		List<Customer> customers;
		customers = service.getAllCustomers();
		if (customers != null) {
			for (Customer customer : customers) {
				System.out.println(customer);
			}
		} else {
			System.out.println("*** List of customers is empty ***");
		}
		System.out.println();
	}

	public void testAll() {
		try {
			login();
			addCompanies();
			updateCompany(2);
			deleteCompany(3);
			getCompany(1);
			getCompanies();
			addCustomers();
			updateCustomer(2);
			deleteCustomer(3);
			getCustomer(1);
			getCustomers();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
