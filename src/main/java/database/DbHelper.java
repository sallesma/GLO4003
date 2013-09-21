package database;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.glo4003.project.UserController;

import exceptions.SaveException;
import model.UserModel;

public class DbHelper {
	
	private AtomicInteger nextId = new AtomicInteger(0);
	private Map<Integer, UserModel> users = new HashMap<Integer, UserModel>();
	private Map<Integer, UserModel> matchs = new HashMap<Integer, UserModel>();
	
	private static DbHelper db = new DbHelper();
	
	private DbHelper() {
		populate();
	}
	
	public static DbHelper getInstance() {
		return db;
	}
	
	private void populate() {
		UserModel user1 = createUser("Matt", "Martin", "MM", "password");
		UserModel user2 = createUser("Houde", "Louis-Jos??", "LH", "password");
		UserModel user3 = createUser("Baddouri", "Rachid", "RB", "password");
		UserModel user4 = createUser("Petit", "Martin", "MP", "password");
		
		users.put(nextId.incrementAndGet(), user1);
		users.put(nextId.incrementAndGet(), user2);
		users.put(nextId.incrementAndGet(), user3);
		users.put(nextId.incrementAndGet(), user4);
	}
	
	private UserModel createUser(String lastName, String firstName, String username, String password) {
		UserModel user= new UserModel();
		user.setAddress("Address");
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setPassword(password);
		user.setPhoneNumber("(444) 444-4444");
		user.setUsername(username);	
		
		return user;
	}
	
	public void add(UserModel user) throws SaveException {
		users.put(nextId.incrementAndGet(), user);
	}
	
	public void LogUsers() {
		for(UserModel user: users.values()) {
			UserController.logger.info(user.getUsername());
		}
	}
	
	public Boolean userExist(String username) {
		Boolean exist = false;
		for (UserModel myUser : users.values()) {
			if (myUser.getUsername().contentEquals(username)) {
				exist = true;			
			}			
		}
		
		return exist;
	}
 }
