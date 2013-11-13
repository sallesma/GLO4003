package database.dao;

import java.util.List;

import model.MatchModel;
import config.ConfigManager.Sports;
import exceptions.PersistException;

public interface MatchModelDaoInterface extends DaoInterface<MatchModel> {
	public List<MatchModel> getAllMatchsBySport(Sports sport) throws PersistException;
	
}
