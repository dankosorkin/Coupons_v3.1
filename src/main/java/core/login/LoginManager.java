package core.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import core.exceptions.CouponSystemException;
import core.services.AdminService;
import core.services.ClientService;
import core.services.CompanyService;
import core.services.CustomerService;

@Component
public class LoginManager {

	@Autowired
	private ApplicationContext ctx;

	public ClientService login(String email, String password, ClientType client) throws CouponSystemException {

		switch (client) {
		case ADMIN:
			AdminService adminService = ctx.getBean(AdminService.class);
			if (adminService.login(email, password))
				return adminService;
			break;
		case COMPANY:
			CompanyService companyService = ctx.getBean(CompanyService.class);
			if (companyService.login(email, password))
				return companyService;
			break;
		case CUSTOMER:
			CustomerService customerService = ctx.getBean(CustomerService.class);
			if (customerService.login(email, password))
				return customerService;
			break;
		}
		throw new CouponSystemException("Check your credentials");
	}
}
