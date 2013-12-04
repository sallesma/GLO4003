package com.glo4003.project.user.service;

import java.util.ArrayList;
import java.util.List;

import com.glo4003.project.database.exception.ConvertException;
import com.glo4003.project.database.exception.PersistException;
import com.glo4003.project.database.exception.SaveException;
import com.glo4003.project.global.ModelValidator;
import com.glo4003.project.user.dao.UserModelDao;
import com.glo4003.project.user.helper.UserConverter;
import com.glo4003.project.user.model.UserConcreteModel;
import com.glo4003.project.user.model.view.UserViewModel;

public class UserService {	
	
	private UserModelDao userDao;
	private UserConverter converter;
	private ModelValidator validator;
	
	public UserService (UserModelDao userDao, UserConverter converter, ModelValidator validator)
	{
		this.userDao = userDao;
		this.converter = converter;
		this.validator = validator;
	}
	
	public void saveNew(UserViewModel user) throws SaveException, PersistException, ConvertException {		
		List<String> errors = validator.validate(user);
		if (userDao.getUserByUsername(user.getUsername()) != null) {
			errors.add("Le nom d'utilisateur est deja utilise");
		}
		
		if (!errors.isEmpty()) {
			throw new SaveException(errors);
		}
		
		userDao.save(converter.convertToDB(converter.convertFromView(user)));			
	}	

	public void modify(UserViewModel user) throws SaveException, PersistException, ConvertException {		
		List<String> errors = validator.validate(user);
		if (userDao.getUserByUsername(user.getUsername()) == null) {
			errors.add("Le nom d'utilisateur n'existe pas");
		}
		
		if (!errors.isEmpty()) {
			throw new SaveException(errors);
		}
		userDao.delete(userDao.getUserByUsername(user.getUsername()));
		userDao.save(converter.convertToDB(converter.convertFromView(user)));			
	}

	public List<String> validate(String username, String password) throws PersistException {			
		List<String> warnings = new ArrayList<String>();
		if (userDao.getUserByUsername(username) == null) {
			warnings.add("Le nom d'utilisateur n'existe pas");			
		} else if (!userDao.isLoginValid(username, password)) {
			warnings.add("Le nom d'utilisateur ou le mot de passe est invalide");
		}
		
		return warnings;		
	}
	
	public UserConcreteModel getUser(String username) throws PersistException {
		return converter.convertFromDB(userDao.getUserByUsername(username));
	}
	
	public UserViewModel convert(UserConcreteModel model) {
		return converter.convertToView(model);
	}
	
	public void replaceConverter(UserConverter converter) {
		this.converter = converter;
	}
	
	public void replaceValidator(ModelValidator validator) {
		this.validator = validator;
	}
	
	public void replaceDao(UserModelDao userDao) {
		this.userDao = userDao;		
	}
}
