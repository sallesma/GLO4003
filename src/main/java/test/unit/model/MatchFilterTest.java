package test.unit.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

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
import com.glo4003.project.database.dto.MatchDto.Gender;
import com.glo4003.project.database.dto.MatchDto.Sports;
import com.glo4003.project.database.dto.SearchCriteriaDto;
import com.glo4003.project.database.exception.PersistException;
import com.glo4003.project.match.dao.MatchModelDaoInterface;
import com.glo4003.project.match.helper.MatchConverter;
import com.glo4003.project.match.helper.MatchFilter;
import com.glo4003.project.match.model.MatchConcreteModel;
import com.glo4003.project.match.viewModel.MatchViewModel;
import com.glo4003.project.ticket.category.factory.TicketCategoryFactory;

public class MatchFilterTest {

	private MatchFilter matchFilter = null;
	private SearchCriteriaDto searchCriteria;
	private List<MatchViewModel> populatedMatchList = null;
	private List<MatchViewModel> emptyMatchList = null;
	
	@Before
	public void populate() {
		populatedMatchList =  new ArrayList<MatchViewModel>();
		emptyMatchList = new ArrayList<MatchViewModel>();
		
		ArrayList<AbstractTicketCategory> billetsMatch1 = new ArrayList<AbstractTicketCategory>();
		billetsMatch1.add(TicketCategoryFactory.getTicketCategory(AbstractTicketCategory.FREE_TICKET, "Debout", 200, 0, 10));
		billetsMatch1.add(TicketCategoryFactory.getTicketCategory(AbstractTicketCategory.RESERVED_TICKET,"Billet loges", 100, 0, 32));
		
		ArrayList<AbstractTicketCategory> billetsMatch2 = new ArrayList<AbstractTicketCategory>();
		billetsMatch2.add(TicketCategoryFactory.getTicketCategory(AbstractTicketCategory.RESERVED_TICKET,"Billet loges", 100, 0, 32));
		

		Calendar cal = Calendar.getInstance();
		cal.set(2010, 11, 11);
		MatchConcreteModel match0concrete =  new MatchConcreteModel(Sports.Football, Gender.M, 1L, cal.getTime(), "UQAM", "Quebec", "ULaval", billetsMatch1);
		MatchViewModel match0 = new MatchConverter().convertToView(match0concrete);
		
		cal.set(2013, 11, 11);
		MatchConcreteModel match1concrete = new MatchConcreteModel(Sports.Football, Gender.M, 2L, cal.getTime(), "UQAM", "Montreal", "ULaval", billetsMatch2);
		MatchViewModel match1 = new MatchConverter().convertToView(match1concrete);
		
		cal.set(2013, 11, 9);
		MatchConcreteModel match2concrete = new MatchConcreteModel(Sports.Rugby, Gender.F, 3L, cal.getTime(), "Vert et or", "Sherbrooke", "unknown", billetsMatch1);
		MatchViewModel match2 = new MatchConverter().convertToView(match2concrete);
		
		cal.set(2013, 11, 8);
		MatchConcreteModel match3concrete = new MatchConcreteModel(Sports.Volleyball, Gender.F, 4L, cal.getTime(), "Rimouski", "Rimouski", "Gymnase municipal", billetsMatch1);
		MatchViewModel match3 = new MatchConverter().convertToView(match3concrete);
		
		MatchConcreteModel match4concrete =  new MatchConcreteModel(Sports.Football, Gender.M, 1L, cal.getTime(), "UQAM", "Quebec", "ULaval", billetsMatch2);
		MatchViewModel match4 = new MatchConverter().convertToView(match4concrete);
		
		populatedMatchList.add(match0);
		populatedMatchList.add(match1);
		populatedMatchList.add(match2);
		populatedMatchList.add(match3);
		populatedMatchList.add(match4);
		
		emptyMatchList = new ArrayList<MatchViewModel>();
		emptyMatchList.clear();
		
		searchCriteria = mock(SearchCriteriaDto.class);
	}

