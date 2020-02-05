package microservices.app.user.service.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import microservices.app.user.service.exceptions.ApiRequestException;
import microservices.app.user.service.models.dto.UserDto;
import microservices.app.user.service.models.Users;
import microservices.app.user.service.services.UserService;
import microservices.app.user.service.repositories.UserRepository;
import microservices.app.user.service.utils.Utils;

@Service
public class UserServiceImpl implements UserService {

	private List<UserDto> users = new ArrayList<UserDto>();

	@Autowired
	private Utils utils;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public List<UserDto> getUsers() {
		return users;
	}

	@Override
	public UserDto getUser(String userId) {
		Users user = userRepository.findByUserId(userId);
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		UserDto userDto = modelMapper.map(user, UserDto.class);
		return userDto;

	}

	@Override
	public UserDto createUser(UserDto userDto) {
		String userId = utils.generateUserId();
		userDto.setUserId(userId);
		userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		Users user = modelMapper.map(userDto, Users.class);
		userRepository.save(user);
		userDto.setPassword(null);
		return userDto;
	}

	@Override
	public UserDto updateUser(String userId, UserDto user) {
		UserDto userFilter = users.stream().filter(x -> x.getUserId().equals(userId)).findFirst()
				.orElseThrow(() -> new ApiRequestException("User Not Found, try to enter a valid Id"));
		userFilter.setEmail(user.getEmail());
		userFilter.setFirstName(user.getFirstName());
		userFilter.setLastName(user.getLastName());
		userFilter.setPassword(user.getPassword());
		users.set(users.indexOf(userFilter), userFilter);
		return users.stream().filter(x -> x.getUserId().equals(userId)).findFirst().get();
	}

	@Override
	public String deleteUser(String userId) {
		UserDto userFilter = users.stream().filter(x -> x.getUserId().equals(userId)).findFirst()
				.orElseThrow(() -> new ApiRequestException("User Not Found, try yo enter a valid Id"));
		users.remove(users.indexOf(userFilter));
		return userId;
	}

}
