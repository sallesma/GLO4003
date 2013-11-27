package com.glo4003.project.ticket.model;

import java.util.concurrent.atomic.AtomicInteger;

import com.glo4003.project.database.converter.XmlObjectConverter;
import com.glo4003.project.database.model.AbstractTicketCategory;
import com.glo4003.project.database.model.MatchModel;
import com.glo4003.project.global.ModelInterface;
import com.thoughtworks.xstream.annotations.XStreamConverter;

public abstract class InstantiateAbstractTicket implements ModelInterface {
	
	 private static AtomicInteger nextTicketId = new AtomicInteger(0);
     private int ticketId;
     private Long id;
     @XStreamConverter(XmlObjectConverter.class)
     private MatchModel match;
     private int catIndex;
     private AbstractTicketCategory correspondingCat;
     
     
     public InstantiateAbstractTicket(MatchModel match, int catIndex) {
    	 this.ticketId = nextTicketId.incrementAndGet();
         this.match = match;
         this.setCatIndex(catIndex);
         this.correspondingCat = match.getTickets().get(catIndex);
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

	public AbstractTicketCategory getCorrespondingCat() {
		return correspondingCat;
	}

	public void setCorrespondingCat(AbstractTicketCategory correspondingCat) {
		this.correspondingCat = correspondingCat;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}



	
     
}
