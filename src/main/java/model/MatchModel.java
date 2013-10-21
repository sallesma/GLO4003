package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import converter.XmlObjectConverter;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import config.ConfigManager.Gender;
import config.ConfigManager.Sports;

public class MatchModel implements ModelInterface {
	
	private Sports sport;
	private Gender gender;
	private Long id = 0L;
	private Date date;
	private String opponent;
	private String city;
	private String field;
	@XStreamImplicit(itemFieldName="AbstractTicketCategory")
	@XStreamConverter(XmlObjectConverter.class)
	private List<AbstractTicketCategory> tickets;
	
	
	public MatchModel () {
		this.setSport(null);
		this.setGender(null);
		this.setDate(null);
		this.setOpponent("");
		this.setCity("");
		this.setField("");
		this.setTickets(new ArrayList<AbstractTicketCategory>());
	}
	
	public MatchModel(Sports sport, Gender gender, Long matchID, Date date,
			String adversaire, String city, String terrain, List<AbstractTicketCategory> billets) {
		super();
		this.sport = sport;
		this.gender = gender;
		this.id = matchID;
		this.date = date;
		this.opponent = adversaire;
		this.city = city;
		this.field = terrain;
		this.setTickets(billets);
	}
	
	public void addTicketCategory(AbstractTicketCategory cat) {
		this.tickets.add(cat);
	}
	
	public int getNumberRemainingTickets() {
		int amount = 0;
		for (AbstractTicketCategory cat : this.tickets) {
			amount += cat.getNumberRemainingTickets();
		}
		return amount;
	}
	
	// Getter and Setter
	public Long getMatchID() {
		return id;
	}
	public void setMatchID(Long matchID) {
		this.id = matchID;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	public String getOpponent() {
		return opponent;
	}
	
	public void setOpponent(String opponent) {
		this.opponent = opponent;
	}
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	
	public Sports getSport() {
		return sport;
	}

	public void setSport(Sports sport) {
		this.sport = sport;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	public List<AbstractTicketCategory> getTickets() {
		return tickets;
	}

	public void setTickets(List<AbstractTicketCategory> tickets) {
		this.tickets = tickets;
	}

	@Override
	public Long getId() {		
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;		
	}
}
