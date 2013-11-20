package com.glo4003.project.database.model;

import java.util.ArrayList;

public class ReservedTicketCategory extends AbstractTicketCategory {

	private ArrayList<String> placements;
	
	public ReservedTicketCategory() {		
	}
	
	public ReservedTicketCategory(String cat, String name, int nbInitialTickets,
			int nbSoldTickets, float price) {
		super(cat, name, nbInitialTickets, nbSoldTickets, price);
		this.placements = new ArrayList<String>();
		
		for (int i =nbSoldTickets; i < nbInitialTickets; i++) {
			this.placements.add(String.valueOf(i));
		}		
	}

	public ArrayList<String> getPlacements() {
		return placements;
	}

	public void setPlacements(ArrayList<String> placements) {
		this.placements = placements;
	}
	
	public void remove(String placement) {
		// Now, User can select only one reserved ticket at time
		// nbPlace is always 1;
			this.placements.remove(placement);
			this.setNumberSoldTickets(this.getNumberSoldTickets() + 1);

	}
	
	public void replace (String placement) {
		this.placements.add(placement);
		this.setNumberSoldTickets(this.getNumberSoldTickets() - 1);
	}

	@Override
	public boolean isTicketInstanciable(String placement, int nbPlace) {
		if (this.getNumberRemainingTickets() >= 1 && this.getPlacements().contains(placement)){
			return true;
		}
		return false;
	}

}
