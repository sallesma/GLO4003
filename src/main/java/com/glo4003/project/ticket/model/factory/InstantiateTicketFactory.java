package com.glo4003.project.ticket.model.factory;

import com.glo4003.project.database.dto.AbstractTicketCategory;
import com.glo4003.project.database.dto.GeneralAdmissionTicketCategoryDto;
import com.glo4003.project.database.dto.MatchDto;
import com.glo4003.project.database.dto.ReservedTicketCategoryDto;
import com.glo4003.project.ticket.model.InstantiateAbstractTicket;
import com.glo4003.project.ticket.model.InstantiateGeneralAdmissionTicket;
import com.glo4003.project.ticket.model.InstantiateReservedTicket;


public class InstantiateTicketFactory {

	public static InstantiateAbstractTicket getInstanciateTickets(int catIndex, MatchDto match, String place, int nbPlaces ) {
		
		AbstractTicketCategory ticketCategory = match.getTickets().get(catIndex);
		String type = ticketCategory.getCategory();
		
		if (type.contains(AbstractTicketCategory.RESERVED_TICKET)) {
			// Instantiate a reserved ticket
			
			InstantiateReservedTicket instantiateRTC = new InstantiateReservedTicket(match, catIndex, place);
			
			if (ticketCategory.isTicketInstanciable(place, nbPlaces)) {
				ReservedTicketCategoryDto tCategory = (ReservedTicketCategoryDto)ticketCategory;
				tCategory.remove(place);
				return instantiateRTC;
			}
			
			return null;
		}
		else{
			// Instantiate a general admission ticket
			InstantiateGeneralAdmissionTicket instantiateGAT = new InstantiateGeneralAdmissionTicket(match, catIndex, nbPlaces);
		
			if (ticketCategory.isTicketInstanciable(place, nbPlaces)) {
				GeneralAdmissionTicketCategoryDto tCategory = (GeneralAdmissionTicketCategoryDto)ticketCategory;
				tCategory.remove(nbPlaces);
				return instantiateGAT;
			}
			
			return null;
		}		
	}
}
