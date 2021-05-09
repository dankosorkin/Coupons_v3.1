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

import core.sessions.SessionContext;

public class LoginFilter implements Filter {

	private SessionContext context;

	public LoginFilter(SessionContext context) {
		this.context = context;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		String token = req.getHeader("token");

		if (token != null && context.getSession(token) != null || req.getMethod().equals("OPTIONS")) {
			chain.doFilter(request, response);
			return;
		}

		HttpServletResponse resp = (HttpServletResponse) response;
		resp.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
		resp.sendError(HttpStatus.UNAUTHORIZED.value(), "You are not logged in");
	}

}
