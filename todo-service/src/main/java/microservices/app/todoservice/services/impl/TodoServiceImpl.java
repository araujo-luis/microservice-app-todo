package microservices.app.todoservice.services.impl;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import microservices.app.todoservice.exceptions.ApiRequestException;
import microservices.app.todoservice.models.Todo;
import microservices.app.todoservice.models.dto.TodoDto;
import microservices.app.todoservice.repositories.TodoRepository;
import microservices.app.todoservice.services.TodoService;

@Service
public class TodoServiceImpl implements TodoService {

	@Autowired
	private TodoRepository todoRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<TodoDto> getAllTodos() {
		Type listType = new TypeToken<List<TodoDto>>() {
		}.getType();

		List<Todo> todos = todoRepository.findAll();

		return modelMapper.map(todos, listType);
	}

	@Override
	public TodoDto getTodo(Long id) {
		Optional<Todo> todo = todoRepository.findById(id);

		if (todo.isPresent())
			return modelMapper.map(todo.get(), TodoDto.class);

		throw new ApiRequestException("Todo not found. Enter a valid id");
	}

	@Override
	public List<TodoDto> getUserTodos(Long userId) {

		Type listType = new TypeToken<List<TodoDto>>() {
		}.getType();

		List<Todo> todos = todoRepository.findByUserId(userId);

		return modelMapper.map(todos, listType);
	}

	@Override
	public Long createTodo(TodoDto todo) {
		Todo todoEntity = new Todo();
		todoEntity.setCompleted(todo.isCompleted());
		todoEntity.setDescription(todo.getDescription());
		todoEntity.setUserId(todo.getUserId());
		return todoRepository.save(todoEntity).getId();
	}

}
