package core.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import core.exceptions.CouponSystemException;
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
			session = ctx.createSession();
			session.setAttribute("service", service);
			service.setToken(session.token);
			return session.token;
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
		return null;
	}
}
