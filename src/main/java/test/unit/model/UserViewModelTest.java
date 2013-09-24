package test.unit.model;

import static org.junit.Assert.*;
import model.UserViewModel;

import org.junit.Test;

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
    	assertNull(model.getId());    	
    }
    
    @Test
    public void PopulatedModelHasAddress() {
    	UserViewModel model = getPopulatedUserViewModel();
    	
    	assertTrue(model.getAddress().contentEquals("test"));
    }
    
    @Test
    public void PopulatedModelHasFirstName() {
    	UserViewModel model = getPopulatedUserViewModel();
    	
    	assertTrue(model.getFirstName().contentEquals("test"));
    }
    
    @Test
    public void PopulatedModelHasLastName() {
    	UserViewModel model = getPopulatedUserViewModel();
    	
    	assertTrue(model.getLastName().contentEquals("test"));
    }
    
    @Test
    public void PopulatedModelHasPhoneNumber() {
    	UserViewModel model = getPopulatedUserViewModel();
    	
    	assertTrue(model.getPhoneNumber().contentEquals("test"));
    }
    
    @Test
    public void PopulatedModelHasUserName() {
    	UserViewModel model = getPopulatedUserViewModel();
    	
    	assertTrue(model.getUsername().contentEquals("test"));
    }
    
    @Test
    public void PopulatedModelHasPassword() {
    	UserViewModel model = getPopulatedUserViewModel();
    	
    	assertTrue(model.getPassword().contentEquals("test"));
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
