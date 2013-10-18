package test.unit.database;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import model.ModelInterface;
import nu.xom.Element;
import nu.xom.ParsingException;
import nu.xom.ValidityException;

import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;

import database.XmlModelConverter;
import database.dao.Dao;
import database.dto.FileAccess;
import exceptions.ConvertException;
import exceptions.PersistException;

public class DaoTest {
	private XStream xstream;
	private FileAccess fileAccess;
	private XmlModelConverter converter;	
	private TestClass testedClass;
	
	@Before
	public void bootStrap() throws PersistException {
		xstream = spy(new XStream());		
		fileAccess = mock(FileAccess.class);
		FileAccess.replace(fileAccess);
		converter = spy(new XmlModelConverter(xstream));		
		testedClass = spy(new TestClass(converter, fileAccess));
		
		configureFileAccess();
	}
	
	public DaoTest() {
		xstream = spy(new XStream());
		fileAccess = mock(FileAccess.class);
		converter = spy(new XmlModelConverter(xstream));
		
		testedClass = spy(new TestClass(converter, fileAccess));
	}
	
	@Test
	public void canSave() throws PersistException, ConvertException, ValidityException, IOException, ParsingException {
		testedClass.save(new TestClass2());
		when(converter.toElement(any(TestClass2.class))).thenReturn(null);
		verify(fileAccess, times(1)).save(any(Element.class), anyString());
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
	
	private void configureFileAccess() throws PersistException {
		when(fileAccess.getNewId(anyString())).thenReturn(1L);
	}	
}
