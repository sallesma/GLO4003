package test.functionnal.database;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import nu.xom.Element;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.glo4003.project.database.dto.MatchDto;
import com.glo4003.project.database.exception.PersistException;
import com.glo4003.project.database.filesaccess.FileAccess;

public class FileAccessTest {
	
	private static FileAccess fa;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		fa = FileAccess.getInstance();
	}
	
	@AfterClass
	public static void clean() throws Exception {
		File p = new File("match.xml");
		 if (p.exists()) {
				p.delete();
	     }
	}
	
	@Before
	public void setUp() throws Exception {
        File p = new File("match.xml");

        if (p.exists()) {
			p.delete();
        }
	}
		
	@Test
	public void testGetByid() {
		File p = new File("match.xml");
		FileWriter write;
		try {
			p.createNewFile();
			write = new FileWriter(p);
			write.write("<Matchs><" + MatchDto.class.getSimpleName() + "><id>1</id></" + MatchDto.class.getSimpleName() + "><" + MatchDto.class.getSimpleName() + "><id>2</id></" + MatchDto.class.getSimpleName() + "></Matchs>");
	        write.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		Element elem = new Element(MatchDto.class.getSimpleName());
		Element elemid = new Element("id");
		elem.appendChild(elemid);
		elemid.appendChild("1");
		
		
		try {
			Element result = fa.getByID(1L, MatchDto.class.getSimpleName());
			assertThat(result.toString(), is(elem.toString()));
			
		} catch (PersistException e) {
			e.printStackTrace();
		}
	}

	
	@Test
	public void testSaveElementString() {
		
		
		Element elem = new Element(MatchDto.class.getSimpleName());
		Element elemid = new Element("id");
		Element elemtest = new Element("test");
		elem.appendChild(elemid);
		elem.appendChild(elemtest);
		elemid.appendChild("1");
		elemtest.appendChild("1");
		
		try {
			fa.save(elem, MatchDto.class.getSimpleName());
			Element result = fa.getByID(1L, MatchDto.class.getSimpleName());
			
			assertThat(result.toString(), is(elem.toString()));
		} catch (PersistException e) {
			e.printStackTrace();
		}
		
		Element elem2 = new Element(MatchDto.class.getSimpleName());
		Element elemid2 = new Element("id");
		Element elemtest2 = new Element("test");
		elem2.appendChild(elemid2);
		elem2.appendChild(elemtest2);
		elemid2.appendChild("1");
		elemtest2.appendChild("2");
		
		try {
			fa.save(elem2, MatchDto.class.getSimpleName());
			Element result = fa.getByID(1L, MatchDto.class.getSimpleName());
			
			assertThat(result.toString(), is(elem2.toString()));
		} catch (PersistException e) {
			e.printStackTrace();
		}
		
		
	}

	@Test
	public void testGetAll() {
		
		
		Element elem = new Element(MatchDto.class.getSimpleName());
		Element elemid = new Element("id");
		elem.appendChild(elemid);
		elemid.appendChild("1");
		
		try {
			fa.save(elem, MatchDto.class.getSimpleName());
		} catch (PersistException e) {
			e.printStackTrace();
		}
		
		Element elem2 = new Element(MatchDto.class.getSimpleName());
		Element elemid2 = new Element("id");
		elem2.appendChild(elemid2);
		elemid2.appendChild("2");
		
		try {
			fa.save(elem2, MatchDto.class.getSimpleName());
		} catch (PersistException e) {
			e.printStackTrace();
		}
		
		List<Element> expected = new ArrayList<Element>(2);
		try {
			expected.add(fa.getByID(1L, MatchDto.class.getSimpleName()));
			expected.add(fa.getByID(2L, MatchDto.class.getSimpleName()));
			List<Element> result = fa.getAll(MatchDto.class.getSimpleName());
			
			assertThat(result.size(), is(expected.size()));
			
			int i = 0;
			while (i < result.size())
			{
				assertThat(result.get(i).toXML(), is(expected.get(i).toXML()));
				i++;
			}
			
		} catch (PersistException e) {
			e.printStackTrace();
		}
		
	}


	@Test
	public void testDeleteLongString() {
		
		
		Element elem = new Element(MatchDto.class.getSimpleName());
		Element elemid = new Element("id");
		elem.appendChild(elemid);
		elemid.appendChild("1");
		
		try {
			fa.save(elem, MatchDto.class.getSimpleName());
		} catch (PersistException e) {
			e.printStackTrace();
		}
		
		Element elem2 = new Element(MatchDto.class.getSimpleName());
		Element elemid2 = new Element("id");
		elem2.appendChild(elemid2);
		elemid2.appendChild("2");
		
		try {
			fa.save(elem2, MatchDto.class.getSimpleName());
		} catch (PersistException e) {
			e.printStackTrace();
		}
		
		Element elem3 = new Element(MatchDto.class.getSimpleName());
		Element elemid3 = new Element("id");
		elem3.appendChild(elemid3);
		elemid3.appendChild("3");
		
		try {
			fa.save(elem3, MatchDto.class.getSimpleName());
		} catch (PersistException e) {
			e.printStackTrace();
		}
		
		List<Element> expected = new ArrayList<Element>(2);
		try {
			expected.add(fa.getByID(1L, MatchDto.class.getSimpleName()));
			expected.add(fa.getByID(3L, MatchDto.class.getSimpleName()));
			
			fa.delete(2L, MatchDto.class.getSimpleName());
			
			List<Element> result = fa.getAll(MatchDto.class.getSimpleName());
			
			assertThat(result.size(), is(expected.size()));
			
			int i = 0;
			while (i < result.size())
			{
				assertThat(result.get(i).toXML(), is(expected.get(i).toXML()));
				i++;
			}
			
		} catch (PersistException e) {
			e.printStackTrace();
		}
	}

	

	@Test
	public void testGetNewId() {

		Element elem = new Element(MatchDto.class.getSimpleName());
		Element elemid = new Element("id");
		elem.appendChild(elemid);
		elemid.appendChild("0");
		
		try {
			fa.save(elem, MatchDto.class.getSimpleName());
		} catch (PersistException e) {
			e.printStackTrace();
		}
		
		
		Element elem2 = new Element(MatchDto.class.getSimpleName());
		Element elemid2 = new Element("id");
		elem2.appendChild(elemid2);
		elemid2.appendChild("1");
		
		try {
			fa.save(elem2, MatchDto.class.getSimpleName());
		} catch (PersistException e) {
			e.printStackTrace();
		}
		
		
		Element elem3 = new Element(MatchDto.class.getSimpleName());
		Element elemid3 = new Element("id");
		elem3.appendChild(elemid3);
		elemid3.appendChild("3");
		
		try {
			fa.save(elem3, MatchDto.class.getSimpleName());
		} catch (PersistException e) {
			e.printStackTrace();
		}
		
		
		try {
			Long result = fa.getNewId(MatchDto.class.getSimpleName());
			assertThat(result, is(2L));
		} catch (PersistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	//*/
}