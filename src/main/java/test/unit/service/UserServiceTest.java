package test.unit.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Validator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.glo4003.project.database.dto.UserDto;
import com.glo4003.project.database.exception.ConvertException;
import com.glo4003.project.database.exception.PersistException;
import com.glo4003.project.database.exception.SaveException;
import com.glo4003.project.global.ModelValidator;
import com.glo4003.project.injection.Resolver;
import com.glo4003.project.user.dao.UserModelDao;
import com.glo4003.project.user.helper.UserConverter;
import com.glo4003.project.user.model.view.UserViewModel;
import com.glo4003.project.user.service.UserService;

public class UserServiceTest {

	@Mock private ModelValidator validator ;
	@Mock private UserConverter converter;
	@Mock private UserModelDao dao;

	@InjectMocks private UserService service;
	
	@Before
	public void initialize() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void dontThrowExceptionOnSaveWellFormedUser() throws SaveException, PersistException, ConvertException {
	
		UserViewModel viewModel = getWellFormedUserModel();
			
		service.saveNew(viewModel);	
	}
	
	@Test
	public void throwExceptionOnNotWellFormedUser() throws PersistException, ConvertException {
	
		UserViewModel viewModel = getNotWellFormedUserModel();
		List<String> errors = new ArrayList<String>(1);
		errors.add("erreur");
		when(validator.validate(any(UserViewModel.class))).thenReturn(errors);
		
		SaveException ex = null;
		try {
			service.saveNew(viewModel);	
		} catch (SaveException e) {
			ex = e;
		}
		
		assertTrue(ex != null);
	}
	
	@Test
	public void canSaveWellFormedUser() throws SaveException, PersistException, ConvertException {
		
		UserViewModel viewModel = getWellFormedUserModel();	
		when(validator.validate(any(UserViewModel.class))).thenReturn(new ArrayList<String>());
		
	    service.saveNew(viewModel);		
		
		verify(dao).save(any(UserDto.class));
	}
	
	@Test
	public void dontSaveNotWellFormedUser() throws SaveException, PersistException, ConvertException {
		
		UserViewModel viewModel = getNotWellFormedUserModel();
		List<String> errors = new ArrayList<String>(1);
		errors.add("erreur");
		when(validator.validate(any(UserViewModel.class))).thenReturn(errors);
		
		try {
			
			service.saveNew(viewModel);	
		} catch(SaveException e) {			
		}
		
		verify(dao, never()).save(any(UserDto.class));
	}
	
	@Test
	public void canThrowExceptionOnExistingUser() throws PersistException, ConvertException {
		
		UserViewModel model = getWellFormedUserModel();
		Mockito.when(dao.getUserByUsername(model.getUsername())).thenReturn(new UserDto());
		
		Boolean exceptionFound = false;
		try {
			
			service.saveNew(model);	
		} catch(SaveException e) {	
			exceptionFound = true;
		}
		
		assertTrue(exceptionFound);
		verify(dao, never()).save(any(UserDto.class));
	}
	
	@Test
	public void dontThrowExceptionOnModifyWellFormedUser() throws SaveException, PersistException, ConvertException {
		
		UserViewModel viewModel = getWellFormedUserModel();
		when(validator.validate(any(UserViewModel.class))).thenReturn(new ArrayList<String>());
		when(dao.getUserByUsername(anyString())).thenReturn(mock(UserDto.class));
		
		service.modify(viewModel);	
	}
	
	@Test
	public void throwExceptionOnModifyNotWellFormedUser() throws PersistException, ConvertException {
		
		UserViewModel viewModel = getNotWellFormedUserModel();
		List<String> errors = new ArrayList<String>(1);
		errors.add("erreur");
		when(validator.validate(any(UserViewModel.class))).thenReturn(errors);
		
		SaveException ex = null;
		try {
		
			service.modify(viewModel);	
		} catch (SaveException e) {
			ex = e;
		}
		
		
		assertTrue(ex != null);
	}
	
	@Test
	public void canModifyWellFormedUser() throws SaveException, PersistException, ConvertException {
		
		UserViewModel viewModel = getWellFormedUserModel();	
		when(validator.validate(any(UserViewModel.class))).thenReturn(new ArrayList<String>());
		when(dao.getUserByUsername(anyString())).thenReturn(mock(UserDto.class));
		
		service.modify(viewModel);		
		
		verify(dao).save(any(UserDto.class));
	}	
	
	@Test
	public void dontModifyNotWellFormedUser() throws SaveException, PersistException, ConvertException {
		
		UserViewModel viewModel = getNotWellFormedUserModel();		
		
		try {
			
			service.modify(viewModel);	
		} catch(SaveException e) {			
		}
	
		verify(dao, never()).save(any(UserDto.class));
	}
	
	
	
	@Test
	public void canThrowExceptionOnNotExistingUser() throws PersistException, ConvertException {
		
		UserViewModel model = getWellFormedUserModel();
		Mockito.when(dao.getUserByUsername(model.getUsername())).thenReturn(null);
		
		Boolean exceptionFound = false;
		try {
			
			service.modify(model);	
		} catch(SaveException e) {	
			exceptionFound = true ;
		}
		
		assertTrue(exceptionFound);
		verify(dao, never()).save(any(UserDto.class));
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
	
	private UserDto getWellFormedUserModelDifferentUsername() {
		UserDto model = new UserDto();
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


