package model;

import java.util.ArrayList;


public class ReservedTicketCategory extends AbstractTicketCategory {

	private ArrayList<String> placements;
	
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
	

}