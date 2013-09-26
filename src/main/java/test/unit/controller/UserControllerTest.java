package test.unit.controller;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import model.UserViewModel;

import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;

import com.glo4003.project.UserController;

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
		verify(model).addAttribute(anyString(), any(Object.class));
	}
}
