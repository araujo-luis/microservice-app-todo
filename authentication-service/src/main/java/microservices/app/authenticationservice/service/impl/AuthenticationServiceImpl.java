package microservices.app.authenticationservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import microservices.app.authenticationservice.service.AuthenticationService;

public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	AuthenticationManager authenticationManager;

	@Override
	public Authentication authenticate(String email, String password){
		try {
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));	
		}catch (BadCredentialsException e) {
			System.out.print(e.getMessage());
			return null;
		}
		
		
	}

}
