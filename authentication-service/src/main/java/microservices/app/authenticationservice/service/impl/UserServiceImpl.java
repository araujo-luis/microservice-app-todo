package microservices.app.authenticationservice.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import microservices.app.authenticationservice.model.UserLogin;
import microservices.app.authenticationservice.service.UserService;
import microservices.app.authenticationservice.util.EnvironmentalVariables;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	WebClient.Builder webClientBuilder;

	@Autowired
	EnvironmentalVariables env;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		String requestUrl = "http://" + env.getGatewayService() + "/" 
				+ env.getUsersService() + "/users/email/" + email;

		UserLogin user = webClientBuilder.build().get().uri(requestUrl).retrieve()
				.onStatus(HttpStatus::is4xxClientError, response -> {
					return Mono.error(new UsernameNotFoundException("Bad Credentials"));
				}).bodyToMono(UserLogin.class).block();
		return new User(user.getEmail(), user.getPassword(), new ArrayList<>());

	}

}
