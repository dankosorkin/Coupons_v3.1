package core.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import core.exceptions.CouponSystemException;
import core.services.AdminService;
import core.services.CompanyService;
import core.services.CustomerService;
import core.sessions.SessionContext;

public abstract class ClientController {

	@Autowired
	protected AdminService adminService;
	@Autowired
	protected CompanyService companyService;
	@Autowired
	protected CustomerService customerService;
	@Autowired
	protected SessionContext ctx;

	private Map<Integer, Object> personMap = new HashMap<Integer, Object>();

	public abstract boolean login(String email, String password) throws CouponSystemException;

}
