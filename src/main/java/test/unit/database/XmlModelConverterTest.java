package test.unit.database;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import model.ModelInterface;
import nu.xom.Element;

import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamConverter;

import converter.XmlObjectConverter;
import database.XmlModelConverter;

public class XmlModelConverterTest {
	private XStream xstream;
	private XmlModelConverter converter;
	
	@Before
	public void bootstrap() {
		xstream = spy(new XStream());
		converter = spy(new XmlModelConverter(xstream));
		
		converter.registerModel(TestClass.class);
		converter.registerModel(TestClass2.class);
	}
	
	@Test
	public void canConvertToXml() throws Exception {		
		TestClass test = new TestClass();
		converter.toElement(test);
		
		verify(xstream, times(1)).toXML(eq(test));
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

		@Override
		public void setId(Long id) {
			this.id = id;
		}		
	}
	
	private static class TestClass2 implements ModelInterface {
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

