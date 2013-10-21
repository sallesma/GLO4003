package test.functionnal.database;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.ModelInterface;
import nu.xom.Element;

import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import config.ConfigManager.Gender;
import converter.XmlObjectConverter;
import database.XmlModelConverter;
import database.dto.FileAccess;
import exceptions.ConvertException;
import exceptions.PersistException;

public class XmlModelConverterTest {

	private XmlModelConverter converter;
	
	@Before
	public void bootstrap() throws PersistException {
		converter = new XmlModelConverter();
		
		converter.registerModel(TestClass.class);
		converter.registerModel(TestClass2.class);
		converter.registerModel(TestClass4.class);
		converter.registerModel(TestClass3.class);				
	}
	
	@Test
	public void canConvertToObject() throws ConvertException, PersistException {
		//Before
		TestClass4 testModel = new TestClass4();
		Element elem = converter.toElement(testModel);
		
		//When
		TestClass4 result = (TestClass4) converter.toObject(elem);
		
		//Then
		assertThat(result.date, is(testModel.date));		
		assertThat(result.four, is(testModel.four));
		assertThat(result.gender, is(testModel.gender));
	}
	
	@Test
	public void canConvertToElementWithRightName() throws Exception {
		//Before
		TestClass test = new TestClass();	
		
		//When
		Element elem = converter.toElement(test);
		
		//Then
		assertThat(elem.getLocalName(), is("TestClass"));			
	}
	
	@Test
	public void canConvertToElementRightchilds() throws Exception {
		//Before
		TestClass test = new TestClass();
		
		//When
		Element elem = converter.toElement(test);
		
		//Then
		assertThat(elem.getFirstChildElement("test1").getValue(), is("test1"));		
		assertThat(elem.getFirstChildElement("test1").getValue(), is("test1"));
		assertThat(elem.getFirstChildElement("id").getValue(), is("1"));
		assertThat(elem.getFirstChildElement("test2").getValue(), is("test2"));
		assertThat(elem.getFirstChildElement("testClass2").getValue(), is("1"));		
	}
	
	@Test
	public void canConvertWithListToXml() throws Exception {
		//Before
		TestClass3 test = new TestClass3();
		
		//When
		Element elem = converter.toElement(test);
		
		//Then
		assertThat(elem.getFirstChildElement("test1").getValue(), is("test1"));		
		assertThat(elem.getFirstChildElement("test2").getValue(), is("test2"));		
		assertThat(elem.getFirstChildElement("id").getValue(), is("1"));		
		assertThat(elem.getChildElements("TestClass4").size(), is(2));		
	}
	
	@Test
	public void canConvertWithListToObject() throws Exception {
		//Before
		TestClass3 test = new TestClass3();
		
		//When
		Element elem = converter.toElement(test);
		
		//Then
		assertThat(elem.getFirstChildElement("test1").getValue(), is("test1"));		
		assertThat(elem.getFirstChildElement("test2").getValue(), is("test2"));		
		assertThat(elem.getFirstChildElement("id").getValue(), is("1"));		
		assertThat(elem.getChildElements("TestClass4").size(), is(2));		
	}
	
	private static class TestClass3 implements ModelInterface {		
		Long id = 0L;
		String test1 = "test1";
		String test2 = "test2";
		
		public TestClass3() {
			testClass4List = new ArrayList<TestClass4>();
			testClass4List.add(new TestClass4());
			testClass4List.add(new TestClass4());
		}
		
		@XStreamImplicit(itemFieldName="TestClass4")
		@XStreamConverter(XmlObjectConverter.class)
		List<TestClass4> testClass4List;
		
		public Long getId() {			
			return id;
		}

		@Override
		public void setId(Long id) {
			this.id = id;
			
		}		
	}
	
	private static class TestClass4 implements ModelInterface {
		Long id = 0L;
		long four = 4;
		Date date = new Date();
		Gender gender = Gender.F;
				
		public Long getId() {			
			return id;
		}	
		
		@Override
		public void setId(Long id) {
			this.id = id;
			
		}	
	}
	
	public static class TestClass implements ModelInterface {
		Long id = 0L;
		String test1 = "test1";
		String test2 = "test2";
		Date date = new Date();
		Gender gender = Gender.F;
		
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
	
	public static class TestClass2 implements ModelInterface {
		long id = 0L;
		long four = 4;
		
		public Long getId() {			
			return id;
		}
		
		public void setId(Long id) {
			this.id = id;
			
		}	
	}
}
