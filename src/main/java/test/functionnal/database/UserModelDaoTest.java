package test.functionnal.database;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.spy;

import java.io.File;

import model.UserModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import test.unit.database.DaoTest.TestClass;

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
	
	private final String path = "src/main/java/database/files/";

	@Before
	public void bootStrap() throws PersistException, ConvertException {
		xstream = spy(new XStream());
		fileAccess = FileAccess.getInstance();		
		converter = spy(new XmlModelConverter(xstream));
		dao = spy(new UserModelDao(converter, fileAccess));
		fillBd();
	}
	
	@After
	public void clean() throws Exception {
		File p = new File(path + "UserModel.xml");
		 if (p.exists()) {
				p.delete();
	     }
	}
	
	@Test
	public void canGetUserByUsername() throws PersistException {
		//When
		UserModel model = dao.getUserByUsername("test 1");
		
		//Then
		assertTrue(model.getFirstName().contentEquals("1"));
	}
	
	@Test
	public void canReturnTrueIfLoginValid() throws PersistException {
		//When
		Boolean result = dao.isLoginValid("test 3", "password");
		
		//Then
		assertTrue(result);
	}
	
	@Test
	public void canReturnFalseIfLoginInvalid() throws PersistException {
		//When
		Boolean result = dao.isLoginValid("userr 3", "password");
		
		//Then
		assertFalse(result);
	}
	
	private void fillBd() throws PersistException, ConvertException {
		for(Integer i = 0;i<20;i++) {
			UserModel model = new UserModel();
			model.setAddress(i.toString());
			model.setFirstName(i.toString());
			model.setIsAdmin(false);
			model.setLastName(i.toString());
			model.setPassword("password");
			model.setPhoneNumber("test");
			model.setUsername("test " + i);
			
			fileAccess.save(converter.toElement(model));
		}		
	}
}