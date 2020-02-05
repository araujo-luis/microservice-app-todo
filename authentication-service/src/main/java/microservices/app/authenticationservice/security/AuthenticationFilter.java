package microservices.app.authenticationservice.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.security.core.userdetails.User;

import microservices.app.authenticationservice.models.dto.UserDto;

import com.fasterxml.jackson.databind.ObjectMapper;
import microservices.app.authenticationservice.services.UserService;

import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private Environment env;
	
	private UserService userService;

	public AuthenticationFilter(UserService userService, Environment env, AuthenticationManager authenticationManager){
		this.env = env;
		this.userService = userService;
		super.setAuthenticationManager(authenticationManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req,
			HttpServletResponse res) throws AuthenticationException {
				
		try {
			UserDto credentials = new ObjectMapper().readValue(req.getInputStream(), UserDto.class);
			
			System.out.println(credentials.getEmail() + credentials.getPassword());
			return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(
					credentials.getEmail(), credentials.getPassword(), new ArrayList<>()));

		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {

		String token = JWT.create().withSubject(((User) auth.getPrincipal()).getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + Long.parseLong(
					env.getProperty("token.expiration-time")
				))).sign(HMAC512(SECRET.getBytes()));

		System.out.println("MY TOKEN!!!!!!!!!!!!!!!!!!!!!!!!!!!1");
		System.out.println(token);
	}

}
