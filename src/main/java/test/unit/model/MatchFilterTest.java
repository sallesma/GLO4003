package test.unit.model;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.spy;
import helper.MatchFilter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import model.AbstractTicketCategory;
import model.MatchModel;
import model.factory.TicketCategoryFactory;

import org.junit.Before;
import org.junit.Test;

import config.ConfigManager;
import config.ConfigManager.Gender;
import config.ConfigManager.Sports;

public class MatchFilterTest {

	private MatchFilter matchFilter = null;
	private List<MatchModel> populatedMatchList = null;
	private List<MatchModel> emptyMatchList = null;
	
	@Before
	public void populate() {
		populatedMatchList = new ArrayList<MatchModel>();
		ArrayList<AbstractTicketCategory> billetsMatch1 = new ArrayList<AbstractTicketCategory>();
		billetsMatch1.add(TicketCategoryFactory.getTicketCategory(ConfigManager.RESERVED_TICKET,"Billet loges", 100, 0, 32));
		billetsMatch1.add(TicketCategoryFactory.getTicketCategory(ConfigManager.FREE_TICKET, "Debout", 200, 0, 10));

		Calendar cal = Calendar.getInstance();
		cal.set(2010, 11, 11);
		MatchModel match0 =  new MatchModel(Sports.Football, Gender.M, 1L, cal.getTime(), "UQAM", "Qu��bec", "ULaval", billetsMatch1);
		cal.set(2013, 11, 11);
		MatchModel match1 = new MatchModel(Sports.Football, Gender.M, 2L, cal.getTime(), "UQAM", "Montréal", "ULaval", billetsMatch1);
		cal.set(2013, 11, 9);
		MatchModel match2 = new MatchModel(Sports.Rugby, Gender.F, 3L, cal.getTime(), "Vert et or", "Sherbrooke", "unknown", billetsMatch1);
		cal.set(2013, 11, 8);
		MatchModel match3 = new MatchModel(Sports.Volleyball, Gender.F, 4L, cal.getTime(), "Rimouski", "Rimouski", "Gymnase municipal", billetsMatch1);
		
		MatchModel match4 =  new MatchModel(Sports.Football, Gender.M, 1L, cal.getTime(), "UQAM", "Qu��bec", "ULaval", billetsMatch1);
		
		
		populatedMatchList.add(match0);
		populatedMatchList.add(match1);
		populatedMatchList.add(match2);
		populatedMatchList.add(match3);
		populatedMatchList.add(match4);
		
		emptyMatchList = new ArrayList<MatchModel>();
		emptyMatchList.clear();
	}

	@Test
	public void testFilterEmptyMatchList() {
		//Before
		matchFilter = spy(new MatchFilter(emptyMatchList));
		
		//then
		assertTrue(matchFilter.getMatchList().isEmpty());
		assertTrue(matchFilter.getOpponentsList().isEmpty());
	}
	
	@Test
	public void testFilterMatchNoFilters() {
		//Before
		matchFilter = spy(new MatchFilter(populatedMatchList, "", "", "", "", ""));
		
		//when
		matchFilter.filterMatchList();
		
		//then
		assertTrue(matchFilter.getMatchList().equals(populatedMatchList));
	}
	
	@Test
	public void testDefaultDateFromTodayToNextYear() {
		//Before
		matchFilter = spy(new MatchFilter(populatedMatchList));

		String fromDate = new SimpleDateFormat("MM/dd/yyyy", Locale.CANADA_FRENCH).format(new Date());
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, 1);
		Date oneYearLater = cal.getTime();
		String toDate = new SimpleDateFormat("MM/dd/yyyy", Locale.CANADA_FRENCH).format(oneYearLater);
		
		//then
		assertTrue(matchFilter.getFromDate().equals(fromDate));
		assertTrue(matchFilter.getToDate().equals(toDate));
	}
	
	@Test
	public void testSportFilter() {
		//Before
		matchFilter = spy(new MatchFilter(populatedMatchList));
		matchFilter.setSport(Sports.Football.toString());
		matchFilter.setFromDate(null);
		matchFilter.setToDate(null);
		matchFilter.setOpponent(null);
		matchFilter.setGender(null);
		
		int nbFootballMatchs = 0;
		for (MatchModel m : matchFilter.getMatchList()) {
			if (m.getSport().equals(Sports.Football))
				nbFootballMatchs++;
		}
		
		//When
		List<MatchModel> matchsList = matchFilter.filterMatchList();
		
		//Then
		assertEquals(matchsList.size(), nbFootballMatchs);
		for (MatchModel m : matchsList) {
			assertEquals(m.getSport(), Sports.Football);
		}
		
	}
	
	@Test
	public void testGenreFilter() {
		//Before
		matchFilter = spy(new MatchFilter(populatedMatchList));
		matchFilter.setSport(null);
		matchFilter.setFromDate(null);
		matchFilter.setToDate(null);
		matchFilter.setOpponent(null);
		matchFilter.setGender(Gender.F.toString());
		
		int nbChicksMatchs = 0;
		for (MatchModel m : matchFilter.getMatchList()) {
			if (m.getGender().equals(Gender.F))
				nbChicksMatchs++;
		}
		
		//When
		List<MatchModel> matchsList = matchFilter.filterMatchList();
		
		//Then
		assertEquals(matchsList.size(), nbChicksMatchs);
		for (MatchModel m : matchsList) {
			assertEquals(m.getGender(), Gender.F);
		}
		
	}
	
	@Test
	public void testOpponentFilter() {
		//Before
		matchFilter = spy(new MatchFilter(populatedMatchList));
		String opp = matchFilter.getMatchList().get(0).getOpponent();
		matchFilter.setSport(null);
		matchFilter.setFromDate(null);
		matchFilter.setToDate(null);
		matchFilter.setOpponent(opp);
		matchFilter.setGender(null);
		
		int nbOpponentMatchs = 0;
		for (MatchModel m : matchFilter.getMatchList()) {
			if (m.getOpponent().equals(opp))
				nbOpponentMatchs++;
		}
		
		//When
		List<MatchModel> matchsList = matchFilter.filterMatchList();
		
		//Then
		assertEquals(matchsList.size(), nbOpponentMatchs);
		for (MatchModel m : matchsList) {
			assertEquals(m.getOpponent(), opp);
		}
		
	}
	
	@Test
	public void testFromDateFilter() {
		//Before
		matchFilter = spy(new MatchFilter(populatedMatchList));
		
		Date exempleDate = matchFilter.getMatchList().get(0).getDate();
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(exempleDate);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		Date fromDate = cal.getTime();
		DateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy", Locale.CANADA_FRENCH);
		matchFilter.setFromDate(dateFormatter.format(fromDate));
		
		cal.add(Calendar.YEAR, 1);
		Date toDate = cal.getTime();
		
		matchFilter.setToDate(dateFormatter.format(toDate));
	
		matchFilter.setSport(null);
		matchFilter.setOpponent(null);
		matchFilter.setGender(null);
		
		int nbBetweenDatesMatchs = 0;
		for (MatchModel m : matchFilter.getMatchList()) {
			if (m.getDate().after(fromDate) && m.getDate().before(toDate))
				nbBetweenDatesMatchs++;
		}
		
		//When
		List<MatchModel> matchsList = matchFilter.filterMatchList();
		
		//Then
		assertEquals(matchsList.size(), nbBetweenDatesMatchs);
		for (MatchModel m : matchsList) {
			assertTrue((m.getDate().after(fromDate)) && (m.getDate().before(toDate))) ;
		}
	}
}
