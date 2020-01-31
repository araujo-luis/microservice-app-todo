package microservices.mobile.app.mobileappservice.services;

import java.util.List;

import microservices.mobile.app.mobileappservice.models.Users;

public interface UserService {

	public List<Users> getUsers();
	
	public Users getUser(String userId);
	
	public Users createUser(Users user);
	
	public Users updateUser(String userId, Users user);
	
	public String deleteUser(String userId);
}
