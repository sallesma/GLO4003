package model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserViewModel extends HasWarning {
	
	public UserViewModel() {
		lastName = "";
		firstName = "";
		username = "";
		password = "";
		phoneNumber = "";
		address = "";
		isAdmin = false;
		tickets = new ArrayList<InstantiateTicketModel>();
		searchCriteria = new ArrayList<SearchCriteriaModel>();
	}
	
	private Long id;
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
	
	private boolean isAdmin;
	private ArrayList<InstantiateTicketModel> tickets;
	private ArrayList<SearchCriteriaModel> searchCriteria;
	
	public int getNbTicketsInCart() {
		if (this.tickets != null) 
			return this.tickets.size();
		
		else 
			return 0;
	}

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
	public ArrayList<InstantiateTicketModel> getTickets() {
		return tickets;
	}

	public void setTickets(ArrayList<InstantiateTicketModel> tickets) {
		this.tickets = tickets;
	}

	public ArrayList<SearchCriteriaModel> getSearchCriteria() {
		return searchCriteria;
	}

	public void setSearchCriteria(ArrayList<SearchCriteriaModel> searchCriteria) {
		this.searchCriteria = searchCriteria;
	}
}
