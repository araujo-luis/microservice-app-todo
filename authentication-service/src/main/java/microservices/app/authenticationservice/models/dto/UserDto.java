package microservices.app.authenticationservice.models.dto;

import java.io.Serializable;

public class UserDto implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5042193847965920639L;
	
	private String email;
	
	private String password;
    
	public UserDto() {
		super();
	}

	public UserDto(String email, String password) {
		super();
		this.email = email;
		this.password = password;
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
