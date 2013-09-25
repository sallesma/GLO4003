package model;

public abstract class BilletCategory {
	
	private String category;
	private String name;
	private float price;
	private int nbInitialTickets;
	private int nbSoldTickets;
	

	//Constructor 
	public BilletCategory(String category, String name, int nbInitialTickets,
			int nbSoldTickets, float price) {
		this.category = category;
		this.name = name;
		this.price = price;
		this.nbInitialTickets = nbInitialTickets;
		this.nbSoldTickets = nbSoldTickets;
	}
	
	public int getNbRemainingTickets() {
		return this.nbInitialTickets - this.nbSoldTickets;
	}
	// Getters and Setters
	public String getCategory() {
		return category;
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
	
	public int getNbInitialTickets() {
		return nbInitialTickets;
	}
	public void setNbInitialTickets(int nbInitialTickets) {
		this.nbInitialTickets = nbInitialTickets;
	}
	public int getNbSoldTickets() {
		return nbSoldTickets;
	}
	public void setNbSoldTickets(int nbSoldTickets) {
		this.nbSoldTickets = nbSoldTickets;
	}
}
