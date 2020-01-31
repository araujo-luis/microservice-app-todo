package microservices.mobile.app.mobileappservice.exceptions;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(value = {ApiRequestException.class})
	public ResponseEntity<Object> handleApiExceoption(ApiRequestException e, WebRequest webRequest){
		
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		ApiException apiException = new ApiException(e.getMessage(), e.getCause(), httpStatus, ZonedDateTime.now(ZoneId.of("Z")));
				
		return new ResponseEntity<>(apiException, httpStatus);
	}
}
