package test.unit.model;

import static org.junit.Assert.*;

import org.junit.Test;

import com.glo4003.project.user.model.view.UserViewModel;

public class UserViewModelTest {
	

    @Test
    public void NewModelIsEmpty() {
    	UserViewModel model = getNewUserViewModel();
    	
    	assertTrue(model.getAddress().isEmpty());
    	assertTrue(model.getFirstName().isEmpty());
    	assertTrue(model.getLastName().isEmpty());
    	assertTrue(model.getPassword().isEmpty());
    	assertTrue(model.getPhoneNumber().isEmpty());
    	assertTrue(model.getUsername().isEmpty());
    }
    
    @Test
    public void PopulatedModelHasAddress() {
    	UserViewModel model = getPopulatedUserViewModel();
    	
    	assertTrue(model.getAddress().contentEquals("test"));
    }
    
    @Test
    public void PopulatedModelHasFirstName() {
    	UserViewModel model = getPopulatedUserViewModel();
    	
    	String result = model.getFirstName();
    	
    	assertTrue(result.contentEquals("test"));
    }
    
    @Test
    public void PopulatedModelHasLastName() {
    	UserViewModel model = getPopulatedUserViewModel();
    	
    	String result = model.getLastName();
    	
    	assertTrue(result.contentEquals("test"));
    }
    
    @Test
    public void PopulatedModelHasPhoneNumber() {
    	UserViewModel model = getPopulatedUserViewModel();
    	
    	String result = model.getPhoneNumber();
    	
    	assertTrue(result.contentEquals("test"));
    }
    
    @Test
    public void PopulatedModelHasUserName() {
    	UserViewModel model = getPopulatedUserViewModel();
    	
    	String result = model.getUsername();
    	
    	assertTrue(result.contentEquals("test"));
    }
    
    @Test
    public void PopulatedModelHasPassword() {
    	UserViewModel model = getPopulatedUserViewModel();
    	
    	String result = model.getPassword();
    	
    	assertTrue(result.contentEquals("test"));
    }    
    
    private UserViewModel getPopulatedUserViewModel() {
    	UserViewModel model = new UserViewModel();
    	model.setAddress("test");
    	model.setFirstName("test");
    	model.setLastName("test");
    	model.setPassword("test");
    	model.setPhoneNumber("test");
    	model.setUsername("test");    	
    	
    	return model;
    }
    
    private UserViewModel getNewUserViewModel() {
    	
    	return new UserViewModel();    	
    }
    
}
