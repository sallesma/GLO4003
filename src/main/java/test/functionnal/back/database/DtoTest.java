package test.functionnal.back.database;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import model.ModelInterface;
import nu.xom.Element;

import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import converter.XmlObjectConverter;
import database.Dto;

public class DtoTest {
	
	private Dto dto = new Dto();
	
	@Before
	public void bootstrap() {
		dto.registerModel(TestClass.class);
		dto.registerModel(TestClass2.class);
	}
	
	@Test
	public void canConvertToElementWithRightName() throws Exception {
		TestClass test = new TestClass();
		Element elem = dto.toElement(test);
		
		assertThat(elem.getLocalName(), is("TestClass"));			
	}
	
	@Test
	public void canConvertToElementRightchilds() throws Exception {
		TestClass test = new TestClass();
		Element elem = dto.toElement(test);
		
		assertThat(elem.getFirstChildElement("test1").getValue(), is("test1"));		
		assertThat(elem.getFirstChildElement("test1").getValue(), is("test1"));
		assertThat(elem.getFirstChildElement("id").getValue(), is("1"));
		assertThat(elem.getFirstChildElement("test2").getValue(), is("test2"));
		assertThat(elem.getFirstChildElement("testClass2").getValue(), is("2"));		
	}
	
	@Test
	public void canConvertToObject() throws Exception {
		TestClass test = new TestClass();
		Element elem = dto.toElement(test);
		
		TestClass myClass = (TestClass) dto.toObject(elem);
		
		assertThat(myClass.test1, is("test1"));		
		assertThat(myClass.test2, is("test2"));
		assertThat(myClass.id, is(1L));
		assertThat(myClass.testClass2.four, is(4L));
		assertThat(myClass.testClass2.id, is(2L));		
	}
	
	@Test
	public void test() throws Exception {
		TestClass3 test = new TestClass3();
		Element elem = dto.toElement(test);
		@SuppressWarnings("unused")
		String result = elem.toXML();
		String ee = "";
	}
	
	private static class TestClass3 implements ModelInterface {
		long id = 1;
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
	}
	
	private static class TestClass4 implements ModelInterface {
		long id = 2;
		long four = 4;
		
		public Long getId() {			
			return id;
		}		
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
