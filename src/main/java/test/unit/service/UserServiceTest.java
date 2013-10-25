package test.unit.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import model.UserModel;
import model.UserViewModel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import service.UserService;
import database.dao.UserModelDao;
import exceptions.ConvertException;
import exceptions.PersistException;
import exceptions.SaveException;

public class UserServiceTest {	
	
	private UserModelDao dao;
	
	private UserService service;
	
	@Before
	public void initialize() {
		service = spy(new UserService());
		dao = mock(UserModelDao.class);		
	}
	
	@Test
	public void dontThrowExceptionOnSaveWellFormedUser() throws SaveException, PersistException, ConvertException {
		//Before
		UserViewModel viewModel = getWellFormedUserModel();
		
		//When		
		service.saveNew(viewModel);	
		
		//Then
		//No exception found
	}
	
	@Test
	public void throwExceptionOnNotWellFormedUser() throws PersistException, ConvertException {
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
	public void canSaveWellFormedUser() throws SaveException, PersistException, ConvertException {
		//Before
		UserViewModel viewModel = getWellFormedUserModel();		
		
		//When
		service.saveNew(viewModel);		
		
		//Then
		verify(dao).save(any(UserModel.class));
	}
	
	@Test
	public void dontSaveNotWellFormedUser() throws SaveException, PersistException, ConvertException {
		//Before
		UserViewModel viewModel = getNotWellFormedUserModel();		
		
		try {
			//When
			service.saveNew(viewModel);	
		} catch(SaveException e) {			
		}
		
		//Then
		verify(dao, never()).save(any(UserModel.class));
	}
	
	@Test
	public void canThrowExceptionOnExistingUser() throws PersistException, ConvertException {
		//Before
		UserViewModel model = getWellFormedUserModel();
		Mockito.when(dao.getUserByUsername(model.getUsername())).thenReturn(new UserModel());
		
		Boolean exceptionFound = false;
		try {
			//When
			service.saveNew(model);	
		} catch(SaveException e) {	
			exceptionFound = true;
		}
		
		//Then
		assertTrue(exceptionFound);
		verify(dao, never()).save(any(UserModel.class));
	}
	
	@Test
	public void dontThrowExceptionOnModifyWellFormedUser() throws SaveException, PersistException, ConvertException {
		//Before
		UserViewModel viewModel = getWellFormedUserModel();
		
		//When		
		service.modify(viewModel);	
		
		//Then
		//No exception found
	}
	
	@Test
	public void throwExceptionOnModifyNotWellFormedUser() throws PersistException, ConvertException {
		//Before
		UserViewModel viewModel = getNotWellFormedUserModel();
		SaveException ex = null;
		try {
			//When
			service.modify(viewModel);	
		} catch (SaveException e) {
			ex = e;
		}
		
		//Then
		assertTrue(ex.getErrors().size() == 5);
	}
	
	@Test
	public void canModifyWellFormedUser() throws SaveException, PersistException, ConvertException {
		//Before
		UserViewModel viewModel = getWellFormedUserModel();		
		
		//When
		service.modify(viewModel);		
		
		//Then
		verify(dao).save(any(UserModel.class));
	}	
	
	@Test
	public void dontModifyNotWellFormedUser() throws SaveException, PersistException, ConvertException {
		//Before
		UserViewModel viewModel = getNotWellFormedUserModel();		
		
		try {
			//When
			service.modify(viewModel);	
		} catch(SaveException e) {			
		}
		
		//Then
		verify(dao, never()).save(any(UserModel.class));
	}
	
	
	
	@Test
	public void canThrowExceptionOnNotExistingUser() throws PersistException, ConvertException {
		//Before
		UserViewModel model = getWellFormedUserModel();
		Mockito.when(dao.getUserByUsername(model.getUsername())).thenReturn(getWellFormedUserModelDifferentUsername());
		
		Boolean exceptionFound = false;
		try {
			//When
			service.modify(model);	
		} catch(SaveException e) {	
			exceptionFound = true;
		}
		
		//Then
		assertTrue(exceptionFound);
		verify(dao, never()).save(any(UserModel.class));
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
	
	private UserModel getWellFormedUserModelDifferentUsername() {
		UserModel model = new UserModel();
    	model.setAddress("test");
    	model.setFirstName("test");
    	model.setLastName("test");
    	model.setPassword("test");
    	model.setPhoneNumber("418-465-2430");
    	model.setUsername("test_diff");    	
    	
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
