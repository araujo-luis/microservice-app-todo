package microservices.app.todoservice.exceptions;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;

public class ApiException {
	
	private final String message;
	
	private final Throwable throwable;
	
	private final HttpStatus httpStatus;
	
	private final ZonedDateTime timestamp;

	public String getMessage() {
		return message;
	}

	public ApiException(String message, Throwable throwable, HttpStatus httpStatus, ZonedDateTime timestamp) {
		super();
		this.message = message;
		this.throwable = throwable;
		this.httpStatus = httpStatus;
		this.timestamp = timestamp;
	}

	public Throwable getThrowable() {
		return throwable;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public ZonedDateTime getTimestamp() {
		return timestamp;
	}
	
	
}
