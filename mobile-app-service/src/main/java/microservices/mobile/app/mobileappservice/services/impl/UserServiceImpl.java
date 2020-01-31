package microservices.mobile.app.mobileappservice.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import microservices.mobile.app.mobileappservice.models.Users;
import microservices.mobile.app.mobileappservice.services.UserService;
import microservices.mobile.app.mobileappservice.utils.Utils;

@Service
public class UserServiceImpl implements UserService{

	private List<Users> users = new ArrayList<Users>();
	
	@Autowired
	private Utils utils;
	
	@Override
	public List<Users> getUsers() {
		return users;
	}

	@Override
	public Users getUser(String userId) {
		return users.stream().filter(x -> x.getId().equals(userId)).findFirst().orElse(null);
	}
	
	@Override
	public Users createUser(Users user) {
		Users myUser = new Users();
		myUser.setFirstName(user.getFirstName());
		myUser.setLastName(user.getLastName());
		myUser.setEmail(user.getEmail());
		myUser.setPassword(user.getPassword());
		String userId = utils.generateUserId();
		myUser.setId(userId);
		
		users.add(myUser);
		return myUser;
	}


	@Override
	public Users updateUser(String userId, Users user) {
		Users userFilter = users.stream().filter(x -> x.getId().equals(userId)).findFirst().get();
		userFilter.setEmail(user.getEmail());
		userFilter.setFirstName(user.getFirstName());
		userFilter.setLastName(user.getLastName());
		userFilter.setPassword(user.getPassword());
		users.set(users.indexOf(userFilter), userFilter);
		return users.stream().filter(x -> x.getId().equals(userId)).findFirst().get();
	}

	@Override
	public String deleteUser(String userId) {
		Users userFilter = users.stream().filter(x -> x.getId().equals(userId)).findFirst().get();
		users.remove(users.indexOf(userFilter));
		return userId;
	}


}
