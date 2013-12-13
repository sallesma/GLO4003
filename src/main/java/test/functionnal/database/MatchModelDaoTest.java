package test.functionnal.database;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.spy;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.glo4003.project.database.converter.XmlModelConverter;
import com.glo4003.project.database.dto.GeneralAdmissionTicketCategoryDto;
import com.glo4003.project.database.dto.MatchDto;
import com.glo4003.project.database.dto.ReservedTicketCategoryDto;
import com.glo4003.project.database.exception.ConvertException;
import com.glo4003.project.database.exception.PersistException;
import com.glo4003.project.database.filesaccess.FileAccess;
import com.glo4003.project.match.dao.MatchModelDao;
import com.glo4003.project.match.helper.MatchConverter;
import com.glo4003.project.match.model.MatchConcreteModel;
import com.thoughtworks.xstream.XStream;

public class MatchModelDaoTest {

	private XStream xstream;
	private FileAccess fileAccess;
	private MatchModelDao dao;
	private XmlModelConverter converter;
	private MatchConverter mConverter;
	
	private final String path = "src/main/java/database/files/";

	@Before
	public void bootStrap() throws PersistException, ConvertException {
		xstream = spy(new XStream());
		fileAccess = FileAccess.getInstance();		
		converter = spy(new XmlModelConverter(xstream));
		mConverter = spy(new MatchConverter());
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
		List<MatchDto> models = dao.getAllMatchsBySport(MatchDto.Sports.Rugby);
		
		//Then
		assertTrue(models.size() == 20);
	}
	
	private void fillBd() throws PersistException, ConvertException {
		for(Integer i = 0;i<20;i++) {
			MatchDto model = new MatchDto();
			MatchConcreteModel concreteModel = new MatchConcreteModel();
			concreteModel.setCity("test");
			concreteModel.setField(i.toString());
			concreteModel.setGender(MatchDto.Gender.M);
			concreteModel.setOpponent("test");
			concreteModel.setSport(MatchDto.Sports.Rugby);	
			concreteModel.setDate(new Date());
			concreteModel.addTicketCategory(new GeneralAdmissionTicketCategoryDto("test", "test", 1,1,1));
			concreteModel.addTicketCategory(new ReservedTicketCategoryDto("test", "test", 10,1,1));
			
			model = mConverter.convertToDB(concreteModel);
					
			fileAccess.save(converter.toElement(model));
		}		
	}
}
