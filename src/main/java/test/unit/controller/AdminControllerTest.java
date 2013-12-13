package test.unit.controller;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;

import com.glo4003.project.database.dto.AbstractTicketCategory;
import com.glo4003.project.database.dto.GeneralAdmissionTicketCategoryDto;
import com.glo4003.project.database.dto.MatchDto;
import com.glo4003.project.database.dto.ReservedTicketCategoryDto;
import com.glo4003.project.database.exception.PersistException;
import com.glo4003.project.match.dao.MatchModelDao;
import com.glo4003.project.ticket.category.factory.TicketCategoryFactory;
import com.glo4003.project.user.controller.AdminController;

public class AdminControllerTest {

	private AdminController controller = null;
	ArrayList<AbstractTicketCategory> billetsMatch = null;
	MatchDto match = null;
	MatchModelDao matchDao = null;
	ReservedTicketCategoryDto cat1 = null;
	GeneralAdmissionTicketCategoryDto cat2 = null;
	
	@Before
	public void initialize() {
		controller = new AdminController();
		
		cat1 = (ReservedTicketCategoryDto) TicketCategoryFactory.getTicketCategory(AbstractTicketCategory.RESERVED_TICKET, "Billet loges", 100, 0, 32);
		cat2 = (GeneralAdmissionTicketCategoryDto) TicketCategoryFactory.getTicketCategory(AbstractTicketCategory.FREE_TICKET, "Debout", 200, 0, 10);
		billetsMatch = new ArrayList<AbstractTicketCategory>();
		billetsMatch.add(cat1);
		billetsMatch.add(cat2);
		Calendar cal = Calendar.getInstance();
		cal.set(2010, 11, 11);
		match = new MatchDto(MatchDto.Sports.Football, MatchDto.Gender.M, (long) 0, cal.getTime(), "UQAM", "Québec", "ULaval", billetsMatch);
		
		matchDao = mock(MatchModelDao.class);
		controller.dependanciesInjection(matchDao);
	}

	@Test
	public void addPlacementTicketsTest() throws PersistException {
		//given
		Model model = mock(Model.class);
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		when(mockRequest.getParameter("matchID")).thenReturn("1");
		when(mockRequest.getParameter("newTicket")).thenReturn("1");
		when(mockRequest.getParameter("category")).thenReturn("1");
		when(matchDao.getById(anyLong())).thenReturn(match);
		
		//when
		String returned = controller.addPlacementTickets(model, mockRequest);

		//then
		assertTrue(returned.contentEquals("match"));
	}

	@Test
	public void addGeneralTicketsTest() throws PersistException {
		//given
		Model model = mock(Model.class);
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		when(mockRequest.getParameter("matchID")).thenReturn("1");
		when(mockRequest.getParameter("ticketNumber")).thenReturn("1");
		when(mockRequest.getParameter("category")).thenReturn("1");
		when(matchDao.getById(anyLong())).thenReturn(match);
		
		//when
		String returned = controller.addGeneralTickets(model, mockRequest);

		//then
		assertTrue(returned.contentEquals("match"));
	}

	@Test
	public void addPlacementTicketsAreAddedTest() throws PersistException {
		//given
		Model model = mock(Model.class);
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		when(mockRequest.getParameter("matchID")).thenReturn("0");
		when(mockRequest.getParameter("newTicket")).thenReturn("1000");
		when(mockRequest.getParameter("category")).thenReturn("Billet loges");
		when(matchDao.getById(anyLong())).thenReturn(match);
		//when
		controller.addPlacementTickets(model, mockRequest);

		//then
		assertTrue(cat1.getPlacements().contains("1000"));
	}

	@Test
	public void addGeneralTicketsAreAddedTest() throws PersistException {
		//given
		Model model = mock(Model.class);
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		when(mockRequest.getParameter("category")).thenReturn("Debout");
		when(mockRequest.getParameter("matchID")).thenReturn("0");
		when(mockRequest.getParameter("ticketNumber")).thenReturn("10");
		when(matchDao.getById(anyLong())).thenReturn(match);
		
		//when
		controller.addGeneralTickets(model, mockRequest);

		//then
		assertTrue(cat2.getNumberInitialTickets() == 210);
	}
}
