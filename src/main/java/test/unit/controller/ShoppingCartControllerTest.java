package test.unit.controller;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;

import com.glo4003.project.database.exception.PersistException;
import com.glo4003.project.database.model.MatchModel;
import com.glo4003.project.database.model.UserModel;
import com.glo4003.project.match.dao.MatchModelDao;
import com.glo4003.project.shoppingkart.controller.ShoppingCartController;
import com.glo4003.project.ticket.model.InstantiateAbstractTicket;
import com.glo4003.project.user.helper.UserConverter;
import com.glo4003.project.user.model.UserConcreteModel;
import com.glo4003.project.user.model.view.UserViewModel;

public class ShoppingCartControllerTest {
	private ShoppingCartController controller;
	private UserConverter userConverter;
	private MatchModelDao matchDao;
	
	@Before
	public void initialise() {
		controller = spy(new ShoppingCartController());
		
		userConverter = mock(UserConverter.class);
		controller.replaceUserConverter(userConverter);
	}
	
	@Test
	public void testBuyHandlePostsOnEmptySelection() throws PersistException {
		// Given
		Model model = mock(Model.class);
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		String action = "buy";
		
		HttpSession mockSession = mock(HttpSession.class);
		when(mockRequest.getSession()).thenReturn(mockSession);
		when(mockSession.getAttribute("loggedUser")).thenReturn(null);
		
		when(userConverter.convertFromView( (UserViewModel) org.mockito.Matchers.any())).thenReturn(mock(UserConcreteModel.class));
		
		when(mockRequest.getParameterValues("ticketId")).thenReturn(null);
		
		// When
		String returned = controller.handlePosts(model, mockRequest, action);

		// Then
		assertTrue(returned.contentEquals("shoppingCart"));
	}
	
	@Test
	public void testBuyHandlePosts() throws PersistException {
		// Given
		Model model = mock(Model.class);
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		String action = "buy";
		
		HttpSession mockSession = mock(HttpSession.class);
		when(mockRequest.getSession()).thenReturn(mockSession);
		when(mockSession.getAttribute("loggedUser")).thenReturn(null);
		
		when(userConverter.convertFromView( (UserViewModel) org.mockito.Matchers.any())).thenReturn(mock(UserConcreteModel.class));
		
		Mockito.doReturn( new String[]{"1"} ).when(mockRequest).getParameterValues("ticketId");
		
		// When
		String returned = controller.handlePosts(model, mockRequest, action);

		// Then
		assertTrue(returned.contentEquals("bill"));
	}
	
	@Test
	public void testDeleteHandlePosts() {
		// Given
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
		MatchModel match = mock(MatchModel.class);
		when(user.getTicketById( org.mockito.Matchers.anyInt())).thenReturn(ticket);
		when(ticket.getMatch()).thenReturn(match);

		matchDao = mock(MatchModelDao.class);
		controller.replaceMatchDAO(matchDao);
		try {
			when(matchDao.getById( org.mockito.Matchers.anyLong())).thenReturn(match);
		} catch (PersistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// When
		String returned = controller.handlePosts(model, mockRequest, action);

		// Then
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
		
		// When
		String returned = controller.handlePosts(model, mockRequest, action);

		// Then
		assertTrue(returned.contentEquals("redirect:/"));
	}

	@Test
	public void testPayment() {
		//Given
		Model model = mock(Model.class);
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		HttpSession mockSession = mock(HttpSession.class);
		when(mockRequest.getSession()).thenReturn(mockSession);
		when(mockSession.getAttribute("loggedUser")).thenReturn(null);
		
		//When
		String returned = controller.payment(model, mockRequest);
		
		//Then
		assertTrue(returned.contentEquals("payment"));
	}

	@Test
	public void testPayment_done() throws PersistException {
		// Given
		Model model = mock(Model.class);
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		
		HttpSession mockSession = mock(HttpSession.class);
		when(mockRequest.getSession()).thenReturn(mockSession);
		when(mockSession.getAttribute("loggedUser")).thenReturn(null);
		
		when(userConverter.convertFromView( (UserViewModel) org.mockito.Matchers.any())).thenReturn(mock(UserConcreteModel.class));
		
		when(mockRequest.getParameter("cardType")).thenReturn("Vasi");
		when(mockRequest.getParameter("cardOwner")).thenReturn("Martin");
		when(mockRequest.getParameter("cardNumber")).thenReturn("123456789");
		when(mockRequest.getParameter("expirationMonth")).thenReturn("02");
		when(mockRequest.getParameter("expirationYear")).thenReturn("2020");
		when(mockRequest.getParameter("cardCode")).thenReturn("123");

		// When
		String returned = controller.payment_done(model, mockRequest);

		// Then
		assertTrue(returned.contentEquals("shoppingCart"));
	}

	@Test
	public void testEmptyCart() throws PersistException {
		//Given
		Model model = mock(Model.class);
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		
		HttpSession mockSession = mock(HttpSession.class);
		when(mockRequest.getSession()).thenReturn(mockSession);
		when(mockSession.getAttribute("loggedUser")).thenReturn(null);
		
		when(userConverter.convertFromView( (UserViewModel) org.mockito.Matchers.any())).thenReturn(mock(UserConcreteModel.class));
		
		//When
		String returned = controller.emptyCart(model, mockRequest);

		//Then
		assertTrue(returned.contentEquals("shoppingCart"));
	}
}
