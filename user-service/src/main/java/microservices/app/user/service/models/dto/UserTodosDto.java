package microservices.app.user.service.models.dto;

import java.util.List;

public class UserTodosDto extends UserDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<TodoDto> todos;

	public List<TodoDto> getTodos() {
		return todos;
	}

	public void setTodos(List<TodoDto> todos) {
		this.todos = todos;
	}

	public UserTodosDto(Long id, String userId, String firstName, String lastName, String email, List<TodoDto> todos) {
		super(id, userId, firstName, lastName, email);
		this.todos = todos;
	}

	public UserTodosDto() {
		super();
	}

}
