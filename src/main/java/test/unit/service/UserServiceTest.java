package test.unit.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

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
	
	private UserModelDao dao;
	
	private UserService service;
	
	@Before
	public void initialize() {
		service = Resolver.getInjectedInstance(UserService.class);
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
		verify(dao).save(any(UserDto.class));
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
		verify(dao, never()).save(any(UserDto.class));
	}
	
	@Test
	public void canThrowExceptionOnExistingUser() throws PersistException, ConvertException {
		//Before
		UserViewModel model = getWellFormedUserModel();
		Mockito.when(dao.getUserByUsername(model.getUsername())).thenReturn(new UserDto());
		
		Boolean exceptionFound = false;
		try {
			//When
			service.saveNew(model);	
		} catch(SaveException e) {	
			exceptionFound = true;
		}
		
		//Then
		assertTrue(exceptionFound);
		verify(dao, never()).save(any(UserDto.class));
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
		verify(dao).save(any(UserDto.class));
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
		verify(dao, never()).save(any(UserDto.class));
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
