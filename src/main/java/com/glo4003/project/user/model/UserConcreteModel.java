package com.glo4003.project.user.model;

import java.util.ArrayList;


import com.glo4003.project.database.converter.XmlArrayListConverter;
import com.glo4003.project.database.exception.ConvertException;
import com.glo4003.project.database.exception.PersistException;
import com.glo4003.project.database.model.AbstractTicketCategory;
import com.glo4003.project.database.model.GeneralAdmissionTicketCategory;
import com.glo4003.project.database.model.MatchModel;
import com.glo4003.project.database.model.ReservedTicketCategory;
import com.glo4003.project.database.model.SearchCriteriaModel;
import com.glo4003.project.match.dao.MatchModelDao;
import com.glo4003.project.ticket.model.InstantiateAbstractTicket;
import com.glo4003.project.ticket.model.InstantiateGeneralAdmissionTicket;
import com.glo4003.project.ticket.model.InstantiateReservedTicket;
import com.thoughtworks.xstream.annotations.XStreamConverter;

public class UserConcreteModel {
	private Long id = 0L;
	private String lastName;
	private String firstName;
	private String username;
	private String email;
	private String password;
	private String phoneNumber;
	private String address;
	private boolean isAdmin;	
	@XStreamConverter(XmlArrayListConverter.class)
	private ArrayList<InstantiateAbstractTicket> tickets;
	@XStreamConverter(XmlArrayListConverter.class)
	private ArrayList<SearchCriteriaModel> searchCriteria;

	public UserConcreteModel() {
		lastName = "";
		firstName = "";
		username = "";
		email= "";
		password = "";
		phoneNumber = "";
		address = "";
		isAdmin = false;
		tickets = new ArrayList<InstantiateAbstractTicket>();		
		searchCriteria = new ArrayList<SearchCriteriaModel>();
	}
	
	// Shopping cart methods
	public void addTicket(InstantiateAbstractTicket ticket) {
		this.tickets.add(ticket);
	}

	public void deleteTicketAndReplaceInMatch(int ticketId, MatchModel match) {

		InstantiateAbstractTicket t = this.getTicketById(ticketId);
		AbstractTicketCategory tCat = match.getTickets().get(t.getCatIndex());
	
		if (tCat instanceof GeneralAdmissionTicketCategory) {
			//General Admission Ticket
			GeneralAdmissionTicketCategory tGATCat = (GeneralAdmissionTicketCategory)tCat;
			InstantiateGeneralAdmissionTicket tGAT = (InstantiateGeneralAdmissionTicket)t;
			tGATCat.replace(tGAT.getNbPlaces());
			this.tickets.remove(t);
		}
		else {
			//Reserved Ticket
			ReservedTicketCategory tRTCat = (ReservedTicketCategory)tCat;
			InstantiateReservedTicket tRT = (InstantiateReservedTicket)t;
			tRTCat.replace(tRT.getNumPlace());
			this.tickets.remove(t);
		}
		
	}
	
	public void deleteTicketById(Long id) {

		InstantiateAbstractTicket t = this.getTicketById(id);
	
		if (t != null) {
			this.tickets.remove(t);
		}
		
	}
	
	public void deleteTicket(InstantiateAbstractTicket t) {
	
		if (t != null) {
			this.tickets.remove(t);
		}
		
	}


	public void emptyCartAndReplaceTickets(MatchModelDao matchDao) throws PersistException, ConvertException {

		for (InstantiateAbstractTicket t : this.getTickets()) {
			Long matchId = t.getMatch().getId();
			MatchModel match = matchDao.getById(matchId);
			AbstractTicketCategory tCat = match.getTickets().get(t.getCatIndex());
			
			if (tCat instanceof GeneralAdmissionTicketCategory) {
				//General Admission Ticket
				GeneralAdmissionTicketCategory tGATCat = (GeneralAdmissionTicketCategory)tCat;
				InstantiateGeneralAdmissionTicket tGAT = (InstantiateGeneralAdmissionTicket)t;
				tGATCat.replace(tGAT.getNbPlaces());
			}
			else {
				//Reserved Ticket
				ReservedTicketCategory tRTCat = (ReservedTicketCategory)tCat;
				InstantiateReservedTicket tRT = (InstantiateReservedTicket)t;
				tRTCat.replace(tRT.getNumPlace());
			}
			matchDao.save(match);
		}

		this.tickets.clear();

	}

	public InstantiateAbstractTicket getTicketById(long id) {
		InstantiateAbstractTicket res = null;
		for (InstantiateAbstractTicket t : this.tickets) {
			if(id == t.getId())
				res = t;
		}
		return res;
	}

	public int getNbTicketsInCart() {
		if (this.tickets != null) 
			return this.tickets.size();

		else 
			return 0;
	}



	//Getters and Setters 

	public String getLastName() {
		return lastName;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public ArrayList<InstantiateAbstractTicket> getTickets() {
		return tickets;
	}

	public void setTickets(ArrayList<InstantiateAbstractTicket> tickets) {
		this.tickets = tickets;
	}

	public ArrayList<SearchCriteriaModel> getSearchCriteria() {
		return searchCriteria;
	}

	public void setSearchCriterias(ArrayList<SearchCriteriaModel> searchCriteria) {
		this.searchCriteria = searchCriteria;
	}

	public void addSearchCriteria(SearchCriteriaModel model) {
		searchCriteria.add(model);
	}
}
