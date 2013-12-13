package com.glo4003.project.match.dao;

import java.util.List;

import com.glo4003.project.database.dao.DaoInterface;
import com.glo4003.project.database.dto.MatchDto;
import com.glo4003.project.database.dto.MatchDto.Sports;
import com.glo4003.project.database.exception.PersistException;

public interface MatchModelDaoInterface extends DaoInterface<MatchDto> {
	public List<MatchDto> getAllMatchsBySport(Sports sport) throws PersistException;
	
}
