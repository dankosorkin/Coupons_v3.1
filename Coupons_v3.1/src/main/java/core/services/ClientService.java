package core.services;

import org.springframework.beans.factory.annotation.Autowired;

import core.exceptions.CouponSystemException;
import core.repositories.CompanyRepository;
import core.repositories.CouponRepository;
import core.repositories.CustomerRepository;

public abstract class ClientService {

	@Autowired
	protected CompanyRepository companyRepository;
	@Autowired
	protected CouponRepository couponRepository;
	@Autowired
	protected CustomerRepository customerRepository;
	protected String token;

	public abstract boolean login(String email, String password) throws CouponSystemException;

	public void setToken(String token) {
		this.token = token;
	}

}
