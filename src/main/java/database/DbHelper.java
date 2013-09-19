package database;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.inject.Singleton;

import org.springframework.stereotype.Repository;

import exceptions.SaveException;
import model.UserModel;

@Repository
@Singleton
public class DbHelper {
	
	private AtomicInteger nextId = new AtomicInteger(0);
	private Map<Integer, UserModel> users = new HashMap<Integer, UserModel>();
	
	public DbHelper() {
		populate();
	}
	
	private void populate() {
		UserModel user1 = createUser("Matt", "Martin", "MM", "password");
		UserModel user2 = createUser("Houde", "Louis-José", "LH", "password");
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
		user.setPhoneNumber("444 444-4444");
		user.setUsername(username);	
		
		return user;
	}
	
	public void add(UserModel user) throws SaveException {
		String username = user.getUsername();
		for (UserModel myUser : users.values()) {
			if (myUser.getUsername() == username) {
				throw new SaveException("The username is invalid: A user aldready own this username");
			}
		}
		
		users.put(nextId.incrementAndGet(), user);
	}
 }
