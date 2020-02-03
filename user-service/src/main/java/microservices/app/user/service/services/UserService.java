package microservices.app.user.service.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import microservices.app.user.service.models.Users;
import microservices.app.user.service.models.dto.UserDto;

public interface UserService {

	public List<Users> getUsers();

	public UserDto getUser(String userId);

	public UserDto createUser(UserDto user);

	public UserDto updateUser(String userId, UserDto user);

	public String deleteUser(String userId);
}
