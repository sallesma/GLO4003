package com.glo4003.project.database.model;

import java.util.ArrayList;

import model.InstantiateGeneralAdmissionTicket;
import model.InstantiateReservedTicket;

import com.glo4003.project.database.converter.XmlArrayListConverter;
import com.glo4003.project.database.exception.ConvertException;
import com.glo4003.project.database.exception.PersistException;
import com.glo4003.project.global.ModelInterface;
import com.glo4003.project.match.dao.MatchModelDao;
import com.glo4003.project.ticket.model.InstantiateAbstractTicket;
import com.thoughtworks.xstream.annotations.XStreamConverter;

public class UserModel implements ModelInterface {	

	private Long id = 0L;
	private String lastName;
	private String firstName;
	private String username;
	private String password;
	private String email;
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
		email = "";
		phoneNumber = "";
		address = "";
		isAdmin = false;
		tickets = new ArrayList<InstantiateAbstractTicket>();		
		searchCriteria = new ArrayList<SearchCriteriaModel>();
	}

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
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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


}
