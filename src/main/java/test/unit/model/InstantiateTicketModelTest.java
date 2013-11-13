package test.unit.model;

import static org.mockito.Mockito.spy;
import model.InstantiateTicketModel;
import model.MatchModel;

import org.junit.Before;

import exceptions.ConvertException;
import exceptions.PersistException;

public class InstantiateTicketModelTest {
	
	private InstantiateTicketModel instantiateTicket;
	
	@Before
	public void initialize() throws PersistException, ConvertException {
		instantiateTicket = spy(new InstantiateTicketModel(new MatchModel(), 0, "0", 1));
	}
	
//	@Test
	
}
