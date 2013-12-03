package com.glo4003.project.match.helper;

import com.glo4003.project.database.model.MatchModel;
import com.glo4003.project.match.model.MatchConcreteModel;
import com.glo4003.project.match.viewModel.MatchViewModel;

public class MatchConverter {

	public MatchConcreteModel convertFromView(MatchViewModel matchViewModel) {
		MatchConcreteModel matchModel = new MatchConcreteModel();
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
	
	public MatchViewModel convertToView(MatchConcreteModel matchModel) {
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
	
	public MatchModel convertToDB(MatchConcreteModel matchConcreteModel) {
		MatchModel matchModel = new MatchModel();
		matchModel.setId(matchConcreteModel.getId());
		matchModel.setGender(matchConcreteModel.getGender());
		matchModel.setSport(matchConcreteModel.getSport());
		matchModel.setCity(matchConcreteModel.getCity());
		matchModel.setDate(matchConcreteModel.getDate());
		matchModel.setField(matchConcreteModel.getField());
		matchModel.setOpponent(matchConcreteModel.getOpponent());
		matchModel.setTickets(matchConcreteModel.getTickets());
		return matchModel; 
	}
	
	public MatchConcreteModel convertFromDB(MatchModel matchModel) {
		MatchConcreteModel matchConcreteModel = new MatchConcreteModel();
		matchConcreteModel.setId(matchModel.getId());
		matchConcreteModel.setGender(matchModel.getGender());
		matchConcreteModel.setSport(matchModel.getSport());
		matchConcreteModel.setCity(matchModel.getCity());
		matchConcreteModel.setDate(matchModel.getDate());
		matchConcreteModel.setField(matchModel.getField());
		matchConcreteModel.setOpponent(matchModel.getOpponent());
		matchConcreteModel.setTickets(matchModel.getTickets());
		return matchConcreteModel; 
	}

}
