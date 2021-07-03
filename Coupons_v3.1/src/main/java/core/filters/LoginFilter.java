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

		if (token != null && context.getSession(token) != null) {
			chain.doFilter(request, response);
			System.out.println("session");
			return;
		}

		if (req.getMethod().equals("OPTIONS")) {
			chain.doFilter(request, response);
			System.out.println("preflight request");
			return;
		}

		System.err.println("filter failed: " + req.getMethod());
		HttpServletResponse resp = (HttpServletResponse) response;
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Headers",
				"DNT,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type,Range");
		resp.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS, DELETE");
		resp.setHeader("Access-Control-Expose-Headers", "Content-Length,Content-Range");
		resp.sendError(HttpStatus.UNAUTHORIZED.value(), "You are not logged in");
	}

}
