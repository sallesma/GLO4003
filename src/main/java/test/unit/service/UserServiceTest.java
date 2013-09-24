package test.unit.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import model.UserViewModel;
import model.UserModel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import service.UserService;
import database.DbHelper;
import exceptions.SaveException;

public class UserServiceTest {	
	
	private DbHelper helper;
	
	private UserService service;
	
	@Before
	public void initialize() {
		service = spy(new UserService());
		helper = mock(DbHelper.class);
		DbHelper.replaceDb(helper);
	}
	
	@Test
	public void dontThrowExceptionOnSaveWellFormedUser() throws SaveException {
		UserViewModel viewModel = getWellFormedUserModel();
		
		service.saveNew(viewModel);		
	}
	
	@Test
	public void throwExceptionOnNotWellFormedUser() {
		UserViewModel viewModel = getNotWellFormedUserModel();
		SaveException ex = null;
		try {
			service.saveNew(viewModel);	
		} catch (SaveException e) {
			ex = e;
		}
		
		assertTrue(ex.getErrors().size() == 5);
	}
	
	@Test
	public void canSaveWellFormedUser() throws SaveException {
		UserViewModel viewModel = getWellFormedUserModel();		
		
		service.saveNew(viewModel);		
		
		verify(helper).addUser(any(UserModel.class));
	}
	
	@Test
	public void dontSaveNotWellFormedUser() throws SaveException {
		UserViewModel viewModel = getNotWellFormedUserModel();		
		
		try {
			service.saveNew(viewModel);	
		} catch(SaveException e) {			
		}
		
		verify(helper, never()).addUser(any(UserModel.class));
	}
	
	@Test
	public void canThrowExceptionOnExistingUser() {
		UserViewModel model = getWellFormedUserModel();
		Mockito.when(helper.userExist(model.getUsername())).thenReturn(true);
		
		Boolean exceptionFound = false;
		try {
			service.saveNew(model);	
		} catch(SaveException e) {	
			exceptionFound = true;
		}
		
		assertTrue(exceptionFound);
		verify(helper, never()).addUser(any(UserModel.class));
	}
	
	private UserViewModel getWellFormedUserModel() {
		UserViewModel model = new UserViewModel();
    	model.setAddress("test");
    	model.setFirstName("test");
    	model.setLastName("test");
    	model.setPassword("test");
    	model.setPhoneNumber("418-465-2430");
    	model.setUsername("test");    	
    	
    	return model;
	}
	
	private UserViewModel getNotWellFormedUserModel() {
		UserViewModel model = new UserViewModel();
    	model.setAddress("");
    	model.setFirstName("");
    	model.setLastName("");
    	model.setPassword("");
    	model.setPhoneNumber("");
    	model.setUsername("");    	
    	
    	return model;
	}
}
