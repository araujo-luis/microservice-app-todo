package microservices.app.todoservice.services;

import java.util.List;

import microservices.app.todoservice.models.dto.TodoDto;

public interface TodoService {
	public List<TodoDto> getAllTodos();
	
	public TodoDto getTodo(Long id);
	
	public List<TodoDto> getUserTodos(Long userId);
	
	public Long createTodo(TodoDto todo);
}
