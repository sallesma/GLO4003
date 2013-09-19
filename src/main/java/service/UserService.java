package service;

import org.springframework.beans.factory.annotation.Autowired;

import database.DbHelper;
import exceptions.SaveException;
import model.UserModel;

public class UserService {
	
	@Autowired
	private DbHelper dbHelper;	
	
	public void saveNew(UserModel user) throws SaveException {		
		dbHelper.add(user);		
	}
}
