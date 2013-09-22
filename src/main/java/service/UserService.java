package service;

import helper.UserConverter;

import java.util.List;

import model.UserViewModel;
import validator.ModelValidator;
import database.DbHelper;
import exceptions.SaveException;


public class UserService {	
	
	private UserConverter converter = new UserConverter();
	private ModelValidator validator = new ModelValidator();
	
	public void saveNew(UserViewModel user) throws SaveException {
		DbHelper dbHelper = DbHelper.getInstance();
		List<String> errors = validator.validate(user);
		if (dbHelper.userExist(user.getUsername())) {
			errors.add("Le nom d'utilisateur est déja utilisé");
		}
		
		if (!errors.isEmpty()) {
			throw new SaveException(errors);
		}
		
		dbHelper.add(converter.convert(user));			
	}
}
