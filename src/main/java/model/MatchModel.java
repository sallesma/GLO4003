package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import config.ConfigManager.Genre;
import config.ConfigManager.Sports;

public class MatchModel {
	
	private Sports sport;
	private Genre gender;
	private int matchID;
	private Date date;
	private String adversaire;
	private String city;
	private String terrain;
	private List<BilletCategory> billets;
	
	
	public MatchModel () {
		this.setSport(null);
		this.setGender(null);
		this.setDate(null);
		this.setAdversaire("");
		this.setCity("");
		this.setTerrain("");
		this.setBillets(new ArrayList<BilletCategory>());
	}
	
	public MatchModel(Sports sport, Genre gender, int matchID, Date date,
			String adversaire, String city, String terrain, List<BilletCategory> billets) {
		super();
		this.sport = sport;
		this.gender = gender;
		this.matchID = matchID;
		this.date = date;
		this.adversaire = adversaire;
		this.city = city;
		this.terrain = terrain;
		this.setBillets(billets);
	}
	
	public void addBilletCategory(BilletCategory cat) {
		this.billets.add(cat);
	}
	
	public int getNbRemainingTickets() {
		int amount = 0;
		for (BilletCategory cat : this.billets) {
			amount += cat.getNbRemainingTickets();
		}
		return amount;
	}
	
	// Getter and Setter
	public int getMatchID() {
		return matchID;
	}
	public void setMatchID(int matchID) {
		this.matchID = matchID;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	public String getAdversaire() {
		return adversaire;
	}
	
	public void setAdversaire(String adversaire) {
		this.adversaire = adversaire;
	}
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getTerrain() {
		return terrain;
	}
	public void setTerrain(String terrain) {
		this.terrain = terrain;
	}
	
	public Sports getSport() {
		return sport;
	}


	public void setSport(Sports sport) {
		this.sport = sport;
	}


	public Genre getGender() {
		return gender;
	}


	public void setGender(Genre gender) {
		this.gender = gender;
	}
	
	public List<BilletCategory> getBillets() {
		return billets;
	}

	public void setBillets(List<BilletCategory> billets) {
		this.billets = billets;
	}
}
