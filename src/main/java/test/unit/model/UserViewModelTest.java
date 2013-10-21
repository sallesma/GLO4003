package test.unit.model;

import static org.junit.Assert.*;
import model.UserViewModel;

import org.junit.Test;

public class UserViewModelTest {
	

    @Test
    public void NewModelIsEmpty() {
    	//When
    	UserViewModel model = getNewUserViewModel();
    	
    	//Then
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
    	//Before
    	UserViewModel model = getPopulatedUserViewModel();
    	
    	assertTrue(model.getAddress().contentEquals("test"));
    }
    
    @Test
    public void PopulatedModelHasFirstName() {
    	//Before
    	UserViewModel model = getPopulatedUserViewModel();
    	
    	//When
    	String result = model.getFirstName();
    	
    	//Then
    	assertTrue(result.contentEquals("test"));
    }
    
    @Test
    public void PopulatedModelHasLastName() {
    	//Before
    	UserViewModel model = getPopulatedUserViewModel();
    	
    	//When
    	String result = model.getLastName();
    	
    	//Then
    	assertTrue(result.contentEquals("test"));
    }
    
    @Test
    public void PopulatedModelHasPhoneNumber() {
    	//Before
    	UserViewModel model = getPopulatedUserViewModel();
    	
    	//When
    	String result = model.getPhoneNumber();
    	
    	//Then
    	assertTrue(result.contentEquals("test"));
    }
    
    @Test
    public void PopulatedModelHasUserName() {
    	//Before
    	UserViewModel model = getPopulatedUserViewModel();
    	
    	//When
    	String result = model.getUsername();
    	
    	//Then
    	assertTrue(result.contentEquals("test"));
    }
    
    @Test
    public void PopulatedModelHasPassword() {
    	//Before
    	UserViewModel model = getPopulatedUserViewModel();
    	
    	//When
    	String result = model.getPassword();
    	
    	//Then
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
