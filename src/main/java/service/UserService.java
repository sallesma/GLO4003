package service;

import helper.CookieHelper;
import helper.UserConverter;

import java.util.List;

import model.UserModel;
import model.UserViewModel;
import validator.ModelValidator;
import database.DbHelper;
import exceptions.SaveException;

public class UserService {	
	
	private UserConverter converter = new UserConverter();
	private ModelValidator validator = new ModelValidator();
	private CookieHelper cookieHelper = new CookieHelper();
	
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
	
	public void modify(UserViewModel user) throws SaveException {
		DbHelper dbHelper = DbHelper.getInstance();
		List<String> errors = validator.validate(user);
		if (!dbHelper.userExist(user.getUsername())) {
			errors.add("Le nom d'utilisateur n'existe pas");
		}
		
		if (!errors.isEmpty()) {
			throw new SaveException(errors);
		}
		
		dbHelper.save(converter.convert(user), user.getId());			
	}
	
	public Boolean isLoginValid(String username, String password) {
		DbHelper dbHelper = DbHelper.getInstance();
		
		return dbHelper.isLoginValid(username, password);		
	}
	
	public UserModel getUser(String username) {
		return DbHelper.getInstance().getUserByUsername(username);
	}
	
	public UserViewModel convert(UserModel model) {
		return converter.convert(model);
	}
}
