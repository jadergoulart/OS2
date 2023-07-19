package exceptions;

import java.util.ArrayList;
import java.util.List;

public class validationError extends StandardError{

	private static final long serialVersionUID = 1L;
	private List<FieldMessade>errors = new ArrayList<>();
	public validationError() {
		super();
		
	}
	public validationError(Long timestamp, Integer status, String error) {
		super(timestamp, status, error);
		
	}
	public validationError(List<FieldMessade> errors) {
		super();
		this.errors = errors;
	}
	public List<FieldMessade> getErrors() {
		return errors;
	}
	public void addErrors(String fieldName, String message) {
		this.errors.add(new FieldMessade(fieldName, message));
	}
}