package model;

public class InstantiateGeneralAdmissionTicket extends InstantiateAbstractTicket {

	private int nbPlaces;
	
	public InstantiateGeneralAdmissionTicket(MatchModel match, int nbPlaces) {
		super(match);
		this.nbPlaces = nbPlaces;
	}

	public int getNbPlaces() {
		return nbPlaces;
	}

	public void setNbPlaces(int nbPlaces) {
		this.nbPlaces = nbPlaces;
	}
	
}
