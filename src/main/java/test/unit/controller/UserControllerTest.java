package test.unit.controller;

import model.UserViewModel;

import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.glo4003.project.UserController;

public class UserControllerTest {
	
	UserController controller;
	
	@Before
	public void initialize() {
		controller = spy(new UserController());
	}
	
	@Test
	public void OnGetNewUserCanNavigateToNewUserView() {
		Model model = mock(Model.class);
		String returned = controller.newUser(model);
		
		assertTrue(returned.contentEquals("newuser"));
		verify(model).addAttribute(anyString(), any(UserViewModel.class));
	}
}
