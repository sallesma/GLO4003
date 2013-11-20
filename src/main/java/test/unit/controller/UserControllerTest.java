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

import com.glo4003.project.user.controller.UserController;

public class UserControllerTest {
	
	private UserController controller;
	
	@Before
	public void initialize() {
		controller = spy(new UserController());
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

