package test.unit.database;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import nu.xom.Element;

import org.junit.Before;
import org.junit.Test;

import com.glo4003.project.database.converter.XmlModelConverter;
import com.glo4003.project.database.converter.XmlObjectConverter;
import com.glo4003.project.database.filesaccess.FileAccess;
import com.glo4003.project.global.ModelInterface;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamConverter;

public class XmlModelConverterTest {
	private XStream xstream;
	private XmlModelConverter converter;
	private FileAccess fileAccess;
	
	@Before
	public void bootstrap() {
		xstream = spy(new XStream());
		converter = new XmlModelConverter(xstream);
		
		fileAccess = mock(FileAccess.class);
		FileAccess.replace(fileAccess);
		
		converter.registerModel(TestClass.class);
		converter.registerModel(TestClass2.class);
	}
	
	@Test
	public void canConvertToXml() throws Exception {		
		
		TestClass test = new TestClass();
		when(xstream.toXML(any(ModelInterface.class))).thenReturn("<Model></Model>");
		
		converter.toElement(test);		
	
		verify(xstream, times(1)).toXML(eq(test));
	}
	
	@Test
	public void canConvertToObject() throws Exception {		
							
		Element xml = converter.toElement(new TestClass2());	
	
		converter.toObject(xml);		
				
		verify(xstream, times(1)).fromXML(eq(xml.toXML()));		
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

