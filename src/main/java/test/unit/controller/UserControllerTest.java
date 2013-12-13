package test.unit.controller;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;

import com.glo4003.project.match.dao.MatchModelDao;
import com.glo4003.project.ticket.helper.InstantiateTicketConverter;
import com.glo4003.project.user.controller.UserController;
import com.glo4003.project.user.helper.UserConverter;
import com.glo4003.project.user.model.view.LoginViewModel;
import com.glo4003.project.user.model.view.UserViewModel;
import com.glo4003.project.user.service.UserService;
import com.google.inject.Provider;

public class UserControllerTest {
	
	private UserService userService;
	
	private UserController controller;
	
	@Before
	public void initialize() {
		controller = new UserController();
		userService = mock(UserService.class);
		controller.dependanciesInjection(userService);
	}
	
	@Test
	public void OnGetNewUserCanNavigateToNewUserView() {
		Model model = mock(Model.class);
		String returned = controller.newUser(model);
		
		assertTrue(returned.contentEquals("newuser"));		
	}
	@Test
	public void updateUserTest() {
		Model model = mock(Model.class);
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		HttpSession mockSession = mock(HttpSession.class);
		when(mockRequest.getSession()).thenReturn(mockSession);
		when(mockSession.getAttribute("loggedUser")).thenReturn("1");
		String returned = controller.updateUser(model,mockRequest);
		
		assertTrue(returned.contentEquals("updateUser"));		
	}
	
}

