package model;

public class InstantiateReservedTicket extends InstantiateAbstractTicket {

	private String numPlace;
	public InstantiateReservedTicket(MatchModel match,int catIndex, String numPlace) {
		super(match, catIndex);
		this.numPlace = numPlace;
	}
	
	public String getNumPlace() {
		return numPlace;
	}
	public void setNumPlace(String numPlace) {
		this.numPlace = numPlace;
	}
	
}
