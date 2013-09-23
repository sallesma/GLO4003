package validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import model.UserViewModel;

public class ModelValidator {
	
	private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
	
	public List<String> validate(UserViewModel model) {		
		List<String> errors = new ArrayList<String>(2);		
		
		Set<ConstraintViolation<UserViewModel>> violations = validator.validate(model);
		for (ConstraintViolation<UserViewModel> contraintes : violations) {	
			errors.add(contraintes.getMessage());
		}
		
		return errors;
	}	
}
