package model;

public class InstantiateGeneralAdmissionTicket extends InstantiateAbstractTicket {

	private int nbPlaces;
	
	public InstantiateGeneralAdmissionTicket(MatchModel match, int catIndex, int nbPlaces) {
		super(match, catIndex);
		this.nbPlaces = nbPlaces;
	}

	public int getNbPlaces() {
		return nbPlaces;
	}

	public void setNbPlaces(int nbPlaces) {
		this.nbPlaces = nbPlaces;
	}
	
}
