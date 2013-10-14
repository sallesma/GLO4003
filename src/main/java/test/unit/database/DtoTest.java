package test.unit.database;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import model.ModelInterface;
import nu.xom.Element;

import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamConverter;

import converter.XmlObjectConverter;
import database.Dto;

public class DtoTest {
	private XStream xstream;
	private Dto dto;
	
	@Before
	public void bootstrap() {
		xstream = spy(new XStream());
		dto = spy(new Dto(xstream));
		dto.registerModel(TestClass.class);
		dto.registerModel(TestClass2.class);
	}
	
	@Test
	public void canConvertToXml() throws Exception {		
		TestClass test = new TestClass();
		Element elem = dto.toElement(test);
		
		verify(xstream, times(1)).toXML(eq(test));
	}
	
	@Test
	public void canConvertToObject() throws Exception {
		//TODO implement
	}	
	
	private static class TestClass implements ModelInterface {
		long id = 1;
		String test1 = "test1";
		String test2 = "test2";
		
		@XStreamConverter(XmlObjectConverter.class)
		TestClass2 testClass2 = new TestClass2();		
		
		public Long getId() {			
			return id;
		}		
	}
	
	private static class TestClass2 implements ModelInterface {
		long id = 2;
		long four = 4;
		
		public Long getId() {			
			return id;
		}		
	}
}

