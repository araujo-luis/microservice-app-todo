package microservices.app.user.service.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import microservices.app.user.service.models.Users;

public interface UserService {

	public List<Users> getUsers();

	public ResponseEntity<Users> getUser(String userId);

	public ResponseEntity<Users> createUser(Users user);

	public ResponseEntity<Users> updateUser(String userId, Users user);

	public String deleteUser(String userId);
}
