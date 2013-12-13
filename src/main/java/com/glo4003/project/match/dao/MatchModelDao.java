package com.glo4003.project.match.dao;

import java.util.ArrayList;
import java.util.List;

import com.glo4003.project.database.converter.XmlModelConverter;
import com.glo4003.project.database.dao.Dao;
import com.glo4003.project.database.dto.MatchDto;
import com.glo4003.project.database.dto.MatchDto.Sports;
import com.glo4003.project.database.exception.PersistException;
import com.glo4003.project.database.filesaccess.FileAccess;

public class MatchModelDao extends Dao<MatchDto> implements MatchModelDaoInterface {

	public MatchModelDao(XmlModelConverter dto, FileAccess fileAccess) {
		super(MatchDto.class, dto, fileAccess);	
	}

	public MatchModelDao() {
		super(MatchDto.class);		
	}
	
	public List<MatchDto> getAllMatchsBySport(Sports sport) throws PersistException {
		List<MatchDto> results = new ArrayList<MatchDto>();		
		for (MatchDto myMatch : getAll()) {
			if (myMatch.getSport() == sport) {
				results.add(myMatch);		
			}
		}
		
		return results;
	}
}
