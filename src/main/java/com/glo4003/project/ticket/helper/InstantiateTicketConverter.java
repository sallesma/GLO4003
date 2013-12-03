package com.glo4003.project.ticket.helper;

import com.glo4003.project.database.exception.PersistException;
import com.glo4003.project.database.model.AbstractTicketCategory;
import com.glo4003.project.database.model.ReservedTicketCategory;
import com.glo4003.project.match.dao.MatchModelDao;
import com.glo4003.project.ticket.viewModel.InstantiateGeneralAdmissionTicketViewModel;
import com.glo4003.project.ticket.viewModel.InstantiateReservedTicketViewModel;

import model.InstantiateGeneralAdmissionTicket;
import model.InstantiateReservedTicket;

public class InstantiateTicketConverter {

	private MatchModelDao mDao;
	
	
	public InstantiateGeneralAdmissionTicket convert(InstantiateGeneralAdmissionTicketViewModel instantiateGAVM) throws PersistException {
		
		InstantiateGeneralAdmissionTicket instantiateTicketModel = new InstantiateGeneralAdmissionTicket();
		instantiateTicketModel.setId(instantiateGAVM.getId());
		instantiateTicketModel.setCatIndex(instantiateGAVM.getCatIndex());
		
		if (this.mDao == null ) {
			this.mDao = new MatchModelDao();
		}
		instantiateTicketModel.setMatch(this.mDao.getById(instantiateGAVM.getMatchId()));
		instantiateTicketModel.setNbPlaces(instantiateGAVM.getNbPlaces());
		
		return instantiateTicketModel;
	}
	
	public InstantiateGeneralAdmissionTicketViewModel convert (InstantiateGeneralAdmissionTicket model) {
		InstantiateGeneralAdmissionTicketViewModel instantiateTicketViewModel = new InstantiateGeneralAdmissionTicketViewModel();
		instantiateTicketViewModel.setId(model.getId());
		instantiateTicketViewModel.setCatIndex(model.getCatIndex());
		
		instantiateTicketViewModel.setMatchId(model.getMatch().getId());
		instantiateTicketViewModel.setSport(model.getMatch().getSport());
		instantiateTicketViewModel.setField(model.getMatch().getField());
		instantiateTicketViewModel.setOpponent(model.getMatch().getOpponent());
		instantiateTicketViewModel.setCity(model.getMatch().getCity());
		instantiateTicketViewModel.setDate(model.getMatch().getDate());
		
		instantiateTicketViewModel.setNbPlaces(model.getNbPlaces());
		return instantiateTicketViewModel;
	}
	
	
public InstantiateReservedTicket convert(InstantiateReservedTicketViewModel instantiateRVM) throws PersistException {
		
		InstantiateReservedTicket instantiateTicketModel = new InstantiateReservedTicket();
		instantiateTicketModel.setId(instantiateRVM.getId());
		instantiateTicketModel.setCatIndex(instantiateRVM.getCatIndex());
		
		
		if (this.mDao == null ) {
			this.mDao = new MatchModelDao();
		}
		instantiateTicketModel.setMatch(this.mDao.getById(instantiateRVM.getMatchId()));
		instantiateTicketModel.setCorrespondingCat(this.mDao.getById(instantiateRVM.getMatchId()).getTickets().get(instantiateTicketModel.getCatIndex()));
		instantiateTicketModel.setNumPlace(instantiateRVM.getPlacement());
		
		return instantiateTicketModel;
	}
	
public InstantiateReservedTicketViewModel convert (InstantiateReservedTicket model) {
	
	InstantiateReservedTicketViewModel instantiateTicketViewModel = new InstantiateReservedTicketViewModel();
	instantiateTicketViewModel.setId(model.getId());
	instantiateTicketViewModel.setCatIndex(model.getCatIndex());
	instantiateTicketViewModel.setMatchId(model.getMatch().getId());
	
	ReservedTicketCategory rTC = new ReservedTicketCategory();
	rTC = (ReservedTicketCategory) model.getCorrespondingCat();
	instantiateTicketViewModel.setPlacements(rTC.getPlacements());
	instantiateTicketViewModel.setSport(model.getMatch().getSport());
	instantiateTicketViewModel.setField(model.getMatch().getField());
	instantiateTicketViewModel.setOpponent(model.getMatch().getOpponent());
	instantiateTicketViewModel.setCity(model.getMatch().getCity());
	instantiateTicketViewModel.setDate(model.getMatch().getDate());
	
	instantiateTicketViewModel.setPlacement(model.getNumPlace());
	return instantiateTicketViewModel;
}

	
}
