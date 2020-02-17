package microservices.app.authenticationservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

	

	@Autowired
	private Environment env;

	@GetMapping("/check/status")
	public String checkStatus(){
		return "Authentication Service is working on " + env.getProperty("local.server.port") + " Gateway " +env.getProperty("gateway.ip");
	}

}
