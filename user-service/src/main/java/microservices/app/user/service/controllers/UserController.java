package microservices.app.user.service.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import microservices.app.user.service.models.Users;
import microservices.app.user.service.models.dto.UserDto;
import microservices.app.user.service.models.dto.UserLoginDto;
import microservices.app.user.service.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	private Environment env;

	@GetMapping("/check/status")
	public String checkStatus(){
		return "User Services is working on " + env.getProperty("local.server.port");
	}
	
	@GetMapping
	public ResponseEntity<List<UserDto>> getUsers() {
		return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUser(@PathVariable String userId) {
		return new ResponseEntity<UserDto>(userService.getUser(userId), HttpStatus.OK);
	}

	@GetMapping("/email/{email}")
	public ResponseEntity<UserLoginDto>  getUserByEmail(@PathVariable String email) {
		return new ResponseEntity<UserLoginDto>(userService.getUserByEmail(email), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<UserDto> createUser(@RequestBody Users user) {
		return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
	}

	@PutMapping("/{userId}")
	public ResponseEntity<UserDto>  updateUser(@PathVariable Long userId, @RequestBody UserDto user) {
		return new ResponseEntity<UserDto>(userService.updateUser(userId, user), HttpStatus.OK);
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<Long>  deleteUser(@PathVariable Long userId) {
		return new ResponseEntity<Long>(userService.deleteUser(userId), HttpStatus.OK);
	}
}
