package microservices.app.user.service.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;

@Entity
@Table(name="users")
public class Users {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable=false, unique=true)
	private String userId;

	@NotNull(message="First Name cannot be null")
	@Size(min=2, message="First Name needs at least 2 characters")
	@Column(nullable=false, length=50)
	private String firstName;
	
	@NotNull(message="Last Name cannot be null")
	@Size(min=2, message="Last Name needs at least 2 characters")
	@Column(nullable=false, length=50)
	private String lastName;
	
	@Email
	@NotNull(message="Email cannot be null")
	@Column(nullable=false, unique=true)
	private String email;
	
	@Size(min=4, max=16)
	@NotNull(message="Password cannot be null")
	@Column(nullable=false, unique=true)
	private String password;
	

	public Users() {
		super();
	}

	public Users(Long id, String userId, String firstName, String lastName, String email, String password) {
		super();
		this.id = id;
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