	@Test
	public void testFilterEmptyMatchList() {
		//Before
		try {
			matchFilter = new MatchFilter(emptyMatchList, searchCriteria);
		} catch (PersistException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
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
		when(searchCriteria.getCategory()).thenReturn("");
		when(searchCriteria.getCity()).thenReturn("");
		when(searchCriteria.getFromDateObject()).thenReturn(null);
		when(searchCriteria.getToDateObject()).thenReturn(null);
		when(searchCriteria.getGender()).thenReturn(null);
		when(searchCriteria.getOpponent()).thenReturn("");
		when(searchCriteria.getSport()).thenReturn(null);
		
		
		//Before
		try {
			matchFilter = new MatchFilter(populatedMatchList, searchCriteria);

			when(searchCriteria.isCategoryEmpty()).thenReturn(true);
			when(searchCriteria.isCityEmpty()).thenReturn(true);
			when(searchCriteria.isFromDateEmpty()).thenReturn(true);
			when(searchCriteria.isGenderEmpty()).thenReturn(true);
			when(searchCriteria.isOpponentEmpty()).thenReturn(true);
			when(searchCriteria.isSportEmpty()).thenReturn(true);
			when(searchCriteria.isToDateEmpty()).thenReturn(true);
			
		} catch (PersistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
		
		try {
			matchFilter = new MatchFilter(populatedMatchList, searchCriteria);
			when(searchCriteria.isCategoryEmpty()).thenReturn(true);
			when(searchCriteria.isCityEmpty()).thenReturn(true);
			when(searchCriteria.isFromDateEmpty()).thenReturn(true);
			when(searchCriteria.isGenderEmpty()).thenReturn(true);
			when(searchCriteria.isOpponentEmpty()).thenReturn(true);
			when(searchCriteria.isSportEmpty()).thenReturn(false);
			when(searchCriteria.isToDateEmpty()).thenReturn(true);
			when(searchCriteria.getSport()).thenReturn(Sports.Football);
			
		} catch (PersistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		int nbFootballMatchs = 0;
		for (MatchViewModel m : populatedMatchList) {
			if (m.getSport().equals(Sports.Football))
				nbFootballMatchs++;
		}
		
		//When
		List<MatchViewModel> matchsList = null;
		try {
			matchsList = matchFilter.filterMatchList();
		} catch (PersistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Then
		assertEquals(matchsList.size(), nbFootballMatchs);
		for (MatchViewModel m : matchsList) {
			assertEquals(m.getSport(), Sports.Football);
		}
		
	}
	
	@Test
	public void testGenreFilter() {
		//Before
		
		try {
			matchFilter = new MatchFilter(populatedMatchList, searchCriteria);
			when(searchCriteria.isCategoryEmpty()).thenReturn(true);
			when(searchCriteria.isCityEmpty()).thenReturn(true);
			when(searchCriteria.isFromDateEmpty()).thenReturn(true);
			when(searchCriteria.isGenderEmpty()).thenReturn(false);
			when(searchCriteria.isOpponentEmpty()).thenReturn(true);
			when(searchCriteria.isSportEmpty()).thenReturn(true);
			when(searchCriteria.isToDateEmpty()).thenReturn(true);
			when(searchCriteria.getGender()).thenReturn(Gender.F);
		} catch (PersistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		int nbChicksMatchs = 0;
		for (MatchViewModel m : populatedMatchList) {
			if (m.getGender().equals(Gender.F))
				nbChicksMatchs++;
		}
		
		//When
		List<MatchViewModel> matchsList = null;
		try {
			matchsList = matchFilter.filterMatchList();
		} catch (PersistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Then
		assertEquals(matchsList.size(), nbChicksMatchs);
		for (MatchViewModel m : matchsList) {
			assertEquals(m.getGender(), Gender.F);
		}
		
	}
	
	@Test
	public void testOpponentFilter() {
		//Before
		String opp = populatedMatchList.get(0).getOpponent();		
		
		try {
			matchFilter = new MatchFilter(populatedMatchList, searchCriteria);
			when(searchCriteria.isCategoryEmpty()).thenReturn(true);
			when(searchCriteria.isCityEmpty()).thenReturn(true);
			when(searchCriteria.isFromDateEmpty()).thenReturn(true);
			when(searchCriteria.isGenderEmpty()).thenReturn(true);
			when(searchCriteria.isOpponentEmpty()).thenReturn(false);
			when(searchCriteria.isSportEmpty()).thenReturn(true);
			when(searchCriteria.isToDateEmpty()).thenReturn(true);
			when(searchCriteria.getOpponent()).thenReturn(opp);
		} catch (PersistException e) {
			e.printStackTrace();
		}
		
		
		
		int nbOpponentMatchs = 0;
		for (MatchViewModel m : populatedMatchList) {
			if (m.getOpponent().equals(opp))
				nbOpponentMatchs++;
		}
		
		//When
		List<MatchViewModel> matchsList = null;
		try {
			matchsList = matchFilter.filterMatchList();
		} catch (PersistException e) {
			e.printStackTrace();
		}
		
		//Then
		assertEquals(matchsList.size(), nbOpponentMatchs);
		for (MatchViewModel m : matchsList) {
			assertEquals(m.getOpponent(), opp);
		}
		
	}
	
	@Test
	public void testFilterByTicketType() {
		try {
			matchFilter = new MatchFilter(populatedMatchList, searchCriteria);
			when(searchCriteria.isCategoryEmpty()).thenReturn(false);
			when(searchCriteria.isCityEmpty()).thenReturn(true);
			when(searchCriteria.isFromDateEmpty()).thenReturn(true);
			when(searchCriteria.isGenderEmpty()).thenReturn(true);
			when(searchCriteria.isOpponentEmpty()).thenReturn(true);
			when(searchCriteria.isSportEmpty()).thenReturn(true);
			when(searchCriteria.isToDateEmpty()).thenReturn(true);
			when(searchCriteria.getCategory()).thenReturn("General");
			
		} catch (PersistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int nbTicketTypeMatchs = 0;
		for (MatchViewModel m : populatedMatchList) {
			for (AbstractTicketCategory ticket : m.getTickets())
				if (ticket instanceof GeneralAdmissionTicketCategoryDto)
				nbTicketTypeMatchs++;
		}
		
		//When
		List<MatchViewModel> matchsList = null;
		try {
			matchsList = matchFilter.filterMatchList();
		} catch (PersistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Then
		assertEquals(matchsList.size(), nbTicketTypeMatchs);
		for (MatchViewModel m : matchsList) {
			assertEquals(m.getTickets().get(0).getClass(), GeneralAdmissionTicketCategoryDto.class);
		}
	}
	
	@Test
	public void testFilterByCity() {
		//Before
		String city = populatedMatchList.get(0).getCity();
		
		try {
			matchFilter = new MatchFilter(populatedMatchList, searchCriteria);
			when(searchCriteria.isCategoryEmpty()).thenReturn(true);
			when(searchCriteria.isCityEmpty()).thenReturn(false);
			when(searchCriteria.isFromDateEmpty()).thenReturn(true);
			when(searchCriteria.isGenderEmpty()).thenReturn(true);
			when(searchCriteria.isOpponentEmpty()).thenReturn(true);
			when(searchCriteria.isSportEmpty()).thenReturn(true);
			when(searchCriteria.isToDateEmpty()).thenReturn(true);
			when(searchCriteria.getCity()).thenReturn(city);
		} catch (PersistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int nbCityMatchs = 0;
		for (MatchViewModel m : populatedMatchList) {
			if (m.getCity().equals(city))
				nbCityMatchs++;
		}
		
		//When
		List<MatchViewModel> matchsList = null;
		try {
			matchsList = matchFilter.filterMatchList();
		} catch (PersistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Then
		assertEquals(matchsList.size(), nbCityMatchs);
		for (MatchViewModel m : matchsList) {
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
		
		try {
			matchFilter = new MatchFilter(populatedMatchList, searchCriteria);
			when(searchCriteria.isCategoryEmpty()).thenReturn(true);
			when(searchCriteria.isCityEmpty()).thenReturn(true);
			when(searchCriteria.isFromDateEmpty()).thenReturn(false);
			when(searchCriteria.isGenderEmpty()).thenReturn(true);
			when(searchCriteria.isOpponentEmpty()).thenReturn(true);
			when(searchCriteria.isSportEmpty()).thenReturn(true);
			when(searchCriteria.isToDateEmpty()).thenReturn(false);
			when(searchCriteria.getFromDateObject()).thenReturn(fromDate);
			when(searchCriteria.getToDateObject()).thenReturn(toDate);
			
		} catch (PersistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		int nbBetweenDatesMatchs = 0;
		for (MatchViewModel m : populatedMatchList) {
			if (m.getDate().after(fromDate) && m.getDate().before(toDate))
				nbBetweenDatesMatchs++;
		}
		
		//When
		List<MatchViewModel> matchsList = null;
		try {
			matchsList = matchFilter.filterMatchList();
		} catch (PersistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Then
		assertEquals(matchsList.size(), nbBetweenDatesMatchs);
		for (MatchViewModel m : matchsList) {
			assertTrue((m.getDate().after(fromDate)) && (m.getDate().before(toDate))) ;
		}
	}
}
