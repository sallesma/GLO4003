package test.unit.model;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

import org.junit.Before;
import org.junit.Test;

import service.UserService;
import database.dao.UserModelDao;
import exceptions.ConvertException;
import exceptions.PersistException;
import model.InstantiateTicketModel;
import model.MatchModel;

public class InstantiateTicketModelTest {
	
	private InstantiateTicketModel instantiateTicket;
	
	@Before
	public void initialize() throws PersistException, ConvertException {
		instantiateTicket = spy(new InstantiateTicketModel(new MatchModel(), 0, "0", 1));
	}
	
//	@Test
	
}
