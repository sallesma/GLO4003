package com.glo4003.project.ticket.helper;

import com.glo4003.project.database.dto.AbstractTicketCategory;
import com.glo4003.project.database.dto.ReservedTicketCategoryDto;
import com.glo4003.project.database.exception.PersistException;
import com.glo4003.project.match.dao.MatchModelDao;
import com.glo4003.project.ticket.model.InstantiateGeneralAdmissionTicket;
import com.glo4003.project.ticket.model.InstantiateReservedTicket;
import com.glo4003.project.ticket.viewModel.InstantiateGeneralAdmissionTicketViewModel;
import com.glo4003.project.ticket.viewModel.InstantiateReservedTicketViewModel;
import com.google.inject.Inject;


public class InstantiateTicketConverter {
	@Inject
	private MatchModelDao mDao;
	
	
	public InstantiateGeneralAdmissionTicket convert(InstantiateGeneralAdmissionTicketViewModel instantiateGAVM) throws PersistException {
		
		InstantiateGeneralAdmissionTicket instantiateTicketModel = new InstantiateGeneralAdmissionTicket();
		instantiateTicketModel.setId(instantiateGAVM.getId());
		instantiateTicketModel.setCatIndex(instantiateGAVM.getCatIndex());
		
		instantiateTicketModel.setMatch(mDao.getById(instantiateGAVM.getMatchId()));
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
	
	ReservedTicketCategoryDto rTC = new ReservedTicketCategoryDto();
	rTC = (ReservedTicketCategoryDto) model.getCorrespondingCat();
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
