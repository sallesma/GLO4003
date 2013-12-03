package com.glo4003.project.match.helper;

import com.glo4003.project.database.model.MatchModel;
import com.glo4003.project.match.viewModel.MatchViewModel;

public class MatchConverter {

	public MatchModel convert(MatchViewModel matchViewModel) {
		MatchModel matchModel = new MatchModel();
		matchModel.setId(matchViewModel.getId());
		matchModel.setGender(matchViewModel.getGender());
		matchModel.setSport(matchViewModel.getSport());
		matchModel.setCity(matchViewModel.getCity());
		matchModel.setDate(matchViewModel.getDate());
		matchModel.setField(matchViewModel.getField());
		matchModel.setOpponent(matchViewModel.getOpponent());
		matchModel.setTickets(matchViewModel.getTickets());
		return matchModel; 
	}
	
	public MatchViewModel convert(MatchModel matchModel) {
		MatchViewModel matchViewModel = new MatchViewModel();
		matchViewModel.setId(matchModel.getId());
		matchViewModel.setGender(matchModel.getGender());
		matchViewModel.setSport(matchModel.getSport());
		matchViewModel.setCity(matchModel.getCity());
		matchViewModel.setDate(matchModel.getDate());
		matchViewModel.setField(matchModel.getField());
		matchViewModel.setOpponent(matchModel.getOpponent());
		matchViewModel.setTickets(matchModel.getTickets());
		matchViewModel.setNumberRemainingTickets(matchModel.getNumberRemainingTickets());
		return matchViewModel; 
	}
}
