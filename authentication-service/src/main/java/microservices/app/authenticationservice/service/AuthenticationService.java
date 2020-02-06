package microservices.app.authenticationservice.service;

import org.springframework.security.core.Authentication;

public interface AuthenticationService {
	
	public Authentication authenticate(String email, String password);

}
