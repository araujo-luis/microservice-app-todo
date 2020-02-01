package microservices.mobile.app.mobileappservice.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import microservices.mobile.app.mobileappservice.models.Users;
import microservices.mobile.app.mobileappservice.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping
	public List<Users> getUsers() {
		return userService.getUsers();
	}

	@GetMapping("/{userId}")
	public ResponseEntity<Users> getUser(@PathVariable String userId) {
		return userService.getUser(userId);
	}

	@PostMapping
	public ResponseEntity<Users> createUser(@Valid @RequestBody Users user) {
		return userService.createUser(user);
	}

	@PutMapping("/{userId}")
	public ResponseEntity<Users> updateUser(@PathVariable String userId, @Valid @RequestBody Users user) {
		return userService.updateUser(userId, user);
	}

	@DeleteMapping("/{userId}")
	public String deleteUser(@PathVariable String userId) {
		return userService.deleteUser(userId);
	}
}
