package test.unit.model;

import java.util.ArrayList;
import java.util.List;

import model.MatchModel;
import config.ConfigManager.Sports;
import database.dao.MatchModelDaoInterface;
import exceptions.ConvertException;
import exceptions.PersistException;

public class MockMatchModelDao implements MatchModelDaoInterface {

	private List<MatchModel> matchList = new ArrayList<MatchModel>();
	
	
	public MockMatchModelDao(List<MatchModel> matchList)
	{
		this.matchList = matchList;
	}
	
	@Override
	public void save(MatchModel model) throws PersistException,
			ConvertException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MatchModel getById(Long id) throws PersistException,
			ConvertException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(MatchModel model) throws PersistException,
			ConvertException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Long id) throws PersistException, ConvertException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<MatchModel> getAll() throws PersistException {
		// TODO Auto-generated method stub
		return matchList;
	}

	@Override
	public List<MatchModel> getAllMatchsBySport(Sports sport)
			throws PersistException {
		// TODO Auto-generated method stub
		return null;
	}

}
