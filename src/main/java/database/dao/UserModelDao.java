package database.dao;

import model.UserModel;
import nu.xom.Element;
import database.XmlModelConverter;
import database.dto.FileAccess;

public class UserModelDao extends Dao<UserModel> {

	public UserModelDao(XmlModelConverter dto, FileAccess fileAccess) {
		super(UserModel.class, dto, fileAccess);	
	}

	public UserModelDao() {
		super(UserModel.class);		
	}	
	
	public UserModel getUserByUsername(String username) {
		for (Element myUser : fileAccess.getAll(className)) {
			UserModel model = (UserModel) converter.toObject(myUser);
			if (model.getUsername().contentEquals(username)) {
				return model;			
			}			
		}
		
		return null;
	}
	
	public Boolean isLoginValid(String username, String password) {		
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