package com.glo4003.project.ticket.model.factory;

import com.glo4003.project.database.model.AbstractTicketCategory;
import com.glo4003.project.database.model.GeneralAdmissionTicketCategory;
import com.glo4003.project.database.model.MatchModel;
import com.glo4003.project.database.model.ReservedTicketCategory;
import com.glo4003.project.ticket.model.InstantiateAbstractTicket;
import com.glo4003.project.ticket.model.InstantiateGeneralAdmissionTicket;
import com.glo4003.project.ticket.model.InstantiateReservedTicket;


public class InstantiateTicketFactory {

	public static InstantiateAbstractTicket getInstanciateTickets(int catIndex, MatchModel match, String place, int nbPlaces ) {
		
		AbstractTicketCategory ticketCategory = match.getTickets().get(catIndex);
		String type = ticketCategory.getCategory();
		
		if (type.contains(AbstractTicketCategory.RESERVED_TICKET)) {
			// Instantiate a reserved ticket
			
			InstantiateReservedTicket instantiateRTC = new InstantiateReservedTicket(match, catIndex, place);
			
			if (ticketCategory.isTicketInstanciable(place, nbPlaces)) {
				ReservedTicketCategory tCategory = (ReservedTicketCategory)ticketCategory;
				tCategory.remove(place);
				return instantiateRTC;
			}
			
			return null;
		}
		else{
			// Instantiate a general admission ticket
			InstantiateGeneralAdmissionTicket instantiateGAT = new InstantiateGeneralAdmissionTicket(match, catIndex, nbPlaces);
		
			if (ticketCategory.isTicketInstanciable(place, nbPlaces)) {
				GeneralAdmissionTicketCategory tCategory = (GeneralAdmissionTicketCategory)ticketCategory;
				tCategory.remove(nbPlaces);
				return instantiateGAT;
			}
			
			return null;
		}		
	}
}
