package model;


public class GeneralAdmissionTicketCategory extends AbstractTicketCategory {

	public GeneralAdmissionTicketCategory() {
		super();
	}
	public GeneralAdmissionTicketCategory(String category, String name, int nbInitialTickets,
			int nbSoldTickets, float price) {
		super(category, name, nbInitialTickets, nbSoldTickets, price);		
	}
	
	public void remove(String placement, int nbPlace) {
		this.setNumberSoldTickets(this.getNumberSoldTickets() + nbPlace);
	}
	

}
