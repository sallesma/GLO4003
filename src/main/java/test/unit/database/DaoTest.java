package test.unit.database;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import nu.xom.Element;
import nu.xom.ParsingException;
import nu.xom.ValidityException;

import org.junit.Before;
import org.junit.Test;

import com.glo4003.project.database.converter.XmlModelConverter;
import com.glo4003.project.database.dao.Dao;
import com.glo4003.project.database.exception.ConvertException;
import com.glo4003.project.database.exception.PersistException;
import com.glo4003.project.database.filesaccess.FileAccess;
import com.glo4003.project.global.ModelInterface;

public class DaoTest {
	private FileAccess fileAccess;
	private XmlModelConverter converter;	
	private TestClass testedClass;
	
	@Before
	public void bootStrap() throws PersistException {				
		fileAccess = mock(FileAccess.class);
		FileAccess.replace(fileAccess);
		converter = mock(XmlModelConverter.class);		
		testedClass = new TestClass(converter, fileAccess);		
		
		configureFileAccess();
	}
	
	@Test
	public void canSave() throws PersistException, ConvertException, ValidityException, IOException, ParsingException {
		//When
		testedClass.save(new TestClass2());
		
		//Then
		verify(fileAccess, times(1)).save(any(Element.class), anyString());
	}
	
	@Test
	public void canGetById() throws PersistException {		
		//Before
		when(converter.toObject(any(Element.class))).thenReturn(new TestClass2());
		
		//When
		testedClass.getById(1L);
				
		//Then
		verify(fileAccess, times(1)).getByID(1L, "TestClass2");
	}
	
	@Test
	public void canDelete() throws PersistException, ConvertException {		
		//Before
		when(converter.toElement(any(TestClass2.class))).thenReturn(mock(Element.class));
		
		//When
		testedClass.delete(new TestClass2());
				
		//Then
		verify(fileAccess, times(1)).delete(any(Element.class));
	}
	
	@Test
	public void canDeleteById() throws PersistException, ConvertException {		
		
		//When
		testedClass.deleteById(2L);
				
		//Then
		verify(fileAccess, times(1)).delete(2L, "TestClass2");
	}
	
	@Test
	public void canGetAll() throws PersistException, ConvertException {		
		//Before
		List<Element> list = new ArrayList<Element>();
		list.add(mock(Element.class));
		list.add(mock(Element.class));
		when(converter.toObject(any(Element.class))).thenReturn(new TestClass2());
		when(fileAccess.getAll(anyString())).thenReturn(list);
		
		//When
		testedClass.getAll();
				
		//Then
		verify(fileAccess, times(1)).getAll(anyString());
		verify(converter, times(2)).toObject(any(Element.class));
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
