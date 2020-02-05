package microservices.app.authenticationservice.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import microservices.app.authenticationservice.models.dto.UserDto;
import reactor.core.publisher.Mono;

public interface UserService extends UserDetailsService{
	public Mono<UserDto> getUser(String userId);

}

