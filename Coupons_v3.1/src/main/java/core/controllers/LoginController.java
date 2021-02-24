package core.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import core.sessions.Session;
import core.sessions.SessionContext;

@RestController
@CrossOrigin
public class LoginController {

	@Autowired
	protected SessionContext ctx;

	@PostMapping("/login/{userName}")
	public String login(@PathVariable String userName) {
		Session session = ctx.createSession();
		session.setAttribute("userName", userName);
		return session.token;
	}

}
