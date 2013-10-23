package test.unit.model;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;

import model.AbstractTicketCategory;
import model.InstantiateTicketModel;
import model.MatchModel;
import model.UserModel;
import model.factory.TicketCategoryFactory;

import org.junit.Test;

import config.ConfigManager;
import config.ConfigManager.Gender;
import config.ConfigManager.Sports;

public class UserModelTest {

	@Test
	public void NewModelIsEmpty() {
		UserModel model = getNewUserModel();

		assertTrue(model.getAddress().isEmpty());
		assertTrue(model.getFirstName().isEmpty());
		assertTrue(model.getLastName().isEmpty());
		assertTrue(model.getPassword().isEmpty());
		assertTrue(model.getPhoneNumber().isEmpty());
		assertTrue(model.getUsername().isEmpty()); 
		assertFalse(model.isAdmin()); 
	}

	@Test
	public void PopulatedModelHasAddress() {
		//Before
		UserModel model = getPopulatedUserModel();

		//When
		String result = model.getAddress();

		//Then    	
		assertTrue(result.contentEquals("test"));
	}

	@Test
	public void PopulatedModelHasFirstName() {
		//Before
		UserModel model = getPopulatedUserModel();

		//When
		String result = model.getFirstName();

		//Then
		assertTrue(result.contentEquals("test"));
	}

	@Test
	public void PopulatedModelHasLastName() {
		//Before
		UserModel model = getPopulatedUserModel();

		//When
		String result = model.getLastName();

		//Then
		assertTrue(result.contentEquals("test"));
	}

	@Test
	public void PopulatedModelHasPhoneNumber() {
		//Before
		UserModel model = getPopulatedUserModel();

		//When
		String result = model.getPhoneNumber();

		//Then
		assertTrue(result.contentEquals("test"));
	}

	@Test
	public void PopulatedModelHasUserName() {
		//Before
		UserModel model = getPopulatedUserModel();

		//When
		String result = model.getUsername();

		//Then    	
		assertTrue(result.contentEquals("test"));
	}

	@Test
	public void PopulatedModelHasPassword() {
		//Before
		UserModel model = getPopulatedUserModel();

		//When
		String result = model.getPassword();

		//Then
		assertTrue(result.contentEquals("test"));
	}

	@Test
	public void PopulatedUSerModelIsNotAdmin() {
		//Before
		UserModel model = getPopulatedUserModel();

		//When
		Boolean result = model.isAdmin();

		//Then
		assertFalse(result);
	}



	@Test
	public void PopulatedUserModelGetTicket() {
		//Before
		UserModel model = getPopulatedUserModel();
		InstantiateTicketModel ticket = model.getTickets().get(0);

		int ticketId = ticket.getTicketId();

		//When
		InstantiateTicketModel gettedTicket = model.getTicketById(ticketId);

		//Then
		assertEquals(ticket, gettedTicket);

	}




	@Test
	public void PopulatedUserModelAddTicket() {
		//Before
		UserModel model = getPopulatedUserModel();

		ArrayList<AbstractTicketCategory> billetsMatch = new ArrayList<AbstractTicketCategory>();
		billetsMatch.add(TicketCategoryFactory.getTicketCategory(ConfigManager.FREE_TICKET,"Billet Debout", 200, 0, 32));
		Calendar cal = Calendar.getInstance();
		cal.set(2010, 11, 11);
		MatchModel match = new MatchModel(Sports.Football, Gender.M, (long) 0, cal.getTime(), "UQAM", "Québec", "ULaval", billetsMatch);
		InstantiateTicketModel ticket = new InstantiateTicketModel(match, 0, "23", 1);

		//When
		model.addTicket(ticket);

		//Then
		assertTrue(model.getTickets().contains(ticket));

	}


	@Test
	public void PopulatedUserModelDeleteTicket() {
		//Before
		UserModel model = getPopulatedUserModel();
		InstantiateTicketModel ticket = model.getTickets().get(0);

		//When
		model.deleteTicket(0);

		//Then
		assertFalse(model.getTickets().contains(ticket));
	}

	@Test
	public void PopulatedUserModelNbTicketsWhenTickets(){
		//Before
		UserModel model = getPopulatedUserModel();

		//When
		int nbTicket = model.getNbTicketsInCart();

		//Then
		assertEquals(nbTicket, 1);
	}

	@Test
	public void PopulatedUserModelNbTicketsWhenNoTicket() {
		//Before
		UserModel model = getPopulatedUserModel();
		model.setTickets(null);

		//When
		int nbTickets = model.getNbTicketsInCart();

		//Then
		assertEquals(nbTickets, 0);

	}


	private UserModel getPopulatedUserModel() {
		UserModel model = new UserModel();
		model.setAddress("test");
		model.setFirstName("test");
		model.setLastName("test");
		model.setPassword("test");
		model.setPhoneNumber("test");
		model.setUsername("test");
		model.setIsAdmin(false);

		ArrayList<AbstractTicketCategory> billetsMatch = new ArrayList<AbstractTicketCategory>();
		billetsMatch.add(TicketCategoryFactory.getTicketCategory(ConfigManager.RESERVED_TICKET,"Billet loges", 100, 0, 32));

		Calendar cal = Calendar.getInstance();
		cal.set(2010, 11, 11);
		MatchModel match = new MatchModel(Sports.Football, Gender.M, (long) 0, cal.getTime(), "UQAM", "Québec", "ULaval", billetsMatch);
		InstantiateTicketModel ticket = new InstantiateTicketModel(match, 0, "32", 1);

		model.addTicket(ticket);

		return model;
	}

	private UserModel getNewUserModel() {

		return new UserModel();    	
	}

}
