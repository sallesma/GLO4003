package model;

public class InstantiateGeneralAdmissionTicket extends InstantiateAbstractTicket {

	private int nbPlaces;
	
	public InstantiateGeneralAdmissionTicket(MatchModel match, int catIndex, int nbPlaces) {
		super(match, catIndex);
		this.nbPlaces = nbPlaces;
	}

	public boolean changeNbPlaces(int newNbPlaces, MatchModel match) {
		GeneralAdmissionTicketCategory tCAT = (GeneralAdmissionTicketCategory) match.getTickets().get(this.getCatIndex());
		if (tCAT.isTicketInstanciable("", newNbPlaces - this.getNbPlaces())) {
			tCAT.setNumberSoldTickets(tCAT.getNumberSoldTickets() - this.getNbPlaces());
			this.nbPlaces = newNbPlaces;
			tCAT.setNumberSoldTickets(tCAT.getNumberSoldTickets() + this.getNbPlaces());
			return true;
		}
		else {
			return false;
		}	
	}
	
	
	public int getNbPlaces() {
		return nbPlaces;
	}

	public void setNbPlaces(int nbPlaces) {
		this.nbPlaces = nbPlaces;
	}
	
}
