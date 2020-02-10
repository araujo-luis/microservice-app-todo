package microservices.app.zuulloadbalancer.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;

public class AuthenticationFilter extends BasicAuthenticationFilter {

	private Environment env;

	public AuthenticationFilter(AuthenticationManager auth, Environment env) {
		super(auth);
		this.env = env;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String authHeader = request.getHeader(env.getProperty("auth.token.header.name"));

		if (authHeader == null || !authHeader.startsWith(env.getProperty("auth.token.header.prefix"))) {
			chain.doFilter(request, response);
			return;
		}
		UsernamePasswordAuthenticationToken authToken = getAuthentication(request);

		SecurityContextHolder.getContext().setAuthentication(authToken);
		chain.doFilter(request, response);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		String authHeader = request.getHeader(env.getProperty("auth.token.header.name"));

		if (authHeader == null) {
			return null;
		}

		String token = authHeader.replace(env.getProperty("auth.token.header.prefix"), "");

		String userId = Jwts.parser().setSigningKey(env.getProperty("token.secret")).parseClaimsJws(token).getBody()
				.getSubject();
		if (userId == null)
			return null;
		
		return new UsernamePasswordAuthenticationToken(userId, null , new ArrayList<>());

	}

}
