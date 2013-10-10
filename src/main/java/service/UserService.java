package service;

import helper.UserConverter;

import java.util.ArrayList;
import java.util.List;

import model.UserModel;
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
			errors.add("Le nom d'utilisateur est deja utilise");
		}
		
		if (!errors.isEmpty()) {
			throw new SaveException(errors);
		}
		
		dbHelper.addUser(converter.convert(user));			
	}
	
	public List<String> validate(String username, String password) {		
		DbHelper dbHelper = DbHelper.getInstance();
		List<String> warnings = new ArrayList<String>();
		if (!dbHelper.userExist(username)) {
			warnings.add("Le nom d'utilisateur n'existe pas");			
		} else if (!dbHelper.isLoginValid(username, password)) {
			warnings.add("Le nom d'utilisateur ou le mot de passe est invalide");
		}
		
		return warnings;		
	}
	
	public UserModel getUser(String username) {
		return DbHelper.getInstance().getUserByUsername(username);
	}
	
	public UserViewModel convert(UserModel model) {
		return converter.convert(model);
	}
}
