package test.unit.controller;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;

import com.glo4003.project.database.exception.PersistException;
import com.glo4003.project.match.controller.MatchController;

public class MatchControllerTest {
	private MatchController controller;
	
	@Before
	public void initialize() {
		controller = spy(new MatchController());
	}
	
	@Test
	public void OnGetMatchListCanNavigateToMatchListView() throws PersistException {
		Model model = mock(Model.class);
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		String returned = controller.getMatchList(model, mockRequest);
		
		assertTrue(returned.contentEquals("matchsList"));
	}
	
	@Test
	public void OnGetMatchCanNavigateToMatchView() throws PersistException {
		Model model = mock(Model.class);
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		when(mockRequest.getParameter("matchID")).thenReturn("1");
		String returned = controller.getMatch(model, mockRequest);
		
		assertTrue(returned.contentEquals("match"));
	}
}
