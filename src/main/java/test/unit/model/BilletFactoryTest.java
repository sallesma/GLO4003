package test.unit.model;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyFloat;
import static org.mockito.Matchers.anyInt;

import org.junit.Test;
import org.mockito.Mockito;

import com.glo4003.project.database.model.AbstractTicketCategory;
import com.glo4003.project.database.model.GeneralAdmissionTicketCategory;
import com.glo4003.project.database.model.ReservedTicketCategory;
import com.glo4003.project.ticket.category.factory.TicketCategoryFactory;

public class BilletFactoryTest {

	@Test
	public void testGetSeatedBillet() {
		 AbstractTicketCategory billet = TicketCategoryFactory.getTicketCategory(AbstractTicketCategory.RESERVED_TICKET, Mockito.anyString(), anyInt(), anyInt(), anyFloat());
		 assertTrue(billet.getClass() == ReservedTicketCategory.class);
	}

	@Test
	public void testGetFreeBillet() {
		AbstractTicketCategory billet = TicketCategoryFactory.getTicketCategory(AbstractTicketCategory.FREE_TICKET,Mockito.anyString(), anyInt(), anyInt(), anyFloat());
		assertTrue(billet.getClass() == GeneralAdmissionTicketCategory.class);
	}
}
