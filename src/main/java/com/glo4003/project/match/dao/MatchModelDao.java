package com.glo4003.project.match.dao;

import java.util.ArrayList;
import java.util.List;

import com.glo4003.project.database.converter.XmlModelConverter;
import com.glo4003.project.database.dao.Dao;
import com.glo4003.project.database.dto.FileAccess;
import com.glo4003.project.database.exception.PersistException;
import com.glo4003.project.database.model.MatchModel;
import com.glo4003.project.database.model.MatchModel.Sports;

public class MatchModelDao extends Dao<MatchModel> implements MatchModelDaoInterface {

	public MatchModelDao(XmlModelConverter dto, FileAccess fileAccess) {
		super(MatchModel.class, dto, fileAccess);	
	}

	public MatchModelDao() {
		super(MatchModel.class);		
	}
	
	public List<MatchModel> getAllMatchsBySport(Sports sport) throws PersistException {
		List<MatchModel> results = new ArrayList<MatchModel>();		
		for (MatchModel myMatch : getAll()) {
			if (myMatch.getSport() == sport) {
				results.add(myMatch);		
			}
		}
		
		return results;
	}
}
