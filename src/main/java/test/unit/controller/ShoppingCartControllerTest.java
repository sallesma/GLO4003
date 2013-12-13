package test.unit.controller;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;

import com.glo4003.project.database.dto.MatchDto;
import com.glo4003.project.database.exception.PersistException;
import com.glo4003.project.match.dao.MatchModelDao;
import com.glo4003.project.shoppingkart.controller.ShoppingCartController;
import com.glo4003.project.ticket.helper.InstantiateTicketConverter;
import com.glo4003.project.ticket.model.InstantiateAbstractTicket;
import com.glo4003.project.user.helper.UserConverter;
import com.glo4003.project.user.model.UserConcreteModel;
import com.glo4003.project.user.model.view.UserViewModel;

public class ShoppingCartControllerTest {
	private ShoppingCartController controller;
	private UserConverter userConverter;
	private MatchModelDao matchDao;
	private InstantiateTicketConverter ticketConverter;
	
	@Before
	public void initialise() {
		controller = new ShoppingCartController();
		
		userConverter = mock(UserConverter.class);
		matchDao = mock(MatchModelDao.class);
		ticketConverter= mock(InstantiateTicketConverter.class);
		controller.dependanciesInjection(matchDao, userConverter, ticketConverter  );
	}
	
	@Test
	public void testBuyHandlePostsOnEmptySelection() throws PersistException {
		
		Model model = mock(Model.class);
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		String action = "buy";
		
		HttpSession mockSession = mock(HttpSession.class);
		when(mockRequest.getSession()).thenReturn(mockSession);
		when(mockSession.getAttribute("loggedUser")).thenReturn(null);
		
		when(userConverter.convertFromView( (UserViewModel) org.mockito.Matchers.any())).thenReturn(mock(UserConcreteModel.class));
		
		when(mockRequest.getParameterValues("ticketId")).thenReturn(null);
		
		String returned = controller.handlePosts(model, mockRequest, action);

		assertTrue(returned.contentEquals("shoppingCart"));
	}
	
	@Test
	public void testBuyHandlePosts() throws PersistException {
	
		Model model = mock(Model.class);
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		String action = "buy";
		
		HttpSession mockSession = mock(HttpSession.class);
		when(mockRequest.getSession()).thenReturn(mockSession);
		when(mockSession.getAttribute("loggedUser")).thenReturn(null);
		
		when(userConverter.convertFromView( (UserViewModel) org.mockito.Matchers.any())).thenReturn(mock(UserConcreteModel.class));
		
		Mockito.doReturn( new String[]{"1"} ).when(mockRequest).getParameterValues("ticketId");
		
		String returned = controller.handlePosts(model, mockRequest, action);

		assertTrue(returned.contentEquals("bill"));
	}
	
	@Test
	public void testDeleteHandlePosts() throws PersistException {
		
		Model model = mock(Model.class);
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		String action = "delete";
		
		HttpSession mockSession = mock(HttpSession.class);
		when(mockRequest.getSession()).thenReturn(mockSession);
		when(mockSession.getAttribute("loggedUser")).thenReturn(null);
		
		UserConcreteModel user = mock(UserConcreteModel.class);
		when(userConverter.convertFromView( (UserViewModel) org.mockito.Matchers.any())).thenReturn(user);
		
		Mockito.doReturn( new String[]{"1"} ).when(mockRequest).getParameterValues("ticketId");
		
		InstantiateAbstractTicket  ticket = mock(InstantiateAbstractTicket.class);
		MatchDto match = mock(MatchDto.class);
		when(user.getTicketById( org.mockito.Matchers.anyInt())).thenReturn(ticket);
		when(ticket.getMatch()).thenReturn(match);

		matchDao = mock(MatchModelDao.class);
		controller.replaceMatchDAO(matchDao);
		try {
			when(matchDao.getById( org.mockito.Matchers.anyLong())).thenReturn(match);
		} catch (PersistException e) {
			e.printStackTrace();
		}
		
		String returned = controller.handlePosts(model, mockRequest, action);
		
		assertTrue(returned.contentEquals("redirect:/shoppingCart"));
	}
	
	@Test
	public void testDefaultHandlePosts() throws PersistException {
		// Given
		Model model = mock(Model.class);
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		String action = "";
		
		HttpSession mockSession = mock(HttpSession.class);
		when(mockRequest.getSession()).thenReturn(mockSession);
		when(mockSession.getAttribute("loggedUser")).thenReturn(null);
		
		when(userConverter.convertFromView( (UserViewModel) org.mockito.Matchers.any())).thenReturn(mock(UserConcreteModel.class));
		
		Mockito.doReturn( new String[]{"1"} ).when(mockRequest).getParameterValues("ticketId");
		
		String returned = controller.handlePosts(model, mockRequest, action);
		
		assertTrue(returned.contentEquals("redirect:/"));
	}

	@Test
	public void testPayment() {
	
		Model model = mock(Model.class);
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		HttpSession mockSession = mock(HttpSession.class);
		when(mockRequest.getSession()).thenReturn(mockSession);
		when(mockSession.getAttribute("loggedUser")).thenReturn(null);
		
		String returned = controller.payment(model, mockRequest);
		
		assertTrue(returned.contentEquals("payment"));
	}

	@Test
	public void testPayment_done() throws PersistException {
	
		Model model = mock(Model.class);
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		
		HttpSession mockSession = mock(HttpSession.class);
		when(mockRequest.getSession()).thenReturn(mockSession);
		when(mockSession.getAttribute("loggedUser")).thenReturn(null);
		
		UserConcreteModel user = mock(UserConcreteModel.class);
		
		when(userConverter.convertFromView( (UserViewModel) org.mockito.Matchers.any())).thenReturn(user);
		
		when(user.getEmail()).thenReturn("a@aa.com");
		
		when(mockRequest.getParameter("cardType")).thenReturn("Vasi");
		when(mockRequest.getParameter("cardOwner")).thenReturn("Martin");
		when(mockRequest.getParameter("cardNumber")).thenReturn("123456789");
		when(mockRequest.getParameter("expirationMonth")).thenReturn("02");
		when(mockRequest.getParameter("expirationYear")).thenReturn("2020");
		when(mockRequest.getParameter("cardCode")).thenReturn("123");
		
		String returned = controller.payment_done(model, mockRequest);

		assertTrue(returned.contentEquals("shoppingCart"));
	}

	@Test
	public void testEmptyCart() throws PersistException {
		
		Model model = mock(Model.class);
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		
		HttpSession mockSession = mock(HttpSession.class);
		when(mockRequest.getSession()).thenReturn(mockSession);
		when(mockSession.getAttribute("loggedUser")).thenReturn(null);
		
		when(userConverter.convertFromView( (UserViewModel) org.mockito.Matchers.any())).thenReturn(mock(UserConcreteModel.class));
		
		String returned = controller.emptyCart(model, mockRequest);

		assertTrue(returned.contentEquals("shoppingCart"));
	}
}
