package microservices.app.authenticationservice.service.impl;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import microservices.app.authenticationservice.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO CALL MICROSERVICE
		return new User("foo", "foo", new ArrayList<>());
	}

}
