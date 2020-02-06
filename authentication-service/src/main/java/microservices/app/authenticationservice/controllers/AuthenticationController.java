package microservices.app.authenticationservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import microservices.app.authenticationservice.model.UserLogin;
import microservices.app.authenticationservice.service.AuthenticationService;
import microservices.app.authenticationservice.service.UserService;
import microservices.app.authenticationservice.util.JwtUtil;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtUtil jwt;
	
	@Autowired
	AuthenticationManager authenticationManager;

	@PostMapping
	public ResponseEntity<String> authenticate(@RequestBody UserLogin userLogin) throws Exception{
		
		authenticationService.authenticate(userLogin.getEmail(), userLogin.getPassword());	
	
		UserDetails userDetails = userService.loadUserByUsername(userLogin.getEmail());
		
		String token = jwt.generateToken(userDetails);
		return ResponseEntity.ok(token);
		
	}

	@GetMapping()
	public String hello() {
		return "Hello";
	}

}
