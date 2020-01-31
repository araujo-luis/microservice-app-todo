package microservices.mobile.app.mobileappservice.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import microservices.mobile.app.mobileappservice.models.Users;

public interface UserService {

	public List<Users> getUsers();
	
	public ResponseEntity<Users> getUser(String userId);
	
	public ResponseEntity<Users> createUser(Users user);
	
	public ResponseEntity<Users> updateUser(String userId, Users user);
	
	public String deleteUser(String userId);
}
