package microservices.app.todoservice.models.dto;

public class TodoDto {

	private Long id;

	private Long userId;

	private String description;

	private boolean completed;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public TodoDto(Long id, Long userId, String description, boolean completed) {
		super();
		this.id = id;
		this.userId = userId;
		this.description = description;
		this.completed = completed;
	}

	public TodoDto() {
		super();
	}

}
