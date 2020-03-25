package microservices.app.user.service.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class EnvironmentalVariables {

	@Autowired
	Environment env;

	@Value("${micro.todo-service.name}")
	private String todoService;

	@Value("${micro.gateway-service.name}")
	private String gatewayService;

	public String getTodoService() {
		return todoService;
	}

	public String getGatewayService() {
		return gatewayService;
	}

}
