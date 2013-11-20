package test.unit.database;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import nu.xom.Element;

import org.junit.Before;
import org.junit.Test;

import com.glo4003.project.database.converter.XmlModelConverter;
import com.glo4003.project.database.dto.FileAccess;
import com.glo4003.project.database.exception.ConvertException;
import com.glo4003.project.database.exception.PersistException;
import com.glo4003.project.database.model.MatchModel;
import com.glo4003.project.match.dao.MatchModelDao;

public class MatchModelDaoTest {

	private MatchModelDao dao;
	private FileAccess fileAccess;
	private XmlModelConverter converter;	
	
	@Before
	public void bootStrap() throws PersistException {	
		List<MatchModel> models = new ArrayList<MatchModel>();
		models.add(new MatchModel());
		fileAccess = mock(FileAccess.class);
		FileAccess.replace(fileAccess);
		converter = mock(XmlModelConverter.class);		
		dao = spy(new MatchModelDao(converter, fileAccess));		
		
		configureFileAccess();
	}
	
	@Test
	public void canGetAllMatchsBySport() throws PersistException, ConvertException {
		//Before
		List<Element> list = new ArrayList<Element>();
		list.add(mock(Element.class));
		list.add(mock(Element.class));
		when(converter.toObject(any(Element.class))).thenReturn(new MatchModel());
		when(fileAccess.getAll(anyString())).thenReturn(list);
		
		//When
		List<MatchModel> models = dao.getAllMatchsBySport(MatchModel.Sports.Rugby);
		
		//Then
		verify(dao, times(1)).getAll();
		assertTrue(models.size() == 0);
	}
	
	private void configureFileAccess() throws PersistException {
		when(fileAccess.getNewId(anyString())).thenReturn(1L);
	}
}
