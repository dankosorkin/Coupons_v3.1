package core.test;

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
	private static final String password = "pass1234";

	@Autowired
	private LoginManager manager;
	private AdminService service;

	public AdminTest() {
	}

	public void login() throws CouponSystemException {
		service = (AdminService) manager.login(email, password, ClientType.ADMIN);
	}

	public void addCompanies() throws CouponSystemException {
		System.out.println(">>> Add companies");
		service.addCompany(new Company("Apple", "apple@mail", "pass1234"));
		service.addCompany(new Company("Sony", "sony@mail", "pass1234"));
		service.addCompany(new Company("LG", "lg@mail", "pass1234"));
		service.addCompany(new Company("Samsung", "samsung@mail", "pass1234"));
		service.addCompany(new Company("Philips", "philips@mail", "pass1234"));
	}

	public void addCustomers() throws CouponSystemException {
		service.addCustomer(new Customer("Avi", "Aaa", "avi@mail", "avi1234"));
		service.addCustomer(new Customer("Beny", "Bbb", "beny@mail", "beny1234"));
		service.addCustomer(new Customer("Eldad", "Ccc", "eldad@mail", "eldad1234"));
		service.addCustomer(new Customer("Dana", "Ddd", "dana@mail", "dana1234"));
		service.addCustomer(new Customer("Or", "Eee", "or@mail", "or1234"));

	}

}
