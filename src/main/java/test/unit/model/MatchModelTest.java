package test.unit.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.glo4003.project.database.dto.AbstractTicketCategory;
import com.glo4003.project.database.dto.MatchDto;
import com.glo4003.project.database.dto.UserDto;
import com.glo4003.project.database.dto.MatchDto.Gender;
import com.glo4003.project.database.dto.MatchDto.Sports;
import com.glo4003.project.database.exception.ConvertException;
import com.glo4003.project.database.exception.PersistException;
import com.glo4003.project.match.model.MatchConcreteModel;
import com.glo4003.project.ticket.category.factory.TicketCategoryFactory;
import com.glo4003.project.ticket.model.InstantiateAbstractTicket;
import com.glo4003.project.ticket.model.factory.InstantiateTicketFactory;
import com.glo4003.project.user.model.UserConcreteModel;

public class MatchModelTest {
	@Test
	public void NewModelIsEmpty() {
		MatchDto model = getNewMatchModel();
		
		assertTrue(model.getCity().isEmpty());
		assertTrue(model.getField().isEmpty());
		assertTrue(model.getDate() == null);
		assertTrue(model.getGender() == null);
		assertTrue(model.getOpponent().isEmpty());
		assertTrue(model.getSport() == null);
		assertTrue(model.getTickets().isEmpty());
	}

	@Test
	public void PopulatedModelHasCity() throws PersistException,
			ConvertException {

		MatchConcreteModel model = getPopulatedMatchModel();

		String result = model.getCity();

		assertTrue(result.contentEquals("test"));
	}
	
	@Test
	public void PopulatedModelHasField() throws PersistException,
			ConvertException {
		MatchConcreteModel model = getPopulatedMatchModel();

		String result = model.getField();

		assertTrue(result.contentEquals("test"));
	}
	
	@Test
	public void PopulatedModelHasDate() throws PersistException,
			ConvertException {
		MatchConcreteModel model = getPopulatedMatchModel();

		Date result = model.getDate();

		assertTrue(result.equals(new Date(42, 42, 42, 42, 42, 42)));
	}
	
	@Test
	public void PopulatedModelHasGender() throws PersistException,
			ConvertException {
		MatchConcreteModel model = getPopulatedMatchModel();

		Gender result = model.getGender();

		assertTrue(result.equals(Gender.M));
	}
	
	@Test
	public void PopulatedModelHasOpponent() throws PersistException,
			ConvertException {
		
		MatchConcreteModel model = getPopulatedMatchModel();

		String result = model.getOpponent();

		assertTrue(result.contentEquals("test"));
	}
	
	@Test
	public void PopulatedModelHasSport() throws PersistException,
			ConvertException {

		MatchConcreteModel model = getPopulatedMatchModel();

		Sports result = model.getSport();

		assertTrue(result.equals(Sports.Soccer));
	}

	@Test
	public void PopulatedMatchModelNbTicketsWhenTickets()
			throws PersistException, ConvertException {

		MatchConcreteModel model = getPopulatedMatchModel();

		int nbTicket = model.getNumberRemainingTickets();

		assertEquals(nbTicket, 1);
	}

	@Test
	public void PopulatedMatchModelNbTicketsWhenNoTicket()
			throws PersistException, ConvertException {

		MatchConcreteModel model = getPopulatedMatchModel();
		model.setTickets(null);

		int nbTickets = model.getNumberRemainingTickets();

		assertEquals(nbTickets, 0);

	}

	private MatchConcreteModel getPopulatedMatchModel() throws PersistException,
			ConvertException {
		MatchConcreteModel model = new MatchConcreteModel();
		
		model.setCity("test");
		model.setDate(new Date(42, 42, 42, 42, 42, 42));
		model.setField("test");
		model.setGender(Gender.M);
		model.setId((long) 42);
		model.setOpponent("test");
		model.setSport(Sports.Soccer);
		
		

		ArrayList<AbstractTicketCategory> billetsMatch = new ArrayList<AbstractTicketCategory>();
		billetsMatch.add(TicketCategoryFactory.getTicketCategory(
				AbstractTicketCategory.RESERVED_TICKET, "Billet loges", 1, 0,
				32));
		
		model.setTickets(billetsMatch);

		return model;
	}

	private MatchDto getNewMatchModel() {
		return new MatchDto();
	}
}
