package test.unit.controller;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;

import com.glo4003.project.database.dto.SearchCriteriaDto;
import com.glo4003.project.database.exception.PersistException;
import com.glo4003.project.match.controller.MatchController;
import com.glo4003.project.match.dao.MatchModelDao;
import com.glo4003.project.match.helper.MatchConverter;
import com.glo4003.project.user.dao.UserModelDao;
import com.glo4003.project.user.helper.UserConverter;
import com.glo4003.project.user.model.view.UserViewModel;

public class MatchControllerTest {
	
	private MatchController controller;
	
	private MatchModelDao matchDao;
	private UserModelDao userDao;
	private UserConverter uConverter;
	private MatchConverter mConverter;
	
	
	@Before
	public void initialize() {
		controller = new MatchController();
		
		matchDao = mock(MatchModelDao.class);
		userDao = mock(UserModelDao.class);
		uConverter = mock(UserConverter.class);
		mConverter = mock(MatchConverter.class);
		
		controller.dependanciesInjection(matchDao, userDao, uConverter, mConverter);
	}
	
	@Test
	public void OnGetMatchListCanNavigateToMatchListView() throws PersistException {
		Model model = mock(Model.class);
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		
		UserViewModel userViewModel = mock(UserViewModel.class);
		
		HttpSession mockSession = mock(HttpSession.class);
		when(mockRequest.getSession()).thenReturn(mockSession);
		when(mockSession.getAttribute("loggedUser")).thenReturn(userViewModel);
		
		
		when(userViewModel.getSearchCriteria()).thenReturn(new ArrayList<SearchCriteriaDto>(0));
		
		
		String returned = controller.getMatchList(model, mockRequest);
		
		assertTrue(returned.contentEquals("matchsList"));
	}
	
	@Test
	public void OnGetMatchCanNavigateToMatchView() throws PersistException {
		Model model = mock(Model.class);
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		when(mockRequest.getParameter("matchID")).thenReturn("1");
		
		UserViewModel userViewModel = mock(UserViewModel.class);
		
		HttpSession mockSession = mock(HttpSession.class);
		when(mockRequest.getSession()).thenReturn(mockSession);
		when(mockSession.getAttribute("loggedUser")).thenReturn(userViewModel);
		
		
		when(userViewModel.getSearchCriteria()).thenReturn(new ArrayList<SearchCriteriaDto>(0));
		
		String returned = controller.getMatch(model, mockRequest);
		
		assertTrue(returned.contentEquals("match"));
	}
}
