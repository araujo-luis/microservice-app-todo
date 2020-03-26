package microservices.app.user.service.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import microservices.app.user.service.fallbacks.TodoFallbackFactory;
import microservices.app.user.service.models.dto.TodoDto;

@FeignClient(name="todo-service", fallbackFactory=TodoFallbackFactory.class)
public interface TodoServiceFeign {

	@GetMapping("/todos/user/{userId}")
	public List<TodoDto> getTodos(@PathVariable Long userId);
}
