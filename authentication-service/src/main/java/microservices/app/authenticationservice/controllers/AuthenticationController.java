package microservices.app.authenticationservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import microservices.app.authenticationservice.model.Token;
import microservices.app.authenticationservice.model.UserLogin;
import microservices.app.authenticationservice.service.UserService;
import microservices.app.authenticationservice.util.JwtUtil;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

	@Autowired
	private UserService userService;

	@Autowired
	private JwtUtil jwt;

	@Autowired
	AuthenticationManager authenticationManager;

	@PostMapping
	public ResponseEntity<Token> authenticate(@RequestBody UserLogin userLogin) throws Exception {
	
		try {
			authenticationManager
			.authenticate(new UsernamePasswordAuthenticationToken(userLogin.getEmail(), userLogin.getPassword()));	
		}catch(BadCredentialsException e) {
			throw new Exception("Bad Credentials");
		}
		
		UserDetails userDetails = userService.loadUserByUsername(userLogin.getEmail());

		String token = jwt.generateToken(userDetails);
		return ResponseEntity.ok(new Token(token));

	}

	@GetMapping()
	public String hello() {
		return "Hello";
	}

}
