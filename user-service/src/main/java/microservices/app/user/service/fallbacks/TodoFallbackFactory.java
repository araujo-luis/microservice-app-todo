package microservices.app.user.service.fallbacks;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import feign.FeignException;
import feign.hystrix.FallbackFactory;
import microservices.app.user.service.feign.TodoServiceFeign;
import microservices.app.user.service.models.dto.TodoDto;

@Component
public class TodoFallbackFactory implements FallbackFactory<TodoServiceFeign> {

	@Override
	public TodoServiceFeign create(Throwable cause) {
		return new TodoServiceFallback(cause);
	}

}

class TodoServiceFallback implements TodoServiceFeign {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	private final Throwable cause;

	public TodoServiceFallback(Throwable cause) {
		this.cause = cause;
	}

	@Override
	public List<TodoDto> getTodos(Long userId) {
		if (cause instanceof FeignException && ((FeignException) cause).status() == 404)
			logger.error(
					String.format("404 Not found error took place. Error message: %s", cause.getLocalizedMessage()));
		else
			logger.error(
					String.format("Another kind of error took place. Error message: %s", cause.getLocalizedMessage()));
		return new ArrayList<>();
	}

}
