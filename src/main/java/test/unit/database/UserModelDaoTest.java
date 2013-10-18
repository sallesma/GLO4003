package test.unit.database;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.io.File;

import model.UserModel;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import test.unit.database.DaoTest.TestClass;

import com.thoughtworks.xstream.XStream;

import database.XmlModelConverter;
import database.dao.UserModelDao;
import database.dto.FileAccess;
import exceptions.ConvertException;
import exceptions.PersistException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

public class UserModelDaoTest {

	private TestClass testedClass;
	private XStream xstream;
	private FileAccess fileAccess;
	private UserModelDao dao;
	private XmlModelConverter converter;

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
		File p = new File("UserModel.xml");
		 if (p.exists()) {
				p.delete();
	     }
	}
	
	@Test
	public void canGetUserByUsername() {
		
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
			String elem = converter.toElement(model).toXML();
			fileAccess.save(converter.toElement(model));
		}		
	}
}
