package microservices.app.user.service.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import microservices.app.user.service.exceptions.ApiRequestException;
import microservices.app.user.service.models.Users;
import microservices.app.user.service.services.UserService;
import microservices.app.user.service.utils.Utils;

@Service
public class UserServiceImpl implements UserService {

	private List<Users> users = new ArrayList<Users>();

	@Autowired
	private Utils utils;

	@Override
	public List<Users> getUsers() {
		return users;
	}

	@Override
	public ResponseEntity<Users> getUser(String userId) {
		Users user = users.stream().filter(x -> x.getId().equals(userId)).findFirst()
				.orElseThrow(() -> new ApiRequestException("User Not Found, try to enter a valid Id"));

		return new ResponseEntity<>(user, HttpStatus.OK);

	}

	@Override
	public ResponseEntity<Users> createUser(Users user) {

		Users myUser = new Users();
		myUser.setFirstName(user.getFirstName());
		myUser.setLastName(user.getLastName());
		myUser.setEmail(user.getEmail());
		myUser.setPassword(user.getPassword());
		String userId = utils.generateUserId();
		myUser.setId(userId);

		users.add(myUser);
		return new ResponseEntity<>(myUser, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Users> updateUser(String userId, Users user) {
		Users userFilter = users.stream().filter(x -> x.getId().equals(userId)).findFirst()
				.orElseThrow(() -> new ApiRequestException("User Not Found, try to enter a valid Id"));
		userFilter.setEmail(user.getEmail());
		userFilter.setFirstName(user.getFirstName());
		userFilter.setLastName(user.getLastName());
		userFilter.setPassword(user.getPassword());
		users.set(users.indexOf(userFilter), userFilter);
		return new ResponseEntity<>(users.stream().filter(x -> x.getId().equals(userId)).findFirst().get(),
				HttpStatus.OK);
	}

	@Override
	public String deleteUser(String userId) {
		Users userFilter = users.stream().filter(x -> x.getId().equals(userId)).findFirst()
				.orElseThrow(() -> new ApiRequestException("User Not Found, try yo enter a valid Id"));
		users.remove(users.indexOf(userFilter));
		return userId;
	}

}
