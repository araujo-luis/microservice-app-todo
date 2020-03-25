package microservices.app.user.service.services;

import java.util.List;

import microservices.app.user.service.models.Users;
import microservices.app.user.service.models.dto.UserDto;
import microservices.app.user.service.models.dto.UserLoginDto;
import microservices.app.user.service.models.dto.UserTodosDto;

public interface UserService {

	public List<UserDto> getUsers();

	public UserDto getUser(Long userId);

	public UserLoginDto getUserByEmail(String email);

	public UserDto createUser(Users user);

	public UserDto updateUser(Long userId, UserDto user);

	public Long deleteUser(Long userId);
	
	public UserTodosDto getUserTodos(Long userId);
}
