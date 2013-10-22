package model;

import java.util.ArrayList;
import database.converter.*;

import com.thoughtworks.xstream.annotations.XStreamConverter;

public class UserModel implements ModelInterface {
	public UserModel() {
		lastName = "";
		firstName = "";
		username = "";
		password = "";
		phoneNumber = "";
		address = "";
		isAdmin = false;
		tickets = new ArrayList<InstantiateTicketModel>();
	}
	
	private Long id = 0L;
	private String lastName;
	private String firstName;
	private String username;
	private String password;
	private String phoneNumber;
	private String address;
	private boolean isAdmin;	
	@XStreamConverter(XmlArrayListConverter.class)
	private ArrayList<InstantiateTicketModel> tickets;
	
	
	// Shopping cart methods
	
	public void addTicket(InstantiateTicketModel ticket) {
		this.tickets.add(ticket);
	}
	
	public void deleteTicket(int id) {
		for (InstantiateTicketModel t : this.tickets) {
			if(id == t.getTicketId()) {
				this.tickets.remove(t);
				return;
			}
		}
	}
	
	public InstantiateTicketModel getTicketById(int id) {
		InstantiateTicketModel res = null;
		for (InstantiateTicketModel t : this.tickets) {
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
	
	public ArrayList<InstantiateTicketModel> getTickets() {
		return tickets;
	}

	public void setTickets(ArrayList<InstantiateTicketModel> tickets) {
		this.tickets = tickets;
	}
}
