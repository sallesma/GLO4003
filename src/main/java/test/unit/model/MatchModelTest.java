package test.unit.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import model.factory.InstantiateTicketFactory;

import org.junit.Test;

import com.glo4003.project.database.exception.ConvertException;
import com.glo4003.project.database.exception.PersistException;
import com.glo4003.project.database.model.AbstractTicketCategory;
import com.glo4003.project.database.model.MatchModel;
import com.glo4003.project.database.model.MatchModel.Gender;
import com.glo4003.project.database.model.MatchModel.Sports;
import com.glo4003.project.database.model.UserModel;
import com.glo4003.project.match.model.MatchConcreteModel;
import com.glo4003.project.ticket.category.factory.TicketCategoryFactory;
import com.glo4003.project.ticket.model.InstantiateAbstractTicket;
import com.glo4003.project.user.model.UserConcreteModel;

public class MatchModelTest {
	@Test
	public void NewModelIsEmpty() {
		MatchModel model = getNewMatchModel();
		
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
		// Before
		MatchConcreteModel model = getPopulatedMatchModel();

		// When
		String result = model.getCity();

		// Then
		assertTrue(result.contentEquals("test"));
	}
	
	@Test
	public void PopulatedModelHasField() throws PersistException,
			ConvertException {
		// Before
		MatchConcreteModel model = getPopulatedMatchModel();

		// When
		String result = model.getField();

		// Then
		assertTrue(result.contentEquals("test"));
	}
	
	@Test
	public void PopulatedModelHasDate() throws PersistException,
			ConvertException {
		// Before
		MatchConcreteModel model = getPopulatedMatchModel();

		// When
		Date result = model.getDate();

		// Then
		assertTrue(result.equals(new Date(42, 42, 42, 42, 42, 42)));
	}
	
	@Test
	public void PopulatedModelHasGender() throws PersistException,
			ConvertException {
		// Before
		MatchConcreteModel model = getPopulatedMatchModel();

		// When
		Gender result = model.getGender();

		// Then
		assertTrue(result.equals(Gender.M));
	}
	
	@Test
	public void PopulatedModelHasOpponent() throws PersistException,
			ConvertException {
		// Before
		MatchConcreteModel model = getPopulatedMatchModel();

		// When
		String result = model.getOpponent();

		// Then
		assertTrue(result.contentEquals("test"));
	}
	
	@Test
	public void PopulatedModelHasSport() throws PersistException,
			ConvertException {
		// Before
		MatchConcreteModel model = getPopulatedMatchModel();

		// When
		Sports result = model.getSport();

		// Then
		assertTrue(result.equals(Sports.Soccer));
	}

	@Test
	public void PopulatedMatchModelNbTicketsWhenTickets()
			throws PersistException, ConvertException {
		// Before
		MatchConcreteModel model = getPopulatedMatchModel();

		// When
		int nbTicket = model.getNumberRemainingTickets();

		// Then
		assertEquals(nbTicket, 1);
	}

	@Test
	public void PopulatedMatchModelNbTicketsWhenNoTicket()
			throws PersistException, ConvertException {
		// Before
		MatchConcreteModel model = getPopulatedMatchModel();
		model.setTickets(null);

		// When
		int nbTickets = model.getNumberRemainingTickets();

		// Then
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

	private MatchModel getNewMatchModel() {
		return new MatchModel();
	}
}
