package test.unit.database;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.spy;

import java.io.File;
import java.util.List;

import model.GeneralAdmissionTicketCategory;
import model.MatchModel;
import model.ReservedTicketCategory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;

import config.ConfigManager.Gender;
import config.ConfigManager.Sports;
import database.XmlModelConverter;
import database.dao.MatchModelDao;
import database.dto.FileAccess;
import exceptions.ConvertException;
import exceptions.PersistException;

public class MatchModelDaoTest {

	private XStream xstream;
	private FileAccess fileAccess;
	private MatchModelDao dao;
	private XmlModelConverter converter;
	
	private final String path = "src/main/java/database/files/";

	@Before
	public void bootStrap() throws PersistException, ConvertException {
		xstream = spy(new XStream());
		fileAccess = FileAccess.getInstance();		
		converter = spy(new XmlModelConverter(xstream));
		dao = spy(new MatchModelDao(converter, fileAccess));
		fillBd();
	}
	
	@After
	public void clean() throws Exception {
		File p = new File(path + "MatchModel.xml");
		 if (p.exists()) {
			p.delete();
	     }
	}
	
	@Test
	public void canGetAllMatchsBySport() throws PersistException, ConvertException {
		//When
		List<MatchModel> models = dao.getAllMatchsBySport(Sports.Rugby);
		
		//Then
		assertTrue(models.size() == 20);
	}
	
	private void fillBd() throws PersistException, ConvertException {
		for(Integer i = 0;i<20;i++) {
			MatchModel model = new MatchModel();
			model.setCity("test");
			model.setField(i.toString());
			model.setGender(Gender.M);
			model.setOpponent("test");
			model.setSport(Sports.Rugby);	
			model.addTicketCategory(new GeneralAdmissionTicketCategory("test", "test", 1,1,1));
			model.addTicketCategory(new ReservedTicketCategory("test", "test", 1,1,1));
			
			fileAccess.save(converter.toElement(model));
		}		
	}
}
