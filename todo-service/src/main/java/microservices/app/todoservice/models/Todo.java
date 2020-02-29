package microservices.app.todoservice.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Todo {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private Long userId;
	
	@Column
	private String description;
	
	@Column
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

	public Todo(Long id, Long userId, String description, boolean completed) {
		super();
		this.id = id;
		this.userId = userId;
		this.description = description;
		this.completed = completed;
	}

	public Todo() {
		super();
	}

}
