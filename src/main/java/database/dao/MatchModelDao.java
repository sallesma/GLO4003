package database.dao;

import java.util.ArrayList;
import java.util.List;

import model.MatchModel;
import model.UserModel;
import config.ConfigManager.Sports;
import database.XmlModelConverter;
import database.dto.FileAccess;
import exceptions.PersistException;

public class MatchModelDao extends Dao<MatchModel> {

	public MatchModelDao(XmlModelConverter dto, FileAccess fileAccess) {
		super(MatchModel.class, dto, fileAccess);	
	}

	public MatchModelDao() {
		super(MatchModel.class);		
	}
	
	public List<MatchModel> getAllMatchsBySport(Sports sport) throws PersistException {
		List<MatchModel> results = getAll();		
		for (MatchModel myMatch : results) {
			if (myMatch.getSport() == sport) {
				results.add(myMatch);		
			}			
		}
		
		return results;
	}
}
