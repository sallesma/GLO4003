package test.unit.model;

import java.util.ArrayList;
import java.util.List;

import com.glo4003.project.database.exception.ConvertException;
import com.glo4003.project.database.exception.PersistException;
import com.glo4003.project.database.model.MatchModel;
import com.glo4003.project.match.dao.MatchModelDaoInterface;

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
	public List<MatchModel> getAllMatchsBySport(MatchModel.Sports sport)
			throws PersistException {
		// TODO Auto-generated method stub
		return null;
	}

}
