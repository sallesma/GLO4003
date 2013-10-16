package test.unit.model;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyFloat;
import static org.mockito.Matchers.anyInt;
import model.GeneralAdmissionTicketCategory;
import model.AbstractTicketCategory;
import model.TicketCategoryFactory;
import model.ReservedTicketCategory;

import org.junit.Test;
import org.mockito.Mockito;

import config.ConfigManager;

public class BilletFactoryTest {

	@Test
	public void testGetSeatedBillet() {
		 AbstractTicketCategory billet = TicketCategoryFactory.getTicketCategory(ConfigManager.RESERVED_TICKET, Mockito.anyString(), anyInt(), anyInt(), anyFloat());
		 assertTrue(billet.getClass() == ReservedTicketCategory.class);
	}

	@Test
	public void testGetFreeBillet() {
		AbstractTicketCategory billet = TicketCategoryFactory.getTicketCategory(ConfigManager.FREE_TICKET,Mockito.anyString(), anyInt(), anyInt(), anyFloat());
		assertTrue(billet.getClass() == GeneralAdmissionTicketCategory.class);
	}
}
