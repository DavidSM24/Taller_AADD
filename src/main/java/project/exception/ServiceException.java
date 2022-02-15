package project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class ServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ServiceException(String message, Throwable cause) {
		super(message,cause);
	}
	
	public ServiceException(String message) {
		super(message);
	}
	
	public ServiceException(Throwable cause) {
		super(cause);
	}

}
