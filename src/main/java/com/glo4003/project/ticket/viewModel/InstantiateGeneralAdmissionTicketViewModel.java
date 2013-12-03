package com.glo4003.project.ticket.viewModel;

public class InstantiateGeneralAdmissionTicketViewModel extends InstantiateTicketViewModel{

	private int nbPlaces;
	
	public InstantiateGeneralAdmissionTicketViewModel() {
		super();
		this.setNbPlaces(0);
	}

	public int getNbPlaces() {
		return nbPlaces;
	}

	public void setNbPlaces(int nbPlaces) {
		this.nbPlaces = nbPlaces;
	}
	
	
}	

