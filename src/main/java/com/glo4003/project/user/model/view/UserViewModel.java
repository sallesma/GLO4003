package com.glo4003.project.user.model.view;

import java.util.ArrayList;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.glo4003.project.database.model.SearchCriteriaModel;
import com.glo4003.project.global.HasWarning;
import com.glo4003.project.ticket.viewModel.InstantiateTicketViewModel;

public class UserViewModel extends HasWarning {
	
	public UserViewModel() {
		lastName = "";
		firstName = "";
		username = "";
		password = "";
		phoneNumber = "";
		address = "";
		isAdmin = false;
		tickets = new ArrayList<InstantiateTicketViewModel>();
		searchCriteria = new ArrayList<SearchCriteriaModel>();
		nbTickets = 0;
	}
	
	private Long id = 0L;
	@Size(min=1, message = "Entrez un nom de famille")
	private String lastName;
	@Size(min=1, message = "entrez un prenon")
	private String firstName;
	
	@Size(min=1, message = "Le nom d'utilisateur est trop court")
	private String username;
	@Size(min=3, message = "Le mot de passe est trop court")
	private String password;
	@Pattern(regexp="(?s)^[2-9]\\d{2}-\\d{3}-\\d{4}$", message = "le numero de telephone doit etre de la forme 999-999-9999")
	private String phoneNumber;
	private String address;
	private int nbTickets;
	private boolean isAdmin;
	private ArrayList<InstantiateTicketViewModel> tickets;
	private ArrayList<SearchCriteriaModel> searchCriteria;
	


	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getLastName() {
		return lastName;
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
	public void setIsAdmin(boolean isAdmin){
		this.isAdmin = isAdmin;
	}
	public ArrayList<InstantiateTicketViewModel> getTickets() {
		return tickets;
	}

	public void setTickets(ArrayList<InstantiateTicketViewModel> tickets) {
		this.tickets = tickets;
	}

	public ArrayList<SearchCriteriaModel> getSearchCriteria() {
		return searchCriteria;
	}

	public void setSearchCriteria(ArrayList<SearchCriteriaModel> searchCriteria) {
		this.searchCriteria = searchCriteria;
	}
	
	public String toString(){
		return this.firstName + " " + this.lastName;
	}

	public int getNbTickets() {
		return nbTickets;
	}

	public void setNbTickets(int nbTickets) {
		this.nbTickets = nbTickets;
	}
	
	
}
