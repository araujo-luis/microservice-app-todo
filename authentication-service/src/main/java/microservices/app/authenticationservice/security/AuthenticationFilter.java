package microservices.app.authenticationservice.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import microservices.app.authenticationservice.model.UserLogin;
import microservices.app.authenticationservice.service.UserService;
import microservices.app.authenticationservice.util.JwtUtil;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private JwtUtil jwt;
	
	private UserService userService;

	public AuthenticationFilter(UserService userService, AuthenticationManager authenticationManager, JwtUtil jwt) {
		this.userService = userService;
		this.jwt = jwt;
		super.setAuthenticationManager(authenticationManager);

	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException {

		try {
			UserLogin credentials = new ObjectMapper().readValue(req.getInputStream(), UserLogin.class);

			System.out.println(credentials.getEmail() + credentials.getPassword());
			return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(
					credentials.getEmail(), credentials.getPassword(), new ArrayList<>()));

		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
	
		String email = ((User) auth.getPrincipal()).getUsername();
		
		UserDetails userDetails = userService.loadUserByUsername(email);
		String token = jwt.generateToken(userDetails);
		res.addHeader("token", token);
		res.addHeader("userId", userDetails.getUsername());
	}

}
