package com.glo4003.project.database.model;

public class GeneralAdmissionTicketCategory extends AbstractTicketCategory {

	public GeneralAdmissionTicketCategory() {
		super();
	}
	public GeneralAdmissionTicketCategory(String category, String name, int nbInitialTickets,
			int nbSoldTickets, float price) {
		super(category, name, nbInitialTickets, nbSoldTickets, price);		
	}
	
	public void remove(int nbPlace) {
		this.setNumberSoldTickets(this.getNumberSoldTickets() + nbPlace);
	}
	
	public void replace(int nbPlace) {
		this.setNumberSoldTickets(this.getNumberSoldTickets() - nbPlace);
	}
	
	@Override
	public boolean isTicketInstanciable(String placement, int nbPlace) {
		
		if (this.getNumberRemainingTickets() >= nbPlace) {
			return true;
		}
		return false;
	}
	

}
