package exceptions;

import java.util.ArrayList;
import java.util.List;

public class SaveException extends Exception {
	
	private List<String> errors;
	
	public SaveException(List<String> saveErrors) {
		super();
		errors = saveErrors;
	}
	
	public SaveException() {
		super();
		errors = new ArrayList<String>();
	}
	
	public void addError(String error) {
		errors.add(error);
	}
	
	public List<String> getErrors() {
		return errors;
	}
}
