package core.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;

import core.sessions.Session;
import core.sessions.SessionContext;

public class LoginFilter implements Filter {

	private SessionContext context;

	public LoginFilter(SessionContext context) {
		this.context = context;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		// intercepting an incoming request
		HttpServletRequest req = (HttpServletRequest) request;

		// get token
		String token = req.getHeader("token");

		if (token != null) {
			// get session
			Session session = context.getSession(token);

			if (session != null) {
				// there is an active session
				System.out.println("SESSION - forward the request");
				chain.doFilter(request, response);
				return;
			}
		}

		// if we are here - there is no session
		HttpServletResponse resp = (HttpServletResponse) response;
		System.out.println("NO SESSION - forward the request");
		resp.sendError(HttpStatus.UNAUTHORIZED.value(), "You are not logged in");
	}

}
