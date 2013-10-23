package test.unit.database;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.spy;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import model.GeneralAdmissionTicketCategory;
import model.MatchModel;
import model.ReservedTicketCategory;
import nu.xom.Element;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import test.unit.database.DaoTest.TestClass;
import test.unit.database.DaoTest.TestClass2;

import com.thoughtworks.xstream.XStream;

import config.ConfigManager.Gender;
import config.ConfigManager.Sports;
import database.XmlModelConverter;
import database.dao.MatchModelDao;
import database.dto.FileAccess;
import exceptions.ConvertException;
import exceptions.PersistException;

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
		List<MatchModel> models = dao.getAllMatchsBySport(Sports.Rugby);
		
		//Then
		verify(dao, times(1)).getAll();
		assertTrue(models.size() == 0);
	}
	
	private void configureFileAccess() throws PersistException {
		when(fileAccess.getNewId(anyString())).thenReturn(1L);
	}
}
