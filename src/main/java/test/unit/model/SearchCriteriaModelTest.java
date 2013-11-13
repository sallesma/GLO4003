package test.unit.model;

import static org.junit.Assert.*;
import model.SearchCriteriaModel;

import org.junit.Test;

import config.ConfigManager.Gender;
import config.ConfigManager.Sports;

public class SearchCriteriaModelTest {
	
	private SearchCriteriaModel testClass = new SearchCriteriaModel();
	

	@Test
	public void testIsSportEmpty() {
		assertTrue(testClass.isSportEmpty());
		
		testClass.setSport(null);
		assertTrue(testClass.isSportEmpty());

		testClass.setSport("");
		assertTrue(testClass.isSportEmpty());

		testClass.setSport(Sports.Football.toString());
		assertTrue(!(testClass.isSportEmpty()));

	}

	@Test
	public void testIsGenderEmpty() {
		assertTrue(testClass.isGenderEmpty());
		
		testClass.setGender(null);
		assertTrue(testClass.isGenderEmpty());

		testClass.setGender("");
		assertTrue(testClass.isGenderEmpty());

		testClass.setGender(Gender.F.toString());
		assertTrue(!(testClass.isGenderEmpty()));
	}

	@Test
	public void testIsOpponentEmpty() {
		assertTrue(testClass.isOpponentEmpty());
		
		testClass.setOpponent(null);
		assertTrue(testClass.isOpponentEmpty());

		testClass.setOpponent("");
		assertTrue(testClass.isOpponentEmpty());

		testClass.setOpponent("Laval");
		assertTrue(!(testClass.isOpponentEmpty()));
	}

	@Test
	public void testIsFromDateEmpty() {		
		testClass.setFromDate(null);
		assertTrue(testClass.isFromDateEmpty());

		testClass.setFromDate("");
		assertTrue(testClass.isFromDateEmpty());

		testClass.setFromDate("12/12/2013");
		assertTrue(!(testClass.isFromDateEmpty()));
	}

	@Test
	public void testIsToDateEmpty() {		
		testClass.setToDate(null);
		assertTrue(testClass.isToDateEmpty());

		testClass.setToDate("");
		assertTrue(testClass.isToDateEmpty());

		testClass.setToDate("12/12/2013");
		assertTrue(!(testClass.isToDateEmpty()));
	}

}
