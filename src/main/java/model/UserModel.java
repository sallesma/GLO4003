package model;

import java.util.ArrayList;

import com.thoughtworks.xstream.annotations.XStreamConverter;

import database.converter.XmlArrayListConverter;

public class UserModel implements ModelInterface {	
	
	private Long id = 0L;
	private String lastName;
	private String firstName;
	private String username;
	private String password;
	private String phoneNumber;
	private String address;
	private boolean isAdmin;	
	@XStreamConverter(XmlArrayListConverter.class)
	private ArrayList<InstantiateAbstractTicket> tickets;
	@XStreamConverter(XmlArrayListConverter.class)
	private ArrayList<SearchCriteriaModel> searchCriteria;
	
	public UserModel() {
		lastName = "";
		firstName = "";
		username = "";
		password = "";
		phoneNumber = "";
		address = "";
		isAdmin = false;
		tickets = new ArrayList<InstantiateAbstractTicket>();		
		searchCriteria = new ArrayList<SearchCriteriaModel>();
	}
	// Shopping cart methods
	/*
	public void addTicket(InstantiateTicketModel ticket) {
		this.tickets.add(ticket);
	}
	*/
	
	public void addTicket(InstantiateAbstractTicket ticket) {
		this.tickets.add(ticket);
	}
	
	public void deleteTicket(int id) {
		this.tickets.remove(id);
	}
	
	public void deleteTicket(InstantiateAbstractTicket ticket) {
		this.tickets.remove(ticket);
	}
	
	public InstantiateAbstractTicket getTicketById(int id) {
		InstantiateAbstractTicket res = null;
		for (InstantiateAbstractTicket t : this.tickets) {
			if(id == t.getTicketId())
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
