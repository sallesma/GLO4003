package test.unit.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.spy;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

import com.glo4003.project.database.dto.AbstractTicketCategory;
import com.glo4003.project.database.dto.GeneralAdmissionTicketCategoryDto;
import com.glo4003.project.database.dto.MatchDto;
import com.glo4003.project.database.dto.SearchCriteriaDto;
import com.glo4003.project.database.exception.PersistException;
import com.glo4003.project.match.dao.MatchModelDaoInterface;
import com.glo4003.project.match.helper.MatchFilter;
import com.glo4003.project.ticket.category.factory.TicketCategoryFactory;

public class MatchFilterTest {
/*
	private MatchFilter matchFilter = null;
	private SearchCriteriaModel searchCriteria;
	private MatchModelDaoInterface populateMatchModelDao;
	private MatchModelDaoInterface emptyMatchModelDao;
	private List<MatchModel> populatedMatchList = null;
	List<MatchModel> emptyMatchList = null;
	
	@Before
	public void populate() {
		populatedMatchList =  new ArrayList<MatchModel>();
		emptyMatchList = new ArrayList<MatchModel>();
		
		ArrayList<AbstractTicketCategory> billetsMatch1 = new ArrayList<AbstractTicketCategory>();
		billetsMatch1.add(TicketCategoryFactory.getTicketCategory(AbstractTicketCategory.FREE_TICKET, "Debout", 200, 0, 10));
		billetsMatch1.add(TicketCategoryFactory.getTicketCategory(AbstractTicketCategory.RESERVED_TICKET,"Billet loges", 100, 0, 32));
		
		ArrayList<AbstractTicketCategory> billetsMatch2 = new ArrayList<AbstractTicketCategory>();
		billetsMatch2.add(TicketCategoryFactory.getTicketCategory(AbstractTicketCategory.RESERVED_TICKET,"Billet loges", 100, 0, 32));
		

		Calendar cal = Calendar.getInstance();
		cal.set(2010, 11, 11);
		MatchModel match0 =  new MatchModel(MatchModel.Sports.Football, MatchModel.Gender.M, 1L, cal.getTime(), "UQAM", "Quebec", "ULaval", billetsMatch1);
		cal.set(2013, 11, 11);
		MatchModel match1 = new MatchModel(MatchModel.Sports.Football, MatchModel.Gender.M, 2L, cal.getTime(), "UQAM", "Montreal", "ULaval", billetsMatch2);
		cal.set(2013, 11, 9);
		MatchModel match2 = new MatchModel(MatchModel.Sports.Rugby, MatchModel.Gender.F, 3L, cal.getTime(), "Vert et or", "Sherbrooke", "unknown", billetsMatch1);
		cal.set(2013, 11, 8);
		MatchModel match3 = new MatchModel(MatchModel.Sports.Volleyball, MatchModel.Gender.F, 4L, cal.getTime(), "Rimouski", "Rimouski", "Gymnase municipal", billetsMatch1);
		
		MatchModel match4 =  new MatchModel(MatchModel.Sports.Football, MatchModel.Gender.M, 1L, cal.getTime(), "UQAM", "Quebec", "ULaval", billetsMatch2);
		
		
		populatedMatchList.add(match0);
		populatedMatchList.add(match1);
		populatedMatchList.add(match2);
		populatedMatchList.add(match3);
		populatedMatchList.add(match4);
		
		emptyMatchList = new ArrayList<MatchModel>();
		emptyMatchList.clear();
		
		populateMatchModelDao = new MockMatchModelDao(populatedMatchList);
		emptyMatchModelDao = new MockMatchModelDao(emptyMatchList);
	}

	@Test
	public void testFilterEmptyMatchList() {
		//Before
		matchFilter = spy(new MatchFilter());
		matchFilter.setMatchDao(emptyMatchModelDao);
		
		//When
		try {
			matchFilter.filterMatchList();
		} catch (PersistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//then
		assertTrue(matchFilter.getMatchList().isEmpty());
		assertTrue(matchFilter.getOpponentsList().isEmpty());
	}
	
	@Test
	public void testFilterMatchNoFilters() {
		//Before
		matchFilter = spy(new MatchFilter());
		try {
			searchCriteria = new SearchCriteriaModel("", "", "", "", "", "", "");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		matchFilter.setCriterias(searchCriteria);
		matchFilter.setMatchDao(populateMatchModelDao);
		
		//when
		try {
			matchFilter.filterMatchList();
		} catch (PersistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
		//then
		assertTrue(matchFilter.getMatchList().equals(populatedMatchList));
	}
	
	
	@Test
	public void testSportFilter() {
		//Before
		matchFilter = spy(new MatchFilter());
		try {
			searchCriteria = new SearchCriteriaModel(MatchModel.Sports.Football.toString(), "", "", "", "", "", "");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		matchFilter.setCriterias(searchCriteria);
		matchFilter.setMatchDao(populateMatchModelDao);
		
		int nbFootballMatchs = 0;
		for (MatchModel m : populatedMatchList) {
			if (m.getSport().equals(MatchModel.Sports.Football))
				nbFootballMatchs++;
		}
		
		//When
		List<MatchModel> matchsList = null;
		try {
			matchsList = matchFilter.filterMatchList();
		} catch (PersistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Then
		assertEquals(matchsList.size(), nbFootballMatchs);
		for (MatchModel m : matchsList) {
			assertEquals(m.getSport(), MatchModel.Sports.Football);
		}
		
	}
	
	@Test
	public void testGenreFilter() {
		//Before
		matchFilter = spy(new MatchFilter());
		try {
			searchCriteria = new SearchCriteriaModel("", MatchModel.Gender.F.toString(), "", "", "", "", "");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		matchFilter.setCriterias(searchCriteria);
		matchFilter.setMatchDao(populateMatchModelDao);
		
		
		int nbChicksMatchs = 0;
		for (MatchModel m : populatedMatchList) {
			if (m.getGender().equals(MatchModel.Gender.F))
				nbChicksMatchs++;
		}
		
		//When
		List<MatchModel> matchsList = null;
		try {
			matchsList = matchFilter.filterMatchList();
		} catch (PersistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Then
		assertEquals(matchsList.size(), nbChicksMatchs);
		for (MatchModel m : matchsList) {
			assertEquals(m.getGender(), MatchModel.Gender.F);
		}
		
	}
	
	@Test
	public void testOpponentFilter() {
		//Before
		String opp = populatedMatchList.get(0).getOpponent();		
		matchFilter = spy(new MatchFilter());
		try {
			searchCriteria = new SearchCriteriaModel("", "", opp, "", "", "", "");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		matchFilter.setCriterias(searchCriteria);
		matchFilter.setMatchDao(populateMatchModelDao);
		
		
		
		int nbOpponentMatchs = 0;
		for (MatchModel m : populatedMatchList) {
			if (m.getOpponent().equals(opp))
				nbOpponentMatchs++;
		}
		
		//When
		List<MatchModel> matchsList = null;
		try {
			matchsList = matchFilter.filterMatchList();
		} catch (PersistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Then
		assertEquals(matchsList.size(), nbOpponentMatchs);
		for (MatchModel m : matchsList) {
			assertEquals(m.getOpponent(), opp);
		}
		
	}
	
	@Test
	public void testFilterByTicketType() {
		matchFilter = spy(new MatchFilter());
		try {
			searchCriteria = new SearchCriteriaModel("", "", "", "General", "", "", "");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		matchFilter.setCriterias(searchCriteria);
		matchFilter.setMatchDao(populateMatchModelDao);

		int nbTicketTypeMatchs = 0;
		for (MatchModel m : populatedMatchList) {
			for (AbstractTicketCategory ticket : m.getTickets())
				if (ticket instanceof GeneralAdmissionTicketCategory)
				nbTicketTypeMatchs++;
		}
		
		//When
		List<MatchModel> matchsList = null;
		try {
			matchsList = matchFilter.filterMatchList();
		} catch (PersistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Then
		assertEquals(matchsList.size(), nbTicketTypeMatchs);
		for (MatchModel m : matchsList) {
			assertEquals(m.getTickets().get(0).getClass(), GeneralAdmissionTicketCategory.class);
		}
	}
	
	@Test
	public void testFilterByCity() {
		//Before
		String city = populatedMatchList.get(0).getCity();
		
		matchFilter = spy(new MatchFilter());
		try {
			searchCriteria = new SearchCriteriaModel("", "", "", "", city, "", "");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		matchFilter.setCriterias(searchCriteria);
		matchFilter.setMatchDao(populateMatchModelDao);
		
		int nbCityMatchs = 0;
		for (MatchModel m : populatedMatchList) {
			if (m.getCity().equals(city))
				nbCityMatchs++;
		}
		
		//When
		List<MatchModel> matchsList = null;
		try {
			matchsList = matchFilter.filterMatchList();
		} catch (PersistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Then
		assertEquals(matchsList.size(), nbCityMatchs);
		for (MatchModel m : matchsList) {
			assertEquals(m.getCity(), city);
		}
	}
	
	@Test
	public void testFromDateFilter() {
		//Before
		Date exempleDate = populatedMatchList.get(0).getDate();
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(exempleDate);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		Date fromDate = cal.getTime();
		DateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy", Locale.CANADA_FRENCH);		
		cal.add(Calendar.YEAR, 1);
		Date toDate = cal.getTime();
		
		matchFilter = spy(new MatchFilter());
		try {
			searchCriteria = new SearchCriteriaModel("", "", "", "", "", dateFormatter.format(fromDate), dateFormatter.format(toDate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		matchFilter.setCriterias(searchCriteria);
		matchFilter.setMatchDao(populateMatchModelDao);
		
		int nbBetweenDatesMatchs = 0;
		for (MatchModel m : populatedMatchList) {
			if (m.getDate().after(fromDate) && m.getDate().before(toDate))
				nbBetweenDatesMatchs++;
		}
		
		//When
		List<MatchModel> matchsList = null;
		try {
			matchsList = matchFilter.filterMatchList();
		} catch (PersistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Then
		assertEquals(matchsList.size(), nbBetweenDatesMatchs);
		for (MatchModel m : matchsList) {
			assertTrue((m.getDate().after(fromDate)) && (m.getDate().before(toDate))) ;
		}
	}*/
}
