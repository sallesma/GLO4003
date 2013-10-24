package model;

import java.util.concurrent.atomic.AtomicInteger;

import com.thoughtworks.xstream.annotations.XStreamConverter;

import config.ConfigManager;
import database.converter.XmlObjectConverter;
import database.dao.MatchModelDao;
import exceptions.ConvertException;
import exceptions.PersistException;


public class InstantiateTicketModel {
	
	private static AtomicInteger nextTicketId = new AtomicInteger(0);
	private int ticketId;
	@XStreamConverter(XmlObjectConverter.class)
	private MatchModel match;
	private String numPlace;
	private int nbPlace;
	private MatchModelDao matchModelDao = new MatchModelDao();
	
	public InstantiateTicketModel(MatchModel match, int categoryIndex, String numPlace, int nbPlace) throws PersistException, ConvertException {
		this.ticketId = nextTicketId.incrementAndGet();
		this.match = match;
		this.numPlace = (match.getTickets().get(categoryIndex).getCategory().contains(ConfigManager.RESERVED_TICKET) ? numPlace : null);	
		this.nbPlace = (match.getTickets().get(categoryIndex).getCategory().contains(ConfigManager.FREE_TICKET) ? nbPlace : 1);
		removeFromMatch(match, categoryIndex, numPlace, nbPlace);
		matchModelDao.save(match);
	}
	
	private void removeFromMatch(MatchModel match, int categoryIndex, String placement, int nbPlace){
		
		AbstractTicketCategory cat = match.getTickets().get(categoryIndex);
		 cat.remove(placement, nbPlace);	
	}

	public int getTicketId() {
		return ticketId;
	}

	public MatchModel getMatch() {
		return match;
	}
	public void setMatch(MatchModel match) {
		this.match = match;
	}
	
	public String getNumPlace() {
		return numPlace;
	}
	public void setNumPlace(String numPlace) {
		this.numPlace = numPlace;
	}
	
	public int getNbPlace() {
		return nbPlace;
	}

	public void setNbPlace(int nbPlace) {
		this.nbPlace = nbPlace;
	}
	
}
