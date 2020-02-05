package microservices.app.authenticationservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import microservices.app.authenticationservice.models.dto.UserDto;
import microservices.app.authenticationservice.services.UserService;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/authentication")
public class LoginController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/login")
	public String login(@RequestBody UserDto user) {
		System.out.print("USER " + user.getEmail() + " PASSWORD " +  user.getPassword());
		userService.loadUserByUsername(user.getEmail());
		return null;
	}
	
	@GetMapping("/check/status")
	public String status() {
		return "Authentication Service Working";
	}
	
	

}
