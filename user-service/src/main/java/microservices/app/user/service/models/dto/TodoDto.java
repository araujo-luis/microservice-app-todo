package microservices.app.user.service.models.dto;

public class TodoDto {

	private Long id;

	private String description;

	private boolean completed;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public TodoDto(Long id, String description, boolean completed) {
		super();
		this.id = id;
		this.description = description;
		this.completed = completed;
	}

	public TodoDto() {
		super();
	}

}
