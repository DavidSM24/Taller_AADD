package exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class RecordNotFoundException extends RuntimeException{
	private String ex;
	private Object f;
	/**
	 * @param ex
	 * @param f
	 */
	public RecordNotFoundException(String ex, Long f) {
		super();
		this.ex = ex;
		this.f = f;
	}
	
	//no hacen falta los seters porque estos se generan con el constructor
	/**
	 * @return the ex
	 */
	public String getEx() {
		return ex;
	}
	/**
	 * @return the f
	 */
	public Object getF() {
		return f;
	}

}
