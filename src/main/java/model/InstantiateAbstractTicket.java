package model;

import java.util.concurrent.atomic.AtomicInteger;

import com.thoughtworks.xstream.annotations.XStreamConverter;

import database.converter.XmlObjectConverter;

public abstract class InstantiateAbstractTicket {
	
	 private static AtomicInteger nextTicketId = new AtomicInteger(0);
     private int ticketId;
     @XStreamConverter(XmlObjectConverter.class)
     private MatchModel match;
     
     public InstantiateAbstractTicket(MatchModel match) {
    	 this.ticketId = nextTicketId.incrementAndGet();
         this.match = match;
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
     
}
