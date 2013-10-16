package test.unit.database;

import java.io.IOException;

import nu.xom.Element;
import nu.xom.ParsingException;
import nu.xom.ValidityException;

import org.junit.Before;
import org.junit.Test;

import model.ModelInterface;
import model.UserModel;
import test.functionnal.back.database.XmlModelConverterTest.TestClass2;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamConverter;

import converter.XmlObjectConverter;
import database.XmlModelConverter;
import database.dao.Dao;
import database.dto.FileAccess;
import exceptions.ConvertException;
import exceptions.PersistException;
import static org.mockito.Mockito.*;

public class DaoTest {
	private XStream xstream;
	private FileAccess fileHelper;
	private XmlModelConverter converter;
	
	private TestClass testedClass;
	
	@Before
	public void bootStrap() {
		xstream = spy(new XStream());
		fileHelper = mock(FileAccess.class);
		converter = spy(new XmlModelConverter(xstream));
		
		testedClass = spy(new TestClass(converter, fileHelper));
	}
	
	public DaoTest() {
		xstream = spy(new XStream());
		fileHelper = mock(FileAccess.class);
		converter = spy(new XmlModelConverter(xstream));
		
		testedClass = spy(new TestClass(converter, fileHelper));
	}
	
	@Test
	public void canSave() throws PersistException, ConvertException, ValidityException, IOException, ParsingException {
		testedClass.save(new TestClass2());
		when(converter.toElement(any(TestClass2.class))).thenReturn(null);
		verify(fileHelper, times(1)).save(any(Element.class), anyString());
	}
	
	public static class TestClass extends Dao<TestClass2> {
		public TestClass(XmlModelConverter dto, FileAccess fileAccess) {
			super(TestClass2.class, dto, fileAccess);	
		}

		public TestClass() {
			super(TestClass2.class);		
		}	
	}
	
	public static class TestClass2 implements ModelInterface {
		long id = 2;
		long four = 4;
		
		public Long getId() {			
			return id;
		}
		
		@Override
		public void setId(Long id) {
			this.id = id;
		}	
	}
	
}
