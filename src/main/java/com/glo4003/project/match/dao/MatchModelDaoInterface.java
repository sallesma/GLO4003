package com.glo4003.project.match.dao;

import java.util.List;

import com.glo4003.project.database.dao.DaoInterface;
import com.glo4003.project.database.exception.PersistException;
import com.glo4003.project.database.model.MatchModel;
import com.glo4003.project.database.model.MatchModel.Sports;

public interface MatchModelDaoInterface extends DaoInterface<MatchModel> {
	public List<MatchModel> getAllMatchsBySport(Sports sport) throws PersistException;
	
}
