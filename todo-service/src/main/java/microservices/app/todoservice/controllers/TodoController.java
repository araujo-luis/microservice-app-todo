package microservices.app.todoservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import microservices.app.todoservice.models.dto.TodoDto;
import microservices.app.todoservice.services.TodoService;

@RestController
@RequestMapping("/todos")
public class TodoController {

	@Autowired
	private TodoService todoService;

	@GetMapping
	public List<TodoDto> getAllTodos() {
		return todoService.getAllTodos();
	}

	@GetMapping("/{id}")
	public TodoDto getTodo(@PathVariable Long id) {
		return todoService.getTodo(id);
	}

	@GetMapping("/user/{userId}")
	public List<TodoDto> getUserTodos(@PathVariable Long userId) {
		return todoService.getUserTodos(userId);
	}

	@PostMapping
	public Long createTodo(@RequestBody TodoDto todo) {
		return todoService.createTodo(todo);
	}

}
