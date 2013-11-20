package com.glo4003.project.ticket.category.factory;

import com.glo4003.project.database.model.AbstractTicketCategory;
import com.glo4003.project.database.model.GeneralAdmissionTicketCategory;
import com.glo4003.project.database.model.ReservedTicketCategory;

public class TicketCategoryFactory {

	public static AbstractTicketCategory getTicketCategory (String ticketCategory, String name, int numberInitialTickets, int numberSoldTickets, float price) {
		if (ticketCategory.contains(AbstractTicketCategory.RESERVED_TICKET)) {
			
			return new ReservedTicketCategory(ticketCategory,name, numberInitialTickets, numberSoldTickets, price);
		}
		else {
			return new GeneralAdmissionTicketCategory(ticketCategory,name, numberInitialTickets, numberSoldTickets, price);
		}
	}
}
