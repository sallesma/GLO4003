package model;

import java.util.concurrent.atomic.AtomicInteger;

import config.ConfigManager;


public class InstantiateTicketModel {
	
	private static AtomicInteger nextTicketId = new AtomicInteger(0);
	private int ticketId;
	private MatchModel match;
	private String numPlace;
	private int nbPlace;
	
	public InstantiateTicketModel(MatchModel match, int categoryIndex, String numPlace, int nbPlace) {
		this.ticketId = nextTicketId.incrementAndGet();
		this.match = match;
		this.numPlace = (match.getTickets().get(categoryIndex).getCategory().contains(ConfigManager.RESERVED_TICKET) ? numPlace : null);	
		this.nbPlace = (match.getTickets().get(categoryIndex).getCategory().contains(ConfigManager.FREE_TICKET) ? nbPlace : 1);
		removeFromMatch(match, categoryIndex, numPlace, nbPlace);
	}
	
	private void removeFromMatch(MatchModel match, int categoryIndex, String placement, int nbPlace){
		
		AbstractTicketCategory cat = match.getTickets().get(categoryIndex);
		cat.remove(placement, nbPlace);	
	}

	public int getTicketId() {
		return ticketId;
	}

	public MatchModel getMatch() {
		return match;
	}
	public void setMatch(MatchModel match) {
		this.match = match;
	}
	
	public String getNumPlace() {
		return numPlace;
	}
	public void setNumPlace(String numPlace) {
		this.numPlace = numPlace;
	}
	
	public int getNbPlace() {
		return nbPlace;
	}

	public void setNbPlace(int nbPlace) {
		this.nbPlace = nbPlace;
	}
	
}
