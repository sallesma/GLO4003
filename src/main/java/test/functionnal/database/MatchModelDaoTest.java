package test.functionnal.database;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.spy;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.glo4003.project.database.converter.XmlModelConverter;
import com.glo4003.project.database.dto.FileAccess;
import com.glo4003.project.database.exception.ConvertException;
import com.glo4003.project.database.exception.PersistException;
import com.glo4003.project.database.model.GeneralAdmissionTicketCategory;
import com.glo4003.project.database.model.MatchModel;
import com.glo4003.project.database.model.ReservedTicketCategory;
import com.glo4003.project.match.dao.MatchModelDao;
import com.thoughtworks.xstream.XStream;

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
	

	public void clean() throws Exception {
		File p = new File(path + "MatchModel.xml");
		 if (p.exists()) {
			p.delete();
	     }
		 
		 File pp = new File(path + "ReservedTicketCategory.xml");
		 if (pp.exists()) {
			pp.delete();
	     }
		 
		 File ppp = new File(path + "GeneralAdmissionTicketCategory.xml");
		 if (ppp.exists()) {
			ppp.delete();
	     }
	}
	
	@Test
	public void canGetAllMatchsBySport() throws PersistException, ConvertException {
		//When
		List<MatchModel> models = dao.getAllMatchsBySport(MatchModel.Sports.Rugby);
		
		//Then
		assertTrue(models.size() == 20);
	}
	
	private void fillBd() throws PersistException, ConvertException {
		for(Integer i = 0;i<20;i++) {
			MatchModel model = new MatchModel();
			model.setCity("test");
			model.setField(i.toString());
			model.setGender(MatchModel.Gender.M);
			model.setOpponent("test");
			model.setSport(MatchModel.Sports.Rugby);	
			model.setDate(new Date());
			model.addTicketCategory(new GeneralAdmissionTicketCategory("test", "test", 1,1,1));
			model.addTicketCategory(new ReservedTicketCategory("test", "test", 10,1,1));
			
			fileAccess.save(converter.toElement(model));
		}		
	}
}
