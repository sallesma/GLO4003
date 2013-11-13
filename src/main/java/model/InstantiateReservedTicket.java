package model;

public class InstantiateReservedTicket extends InstantiateAbstractTicket {

	private String numPlace;
	public InstantiateReservedTicket(MatchModel match, String numPlace) {
		super(match);
		this.numPlace = numPlace;
	}
	
	public String getNumPlace() {
		return numPlace;
	}
	public void setNumPlace(String numPlace) {
		this.numPlace = numPlace;
	}
	
}
