package microservices.app.authenticationservice.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import microservices.app.authenticationservice.models.dto.UserDto;
import microservices.app.authenticationservice.services.UserService;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	WebClient.Builder webClientBuilder;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@Override
	public Mono<UserDto> getUser(final String userEmail) {

		return webClientBuilder.build().get().uri("http://users-service/users/" + userEmail)
				.retrieve().bodyToMono(UserDto.class);
		

	}

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		UserDto user = webClientBuilder.build().get().uri("http://users-service/users/email/" + username)
		.retrieve().bodyToMono(UserDto.class).block();
		return new User(user.getEmail(), user.getPassword(), true, true, true, true, new ArrayList<>());
		
		//return new org.springframework.security.core.userdetails.User(user);
	}

}
