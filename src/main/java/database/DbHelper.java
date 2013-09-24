package database;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import model.UserModel;

public class DbHelper {
	
	private AtomicInteger nextId = new AtomicInteger(0);
	private Map<Integer, UserModel> users = new HashMap<Integer, UserModel>();
	private Map<Integer, UserModel> matchs = new HashMap<Integer, UserModel>();
	
	private static DbHelper db = new DbHelper();
	
	private DbHelper() {
		populate();
	}
	
	public Collection<UserModel> getAllUsers() {
		return users.values();
	}
	
	public static DbHelper getInstance() {
		return db;
	}	
	
	public void add(UserModel user) {
		users.put(nextId.incrementAndGet(), user);
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
	
	public static void replaceDb(DbHelper dbHelper) {
		db = dbHelper;
	}
	
	public static DbHelper getNewDb() {
		return new DbHelper();
	}
	
	public void dropAndRepopulate() {
		nextId = new AtomicInteger(0);
		
		users.clear();
		matchs.clear();
		
		populate();
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
 }
