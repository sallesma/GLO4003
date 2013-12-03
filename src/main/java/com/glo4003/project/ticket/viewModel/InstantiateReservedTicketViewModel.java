package com.glo4003.project.ticket.viewModel;

import java.util.ArrayList;

public class InstantiateReservedTicketViewModel extends InstantiateTicketViewModel{

	private String placement;
	private ArrayList<String> placements;
	
	public InstantiateReservedTicketViewModel() {
		super();
		this.setPlacement("");
		this.setPlacements(new ArrayList<String>());
	}

	public String getPlacement() {
		return placement;
	}

	public void setPlacement(String placement) {
		this.placement = placement;
	}

	public ArrayList<String> getPlacements() {
		return placements;
	}

	public void setPlacements(ArrayList<String> placements) {
		this.placements = placements;
	}
	
	
}
