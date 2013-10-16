package test.unit.model;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.spy;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import model.AbstractTicketCategory;
import model.MatchFilter;
import model.MatchModel;
import model.TicketCategoryFactory;

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
		MatchModel match0 =  new MatchModel(Sports.Football, Gender.M, 1, cal.getTime(), "UQAM", "Québec", "ULaval", billetsMatch1);
		cal.set(2013, 11, 11);
		MatchModel match1 = new MatchModel(Sports.Football, Gender.M, 2, cal.getTime(), "UQAM", "Québec", "ULaval", billetsMatch1);
		cal.set(2013, 11, 9);
		MatchModel match2 = new MatchModel(Sports.Rugby, Gender.F, 3, cal.getTime(), "Vert et or", "Sherbrooke", "unknown", billetsMatch1);
		cal.set(2013, 11, 8);
		MatchModel match3 = new MatchModel(Sports.Volleyball, Gender.F, 4, cal.getTime(), "Rimouski", "Rimouski", "Gymnase municipal", billetsMatch1);
		
		populatedMatchList.add(match0);
		populatedMatchList.add(match1);
		populatedMatchList.add(match2);
		populatedMatchList.add(match3);
		
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
		matchFilter.FilterMatchList();
		
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
}
