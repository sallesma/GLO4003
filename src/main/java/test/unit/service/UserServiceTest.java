package test.unit.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import helper.UserConverter;
import model.UserModel;
import model.UserViewModel;
import exceptions.SaveException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import service.UserService;
import validator.ModelValidator;
import database.dao.UserModelDao;
import exceptions.ConvertException;
import exceptions.PersistException;
import exceptions.SaveException;

public class UserServiceTest {	
	
	private UserModelDao dao;	
	private UserConverter converter;
	private ModelValidator validator;
	private UserService service;
	
	@Before
	public void initialize() {
		service = spy(new UserService());
		dao = mock(UserModelDao.class);	
		converter = mock(UserConverter.class);
		validator = mock(ModelValidator.class);
		service.replaceConverter(converter);
		service.replaceDao(dao);
		service.replaceValidator(validator);
	}
	
	@Test
	public void canSaveUser() throws SaveException, PersistException, ConvertException {
		//Before		
		UserViewModel viewModel = getWellFormedUserModel();
		
		//When		
		service.saveNew(viewModel);	
		
		//Then
		verify(dao, times(1)).save(any(UserModel.class));
	}
	
	@Test
	public void throwExceptionOnNotWellFormedUser() throws PersistException, ConvertException {
		//Before
		List<String> errors = new ArrayList<String>();
		errors.add("test");
		when(validator.validate(any(UserViewModel.class))).thenReturn(errors);
		UserViewModel viewModel = getNotWellFormedUserModel();
		SaveException ex = null;
		try {
			//When
			service.saveNew(viewModel);	
		} catch (SaveException e) {
			ex = e;
		}
		
		//Then
		assertTrue(ex != null);
	}
	
	@Test
	public void neverSaveNotWellFormedUser() throws PersistException, ConvertException {
		//Before
		List<String> errors = new ArrayList<String>();
		errors.add("test");
		when(validator.validate(any(UserViewModel.class))).thenReturn(errors);
		UserViewModel viewModel = getNotWellFormedUserModel();
		SaveException ex = null;
		try {
			//When
			service.saveNew(viewModel);	
		} catch (SaveException e) {
			ex = e;
		}
		
		//Then
		verify(dao, never()).save(any(UserModel.class));
	}
	
	
	
	@Test
	public void canModifyWellFormedUser() throws SaveException, PersistException, ConvertException {
		//Before
		when(dao.getUserByUsername(anyString())).thenReturn(new UserModel());
		UserViewModel viewModel = getWellFormedUserModel();		
		
		//When
		service.modify(viewModel);		
		
		//Then
		verify(dao).save(any(UserModel.class));
	}	
	
	@Test
	public void canThrowExceptionOnSaveExistingUser() throws PersistException, ConvertException {
		//Before
		UserViewModel model = getWellFormedUserModel();
		when(dao.getUserByUsername(model.getUsername())).thenReturn(new UserModel());
		
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
