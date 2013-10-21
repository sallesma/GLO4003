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
		//Before
		UserViewModel viewModel = getWellFormedUserModel();
		
		//When		
		service.saveNew(viewModel);	
		
		//Then
		//No exception found
	}
	
	@Test
	public void throwExceptionOnNotWellFormedUser() {
		//Before
		UserViewModel viewModel = getNotWellFormedUserModel();
		SaveException ex = null;
		try {
			//When
			service.saveNew(viewModel);	
		} catch (SaveException e) {
			ex = e;
		}
		
		//Then
		assertTrue(ex.getErrors().size() == 5);
	}
	
	@Test
	public void canSaveWellFormedUser() throws SaveException {
		//Before
		UserViewModel viewModel = getWellFormedUserModel();		
		
		//When
		service.saveNew(viewModel);		
		
		//Then
		verify(helper).addUser(any(UserModel.class));
	}
	
	@Test
	public void dontSaveNotWellFormedUser() throws SaveException {
		//Before
		UserViewModel viewModel = getNotWellFormedUserModel();		
		
		try {
			//When
			service.saveNew(viewModel);	
		} catch(SaveException e) {			
		}
		
		//Then
		verify(helper, never()).addUser(any(UserModel.class));
	}
	
	@Test
	public void canThrowExceptionOnExistingUser() {
		//Before
		UserViewModel model = getWellFormedUserModel();
		Mockito.when(helper.userExist(model.getUsername())).thenReturn(true);
		
		Boolean exceptionFound = false;
		try {
			//When
			service.saveNew(model);	
		} catch(SaveException e) {	
			exceptionFound = true;
		}
		
		//Then
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
