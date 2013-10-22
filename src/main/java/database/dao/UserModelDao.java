package database.dao;

import java.util.List;

import model.UserModel;
import nu.xom.Element;
import database.XmlModelConverter;
import database.dto.FileAccess;
import exceptions.PersistException;

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