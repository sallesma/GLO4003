package test.unit.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.Test;

import com.glo4003.project.database.dto.AbstractTicketCategory;
import com.glo4003.project.database.dto.MatchDto;
import com.glo4003.project.database.dto.UserDto;
import com.glo4003.project.database.exception.ConvertException;
import com.glo4003.project.database.exception.PersistException;
import com.glo4003.project.ticket.category.factory.TicketCategoryFactory;
import com.glo4003.project.ticket.model.InstantiateAbstractTicket;
import com.glo4003.project.ticket.model.factory.InstantiateTicketFactory;
import com.glo4003.project.user.model.UserConcreteModel;

public class UserModelTest {

	@Test
	public void NewModelIsEmpty() {
		UserDto model = getNewUserModel();

		assertTrue(model.getAddress().isEmpty());
		assertTrue(model.getFirstName().isEmpty());
		assertTrue(model.getLastName().isEmpty());
		assertTrue(model.getPassword().isEmpty());
		assertTrue(model.getPhoneNumber().isEmpty());
		assertTrue(model.getUsername().isEmpty()); 
		assertFalse(model.isAdmin()); 
	}

	@Test
	public void PopulatedModelHasAddress() throws PersistException, ConvertException {
		 
		UserConcreteModel model = getPopulatedUserModel();

		 
		String result = model.getAddress();

		     	
		assertTrue(result.contentEquals("test"));
	}

	@Test
	public void PopulatedModelHasFirstName() throws PersistException, ConvertException {
		 
		UserConcreteModel model = getPopulatedUserModel();

		 
		String result = model.getFirstName();

		 
		assertTrue(result.contentEquals("test"));
	}

	@Test
	public void PopulatedModelHasLastName() throws PersistException, ConvertException {
		 
		UserConcreteModel model = getPopulatedUserModel();

		 
		String result = model.getLastName();

		 
		assertTrue(result.contentEquals("test"));
	}

	@Test
	public void PopulatedModelHasPhoneNumber() throws PersistException, ConvertException {
		 
		UserConcreteModel model = getPopulatedUserModel();

		 
		String result = model.getPhoneNumber();

		 
		assertTrue(result.contentEquals("test"));
	}

	@Test
	public void PopulatedModelHasUserName() throws PersistException, ConvertException {
		 
		UserConcreteModel model = getPopulatedUserModel();

		 
		String result = model.getUsername();

		     	
		assertTrue(result.contentEquals("test"));
	}

	@Test
	public void PopulatedModelHasPassword() throws PersistException, ConvertException {
		 
		UserConcreteModel model = getPopulatedUserModel();

		 
		String result = model.getPassword();

		 
		assertTrue(result.contentEquals("test"));
	}

	@Test
	public void PopulatedUSerModelIsNotAdmin() throws PersistException, ConvertException {
		 
		UserConcreteModel model = getPopulatedUserModel();

		 
		Boolean result = model.isAdmin();

		 
		assertFalse(result);
	}


	@Test
	public void PopulatedUserModelNbTicketsWhenTickets() throws PersistException, ConvertException{
		 
		UserConcreteModel model = getPopulatedUserModel();

		 
		int nbTicket = model.getNbTicketsInCart();

		 
		assertEquals(nbTicket, 1);
	}

	@Test
	public void PopulatedUserModelNbTicketsWhenNoTicket() throws PersistException, ConvertException {
		 
		UserConcreteModel model = getPopulatedUserModel();
		model.setTickets(null);

		 
		int nbTickets = model.getNbTicketsInCart();

		 
		assertEquals(nbTickets, 0);

	}


	private UserConcreteModel getPopulatedUserModel() throws PersistException, ConvertException {
		UserConcreteModel model = new UserConcreteModel();
		model.setAddress("test");
		model.setFirstName("test");
		model.setLastName("test");
		model.setPassword("test");
		model.setPhoneNumber("test");
		model.setUsername("test");
		model.setIsAdmin(false);

		ArrayList<AbstractTicketCategory> billetsMatch = new ArrayList<AbstractTicketCategory>();
		billetsMatch.add(TicketCategoryFactory.getTicketCategory(AbstractTicketCategory.RESERVED_TICKET,"Billet loges", 100, 0, 32));

		Calendar cal = Calendar.getInstance();
		cal.set(2010, 11, 11);
		MatchDto match = new MatchDto(MatchDto.Sports.Football, MatchDto.Gender.M, (long) 0, cal.getTime(), "UQAM", "Qu��bec", "ULaval", billetsMatch);
		InstantiateAbstractTicket ticket = InstantiateTicketFactory.getInstanciateTickets(0, match,"32", 1);

		model.addTicket(ticket);

		return model;
	}

	private UserDto getNewUserModel() {

		return new UserDto();    	
	}

}
