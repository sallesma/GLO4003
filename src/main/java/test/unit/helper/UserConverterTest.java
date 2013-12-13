package test.unit.helper;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

import com.glo4003.project.database.dto.UserDto;
import com.glo4003.project.database.exception.PersistException;
import com.glo4003.project.user.helper.UserConverter;
import com.glo4003.project.user.model.UserConcreteModel;
import com.glo4003.project.user.model.view.UserViewModel;

public class UserConverterTest {
	
	private UserConverter converter = new UserConverter();	
	
	@Test
	public void canConvertUserModelToDB() {
		
		UserConcreteModel model = spy(createUserConcreteModel("Matt", "Martin", "MM", "password", false));
		
		
		converter.convertToDB(model);		
		
		verify(model, times(1)).getAddress();
		verify(model, times(1)).getFirstName();
		verify(model, times(1)).getLastName();
		verify(model, times(1)).getPassword();
		verify(model, times(1)).getPhoneNumber();
		verify(model, times(1)).getUsername();
		verify(model, times(1)).isAdmin();
	}
	
	@Test
	public void canConvertUserModelToView() {
		
		UserConcreteModel model = spy(createUserConcreteModel("Matt", "Martin", "MM", "password", false));
		
		converter.convertToView(model);		
		
		verify(model, times(1)).getAddress();
		verify(model, times(1)).getFirstName();
		verify(model, times(1)).getLastName();
		verify(model, times(1)).getPassword();
		verify(model, times(1)).getPhoneNumber();
		verify(model, times(1)).getUsername();
		verify(model, times(1)).isAdmin();
	}
	
	@Test
	public void canConvertUserViewModel() throws PersistException {
		
		UserViewModel model = spy(createUserViewModel("Matt", "Martin", "MM", "password", false));
		
		converter.convertFromView(model);		
		
		verify(model, times(1)).getAddress();
		verify(model, times(1)).getFirstName();
		verify(model, times(1)).getLastName();
		verify(model, times(1)).getPassword();
		verify(model, times(1)).getPhoneNumber();
		verify(model, times(1)).getUsername();		
	}
	
	@Test
	public void canConvertUserDBModel() {
		
		UserDto model = spy(createUserModel("Matt", "Martin", "MM", "password", false));
	
		converter.convertFromDB(model);		
		
		verify(model, times(1)).getAddress();
		verify(model, times(1)).getFirstName();
		verify(model, times(1)).getLastName();
		verify(model, times(1)).getPassword();
		verify(model, times(1)).getPhoneNumber();
		verify(model, times(1)).getUsername();		
	}
	
	private Map<Integer, UserDto> populate() {	
		AtomicInteger nextId = new AtomicInteger(0);
		Map<Integer, UserDto> users = new HashMap<Integer, UserDto>();
		
		UserDto user1 = createUserModel("Matt", "Martin", "MM", "password", false);
		UserDto user2 = createUserModel("Houde", "Louis-Jos??", "LH", "password", false);
		UserDto user3 = createUserModel("Baddouri", "Rachid", "RB", "password", false);
		UserDto user4 = createUserModel("Petit", "Martin", "MP", "password", false);
		
		users.put(nextId.incrementAndGet(), user1);
		users.put(nextId.incrementAndGet(), user2);
		users.put(nextId.incrementAndGet(), user3);
		users.put(nextId.incrementAndGet(), user4);
		
		return users;
	}
	
	private UserConcreteModel createUserConcreteModel(String lastName, String firstName, String username, String password, boolean isAdmin) {
		UserConcreteModel user= new UserConcreteModel();
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
	
	private UserDto createUserModel(String lastName, String firstName, String username, String password, boolean isAdmin) {
		UserDto user= new UserDto();
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
