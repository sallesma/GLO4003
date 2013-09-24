package test.unit.model;

import static org.junit.Assert.assertTrue;
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
    }
    
    @Test
    public void PopulatedModelHasAddress() {
    	UserModel model = getPopulatedUserModel();
    	
    	assertTrue(model.getAddress().contentEquals("test"));
    }
    
    @Test
    public void PopulatedModelHasFirstName() {
    	UserModel model = getPopulatedUserModel();
    	
    	assertTrue(model.getFirstName().contentEquals("test"));
    }
    
    @Test
    public void PopulatedModelHasLastName() {
    	UserModel model = getPopulatedUserModel();
    	
    	assertTrue(model.getLastName().contentEquals("test"));
    }
    
    @Test
    public void PopulatedModelHasPhoneNumber() {
    	UserModel model = getPopulatedUserModel();
    	
    	assertTrue(model.getPhoneNumber().contentEquals("test"));
    }
    
    @Test
    public void PopulatedModelHasUserName() {
    	UserModel model = getPopulatedUserModel();
    	
    	assertTrue(model.getUsername().contentEquals("test"));
    }
    
    @Test
    public void PopulatedModelHasPassword() {
    	UserModel model = getPopulatedUserModel();
    	
    	assertTrue(model.getPassword().contentEquals("test"));
    }    
    
    private UserModel getPopulatedUserModel() {
    	UserModel model = new UserModel();
    	model.setAddress("test");
    	model.setFirstName("test");
    	model.setLastName("test");
    	model.setPassword("test");
    	model.setPhoneNumber("test");
    	model.setUsername("test");    	
    	
    	return model;
    }
    
    private UserModel getNewUserModel() {
    	
    	return new UserModel();    	
    }
    
}
