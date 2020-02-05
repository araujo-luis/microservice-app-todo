package microservices.app.authorizationservice.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import microservices.app.authorizationservice.models.User;
import microservices.app.authorizationservice.models.dto.UserDto;
import microservices.app.authorizationservice.services.UserService;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	WebClient.Builder webClientBuilder;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@Override
	public UserDto getUser(final String userEmail) {

		final UserDto response = webClientBuilder.build().get().uri("http://users-service/users/" + userEmail)
				.retrieve().bodyToMono(UserDto.class).block();
		return response;

	}

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

		return null;
	}

}
