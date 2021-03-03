package core.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import core.exceptions.CouponSystemException;
import core.login.ClientType;
import core.login.LoginManager;
import core.services.ClientService;
import core.sessions.Session;
import core.sessions.SessionContext;

@RestController
@CrossOrigin
public class LoginController {

	@Autowired
	private LoginManager manager;
	@Autowired
	private SessionContext ctx;
	private ClientService service;
	private Session session;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam String email, @RequestParam String password, @RequestParam ClientType client) {
		try {
			service = manager.login(email, password, client);
			if (service != null) {
				session = ctx.createSession();
				session.setAttribute("service", service);
				service.setToken(session.token);
				return session.token;
			} else {
				throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Login failed");
			}
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, e.getMessage());
		}
	}
}
