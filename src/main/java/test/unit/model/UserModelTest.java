package test.unit.model;

import static org.junit.Assert.*;
import model.UserModel;

import org.junit.Test;

public class UserModelTest {

    @Test
    public void NewModelIsEmpty() {
    	UserModel model = getNewUserModel();
    	
    	assertTrue(model.getAddress().isEmpty());
    	assertTrue(model.getFirstName().isEmpty());
    	assertTrue(model.getLastName().isEmpty());
    	assertTrue(model.getPassword().isEmpty());
    	assertTrue(model.getPhoneNumber().isEmpty());
    	assertTrue(model.getUsername().isEmpty()); 
    	assertFalse(model.isAdmin()); 
    }
    
    @Test
    public void PopulatedModelHasAddress() {
    	//Before
    	UserModel model = getPopulatedUserModel();
    	
    	//When
    	String result = model.getAddress();
    	
    	//Then    	
    	assertTrue(result.contentEquals("test"));
    }
    
    @Test
    public void PopulatedModelHasFirstName() {
    	//Before
    	UserModel model = getPopulatedUserModel();
    	
    	//When
    	String result = model.getFirstName();
    	
    	//Then
    	assertTrue(result.contentEquals("test"));
    }
    
    @Test
    public void PopulatedModelHasLastName() {
    	//Before
    	UserModel model = getPopulatedUserModel();
    	
    	//When
    	String result = model.getLastName();
    	
    	//Then
    	assertTrue(result.contentEquals("test"));
    }
    
    @Test
    public void PopulatedModelHasPhoneNumber() {
    	//Before
    	UserModel model = getPopulatedUserModel();
    	
    	//When
    	String result = model.getPhoneNumber();
    	
    	//Then
    	assertTrue(result.contentEquals("test"));
    }
    
    @Test
    public void PopulatedModelHasUserName() {
    	//Before
    	UserModel model = getPopulatedUserModel();
    	
    	//When
    	String result = model.getUsername();
    	
    	//Then    	
    	assertTrue(result.contentEquals("test"));
    }
    
    @Test
    public void PopulatedModelHasPassword() {
    	//Before
    	UserModel model = getPopulatedUserModel();
    	
    	//When
    	String result = model.getPassword();
    	
    	//Then
    	assertTrue(result.contentEquals("test"));
    }
    
    @Test
    public void PopulatedUSerModelIsNotAdmin() {
    	//Before
    	UserModel model = getPopulatedUserModel();
    	
    	//When
    	Boolean result = model.isAdmin();
    	
    	//Then
    	assertFalse(result);
    }
    
    private UserModel getPopulatedUserModel() {
    	UserModel model = new UserModel();
    	model.setAddress("test");
    	model.setFirstName("test");
    	model.setLastName("test");
    	model.setPassword("test");
    	model.setPhoneNumber("test");
    	model.setUsername("test");
    	model.setIsAdmin(false);
    	
    	return model;
    }
    
    private UserModel getNewUserModel() {
    	
    	return new UserModel();    	
    }
    
}
