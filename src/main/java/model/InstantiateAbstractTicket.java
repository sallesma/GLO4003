package model;

import java.util.concurrent.atomic.AtomicInteger;

import com.thoughtworks.xstream.annotations.XStreamConverter;

import database.converter.XmlObjectConverter;

public abstract class InstantiateAbstractTicket {
	
	 private static AtomicInteger nextTicketId = new AtomicInteger(0);
     private int ticketId;
     @XStreamConverter(XmlObjectConverter.class)
     private MatchModel match;
     private int catIndex;
     
     public InstantiateAbstractTicket(MatchModel match, int catIndex) {
    	 this.ticketId = nextTicketId.incrementAndGet();
         this.match = match;
         this.setCatIndex(catIndex);
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

	public int getCatIndex() {
		return catIndex;
	}

	public void setCatIndex(int catIndex) {
		this.catIndex = catIndex;
	}
     
}
