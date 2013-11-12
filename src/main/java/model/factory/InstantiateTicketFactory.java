package model.factory;

import config.ConfigManager;
import model.AbstractTicketCategory;
import model.GeneralAdmissionTicketCategory;
import model.InstantiateAbstractTicket;
import model.InstantiateGeneralAdmissionTicket;
import model.InstantiateReservedTicket;
import model.MatchModel;
import model.ReservedTicketCategory;

public class InstantiateTicketFactory {

	public static InstantiateAbstractTicket getInstanciateTickets(int catIndex, MatchModel match, String place, int nbPlaces ) {
		
		AbstractTicketCategory ticketCategory = match.getTickets().get(catIndex);
		String type = ticketCategory.getCategory();
		
		if (type.contains(ConfigManager.RESERVED_TICKET)) {
			// Instantiate a reserved ticket
			
			InstantiateReservedTicket instantiateRTC = new InstantiateReservedTicket(match, place);
			
			if (ticketCategory.isTicketInstanciable(place, nbPlaces)) {
				ReservedTicketCategory tCategory = (ReservedTicketCategory)ticketCategory;
				tCategory.remove(place);
				return instantiateRTC;
			}
			
			return null;
		}
		else{
			// Instantiate a general admission ticket
			InstantiateGeneralAdmissionTicket instantiateGAT = new InstantiateGeneralAdmissionTicket(match, nbPlaces);
		
			if (ticketCategory.isTicketInstanciable(place, nbPlaces)) {
				GeneralAdmissionTicketCategory tCategory = (GeneralAdmissionTicketCategory)ticketCategory;
				tCategory.remove(nbPlaces);
				return instantiateGAT;
			}
			
			return null;
		}
		
	}
}
