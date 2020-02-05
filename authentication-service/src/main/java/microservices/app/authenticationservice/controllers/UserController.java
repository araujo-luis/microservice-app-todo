package microservices.app.authenticationservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import microservices.app.authenticationservice.models.dto.UserDto;
import microservices.app.authenticationservice.services.UserService;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/authentication")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/user/{userEmail}")
	public Mono<UserDto> getUser(@PathVariable String userEmail) {
		return userService.getUser(userEmail);
	}
	
	@GetMapping("/check/status")
	public String status() {
		return "Authentication Service Working";
	}
	
	

}
