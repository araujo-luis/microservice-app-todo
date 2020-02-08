package microservices.app.authenticationservice.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class EnvironmentalVariables {

	@Autowired
	Environment env;

	@Value("${micro.users-service.name}")
	private String usersService;

	@Value("${micro.gateway-service.name}")
	private String gatewayService;

	public String getUsersService() {
		return usersService;
	}

	public String getGatewayService() {
		return gatewayService;
	}

}
