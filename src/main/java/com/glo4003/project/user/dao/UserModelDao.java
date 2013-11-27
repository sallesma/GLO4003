package com.glo4003.project.user.dao;

import java.util.List;

import com.glo4003.project.database.converter.XmlModelConverter;
import com.glo4003.project.database.dao.Dao;
import com.glo4003.project.database.dto.FileAccess;
import com.glo4003.project.database.exception.PersistException;
import com.glo4003.project.database.model.UserModel;

import nu.xom.Element;

public class UserModelDao extends Dao<UserModel> {

	public UserModelDao(XmlModelConverter dto, FileAccess fileAccess) {
		super(UserModel.class, dto, fileAccess);	
	}

	public UserModelDao() {
		super(UserModel.class);		
	}	
	
	public UserModel getUserByUsername(String username) throws PersistException {
		List<Element> models = fileAccess.getAll(className);
		for (Element myUser : models) {			
			UserModel model = (UserModel) converter.toObject(myUser);
			if (model.getUsername().contentEquals(username)) {
				return model;			
			}			
		}
		
		return null;
	}
	
	public Boolean isLoginValid(String username, String password) throws PersistException {		
		for(Element user : fileAccess.getAll(className)) {
			UserModel model = (UserModel) converter.toObject(user);			
			if(model.getUsername().contentEquals(username)) {
				if(model.getPassword().contentEquals(password)) {
					return true;
				}
			}
		}
		
		return false;
	}
}