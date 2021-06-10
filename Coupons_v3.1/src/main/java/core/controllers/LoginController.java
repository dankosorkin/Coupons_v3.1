package core.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import core.entities.User;
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
	public ResponseEntity<?> login(@RequestBody User user) {
		try {
			service = manager.login(user.getEmail(), user.getPassword(),
					ClientType.valueOf(user.getRole().toUpperCase()));
			if (service != null) {
				session = ctx.createSession();
				session.setAttribute("service", this.service);
				user.setToken(session.token);
			}
			HttpHeaders headers = new HttpHeaders();
			headers.add("token", user.getToken());
			ResponseEntity<?> resp = new ResponseEntity<>(user, headers, HttpStatus.OK);

			return resp;
		} catch (CouponSystemException e) {
			System.err.println(e);
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
		}
	}

}
