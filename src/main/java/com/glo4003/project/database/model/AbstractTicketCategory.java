package com.glo4003.project.database.model;

import com.glo4003.project.global.ModelInterface;

public abstract class AbstractTicketCategory implements ModelInterface {
	
	public static final String RESERVED_TICKET = "réservé";
	public static final String FREE_TICKET = "libre";
	
	private Long id = 0L;	
	private String category;
	private String name;
	private float price;
	private int numberInitialTickets;
	private int numberSoldTickets;
	
	public AbstractTicketCategory() {
		
	}
	
	//Constructor 
	public AbstractTicketCategory(String category, String name, int numberInitialTickets,
			int numberSoldTickets, float price) {
		this.category = category;
		this.name = name;
		this.price = price;
		this.numberInitialTickets = numberInitialTickets;
		this.numberSoldTickets = numberSoldTickets;
	}
	
	public int getNumberRemainingTickets() {
		return this.numberInitialTickets - this.numberSoldTickets;
	}
	
	public abstract boolean isTicketInstanciable (String placement, int nbPlace);
	
	
	// Getters and Setters
	public String getCategory() {
		return category;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public float getPrice() {
		return price;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
	
	public int getNumberInitialTickets() {
		return numberInitialTickets;
	}
	public void setNumberInitialTickets(int numberInitialTickets) {
		this.numberInitialTickets = numberInitialTickets;
	}
	public int getNumberSoldTickets() {
		return numberSoldTickets;
	}
	public void setNumberSoldTickets(int numberSoldTickets) {
		this.numberSoldTickets = numberSoldTickets;
	}
}
