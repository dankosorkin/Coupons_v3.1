package core.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import core.exceptions.CouponSystemException;
import core.services.ClientService;
import core.sessions.SessionContext;

public abstract class ClientController {

	@Autowired
	protected SessionContext ctx;
	protected ClientService service;

	protected ClientService getService(String token) throws CouponSystemException {
		service = (ClientService) ctx.getSession(token).getAttribute("service");
		if (service != null)
			return service;
		throw new CouponSystemException("failed to get session");
	}

}
