package test.unit.database;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import nu.xom.Element;

import org.junit.Before;
import org.junit.Test;

import com.glo4003.project.database.converter.XmlModelConverter;
import com.glo4003.project.database.dto.UserDto;
import com.glo4003.project.database.exception.ConvertException;
import com.glo4003.project.database.exception.PersistException;
import com.glo4003.project.database.filesaccess.FileAccess;
import com.glo4003.project.user.dao.UserModelDao;

public class UserModelDaoTest {

	private FileAccess fileAccess;
	private XmlModelConverter converter;	
	private UserModelDao dao;
	
	@Before
	public void bootStrap() throws PersistException {				
		fileAccess = mock(FileAccess.class);
		FileAccess.replace(fileAccess);
		converter = mock(XmlModelConverter.class);		
		dao = new UserModelDao(converter, fileAccess);		
		
		configureFileAccess();
	}
	
	@Test
	public void canGetUserByUsername() throws PersistException, ConvertException {
		
		List<Element> models = getModels();
		when(fileAccess.getNewId(anyString())).thenReturn(1L,2L,3L,4L,5L,6L,7L,8L,9L,10L,11L);
		when(fileAccess.getAll(anyString())).thenReturn(models);
		when(converter.toObject(any(Element.class))).thenReturn(getModel());
	
		dao.getUserByUsername("test");
	
		verify(converter, times(10)).toObject(any(Element.class));
	}
	
	@Test
	public void canReturnTrueIfLoginValid() throws PersistException, ConvertException {
	
		when(fileAccess.getAll(anyString())).thenReturn(getModels());
		when(converter.toObject(any(Element.class))).thenReturn(getModel());
	
		Boolean result = dao.isLoginValid("test 3", "password");
		
		assertTrue(result);
	}
	
	@Test
	public void canReturnFalseIfLoginInvalid() throws PersistException, ConvertException {
		
		when(fileAccess.getAll(anyString())).thenReturn(getModels());
		when(converter.toObject(any(Element.class))).thenReturn(getModel());
		
		Boolean result = dao.isLoginValid("userr 3", "password");
	
		assertFalse(result);
	}
	
	private List<Element> getModels() {
		List<Element> models = new ArrayList<Element>(20);
		for(Integer i = 0;i<10;i++) {
			Element elem = mock(Element.class);			
			models.add(elem);
		}		
		
		return models;
	}
	
	private UserDto getModel() {
		UserDto model = new UserDto();
		model.setAddress("address");
		model.setFirstName("firstname");
		model.setIsAdmin(false);
		model.setLastName("lastname");
		model.setPassword("password");
		model.setPhoneNumber("test");
		model.setUsername("test 3");
		
		return model;
	}
	
	private void configureFileAccess() throws PersistException {
		when(fileAccess.getNewId(anyString())).thenReturn(1L);
	}	
}
