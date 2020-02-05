package microservices.app.authorizationservice.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import microservices.app.authorizationservice.models.dto.UserDto;
import reactor.core.publisher.Mono;

public interface UserService extends UserDetailsService{
	public UserDto getUser(String userId);

}

