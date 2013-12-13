package test.unit.model;

import java.util.ArrayList;
import java.util.List;

import com.glo4003.project.database.dto.MatchDto;
import com.glo4003.project.database.exception.ConvertException;
import com.glo4003.project.database.exception.PersistException;
import com.glo4003.project.match.dao.MatchModelDaoInterface;

public class MockMatchModelDao implements MatchModelDaoInterface {

	private List<MatchDto> matchList = new ArrayList<MatchDto>();
	
	
	public MockMatchModelDao(List<MatchDto> matchList)
	{
		this.matchList = matchList;
	}
	
	@Override
	public void save(MatchDto model) throws PersistException,
			ConvertException {
		
	}

	@Override
	public MatchDto getById(Long id) throws PersistException,
			ConvertException {
 
		return null;
	}

	@Override
	public void delete(MatchDto model) throws PersistException,
			ConvertException {
		
	}

	@Override
	public void deleteById(Long id) throws PersistException, ConvertException {
		
	}

	@Override
	public List<MatchDto> getAll() throws PersistException {
		 
		return matchList;
	}

	@Override
	public List<MatchDto> getAllMatchsBySport(MatchDto.Sports sport)
			throws PersistException {
		 
		return null;
	}

}
