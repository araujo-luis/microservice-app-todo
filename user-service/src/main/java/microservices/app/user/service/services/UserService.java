package microservices.app.user.service.services;

import java.util.List;

import microservices.app.user.service.models.dto.UserDto;

public interface UserService {

	public List<UserDto> getUsers();

	public UserDto getUser(String userId);

	public UserDto getUserByEmail(String email);

	public UserDto createUser(UserDto user);

	public UserDto updateUser(String userId, UserDto user);

	public String deleteUser(String userId);
}
