package com.glo4003.project.ticket.model;

import com.glo4003.project.database.model.MatchModel;
import com.glo4003.project.database.model.ReservedTicketCategory;

public class InstantiateReservedTicket extends InstantiateAbstractTicket {

	private String numPlace;
	
	public InstantiateReservedTicket() {
		super();
		this.setNumPlace("");
	}
	
	public InstantiateReservedTicket(MatchModel match,int catIndex, String numPlace) {
		super(match, catIndex);
		this.numPlace = numPlace;
	}
	
	public boolean changePlace(String placement, MatchModel match) {
		ReservedTicketCategory tCAT = (ReservedTicketCategory) match.getTickets().get(this.getCatIndex());
		if (tCAT.isTicketInstanciable(placement, 0)) {
			tCAT.getPlacements().add(this.getNumPlace());
			tCAT.getPlacements().remove(placement);
			this.numPlace = placement;
			return true;
		}
		else {
			return false;
		}	
	}
	
	public String getNumPlace() {
		return numPlace;
	}
	public void setNumPlace(String numPlace) {
		this.numPlace = numPlace;
	}
	
}
