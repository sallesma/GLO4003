package test.unit.database;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import model.UserModel;
import nu.xom.Element;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;

import database.XmlModelConverter;
import database.dao.UserModelDao;
import database.dto.FileAccess;
import exceptions.ConvertException;
import exceptions.PersistException;

public class UserModelDaoTest {

	private XStream xstream;
	private FileAccess fileAccess;
	private UserModelDao dao;
	private XmlModelConverter converter;

	@Before
	public void bootStrap() throws PersistException, ConvertException {
		xstream = spy(new XStream());
		fileAccess = mock(FileAccess.class);	
		FileAccess.replace(fileAccess);
		converter = spy(new XmlModelConverter(xstream));
		dao = spy(new UserModelDao(converter, fileAccess));		
	}
	
	@Test
	public void canGetUserByUsername() throws PersistException, ConvertException {
		//Before
		when(fileAccess.getAll(anyString())).thenReturn(getModels());
		
		//When
		UserModel model = dao.getUserByUsername("test 1");
		
		//Then
		assertTrue(model.getFirstName().contentEquals("1"));
	}
	
	@Test
	public void canReturnTrueIfLoginValid() throws PersistException, ConvertException {
		//Before
		when(fileAccess.getAll(anyString())).thenReturn(getModels());
		
		//When
		Boolean result = dao.isLoginValid("test 3", "password");
		
		//Then
		assertTrue(result);
	}
	
	@Test
	public void canReturnFalseIfLoginInvalid() throws PersistException, ConvertException {
		//Before
		when(fileAccess.getAll(anyString())).thenReturn(getModels());
				
		//When
		Boolean result = dao.isLoginValid("userr 3", "password");
		
		//Then
		assertFalse(result);
	}
	
	private List<Element> getModels() throws PersistException, ConvertException {
		List<Element> models = new ArrayList<Element>(20);
		for(Integer i = 0;i<20;i++) {
			UserModel model = new UserModel();
			model.setAddress(i.toString());
			model.setFirstName(i.toString());
			model.setIsAdmin(false);
			model.setLastName(i.toString());
			model.setPassword("password");
			model.setPhoneNumber("test");
			model.setUsername("test " + i);
			
			models.add(converter.toElement(model));
		}		
		
		return models;
	}
}
