package test.unit.helper;

import static org.junit.Assert.*;
import helper.UserConverter;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

import java.util.concurrent.atomic.AtomicInteger;

import model.UserModel;
import model.UserViewModel;

import org.junit.Test;

public class UserConverterTest {
	
	private UserConverter converter = spy(new UserConverter());
	
	
	@Test
	public void canConvertUserModelList() {
		Map<Integer, UserModel> users = populate();
		
		assertTrue(converter.convert(users).size() == 4);
		verify(converter, times(4)).convert(any(UserModel.class));		
	}
	
	@Test
	public void canConvertUserModel() {
		UserModel model = spy(createUserModel("Matt", "Martin", "MM", "password", false));
		converter.convert(model);		
		
		verify(model, times(1)).getAddress();
		verify(model, times(1)).getFirstName();
		verify(model, times(1)).getLastName();
		verify(model, times(1)).getPassword();
		verify(model, times(1)).getPhoneNumber();
		verify(model, times(1)).getUsername();
		verify(model, times(1)).isAdmin();
	}
	
	@Test
	public void canConvertUserViewModel() {
		UserViewModel model = spy(createUserViewModel("Matt", "Martin", "MM", "password", false));
		converter.convert(model);		
		
		verify(model, times(1)).getAddress();
		verify(model, times(1)).getFirstName();
		verify(model, times(1)).getLastName();
		verify(model, times(1)).getPassword();
		verify(model, times(1)).getPhoneNumber();
		verify(model, times(1)).getUsername();		
	}
	
	private Map<Integer, UserModel> populate() {		
		AtomicInteger nextId = new AtomicInteger(0);
		Map<Integer, UserModel> users = new HashMap<Integer, UserModel>();
		
		UserModel user1 = createUserModel("Matt", "Martin", "MM", "password", false);
		UserModel user2 = createUserModel("Houde", "Louis-Jos??", "LH", "password", false);
		UserModel user3 = createUserModel("Baddouri", "Rachid", "RB", "password", false);
		UserModel user4 = createUserModel("Petit", "Martin", "MP", "password", false);
		
		users.put(nextId.incrementAndGet(), user1);
		users.put(nextId.incrementAndGet(), user2);
		users.put(nextId.incrementAndGet(), user3);
		users.put(nextId.incrementAndGet(), user4);
		
		return users;
	}
	
	private UserModel createUserModel(String lastName, String firstName, String username, String password, boolean isAdmin) {
		UserModel user= new UserModel();
		user.setAddress("Address");
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setPassword(password);
		user.setPhoneNumber("(444) 444-4444");
		user.setUsername(username);	
		user.setIsAdmin(isAdmin);
		return user;
	}	
	
	private UserViewModel createUserViewModel(String lastName, String firstName, String username, String password, boolean isAdmin) {
		UserViewModel user= new UserViewModel();
		user.setAddress("Address");
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setPassword(password);
		user.setPhoneNumber("(444) 444-4444");
		user.setUsername(username);	
		user.setIsAdmin(isAdmin);
		
		return user;
	}	
}
