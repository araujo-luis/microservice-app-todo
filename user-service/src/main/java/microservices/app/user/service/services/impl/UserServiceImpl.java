package microservices.app.user.service.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;

import microservices.app.user.service.exceptions.ApiRequestException;
import microservices.app.user.service.models.dto.TodoDto;
import microservices.app.user.service.models.dto.UserDto;
import microservices.app.user.service.models.dto.UserLoginDto;
import microservices.app.user.service.models.dto.UserTodosDto;
import microservices.app.user.service.models.Users;
import microservices.app.user.service.services.UserService;
import microservices.app.user.service.repositories.UserRepository;
import microservices.app.user.service.utils.EnvironmentalVariables;
import microservices.app.user.service.utils.Utils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.reflect.Type;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private Utils utils;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private WebClient.Builder webClientBuilder;

	@Autowired
	private EnvironmentalVariables env;

	@Override
	public List<UserDto> getUsers() {
		Type listType = new TypeToken<List<UserDto>>() {
		}.getType();

		List<Users> users = userRepository.findAll();
		users.forEach(f -> f.setPassword(null));

		return modelMapper.map(users, listType);
	}

	@Override
	public UserDto getUser(Long userId) {
		Optional<Users> user = userRepository.findById(userId);
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		if (user.isPresent()) {
			user.get().setPassword(null);
		}
		return modelMapper.map(user, UserDto.class);

	}

	@Override
	public UserDto createUser(Users user) {
		String userId = utils.generateUserId();
		user.setUserId(userId);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserDto userDto = modelMapper.map(user, UserDto.class);
		userRepository.save(user);
		return userDto;
	}

	@Override
	public UserDto updateUser(Long userId, UserDto userDto) {
		Optional<Users> user = userRepository.findById(userId);

		if (user.isPresent()) {
			user.get().setFirstName(userDto.getFirstName());
			user.get().setLastName(userDto.getLastName());
			return modelMapper.map(userRepository.save(user.get()), UserDto.class);
		}
		throw new ApiRequestException("Uset not found, try to enter a valid Id");
	}

	@Override
	public Long deleteUser(Long userId) {
		Optional<Users> user = userRepository.findById(userId);

		if (user.isPresent()) {
			userRepository.delete(user.get());
			return userId;
		}
		throw new ApiRequestException("User not found, try to enter a valid Id");
	}

	@Override
	public UserLoginDto getUserByEmail(String email) {
		Users user = userRepository.findByEmail(email);
		if (user == null)
			throw new ApiRequestException("Uset not found, try to enter a valid Email");
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		return modelMapper.map(user, UserLoginDto.class);
	}

	@Override
	public UserTodosDto getUserTodos(Long userId) {
		Optional<Users> user = userRepository.findById(userId);

		if (user.isPresent()) {

			UserTodosDto userTodosDto = new ModelMapper().map(user, UserTodosDto.class);
			userTodosDto.setUserId(user.get().getUserId());
			userTodosDto.setId(user.get().getId());
			userTodosDto.setFirstName(user.get().getFirstName());
			userTodosDto.setLastName(user.get().getLastName());
			
			
			String requestUrl = "http://" + env.getGatewayService() + "/" + env.getTodoService() + "/todos/user/"
					+ user.get().getId();

			Flux<TodoDto> todos = webClientBuilder.build().get().uri(requestUrl).retrieve()
					.onStatus(HttpStatus::is4xxClientError, response -> 
						Mono.error(new ApiRequestException("Todo Service Call error: " + response.statusCode()))
					).bodyToFlux(TodoDto.class);
			
			userTodosDto.setTodos(todos.collectList().block());
			return userTodosDto; 
		}
		throw new ApiRequestException("User not found, try to enter a valid Id");
	}

}
